package org.jdb2de.core.data.enitity;

import org.jdb2de.core.data.database.ColumnData;

import java.io.Serializable;

public class FieldData implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4531843591765897754L;

    private String name;
    private String upperName;
    private Class type;
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

    public Class getType() {
        return type;
    }

    /**
     *
     * @param type A {@link Class} value
     */
    public void setType(Class type) {
        this.type = type;
    }

    public ColumnData getColumn() {
        return column;
    }

    public void setColumn(ColumnData column) {
        this.column = column;
    }
}
