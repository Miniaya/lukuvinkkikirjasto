package library.dao;

import java.sql.SQLException;
import library.domain.Suggestion;
import java.util.List;
import library.domain.Article;
import library.domain.Book;

public interface LibraryDao {
    
    // Returns true, jos lisäys onnistui, muuten false
    public boolean add(Suggestion suggestion);
    
    public boolean remove(String name, String type);
    
    public boolean update(String type, String name, String update);
    
    public boolean updateBookTag(String name, String tag);
    
    public boolean updateArticleTag(String name, String tag);
    
    List<Suggestion> getAll();
    
    List<Suggestion> getSuggestionsByTag(String tag);
}
