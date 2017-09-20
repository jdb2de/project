package org.jdb2de.core.component;

import com.google.common.base.Preconditions;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.jdb2de.core.data.EntityData;
import org.jdb2de.core.data.FieldData;
import org.jdb2de.core.data.ParameterData;
import org.jdb2de.core.factory.GeneratorFactory;
import org.jdb2de.core.model.TableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EntityCreator {

    /**
     * Instance for log registration
     */
    public static final Logger LOG = LoggerFactory.getLogger(EntityCreator.class);

    private String compositePkPath;
    private final ParameterData parameters;
    private final Configuration freemarkerConfig;


    @Autowired
    public EntityCreator(ParameterData parameters, Configuration freemarkerConfig) {
        this.parameters = parameters;
        this.freemarkerConfig = freemarkerConfig;
    }

    public void create(final String compositePkPath, final List<TableModel> tables) {
        Preconditions.checkNotNull(compositePkPath, "Composite PK path can not be null");
        Preconditions.checkNotNull(tables, "TableModel list can not be null");

        this.compositePkPath = compositePkPath;

        // Parameters to file generation
        Map<String, Object> params = new HashMap<>();
        params.put("param", parameters);

        for (TableModel table : tables) {

            List<FieldData> fields = new ArrayList<>();
            // Convert columns to fields
            table.getColumns().stream().map(GeneratorFactory::createFieldData).forEach(fields::add);

            // Create entity instance
            EntityData entity = GeneratorFactory.createEntityData(table, parameters.getEntityPackage(), fields);
            // Update Freemarker params
            params.put("entity", entity);

            String strEntity;
            try {
                Template template = freemarkerConfig.getTemplate("entity.ftl");
                strEntity = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
            } catch (IOException | TemplateException e) {
                throw new RuntimeException("Error to generate Entity content");
            }

            // Save class to file
            saveFile(strEntity);
        }
    }

    /**
     *
     * @param strEntity
     */
    private void saveFile(String strEntity) {
        LOG.info("\n{}", strEntity);
    }

}
