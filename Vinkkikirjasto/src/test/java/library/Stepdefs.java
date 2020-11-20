
package library;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import library.dao.*;
import library.domain.*;
import library.ui.*;
import library.io.*;
import java.util.*;

public class Stepdefs {
    
    CLUI ui;
    LibraryService library;
    StubIO io;
    StringBuilder input;
    
    @Before
    public void setup() {
        library = new LibraryService(new InMemoryLibraryDao());
        input = new StringBuilder();
        io = new StubIO();
    }
    
    @Given("command uusi is selected")
    public void commandUusiIsSelected() {
        input.append("uusi\n");
    }
    
    @When("correct book information is given")
    public void correctBookInfoGive() {
        input.append("kirja\n");
        input.append("kirjoittaja\n");
        input.append("100\n");
    }
    
    @Then("book is saved to library")
    public void bookIsSaved() {
       input.append("sulje\n");
       ui = new CLUI(io, library);
       ui.init();
       assertTrue(io.getPrints().contains("Vinkki lisätty"));
    }


}