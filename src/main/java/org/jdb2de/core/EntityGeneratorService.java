package org.jdb2de.core;

import org.jdb2de.core.information.impl.PostgresInformationImpl;
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
    private PostgresInformationImpl dbInformation;

    public void generate() {

        List<String> ls = dbInformation.getAllTables(null);
        if (ls == null) {
            System.out.println("No tables.....");
            return;
        }

        ls.forEach((s) -> System.out.println(s));
    }

}
