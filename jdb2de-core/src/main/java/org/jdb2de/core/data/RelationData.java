package org.jdb2de.core.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RelationData implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -6145705843655597612L;

    private String name;
    private String upperName;
    private String type;
    private List<RelationReferenceData> columns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpperName() {
        return upperName;
    }

    public void setUpperName(String upperName) {
        this.upperName = upperName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<RelationReferenceData> getColumns() {
        if (columns == null) {
            columns = new ArrayList<>();
        }
        return columns;
    }

    public void setColumns(List<RelationReferenceData> columns) {
        this.columns = columns;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, upperName, columns, type);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("upperName", upperName)
                .add("type", type)
                .add("columns", columns)
                .toString();
    }
}
