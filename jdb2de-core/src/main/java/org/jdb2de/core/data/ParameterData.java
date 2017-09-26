package org.jdb2de.core.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
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
    private String entitySuffix;
    private String compositePrimaryKeyPackage;
    private List<String> copyright;
    private String author;
    private String tableSearch;
    private String tableName;
    private String primaryKeyFieldNameRegex;
    private String tableNameRegex;
    private boolean tableNameRegexCleanEntityName;
    private boolean tableNameRegexCleanPrimaryKeyField;

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

    public String getEntitySuffix() {
        return entitySuffix;
    }

    public void setEntitySuffix(String entitySuffix) {
        this.entitySuffix = entitySuffix;
    }

    public String getCompositePrimaryKeyPackage() {
        return compositePrimaryKeyPackage;
    }

    public void setCompositePrimaryKeyPackage(String compositePrimaryKeyPackage) {
        this.compositePrimaryKeyPackage = compositePrimaryKeyPackage;
    }

    public List<String> getCopyright() {
        if (copyright == null) {
            copyright = new ArrayList<>();
        }
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

    public String getPrimaryKeyFieldNameRegex() {
        return primaryKeyFieldNameRegex;
    }

    public void setPrimaryKeyFieldNameRegex(String primaryKeyFieldNameRegex) {
        this.primaryKeyFieldNameRegex = primaryKeyFieldNameRegex;
    }

    public String getTableNameRegex() {
        return tableNameRegex;
    }

    public void setTableNameRegex(String tableNameRegex) {
        this.tableNameRegex = tableNameRegex;
    }

    public boolean isTableNameRegexCleanEntityName() {
        return tableNameRegexCleanEntityName;
    }

    public void setTableNameRegexCleanEntityName(boolean tableNameRegexCleanEntityName) {
        this.tableNameRegexCleanEntityName = tableNameRegexCleanEntityName;
    }

    public boolean isTableNameRegexCleanPrimaryKeyField() {
        return tableNameRegexCleanPrimaryKeyField;
    }

    public void setTableNameRegexCleanPrimaryKeyField(boolean tableNameRegexCleanPrimaryKeyField) {
        this.tableNameRegexCleanPrimaryKeyField = tableNameRegexCleanPrimaryKeyField;
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
                , entitySuffix
                , compositePrimaryKeyPackage
                , copyright
                , author
                , tableSearch
                , tableName
                , primaryKeyFieldNameRegex
                , tableNameRegex
                , tableNameRegexCleanEntityName
                , tableNameRegexCleanPrimaryKeyField
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("entityPath", entityPath)
                .add("entityPackage", entityPackage)
                .add("entitySuffix", entitySuffix)
                .add("compositePrimaryKeyPackage", compositePrimaryKeyPackage)
                .add("copyright", copyright)
                .add("author", author)
                .add("tableSearch", tableSearch)
                .add("tableName", tableName)
                .add("primaryKeyFieldNameRegex", primaryKeyFieldNameRegex)
                .add("tableNameRegex", tableNameRegex)
                .add("tableNameRegexCleanEntityName", tableNameRegexCleanEntityName)
                .add("tableNameRegexCleanPrimaryKeyField", tableNameRegexCleanPrimaryKeyField)
                .toString();
    }
}
