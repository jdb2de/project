package org.jdb2de.core.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;

public class ColumnParameterModel implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -2752760036637161514L;

    private int size;
    private int precision;
    private boolean nullable;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }


    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(size, precision, nullable);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("size", size)
                .add("precision", precision)
                .add("nullable", nullable)
                .toString();
    }
}
