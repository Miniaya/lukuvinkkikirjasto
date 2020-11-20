
package library;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import library.dao.*;
import library.domain.*;
import library.ui.*;
import java.util.*;

public class Stepdefs {
    
    CLUI ui;
    LibraryDao library;
    StringBuilder input;
    
    @Before
    public void setup() {
        library = new InMemoryLibraryDao();
        input = new StringBuilder();
    }
    
    @Given("command uusi is selected")
    public void commandUusiIsSelected() {
        input.append("uusi\n");
    }
    
    @Then("system will respond with {string}")
    public void systemRespondsWith(String viesti) {
       ui = new CLUI(new Scanner(input.toString()), library);
       ui.init();
       
    }

//    @When("it is incremented")
//    public void itIsIncremented() {
//        counter.increase();
//    }
//
//    @Then("the value should be {int}")
//    public void theValueShouldBe(Integer val) {
//        assertEquals(val.intValue(), counter.value());
//    }
//
//    @When("it is incremented by {int}")
//    public void itIsIncrementedBy(Integer val) {
//         counter.increment(val);       
//    }    
//
//    @When("it is reset")
//    public void itIsReset() {
//        counter.reset();
//    }

}