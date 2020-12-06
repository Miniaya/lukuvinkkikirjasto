package library.domain;

import java.util.ArrayList;
import java.util.List;
import library.dao.InMemoryLibraryDao;
import library.dao.LibraryDao;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryServiceTest {

    private LibraryDao dao;
    private LibraryService service;
    private String[] bookDetails;
    private ArrayList<String> bookInput;
    private String[] articleDetails;
    private ArrayList<String> articleInput;

    @Before
    public void setUp() {
        dao = new InMemoryLibraryDao();
        service = new LibraryService(dao);
        bookDetails = new String[]{"nimi", "kirjoittaja", "sivumäärä", "tagit"};
        bookInput = new ArrayList<>();
        articleDetails = new String[]{"nimi", "url", "tagit"};
        articleInput = new ArrayList<>();
    }

    @Test
    public void addingValidBookOrArticleReturnsTrue() {
        bookInput.add("TestBook");
        bookInput.add("TestAuthor");
        bookInput.add("100");
        bookInput.add("TestTag");
        assertTrue(service.add("book", bookDetails, bookInput.toArray(new String[bookInput.size()])));
        articleInput.add("TestArticle");
        articleInput.add("TestUrl");
        articleInput.add("TestTag");
        assertTrue(service.add("article", articleDetails, articleInput.toArray(new String[articleInput.size()])));
    }

    @Test
    public void tagsAreTrimmedCorrectly() {
        this.addBookToService("TestBook", "TestAuthor", "100", "tag1, tag2");
        List<Book> books = dao.getBooks();
        assertEquals("tag1 tag2", books.get(0).getDetail("tagit"));
    }

    @Test
    public void noDuplicateTags() {
        this.addBookToService("TestBook", "TestAuthor", "100", "tag1, tag1");
        List<Book> books = dao.getBooks();
        assertEquals("tag1", books.get(0).getDetail("tagit"));
    }

    @Test
    public void noDuplicateTagsWithDifferentCapitalization() {
        this.addBookToService("TestBook", "TestAuthor", "100", "tag1, TAG1");
        List<Book> books = dao.getBooks();
        assertEquals("tag1", books.get(0).getDetail("tagit"));
    }
    
    @Test
    public void searchReturnsEmptyListWhenNoMatches() {
        List<Suggestion> sugs = service.searchByTag("tag");
        assertTrue(sugs.isEmpty());
    }
    
    @Test
    public void searchReturnsSuggestionsWhenMatch() {
        this.addBookToService("RightBook", "RightAuthor", "200", "right");
        List<Suggestion> sugs = service.searchByTag("right");
        assertEquals(1, sugs.size());
    }
    
    @Test
    public void searchReturnsSuggestionsFromBothTypes() {
        this.addBookToService("RightBook", "RightAuthor", "200", "right");
        this.addArticleToService("RightArticle", "articleurl", "right");
        List<Suggestion> sugs = service.searchByTag("right");
        assertEquals(2, sugs.size());
    }
    
    private void addBookToService(String name, String author, String pages, String tags) {
        bookInput.add(name);
        bookInput.add(author);
        bookInput.add(pages);
        bookInput.add(tags);
        service.add("book", bookDetails, bookInput.toArray(new String[bookInput.size()]));
        bookInput.clear();
    }
    
    private void addArticleToService(String name, String url, String tags) {
        articleInput.add(name);
        articleInput.add(url);
        articleInput.add(tags);
        service.add("article", articleDetails, articleInput.toArray(new String[articleInput.size()]));
        articleInput.clear();
    }
}
