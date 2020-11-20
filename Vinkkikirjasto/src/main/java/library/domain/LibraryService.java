package library.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import library.dao.LibraryDao;

public class LibraryService {
    
    // Tänne sovelluslogiikkaan liittyvät metodit, esim tietokannan käyttö
    
    private LibraryDao libraryDao;
    
    public LibraryService(LibraryDao dao) {
        this.libraryDao = dao;
    }
    
    public void add(Scanner scanner) throws SQLException {
        String[] details = new String[]{"nimi", "kirjoittaja", "sivumäärä"};
        ArrayList<String> input = new ArrayList<>();

        for (String detail : details) {
            System.out.print("Anna kirjan " + detail + ":");
            input.add(scanner.nextLine());
            System.out.println("");
        }

        // lisää arraylist tietokantaan?
        Suggestion book = new Suggestion("Book");
        book.addDetails(details, input.toArray(new String[input.size()]));
        
        libraryDao.addBook(book.getDetailValues());
    }
}