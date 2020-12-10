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

    @Given("book {string} with author {string} and pages {string} is saved")
    public void correctBookSaved(String title, String author, String pages) {
        library.add("book", new String[]{"nimi", "kirjoittaja", "sivumäärä", "tagit"}, new String[]{title, author, pages, "tag"});
    }

    @Given("^command poista is selected$")
    public void commandPoistaIsSelected() {
        input.add("poista");
    }

    @Given("article {string} with url {string} is saved")
    public void correctArticleSaved(String title, String url) {
        library.add("article", new String[]{"nimi", "url", "tagit"}, new String[]{title, url, "tag"});
    }
    
    @Given ("command listaa is selected")
    public void commandListaaIsSelected() {
        input.add("listaa");
    }

    @When("correct book information is given")
    public void correctBookInfoGiven() {
        input.add("kirja");
        input.add("kirja");
        input.add("kirjoittaja");
        input.add("100");
        input.add("tagi");
    }

    @When("incorrect book title is given")
    public void incorrectTitleGiven() {
        input.add("kirja");
        input.add("väärä");
    }

    @When("correct book title is given")
    public void correctBookTitleGiven() {
        input.add("kirja");
        input.add("kirja");
    }

    @When("correct article title is given")
    public void correctArticleTitleGiven() {
        input.add("artikkeli");
        input.add("artikkeli");
    }

    @When("incorrect article title is given")
    public void incorrectArticleTitleGiven() {
        input.add("artikkeli");
        input.add("väärä");
    }

    @When("I want to try again")
    public void tryAgain() {
        input.add("k");
    }

    @When("I don't want to try again")
    public void dontTryAgain() {
        input.add("e");
    }
    
    @When("tags {string}, {string} and {string} are added")
    public void tagsAreAdded(String tag1, String tag2, String tag3) {
        // Poistetaan inputista default-tagi
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).contains("tag")) {
                input.remove(i);
            }
        }
        input.add(tag1 + "," + tag2 + "," + tag3);
    }

    @Then("book is saved to library")
    public void bookIsSaved() {
        input.add("sulje");

        io.setInputs(input);
        ui.init();
        assertTrue(io.getPrints().contains("Vinkki lisätty."));
    }

    @Then("error message {string} is shown")
    public void errorMessageShown(String message) {
        input.add("sulje");

        io.setInputs(input);
        ui.init();
        assertTrue(io.getPrints().contains(message));
    }

    @Then("book is removed from library")
    public void bookIsRemoved() {
        input.add("sulje");

        io.setInputs(input);
        ui.init();
        assertTrue(io.getPrints().contains("Kirja kirja poistettu vinkkikirjastosta."));
    }

    @Then("article is removed from library")
    public void articleIsRemoved() {
        input.add("sulje");

        io.setInputs(input);
        ui.init();
        assertTrue(io.getPrints().contains("Artikkeli artikkeli poistettu vinkkikirjastosta."));
    }

    @When("correct article information is given")
    public void correctArticleInformationIsGiven() {
        input.add("artikkeli");
        input.add("artikkeli");
        input.add("www.garbage.com");
        input.add("tag");
    }

    @Then("article is saved to library")
    public void articleIsSavedToLibrary() {
        input.add("sulje");

        io.setInputs(input);
        ui.init();
        assertTrue(io.getPrints().contains("Vinkki lisätty."));
    }

    @When("name of the book {string} and amount of pages read {string} is given")
    public void nameOfTheBookAndAmountOfPagesReadIsGiven(String string, String string2) {
        input.add("muokkaa");
        input.add("sivumaara");
        input.add(string);
        input.add(string2);
    }

    @Then("pages read is updated with the new value")
    public void pagesReadIsUpdatedWithTheNewValue() {
        input.add("sulje");
        io.setInputs(input);
        ui.init();
        assertTrue(io.getPrints().contains("Luettu sivumäärä päivitetty."));
    }
    
    @Then("number of pages read are displayed in red")
    public void pagesDisplayedRed() {
        input.add("sulje");
        
        io.setInputs(input);
        ui.init();
        boolean result = false;
        for (String s: io.getPrints()) {
            if (s.contains("\nLuettu     | \u001b[38;5;210m")) {
                result = true;
            }
        }
        assertTrue(result);
    }
    
    @Then("number of pages read are displayed in yellow")
    public void pagesDisplayedYellow() {
        input.add("sulje");
        
        io.setInputs(input);
        ui.init();
        boolean result = false;
        for (String s: io.getPrints()) {
            if (s.contains("\nLuettu     | \u001b[38;5;229m")) {
                result = true;
            }
        }
        assertTrue(result);
    }
    
    @Then("number of pages read are displayed in green")
    public void pagesDisplayedGreen() {
        input.add("sulje");
        
        io.setInputs(input);
        ui.init();
        boolean result = false;
        for (String s: io.getPrints()) {
            if (s.contains("\nLuettu     | \u001b[38;5;157m")) {
                result = true;
            }
        }
        assertTrue(result);
    }
    
    @Then("suggestion has tags {string}, {string} and {string}")
    public void suggestionHasTags(String tag1, String tag2, String tag3) {
        String tags = tag1 + " " + tag2 + " " + tag3;
        input.add("listaa");
        input.add("sulje");
        io.setInputs(input);
        ui.init();
        boolean result = false;
        for (String s: io.getPrints()) {
            if (s.contains(tags)) {
                result = true;
            }
        }
        assertTrue(result);
    }
    
    @Then("suggestion has no duplicate tags")
    public void noDuplicateTags() {
        input.add("listaa");
        input.add("sulje");
        io.setInputs(input);
        ui.init();
        boolean result = true;
        String[] tags = new String[2];
        for (String s: io.getPrints()) {
            if (s.contains("Tagit")) {
                tags = s.split("Tagit");
                System.out.println(Arrays.toString(tags));
                tags = tags[tags.length-1].split("\\s+");
                System.out.println(Arrays.toString(tags));
                break;             
            }
        }
        for (int i = 2; i < tags.length; i++) {
                    for (int j = i + 1; j < tags.length; j++) {
                        if (tags[i].equals(tags[j])) {
                            result = false;
                        }
                    }
                }
        assertTrue(result);
    }
}
