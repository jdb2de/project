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
@PropertySource("file:jdb2de-command-line.properties")
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

    @Value("${config.entity.path}")
    private String configEntityPath;

    @Value("${config.entity.package}")
    private String configEntityPackage;

    @Value("${config.id.package}")
    private String configIdPackage;

    @Value("${config.author}")
    private String configAuthor;

    @Value("${database.driver}")
    private String databaseDriver;

    @Value("${database.url}")
    private String databaseUrl;

    @Value("${database.username}")
    private String databaseUsername;

    @Value("${database.password}")
    private String databasePassword;

    @Value("${database.schema}")
    private String databaseSchema;

    @Value("${database.catalog}")
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
        parameterData.setIdPackage(configIdPackage);
        parameterData.setAuthor(configAuthor);
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

