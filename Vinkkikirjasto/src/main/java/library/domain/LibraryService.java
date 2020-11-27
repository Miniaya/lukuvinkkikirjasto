package library.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
    
    public boolean remove(String name, String type) {
        return false;
    }
    
    public List<Book> listBooks() {
        return libraryDao.getBooks();
    }
    
    public List<Article> listArticles() {
        return libraryDao.getArticles();
    }
}