package org.jdb2de.core.information;

import org.jdb2de.core.data.ColumnData;

import java.util.List;

/**
 *
 * @author  Rodrigo Tavares
 */
public interface IDatabaseInformation {

    /**
     * Query in the database all table names from the specified schema
     *
     * @param schema Database schema identification
     * @param regex A regular expression for table names, if null (or empty) query all tables
     * @return A list of all table names
     */
    List<String> allTables(String schema, String regex);

    /**
     * Check if a table exists
     * @param schema Database schema identification
     * @param tableName The name of a table
     * @return If table exists <code>true</code>, otherwise <code>false</code>
     */
    boolean checkIfTableExists(String schema, String tableName);

    /**
     *
     * @param schema Database schema identification
     * @param tableName
     * @return A list of {@link ColumnData}
     */
    List<ColumnData> tableColumns(String schema, String tableName);

    /**
     *
     * @param schema Database schema identification
     * @param tableName
     * @return A {@link String}
     */
    String tableComment(String schema, String tableName);

    /**
     *
     * @param schema Database schema identification
     * @param tableName
     * @param columnName
     * @return
     */
    String columnComment(String schema, String tableName, String columnName);

    /**
     *
     * @param dbType
     * @return
     */
    Class<?> translateDbType(String dbType);

}
