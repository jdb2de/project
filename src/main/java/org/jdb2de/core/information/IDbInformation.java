package org.jdb2de.core.information;

import java.util.List;

/**
 *
 * @author  Rodrigo Tavares
 */
public interface IDbInformation {

    /**
     * Recover com database all table names
     *
     * @param regex A regular expression for table names, if null (or empty) query all tables
     * @return A list of all table names
     */
    List<String> getAllTables(String regex);

}
