package org.jdb2de.core.component;

import com.google.common.base.Preconditions;
import com.google.common.io.Files;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.jdb2de.core.data.CompositePrimaryKeyData;
import org.jdb2de.core.data.EntityData;
import org.jdb2de.core.data.FieldData;
import org.jdb2de.core.data.ParameterData;
import org.jdb2de.core.exception.GeneratorException;
import org.jdb2de.core.model.TableModel;
import org.jdb2de.core.util.GeneratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EntityCreator {

    /**
     * Instance for log registration
     */
    private static final Logger LOG = LoggerFactory.getLogger(EntityCreator.class);
    private static final String PARAMETER_KEY = "param";
    private static final String ENTITY_KEY = "entity";
    private static final String COMPOSITE_NAME_KEY = "compositeName";
    private static final String COMPOSITE_KEY = "composite";
    private static final String JAVA_FILE_PATTERN = "{}.java";

    private String compositePkPath;
    private final ParameterData parameters;
    private final GeneratorFactory factory;
    private final Configuration freemarkerConfig;

    @Autowired
    public EntityCreator(ParameterData parameters, GeneratorFactory factory, Configuration freemarkerConfig) {
        this.parameters = parameters;
        this.factory = factory;
        this.freemarkerConfig = freemarkerConfig;
    }

    /**
     * When a table has a composite primary key, a new class is created with the primary key fields only.
     * This method defines the composite primary key path based on the entity path and primary key package if it is
     * different from the entity package
     */
    private void createCompositePkPath() throws IOException {

        String additionalPath = StringUtils.replace(parameters.getCompositePrimaryKeyPackage(),
                parameters.getEntityPackage(), "");
        additionalPath = StringUtils.replace(additionalPath, ".", File.separator);
        compositePkPath = parameters.getEntityPath().concat(additionalPath);
        if (!parameters.getEntityPackage().equals(parameters.getCompositePrimaryKeyPackage())) {
            return;
        }

        LOG.info("Composite primary key generation directory: {}", compositePkPath);
        Path path = Paths.get(compositePkPath);
        if (!path.toFile().exists()) {
            java.nio.file.Files.createDirectories(path);
        }
    }

    public void create(final List<TableModel> tables) throws IOException {
        Preconditions.checkNotNull(tables, "TableModel list can not be null");

        // Set primary key path based on entity path and pk package
        createCompositePkPath();

        // Parameters to file generation
        Map<String, Object> params = new HashMap<>();
        params.put(PARAMETER_KEY, parameters);

        List<EntityData> entities = new ArrayList<>();
        for (TableModel table : tables) {

            List<FieldData> fields = new ArrayList<>();
            // Convert columns to fields
            table.getColumns().stream().map(factory::createFieldData).forEach(fields::add);

            // Create entity instance
            EntityData entity = factory.createEntityData(table, fields);
            entities.add(entity);
        }

        // Generate many relations and save file
        List<CompositePrimaryKeyData> compositePrimaryKeys = new ArrayList<>();
        for (EntityData entity : entities) {
            entity.setManyRelations(factory.createManyRelations(entity, entities));
            params.put(ENTITY_KEY, entity);

            if (entity.getTable().isCompositeKey()) {
                CompositePrimaryKeyData compositePrimaryKey = factory.createCompositePrimaryKeyData(entity);
                compositePrimaryKeys.add(compositePrimaryKey);
                params.put(COMPOSITE_NAME_KEY, compositePrimaryKey.getName());
            }

            saveEntityFile(params);
        }

        // Remove unneeded parameters
        params.remove(ENTITY_KEY);
        params.remove(COMPOSITE_NAME_KEY);

        for (CompositePrimaryKeyData compositePrimaryKey : compositePrimaryKeys) {
            params.put(COMPOSITE_KEY, compositePrimaryKey);
            saveCompositePrimaryKeyFile(params);
        }
    }

    /**
     *
     * @param params Composite primary key content
     */
    private void saveEntityFile(Map<String, Object> params) {
        String strEntity;
        try {
            Template template = freemarkerConfig.getTemplate("entity.ftl");
            strEntity = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
        } catch (IOException | TemplateException e) {
            throw new GeneratorException("Error to generate Entity content", e);
        }

        EntityData entity = (EntityData)params.get(ENTITY_KEY);
        String fileName = GeneratorUtils.messageFormat(JAVA_FILE_PATTERN, entity.getName());
        try {
            LOG.info("Saving entity {}", entity.getName());
            LOG.debug("Entity content:\n{}", strEntity);

            Path savePath = Paths.get(parameters.getEntityPath(), fileName);

            LOG.info("Entity path: {}", savePath);
            if (java.nio.file.Files.deleteIfExists(savePath)) {
                LOG.info("Previous entity removed");
            }

            Files.write(strEntity.getBytes(), savePath.toFile());
            LOG.info("Entity {} created", fileName);
        } catch (IOException e) {
            LOG.error("Failed to save entity to file: {}", e, fileName);
        }
    }

    private void saveCompositePrimaryKeyFile(Map<String, Object> params) {
        String strCompositePrimaryKey;
        try {
            Template template = freemarkerConfig.getTemplate("composite-primary-key.ftl");
            strCompositePrimaryKey = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
        } catch (IOException | TemplateException e) {
            throw new GeneratorException("Error to generate Entity content", e);
        }

        CompositePrimaryKeyData compositePrimaryKey = (CompositePrimaryKeyData)params.get(COMPOSITE_KEY);
        String fileName = GeneratorUtils.messageFormat(JAVA_FILE_PATTERN, compositePrimaryKey.getName());
        try {
            LOG.info("Saving composite primary key {}", compositePrimaryKey.getName());
            LOG.debug("Composite Primary Key content:\n{}", strCompositePrimaryKey);

            Path savePath = Paths.get(compositePkPath, fileName);

            LOG.info("Composite Primary Key path: {}", savePath);
            if (java.nio.file.Files.deleteIfExists(savePath)) {
                LOG.info("Previous composite primary key removed");
            }

            Files.write(strCompositePrimaryKey.getBytes(), savePath.toFile());
            LOG.info("Composite Primary Key {} created", fileName);
        } catch (IOException e) {
            LOG.error("Failed to save composite primary key to file: {}", e, fileName);
        }
    }

}
