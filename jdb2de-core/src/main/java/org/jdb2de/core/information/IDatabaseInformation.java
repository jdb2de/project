package org.jdb2de.core.information;

import org.jdb2de.core.data.database.ColumnData;
import org.jdb2de.core.data.database.ForeignKeyData;

import java.util.List;

/**
 *
 * @author  Rodrigo Tavares
 */
public interface IDatabaseInformation {

    /**
     * Query in the database all table names from the specified schema
     *
     * @param regex A regular expression for table names, if null (or empty) query all tables
     * @return A list of all table names
     */
    List<String> allTables(String regex);

    /**
     * Check if a table exists
     * @param tableName The name of a table
     * @return If table exists <code>true</code>, otherwise <code>false</code>
     */
    boolean checkIfTableExists(String tableName);

    /**
     *
     * @param tableName
     * @return A list of {@link ColumnData}
     */
    List<ColumnData> tableColumns(String tableName);

    /**
     *
     * @param tableName
     * @return A {@link String}
     */
    String tableComment(String tableName);

    /**
     *
     * @param tableName
     * @param columnName
     * @return
     */
    String columnComment(String tableName, String columnName);

    /**
     *
     * @param tableName
     * @return
     */
    List<ForeignKeyData> tableForeignKeys(String tableName);

    /**
     *
     * @param databaseType
     * @return
     */
    String translateDbType(String databaseType);

}
