package library.domain;

import java.util.List;
import library.dao.LibraryDao;

public class LibraryService {
    
    // Tänne sovelluslogiikkaan liittyvät metodit, esim tietokannan käyttö
    
    private LibraryDao libraryDao;
    
    public LibraryService(LibraryDao dao) {
        this.libraryDao = dao;
    }
    
    public boolean add(String[] detailTypes, String[] detailValues) {
        
        Suggestion book = new Suggestion("Book");
        book.addDetails(detailTypes, detailValues);
        
        boolean success = libraryDao.add(book);
        
        return success;
    }
    
    public List<Book> listSuggestions() {
        List<Book> books = libraryDao.getBooks();
        return books;
    }
}