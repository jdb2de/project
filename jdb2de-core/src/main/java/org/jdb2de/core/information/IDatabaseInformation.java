package org.jdb2de.core.information;

import org.jdb2de.core.model.ColumnModel;
import org.jdb2de.core.model.ColumnParameterModel;
import org.jdb2de.core.model.ForeignKeyModel;

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
     *
     * @param tableName Table name
     * @return A list of columns
     */
    List<String> tablePrimaryKeyColumns(String tableName);

    /**
     * Check if a table exists
     * @param tableName The name of a table
     * @return If table exists <code>true</code>, otherwise <code>false</code>
     */
    boolean checkIfTableExists(String tableName);

    /**
     *
     * @param tableName
     * @return A list of {@link ColumnModel}
     */
    List<ColumnModel> tableColumns(String tableName);

    /**
     *
     * @param tableName Table Name
     * @param columnName Column name
     * @return
     */
    ColumnParameterModel columnParameters(String tableName, String columnName);

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
    List<ForeignKeyModel> tableForeignKeys(String tableName);

    /**
     *
     * @param databaseType
     * @return
     */
    String translateDbType(String databaseType);

}
