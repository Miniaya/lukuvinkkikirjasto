package library.domain;

import java.util.ArrayList;
import library.dao.InMemoryLibraryDao;
import library.dao.LibraryDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryServiceTest {
    
    private LibraryDao dao;
    private LibraryService service;
    
    @Before
    public void setUp() {
        dao = new InMemoryLibraryDao();
        service = new LibraryService(dao);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void addingValidBookReturnsTrue() {
    }
    
}
