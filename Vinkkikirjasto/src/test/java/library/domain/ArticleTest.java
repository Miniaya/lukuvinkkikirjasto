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
public class ArticleTest {
    
    private Article testArticle1;
    private Article testArticle2;
    
    @Before
    public void setUp() {
        this.testArticle1 = new Article("TestArticle", "www.test.fi", "TestTag");
        this.testArticle2 = new Article("TestArticle2", "www.test2.fi", "TestTag2");
    }

    @Test
    public void toStringMethodReturnsCorrectDetails() {
        assertEquals("\u001b[38;5;117mARTIKKELI\u001b[0m"
                + "\nNimi       | TestArticle"
                + "\nUrl        | \u001b[4m\u001b[38;5;159mwww.test.fi\u001b[0m"
                + "\nTagit      | TestTag\n-------------", testArticle1.toString());

        assertEquals("\u001b[38;5;117mARTIKKELI\u001b[0m"
                + "\nNimi       | TestArticle2"
                + "\nUrl        | \u001b[4m\u001b[38;5;159mwww.test2.fi\u001b[0m"
                + "\nTagit      | TestTag2\n-------------", testArticle2.toString());
    }
    
    @Test
    public void changedArticleDetailsAreShownInListing() {
        testArticle1.setName("New name");
        assertEquals("\u001b[38;5;117mARTIKKELI\u001b[0m"
        + "\nNimi       | New name"
        + "\nUrl        | \u001b[4m\u001b[38;5;159mwww.test.fi\u001b[0m"
        + "\nTagit      | TestTag\n-------------", testArticle1.toString());
    }
}
