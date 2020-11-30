package library.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import library.domain.Article;
import library.domain.Book;
import library.domain.Suggestion;
import org.junit.After;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SQLLibraryDaoTest {
    
    private SQLLibraryDao sqldao;
    private Suggestion book;
    private Suggestion article;
    
    @Before
    public void setUp() throws FileNotFoundException, IOException {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));

        String testiDB = properties.getProperty("testiDB");
        sqldao = new SQLLibraryDao(testiDB);
        
        book = new Book();
        book.addDetail("nimi", "Test Book");
        book.addDetail("kirjoittaja", "Test Author");
        book.addDetail("sivumäärä", "100");
        
        article = new Article();
        article.addDetail("nimi", "Test Article");
        article.addDetail("url", "www.testurl.com");
    }
    
    @Test
    public void addingBookToDatabaseWorks() {
        boolean added = sqldao.add(book);
        assertTrue(added);
    }
    
    @Test
    public void addingArticleToDatabaseWorks() {
        boolean added = sqldao.add(article);
        assertTrue(added);
    }
    
    @Test
    public void listingBooksFromDatabaseWorks() {
        sqldao.add(book);
        List<Book> books = sqldao.getBooks();
        assertEquals("Test Book", books.get(0).getDetail("nimi"));
    }
    
    @Test
    public void listingArticlesFromDatabaseWorks() {
        sqldao.add(article);
        List<Article> articles = sqldao.getArticles();
        assertEquals("Test Article", articles.get(0).getDetail("nimi"));
    }
    
    @Test
    public void deletingBookFromDatabaseWorks() {
        sqldao.add(book);
        assertTrue(!sqldao.getBooks().isEmpty());
        boolean removed = sqldao.remove("Test Book", "book");
        assertTrue(removed);
    }
    
    @Test
    public void deletingArticleFromDatabaseWorks() {
        sqldao.add(article);
        assertTrue(!sqldao.getArticles().isEmpty());
        boolean removed = sqldao.remove("Test Article", "article");
        assertTrue(removed);
    }
    
    @Test
    public void cantDeleteNonexistingType() {
        boolean removed = sqldao.remove("Non-existent", "None");
        assertFalse(removed);
    }
    
    @Test
    public void updatingReadPagesWorks(){
        boolean updated = sqldao.update("book", "Test Book", "10");
        assertTrue(updated);
    }
    
    @After
    public void tearDown() {
        sqldao.clearDatabase();
    }
    
}
