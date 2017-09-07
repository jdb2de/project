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
}
