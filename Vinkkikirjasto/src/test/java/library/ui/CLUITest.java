package library.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import library.dao.InMemoryLibraryDao;
import library.dao.LibraryDao;
import library.domain.*;
import library.io.IO;
import library.io.StubIO;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class CLUITest {
    
    private StubIO io;
    private LibraryDao dao;
    private LibraryService service;
    private CLUI ui;
    private List<String> input;
    
    @Before
    public void setUp() {
        io = new StubIO();
        service = new LibraryService(new InMemoryLibraryDao());
        ui = new CLUI(io, service);
        input = new ArrayList<>();
    }
    
    @Test
    public void addingBookWithValidInformationWorks() {
        this.inputBookKotkanpesa();
        input.add("sulje");
        
        this.runClui();
        assertTrue(io.getPrints().contains("Vinkki lisätty"));
    }
    
    @Test
    public void addingBookWithInvalidPageNumberDoesntWork() {
        input.add("uusi");
        input.add("kirja");
        input.add("Vahtisokeat");
        input.add("Tuuli Rannikko");
        input.add("ASO");
        input.add("530");
        input.add("sulje");
        
        this.runClui();
        assertTrue(io.getPrints().contains("Syötä sivumäärä uudelleen. Varmista, että nimessä ja kirjoittajassa ei numeroita, sekä sivumäärässä kirjaimia "));
    }
    
    @Test
    public void addingBookWithNegativePageNumberDoesntWork() {
        input.add("uusi");
        input.add("kirja");
        input.add("Pikkuveli");
        input.add("PMMP");
        input.add("-50");
        input.add("50");
        input.add("sulje");
        
        this.runClui();
        assertTrue(io.getPrints().contains("Syötä sivumäärä uudelleen. Varmista, että nimessä ja kirjoittajassa ei numeroita, sekä sivumäärässä kirjaimia "));
    }
    
    @Test
    public void addedBookCanBeListed() {
        input.add("uusi");
        input.add("kirja");
        input.add("Hulabaloo");
        input.add("Piraatit");
        input.add("250");
        input.add("listaa");
        input.add("sulje");
        Book b = new Book("Hulabaloo", "Piraatit", 250, 0.0);
        this.runClui();
        assertTrue(io.getPrints().contains(b.toString() + "\n"));
    }
    
    @Test
    public void printsCorrectTextWhenLibraryIsEmpty() {
        input.add("listaa");
        input.add("sulje");
        
        this.runClui();
        assertTrue(io.getPrints().contains("Vinkkikirjastossa ei ole vielä vinkkejä."));
    }
    
    @Test
    public void listCommandWorks() {
        input.add("help");
        input.add("sulje");
        
        this.runClui();
        assertFalse(io.getPrints().indexOf("Komennot:") == io.getPrints().lastIndexOf("Komennot:"));
    }
    
    @Test
    public void invalidCommandCausesErrorMessage() {
        input.add("ussi");
        input.add("sulje");
        
        this.runClui();
        assertTrue(io.getPrints().contains("Tuntematon komento. Komento \"help\" näyttää sallitut komennot."));
    }
    
    @Test
    public void deletingBookWorks() {
        this.inputBookKotkanpesa();
        input.add("poista");
        input.add("kirja");
        input.add("Kotkanpesä");
        input.add("sulje");
        
        this.runClui();
        assertTrue(io.getPrints().contains("Vinkki lisätty"));
        assertTrue(io.getPrints().contains("Kirja Kotkanpesä poistettu vinkkikirjastosta."));
        
    }
    
    @Test
    public void deletingArticleWorks() {
        this.inputArticleOhtu();
        input.add("poista");
        input.add("artikkeli");
        input.add("Ohtumateriaali");
        input.add("sulje");
        
        this.runClui();
        assertTrue(io.getPrints().contains("Vinkki lisätty"));
        assertTrue(io.getPrints().contains("Artikkeli Ohtumateriaali poistettu vinkkikirjastosta."));
    }
    
    @Test
    public void deletingNonexistentDoesntDeleteAnything() {
        this.inputArticleOhtu();
        this.inputBookKotkanpesa();
        input.add("poista");
        input.add("kirja");
        input.add("Sanakirja");
        input.add("e");
        input.add("sulje");
        
        this.runClui();
        assertTrue(io.getPrints().contains("Virhe. Tarkista, että kirjoitit nimen oikein.")); 
    }
    
    private void inputBookKotkanpesa() {
        input.add("uusi");
        input.add("kirja");
        input.add("Kotkanpesä");
        input.add("Ilkka Remes");
        input.add("450");
    }
    
    private void inputArticleOhtu() {
        input.add("uusi");
        input.add("artikkeli");
        input.add("Ohtumateriaali");
        input.add("https://ohjelmistotuotanto-hy.github.io/");
    }
    
    private void runClui() {
        io.setInputs(input);
        ui.init();
    }
}
