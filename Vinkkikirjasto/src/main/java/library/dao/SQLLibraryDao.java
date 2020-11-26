package library.dao;

import java.sql.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import library.domain.Book;
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
    
    public void createDatabase() {
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
                    + "pages VARCHAR, "
                    + "time_of_adding DATE,"
                    + "time_of_modifying DATE)");
            s.execute("DROP TABLE IF EXISTS Article;");
            s.execute("CREATE TABLE Article (id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "title TEXT, "
                    + "url TEXT, "
                    + "time_of_adding DATE,"
                    + "time_of_modifying DATE)");

            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public boolean add(Suggestion suggestion) {
        
        if (suggestion.getType().equals("Book")) {
            return addBook(suggestion);
        } else if (suggestion.getType().equals("Article")) {
            return addArticle(suggestion);
        }
        return false;
    }
    
    private boolean addBook(Suggestion suggestion) {
        
        try {
            Connection conn = connect();
            Statement s = conn.createStatement();
        
            String title = suggestion.getDetail("nimi");
            String author = suggestion.getDetail("kirjoittaja");
            String pages = suggestion.getDetail("sivumäärä");
            if (pages.equals("")) {
                pages = "-1";
            }
        
            s.execute("BEGIN TRANSACTION");
        
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
            
            s.execute("COMMIT");
            conn.close();
                    
            return true;
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        
    }
    
    private boolean addArticle(Suggestion suggestion) {
        
        try {
            Connection conn = connect();
            Statement s = conn.createStatement();
        
            String title = suggestion.getDetail("nimi");
            String url = suggestion.getDetail("url");
        
            s.execute("BEGIN TRANSACTION");
            
            PreparedStatement p = conn.prepareStatement("INSERT INTO Article (title, url, time_of_adding, time_of_modifying) "
                    + "VALUES (?, ?, datetime('now'), datetime('now'))");
            p.setString(1, title);
            p.setString(2, url);

            p.executeUpdate();
            
            s.execute("COMMIT");
            conn.close();
                    
            return true;
            
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        
    }
    
    @Override
    public List<Book> getBooks() {
        try {
            Connection conn = connect();
            
            PreparedStatement p = conn.prepareStatement("SELECT * From Book LEFT JOIN Author WHERE Book.author_id=Author.id");
            ResultSet r = p.executeQuery();
            
            List<Book> books = null;
            
            while (r.next()) {
                books = new ArrayList<>();
                Book book = new Book(r.getString("title"), r.getString("name"), r.getInt("pages"));
                books.add(book);
            }
            r.close();
            conn.close();
            
            return books;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return null;
    }
}
