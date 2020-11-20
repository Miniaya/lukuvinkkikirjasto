package library.ui;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import library.dao.LibraryDao;
import library.dao.SQLLibraryDao;
import library.domain.Suggestion;

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

    public CLUI(Scanner scanner, LibraryDao dao) {
        this.scanner = scanner;
        database = dao;
    }

    public void init() {

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
                    add();
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

    private void add() {
        String[] details = new String[]{"nimi", "kirjoittaja", "sivumäärä"};
        ArrayList<String> input = new ArrayList<>();

        for (String detail : details) {
            out.println("Anna kirjan " + detail + ":");
            input.add(scanner.nextLine());
            out.println("");
        }

        // lisää arraylist tietokantaan?
        Suggestion book = new Suggestion("Book");
        book.addDetails(details, input.toArray(new String[input.size()]));
    }
}
