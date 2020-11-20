package library.ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import library.dao.LibraryDao;
import library.dao.SQLLibraryDao;
import library.domain.Suggestion;
import library.io.*;

/**
 * Komentoriviä käyttävä UI. Ottaa Scanner-olion konstruktorin parametrina.
 */
public class CLUI {

    private IO io;
    private Scanner scanner;
    static LibraryDao database;
    private ArrayList<String> commands = new ArrayList<>();

    public CLUI(IO io, Scanner scanner, LibraryDao dao) {
        this.io = io;
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
        io.print("##############\n"
                + "# Lukuvinkit #\n"
                + "##############\n");
        listCommands();
        // Kysyy ja toteuttaa komentoja kunnes saadaan komento "sulje"
        loop:
        while (true) {
            io.print("\nSyötä komento: ");
            String command = scanner.nextLine();
            io.print("");
            switch (command) {
                case "uusi":
                    add();
                    break;
                case "sulje":
                    io.print("Suljetaan Lukuvinkit");
                    break loop;
                case "help":
                    listCommands();
                    break;
                default:
                    io.print("Tuntematon komento. Komento \"help\""
                            + " näyttää sallitut komennot.");
            }
        }
    }

    private void listCommands() {
        io.print("Komennot:");
        for (String s : commands) {
            System.out.println(s);
        }
    }

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
}
