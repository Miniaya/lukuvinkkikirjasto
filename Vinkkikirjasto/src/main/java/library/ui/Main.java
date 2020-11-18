package library.ui;

import library.domain.LibraryService;

import java.util.Scanner;

public class Main {
    
    private LibraryService service;
    
    public static void main(String[] args) {
        // pääohjelma, tämä käyttää vain LibraryServicen metodeja!
        
        Scanner scanner = new Scanner(System.in);
        CLUI ui = new CLUI(scanner);
        ui.init();
    }
}