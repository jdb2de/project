package org.jdb2de.core.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodrigo Tavares
 */
public class TableModel implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -867165492491695312L;

    private String schema;
    private String catalog;
    private String name;
    private String comment;
    private boolean compositeKey;
    private List<ColumnModel> columns;
    private List<ForeignKeyModel> foreignKeys;

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return StringUtils.trimToEmpty(comment);
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isCompositeKey() {
        return compositeKey;
    }

    public void setCompositeKey(boolean compositeKey) {
        this.compositeKey = compositeKey;
    }

    public List<ColumnModel> getColumns() {
        if (columns == null) {
            columns = new ArrayList<>();
        }
        return columns;
    }

    public void setColumns(List<ColumnModel> columns) {
        this.columns = columns;
    }

    public List<ForeignKeyModel> getForeignKeys() {
        if (foreignKeys == null) {
            foreignKeys = new ArrayList<>();
        }
        return foreignKeys;
    }

    public void setForeignKeys(List<ForeignKeyModel> foreignKeys) {
        this.foreignKeys = foreignKeys;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(schema, catalog, name, comment, compositeKey, columns);
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("schema", schema)
                .add("catalog", catalog)
                .add("name", name)
                .add("comment", comment)
                .add("compositeKey", compositeKey)
                .add("columns", columns)
                .toString();
    }
}
