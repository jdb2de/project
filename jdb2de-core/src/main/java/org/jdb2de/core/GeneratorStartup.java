package org.jdb2de.core;

import com.google.common.io.Files;
import org.jdb2de.CommandLineGenerator;
import org.jdb2de.core.data.ParameterData;
import org.jdb2de.core.data.ConnectionData;
import org.jdb2de.core.util.LanguageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;

@Component
public class GeneratorStartup {

    /**
     * Instance to register log
     */
    private static final Logger LOG = LoggerFactory.getLogger(CommandLineGenerator.class);

    /**
     *
     */
    private static final String KEY_CFG_ENTITY_PATH = "config.entity.path";

    /**
     *
     */
    private static final String KEY_CFG_ENTITY_PACKAGE = "config.entity.package";

    /**
     *
     */
    private static final String KEY_CFG_ID_PACKAGE = "config.id.package";

    /**
     *
     */
    private static final String KEY_CFG_AUTHOR = "config.author";

    /**
     *
     */
    private static final String KEY_DB_DRIVER = "database.driver";

    /**
     *
     */
    private static final String KEY_DB_URL = "database.url";

    /**
     *
     */
    private static final String KEY_DB_USERNAME = "database.username";

    /**
     *
     */
    private static final String KEY_DB_PASSWORD = "database.password";

    /**
     *
     */
    private static final String KEY_DB_SCHEMA = "database.schema";

    /**
     *
     */
    private static final String KEY_DB_CATALOG = "database.catalog";


    @Autowired
    private ParameterData parameterData;

    @Autowired
    private ConnectionData connectionData;

    @PostConstruct
    public void init() throws IOException {

        LOG.info("Running post construct tasks");

        // TODO Remove hard code
        parameterData.setEntityPackage("org.jdb2de.model");
        parameterData.setIdPackage("org.jdb2de.model.pk");
        parameterData.setCopyright(Files.readLines(LanguageUtils.fileFromResource("copyright"),
                Charset.defaultCharset()));
        parameterData.setAuthor("Rodrigo Tavares");
        parameterData.setEntityPath("/home/sistemas/project-jdb2de/jdb2de-sample/src/main/java/org/jdb2de/sample/model");

        connectionData.setSchema("public");
        connectionData.setDriver("org.postgresql.Driver");
        connectionData.setUrl("jdbc:postgresql://localhost:5432/postgres");
        connectionData.setUserName("postgres");
        connectionData.setPassword("postgres");
    }

}
