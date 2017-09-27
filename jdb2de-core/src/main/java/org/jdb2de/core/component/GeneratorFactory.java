package org.jdb2de.core.component;

import com.google.common.base.CaseFormat;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jdb2de.core.data.*;
import org.jdb2de.core.model.ColumnModel;
import org.jdb2de.core.model.ForeignKeyModel;
import org.jdb2de.core.model.TableModel;
import org.jdb2de.core.model.TranslateTypeModel;
import org.jdb2de.core.util.GeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodrigo Tavares
 */
@Component
@Scope(value = "singleton")
public final class GeneratorFactory {

    private final ParameterData parameters;
    
    /**
     * Constructor omitted
     */
    @Autowired
    private GeneratorFactory(ParameterData parameters) {
        this.parameters = parameters;
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
    public TableModel createTableModel(String tableName, String comment, List<String> primaryKeyColumn,
                                       List<ColumnModel> columns) {
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
    public TranslateTypeModel createTranslateTypeModel(String type) {
        return createTranslateTypeModel(type, null, false);
    }

    /**
     * Create a new instance of {@link TranslateTypeModel}
     * @param type Type identification
     * @param isLob
     * @return A new instance of {@link TranslateTypeModel}
     */
    public TranslateTypeModel createTranslateTypeModel(String type, boolean isLob) {
        return createTranslateTypeModel(type, null, isLob);
    }

    /**
     * Create a new instance of {@link TranslateTypeModel}
     * @param type Type identification
     * @param typeImport
     * @return A new instance of {@link TranslateTypeModel}
     */
    public TranslateTypeModel createTranslateTypeModel(String type, String typeImport) {
        return createTranslateTypeModel(type, typeImport, false);
    }

    /**
     * Create a new instance of {@link TranslateTypeModel}
     * @param type Type identification
     * @param typeImport Type package to import
     * @param isLob Indicate if type is binary (blob, clob, lob, etc)
     * @return A new instance of {@link TranslateTypeModel}
     */
    private TranslateTypeModel createTranslateTypeModel(String type, String typeImport, boolean isLob) {
        TranslateTypeModel translateType = new TranslateTypeModel();
        translateType.setTargetType(type);
        translateType.setTargetImport(typeImport);
        translateType.setLob(isLob);
        return translateType;
    }

    /**
     *
     * @param tableName Table name
     * @param useSuffix Indicates if
     * @return A {@link String} with type name
     */
    private String tableNameToType(String tableName, boolean useSuffix) {
        String typeName = GeneratorUtils.underscoreToUpperCamelcase(tableName);
        if (useSuffix && StringUtils.isNotEmpty(parameters.getEntitySuffix())) {
            String entitySuffix = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, parameters.getEntitySuffix());
            typeName = typeName.concat(entitySuffix);
        }

        return typeName;
    }

    /**
     * Create a new instance of {@link EntityData}
     * @param table Table name
     * @param fields List of {@link FieldData}
     * @return A new instance of {@link EntityData}
     */
    public EntityData createEntityData(TableModel table, List<FieldData> fields) {

        EntityData entity = new EntityData();
        entity.setPackageName(parameters.getEntityPackage());
        entity.setTable(table);
        entity.setName(tableNameToType(table.getName(), true));
        entity.setFields(fields);

        String typeName = StringUtils.trim(entity.getPackageName()).concat(".");
        typeName += StringUtils.trim(entity.getName());
        entity.setSerialUid(GeneratorUtils.generateSerialVersionUUID(entity.getName(), typeName));

        if (CollectionUtils.isNotEmpty(table.getForeignKeys())) {
            List<RelationData> relations = new ArrayList<>();
            table.getForeignKeys().forEach(foreignKey -> relations.add(createRelationData(foreignKey)));
            entity.setOneRelations(relations);
        }

        return entity;
    }

    public FieldData createFieldData(ColumnModel column) {

        FieldData field = new FieldData();
        field.setColumn(column);
        field.setName(GeneratorUtils.underscoreToLowerCamelcase(column.getName()));
        field.setUpperName(GeneratorUtils.underscoreToUpperCamelcase(column.getName()));
        field.setType(column.getTranslatedType().getTargetType());

        return field;
    }

    private String generateRelationManyName(EntityData entityRelation, RelationData relationOne) {
        final String relationManySuffix = "List";
        String relationManyName = tableNameToType(entityRelation.getTable().getName(), false);
        relationManyName = relationManyName.concat(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, relationOne.getName()));
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, relationManyName.concat(relationManySuffix));
    }

    public List<RelationData> createManyRelations(EntityData entity, List<EntityData> entities) {
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

    private String generateRelationName(ForeignKeyModel foreignKey) {

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
        String relationName = mainRelationColumn.replaceAll(parameters.getPrimaryKeyFieldNameRegex(), "");
        return GeneratorUtils.underscoreToLowerCamelcase(relationName);
    }

    private RelationData createRelationData(ForeignKeyModel foreignKey) {

        RelationData relation = new RelationData();
        String relationName = generateRelationName(foreignKey);

        relation.setName(relationName);
        relation.setUpperName(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, relationName));
        relation.setType(tableNameToType(foreignKey.getReferenceTable(), true));

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
