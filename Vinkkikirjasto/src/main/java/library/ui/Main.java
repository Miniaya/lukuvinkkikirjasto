package library.ui;

import library.domain.LibraryService;

import java.util.Scanner;
import library.dao.SQLLibraryDao;

public class Main {
    
    private LibraryService service;
    
    public static void main(String[] args) {
        // pääohjelma, tämä käyttää vain LibraryServicen metodeja!
        
        Scanner scanner = new Scanner(System.in, "Cp850");
        CLUI ui = new CLUI(scanner, new SQLLibraryDao("jdbc:sqlite:vinkit.db", "vinkit.db"));
        ui.init();
    }
}