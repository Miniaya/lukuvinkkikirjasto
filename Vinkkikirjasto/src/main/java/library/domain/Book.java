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

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }
    
    @Override
    public String toString() {
        return "Nimi: " + name + ", kirjoittaja: " + author + ", sivumäärä: " + pages;
    }
}
