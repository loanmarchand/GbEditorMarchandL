package org.helmo.gbeditor.model;

public class Book {
    private final String title;
    private final String isbn;
    private final String summary;
    private final Author author;
    private final String imageUrl;

    public Book(String title, String isbn, String summary, Author author, String imageUrl) {
        this.title = title;
        this.isbn = isbn;
        this.summary = summary;
        this.author = author;
        this.imageUrl = imageUrl;
    }

    public String getTitle() { return title; }
    public String getIsbn() { return isbn; }
    public String getSummary() { return summary; }
    public Author getAuthor() { return author; }
    public String getImageUrl() { return imageUrl; }
}
