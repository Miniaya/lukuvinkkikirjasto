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
            books.add((new Book(sug.getDetail("nimi"), sug.getDetail("kirjoittaja"), Integer.valueOf(sug.getDetail("sivumäärä")), 0.0, sug.getDetail("tagit"))));
            return true;
        } else if (sug.getType().equals("Article")) {
            if (articles == null) {
                articles = new ArrayList<>();
            }
            suggestions.add(sug);
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
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public List<Article> getArticles() {
        return articles;
    }
}
