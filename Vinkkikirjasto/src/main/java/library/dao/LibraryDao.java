package library.dao;

import java.sql.SQLException;
import library.domain.Suggestion;

public interface LibraryDao {
    
    // Returns true, jos lisäys onnistui, muuten false
    public boolean add(Suggestion suggestion);
  

}
