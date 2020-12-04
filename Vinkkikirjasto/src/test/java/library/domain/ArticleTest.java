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
        assertEquals("Tyyppi: Artikkeli"
                + "\nNimi: TestArticle"
                + "\nUrl: www.test.fi"
                + "\nTagit: TestTag", testArticle1.toString());
        assertEquals("Tyyppi: Artikkeli"
                + "\nNimi: TestArticle2"
                + "\nUrl: www.test2.fi"
                + "\nTagit: TestTag2", testArticle2.toString());
    }
    
    @Test
    public void changedArticleDetailsAreShownInListing() {
        testArticle1.setName("New name");
        assertEquals("Tyyppi: Artikkeli"
                + "\nNimi: New name"
                + "\nUrl: www.test.fi"
                + "\nTagit: TestTag", testArticle1.toString());
    }
}
