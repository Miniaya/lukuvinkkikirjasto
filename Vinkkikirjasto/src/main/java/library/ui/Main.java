package library.ui;

import library.domain.LibraryService;
import java.util.Scanner;
<<<<<<< HEAD
import java.io.PrintWriter;
=======
import library.dao.SQLLibraryDao;
>>>>>>> d6cefcce677fe7399a1aa8bb897018df55f4ef0f

public class Main {
    
    private LibraryService service;
    
    public static Scanner in;
    public static PrintWriter out;
    
    // <ÄÄKKÖSKORJAUS>
    // https://www.ohjelmointiputka.net/koodivinkit/26866-java-%C3%A4%C3%A4kk%C3%B6set-windowsin-komentorivill%C3%A4
    // Tässä lohkossa alustetaan edelliset muuttujat.
    static {
	try {
            // Yritetään hakea tässä vinkissä esitelty olio.
            in = new Scanner(System.console().reader());
	} catch (NullPointerException e) {
            // Virhetilanteessa käytetään tavallista tapaa.
            in = new Scanner(System.in);
	}
    }
    // </ÄÄKKÖSKORJAUS> 
    
    
    public static void main(String[] args) {
        // pääohjelma, tämä käyttää vain LibraryServicen metodeja!
        
<<<<<<< HEAD
//        Scanner scanner = new Scanner(System.in, "Cp850");
        CLUI ui = new CLUI(in);
=======
        Scanner scanner = new Scanner(System.in, "Cp850");
        CLUI ui = new CLUI(scanner, new SQLLibraryDao("jdbc:sqlite:vinkit.db", "vinkit.db"));
>>>>>>> d6cefcce677fe7399a1aa8bb897018df55f4ef0f
        ui.init();
    }
}