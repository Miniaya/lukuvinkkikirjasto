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
    private String RED = "\033[0;31m";
    private String GREEN = "\033[0;32m";
    private String YELLOW = "\033[0;33m";
    private String RESET = "\033[0m";
    
    @Before
    public void setUp() {
        this.testBook1 = new Book("TestName", "TestAuthor", 100, 0, "TestTag");
        this.testBook2 = new Book("TestName2", "TestAuthor2", 200, 100, "TestTag2");
    }
    
    @Test
    public void toStringMethodReturnsCorrectDetails() {
        String percent1 = RED + "0.0%" + RESET;
        String percent2 = GREEN + "100.0%" + RESET;
        assertEquals("Tyyppi: Kirja" + "\nNimi: TestName" + 
                "\nKirjoittaja: TestAuthor" + "\nSivumäärä: 100" + 
                "\nLuettu: " + percent1 + "\nTagit: TestTag", testBook1.toString());
        assertEquals("Tyyppi: Kirja" + "\nNimi: TestName2" + 
                "\nKirjoittaja: TestAuthor2" + "\nSivumäärä: 200" + 
                "\nLuettu: " + percent2 + "\nTagit: TestTag2", testBook2.toString());
    }
    
    @Test
    public void updatedBookDetailsAreShownInListing() {
        testBook1.setRead(50);
        String updatedPercent = YELLOW + "50.0%" + RESET;
        assertEquals("Tyyppi: Kirja" + "\nNimi: TestName" + 
                "\nKirjoittaja: TestAuthor" + "\nSivumäärä: 100" + 
                "\nLuettu: " + updatedPercent + "\nTagit: TestTag", testBook1.toString());
    }
    

}
