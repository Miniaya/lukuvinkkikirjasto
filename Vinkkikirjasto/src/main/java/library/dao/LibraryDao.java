package library.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface LibraryDao {

    // Talletuksen rajapinta, nämä metodit pitää @Override
    
    void addBook(String[] values) throws SQLException;
}
