package library.domain;

import java.util.HashMap;

/**
 *
 * @author saasini
 */
public class Book implements Suggestion{
    
    private String[] detailTypes;
    private HashMap<String, String> details;
    private String RED = "\u001b[38;5;210m";     // RED
    private String GREEN = "\u001b[38;5;157m";   // GREEN
    private String YELLOW = "\u001b[38;5;229m";  // YELLOW
    private String RESET = "\u001b[0m";  // RESET
    
    public Book(String name, String author, int pages, double read, String tags) {
        this.detailTypes = new String[]{"nimi", "kirjoittaja", "sivumäärä", "luettu", "tagit"};
        this.details = new HashMap<>();
        details.put("nimi", name);
        details.put("kirjoittaja", author);
        details.put("sivumäärä", Integer.toString(pages));
        details.put("luettu", Double.toString(read));
        details.put("tagit", tags);
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
    
    public void setTags(String tags) {
        details.put("tagit", tags);
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
    
    public String getTags() {
        return details.get("tagit");
    }

    public String getPercentColor(String prosentti) {
        if (Double.valueOf(prosentti) >= 100.0) {
            prosentti = GREEN + prosentti + "%" + RESET;
        } else if (Double.valueOf(prosentti) > 0.0) {
            prosentti = YELLOW + prosentti + "%" + RESET;
        } else {
            prosentti = RED + prosentti + "%" + RESET;
        }
        return prosentti;
    }
    
    @Override
    public String toString() {
        String prosentti = details.get("luettu");
        prosentti = getPercentColor(prosentti);
        return "\u001b[38;5;115mKIRJA \u001b[0m" 
                + "\nNimi       | " + details.get("nimi") 
                + "\nKirjoittaja| "  + details.get("kirjoittaja") 
                + "\nSivumäärä  | " + details.get("sivumäärä")
                + "\nLuettu     | " + prosentti
                + "\nTagit      | " + details.get("tagit")
                + "\n-------------";
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
