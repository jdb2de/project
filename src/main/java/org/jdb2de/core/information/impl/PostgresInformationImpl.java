package org.jdb2de.core.information.impl;

import org.jdb2de.core.connection.DataSourceSettings;
import org.jdb2de.core.information.IDbInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author  Rodrigo Tavares
 */
@Component
public class PostgresInformationImpl implements IDbInformation {

    @Autowired
    private DataSourceSettings dataSourceSettings;
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        if (jdbcTemplate == null) {
            jdbcTemplate = new JdbcTemplate(dataSourceSettings.dataSource());
        }
        return jdbcTemplate;
    }

    public List<String> getAllTables(String regex) {
        List<String> ls = new ArrayList<>();

        String sql = "select * from information_schema.tables where table_schema = 'public'";
        getJdbcTemplate().query(sql, (rs, rowNum) -> rs.getString("table_name")).forEach(ls::add);

        return ls;
    }
}
