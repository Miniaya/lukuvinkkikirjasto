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
        bookInput.add("TestBook");
        bookInput.add("TestAuthor");
        bookInput.add("100");
        bookInput.add("tag1, tag2");
        service.add("book", bookDetails, bookInput.toArray(new String[bookInput.size()]));
        List<Book> books = dao.getBooks();
        assertEquals("tag1 tag2", books.get(0).getDetail("tagit"));
    }

    @Test
    public void noDuplicateTags() {
        bookInput.add("TestBook");
        bookInput.add("TestAuthor");
        bookInput.add("100");
        bookInput.add("tag1, tag1");
        service.add("book", bookDetails, bookInput.toArray(new String[bookInput.size()]));
        List<Book> books = dao.getBooks();
        assertEquals("tag1", books.get(0).getDetail("tagit"));
    }

    @Test
    public void noDuplicateTagsWithDifferentCapitalization() {
        bookInput.add("TestBook");
        bookInput.add("TestAuthor");
        bookInput.add("100");
        bookInput.add("tag1, TAG1");
        service.add("book", bookDetails, bookInput.toArray(new String[bookInput.size()]));
        List<Book> books = dao.getBooks();
        assertEquals("tag1", books.get(0).getDetail("tagit"));
    }
}
