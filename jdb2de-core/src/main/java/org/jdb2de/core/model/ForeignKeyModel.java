package org.jdb2de.core.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author  Rodrigo Tavares
 */
public class ForeignKeyModel implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5526158702379112982L;

    private String name;
    private String table;
    private List<String> columns;
    private String referenceTable;
    private List<String> referenceColumns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<String> getColumns() {
        if (columns == null) {
            columns = new ArrayList<>();
        }
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public String getReferenceTable() {
        return referenceTable;
    }

    public void setReferenceTable(String referenceTable) {
        this.referenceTable = referenceTable;
    }

    public List<String> getReferenceColumns() {
        if (referenceColumns == null) {
            referenceColumns = new ArrayList<>();
        }
        return referenceColumns;
    }

    public void setReferenceColumns(List<String> referenceColumns) {
        this.referenceColumns = referenceColumns;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, table, columns, referenceTable, referenceColumns);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("table", table)
                .add("columns", columns)
                .add("referenceTable", referenceTable)
                .add("referenceColumns", referenceColumns)
                .toString();
    }
}
