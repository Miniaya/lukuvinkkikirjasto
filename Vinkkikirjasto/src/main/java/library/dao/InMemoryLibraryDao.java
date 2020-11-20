
package library.dao;

import library.domain.Suggestion;
import java.util.*;

public class InMemoryLibraryDao implements LibraryDao {
    
    ArrayList<Suggestion> suggestions;
    
    @Override
    public boolean add(Suggestion sug) {
        suggestions.add(sug);
        return true;
    }
    
    public ArrayList<Suggestion> getSuggestions() {
        return suggestions;
    }
}
