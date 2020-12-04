package library.domain;

import java.util.ArrayList;
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
}
