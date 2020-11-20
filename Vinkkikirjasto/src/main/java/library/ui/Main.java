package library.ui;

import java.io.IOException;
import java.sql.SQLException;
import library.domain.LibraryService;
import java.util.Scanner;
import java.io.PrintWriter;

public class Main {
    
    private LibraryService service;
    
    public static void main(String[] args) throws IOException, SQLException {
        // pääohjelma, tämä käyttää vain LibraryServicen metodeja!
        
        Scanner scanner = new Scanner(System.in, "Cp850");
        CLUI ui = new CLUI(scanner);
        
        ui.init();

//    public static Scanner in;
//    public static PrintWriter out;
//    
//    // <ÄÄKKÖSKORJAUS>
//    // https://www.ohjelmointiputka.net/koodivinkit/26866-java-%C3%A4%C3%A4kk%C3%B6set-windowsin-komentorivill%C3%A4
//    // Tässä lohkossa alustetaan edelliset muuttujat.
//    static {
//	try {
//            // Yritetään hakea tässä vinkissä esitelty olio.
//            in = new Scanner(System.console().reader());
//	} catch (NullPointerException e) {
//            // Virhetilanteessa käytetään tavallista tapaa.
//            in = new Scanner(System.in, "Cp850");
//	}
//    }
    // </ÄÄKKÖSKORJAUS> 
    
    }
}