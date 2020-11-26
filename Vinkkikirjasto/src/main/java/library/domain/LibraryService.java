package library.domain;

import java.util.List;
import library.dao.LibraryDao;

public class LibraryService {
    
    // Tänne sovelluslogiikkaan liittyvät metodit, esim tietokannan käyttö
    
    private LibraryDao libraryDao;
    
    public LibraryService(LibraryDao dao) {
        this.libraryDao = dao;
    }
    
    public boolean add(String suggestionType, String[] detailTypes, String[] detailValues) {
        boolean success = false;
        if (suggestionType.equals("book")) {
            Suggestion book = new Book();
            book.addDetails(detailTypes, detailValues);
        
            success = libraryDao.add(book);
        } else if (suggestionType.equals("article")) {
            Suggestion article = new Article();
            article.addDetails(detailTypes, detailValues);
        
            success = libraryDao.add(article);
        }
        
        return success;
    }
    
    public List<Book> listSuggestions() {
        List<Book> books = libraryDao.getBooks();
        return books;
    }
}