package org.jdb2de.core.data;

import java.io.Serializable;

/**
 *
 * @author Rodrigo Tavares
 */
public class ParametersData implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6661098610846285142L;

    private String schema;
    private String catalog;
    private String entityPackage;
    private String idPackage;

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

    public String getEntityPackage() {
        return entityPackage;
    }

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public String getIdPackage() {
        return idPackage;
    }

    public void setIdPackage(String idPackage) {
        this.idPackage = idPackage;
    }
}
