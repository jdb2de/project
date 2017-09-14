package org.jdb2de.core.data.database;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Rodrigo Tavares
 */
public class TableData implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -867165492491695312L;

    private String name;
    private String comment;
    private boolean compositeKey;
    private List<ColumnData> columns;

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

    public List<ColumnData> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnData> columns) {
        this.columns = columns;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, comment, compositeKey, columns);
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("comment", comment)
                .add("compositeKey", compositeKey)
                .add("columns", columns)
                .toString();
    }
}
