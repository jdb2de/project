package org.jdb2de.core;

import com.google.common.base.Preconditions;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jdb2de.core.component.EntityCreator;
import org.jdb2de.core.data.ParameterData;
import org.jdb2de.core.exception.ValidationException;
import org.jdb2de.core.factory.GeneratorFactory;
import org.jdb2de.core.information.IDatabaseInformation;
import org.jdb2de.core.model.ColumnModel;
import org.jdb2de.core.model.ColumnParameterModel;
import org.jdb2de.core.model.TableModel;
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

    private final IDatabaseInformation information;

    private final ParameterData parameters;

    private final EntityCreator entityCreator;

    private String searchRegex;

    private String compositePkPath;

    @Autowired
    public GeneratorService(ParameterData parameters, IDatabaseInformation information, EntityCreator entityCreator) {
        this.information = information;
        this.parameters = parameters;
        this.entityCreator = entityCreator;
    }

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
     * When a table has a composite primary key, a new class is created with the primary key fields only.
     * This method defines the composite primary key path based on the entity path and primary key package if it is
     * different from the entity package
     */
    private void createCompositePkPath() throws IOException {

        String additionalPath = StringUtils.replace(parameters.getCompositePkPackage(), parameters.getEntityPackage(), "");
        additionalPath = StringUtils.replace(additionalPath, ".", File.separator);
        compositePkPath = parameters.getEntityPath().concat(additionalPath);
        if (!parameters.getEntityPackage().equals(parameters.getCompositePkPackage())) {
            return;
        }

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
        List<TableModel> tables = populateTableModel(tableNames);

        // Create the classes
        entityCreator.create(compositePkPath, tables);
    }

    private List<String> querySingleTable(String tableName) {
        List<String> ls = new ArrayList<>();
        if (information.checkIfTableExists(parameters.getTableName())) {
            ls.add(tableName);
        }

        return ls;
    }

    private List<TableModel> populateTableModel(final List<String> tableNames) {
        List<TableModel> tables = new ArrayList<>();
        // For each table name create a new TableModel instance
        tableNames.forEach(tableName -> {
            TableModel table = createTableModel(tableName);
            Preconditions.checkNotNull(table, "Error to create a model to table %s", tableName);
            tables.add(table);
        });

        return tables;
    }

    private TableModel createTableModel(String tableName) {

        LOG.info("Querying table [{}] details", tableName);
        String comment = information.tableComment(tableName);

        List<ColumnModel> columns = information.tableColumns(tableName);
        if (CollectionUtils.isEmpty(columns)) {
            LOG.info("No columns found");
            return null;
        }

        List<String> primaryKeyColumns = information.tablePrimaryKeyColumns(tableName);
        LOG.info("Found {} columns", columns.size(), tableName);
        for (ColumnModel column : columns) {
            String columnComment = information.columnComment(tableName, column.getName());
            ColumnParameterModel columnParameter = information.columnParameters(tableName, column.getName());
            column.setPrimaryKey(primaryKeyColumns.contains(column.getName()));
            column.setComment(columnComment);
            column.setColumnParameter(columnParameter);
        }

        return GeneratorFactory.createTableData(tableName, comment, primaryKeyColumns, columns);
    }
}
