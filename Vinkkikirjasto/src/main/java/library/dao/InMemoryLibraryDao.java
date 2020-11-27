
package library.dao;

import library.domain.Suggestion;
import java.util.*;
import library.domain.Article;
import library.domain.Book;

public class InMemoryLibraryDao implements LibraryDao {
    
    List<Suggestion> suggestions;
    List<Book> books;
    List<Article> articles;
    
    public InMemoryLibraryDao() {
        suggestions = new ArrayList<>();
    }
    
    @Override
    public boolean add(Suggestion sug) {
        if (sug.getType().equals("Book")) {
            if (books == null) {
                books = new ArrayList<>();
            }
            suggestions.add(sug);
            books.add(new Book(sug.getDetail("nimi"), sug.getDetail("kirjoittaja"), Integer.parseInt(sug.getDetail("sivumäärä"))));
            return true;
        } else if (sug.getType().equals("Article")) {
            if (articles == null) {
                articles = new ArrayList<>();
            }
            suggestions.add(sug);
            articles.add(new Article(sug.getDetail("nimi"), sug.getDetail("url")));
            return true;
        }
        return false;
    }
    
    @Override
    public boolean remove(String name, String type) {
        // tee tämä jos tarpeen
        return false;
    }
    
    @Override
    public List<Book> getBooks() {
        return books;
    }
    
    @Override
    public List<Article> getArticles() {
        return articles;
    }
}
