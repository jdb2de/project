package org.jdb2de.core.information.impl;

import com.google.common.base.Preconditions;
import org.jdb2de.core.connection.DataSourceSettings;
import org.jdb2de.core.data.database.ColumnData;
import org.jdb2de.core.data.database.ForeignKeyData;
import org.jdb2de.core.information.IDatabaseInformation;
import org.jdb2de.core.util.LanguageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author  Rodrigo Tavares
 */
@Component
public class PostgresInformation implements IDatabaseInformation {

    @Autowired
    private DataSourceSettings dataSourceSettings;
    private JdbcTemplate jdbcTemplate;
    private Map<String, String> types;

    public JdbcTemplate getJdbcTemplate() {
        if (jdbcTemplate == null) {
            jdbcTemplate = new JdbcTemplate(dataSourceSettings.dataSource());
        }
        return jdbcTemplate;
    }

    @Override
    public List<String> allTables(String schema, String regex) {
        List<String> ls = new ArrayList<>();
        String sql = ""
                + "select c.relname "
                + "  from pg_class c "
                + "  join pg_namespace n "
                + "    on c.relnamespace = n.oid "
                + " where c.relkind = 'r' "
                + "   and n.nspname = ?";
        getJdbcTemplate().query(sql, LanguageUtils.toArray(schema),
                (rs, rowNum) -> rs.getString("relname")).forEach(ls::add);

        return ls;
    }

    @Override
    public boolean checkIfTableExists(String schema, String tableName) {
        String sql = ""
                + "select count(*) "
                + "  from pg_class c "
                + "  join pg_namespace n "
                + "    on c.relnamespace = n.oid "
                + " where c.relkind = 'r' "
                + "   and n.nspname = ? "
                + "   and c.relname = ?";
        int qtd = getJdbcTemplate().queryForObject(sql, LanguageUtils.toArray(schema, tableName), Integer.class);
        return qtd > 0;
    }

    @Override
    public List<ColumnData> tableColumns(String schema, String tableName) {
        List<ColumnData> ls = new ArrayList<>();
        String sql = ""
                + "  select a.attname "
                + "       , t.typname "
                + "       , a.attnum "
                + "    from pg_attribute a "
                + "    join pg_class c "
                + "      on a.attrelid = c.oid "
                + "    join pg_type t "
                + "      on a.atttypid = t.oid "
                + "    join pg_namespace n "
                + "      on c.relnamespace = n.oid "
                + "   where c.relkind = 'r' "
                + "     and n.nspname = ? "
                + "     and c.relname = ? "
                + "     and a.attnum > 0 "
                + "order by a.attnum";

        getJdbcTemplate().query(sql, LanguageUtils.toArray(schema, tableName),
                (rs, rowNum) -> createColumnData(rs)).forEach(ls::add);
        return ls;
    }

    @Override
    public String tableComment(String schema, String tableName) {
        int oid = tableOid(schema, tableName);
        return objectDescription(oid);
    }

    @Override
    public String columnComment(String schema, String tableName, String columnName) {
        int tableOid = tableOid(schema, tableName);
        int columnIndex = columnIndex(schema, tableName, columnName);
        return columnDescription(tableOid, columnIndex);
    }

    @Override
    public List<ForeignKeyData> tableForeignKeys(String schema, String tableName) {
        List<ForeignKeyData> ls = new ArrayList<>();
        String sql = ""
                + "select co.conname " +
                "       , cf.relname "
                + "     , co.conkey::int4[] "
                + "     , co.confkey::int4[] "
                + "  from pg_constraint co "
                + "  join pg_namespace n "
                + "    on co.connamespace = n.oid "
                + "  join pg_class c "
                + "    on co.conrelid = c.oid "
                + "  join pg_class cf "
                + "    on co.confrelid = cf.oid "
                + " where co.contype = 'f' "
                + "   and n.nspname = ? "
                + "   and c.relname = ?";

        getJdbcTemplate().query(sql, LanguageUtils.toArray(schema, tableName),
                (rs, rowNum) -> createForeignKeyData(tableName, schema, rs)).forEach(ls::add);
        return ls;
    }

    @Override
    public String translateDbType(String databaseType) {
        String result = getTypes().get(databaseType);
        Preconditions.checkNotNull(result, "Database type %s not mapped", databaseType);
        return result;
    }

