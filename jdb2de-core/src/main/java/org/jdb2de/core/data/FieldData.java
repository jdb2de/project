package org.jdb2de.core.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.jdb2de.core.model.ColumnModel;

import java.io.Serializable;

public class FieldData implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4531843591765897754L;

    private String name;
    private String upperName;
    private String typeImport;
    private String type;
    private ColumnModel column;

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

    public String getTypeImport() {
        return typeImport;
    }

    public void setTypeImport(String typeImport) {
        this.typeImport = typeImport;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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
        return Objects.hashCode(name, upperName, typeImport, type, column);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("upperName", upperName)
                .add("typeImport", typeImport)
                .add("type", type)
                .add("column", column)
                .toString();
    }
}
