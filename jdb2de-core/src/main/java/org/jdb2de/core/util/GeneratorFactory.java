package org.jdb2de.core.util;

import org.apache.commons.lang3.StringUtils;
import org.jdb2de.core.data.EntityData;
import org.jdb2de.core.data.FieldData;
import org.jdb2de.core.model.ColumnModel;
import org.jdb2de.core.model.TableModel;

import java.util.List;

/**
 *
 * @author Rodrigo Tavares
 */
public final class GeneratorFactory {

    /**
     * Create a new instance of {@link TableModel}
     *
     * @param tableName Table name
     * @param comment Table comment
     * @param columns Table columns
     * @return A new instance of {@link TableModel}
     */
    public static TableModel createTableData(String tableName, String comment, List<ColumnModel> columns) {
        TableModel table = new TableModel();
        table.setName(tableName);
        table.setComment(comment);
        table.setColumns(columns);
        return table;
    }

    /**
     * Create a new instance of {@link EntityData}
     * @param table Table name
     * @param packageName Package name
     * @param fields List of {@link FieldData}
     * @return A new instance of {@link EntityData}
     */
    public static EntityData createEntityData(TableModel table, String packageName, List<FieldData> fields) {

        EntityData entity = new EntityData();
        entity.setPackageName(packageName);
        entity.setTable(table);
        entity.setName(GeneratorUtils.underscoreToUpperCamelcase(table.getName()));
        entity.setFields(fields);

        String typeName = StringUtils.trim(entity.getPackageName()).concat(".");
        typeName += StringUtils.trim(entity.getName());
        entity.setSerialUid(GeneratorUtils.generateSerialVersionUUID(entity.getName(), typeName));

        return entity;
    }

}
