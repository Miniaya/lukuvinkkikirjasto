package library.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import library.dao.LibraryDao;
import library.dao.SQLLibraryDao;
import library.io.*;
import library.domain.Book;
import library.domain.Suggestion;
import library.domain.LibraryService;

/**
 * Komentoriviä käyttävä UI. Ottaa Scanner-olion konstruktorin parametrina.
 */
public class CLUI {
   
    private IO io;
    private ArrayList<String> commands = new ArrayList<>();
    private LibraryService service;

    public CLUI(IO io) throws FileNotFoundException, IOException {
        
        Properties properties = new Properties();
        
        properties.load(new FileInputStream("config.properties"));
        
        String libraryDB = properties.getProperty("libraryDB");
        
        LibraryDao libraryDBname = new SQLLibraryDao(libraryDB);
        
        service = new LibraryService(libraryDBname);
        this.io = io;
    }
    
    public CLUI(IO io, LibraryService s) {
        this.io = io;
        this.service = s;
    }

    public void init() {

        // Lisätään toteutetut komennot commands-listaan.
        commands.add("uusi - lisää uuden vinkin");
        commands.add("listaa - listaa vinkit");
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
            String command = io.readLine("\nSyötä komento: ");
            io.print("");

            switch (command) {
                case "uusi":
                    add();
                    break;
                case "listaa":
                    service.listSuggestions();
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
            io.print(s);
        }
    }

    private void add() {
        String[] details = new String[]{"nimi", "kirjoittaja", "sivumäärä"};
        ArrayList<String> input = new ArrayList<>();

        for (String detail : details) {
            input.add(io.readLine("Anna kirjan " + detail + ": "));
            io.print("");
        }

        boolean success = service.add(details, input.toArray(new String[input.size()]));
        
        if (success){
            io.print("Vinkki lisätty");
        } else {
            io.print("Vinkin lisäys epäonnistui");
        }
    }
}
