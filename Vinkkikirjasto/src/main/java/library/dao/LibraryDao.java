package library.dao;

import java.sql.SQLException;
import library.domain.Suggestion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import library.domain.Book;

public interface LibraryDao {
    
    // Returns true, jos lisäys onnistui, muuten false
    public boolean add(Suggestion suggestion);
    
    List<Book> getBooks();
}
