package org.jdb2de.core;

import freemarker.template.Configuration;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jdb2de.core.data.ParameterData;
import org.jdb2de.core.data.database.ColumnData;
import org.jdb2de.core.data.database.TableData;
import org.jdb2de.core.data.enitity.EntityData;
import org.jdb2de.core.exception.ValidationException;
import org.jdb2de.core.information.IDatabaseInformation;
import org.jdb2de.core.util.GeneratorFactory;
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

    private String compositePkPath;

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

        // If there is no composite primary key package, use the same entity package
        if (StringUtils.isEmpty(parameters.getCompositePkPackage())) {
            parameters.setCompositePkPackage(parameters.getEntityPackage());
        }

        if (StringUtils.indexOf(parameters.getCompositePkPackage(), parameters.getEntityPackage()) != 0) {
            throw new ValidationException("Parameter [config.composite.pk.package] is invalid, it must be the same or a nested package of [config.entity.package]");
        }
    }

    /**
     * When a table has a composite primary key, a new class is created with only the primary key fields.
     * This method defines the composite primary key path based on the entity path and primary key package if it is
     * different from the entity package
     */
    private void createCompositePkPath() throws IOException {

        String additionalPath = StringUtils.replace(parameters.getCompositePkPackage(), parameters.getEntityPackage(), "");
        additionalPath = StringUtils.replace(additionalPath, ".", File.separator);
        compositePkPath = parameters.getEntityPath().concat(additionalPath);

        LOG.info("Composite primary key generation directory: {}", compositePkPath);
        Path path = Paths.get(compositePkPath);
        if (!path.toFile().exists()) {
            Files.createDirectories(path);
        }
    }

    public void generate() throws ValidationException, IOException {

        LOG.info("Initializing entity generation");
        // Execute a parameter validation
        validateParameters();

        // Set primary key path based on entity path and pk package
        createCompositePkPath();

        List<String> tableNames;
        if (!StringUtils.isEmpty(parameters.getTableName())) {
            tableNames = querySingleTable(parameters.getTableName());
        } else {
            tableNames = information.allTables(searchRegex);
        }

        if (CollectionUtils.isEmpty(tableNames)) {
            LOG.info("No tables found.....");
            return;
        }

        LOG.info("Found {} tables", tableNames.size());
        for (String tableName : tableNames) {
            LOG.info("Querying table [{}] columns", tableName);

            String comment = information.tableComment(tableName);

            List<ColumnData> columns = information.tableColumns(tableName);
            if (CollectionUtils.isEmpty(columns)) {
                LOG.info("No columns found for table [{}]", tableName);
                continue;
            }

            LOG.info("Found {} columns for table {}", columns.size(), tableName);
            TableData table = GeneratorFactory.createTableData(tableName, comment, columns);
            EntityData entityData = GeneratorFactory.createEntityData(table, parameters.getEntityPackage(), null);

            LOG.info("Entity Data: {}", entityData);
        }
    }

    private List<String> querySingleTable(String tableName) {
        List<String> ls = new ArrayList<>();
        if (information.checkIfTableExists(parameters.getTableName())) {
            ls.add(tableName);
        }

        return ls;
    }


}
