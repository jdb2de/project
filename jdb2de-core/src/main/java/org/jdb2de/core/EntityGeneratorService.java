package org.jdb2de.core;

import org.apache.commons.collections4.CollectionUtils;
import org.jdb2de.core.data.ColumnData;
import org.jdb2de.core.data.ForeignKeyData;
import org.jdb2de.core.information.impl.PostgresInformation;
import org.jdb2de.core.util.NameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author  Rodrigo Tavares
 */
@Service
public class EntityGeneratorService {

    @Autowired
    private PostgresInformation dbInformation;

    public void generate() {

        List<String> ls = dbInformation.allTables("public", null);
        if (ls == null) {
            System.out.println("No tables.....");
            return;
        }

        ls.forEach((s) -> System.out.println(NameUtils.underscoreToTypeCamelcase(s)));
        System.out.println("****************");
        ls.forEach((s) -> System.out.println(NameUtils.underscoreToFieldCamelcase(s)));
        System.out.println("****************");
        System.out.println(dbInformation.checkIfTableExists("public", "parameter"));
        System.out.println("****************");
        System.out.println(dbInformation.checkIfTableExists("public", "time"));
        System.out.println("****************");

        for (String table : ls) {
            System.out.println("****************");
            System.out.println(table + " Columns");
            System.out.println("Comment: " + dbInformation.tableComment("public", table));
            System.out.println("****************");
            System.out.println();

            List<ColumnData> cols = dbInformation.tableColumns("public", table);
            for (ColumnData col : cols) {
                System.out.println(col);
                System.out.println("Comment: " + dbInformation.columnComment("public", table, col.getName()));
                System.out.println();
            }

            List<ForeignKeyData> foreignKeys = dbInformation.tableForeignKeys("public", table);

            if (CollectionUtils.isNotEmpty(foreignKeys)) {
                System.out.println();
                System.out.println("****************");
                System.out.println("Foreign Keys");
                System.out.println("****************");

                System.out.println();

                for (ForeignKeyData foreignKey : foreignKeys) {
                    System.out.println(foreignKey);
                    System.out.println();
                }
            }

        }
        System.out.println("****************");
        System.out.println();
    }

}
