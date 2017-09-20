package org.jdb2de.core.test;

import org.jdb2de.core.GeneratorService;
import org.jdb2de.core.data.ParameterData;
import org.jdb2de.core.information.IDatabaseInformation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeneratorServiceTest {

    @Mock
    private IDatabaseInformation information;

    @Mock
    private ParameterData parameters;

    @InjectMocks
    private GeneratorService generatorService;

    @Before
    public void setup() {
        List<String> allTables = new ArrayList<>();
        allTables.add("country");
        allTables.add("state");
        allTables.add("city");

        Mockito.when(information.allTables(null)).thenReturn(allTables);

        Mockito.when(parameters.getEntityPath()).thenReturn(System.getProperty("java.io.tmpdir"));
        Mockito.when(parameters.getEntityPackage()).thenReturn("temp");
        Mockito.when(parameters.getCompositePkPackage()).thenReturn("temp.pk");
    }

    @Test
    public void testGeneration() throws Exception {
        // FIXME Implements unit tests
        // generatorService.generate();
    }

}
