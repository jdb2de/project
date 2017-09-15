package org.jdb2de.core.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class ColumnModel implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 8330609921988234700L;

    private String name;
    private String type;
    private Integer index;
    private String comment;
    private boolean primaryKey;
    private ColumnParameterModel columnParameter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getComment() {
        return StringUtils.trimToEmpty(comment);
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ColumnParameterModel getColumnParameter() {
        return columnParameter;
    }

    public void setColumnParameter(ColumnParameterModel columnParameter) {
        this.columnParameter = columnParameter;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, type, index, comment, primaryKey, columnParameter);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("type", type)
                .add("index", index)
                .add("comment", comment)
                .add("primaryKey", primaryKey)
                .add("columnParameter", columnParameter)
                .toString();
    }
}
