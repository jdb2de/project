package org.jdb2de.core.connection;

import org.jdb2de.core.data.ConnectionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author Rodrigo Tavares
 */
@Component
@Scope(value = "singleton")
public class DataSourceSettings {

    @Autowired
    private ConnectionData connectionData;
    private DataSource dataSource;

    public DataSource dataSource() {
        if (dataSource == null) {
            DataSourceBuilder builder = DataSourceBuilder.create();
            builder.driverClassName(connectionData.getDriver());
            builder.url(connectionData.getUrl());
            builder.username(connectionData.getUserName());
            builder.password(connectionData.getPassword());
            dataSource = builder.build();
        }

        return dataSource;
    }

}
