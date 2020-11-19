package library.domain;

import java.util.*;

/**
 * Luokka johon tallennetaan syötetyt vinkit.
 */
public class Suggestion {
    private String type;
    private ArrayList<String> detailTypes;
    private HashMap<String, String> details;
    
    /**
     * Konstruktorille annetaan tyyppi joka vastaa jotain tietokannan taulun
     * nimeä.
     * @param type vinkin tyyppi Stringinä (esim. "Book", "Article" jne.)
     */
    public Suggestion(String type) {
        this.type = type;
        this.detailTypes = new ArrayList<>();
        this.details = new HashMap<>();
    }
    
    /**
     * Lisää vinkkiin tietoa ("nimi", "kirjoittaja" jne.).
     * @param detailType arvon tyyppi
     * @param value arvo
     */
    public void addDetail(String detailType, String value) {
        detailTypes.add(detailType);
        details.put(detailType, value);
    }
    
    /**
     * Lisää vinkkiin useamman tyyppi:arvo-parin. Lisättävien taulujen täytyy
     * olla järjestetty niin että tyyppi ja sitä vastaava arvo ovat samassa
     * indeksissä.
     * @param types lisättävien arvojen tyypit tauluna
     * @param values lisättävät arvot tauluna
     */
    public void addDetails(String[] types, String[] values) {
        for (int i = 0; i < types.length; i++) {
            detailTypes.add(types[i]);
            details.put(types[i], values[i]);
        }
    }
    
    /**
     * Getteri tämän vinkin tyypille.
     * @return vinkin tyyppi Stringinä
     */
    public String getType() {
        return this.type;
    }
    
    /**
     * Getteri yksittäisen tyyppi:arvo-parin arvolle.
     * @param detailType palautettavan arvon tyyppi
     * @return annettua tyyppiä vastaava arvo Stringinä
     */
    public String getDetail(String detailType) {
        return details.getOrDefault(detailType, "n/a");
    }
    
    /**
     * Getteri tämän vinkin sisältämille tietotyypeille.
     * @return tietotyypit String-tauluna
     */
    public String[] getDetailTypes() {
        return detailTypes.toArray(new String[detailTypes.size()]);
    }
    
    /**
     * Getteri tämän vinkin sisältämille arvoille.
     * @return arvot String-tauluna
     */
    public String[] getDetailValues() {
        String[] detailValues = new String[detailTypes.size()];
        for (int i = 0; i < detailTypes.size(); i++) {
            detailValues[i] = getDetail(detailTypes.get(i));
        }
        return detailValues;
    }
    
    /**
     * Getteri tämän vinkin sisältämille tyyppi:arvo-pareille.
     * @return tyyppi:arvo-parit hajautustauluna
     */
    public HashMap getDetails() {
        return details;
    }
}