    private Map<String, String> getTypes() {
        if (types == null) {
            types = new HashMap<>();
            types.put("varchar", String.class.getSimpleName());
            types.put("int2", Integer.class.getSimpleName());
            types.put("int4", Integer.class.getSimpleName());
            types.put("bool", Boolean.class.getSimpleName());
            types.put("timestamp", Date.class.getName());
            types.put("text", String.class.getSimpleName());
            types.put("bytea", "int[]");
        }
        return types;
    }

    /**
     *
     * @param schema Database schema identification
     * @param tableName
     * @return
     */
    private Integer tableOid(String schema, String tableName) {
        String sql = ""
                + "select c.oid "
                + "  from pg_class c "
                + "  join pg_namespace n "
                + "    on c.relnamespace = n.oid "
                + " where c.relkind = 'r' "
                + "   and n.nspname = ? "
                + "   and c.relname = ?";
        return getJdbcTemplate().queryForObject(sql, LanguageUtils.toArray(schema, tableName), Integer.class);
    }

    /**
     *
     * @param schema Database schema identification
     * @param tableName Table name
     * @param columnName Column name
     * @return A {@link Integer} with column <code>oid</code>
     */
    private Integer columnIndex(String schema, String tableName, String columnName) {
        String sql = ""
                + "select a.attnum "
                + "  from pg_attribute a "
                + "  join pg_class c "
                + "    on a.attrelid = c.oid "
                + "  join pg_namespace n "
                + "    on c.relnamespace = n.oid "
                + " where c.relkind = 'r' "
                + "   and n.nspname = ? "
                + "   and c.relname = ? "
                + "   and a.attname = ? "
                + "   and a.attnum > 0";

        return getJdbcTemplate().queryForObject(sql, LanguageUtils.toArray(schema, tableName, columnName), Integer.class);
    }

    private ColumnData tableColumnByIndex(String schema, String tableName, Integer columnIndex) {
        String sql = ""
                + "  select a.attname "
                + "       , t.typname "
                + "       , a.attnum "
                + "    from pg_attribute a "
                + "    join pg_class c "
                + "      on a.attrelid = c.oid "
                + "    join pg_type t "
                + "      on a.atttypid = t.oid "
                + "    join pg_namespace n "
                + "      on c.relnamespace = n.oid "
                + "   where c.relkind = 'r' "
                + "     and n.nspname = ? "
                + "     and c.relname = ? "
                + "     and a.attnum = ? ";

        return getJdbcTemplate().queryForObject(sql, LanguageUtils.toArray(schema, tableName, columnIndex),
                (rs, rowNum) -> createColumnData(rs));
    }

    private ColumnData createColumnData(ResultSet rs) throws SQLException {
        ColumnData columnData = new ColumnData();
        columnData.setName(rs.getString("attname"));
        columnData.setType(rs.getString("typname"));
        columnData.setIndex(rs.getInt("attnum"));
        return columnData;
    }

    private ForeignKeyData createForeignKeyData(String tableName, String schema, ResultSet rs) throws SQLException {

        ForeignKeyData foreignKeyData = new ForeignKeyData();
        foreignKeyData.setName(rs.getString("conname"));
        foreignKeyData.setTable(tableName);
        foreignKeyData.setReferenceTable(rs.getString("relname"));

        Array columnsArray = rs.getArray("conkey");
        Integer[] columnsIndexes = (Integer[]) columnsArray.getArray();

        for (int idx : columnsIndexes) {
            ColumnData columnData = tableColumnByIndex(schema, tableName, idx);
            foreignKeyData.getColumns().add(columnData.getName());
        }

        Array referenceColumnsArray = rs.getArray("confkey");
        Integer[] referenceColumnsIndexes = (Integer[]) referenceColumnsArray.getArray();
        for (int idx : referenceColumnsIndexes) {
            ColumnData columnData = tableColumnByIndex(schema, tableName, idx);
            foreignKeyData.getReferenceColumns().add(columnData.getName());
        }


        return foreignKeyData;
    }

    /**
     * Query object comment by <code>oid</code> identification
     * @param objectOid Object <code>oid</code> identification
     * @return A {@link String} with object comment
     */
    private String objectDescription(Integer objectOid) {
        String sql = "select obj_description(?)";
        return getJdbcTemplate().queryForObject(sql, LanguageUtils.toArray(objectOid), String.class);
    }

    /**
     *
     * @param tableOid
     * @param columnIndex
     * @return
     */
    private String columnDescription(Integer tableOid, Integer columnIndex) {
        String sql = "select col_description(?, ?)";
        return getJdbcTemplate().queryForObject(sql, LanguageUtils.toArray(tableOid, columnIndex), String.class);
    }

}
