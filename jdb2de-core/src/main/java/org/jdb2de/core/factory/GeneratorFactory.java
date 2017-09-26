package org.jdb2de.core.factory;

import com.google.common.base.CaseFormat;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jdb2de.core.data.*;
import org.jdb2de.core.model.ColumnModel;
import org.jdb2de.core.model.ForeignKeyModel;
import org.jdb2de.core.model.TableModel;
import org.jdb2de.core.model.TranslateTypeModel;
import org.jdb2de.core.util.GeneratorUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodrigo Tavares
 */
public final class GeneratorFactory {

    private static final String RELATION_MANY_SUFFIX = "List";

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
    private static TranslateTypeModel createTranslateTypeModel(String type, String typeImport, boolean isLob) {
        TranslateTypeModel translateType = new TranslateTypeModel();
        translateType.setTargetType(type);
        translateType.setTargetImport(typeImport);
        translateType.setLob(isLob);
        return translateType;
    }

    /**
     *
     * @param tableName Table name
     * @param suffix Entity name suffix
     * @return A {@link String} with type name
     */
    private static String tableNameToType(String tableName, String suffix) {
        String typeName = GeneratorUtils.underscoreToUpperCamelcase(tableName);
        if (StringUtils.isNotEmpty(suffix)) {
            String entitySuffix = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, suffix);
            typeName = typeName.concat(entitySuffix);
        }

        return typeName;
    }

    /**
     * Create a new instance of {@link EntityData}
     * @param table Table name
     * @param fields List of {@link FieldData}
     * @param parameter Generator parameters
     * @return A new instance of {@link EntityData}
     */
    public static EntityData createEntityData(TableModel table, List<FieldData> fields, ParameterData parameter) {

        EntityData entity = new EntityData();
        entity.setPackageName(parameter.getEntityPackage());
        entity.setTable(table);
        entity.setName(tableNameToType(table.getName(), parameter.getEntitySuffix()));
        entity.setFields(fields);

        String typeName = StringUtils.trim(entity.getPackageName()).concat(".");
        typeName += StringUtils.trim(entity.getName());
        entity.setSerialUid(GeneratorUtils.generateSerialVersionUUID(entity.getName(), typeName));

        if (CollectionUtils.isNotEmpty(table.getForeignKeys())) {
            List<RelationData> relations = new ArrayList<>();
            table.getForeignKeys().forEach(f -> relations.add(createRelationData(f, parameter)));
            entity.setOneRelations(relations);
        }

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

    private static String generateRelationManyName(EntityData entityRelation, RelationData relationOne) {
        String relationManyName = tableNameToType(entityRelation.getTable().getName(), null);
        relationManyName = relationManyName.concat(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, relationOne.getName()));
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, relationManyName.concat(RELATION_MANY_SUFFIX));
    }

    public static List<RelationData> createManyRelations(EntityData entity, List<EntityData> entities) {
        List<RelationData> relationsMany = new ArrayList<>();
        for (EntityData entityRelation : entities) {
            for (RelationData relationOne : entityRelation.getOneRelations()) {

                String entityType = entity.getName();
                if (!entityType.equals(relationOne.getType())) {
                    continue;
                }

                RelationData relationMany = new RelationData();
                relationMany.setName(generateRelationManyName(entityRelation, relationOne));
                relationMany.setUpperName(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, relationMany.getName()));
                relationMany.setName(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, relationMany.getUpperName()));
                relationMany.setType(entityRelation.getName());
                relationMany.setMappedBy(relationOne.getName());

                relationsMany.add(relationMany);
            }
        }

        return relationsMany;
    }

    private static String generateRelationName(ForeignKeyModel foreignKey, ParameterData parameter) {

        String mainRelationColumn = null;
        // If there is only one column, gets the first one
        if (foreignKey.getColumns().size() == 1) {
            mainRelationColumn = foreignKey.getColumns().get(0);
        } else {
            // Otherwise gets the column that contains the reference table in the name
            for (String column : foreignKey.getColumns()) {
                if (column.contains(foreignKey.getReferenceTable())) {
                    mainRelationColumn = column;
                    break;
                }
            }

            // if anyway were not possible to identify the column, gets the reference table name as relation name
            if (StringUtils.isEmpty(mainRelationColumn)) {
                return GeneratorUtils.underscoreToLowerCamelcase(foreignKey.getReferenceTable());
            }
        }

        // Remove from main primary key column name the primary key
        String relationName = mainRelationColumn.replaceAll(parameter.getPrimaryKeyFieldNameRegex(), "");
        return GeneratorUtils.underscoreToLowerCamelcase(relationName);
    }

    private static RelationData createRelationData(ForeignKeyModel foreignKey, ParameterData parameter) {

        RelationData relation = new RelationData();
        String relationName = generateRelationName(foreignKey, parameter);

        relation.setName(relationName);
        relation.setUpperName(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, relationName));
        relation.setType(tableNameToType(foreignKey.getReferenceTable(), parameter.getEntitySuffix()));

        List<RelationReferenceData> relationReferences = new ArrayList<>();
        for (int i = 0; i < foreignKey.getColumns().size(); i++) {
            String column = foreignKey.getColumns().get(i);
            String referenceColumn = foreignKey.getReferenceColumns().get(i);

            RelationReferenceData relationReference = new RelationReferenceData();
            relationReference.setSource(column);
            relationReference.setTarget(referenceColumn);

            relationReferences.add(relationReference);
        }
        relation.setColumns(relationReferences);

        return relation;
    }

}
