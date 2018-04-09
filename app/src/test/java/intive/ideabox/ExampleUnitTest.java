package intive.ideabox;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import intive.ideabox.models.DataProvider;
import intive.ideabox.models.IdeaData;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ExampleUnitTest {

    private static DataProvider mockedDataProvider;
    private static IdeaData ideaData1;
    private static IdeaData ideaData2;


    @BeforeClass
    public static void setUp(){
        mockedDataProvider = mock(DataProvider.class);

        ideaData1 = new IdeaData("pierwsza idea", "pierwszy user");
        ideaData2 = new IdeaData("druga idea", "drugi user");

        when(mockedDataProvider.saveIdea("pierwsza idea")).thenReturn(true);
        when(mockedDataProvider.loadIdea()).thenReturn(Arrays.asList(ideaData1, ideaData2));

    }

    @Test
    public void testInterfaceMethods() throws Exception {

        List<IdeaData> testLoadIdeaData1 = mockedDataProvider.loadIdea();
        Boolean testSaveIdeaData2 = mockedDataProvider.saveIdea("pierwsza idea");

        assertNotNull(testSaveIdeaData2);
        assertNotNull(testLoadIdeaData1);

        IdeaData testIdeaData = testLoadIdeaData1.get(0);

        assertEquals(true, testSaveIdeaData2);
        assertEquals("pierwsza idea", testIdeaData.ideatext);
        assertEquals("pierwszy user", testIdeaData.ideauser);

        testIdeaData = testLoadIdeaData1.get(1);
        assertEquals("druga idea", testIdeaData.ideatext);
        assertEquals("drugi user", testIdeaData.ideauser);


    }


}