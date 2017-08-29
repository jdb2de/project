package org.jdb2de.core.information.impl;

import com.google.common.base.Preconditions;
import org.jdb2de.core.connection.DataSourceSettings;
import org.jdb2de.core.data.ColumnData;
import org.jdb2de.core.information.IDatabaseInformation;
import org.jdb2de.core.util.LanguageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 *
 * @author  Rodrigo Tavares
 */
@Component
public class PostgresInformation implements IDatabaseInformation {

    @Autowired
    private DataSourceSettings dataSourceSettings;
    private JdbcTemplate jdbcTemplate;
    private Map<String, Class<?>> types;

    public JdbcTemplate getJdbcTemplate() {
        if (jdbcTemplate == null) {
            jdbcTemplate = new JdbcTemplate(dataSourceSettings.dataSource());
        }
        return jdbcTemplate;
    }

    @Override
    public List<String> allTables(String schema, String regex) {
        List<String> ls = new ArrayList<>();
        String sql = ""
                + "select c.relname "
                + "  from pg_class c "
                + "  join pg_namespace n "
                + "    on c.relnamespace = n.oid "
                + " where c.relkind = 'r' "
                + "   and n.nspname = ?";
        getJdbcTemplate().query(sql, LanguageUtils.toArray(schema),
                (rs, rowNum) -> rs.getString("relname")).forEach(ls::add);

        return ls;
    }

    @Override
    public boolean checkIfTableExists(String schema, String tableName) {
        String sql = ""
                + "select count(*) "
                + "  from pg_class c "
                + "  join pg_namespace n "
                + "    on c.relnamespace = n.oid "
                + " where c.relkind = 'r' "
                + "   and n.nspname = ? "
                + "   and c.relname = ?";
        int qtd = getJdbcTemplate().queryForObject(sql, LanguageUtils.toArray(schema, tableName), Integer.class);
        return qtd > 0;
    }

    @Override
    public List<ColumnData> tableColumns(String schema, String tableName) {
        List<ColumnData> ls = new ArrayList<>();
        String sql = ""
                + "select a.attname "
                + "     , t.typname "
                + "     , a.* "
                + "  from pg_attribute a "
                + "  join pg_class c "
                + "    on a.attrelid = c.oid "
                + "  join pg_type t "
                + "    on a.atttypid = t.oid "
                + "  join pg_namespace n "
                + "    on c.relnamespace = n.oid "
                + " where c.relkind = 'r' "
                + "   and n.nspname = ? "
                + "   and c.relname = ? "
                + "   and a.attnum > 0";

        getJdbcTemplate().query(sql, LanguageUtils.toArray(schema, tableName),
                (rs, rowNum) -> {
                    ColumnData columnData = new ColumnData();
                    columnData.setName(rs.getString("attname"));
                    columnData.setType(rs.getString("typname"));
                    return columnData;
                }).forEach(ls::add);
        return ls;
    }

    @Override
    public String tableComment(String schema, String tableName) {
        return null;
    }

    @Override
    public String columnComment(String schema, String tableName) {
        return null;
    }


    @Override
    public Class<?> translateDbType(String dbType) {
        Class<?> result = getTypes().get(dbType);
        Preconditions.checkNotNull(result, "Database type %s not mapped", dbType);
        return result;
    }

    private Map<String, Class<?>> getTypes() {
        if (types == null) {
            types = new HashMap<>();
            types.put("varchar", String.class);
            types.put("int4", Integer.class);
            types.put("timestamp", Date.class);
            types.put("text", String.class);
        }
        return types;
    }

    /*
    Get comments from oid, table or column
    select obj_description(17345)
     */

}
