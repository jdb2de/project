package org.jdb2de.core;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jdb2de.core.data.ParameterData;
import org.jdb2de.core.data.database.ColumnData;
import org.jdb2de.core.data.database.ForeignKeyData;
import org.jdb2de.core.data.database.TableData;
import org.jdb2de.core.data.enitity.EntityData;
import org.jdb2de.core.data.enitity.FieldData;
import org.jdb2de.core.exception.ValidationException;
import org.jdb2de.core.information.IDatabaseInformation;
import org.jdb2de.core.util.GeneratorUtils;
import org.jdb2de.core.util.NameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 *
 * @author  Rodrigo Tavares
 */
@Service
public class GeneratorService {

    private static final Logger LOG = LoggerFactory.getLogger(GeneratorService.class);

    /**
     * Copyright and licence file name
     */
    private static final String COPYRIGHT_FILE = "copyright";

    /**
     *
     */
    private static final String MSG_PARAMETER_NOT_FOUND = "Required parameter [{}] not found";

    @Autowired
    private IDatabaseInformation information;

    @Autowired
    private ParameterData parameters;

    @Autowired
    private Configuration freemarkerConfig;

    @PostConstruct
    private void init() throws IOException {
        // Load copyright and licence from file
        parameters.setCopyright(com.google.common.io.Files.readLines(GeneratorUtils.fileFromResource(COPYRIGHT_FILE),
                Charset.defaultCharset()));
    }

    /**
     * Checks if all parameters were correctly informed
     */
    private void validateParameters() throws ValidationException {

        if (StringUtils.isEmpty(parameters.getEntityPath())) {
            throw new ValidationException(MSG_PARAMETER_NOT_FOUND, "config.entity.path");
        }

        if (StringUtils.isEmpty(parameters.getEntityPackage())) {
            throw new ValidationException(MSG_PARAMETER_NOT_FOUND, "config.entity.package");
        }

        if (StringUtils.isEmpty(parameters.getIdPackage())) {
            throw new ValidationException(MSG_PARAMETER_NOT_FOUND, "config.id.package");
        }

        if (StringUtils.indexOf(parameters.getIdPackage(), parameters.getEntityPackage()) != 0) {
            throw new ValidationException("Parameter [config.id.package] is invalid, it must be a substring of [config.entity.package]");
        }

        Path entityPath = Paths.get(parameters.getEntityPath());
        if (!Files.exists(entityPath)) {
            throw new ValidationException("Entity generation path not found [config.entity.path={}]", entityPath);
        }

        // If there is not an author gets the operating system user name
        if (StringUtils.isEmpty(parameters.getAuthor())) {
            parameters.setAuthor(System.getProperty("user.name"));
        }
    }

    /**
     * Set primary key path based on entity path and id package
     */
    private void createIdPath() throws IOException {

        String additionalPath = StringUtils.replace(parameters.getIdPackage(), parameters.getEntityPackage(), "");
        additionalPath = StringUtils.replace(additionalPath, ".", File.separator);
        parameters.setIdPath(parameters.getEntityPath().concat(additionalPath));

        Path idPath = Paths.get(parameters.getIdPath());
        if (!Files.exists(idPath)) {
            Files.createDirectories(idPath);
        }
    }

    public void generate() throws Exception {

        // Execute a parameter validation
        validateParameters();

        // Set primary key path based on entity path and id package
        createIdPath();

        List<String> ls = information.allTables(null);
        if (ls == null) {
            LOG.info("No tables.....");
            return;
        }

        ls.forEach(s -> LOG.info(NameUtils.underscoreToUpperCamelcase(s)));
        LOG.info("****************");
        ls.forEach(s -> LOG.info(NameUtils.underscoreToLowerCamelcase(s)));
        LOG.info("****************");
        LOG.info(String.valueOf(information.checkIfTableExists("staff")));
        LOG.info("****************");
        LOG.info(String.valueOf(information.checkIfTableExists("xxx")));
        LOG.info("****************");

        for (String table : ls) {
            LOG.info("****************");
            LOG.info(table + " Columns");
            LOG.info("Comment: " + information.tableComment(table));
            LOG.info("****************");
            LOG.info("");

            List<ColumnData> cols = information.tableColumns(table);
            for (ColumnData col : cols) {
                LOG.info(col.toString());
                LOG.info("Comment: " + information.columnComment(table, col.getName()));
                LOG.info("");
            }

            List<ForeignKeyData> foreignKeys = information.tableForeignKeys(table);

            if (CollectionUtils.isNotEmpty(foreignKeys)) {
                LOG.info("");
                LOG.info("****************");
                LOG.info("Foreign Keys");
                LOG.info("****************");

                LOG.info("");

                for (ForeignKeyData foreignKey : foreignKeys) {
                    LOG.info(foreignKey.toString());
                    LOG.info("");
                }
            }

        }
        LOG.info("****************");
        LOG.info("");
        entityTemplate();
    }

    private void entityTemplate() {

        try {
            List<String> ls = information.allTables(null);
            int idx = (int) (ls.size() * Math.random());
            String tableName = ls.get(idx);
            String tableComment = information.tableComment(tableName);

            TableData table = new TableData();
            table.setName(tableName);
            table.setComment(tableComment);

            List<ColumnData> cols = information.tableColumns(tableName);
            List<FieldData> fields = new ArrayList<>();
            for (ColumnData col : cols) {
                String columnComment = information.columnComment(tableName, col.getName());
                col.setComment(columnComment);

                FieldData field = new FieldData();
                field.setColumn(col);
                field.setName(NameUtils.underscoreToLowerCamelcase(col.getName()));
                field.setUpperName(NameUtils.underscoreToUpperCamelcase(col.getName()));
                field.setType(information.translateDbType(col.getType()));
                fields.add(field);
            }

            EntityData entity = new EntityData();
            entity.setTable(table);
            entity.setFields(fields);
            entity.setName(NameUtils.underscoreToUpperCamelcase(tableName));

            String entityName = parameters.getEntityPackage() + "." + entity.getName();
            entity.setSerialUid(GeneratorUtils.generateSerialVersionUUID(entityName, entity.getName()));
            Map<String, Object> params = new HashMap<>();
            params.put("param", parameters);
            params.put("entity", entity);
            params.put("now", new Date());

            //freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
            Template template = freemarkerConfig.getTemplate("entity.ftl");
            String strEntity = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);
            LOG.info("");
            LOG.info("*************************************");
            LOG.info(strEntity);
            LOG.info("*************************************");
        } catch (Exception e) {
            LOG.error("Fatal error to process template file", e);
        }
    }

}
