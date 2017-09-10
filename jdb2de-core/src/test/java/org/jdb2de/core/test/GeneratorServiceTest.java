package org.jdb2de.core.test;

import org.jdb2de.core.GeneratorService;
import org.jdb2de.core.information.IDatabaseInformation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class GeneratorServiceTest {

    @Mock
    private IDatabaseInformation information;

    @InjectMocks
    private GeneratorService generatorService;

    @Before
    public void setup() {
        List<String> allTables = new ArrayList<>();
        allTables.add("country");
        allTables.add("state");
        allTables.add("city");
        Mockito.when(information.allTables(null)).thenReturn(allTables);
    }

    @Test
    public void testGeneration() {
        generatorService.generate();
    }

}
