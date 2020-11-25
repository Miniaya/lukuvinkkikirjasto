package library.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import library.dao.InMemoryLibraryDao;
import library.dao.LibraryDao;
import library.domain.LibraryService;
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
        input.add("uusi");
        input.add("Kotkanpesä");
        input.add("Ilkka Remes");
        input.add("450");
        input.add("sulje");
        
        io.setInputs(input);
        
        ui.init();
        assertTrue(io.getPrints().contains("Vinkki lisätty"));
    }
    
    @Test
    public void addingBookWithInvalidPageNumberDoesntWork() {
        input.add("uusi");
        input.add("Vahtisokeat");
        input.add("Tuuli Rannikko");
        input.add("ASO");
        input.add("530");
        input.add("sulje");
        
        io.setInputs(input);
        
        ui.init();
        assertTrue(io.getPrints().contains("Syötä sivumäärä uudelleen. Varmista, että nimessä ja kirjoittajassa ei numeroita, sekä sivumäärässä kirjaimia "));
    }
    
    @Test
    public void addingBookWithNegativePageNumberDoesntWork() {
        input.add("uusi");
        input.add("Pikkuveli");
        input.add("PMMP");
        input.add("-50");
        input.add("50");
        input.add("sulje");
        
        io.setInputs(input);
        
        ui.init();
        assertTrue(io.getPrints().contains("Syötä sivumäärä uudelleen. Varmista, että nimessä ja kirjoittajassa ei numeroita, sekä sivumäärässä kirjaimia "));
    }
    
    @Test
    public void addedBookCanBeListed() {
        input.add("uusi");
        input.add("Hulabaloo");
        input.add("Piraatit");
        input.add("250");
        input.add("listaa");
        input.add("sulje");
        
        io.setInputs(input);
        
        ui.init();
        assertTrue(io.getPrints().contains("Nimi: Hulabaloo, kirjoittaja: Piraatit, sivumäärä: 250"));
    }
    
    @Test
    public void printsCorrectTextWhenLibraryIsEmpty() {
        input.add("listaa");
        input.add("sulje");
        
        io.setInputs(input);
        
        ui.init();
        assertTrue(io.getPrints().contains("Vinkkikirjastossa ei ole vielä vinkkejä."));
    }
    
    @Test
    public void listCommandWorks() {
        input.add("help");
        input.add("sulje");
        
        io.setInputs(input);
        
        ui.init();
        assertFalse(io.getPrints().indexOf("Komennot:") == io.getPrints().lastIndexOf("Komennot:"));
    }
    
    @Test
    public void invalidCommandCausesErrorMessage() {
        input.add("ussi");
        input.add("sulje");
        
        io.setInputs(input);
        
        ui.init();
        assertTrue(io.getPrints().contains("Tuntematon komento. Komento \"help\" näyttää sallitut komennot."));
    }
}
