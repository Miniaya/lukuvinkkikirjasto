package library.dao;

import java.sql.*;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.sqlite.SQLiteConfig;
import library.domain.Suggestion;

public class SQLLibraryDao implements LibraryDao {
    // Tänne tietokantaan liittyvät kyselyt

    private static String jdbcStatement; // muotoa "jdbc:sqlite:<tietokantanimi>.db"
    private static String dbName; // <tietokantanimi>.db

    public SQLLibraryDao(String dbName) {
        this.jdbcStatement = "jdbc:sqlite:" + dbName;
        this.dbName = dbName;
        start();
    }

    private void start() {
        if (!new File(dbName).exists()) {
            createDatabase();
        }
        try {
            Connection conn = DriverManager.getConnection(jdbcStatement);
            conn.close();
        } catch (SQLException e) {
            createDatabase();
        }
    }


    private Connection connect() throws SQLException {
        Connection conn = null;
        //foreign keys päälle
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        conn = DriverManager.getConnection(jdbcStatement, config.toProperties());
        return conn;
    }


    private void createDatabase() {
        try {
            Connection conn = connect();
            Statement s = conn.createStatement();

            s.execute("PRAGMA foreign_keys = ON");
            s.execute("DROP TABLE IF EXISTS Author;");
            s.execute("CREATE TABLE Author (id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name TEXT NOT NULL)");

            s.execute("DROP TABLE IF EXISTS Book;");
            s.execute("CREATE TABLE Book (id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "title TEXT, "
                    + "author_id INTEGER REFERENCES Author, "
                    + "pages INTEGER, "
                    + "time_of_adding DATE,"
                    + "time_of_modifying DATE)");

            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public boolean add(Suggestion suggestion) {

        
        Connection conn = connect();
        Statement s = conn.createStatement();
        
        String title = suggestion.getDetail("nimi");
        String author = suggestion.getDetail("kirjoittaja");
        String pages = suggestion.getDetail("sivumäärä");
        
        s.execute("BEGIN TRANSACTION");
        
        try {
            PreparedStatement p = conn.prepareStatement("SELECT id FROM Author WHERE name = ?");
         
            p.setString(1, author);
            
            ResultSet r = p.executeQuery();
            
            if(!r.next()){
                
                p = conn.prepareStatement("INSERT INTO Author (name) VALUES (?)");
                p.setString(1, author);
                
                p.executeUpdate();
            } 
            
            p = conn.prepareStatement("INSERT INTO Book (title, author_id, pages, time_of_adding, time_of_modifying) "
                    + "VALUES (?, (SELECT id FROM Author WHERE name = ?), ?, datetime('now'), datetime('now'))");
            p.setString(1, title);
            p.setString(2, author);
            p.setInt(3, Integer.valueOf(pages));
                    
            p.executeUpdate();
                    
            return true;
            
        } catch (SQLException e){
            System.out.println("VIRHE: Kirjan lisääminen epäonnistui.");
            System.out.println(e.getMessage());
            return false;
        }
        
        s.execute("COMMIT");
        conn.close();
    }
}
