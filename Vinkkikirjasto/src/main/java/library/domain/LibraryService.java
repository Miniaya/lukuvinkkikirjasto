package library.domain;

import library.dao.LibraryDao;

public class LibraryService {
    
    // Tänne sovelluslogiikkaan liittyvät metodit, esim tietokannan käyttö
    
    private LibraryDao libraryDao;
    
    public LibraryService(LibraryDao dao) {
        this.libraryDao = dao;
    }
}