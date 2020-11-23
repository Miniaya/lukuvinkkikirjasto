
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
    List<String> input;
    
    @Before
    public void setup() {
        library = new LibraryService(new InMemoryLibraryDao());
        io = new StubIO();
        ui = new CLUI(io, library);
        input = new ArrayList<>();
    }
    
    @Given("command uusi is selected")
    public void commandUusiIsSelected() {
        input.add("uusi");
    }
    
    @When("correct book information is given")
    public void correctBookInfoGive() {
        input.add("kirja");
        input.add("kirjoittaja");
        input.add("100");
    }
    
    @Then("book is saved to library")
    public void bookIsSaved() {
       input.add("sulje");
       
       io.setInputs(input);
       ui.init();
       assertTrue(io.getPrints().contains("Vinkki lisätty"));
    }


}