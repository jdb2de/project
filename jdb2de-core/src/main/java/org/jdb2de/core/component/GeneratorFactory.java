package org.jdb2de.core.component;

import com.google.common.base.CaseFormat;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.jdb2de.core.data.*;
import org.jdb2de.core.model.*;
import org.jdb2de.core.util.GeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Rodrigo Tavares
 */
@Component
@Scope(value = "singleton")
public final class GeneratorFactory {

    private static String UNDERLINE = "_";

    /**
     * Suffix for reverse relation name
     */
    private static final String RELATION_MANY_SUFFIX = "List";

    /**
     * Suffix for composite primary keys
     */
    private static final String COMPOSITE_PRIMARY_KEY_SUFFIX = "PK";

    private final ParameterData parameters;
    private final ConnectionConfigurationData connectionConfigurationData;

    /**
     * Constructor omitted
     */
    @Autowired
    private GeneratorFactory(ParameterData parameters, ConnectionConfigurationData connectionConfigurationData) {
        this.parameters = parameters;
        this.connectionConfigurationData = connectionConfigurationData;
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
        table.setSchema(connectionConfigurationData.getSchema());
        table.setCatalog(connectionConfigurationData.getCatalog());
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
     * @param isLob Indicates if field is binary (lob)
     * @return A new instance of {@link TranslateTypeModel}
     */
    public TranslateTypeModel createTranslateTypeModel(String type, boolean isLob) {
        return createTranslateTypeModel(type, null, isLob);
    }

    /**
     * Create a new instance of {@link TranslateTypeModel}
     * @param type Type identification
     * @param typeImport Type package to import
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

        String table = clearName(tableName, parameters.isTableNameRegexCleanEntityName());

        String typeName = GeneratorUtils.underscoreToUpperCamelcase(table);
        if (useSuffix && StringUtils.isNotEmpty(parameters.getEntitySuffix())) {
            String entitySuffix = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, parameters.getEntitySuffix());
            typeName = typeName.concat(entitySuffix);
        }

        return typeName;
    }

    private String generateRelationManyName(EntityData entityRelation, RelationData relationOne) {

        String table = clearName(entityRelation.getTable().getName(),
                parameters.isTableNameRegexCleanForeignKeyField());

        String relationManyName = tableNameToType(table, false);
        relationManyName = relationManyName.concat(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, relationOne.getName()));
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, relationManyName.concat(RELATION_MANY_SUFFIX));
    }

    private String clearName(String originalName, boolean clean) {
        if (StringUtils.isEmpty(parameters.getTableNameRegex())) {
            return originalName;
        }

        if (!clean) {
            return originalName;
        }

        String table = originalName.replaceFirst(parameters.getTableNameRegex(), "");
        if (table.startsWith(UNDERLINE)) {
            table = table.replaceFirst("^".concat(UNDERLINE), "");
        }

        if (table.endsWith(UNDERLINE)) {
            table = table.replaceFirst(UNDERLINE.concat("$"), "");
        }

        return table;
    }

    private List<String> createImportList(List<FieldData> fields) {
        // Check if fields are empty
        List<String> imports = new ArrayList<>();
        if (CollectionUtils.isEmpty(fields)) {
            return imports;
        }

        // Temporally list for unique imports
        Set<String> tmpImports = new HashSet<>();
        fields.forEach(field -> {
            // Add if not null
            if (StringUtils.isNotEmpty(field.getTypeImport())) {
                tmpImports.add(field.getTypeImport());
            }
        });

        // Create a ordered list with imports
        imports.addAll(tmpImports);
        Collections.sort(imports);
        return imports;
    }

    /**
     * Create a new instance of {@link EntityData}
     * @param table Table name
     * @param fields List of {@link FieldData}
     * @return A new instance of {@link EntityData}
     */
    public EntityData createEntityData(TableModel table, List<FieldData> fields) {

        EntityData entity = new EntityData();
        entity.setPackageName(StringUtils.trim(parameters.getEntityPackage()));
        entity.setTable(table);
        entity.setName(tableNameToType(table.getName(), true));
        entity.setFields(fields);

        String typeName = entity.getPackageName().concat(".");
        typeName += StringUtils.trim(entity.getName());
        entity.setSerialUid(GeneratorUtils.generateSerialVersionUUID(entity.getName(), typeName));

        if (CollectionUtils.isNotEmpty(table.getForeignKeys())) {
            List<RelationData> relations = new ArrayList<>();
            table.getForeignKeys().forEach(foreignKey -> relations.add(createRelationData(foreignKey, relations)));
            entity.setOneRelations(relations);
        }

        List<String> imports = createImportList(entity.getFields());
        entity.setImports(imports);

        return entity;
    }

    public FieldData createFieldData(ColumnModel column) {
        FieldData field = new FieldData();
        field.setColumn(column);
        field.setName(GeneratorUtils.underscoreToLowerCamelcase(column.getName()));
        field.setUpperName(GeneratorUtils.underscoreToUpperCamelcase(column.getName()));
        field.setTypeImport(column.getTranslatedType().getTargetImport());
        field.setType(column.getTranslatedType().getTargetType());

        return field;
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

    private ForeignKeyColumnModel findMainRelation(String referenceTable, List<ForeignKeyColumnModel> relations) {

        Double currentSimilarity = 0.0;
        ForeignKeyColumnModel currentRelation = null;
        for (ForeignKeyColumnModel relation : relations) {

            // Check if there is a constant primary key name, if yes and target column
            // is equals the  constant name, uses this instance as main relation, otherwise
            // follows to similarity rules
            if (StringUtils.isNotEmpty(parameters.getTableConstantPrimaryKeyName()) &&
                    relation.getTarget().equals(parameters.getTableConstantPrimaryKeyName())) {
                return relation;
            }

            // Checks if one of the columns in the reference table has the table name
            String sourceColumn = relation.getSource().replaceFirst(parameters.getPrimaryKeyFieldNameRegex(), "");
            String targetColumn = relation.getTarget().replaceFirst(parameters.getPrimaryKeyFieldNameRegex(), "");

            // Check the similarity between reference table and theirs primary key column
            JaroWinklerDistance distance = new JaroWinklerDistance();
            Double similaritySource = distance.apply(referenceTable, sourceColumn);
            Double similarityTarget = distance.apply(referenceTable, targetColumn);

            // Check the greater similarity between source and target column
            Double similarity = (similaritySource > similarityTarget) ? similaritySource : similarityTarget;

            // Compare the current similarity with the actual
            if (similarity > currentSimilarity) {
                currentRelation = relation;
                currentSimilarity = similarity;
            }
        }

        return currentRelation;
    }

    private String generateRelationName(ForeignKeyModel foreignKey) {

        ForeignKeyColumnModel relation;
        // If there is only one column, gets the first one
        if (foreignKey.getRelations().size() == 1) {
            relation= foreignKey.getRelations().get(0);
        } else {
            relation = findMainRelation(foreignKey.getReferenceTable(), foreignKey.getRelations());
        }

        // if anyway was not possible to identify the column,
        // gets the reference table name as relation name
        if (relation == null) {
            return tableNameToType(foreignKey.getReferenceTable(), false);
        }

        // Remove prefixes or suffixes from main source and target columns to generate the relation name
        String relationName = relation.getSource().replaceFirst(parameters.getPrimaryKeyFieldNameRegex(), "");
        return tableNameToType(relationName, false);
    }

    private RelationData createRelationData(ForeignKeyModel foreignKey, final List<RelationData> relations) {

        RelationData relation = new RelationData();
        String relationUpperName = generateRelationName(foreignKey);

        // Check if relation already exists
        int count = 1;
        String tempName = relationUpperName;
        for (RelationData r : relations) {
            if (tempName.equals(r.getUpperName())) {
                count += 1;
                tempName = relationUpperName.concat(String.valueOf(count));
            }
        }
        // Update relation name
        relationUpperName = tempName;

        relation.setUpperName(relationUpperName);
        relation.setName(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, relationUpperName));
        relation.setType(tableNameToType(foreignKey.getReferenceTable(), true));

        List<RelationReferenceData> relationReferences = new ArrayList<>();
        for (ForeignKeyColumnModel relationColumns : foreignKey.getRelations()) {
            String column = relationColumns.getSource();
            String referenceColumn = relationColumns.getTarget();

            RelationReferenceData relationReference = new RelationReferenceData();
            relationReference.setSource(column);
            relationReference.setTarget(referenceColumn);

            relationReferences.add(relationReference);
        }
        relation.setColumns(relationReferences);

        return relation;
    }

    public CompositePrimaryKeyData createCompositePrimaryKeyData(EntityData entity) {

        CompositePrimaryKeyData compositePrimaryKey = new CompositePrimaryKeyData();
        compositePrimaryKey.setEntity(entity);
        compositePrimaryKey.setPackageName(StringUtils.trim(parameters.getCompositePrimaryKeyPackage()));
        compositePrimaryKey.setName(tableNameToType(entity.getTable().getName(), false).concat(
                COMPOSITE_PRIMARY_KEY_SUFFIX));

        String compositePrimaryKeyTypeName = compositePrimaryKey.getPackageName().concat(".");
        compositePrimaryKeyTypeName += StringUtils.trim(compositePrimaryKey.getName());
        compositePrimaryKey.setSerialUid(GeneratorUtils.generateSerialVersionUUID(compositePrimaryKey.getName(),
                compositePrimaryKeyTypeName));

        List<FieldData> fields = entity.getFields().stream().filter(
                field -> field.getColumn().isPrimaryKey()).collect(Collectors.toList());
        compositePrimaryKey.setFields(fields);

        List<String> imports = createImportList(fields);
        compositePrimaryKey.setImports(imports);

        return compositePrimaryKey;
    }

}
