package org.jdb2de.core;

import org.jdb2de.core.data.ColumnData;
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

        ColumnData column = new ColumnData();
        column.setName("id");
        column.setType("int8");
        System.out.println(column);
        System.out.println("****************");

    }

}
