package library.ui;

import java.io.IOException;
import java.sql.SQLException;
import library.domain.LibraryService;

import java.util.Scanner;

public class Main {
    
    private LibraryService service;
    
    public static void main(String[] args) throws IOException, SQLException {
        // pääohjelma, tämä käyttää vain LibraryServicen metodeja!
        
        Scanner scanner = new Scanner(System.in, "Cp850");
        CLUI ui = new CLUI(scanner);
        ui.init();
    }
}