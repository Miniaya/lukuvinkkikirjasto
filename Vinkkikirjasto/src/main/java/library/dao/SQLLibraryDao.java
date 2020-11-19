package library.dao;

import java.sql.*;
import java.io.File;
import org.sqlite.SQLiteConfig;

public class SQLLibraryDao implements LibraryDao {
    // Tänne tietokantaan liittyvät kyselyt

    private static String jdbcStatement; // muotoa "jdbc:sqlite:<tietokantanimi>.db
    private static String dbName; // <tietokantanimi>.db

    public SQLLibraryDao(String url, String dbName) {
        this.jdbcStatement = url;
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

    @Override
    public Connection connect() throws Exception {
        Connection conn = null;
        try {
            //foreign keys päälle
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);

            conn = DriverManager.getConnection(jdbcStatement, config.toProperties());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    @Override
    public void createDatabase() {
        try {
            Connection conn = connect();

            conn.prepareStatement("DROP TABLE IF EXISTS Lukuvinkki;").executeUpdate();
            conn.prepareStatement("CREATE TABLE Lukuvinkki (id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nimi TEXT NOT NULL,"
                    + "tyyppi TEXT CHECK(Type IN ('kirja')) NOT NULL);").executeUpdate();

            conn.prepareStatement("DROP TABLE IF EXISTS Kirja;").executeUpdate();
            conn.prepareStatement("CREATE TABLE Kirja (Kirja_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "title TEXT, "
                    + "kirjoittaja TEXT, "
                    + "sivumaara INTEGER"
                    + ");").executeUpdate();

            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
