package org.jdb2de.core.component;

import com.google.common.base.Preconditions;
import com.google.common.io.Files;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.jdb2de.core.data.EntityData;
import org.jdb2de.core.data.FieldData;
import org.jdb2de.core.data.ParameterData;
import org.jdb2de.core.factory.GeneratorFactory;
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
    private static final String ENTITY_NAME_PATTERN = "{}.java";

    private String compositePkPath;
    private final ParameterData parameters;
    private final Configuration freemarkerConfig;

    @Autowired
    public EntityCreator(ParameterData parameters, Configuration freemarkerConfig) {
        this.parameters = parameters;
        this.freemarkerConfig = freemarkerConfig;
    }

    /**
     * When a table has a composite primary key, a new class is created with the primary key fields only.
     * This method defines the composite primary key path based on the entity path and primary key package if it is
     * different from the entity package
     */
    private void createCompositePkPath() throws IOException {

        String additionalPath = StringUtils.replace(parameters.getCompositePkPackage(), parameters.getEntityPackage(), "");
        additionalPath = StringUtils.replace(additionalPath, ".", File.separator);
        compositePkPath = parameters.getEntityPath().concat(additionalPath);
        if (!parameters.getEntityPackage().equals(parameters.getCompositePkPackage())) {
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
        params.put("param", parameters);

        for (TableModel table : tables) {
            // TODO Implements composite key generation
            if (table.isCompositeKey()) {
                continue;
            }

            List<FieldData> fields = new ArrayList<>();
            // Convert columns to fields
            table.getColumns().stream().map(GeneratorFactory::createFieldData).forEach(fields::add);

            // Create entity instance
            EntityData entity = GeneratorFactory.createEntityData(table, parameters.getEntityPackage(),
                    parameters.getEntitySuffix(), fields);
            // Update Freemarker params
            params.put("imports", GeneratorUtils.createImportList(table.getColumns()));
            params.put("entity", entity);

            String strEntity;
            try {
                Template template = freemarkerConfig.getTemplate("entity.ftl");
                strEntity = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
            } catch (IOException | TemplateException e) {
                throw new RuntimeException("Error to generate Entity content", e);
            }

            // Save class to file
            saveFile(entity.getName(), strEntity);
        }
    }

    /**
     *
     * @param entityName Entity name
     * @param strEntity Entity content
     */
    private void saveFile(String entityName, String strEntity) {

        String fileName = GeneratorUtils.messageFormat(ENTITY_NAME_PATTERN, entityName);
        try {
            LOG.info("Saving entity {}", entityName);
            LOG.debug("Entity content:\n{}", strEntity);

            Path entityPath = Paths.get(parameters.getEntityPath(), fileName);

            LOG.info("Entity path: {}", entityPath);
            if (java.nio.file.Files.deleteIfExists(entityPath)) {
                LOG.info("Previous entity removed");
            }

            Files.write(strEntity.getBytes(), entityPath.toFile());
            LOG.info("Entity {} created", fileName);
        } catch (IOException e) {
            LOG.error("Failed to save entity to file: {}", e, fileName);
        }
    }

}
