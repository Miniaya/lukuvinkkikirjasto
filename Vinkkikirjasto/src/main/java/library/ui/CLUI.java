package library.ui;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import library.domain.Suggestion;

/**
 * Komentoriviä käyttävä UI. Ottaa Scanner-olion konstruktorin parametrina.
 */
public class CLUI {

    private Scanner scanner;
    private ArrayList<String> commands = new ArrayList<>();

    public CLUI(Scanner scanner) {
        this.scanner = scanner;
    }

    public void init() {
        // Lisätään toteutetut komennot commands-listaan.
        commands.add("uusi - lisää uuden vinkin");
        commands.add("sulje - sulkee ohjelman");
        commands.add("help - tulostaa komennot");
        // Laitetaan komennot aakkosjärjestykseen
        Collections.sort(commands);
        System.out.println("##############\n"
                + "# Lukuvinkit #\n"
                + "##############\n");
        listCommands();
        // Kysyy ja toteuttaa komentoja kunnes saadaan komento "sulje"
        loop:
        while (true) {
            System.out.println("\nSyötä komento: ");
            String command = scanner.nextLine();
            System.out.println("");
            switch (command) {
                case "uusi":
                    add();
                    break;
                case "sulje":
                    System.out.println("Suljetaan Lukuvinkit");
                    break loop;
                case "help":
                    listCommands();
                    break;
                default:
                    System.out.println("Tuntematon komento. Komento \"help\""
                            + " näyttää sallitut komennot.");
            }
        }
    }

    private void listCommands() {
        System.out.println("Komennot:");
        for (String s : commands) {
            System.out.println(s);
        }
    }

    private void add() {
        String[] details = new String[]{"nimi", "kirjoittaja", "sivumäärä"};
        ArrayList<String> input = new ArrayList<>();

        for (String detail : details) {
            System.out.print("Anna kirjan " + detail + ":");
            input.add(scanner.nextLine());
            System.out.println("");
        }
                
        // lisää arraylist tietokantaan?
        Suggestion book = new Suggestion("Book");
        book.addDetails(details, input.toArray(new String[input.size()]));
    }
}
