package org.jdb2de.core.configuration;

import org.apache.commons.lang3.StringUtils;
import org.jdb2de.core.data.ConnectionConfigurationData;
import org.jdb2de.core.data.ParameterData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author Rodrigo Tavares
 */
@Component
@PropertySource(value = "file:jdb2de-command-line.properties", ignoreResourceNotFound = true)
public class StartupListener {

    private final ParameterData parameterData;

    private final ConnectionConfigurationData connectionConfigurationData;

    @Value("${config.entity.path:#{null}}")
    private String configEntityPath;

    @Value("${config.entity.package:#{null}}")
    private String configEntityPackage;

    @Value("${config.entity.suffix:#{null}}")
    private String configEntitySuffix;

    @Value("${config.composite.pk.package:#{null}}")
    private String configPrimaryKeyPackage;

    @Value("${config.author:#{null}}")
    private String configAuthor;

    @Value("${config.table.search:#{null}}")
    private String configTableSearch;

    @Value("${config.table.name:#{null}}")
    private String configTableName;

    @Value("${config.pk.field.name.regex:#{null}}")
    private String configPrimaryKeyFieldNameRegex;

    @Value("${config.table.name.regex:#{null}}")
    private String configTableNameRegex;

    @Value("${config.is.table.name.regex.clean.entity.name:#{\"true\"}}")
    private String configTableNameRegexCleanEntityName;

    @Value("${config.is.table.name.regex.clean.fk.field:#{\"true\"}}")
    private String configTableNameRegexCleanForeignKeyField;

    @Value("${config.table.constant.pk.name:#{null}}")
    private String configTableConstantPrimaryKeyName;

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

    @Autowired
    public StartupListener(ParameterData parameterData, ConnectionConfigurationData connectionConfigurationData) {
        this.parameterData = parameterData;
        this.connectionConfigurationData = connectionConfigurationData;
    }

    /**
     * Listener method executed on application startup
     */
    @EventListener(ContextRefreshedEvent.class)
    private void onApplicationEvent() {
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
        parameterData.setEntitySuffix(configEntitySuffix);
        parameterData.setCompositePrimaryKeyPackage(configPrimaryKeyPackage);
        parameterData.setAuthor(configAuthor);
        parameterData.setTableSearch(configTableSearch);
        parameterData.setTableName(configTableName);
        parameterData.setPrimaryKeyFieldNameRegex(configPrimaryKeyFieldNameRegex);
        parameterData.setTableNameRegex(configTableNameRegex);

        // Boolean conversion
        final String cleanEntityName = StringUtils.lowerCase(configTableNameRegexCleanEntityName);
        final String cleanForeignKeyField = StringUtils.lowerCase(configTableNameRegexCleanForeignKeyField);
        final String strTrue = Boolean.TRUE.toString();

        parameterData.setTableNameRegexCleanEntityName(strTrue.equals(cleanEntityName));
        parameterData.setTableNameRegexCleanForeignKeyField(strTrue.equals(cleanForeignKeyField));
    }

    /**
     * Load database configuration parameters
     */
    private void loadDatabaseParameters() {
        connectionConfigurationData.setDriver(databaseDriver);
        connectionConfigurationData.setUrl(databaseUrl);
        connectionConfigurationData.setUserName(databaseUsername);
        connectionConfigurationData.setPassword(databasePassword);
        connectionConfigurationData.setSchema(databaseSchema);
        connectionConfigurationData.setCatalog(databaseCatalog);
    }
}
