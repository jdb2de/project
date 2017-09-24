package org.jdb2de.core.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.jdb2de.core.model.ColumnModel;

import java.io.Serializable;

public class FieldData extends AbstractFieldData implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4531843591765897754L;

    private ColumnModel column;

    public ColumnModel getColumn() {
        return column;
    }

    public void setColumn(ColumnModel column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName(), getUpperName(), getType(), column);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", getName())
                .add("upperName", getUpperName())
                .add("type", getType())
                .add("column", column)
                .toString();
    }
}
