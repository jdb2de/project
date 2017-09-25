package org.jdb2de.core.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.jdb2de.core.model.TableModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EntityData implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -5480509444982670622L;

    private String packageName;
    private List<String> imports;
    private String serialUid;
    private String name;
    private TableModel table;
    private List<FieldData> fields;
    private List<RelationData> oneRelations;
    private List<RelationData> manyRelations;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<String> getImports() {
        if (imports == null) {
            imports = new ArrayList<>();
        }
        return imports;
    }

    public void setImports(List<String> imports) {
        this.imports = imports;
    }

    public TableModel getTable() {
        return table;
    }

    public void setTable(TableModel table) {
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
        if (fields == null) {
            fields = new ArrayList<>();
        }

        return fields;
    }

    public void setFields(List<FieldData> fields) {
        this.fields = fields;
    }

    public List<RelationData> getOneRelations() {
        if (oneRelations == null) {
            oneRelations = new ArrayList<>();
        }
        return oneRelations;
    }

    public List<RelationData> getManyRelations() {
        if (manyRelations == null) {
            manyRelations = new ArrayList<>();
        }
        return manyRelations;
    }

    public void setManyRelations(List<RelationData> manyRelations) {
        this.manyRelations = manyRelations;
    }

    public void setOneRelations(List<RelationData> oneRelations) {
        this.oneRelations = oneRelations;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(packageName, serialUid, name, table, fields, oneRelations, manyRelations);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("packageName", packageName)
                .add("serialUid", serialUid)
                .add("name", name)
                .add("table", table)
                .add("fields", fields)
                .add("oneRelations", oneRelations)
                .add("manyRelations", manyRelations)
                .toString();
    }
}
