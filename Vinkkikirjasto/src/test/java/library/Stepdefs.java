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
    
    @Given ("book Margarita with tag finlandia is saved")
    public void saveMargaritaBook() {
        Book m = this.getBookMargarita();
        library.add("book", m.getDetailTypes(),
                new String[]{m.getName(), m.getAuthor(), Integer.toString(m.getPages()), Double.toString(m.getRead()), m.getTags()});
    }
    
    @Given ("article Ohtumateriaali with tag ohjelmistotuotanto is saved")
    public void saveOhtuArticle() {
        Article a = this.getArticleOhtumateriaali();
        library.add("article", a.getDetailTypes(), 
                new String[]{a.getName(), a.getUrl(), a.getTags()});
    }
    
    @Given("article Finlandia-hymni is saved")
    public void saveFinlandia() {
        Article a = this.getArticleFinlandiaHymn();
        library.add("article", a.getDetailTypes(),
                new String[]{a.getName(), a.getUrl(), a.getTags()});
    }
    
    @Given ("command hae is selected")
    public void commandHaeIsSelected() {
        input.add("hae");
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

    @When("tag {string} is entered")
    public void enterTag(String tag) {
        input.add(tag);
    }

    @When("name of the book {string} and amount of pages read {string} is given")
    public void nameOfTheBookAndAmountOfPagesReadIsGiven(String string, String string2) {
        input.add("muokkaa");
        input.add("sivumaara");
        input.add(string);
        input.add(string2);
    }

    @When("correct article information is given")
    public void correctArticleInformationIsGiven() {
        input.add("artikkeli");
        input.add("artikkeli");
        input.add("www.garbage.com");
        input.add("tag");
    }
    
    @When("tag {string} is added to {string} named {string}")
    public void addDesiredTag(String tag, String type, String name) {
        input.addAll(Arrays.asList("muokkaa", "tagi", "lisaa", type, name, tag));
    }
    
    @When("tag {string} is removed from {string} named {string}")
    public void removeDesiredTagFromGivenBookOrArticle(String tag, String type, String name) {
        input.addAll(Arrays.asList("muokkaa", "tagi", "poista", type, name, tag));
    }

    @Then("book is saved to library")
    public void bookIsSaved() {
        this.closeAndRunCLUI();
        assertTrue(io.getPrints().contains("Vinkki lisätty."));
    }

    @Then("error message {string} is shown")
    public void errorMessageShown(String message) {
        this.closeAndRunCLUI();
        assertTrue(io.getPrints().contains(message));
    }

    @Then("book is removed from library")
    public void bookIsRemoved() {
        this.closeAndRunCLUI();
        assertTrue(io.getPrints().contains("Kirja kirja poistettu vinkkikirjastosta."));
    }

    @Then("article is removed from library")
    public void articleIsRemoved() {
        this.closeAndRunCLUI();
        assertTrue(io.getPrints().contains("Artikkeli artikkeli poistettu vinkkikirjastosta."));
    }

    @Then("article is saved to library")
    public void articleIsSavedToLibrary() {
        this.closeAndRunCLUI();
        assertTrue(io.getPrints().contains("Vinkki lisätty."));
    }


    @Then("pages read is updated with the new value")
    public void pagesReadIsUpdatedWithTheNewValue() {
        this.closeAndRunCLUI();
        assertTrue(io.getPrints().contains("Luettu sivumäärä päivitetty."));
    }
    
    @Then("number of pages read are displayed in red")
    public void pagesDisplayedRed() {
        this.closeAndRunCLUI();
        boolean result = this.goThroughPrintsLookingFor("\nLuettu     | \u001b[38;5;210m");
        assertTrue(result);
    }
    
    @Then("number of pages read are displayed in yellow")
    public void pagesDisplayedYellow() {
        this.closeAndRunCLUI();
        boolean result = this.goThroughPrintsLookingFor("\nLuettu     | \u001b[38;5;229m");
        assertTrue(result);
    }
    
    @Then("number of pages read are displayed in green")
    public void pagesDisplayedGreen() {
        this.closeAndRunCLUI();
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
        this.closeAndRunCLUI();
        boolean result = this.goThroughPrintsLookingFor(tags);
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
    
    @Then ("info for Margarita is displayed")
    public void margaritaInfoDisplayed() {
        this.closeAndRunCLUI();
        Book book = this.getBookMargarita();
        Boolean result = this.goThroughPrintsLookingFor(book.toString());
        assertTrue(result);
    }
    
    @Then ("info for Ohtumateriaali is displayed")
    public void ohtuInfoDisplayed() {
        this.closeAndRunCLUI();
        Article ohtu = this.getArticleOhtumateriaali();
        boolean result = this.goThroughPrintsLookingFor(ohtu.toString());
        assertTrue(result);
    }
    
    @Then("info for both Margarita and Finlandia are displayed")
    public void margaritaAndOhtuInfoDisplayed() {
        this.closeAndRunCLUI();
        Article finlandia = this.getArticleFinlandiaHymn();
        Book margarita = this.getBookMargarita();
        boolean result = this.goThroughPrintsLookingFor(finlandia.toString()) 
                && this.goThroughPrintsLookingFor(margarita.toString());
        assertTrue(result);
    }
    
    @Then("new tag {string} is added")
    public void getsMessageForAddingTagSuccesfully(String tag) {
        this.closeAndRunCLUI();
        boolean result = this.goThroughPrintsLookingFor("Tagi " + tag + " lisätty.");
        assertTrue(result);
    }
    
    @Then("tag {string} is deleted")
    public void getsMessageForDeletingTagSuccesfully(String tag) {
        this.closeAndRunCLUI();
        boolean result = this.goThroughPrintsLookingFor("Tagi " + tag + " poistettu.");
        assertTrue(result);
    }
    
    @Then("there is no duplicate of tag {string}")
    public void noDuplicatedTag(String tag) {
        this.closeAndRunCLUI();
        boolean result = !this.goThroughPrintsLookingFor(tag + " " + tag);
        assertTrue(result);
    }
    
    
    private void closeAndRunCLUI() {
        input.add("sulje");
        io.setInputs(input);
        ui.init(); 
    }
    
    private boolean goThroughPrintsLookingFor(String toFind) {
        for (String s: io.getPrints()) {
            if (s.contains(toFind)) {
                return true;
            }
        }
        return false;
    }
    
    private Book getBookMargarita() {
        Book book = new Book("Margarita", "Anni Kytömäki", 581, 0, "finlandia");
        return book;
    }
    
    private Article getArticleOhtumateriaali() {
        Article article = new Article("Ohtumateriaali", "https://ohjelmistotuotanto-hy.github.io/", "ohjelmistotuotanto");
        return article;
    }
    
    private Article getArticleFinlandiaHymn() {
        Article article = new Article("Finlandia (Youtube)", "https://www.youtube.com/watch?v=F5zg_af9b8c", "finlandia");
        return article;
    }
}
