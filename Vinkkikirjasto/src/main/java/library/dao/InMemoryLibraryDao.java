
package library.dao;

import library.domain.Suggestion;
import java.util.*;
import library.domain.Book;

public class InMemoryLibraryDao implements LibraryDao {
    
    ArrayList<Suggestion> suggestions;
    
    public InMemoryLibraryDao() {
        suggestions = new ArrayList<>();
    }
    
    @Override
    public boolean add(Suggestion sug) {
        suggestions.add(sug);
        return true;
    }
    
    public ArrayList<Suggestion> getSuggestions() {
        return suggestions;
    }
    
    //Tämä metodi ei ole käytössä
    @Override
    public List<Book> getBooks() {
        return new ArrayList<>();
    }
}
