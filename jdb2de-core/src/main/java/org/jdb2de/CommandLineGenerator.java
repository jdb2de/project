package org.jdb2de;

import org.jdb2de.core.EntityGeneratorService;
import org.jdb2de.core.data.ConnectionData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 *
 * @author  Rodrigo Tavares
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class CommandLineGenerator implements CommandLineRunner {

    /**
     * Instance to register log
     */
    private static final Logger LOG = LoggerFactory.getLogger(CommandLineGenerator.class);

    @Autowired
    private ConnectionData connectionData;

    @Autowired
    private EntityGeneratorService service;

    public static void main(String[] args) {
        SpringApplication.run(CommandLineGenerator.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        LOG.info("Database to Documented Entity");
        LOG.info("=============================");
        LOG.info("Starting Command Line Generator...");


        // TODO Remove hard code
        connectionData.setDriver("org.postgresql.Driver");
        connectionData.setUrl("jdbc:postgresql://localhost:5432/postgres");
        connectionData.setUserName("postgres");
        connectionData.setPassword("postgres");

        service.generate();
    }
}
