package org.jdb2de.core.data.enitity;

import org.jdb2de.core.data.database.TableData;

import java.io.Serializable;
import java.util.List;

public class EntityData implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -5480509444982670622L;

    private List<String> copyright;
    private String entityPackage;
    private String author;
    private String serialUid;
    private String name;
    private TableData table;
    private List<FieldData> fields;

    /**
     * @return
     */
    public List<String> getCopyright() {
        return copyright;
    }

    public void setCopyright(List<String> copyright) {
        this.copyright = copyright;
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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


}
