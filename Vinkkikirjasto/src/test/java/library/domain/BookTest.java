/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author saasini
 */
public class BookTest {
    
    private Book testBook1;
    private Book testBook2;
    private String RED = "\u001b[38;5;210m";     // RED
    private String GREEN = "\u001b[38;5;157m";   // GREEN
    private String YELLOW = "\u001b[38;5;229m";  // YELLOW
    private String RESET = "\u001b[0m";  // RESET
    
    @Before
    public void setUp() {
        this.testBook1 = new Book("TestName", "TestAuthor", 100, 0, "TestTag");
        this.testBook2 = new Book("TestName2", "TestAuthor2", 200, 100, "TestTag2");
    }
    
    @Test
    public void toStringMethodReturnsCorrectDetails() {
        String percent1 = RED + "0.0%" + RESET;
        String percent2 = GREEN + "100.0%" + RESET;
        assertEquals("\u001b[38;5;115mKIRJA \u001b[0m" 
        + "\nNimi       | TestName"
        + "\nKirjoittaja| TestAuthor" 
        + "\nSivumäärä  | 100" 
        + "\nLuettu     | " + percent1 
        + "\nTagit      | TestTag"
        + "\n-------------", testBook1.toString());
        assertEquals("\u001b[38;5;115mKIRJA \u001b[0m" 
        + "\nNimi       | TestName2"
        + "\nKirjoittaja| TestAuthor2" 
        + "\nSivumäärä  | 200" 
        + "\nLuettu     | " + percent2 
        + "\nTagit      | TestTag2"
        + "\n-------------", testBook2.toString());
    }
    
    @Test
    public void updatedBookDetailsAreShownInListing() {
        testBook1.setRead(50);
        String updatedPercent = YELLOW + "50.0%" + RESET;
        assertEquals("\u001b[38;5;115mKIRJA \u001b[0m" 
        + "\nNimi       | TestName"
        + "\nKirjoittaja| TestAuthor" 
        + "\nSivumäärä  | 100" 
        + "\nLuettu     | " + updatedPercent 
        + "\nTagit      | TestTag"
        + "\n-------------", testBook1.toString());
    }
    

}
