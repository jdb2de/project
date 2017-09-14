package org.jdb2de.core.util;

import org.apache.commons.lang3.StringUtils;
import org.jdb2de.core.data.database.ColumnData;
import org.jdb2de.core.data.database.TableData;
import org.jdb2de.core.data.enitity.EntityData;
import org.jdb2de.core.data.enitity.FieldData;

import java.util.List;

/**
 *
 * @author Rodrigo Tavares
 */
public final class GeneratorFactory {

    /**
     * Create a new instance of {@link TableData}
     *
     * @param tableName Table name
     * @param comment Table comment
     * @param columns Table columns
     * @return A new instance of {@link TableData}
     */
    public static TableData createTableData(String tableName, String comment, List<ColumnData> columns) {
        TableData table = new TableData();
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
    public static EntityData createEntityData(TableData table, String packageName, List<FieldData> fields) {

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
