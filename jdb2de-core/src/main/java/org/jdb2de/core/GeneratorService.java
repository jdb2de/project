package org.jdb2de.core;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.collections4.CollectionUtils;
import org.jdb2de.core.data.ParameterData;
import org.jdb2de.core.data.database.ColumnData;
import org.jdb2de.core.data.database.ForeignKeyData;
import org.jdb2de.core.data.database.TableData;
import org.jdb2de.core.data.enitity.EntityData;
import org.jdb2de.core.data.enitity.FieldData;
import org.jdb2de.core.information.IDatabaseInformation;
import org.jdb2de.core.util.LanguageUtils;
import org.jdb2de.core.util.NameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.*;

/**
 *
 * @author  Rodrigo Tavares
 */
@Service
public class GeneratorService {

    private static final Logger LOG = LoggerFactory.getLogger(GeneratorService.class);

    @Autowired
    private IDatabaseInformation information;

    @Autowired
    private ParameterData parameters;

    @Autowired
    private Configuration freemarkerConfig;

    public void generate() {

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
            entity.setSerialUid(LanguageUtils.generateSerialVersionUUID(entityName, entity.getName()));
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
