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
    private String pkPath;
    private String pkPackage;
    private List<String> copyright;
    private String author;
    private String tableSearch;
    private String tableName;

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

    public String getPkPath() {
        return pkPath;
    }

    public void setPkPath(String pkPath) {
        this.pkPath = pkPath;
    }

    public String getPkPackage() {
        return pkPackage;
    }

    public void setPkPackage(String pkPackage) {
        this.pkPackage = pkPackage;
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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableSearch() {
        return tableSearch;
    }

    public void setTableSearch(String tableSearch) {
        this.tableSearch = tableSearch;
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
                , pkPath
                , pkPackage
                , copyright
                , author
                , tableSearch
                , tableName
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("entityPath", entityPath)
                .add("entityPackage", entityPackage)
                .add("pkPath", pkPath)
                .add("pkPackage", pkPackage)
                .add("copyright", copyright)
                .add("author", author)
                .add("tableSearch", tableSearch)
                .add("tableName", tableName)
                .toString();
    }
}
