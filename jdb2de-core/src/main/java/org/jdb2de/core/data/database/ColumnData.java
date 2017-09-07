package org.jdb2de.core.data.database;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class ColumnData implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 8330609921988234700L;

    private String name;
    private String type;
    private Integer index;
    private String comment;
    private int size;
    private boolean nullable;
    private boolean primaryKey;

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

    public int getSize() {
        return size;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     *
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, type, index, comment, size, nullable, primaryKey);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("type", type)
                .add("index", index)
                .add("comment", comment)
                .add("size", size)
                .add("nullable", nullable)
                .add("primaryKey", primaryKey)
                .toString();
    }
}
