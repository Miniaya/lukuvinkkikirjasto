package library.domain;

import java.util.HashMap;

/**
 *
 * @author saasini
 */
public class Book implements Suggestion{
    
    private String[] detailTypes;
    private HashMap<String, String> details;
    private String RED = "\033[0;31m";     // RED
    private String GREEN = "\033[0;32m";   // GREEN
    private String YELLOW = "\033[0;33m";  // YELLOW
    private String RESET = "\033[0m";  // RESET
    
    public Book(String name, String author, int pages, double read) {
        this.detailTypes = new String[]{"nimi", "kirjoittaja", "sivumäärä", "luettu"};
        this.details = new HashMap<>();
        details.put("nimi", name);
        details.put("kirjoittaja", author);
        details.put("sivumäärä", Integer.toString(pages));
        details.put("luettu", Double.toString(read));
    }
    
    public Book() {
        this.details = new HashMap<String, String>();
    }

    public void setName(String name) {
        details.put("nimi", name);
    }

    public void setAuthor(String author) {
        details.put("kirjoittaja", author);
    }

    public void setPages(int pages) {
        details.put("sivumäärä", Integer.toString(pages));
    }
    
    public void setRead(double read) {
        details.put("luettu", Double.toString(read));
    }

    public String getName() {
        return details.get("nimi");
    }

    public String getAuthor() {
        return details.get("kirjoittaja");
    }

    public int getPages() {
        return Integer.valueOf(details.get("sivumäärä"));
    }
    
    public double getRead() {
        return Double.valueOf(details.get("luettu"));
    }
    
    @Override
    public String toString() {
        String prosentti = details.get("luettu");
        if (prosentti == "100") {
            prosentti = GREEN + prosentti + "%" + RESET;
        } else if (Double.valueOf(prosentti) > 0) {
            prosentti = YELLOW + prosentti + "%" + RESET;
        } else {
            prosentti = RED + prosentti + "%" + RESET;
        }
        return "Tyyppi: Kirja" 
                + "\nNimi: " + details.get("nimi") 
                + "\nKirjoittaja: "  + details.get("kirjoittaja") 
                + "\nSivumäärä: " + details.get("sivumäärä")
                + "\nLuettu: " + prosentti;
    }
    
    @Override
    public void addDetail(String detailType, String value) {
        details.put(detailType, value);
    }

    @Override
    public void addDetails(String[] detailTypes, String[] values) {
        this.detailTypes = detailTypes;
        for (int i = 0; i < detailTypes.length; i++) {
                this.details.put(detailTypes[i], values[i]);
            }
    }

    @Override
    public String[] getDetailTypes() {
        return detailTypes;
    }
    
    @Override
    public String getDetail(String detailType) {
        return details.getOrDefault(detailType, "-1");
    }

    @Override
    public String getType() {
        return "Book";
    }
}
