package org.helmo.gbeditor.model;

public class BookDTO {
    private final String title;
    private final String isbn;
    private final Author author;
    private final String resume;
    private final String imageUrl;

    public BookDTO(String title, String isbn,String resume, Author author, String imageUrl) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.resume = resume;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public String getResume() {
        return resume;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
