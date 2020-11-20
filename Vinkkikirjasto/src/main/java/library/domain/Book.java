/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.domain;

/**
 *
 * @author saasini
 */
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
        return "Name: " + name + ", author: " + author + ", pages: " + pages;
    }
}
