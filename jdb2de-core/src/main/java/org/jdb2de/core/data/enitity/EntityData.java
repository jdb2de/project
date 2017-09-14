package org.jdb2de.core.data.enitity;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.jdb2de.core.data.database.TableData;

import java.io.Serializable;
import java.util.List;

public class EntityData implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -5480509444982670622L;

    private String packageName;
    private String serialUid;
    private String name;
    private TableData table;
    private List<FieldData> fields;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public TableData getTable() {
        return table;
    }

    public void setTable(TableData table) {
        this.table = table;
    }

    public String getSerialUid() {
        return serialUid;
    }

    public void setSerialUid(String serialUid) {
        this.serialUid = serialUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FieldData> getFields() {
        return fields;
    }

    public void setFields(List<FieldData> fields) {
        this.fields = fields;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(packageName, serialUid, name, table, fields);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("packageName", packageName)
                .add("serialUid", serialUid)
                .add("name", name)
                .add("table", table)
                .add("fields", fields)
                .toString();
    }
}
