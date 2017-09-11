package org.jdb2de.core.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Rodrigo Tavares
 */
@Component
@Scope(value = "singleton")
public class ParameterData implements Serializable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6661098610846285142L;

    private String entityPath;
    private String entityPackage;
    private String idPath;
    private String idPackage;
    private List<String> copyright;
    private String author;

    public String getEntityPath() {
        return entityPath;
    }

    public void setEntityPath(String entityPath) {
        this.entityPath = entityPath;
    }

    public String getEntityPackage() {
        return entityPackage;
    }

    public void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public String getIdPath() {
        return idPath;
    }

    public void setIdPath(String idPath) {
        this.idPath = idPath;
    }

    public String getIdPackage() {
        return idPackage;
    }

    public void setIdPackage(String idPackage) {
        this.idPackage = idPackage;
    }

    public List<String> getCopyright() {
        return copyright;
    }

    public void setCopyright(List<String> copyright) {
        this.copyright = copyright;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object obj) {
        return Objects.equal(this, obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                entityPath
                , entityPackage
                , idPath
                , idPackage
                , copyright
                , author
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("entityPath", entityPath)
                .add("entityPackage", entityPackage)
                .add("idPath", idPath)
                .add("idPackage", idPackage)
                .add("copyright", copyright)
                .add("author", author)
                .toString();
    }
}
