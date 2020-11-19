package library.domain;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class SuggestionTest {
    Suggestion genericSuggestion;
    
    public SuggestionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        genericSuggestion = new Suggestion("Generic");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addDetail method, of class Suggestion.
     */
    @Test
    public void testAddDetail() {
        genericSuggestion.addDetail("name", "Test Name");
        genericSuggestion.addDetail("author", "Test Author");
        String expName = "Test Name";
        String expAuthor = "Test Author";
        HashMap<String, String> resMap = genericSuggestion.getDetails();
        String resName = resMap.get("name");
        String resAuthor = resMap.get("author");
        assertEquals(expName, resName);
        assertEquals(expAuthor, resAuthor);
    }

    /**
     * Test of addDetails, getDetailTypes and getDetailValues methods, of class Suggestion.
     */
    @Test
    public void testAddDetailsGetDetailTypesGetDetailValues() {
        String[] types = {"name", "author", "pages"};
        String[] values = {"Test Name", "Test Author", "999"};
        genericSuggestion.addDetails(types, values);
        String[] resTypes = genericSuggestion.getDetailTypes();
        String[] resValues = genericSuggestion.getDetailValues();
        Assert.assertArrayEquals(types, resTypes);
        Assert.assertArrayEquals(values, resValues);
    }

    /**
     * Test of getType method, of class Suggestion.
     */
    @Test
    public void testGetType() {
        String exp = "Generic";
        String res = genericSuggestion.getType();
        assertEquals(exp, res);
    }

    /**
     * Test of getDetail method, of class Suggestion.
     */
    @Test
    public void testGetDetail() {
        genericSuggestion.addDetail("name", "Test Name");
        String exp = "Test Name";
        String res = genericSuggestion.getDetail("name");
        assertEquals(exp, res);
    }

    /**
     * Test of getDetails method, of class Suggestion.
     */
    @Test
    public void testGetDetails() {
        String[] types = {"name", "author", "pages"};
        String[] values = {"Test Name", "Test Author", "999"};
        genericSuggestion.addDetails(types, values);
        HashMap<String, String> resMap = genericSuggestion.getDetails();
        boolean res = true;
        for (int i = 0; i < 3; i++) {
            if (!resMap.get(types[i]).equals(values[i])) {
                res = false;
            }
        }
        assertTrue(res);
    }
    
}
