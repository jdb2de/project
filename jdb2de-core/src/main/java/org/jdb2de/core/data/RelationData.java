package org.jdb2de.core.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RelationData extends AbstractFieldData implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -6145705843655597612L;

    private List<RelationReferenceData> columns;

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
        return Objects.hashCode(getName(), getUpperName(), getType(), columns);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", getName())
                .add("upperName", getUpperName())
                .add("type", getType())
                .add("columns", columns)
                .toString();
    }
}
