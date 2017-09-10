package org.jdb2de;

import com.google.common.io.Files;
import org.jdb2de.core.GeneratorService;
import org.jdb2de.core.data.ConnectionData;
import org.jdb2de.core.data.ParameterData;
import org.jdb2de.core.util.LanguageUtils;
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
import java.nio.charset.Charset;

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
        SpringApplication.run(CommandLineGenerator.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        LOG.info("Database to Documented Entity");
        LOG.info("=============================");
        LOG.info("Starting Command Line Generator...");

        // Set application configuration
        applicationConfiguration();
        // Set database configuration
        databaseConfiguration();

        try {
            // Start the generation
            service.generate();
        } finally {
            // Close application
            SpringApplication.exit(context);
        }
    }

    private void applicationConfiguration() throws IOException {
        parameterData.setEntityPath(configEntityPath);
        parameterData.setEntityPackage(configEntityPackage);
        parameterData.setIdPackage(configIdPackage);
        parameterData.setAuthor(configAuthor);
        parameterData.setCopyright(Files.readLines(LanguageUtils.fileFromResource("copyright"),
                Charset.defaultCharset()));
    }

    private void databaseConfiguration() {
        connectionData.setDriver(databaseDriver);
        connectionData.setUrl(databaseUrl);
        connectionData.setUserName(databaseUsername);
        connectionData.setPassword(databasePassword);
        connectionData.setSchema(databaseSchema);
        connectionData.setCatalog(databaseCatalog);
    }
}

