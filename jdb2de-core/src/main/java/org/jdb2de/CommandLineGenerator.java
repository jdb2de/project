package org.jdb2de;

import org.jdb2de.core.GeneratorService;
import org.jdb2de.core.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

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

    /**
     * Indicates whether the application is running, avoid running the generator method during unit tests
     */
    private static boolean generatorRunning = false;

    private final ConfigurableApplicationContext context;
    private final GeneratorService service;

    @Autowired
    public CommandLineGenerator(ConfigurableApplicationContext context, GeneratorService service) {
        this.context = context;
        this.service = service;
    }

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
            // Start the generation
            service.generate();
        } finally {
            // Close application
            SpringApplication.exit(context);
        }
    }
}

