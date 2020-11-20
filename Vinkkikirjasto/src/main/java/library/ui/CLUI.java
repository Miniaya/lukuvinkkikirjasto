package library.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import library.dao.LibraryDao;
import library.dao.SQLLibraryDao;
import library.domain.Suggestion;
import library.io.*;
import library.domain.LibraryService;

/**
 * Komentoriviä käyttävä UI. Ottaa Scanner-olion konstruktorin parametrina.
 */
public class CLUI {
    
    public static PrintWriter out;
    
    // <ÄÄKKÖSKORJAUS>
    // https://www.ohjelmointiputka.net/koodivinkit/26866-java-%C3%A4%C3%A4kk%C3%B6set-windowsin-komentorivill%C3%A4
    // Tässä lohkossa alustetaan edelliset muuttujat.
    static {
	// Vastaava tulostuspuolelle.
	try {
            out = System.console().writer();
	} catch (NullPointerException e) {
            out = new PrintWriter(System.out, true);
            }
	}
    // </ÄÄKKÖSKORJAUS> 
    

    private IO io;
    private Scanner scanner;
    static LibraryDao database;
    private ArrayList<String> commands = new ArrayList<>();
    private LibraryService service;

<<<<<<< HEAD
    public CLUI(IO io, Scanner scanner, LibraryDao dao) {
        this.io = io;
=======
    public CLUI(Scanner scanner) throws FileNotFoundException, IOException {
        
        Properties properties = new Properties();
        
        properties.load(new FileInputStream("config.properties"));
        
        String libraryDB = properties.getProperty("libraryDB");
        
        LibraryDao libraryDBname = new SQLLibraryDao(libraryDB);
        
>>>>>>> 1c8e3f4df5955a1d487eabcc9c572e4e405335f9
        this.scanner = scanner;
        service = new LibraryService(libraryDBname);
    }

    public void init() throws SQLException {

        // Lisätään toteutetut komennot commands-listaan.
        commands.add("uusi - lisää uuden vinkin");
        commands.add("sulje - sulkee ohjelman");
        commands.add("help - tulostaa komennot");
        // Laitetaan komennot aakkosjärjestykseen
        Collections.sort(commands);
<<<<<<< HEAD
        io.print("##############\n"
=======
        out.println("##############\n"
>>>>>>> 1c8e3f4df5955a1d487eabcc9c572e4e405335f9
                + "# Lukuvinkit #\n"
                + "##############\n");
        listCommands();
        // Kysyy ja toteuttaa komentoja kunnes saadaan komento "sulje"
        loop:
        while (true) {
<<<<<<< HEAD
            io.print("\nSyötä komento: ");
            String command = scanner.nextLine();
            io.print("");
=======
            out.println("\nSyötä komento: ");
            String command = scanner.nextLine();
            out.println("");
>>>>>>> 1c8e3f4df5955a1d487eabcc9c572e4e405335f9
            switch (command) {
                case "uusi":
                    service.add(scanner);
                    break;
                case "sulje":
<<<<<<< HEAD
                    io.print("Suljetaan Lukuvinkit");
=======
                    out.println("Suljetaan Lukuvinkit");
>>>>>>> 1c8e3f4df5955a1d487eabcc9c572e4e405335f9
                    break loop;
                case "help":
                    listCommands();
                    break;
                default:
<<<<<<< HEAD
                    io.print("Tuntematon komento. Komento \"help\""
=======
                    out.println("Tuntematon komento. Komento \"help\""
>>>>>>> 1c8e3f4df5955a1d487eabcc9c572e4e405335f9
                            + " näyttää sallitut komennot.");
            }
        }
    }

    private void listCommands() {
<<<<<<< HEAD
        io.print("Komennot:");
=======
        out.println("Komennot:");
>>>>>>> 1c8e3f4df5955a1d487eabcc9c572e4e405335f9
        for (String s : commands) {
            out.println(s);
        }
    }

<<<<<<< HEAD
    private void add() {
        String[] details = new String[]{"nimi", "kirjoittaja", "sivumäärä"};
        ArrayList<String> input = new ArrayList<>();

        for (String detail : details) {
            io.print("Anna kirjan " + detail + ":");
            input.add(scanner.nextLine());
            io.print("");
        }

        // lisää arraylist tietokantaan?
        Suggestion book = new Suggestion("Book");
        book.addDetails(details, input.toArray(new String[input.size()]));
        
        if (database.add(book)){
            io.print("Vinkki lisätty");
        } else {
            io.print("Vinkin lisäys epäonnistui");
        }
    }
=======
>>>>>>> 1c8e3f4df5955a1d487eabcc9c572e4e405335f9
}
