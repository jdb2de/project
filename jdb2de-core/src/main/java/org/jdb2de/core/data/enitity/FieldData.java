package org.jdb2de.core.data.enitity;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.jdb2de.core.data.database.ColumnData;

import java.io.Serializable;

public class FieldData implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4531843591765897754L;

    private String name;
    private String upperName;
    private String type;
    private ColumnData column;

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

    /**
     *
     * @param type A {@link String} value
     */
    public void setType(String type) {
        this.type = type;
    }

    public ColumnData getColumn() {
        return column;
    }

    public void setColumn(ColumnData column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, upperName, type, column);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("upperName", upperName)
                .add("type", type)
                .add("column", column)
                .toString();
    }
}
