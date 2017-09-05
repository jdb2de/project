package org.jdb2de.core;

import com.google.common.io.Files;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.collections4.CollectionUtils;
import org.jdb2de.core.data.ParametersData;
import org.jdb2de.core.data.database.ColumnData;
import org.jdb2de.core.data.database.ForeignKeyData;
import org.jdb2de.core.data.database.TableData;
import org.jdb2de.core.data.enitity.EntityData;
import org.jdb2de.core.data.enitity.FieldData;
import org.jdb2de.core.information.impl.PostgresInformation;
import org.jdb2de.core.util.LanguageUtils;
import org.jdb2de.core.util.NameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.nio.charset.Charset;
import java.util.*;

/**
 *
 * @author  Rodrigo Tavares
 */
@Service
public class EntityGeneratorService {

    public static final Logger LOG = LoggerFactory.getLogger(EntityGeneratorService.class);

    @Autowired
    private PostgresInformation dbInformation;

    @Autowired
    private Configuration freemarkerConfig;

    public void generate() {

        List<String> ls = dbInformation.allTables("public", null);
        if (ls == null) {
            System.out.println("No tables.....");
            return;
        }

        ls.forEach((s) -> System.out.println(NameUtils.underscoreToUpperCamelcase(s)));
        System.out.println("****************");
        ls.forEach((s) -> System.out.println(NameUtils.underscoreToLowerCamelcase(s)));
        System.out.println("****************");
        System.out.println(dbInformation.checkIfTableExists("public", "parameter"));
        System.out.println("****************");
        System.out.println(dbInformation.checkIfTableExists("public", "time"));
        System.out.println("****************");

        for (String table : ls) {
            System.out.println("****************");
            System.out.println(table + " Columns");
            System.out.println("Comment: " + dbInformation.tableComment("public", table));
            System.out.println("****************");
            System.out.println();

            List<ColumnData> cols = dbInformation.tableColumns("public", table);
            for (ColumnData col : cols) {
                System.out.println(col);
                System.out.println("Comment: " + dbInformation.columnComment("public", table, col.getName()));
                System.out.println();
            }

            List<ForeignKeyData> foreignKeys = dbInformation.tableForeignKeys("public", table);

            if (CollectionUtils.isNotEmpty(foreignKeys)) {
                System.out.println();
                System.out.println("****************");
                System.out.println("Foreign Keys");
                System.out.println("****************");

                System.out.println();

                for (ForeignKeyData foreignKey : foreignKeys) {
                    System.out.println(foreignKey);
                    System.out.println();
                }
            }

        }
        System.out.println("****************");
        System.out.println();
        entityTemplate();
    }

    private void entityTemplate() {

        try {
            ParametersData parameters = new ParametersData();
            parameters.setSchema("public");
            parameters.setEntityPackage("org.jdb2de.model");
            parameters.setIdPackage("org.jdb2de.model.pk");

            String schema = "public";
            List<String> ls = dbInformation.allTables(schema, null);
            String tableName = ls.get(0);
            String tableComment = dbInformation.tableComment(schema, tableName);

            TableData table = new TableData();
            table.setName(tableName);
            table.setComment(tableComment);

            List<ColumnData> cols = dbInformation.tableColumns(schema, tableName);
            List<FieldData> fields = new ArrayList<>();
            for (ColumnData col : cols) {
                String columnComment = dbInformation.columnComment(schema, tableName, col.getName());
                col.setComment(columnComment);

                FieldData field = new FieldData();
                field.setColumn(col);
                field.setName(NameUtils.underscoreToLowerCamelcase(col.getName()));
                field.setUpperName(NameUtils.underscoreToUpperCamelcase(col.getName()));
                field.setType(dbInformation.translateDbType(col.getType()));
                fields.add(field);
            }

            EntityData entity = new EntityData();
            entity.setTable(table);
            entity.setAuthor("Rodrigo Tavares");
            entity.setCopyright(Files.readLines(LanguageUtils.fileFromResource("copyright"), Charset.defaultCharset()));
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
            System.out.println();
            System.out.println("*************************************");
            System.out.println(strEntity);
            System.out.println("*************************************");
        } catch (Exception e) {
            LOG.error("Fatal error to process template file", e);
        }


    }

}
