
package library.dao;

import library.domain.Suggestion;
import java.util.*;
import library.domain.Book;

public class InMemoryLibraryDao implements LibraryDao {
    
    List<Suggestion> suggestions;
    List<Book> books;
    
    public InMemoryLibraryDao() {
        suggestions = new ArrayList<>();
    }
    
    @Override
    public boolean add(Suggestion sug) {
        if(books == null) {
            books = new ArrayList<>();
        }
        suggestions.add(sug);
        books.add(new Book(sug.getDetail("nimi"), sug.getDetail("kirjoittaja"), Integer.parseInt(sug.getDetail("sivumäärä"))));
        return true;
    }
    
    public List<Suggestion> getSuggestions() {
        return suggestions;
    }
    
    @Override
    public List<Book> getBooks() {
        return books;
    }
}
