package library.dao;

import library.domain.Suggestion;
import java.util.*;
import library.domain.Article;
import library.domain.Book;

public class InMemoryLibraryDao implements LibraryDao {

    List<Book> books;
    List<Article> articles;

    public InMemoryLibraryDao() {
        books = new ArrayList<>();
        articles = new ArrayList<>();
    }

    @Override
    public boolean add(Suggestion sug) {
        if (sug.getType().equals("Book")) {
            books.add((new Book(sug.getDetail("nimi"), sug.getDetail("kirjoittaja"), Integer.valueOf(sug.getDetail("sivumäärä")), 0.0, sug.getDetail("tagit"))));
            return true;
        } else if (sug.getType().equals("Article")) {
            articles.add(new Article(sug.getDetail("nimi"), sug.getDetail("url"), sug.getDetail("tagit")));
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(String name, String type) {
        boolean removed = false;
        ArrayList<Suggestion> toRemove = new ArrayList<>();
        if (type.equals("book")) {
            for (Book book : books) {
                if (book.getName().equals(name)) {
                    toRemove.add(book);
                    removed = true;
                }
            }
            for (Suggestion s : toRemove) {
                books.remove((Book) s);
            }
            toRemove.clear();
        } else if (type.equals("article")) {
            for (Article article : articles) {
                if (article.getName().equals(name)) {
                    toRemove.add(article);
                    removed = true;
                }
            }
            for (Suggestion s : toRemove) {
                articles.remove((Article) s);
            }
        }
        return removed;
    }

    @Override
    public boolean update(String type, String name, String pages) {
        boolean updated = false;
        if (type.equals("book")) {
            for (Book book : books) {
                if (book.getName().equals(name)) {
                    book.setRead(Double.valueOf(pages));
                    updated = true;
                }
            }
        }
        return updated;
    }
    
    @Override
    public List<Suggestion> getAll() {
        List<Suggestion> sugs = new ArrayList<>();
        
        sugs.addAll(books);
        sugs.addAll(articles);
        
        return sugs;
    }

    public List<Book> getBooks(String tag) {
        return books;
    }

    public List<Article> getArticles(String tag) {
        return articles;
    }
    
    @Override
    public List<Suggestion> getSuggestionsByTag(String tag) {
        ArrayList<Suggestion> sugs = new ArrayList<>();
        for (Book book: books) {
            if (book.getTags().contains(tag)) {
                sugs.add(book);
            }
        }
        for (Article a: articles) {
            if (a.getTags().contains(tag)) {
                sugs.add(a);
            }
        }
        return sugs;
    }
}
