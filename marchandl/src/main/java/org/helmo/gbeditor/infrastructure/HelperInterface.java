package org.helmo.gbeditor.infrastructure;

import org.helmo.gbeditor.model.BookDTO;

import java.util.List;

public interface HelperInterface {
    void write(BookDTO object);
    BookDTO read(String isbn);
    void delete(String isbn);
    List<BookDTO> list();
}
