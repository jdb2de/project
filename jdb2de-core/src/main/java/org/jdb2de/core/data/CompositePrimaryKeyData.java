package org.jdb2de.core.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CompositePrimaryKeyData implements Serializable {

    private static final long serialVersionUID = 7195914624595225722L;

    private String packageName;
    private List<String> imports;
    private String serialUid;
    private String name;
    private List<FieldData> fields;
    private EntityData entity;

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

    public EntityData getEntity() {
        return entity;
    }

    public void setEntity(EntityData entity) {
        this.entity = entity;
    }

    public Collection<Object> getEmptyList() {
        return Collections.emptyList();
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(packageName, imports, serialUid, name, fields, entity);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("packageName", packageName)
                .add("imports", imports)
                .add("serialUid", serialUid)
                .add("name", name)
                .add("fields", fields)
                .add("entity", entity)
                .toString();
    }
}
