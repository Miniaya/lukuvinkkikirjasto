package library.domain;

public class Book {
    
    private String name;
    private String author;
    private int pages;
    
    public Book(String name, String author, int pages) {
        this.name = name;
        this.author = author;
        this.pages = pages;
    }
    
    @Override
    public String toString() {
        return "Nimi: " + name + ", kirjoittaja: " + author + ", sivumäärä: " + pages;
    }
}
