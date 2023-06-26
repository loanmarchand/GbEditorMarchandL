package org.helmo.gbeditor.model;

public class AuthServices {
    private Author author;

    public void authenticate(String firstName, String lastName) {
        author = new Author(firstName, lastName);
    }

    public Author getAuthor() {
        return author;
    }
}
