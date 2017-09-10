package org.jdb2de.core.information.impl;

import com.google.common.base.Preconditions;
import org.jdb2de.core.DatabaseService;
import org.jdb2de.core.data.database.ColumnData;
import org.jdb2de.core.data.database.ForeignKeyData;
import org.jdb2de.core.information.IDatabaseInformation;
import org.jdb2de.core.util.LanguageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author  Rodrigo Tavares
 */
@Component(value = "PostgresInformation")
@PropertySource("classpath:/queries/postgresql.properties")
public class PostgresInformation implements IDatabaseInformation {

    @Value("${pg.all.tables}")
    private String sqlAllTables;

    @Value("${pg.check.if.table.exists}")
    private String sqlCheckIfTableExists;

    @Value("${pg.table.columns}")
    private String sqlTableColumns;

    @Value("${pg.table.foreign.keys}")
    private String sqlTableForeignKeys;

    @Value("${pg.table.oid}")
    private String sqlTableOid;

    @Value("${pg.column.index}")
    private String sqlColumnIndex;

    @Value("${pg.table.column.by.index}")
    private String sqlTableColumnByIndex;

    @Value("${pg.object.description}")
    private String sqlObjectDescription;

    @Value("${pg.column.description}")
    private String sqlColumnDescription;

    @Autowired
    private DatabaseService databaseService;
    private Map<String, String> types;

    @Override
    public List<String> allTables(String regex) {
        List<String> ls = new ArrayList<>();
        databaseService.getJdbc().query(sqlAllTables, LanguageUtils.toArray(databaseService.getSchema()),
                (rs, rowNum) -> rs.getString("relname")).forEach(ls::add);

        return ls;
    }

    @Override
    public boolean checkIfTableExists(String tableName) {
        int qtd = databaseService.getJdbc().queryForObject(sqlCheckIfTableExists,
                LanguageUtils.toArray(databaseService.getSchema(), tableName), Integer.class);
        return qtd > 0;
    }

    @Override
    public List<ColumnData> tableColumns(String tableName) {
        List<ColumnData> ls = new ArrayList<>();

        databaseService.getJdbc().query(sqlTableColumns, LanguageUtils.toArray(databaseService.getSchema(), tableName),
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

        databaseService.getJdbc().query(sqlTableForeignKeys, LanguageUtils.toArray(databaseService.getSchema(),
                tableName), (rs, rowNum) -> createForeignKeyData(tableName, rs)).forEach(ls::add);
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
        return databaseService.getJdbc().queryForObject(sqlTableOid, LanguageUtils.toArray(databaseService.getSchema(),
                tableName), Integer.class);
    }

    /**
     *
     * @param tableName Table name
     * @param columnName Column name
     * @return A {@link Integer} with column <code>oid</code>
     */
    private Integer columnIndex(String tableName, String columnName) {
        return databaseService.getJdbc().queryForObject(sqlColumnIndex,
                LanguageUtils.toArray(databaseService.getSchema(), tableName, columnName), Integer.class);
    }

    private ColumnData tableColumnByIndex(String tableName, Integer columnIndex) {
        return databaseService.getJdbc().queryForObject(sqlTableColumnByIndex,
                LanguageUtils.toArray(databaseService.getSchema(), tableName, columnIndex),
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
        return databaseService.getJdbc().queryForObject(sqlObjectDescription, LanguageUtils.toArray(objectOid),
                String.class);
    }

    /**
     *
     * @param tableOid
     * @param columnIndex
     * @return
     */
    private String columnDescription(Integer tableOid, Integer columnIndex) {
        return databaseService.getJdbc().queryForObject(sqlColumnDescription,
                LanguageUtils.toArray(tableOid, columnIndex), String.class);
    }

}
