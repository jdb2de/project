package org.jdb2de.core.information.impl;

import org.jdb2de.core.connection.DataSourceSettings;
import org.jdb2de.core.data.ColumnData;
import org.jdb2de.core.information.IDatabaseInformation;
import org.jdb2de.core.util.LanguageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author  Rodrigo Tavares
 */
@Component
public class PostgresInformation implements IDatabaseInformation {

    @Autowired
    private DataSourceSettings dataSourceSettings;
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        if (jdbcTemplate == null) {
            jdbcTemplate = new JdbcTemplate(dataSourceSettings.dataSource());
        }
        return jdbcTemplate;
    }

    @Override
    public List<String> allTables(String schema, String regex) {
        List<String> ls = new ArrayList<>();
        String sql = "select c.relname " +
                "  from pg_class c " +
                "  join pg_namespace n " +
                "    on c.relnamespace = n.oid " +
                " where c.relkind = 'r' " +
                "   and n.nspname = ?";
        getJdbcTemplate().query(sql, LanguageUtils.toArray(schema),
                (rs, rowNum) -> rs.getString("relname")).forEach(ls::add);

        return ls;
    }

    @Override
    public boolean checkIfTableExists(String schema, String tableName) {
        String sql = "select count(*) " +
                "  from pg_class c " +
                "  join pg_namespace n " +
                "    on c.relnamespace = n.oid " +
                " where c.relkind = 'r' " +
                "   and n.nspname = ? " +
                "   and c.relname = ?";
        int qtd = getJdbcTemplate().queryForObject(sql, LanguageUtils.toArray(schema, tableName), Integer.class);
        return qtd > 0;
    }

    @Override
    public List<ColumnData> tableColumns(String schema, String tableName) {
        return null;
    }

/*
select a.attname
     , t.typname
     , a.*
  from pg_attribute a
  join pg_class c
    on a.attrelid = c.oid
  join pg_type t
    on a.atttypid = t.oid
  join pg_namespace n
    on c.relnamespace = n.oid
 where c.relkind = 'r'
   and n.nspname = 'public'
   and c.relname = 'language'
   and a.attnum > 0
*/

}
