package org.jdb2de.core;

import freemarker.template.Configuration;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jdb2de.core.data.ParameterData;
import org.jdb2de.core.exception.ValidationException;
import org.jdb2de.core.information.IDatabaseInformation;
import org.jdb2de.core.util.GeneratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author  Rodrigo Tavares
 */
@Service
public class GeneratorService {

    private static final Logger LOG = LoggerFactory.getLogger(GeneratorService.class);

    /**
     * Copyright and licence file name
     */
    private static final String COPYRIGHT_FILE = "copyright";

    /**
     *
     */
    private static final String MSG_PARAMETER_NOT_FOUND = "Required parameter [{}] not found";

    @Autowired
    private IDatabaseInformation information;

    @Autowired
    private ParameterData parameters;

    @Autowired
    private Configuration freemarkerConfig;

    private String searchRegex;

    @PostConstruct
    private void init() throws IOException {
        // Load copyright and licence from file
        parameters.setCopyright(com.google.common.io.Files.readLines(GeneratorUtils.fileFromResource(COPYRIGHT_FILE),
                Charset.defaultCharset()));
    }

    /**
     * Checks if all parameters were correctly informed
     */
    private void validateParameters() throws ValidationException {

        if (StringUtils.isEmpty(parameters.getEntityPath())) {
            throw new ValidationException(MSG_PARAMETER_NOT_FOUND, "config.entity.path");
        }

        if (StringUtils.isEmpty(parameters.getEntityPackage())) {
            throw new ValidationException(MSG_PARAMETER_NOT_FOUND, "config.entity.package");
        }

        if (StringUtils.isEmpty(parameters.getPkPackage())) {
            throw new ValidationException(MSG_PARAMETER_NOT_FOUND, "config.pk.package");
        }

        if (StringUtils.indexOf(parameters.getPkPackage(), parameters.getEntityPackage()) != 0) {
            throw new ValidationException("Parameter [config.pk.package] is invalid, it must be a substring of [config.entity.package]");
        }

        Path entityPath = Paths.get(parameters.getEntityPath());
        if (!entityPath.toFile().exists()) {
            throw new ValidationException("Entity generation directory not found [config.entity.path={}]", entityPath);
        }

        if (!entityPath.toFile().canWrite()) {
            throw new ValidationException("Entity generation directory is not writable");
        }

        if (!GeneratorUtils.isValidSearchWildcard(parameters.getTableSearch())) {
            throw new ValidationException("Invalid value to table search");
        }

        // Update search regex
        searchRegex = GeneratorUtils.replaceSearchToRegex(parameters.getTableSearch());

        // If there is not an author gets the operating system user name
        if (StringUtils.isEmpty(parameters.getAuthor())) {
            parameters.setAuthor(System.getProperty("user.name"));
        }
    }

    /**
     * Set primary key path based on entity path and primary key package
     */
    private void createPkPath() throws IOException {

        String additionalPath = StringUtils.replace(parameters.getPkPackage(), parameters.getEntityPackage(), "");
        additionalPath = StringUtils.replace(additionalPath, ".", File.separator);
        parameters.setPkPath(parameters.getEntityPath().concat(additionalPath));

        Path pkPath = Paths.get(parameters.getPkPath());
        if (!pkPath.toFile().exists()) {
            Files.createDirectories(pkPath);
        }
    }

    public void generate() throws ValidationException, IOException {

        LOG.info("Initializing entity generation");
        // Execute a parameter validation
        validateParameters();

        // Set primary key path based on entity path and pk package
        createPkPath();

        List<String> ls;
        if (!StringUtils.isEmpty(parameters.getTableName())) {
            ls = querySingleTable(parameters.getTableName());
        } else {
            ls = information.allTables(searchRegex);
        }

        if (CollectionUtils.isEmpty(ls)) {
            LOG.info("No tables found.....");
            return;
        }

        LOG.info("Found {} tables", ls.size());


    }

    private List<String> querySingleTable(String tableName) {
        List<String> ls = new ArrayList<>();
        if (information.checkIfTableExists(parameters.getTableName())) {
            ls.add(tableName);
        }

        return ls;
    }


}
