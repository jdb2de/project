package org.jdb2de.core.factory;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import org.jdb2de.core.data.EntityData;
import org.jdb2de.core.data.FieldData;
import org.jdb2de.core.model.ColumnModel;
import org.jdb2de.core.model.TableModel;
import org.jdb2de.core.model.TranslateTypeModel;
import org.jdb2de.core.util.GeneratorUtils;

import java.util.List;

/**
 *
 * @author Rodrigo Tavares
 */
public final class GeneratorFactory {

    /**
     * Constructor omitted
     */
    private GeneratorFactory() {
    }

    /**
     * Create a new instance of {@link TableModel}
     *
     * @param tableName Table name
     * @param comment Table comment
     * @param primaryKeyColumn A list of primary key columns
     * @param columns Table columns
     * @return A new instance of {@link TableModel}
     */
    public static TableModel createTableModel(String tableName, String comment, List<String> primaryKeyColumn, List<ColumnModel> columns) {
        TableModel table = new TableModel();
        table.setName(tableName);
        table.setComment(comment);
        table.setColumns(columns);
        table.setCompositeKey(primaryKeyColumn.size() > 1);
        return table;
    }

    /**
     * Create a new instance of {@link TranslateTypeModel}
     * @param type Type identification
     * @return A new instance of {@link TranslateTypeModel}
     */
    public static TranslateTypeModel createTranslateTypeModel(String type) {
        return createTranslateTypeModel(type, null, false);
    }

    /**
     * Create a new instance of {@link TranslateTypeModel}
     * @param type Type identification
     * @param isLob
     * @return A new instance of {@link TranslateTypeModel}
     */
    public static TranslateTypeModel createTranslateTypeModel(String type, boolean isLob) {
        return createTranslateTypeModel(type, null, isLob);
    }

    /**
     * Create a new instance of {@link TranslateTypeModel}
     * @param type Type identification
     * @param typeImport
     * @return A new instance of {@link TranslateTypeModel}
     */
    public static TranslateTypeModel createTranslateTypeModel(String type, String typeImport) {
        return createTranslateTypeModel(type, typeImport, false);
    }

    /**
     * Create a new instance of {@link TranslateTypeModel}
     * @param type Type identification
     * @param typeImport Type package to import
     * @param isLob Indicate if type is binary (blob, clob, lob, etc)
     * @return A new instance of {@link TranslateTypeModel}
     */
    public static TranslateTypeModel createTranslateTypeModel(String type, String typeImport, boolean isLob) {
        TranslateTypeModel translateType = new TranslateTypeModel();
        translateType.setTargetType(type);
        translateType.setTargetImport(typeImport);
        translateType.setLob(isLob);
        return translateType;
    }

    /**
     * Create a new instance of {@link EntityData}
     * @param table Table name
     * @param packageName Package name
     * @param suffix Entity name suffix
     * @param fields List of {@link FieldData}
     * @return A new instance of {@link EntityData}
     */
    public static EntityData createEntityData(TableModel table, String packageName, String suffix, List<FieldData> fields) {

        EntityData entity = new EntityData();
        entity.setPackageName(packageName);
        entity.setTable(table);
        entity.setName(GeneratorUtils.underscoreToUpperCamelcase(table.getName()));
        if (StringUtils.isNotEmpty(suffix)) {
            String entitySuffix = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, suffix);
            entity.setName(entity.getName().concat(entitySuffix));
        }
        entity.setFields(fields);

        String typeName = StringUtils.trim(entity.getPackageName()).concat(".");
        typeName += StringUtils.trim(entity.getName());
        entity.setSerialUid(GeneratorUtils.generateSerialVersionUUID(entity.getName(), typeName));

        return entity;
    }

    public static FieldData createFieldData(ColumnModel column) {

        FieldData field = new FieldData();
        field.setColumn(column);
        field.setName(GeneratorUtils.underscoreToLowerCamelcase(column.getName()));
        field.setUpperName(GeneratorUtils.underscoreToUpperCamelcase(column.getName()));
        field.setType(column.getTranslatedType().getTargetType());

        return field;
    }

}
