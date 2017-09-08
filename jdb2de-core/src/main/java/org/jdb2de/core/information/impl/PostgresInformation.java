package org.jdb2de.core.information.impl;

import com.google.common.base.Preconditions;
import org.jdb2de.core.DatabaseService;
import org.jdb2de.core.data.database.ColumnData;
import org.jdb2de.core.data.database.ForeignKeyData;
import org.jdb2de.core.information.IDatabaseInformation;
import org.jdb2de.core.util.LanguageUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private DatabaseService databaseService;
    private Map<String, String> types;

    @Override
    public List<String> allTables(String regex) {
        List<String> ls = new ArrayList<>();
        String sql = ""
                + "select c.relname "
                + "  from pg_class c "
                + "  join pg_namespace n "
                + "    on c.relnamespace = n.oid "
                + " where c.relkind = 'r' "
                + "   and n.nspname = ?";
        databaseService.getJdbc().query(sql, LanguageUtils.toArray(databaseService.getSchema()),
                (rs, rowNum) -> rs.getString("relname")).forEach(ls::add);

        return ls;
    }

    @Override
    public boolean checkIfTableExists(String tableName) {
        String sql = ""
                + "select count(*) "
                + "  from pg_class c "
                + "  join pg_namespace n "
                + "    on c.relnamespace = n.oid "
                + " where c.relkind = 'r' "
                + "   and n.nspname = ? "
                + "   and c.relname = ?";
        int qtd = databaseService.getJdbc().queryForObject(sql, LanguageUtils.toArray(databaseService.getSchema(), tableName), Integer.class);
        return qtd > 0;
    }

    @Override
    public List<ColumnData> tableColumns(String tableName) {
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

        databaseService.getJdbc().query(sql, LanguageUtils.toArray(databaseService.getSchema(), tableName),
                (rs, rowNum) -> createColumnData(rs)).forEach(ls::add);
        return ls;
    }

    @Override
    public String tableComment(String tableName) {
        int oid = tableOid(tableName);
        return objectDescription(oid);
    }

    @Override
    public String columnComment(String tableName, String columnName) {
        int tableOid = tableOid(tableName);
        int columnIndex = columnIndex(tableName, columnName);
        return columnDescription(tableOid, columnIndex);
    }

    @Override
    public List<ForeignKeyData> tableForeignKeys(String tableName) {
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

        databaseService.getJdbc().query(sql, LanguageUtils.toArray(databaseService.getSchema(), tableName),
                (rs, rowNum) -> createForeignKeyData(tableName, rs)).forEach(ls::add);
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
     * @param tableName
     * @return
     */
    private Integer tableOid(String tableName) {
        String sql = ""
                + "select c.oid "
                + "  from pg_class c "
                + "  join pg_namespace n "
                + "    on c.relnamespace = n.oid "
                + " where c.relkind = 'r' "
                + "   and n.nspname = ? "
                + "   and c.relname = ?";
        return databaseService.getJdbc().queryForObject(sql, LanguageUtils.toArray(databaseService.getSchema(), tableName), Integer.class);
    }

    /**
     *
     * @param tableName Table name
     * @param columnName Column name
     * @return A {@link Integer} with column <code>oid</code>
     */
    private Integer columnIndex(String tableName, String columnName) {
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

        return databaseService.getJdbc().queryForObject(sql, LanguageUtils.toArray(databaseService.getSchema(), tableName, columnName), Integer.class);
    }

    private ColumnData tableColumnByIndex(String tableName, Integer columnIndex) {
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

        return databaseService.getJdbc().queryForObject(sql, LanguageUtils.toArray(databaseService.getSchema(), tableName, columnIndex),
                (rs, rowNum) -> createColumnData(rs));
    }

    private ColumnData createColumnData(ResultSet rs) throws SQLException {
        ColumnData columnData = new ColumnData();
        columnData.setName(rs.getString("attname"));
        columnData.setType(rs.getString("typname"));
        columnData.setIndex(rs.getInt("attnum"));
        return columnData;
    }

    private ForeignKeyData createForeignKeyData(String tableName, ResultSet rs) throws SQLException {

        ForeignKeyData foreignKeyData = new ForeignKeyData();
        foreignKeyData.setName(rs.getString("conname"));
        foreignKeyData.setTable(tableName);
        foreignKeyData.setReferenceTable(rs.getString("relname"));

        Array columnsArray = rs.getArray("conkey");
        Integer[] columnsIndexes = (Integer[]) columnsArray.getArray();

        for (int idx : columnsIndexes) {
            ColumnData columnData = tableColumnByIndex(tableName, idx);
            foreignKeyData.getColumns().add(columnData.getName());
        }

        Array referenceColumnsArray = rs.getArray("confkey");
        Integer[] referenceColumnsIndexes = (Integer[]) referenceColumnsArray.getArray();
        for (int idx : referenceColumnsIndexes) {
            ColumnData columnData = tableColumnByIndex(tableName, idx);
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
        return databaseService.getJdbc().queryForObject(sql, LanguageUtils.toArray(objectOid), String.class);
    }

    /**
     *
     * @param tableOid
     * @param columnIndex
     * @return
     */
    private String columnDescription(Integer tableOid, Integer columnIndex) {
        String sql = "select col_description(?, ?)";
        return databaseService.getJdbc().queryForObject(sql, LanguageUtils.toArray(tableOid, columnIndex), String.class);
    }

}
