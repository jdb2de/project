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
    private String referenceTable;
    private List<ForeignKeyColumnModel> relations;

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

    public String getReferenceTable() {
        return referenceTable;
    }

    public void setReferenceTable(String referenceTable) {
        this.referenceTable = referenceTable;
    }

    public List<ForeignKeyColumnModel> getRelations() {
        if (relations == null) {
            relations = new ArrayList<>();
        }
        return relations;
    }

    public void setRelations(List<ForeignKeyColumnModel> relations) {
        this.relations = relations;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, table, referenceTable, relations);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("table", table)
                .add("referenceTable", referenceTable)
                .add("relations", relations)
                .toString();
    }
}
