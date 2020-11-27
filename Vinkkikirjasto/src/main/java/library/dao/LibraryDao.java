package library.dao;

import library.domain.Suggestion;
import java.util.List;
import library.domain.Article;
import library.domain.Book;

public interface LibraryDao {
    
    // Returns true, jos lisäys onnistui, muuten false
    public boolean add(Suggestion suggestion);
    
    public boolean remove(String name, String type);
    
    List<Book> getBooks();
    
    List<Article> getArticles();
}
