package library.domain;

import java.util.HashMap;

/**
 *
 * @author saasini
 */
public class Book implements Suggestion{
    
    private String[] detailTypes;
    private HashMap<String, String> details;
    
    public Book(String name, String author, int pages) {
        this.detailTypes = new String[]{"nimi", "kirjoittaja", "sivumäärä"};
        this.details = new HashMap<>();
        details.put("nimi", name);
        details.put("kirjoittaja", author);
        details.put("sivumäärä", Integer.toString(pages));
    }
    
    public Book() {
        this.details = new HashMap<String, String>();
    }

    
    @Override
    public String toString() {
        return "Nimi: " + details.get("nimi") + "\nkirjoittaja: " 
                + details.get("kirjoittaja") + "\nsivumäärä: " 
                + details.get("sivumäärä");
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
