package org.helmo.gbeditor.repositories;

import org.helmo.gbeditor.model.Book;
import org.helmo.gbeditor.model.BookDTO;

public class DtoHelper {

    public static Book toBook(BookDTO dto) {
        return new Book(dto.getTitle(), dto.getIsbn(), dto.getResume(), dto.getAuthor(), dto.getImageUrl());
    }

    public static BookDTO toBookDTO(Book book) {
        return new BookDTO(book.getTitle(), book.getIsbn(), book.getSummary(),book.getAuthor(), book.getImageUrl());
    }
}
