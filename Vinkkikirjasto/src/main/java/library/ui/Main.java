package library.ui;

import java.io.IOException;
import library.domain.LibraryService;
import java.util.Scanner;
import library.io.*;

public class Main {
    
    private LibraryService service;
    
    public static void main(String[] args) throws IOException {
        // pääohjelma, tämä käyttää vain LibraryServicen metodeja!
        
        Scanner scanner = new Scanner(System.in, "Cp850");

        IO io = new ConsoleIO();
        CLUI ui = new CLUI(io);
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