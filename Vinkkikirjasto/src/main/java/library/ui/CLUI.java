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
import library.domain.Article;
import library.io.*;
import library.domain.Book;
import library.domain.LibraryService;
import library.domain.Suggestion;

/**
 * Komentoriviä käyttävä UI. Ottaa IO:n ja LibraryServicen konstruktorin parametrina.
 * Tulostukset ja syötteet toteuteaan IO-oliolla.
 * LibraryService vastaa sovelluslogiikasta.
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
                    listSuggestions();
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

        while (true) {
            String suggestionType = "";
            String in = io.readLine("Lisää kirja komennolla kirja tai artikkeli komennolla artikkeli.");
            if (in.equals("kirja")) {
                addBook();
                break;
            } else if (in.equals("artikkeli")) {
                addArticle();
                break;
            } else {
                io.print("Tuntematon komento.");
                break;
            }
        }
    }
        
    private void addBook() {
                             
        String[] details = new String[]{"nimi", "kirjoittaja", "sivumäärä"};
        ArrayList<String> input = new ArrayList<>();

//        for (String detail : details) {
//            input.add(io.readLine("Anna kirjan " + detail + ": "));
//            io.print("");
//        }
        
        boolean correctType;
        for (int i = 0; i < details.length; i++) {
            correctType = false;
            while (!correctType) {
                String in = io.readLine("Anna kirjan " + details[i] + ": ");
                io.print("");
                correctType = checkType(in, i);
                if (correctType) {
                    input.add(in);
                } else {
                    io.print("Syötä " + details[i] + " uudelleen. Varmista, että nimessä ja kirjoittajassa ei numeroita, sekä sivumäärässä kirjaimia ");
                }
            }
        }

        boolean success = service.add("book", details, input.toArray(new String[input.size()]));

        if (success) {
            io.print("Vinkki lisätty");
            io.print("");
        } else {
            io.print("Vinkin lisäys epäonnistui");
        }
    }
    
    private void addArticle() {
                             
        String[] details = new String[]{"nimi", "url"};
        ArrayList<String> input = new ArrayList<>();

//        for (String detail : details) {
//            input.add(io.readLine("Anna kirjan " + detail + ": "));
//            io.print("");
//        }
        
        boolean correctType;
        for (int i = 0; i < details.length; i++) {
            correctType = false;
            while (!correctType) {
                String in = io.readLine("Anna artikkelin " + details[i] + ": ");
                io.print("");
                correctType = checkType(in, i);
                if (correctType) {
                    input.add(in);
                } else {
                    io.print("Syötä " + details[i] + " uudelleen.");
                }
            }
        }

        boolean success = service.add("article", details, input.toArray(new String[input.size()]));

        if (success) {
            io.print("Vinkki lisätty");
            io.print("");
        } else {
            io.print("Vinkin lisäys epäonnistui");
        }
    }

    private boolean checkType(String input, int index) {
        switch (index) {
            //atm ainoastaan sivumäärä haluaa numeroita, helppo lisätä myöhemmin jos tulee muita
            case 2:
                return input.matches("[0-9]+");
            default:
                return true;
        }
    }

    private void listSuggestions() {
        List<Book> books = service.listBooks();
        List<Article> articles = service.listArticles();
        if (books == null && articles == null) {
            io.print("Vinkkikirjastossa ei ole vielä vinkkejä.");
        } else {
            for (Book book: books) {
                io.print(book.toString() + "\n");
            }
            for (Article article: articles) {
                io.print(article.toString() + "\n");
            }
        }
    }
}
