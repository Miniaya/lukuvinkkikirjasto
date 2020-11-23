package library.dao;

import library.domain.Suggestion;
import java.util.List;
import library.domain.Book;

public interface LibraryDao {
    
    // Returns true, jos lisäys onnistui, muuten false
    public boolean add(Suggestion suggestion);
    
    List<Book> getBooks();
}
