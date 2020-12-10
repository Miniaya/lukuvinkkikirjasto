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
        sqldao.clearDatabase();

        book = new Book();
        book.addDetail("nimi", "Test Book");
        book.addDetail("kirjoittaja", "Test Author");
        book.addDetail("sivumäärä", "100");
        book.addDetail("tagit", "tag1, tag2");

        article = new Article();
        article.addDetail("nimi", "Test Article");
        article.addDetail("url", "www.testurl.com");
        article.addDetail("tagit", "tag1, tag2");
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
        List<Suggestion> books = sqldao.getAll();
        assertEquals("Test Book", books.get(0).getDetail("nimi"));
    }

    @Test
    public void listingArticlesFromDatabaseWorks() {
        sqldao.add(article);
        List<Suggestion> articles = sqldao.getAll();
        assertEquals("Test Article", articles.get(2).getDetail("nimi"));
    }

    @Test
    public void deletingBookFromDatabaseWorks() {
        sqldao.add(book);
        assertTrue(!sqldao.getAll().isEmpty());
        boolean removed = sqldao.remove("Test Book", "book");
        assertTrue(removed);
    }

    @Test
    public void deletingArticleFromDatabaseWorks() {
        assertTrue(!sqldao.getAll().isEmpty());
        boolean removed = sqldao.remove("Test Article", "article");
        assertTrue(removed);
    }

    @Test
    public void cantDeleteNonexistingType() {
        boolean removed = sqldao.remove("Non-existent", "None");
        assertFalse(removed);
    }

    @Test
    public void cantDeleteNonexistentBookOrArticle() {
        boolean removedBook = sqldao.removeBook("Non Existent");
        assertFalse(removedBook);
        boolean removedArticle = sqldao.removeArticle("Non Existent");
        assertFalse(removedArticle);
    }

    @Test
    public void updatingReadPagesWorks() {
        boolean updated = sqldao.update("book", "Test Book", "10");
        assertTrue(updated);
    }

    @Test
    public void updatingNonexistentBookDoesntWork() {
        boolean updated = sqldao.update("book", "Non Existent", "100");
        assertFalse(updated);
    }

    @Test
    public void updatingOnlyWorksForBooks() {
        boolean updated = sqldao.update("article", "Article", "100");
        assertFalse(updated);
    }

    @Test
    public void addingTagsToExistingSuggestionsWorks() {
        sqldao.add(article);
        boolean updatedArticle = sqldao.updateArticleTag("Test Article", "jee");
        assertTrue(updatedArticle);
        sqldao.add(book);
        boolean updatedBook = sqldao.updateBookTag("Test Book", "jee");
        assertTrue(updatedBook);
    }
    
    @Test
    public void addingTagsToNonexistingSuggestionDoesntWork() {
        boolean updatedArticle = sqldao.updateArticleTag("Moi", "jee");
        assertFalse(updatedArticle);
        boolean updatedBook = sqldao.updateBookTag("Hei", "jee");
        assertFalse(updatedBook);
    }

    @After
    public void tearDown() {
        sqldao.clearDatabase();
    }

}
