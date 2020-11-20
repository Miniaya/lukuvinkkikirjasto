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
    

    private Scanner scanner;
    static LibraryDao database;
    private ArrayList<String> commands = new ArrayList<>();
    private LibraryService service;

    public CLUI(Scanner scanner) throws FileNotFoundException, IOException {
        
        Properties properties = new Properties();
        
        properties.load(new FileInputStream("config.properties"));
        
        String libraryDB = properties.getProperty("libraryDB");
        
        LibraryDao libraryDBname = new SQLLibraryDao(libraryDB);
        
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
        out.println("##############\n"
                + "# Lukuvinkit #\n"
                + "##############\n");
        listCommands();
        // Kysyy ja toteuttaa komentoja kunnes saadaan komento "sulje"
        loop:
        while (true) {
            out.println("\nSyötä komento: ");
            String command = scanner.nextLine();
            out.println("");
            switch (command) {
                case "uusi":
                    service.add(scanner);
                    break;
                case "sulje":
                    out.println("Suljetaan Lukuvinkit");
                    break loop;
                case "help":
                    listCommands();
                    break;
                default:
                    out.println("Tuntematon komento. Komento \"help\""
                            + " näyttää sallitut komennot.");
            }
        }
    }

    private void listCommands() {
        out.println("Komennot:");
        for (String s : commands) {
            out.println(s);
        }
    }

}
