package org.jdb2de.core.information.impl;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.jdb2de.core.component.DatabaseConnection;
import org.jdb2de.core.information.IDatabaseInformation;
import org.jdb2de.core.model.ColumnModel;
import org.jdb2de.core.model.ColumnParameterModel;
import org.jdb2de.core.model.ForeignKeyModel;
import org.jdb2de.core.util.GeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author  Rodrigo Tavares
 */
@Component(value = "PostgresInformation")
@PropertySource("classpath:/queries/postgresql.properties")
public class PostgresInformation implements IDatabaseInformation {

    public static final String NULLABLE_FALSE = "YES";

    @Autowired
    private DatabaseConnection databaseConnection;

    @Value("${pg.all.tables}")
    private String sqlAllTables;

    @Value("${pg.table.primary.key.columns}")
    private String sqlTablePrimaryKeyColumns;

    @Value("${pg.check.if.table.exists}")
    private String sqlCheckIfTableExists;

    @Value("${pg.table.columns}")
    private String sqlTableColumns;

    @Value("${pg.table.column.parameters}")
    private String sqlTableColumnParameters;

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

    private Map<String, String> types;

    @Override
    public List<String> allTables(String regex) {
        List<String> ls = new ArrayList<>();
        databaseConnection.getJdbc().query(sqlAllTables, GeneratorUtils.toArray(databaseConnection.getSchema()),
                (rs, rowNum) -> rs.getString("relname")).forEach(ls::add);

        if (StringUtils.isNotEmpty(regex)) {
            ls = ls.stream().filter(s -> s.matches(regex)).collect(Collectors.toList());
        }

        return ls;
    }

    @Override
    public List<String> tablePrimaryKeyColumns(String tableName) {
        List<String> ls = new ArrayList<>();
        databaseConnection.getJdbc().query(sqlTablePrimaryKeyColumns,
                GeneratorUtils.toArray(databaseConnection.getSchema(), tableName),
                (rs, rowNum) -> rs.getString("attname")).forEach(ls::add);

        return ls;
    }

    @Override
    public boolean checkIfTableExists(String tableName) {
        int qtd = databaseConnection.getJdbc().queryForObject(sqlCheckIfTableExists,
                GeneratorUtils.toArray(databaseConnection.getSchema(), tableName), Integer.class);
        return qtd > 0;
    }

    @Override
    public List<ColumnModel> tableColumns(String tableName) {
        List<ColumnModel> ls = new ArrayList<>();

        databaseConnection.getJdbc().query(sqlTableColumns, GeneratorUtils.toArray(databaseConnection.getSchema(), tableName),
                (rs, rowNum) -> createColumnData(rs)).forEach(ls::add);
        return ls;
    }

    @Override
    public ColumnParameterModel columnParameters(String tableName, String columnName) {
        return databaseConnection.getJdbc().queryForObject(sqlTableColumnParameters,
                GeneratorUtils.toArray(databaseConnection.getSchema(), tableName, columnName),
                (rs, rowNum) -> createColumnParameterModel(rs));
    }

    private ColumnParameterModel createColumnParameterModel(ResultSet rs) throws SQLException {

        String nullable = rs.getString("is_nullable");

        Integer charSize = rs.getInt("character_maximum_length");
        if (rs.wasNull()) {
            charSize = null;
        }

        Integer numSize = rs.getInt("numeric_precision");
        Integer numPrecision = rs.getInt("numeric_scale");

        ColumnParameterModel columnParameter = new ColumnParameterModel();
        columnParameter.setNullable(NULLABLE_FALSE.equals(nullable));
        columnParameter.setSize(ObjectUtils.defaultIfNull(charSize, numSize));
        columnParameter.setPrecision(ObjectUtils.defaultIfNull(numPrecision, 0));

        return columnParameter;
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
    public List<ForeignKeyModel> tableForeignKeys(String tableName) {
        List<ForeignKeyModel> ls = new ArrayList<>();

        databaseConnection.getJdbc().query(sqlTableForeignKeys, GeneratorUtils.toArray(databaseConnection.getSchema(),
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
        return databaseConnection.getJdbc().queryForObject(sqlTableOid, GeneratorUtils.toArray(databaseConnection.getSchema(),
                tableName), Integer.class);
    }

    /**
     *
     * @param tableName Table name
     * @param columnName Column name
     * @return A {@link Integer} with column <code>oid</code>
     */
    private Integer columnIndex(String tableName, String columnName) {
        return databaseConnection.getJdbc().queryForObject(sqlColumnIndex,
                GeneratorUtils.toArray(databaseConnection.getSchema(), tableName, columnName), Integer.class);
    }

    private ColumnModel tableColumnByIndex(String tableName, Integer columnIndex) {
        return databaseConnection.getJdbc().queryForObject(sqlTableColumnByIndex,
                GeneratorUtils.toArray(databaseConnection.getSchema(), tableName, columnIndex),
                (rs, rowNum) -> createColumnData(rs));
    }

    private ColumnModel createColumnData(ResultSet rs) throws SQLException {
        ColumnModel columnModel = new ColumnModel();
        columnModel.setName(rs.getString("attname"));
        columnModel.setType(rs.getString("typname"));
        columnModel.setIndex(rs.getInt("attnum"));
        return columnModel;
    }

    private ForeignKeyModel createForeignKeyData(String tableName, ResultSet rs) throws SQLException {

        ForeignKeyModel foreignKeyModel = new ForeignKeyModel();
        foreignKeyModel.setName(rs.getString("conname"));
        foreignKeyModel.setTable(tableName);
        foreignKeyModel.setReferenceTable(rs.getString("relname"));

        Array columnsArray = rs.getArray("conkey");
        Integer[] columnsIndexes = (Integer[]) columnsArray.getArray();

        for (int idx : columnsIndexes) {
            ColumnModel columnModel = tableColumnByIndex(tableName, idx);
            foreignKeyModel.getColumns().add(columnModel.getName());
        }

        Array referenceColumnsArray = rs.getArray("confkey");
        Integer[] referenceColumnsIndexes = (Integer[]) referenceColumnsArray.getArray();
        for (int idx : referenceColumnsIndexes) {
            ColumnModel columnModel = tableColumnByIndex(tableName, idx);
            foreignKeyModel.getReferenceColumns().add(columnModel.getName());
        }


        return foreignKeyModel;
    }

    /**
     * Query object comment by <code>oid</code> identification
     * @param objectOid Object <code>oid</code> identification
     * @return A {@link String} with object comment
     */
    private String objectDescription(Integer objectOid) {
        return databaseConnection.getJdbc().queryForObject(sqlObjectDescription, GeneratorUtils.toArray(objectOid),
                String.class);
    }

    /**
     *
     * @param tableOid
     * @param columnIndex
     * @return
     */
    private String columnDescription(Integer tableOid, Integer columnIndex) {
        return databaseConnection.getJdbc().queryForObject(sqlColumnDescription,
                GeneratorUtils.toArray(tableOid, columnIndex), String.class);
    }

}
