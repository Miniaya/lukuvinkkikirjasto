package library.ui;

import java.util.Scanner;
import java.util.ArrayList;
/**
 * Komentoriviä käyttävä UI. Ottaa Scanner-olion konstruktorin parametrina.
 */
public class CLUI {
    private Scanner scanner;
    private ArrayList<String> commands = new ArrayList<>();
    
    public CLUI(Scanner scanner) {
        this.scanner = scanner;
        // Lisätään toteutetut komennot commands-listaan.
        commands.add("uusi - lisää uuden vinkin");
        commands.add("sulje - sulkee ohjelman");
        commands.add("help - tulostaa komennot");
    }
    
    public void init() {        
        System.out.println("##############\n"
                         + "# Lukuvinkit #\n"
                         + "##############\n");
        listCommands();
        // Kysyy ja toteuttaa komentoja kunnes saadaan komento "sulje"
        loop: while (true) {
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
                            + "näyttää sallitut komennot.");
            }
        }
    }
    
    private void listCommands() {
        System.out.println("Komennot:");
        for (String s: commands) {
            System.out.println(s);
        }
    }
    
    private void add() {
        //TODO
        System.out.println("TODO");
    }
}
