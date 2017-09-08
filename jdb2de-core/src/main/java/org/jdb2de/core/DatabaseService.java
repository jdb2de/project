package org.jdb2de.core;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.jdb2de.core.data.ConnectionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Rodrigo Tavares
 */
@Component
@Scope(value = "singleton")
public class DatabaseService {

    /**
     * Instance to log registration
     */
    private static final Logger LOG = LoggerFactory.getLogger(DatabaseService.class);

    @Autowired
    private ConnectionData connectionData;
    private Connection connection;
    private JdbcTemplate jdbcTemplate;

    private Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(connectionData.getDriver());
                connection = DriverManager.getConnection(connectionData.getUrl(), connectionData.getUserName(),
                        connectionData.getPassword());
            } catch (ClassNotFoundException|SQLException e) {
                LOG.error("Failed to connect to database [user={}, pass=****, url={}]", e,
                        connectionData.getUserName(), connectionData.getUrl());
            }
        }
        return connection;
    }

    public JdbcTemplate getJdbc() {
        if (jdbcTemplate == null) {
            jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(getConnection(), false));
            Preconditions.checkNotNull(jdbcTemplate);
        }
        return jdbcTemplate;
    }

    public String getSchema() {
        return StringUtils.trimToEmpty(connectionData.getSchema());
    }

    public String getCatalog() {
        return StringUtils.trimToEmpty(connectionData.getCatalog());
    }
}
