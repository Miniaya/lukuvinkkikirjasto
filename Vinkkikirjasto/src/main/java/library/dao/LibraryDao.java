package library.dao;

import java.sql.Connection;

public interface LibraryDao {

    // Talletuksen rajapinta, nämä metodit pitää @Override
    Connection connect() throws Exception;

    void createDatabase();
}
