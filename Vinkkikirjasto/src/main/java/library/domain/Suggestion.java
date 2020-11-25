/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.domain;

import java.util.HashMap;

/**
 *
 * @author aleksi
 */
public interface Suggestion {
    
    void addDetail(String detailType, String value);

    /**
     * Lisää vinkkiin useamman tyyppi:arvo-parin. Lisättävien taulujen täytyy
     * olla järjestetty niin että tyyppi ja sitä vastaava arvo ovat samassa
     * indeksissä.
     * @param types lisättävien arvojen tyypit tauluna
     * @param values lisättävät arvot tauluna
     */
    void addDetails(String[] types, String[] values);

    /**
     * Getteri tämän vinkin sisältämille tietotyypeille.
     * @return tietotyypit String-tauluna
     */
    String[] getDetailTypes();
    
    /**
     * Palauttaa annettua tyyppiä vastaavan arvon. Esim:
     * book.getDetail("nimi") palauttaa "Iloinen Aapinen" jne.
     * @param detailType haettavan kentän tyyppi
     * @return kentän arvo Stringinä
     */
    String getDetail(String detailType);

    /**
     * Getteri tämän vinkin tyypille ("Book", "Article" jne.).
     * @return vinkin tyyppi Stringinä
     */
    String getType();
    
    /**
     * UI:n käyttämä tulostusmetodi. Formaatti:
     * "[Kenttä 1]: " + [arvo 1] + ... + "\n[Kenttä n]: " + [arvo n]
     * Book-luokasta voi katsoa myös mallia.
     * @return oikein formatoitu String joka sisältää objektin kentät ja arvot
     */
    @Override
    String toString();
}
