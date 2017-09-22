package org.jdb2de.core.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;

/**
 *
 * @author Rodrigo Tavares
 */
public class TranslateTypeModel implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 7496350408506210160L;

    private String targetType;
    private String targetImport;
    private boolean lob;

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetImport() {
        return targetImport;
    }

    public void setTargetImport(String targetImport) {
        this.targetImport = targetImport;
    }

    public boolean isLob() {
        return lob;
    }

    public void setLob(boolean lob) {
        this.lob = lob;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(targetType, targetImport, lob);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("targetType", targetType)
                .add("targetImport", targetImport)
                .add("lob", lob)
                .toString();
    }
}
