package org.jdb2de;

import org.jdb2de.core.GeneratorService;
import org.jdb2de.core.data.ConnectionData;
import org.jdb2de.core.data.ParameterData;
import org.jdb2de.core.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;

/**
 *
 * @author  Rodrigo Tavares
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@PropertySource(value = "file:jdb2de-command-line.properties", ignoreResourceNotFound = true)
public class CommandLineGenerator implements CommandLineRunner {

    /**
     * Instance to register log
     */
    private static final Logger LOG = LoggerFactory.getLogger(CommandLineGenerator.class);

    /**
     * Indicates whether the application is running, avoid running the generator method during unit tests
     */
    private static boolean generatorRunning = false;

    @Autowired
    private ParameterData parameterData;

    @Autowired
    private ConnectionData connectionData;

    @Autowired
    private GeneratorService service;

    @Autowired
    private ConfigurableApplicationContext context;

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

    public static void main(String[] args) {
        // Indicates that the generator is running
        generatorRunning = true;
        SpringApplication.run(CommandLineGenerator.class, args);
    }

    @Override
    public void run(String... strings) throws ValidationException, IOException {

        // Checks if application is running by main method
        if (!generatorRunning) {
            return;
        }

        LOG.info("Database to Documented Entity");
        LOG.info("=============================");
        LOG.info("Starting Command Line Generator...");

        try {
            // Set application configuration
            applicationConfiguration();
            // Set database configuration
            databaseConfiguration();
            // Start the generation
            service.generate();
        } finally {
            // Close application
            SpringApplication.exit(context);
        }
    }

    /**
     * Load application configuration parameters
     * @throws IOException Error
     */
    private void applicationConfiguration() {
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
    private void databaseConfiguration() {
        connectionData.setDriver(databaseDriver);
        connectionData.setUrl(databaseUrl);
        connectionData.setUserName(databaseUsername);
        connectionData.setPassword(databasePassword);
        connectionData.setSchema(databaseSchema);
        connectionData.setCatalog(databaseCatalog);
    }


}

