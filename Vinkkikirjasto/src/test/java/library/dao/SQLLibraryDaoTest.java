package library.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import library.domain.Book;
import library.domain.Suggestion;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author machenna
 */
public class SQLLibraryDaoTest {
    
    private SQLLibraryDao sqldao;
    
    @Before
    public void setUp() throws FileNotFoundException, IOException {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));

        String testiDB = properties.getProperty("testiDB");
        sqldao = new SQLLibraryDao(testiDB);
    }
    
    @Test
    public void addingSuggestionsToDatabaseWorks() {
        Suggestion book = new Book();
        book.addDetail("nimi", "Test Name");
        book.addDetail("kirjoittaja", "Test Author");
        book.addDetail("sivumäärä", "100");
        sqldao.add(book);
        assertEquals(1, sqldao.getBooks().size());
    }
    
    @Test
    public void listingSuggestionsFromDatabaseWorks() {
        Suggestion book = new Book();
        book.addDetail("nimi", "Test Name");
        book.addDetail("kirjoittaja", "Test Author");
        book.addDetail("sivumäärä", "100");
        sqldao.add(book);
        List<Suggestion> books = sqldao.getBooks();
        assertEquals("Test Name", books.get(0).getDetail("nimi"));
    }
}
