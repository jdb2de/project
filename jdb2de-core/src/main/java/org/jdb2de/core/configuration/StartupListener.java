package org.jdb2de.core.configuration;

import org.jdb2de.core.data.ConnectionData;
import org.jdb2de.core.data.ParameterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "file:jdb2de-command-line.properties", ignoreResourceNotFound = true)
public class StartupListener {

    @Autowired
    private ParameterData parameterData;

    @Autowired
    private ConnectionData connectionData;


    @Value("${config.entity.path:#{null}}")
    private String configEntityPath;

    @Value("${config.entity.package:#{null}}")
    private String configEntityPackage;

    @Value("${config.pk.package:#{null}}")
    private String configPkPackage;

    @Value("${config.author:#{null}}")
    private String configAuthor;

    @Value("${config.table.search:#{null}}")
    private String configTableSearch;

    @Value("${config.table.name:#{null}}")
    private String configTableName;

    @Value("${database.driver:#{null}}")
    private String databaseDriver;

    @Value("${database.url:#{null}}")
    private String databaseUrl;

    @Value("${database.username:#{null}}")
    private String databaseUsername;

    @Value("${database.password:#{null}}")
    private String databasePassword;

    @Value("${database.schema:#{null}}")
    private String databaseSchema;

    @Value("${database.catalog:#{null}}")
    private String databaseCatalog;

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent() {
        // Set generation parameters
        loadGenerationParameters();
        // Set database parameters
        loadDatabaseParameters();
    }

    /**
     * Load generation parameters
     */
    private void loadGenerationParameters() {
        parameterData.setEntityPath(configEntityPath);
        parameterData.setEntityPackage(configEntityPackage);
        parameterData.setPkPackage(configPkPackage);
        parameterData.setAuthor(configAuthor);
        parameterData.setTableSearch(configTableSearch);
        parameterData.setTableName(configTableName);
    }

    /**
     * Load database configuration parameters
     */
    private void loadDatabaseParameters() {
        connectionData.setDriver(databaseDriver);
        connectionData.setUrl(databaseUrl);
        connectionData.setUserName(databaseUsername);
        connectionData.setPassword(databasePassword);
        connectionData.setSchema(databaseSchema);
        connectionData.setCatalog(databaseCatalog);
    }
}
