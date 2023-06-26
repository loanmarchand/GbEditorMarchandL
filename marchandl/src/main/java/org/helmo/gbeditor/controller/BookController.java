package org.helmo.gbeditor.controller;

import org.helmo.gbeditor.model.BookServices;
import org.helmo.gbeditor.repositories.BookRepositories;
import org.helmo.gbeditor.repositories.DtoHelper;

import java.io.File;
import java.io.IOException;

public class BookController {
    private final BookServices bookService;
    private final BookRepositories bookRepositories;
    public BookController(BookServices bookService) {
        this.bookService = bookService;
        bookRepositories = new BookRepositories();
    }

    public boolean createBook(String title, String isbn, String resume) {
        if (title == null || isbn == null || resume == null || title.isEmpty() || isbn.isEmpty() || resume.isEmpty())
            return false;
        else
            bookRepositories.create(DtoHelper.toBookDTO(bookService.createBook(title, isbn, resume)));
        return true;
    }

    public String getIsbn(String lastTwoDigits) {
        return bookService.getIsbn(lastTwoDigits);
    }

    public boolean testFile(File selectedFile) throws IOException {
        return bookService.testFile(selectedFile);
    }
}
