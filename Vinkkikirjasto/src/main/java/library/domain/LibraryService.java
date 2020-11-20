package library.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
    
    public void listSuggestions() {
        List<Book> books = libraryDao.getBooks();
        if (books == null) {
            System.out.println("No suggestions were added yet.");
        } else {
            for (Book book: books) {
                System.out.println(book.toString());
            }
        }
    }
}