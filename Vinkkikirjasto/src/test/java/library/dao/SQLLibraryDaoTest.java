package library.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import library.domain.Book;
import library.domain.Suggestion;
import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        Suggestion book = new Suggestion("Book");
        book.addDetail("nimi", "Test Name");
        book.addDetail("kirjoittaja", "Test Author");
        book.addDetail("sivumäärä", "100");
        sqldao.add(book);
        assertEquals(1, sqldao.getBooks().size());
    }
    
    @Test
    public void listingSuggestionsFromDatabaseWorks() {
        Suggestion book = new Suggestion("Book");
        book.addDetail("nimi", "Test Name");
        book.addDetail("kirjoittaja", "Test Author");
        book.addDetail("sivumäärä", "100");
        sqldao.add(book);
        List<Book> books = sqldao.getBooks();
        assertEquals("Test Name", books.get(0).getName());
    }
}
