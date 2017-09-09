package org.jdb2de.core.test.information.impl;

import org.jdb2de.core.information.impl.PostgresInformation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
public class PostgresInformationTest {

    @MockBean
    private PostgresInformation postgresInformation;

    private void givenAllTables() {
        List<String> allTables = new ArrayList<>();
        allTables.add("country");
        allTables.add("state");
        allTables.add("city");
        allTables.add("customer");
        Mockito.when(postgresInformation.allTables(null)).thenReturn(allTables);
        Mockito.when(postgresInformation.allTables("customer")).thenAnswer(invocationOnMock -> {
            String regex = (String) invocationOnMock.getArguments()[0];
            return allTables.stream().filter(s -> s.matches(regex)).collect(Collectors.toList());
        });
    }

    @Test
    public void testListAllTables() {
        givenAllTables();
        List<String> list = postgresInformation.allTables(null);
        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void testListAllTablesRegex() {
        givenAllTables();
        List<String> list = postgresInformation.allTables("customer");
        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(), 1);

    }
}
