package org.helmo.gbeditor.repositories;

import org.helmo.gbeditor.infrastructure.HelperInterface;
import org.helmo.gbeditor.infrastructure.JsonHelper;
import org.helmo.gbeditor.model.BookDTO;

import java.util.List;

public class BookRepositories {
    private final HelperInterface helperInterface;

    public BookRepositories() {
        this.helperInterface = new JsonHelper();
    }

    public void create(BookDTO bookDTO) {
        helperInterface.write(bookDTO);
    }

    public BookDTO read(String isbn) {
        return helperInterface.read(isbn);
    }

    public void update(BookDTO bookDTO) {
        helperInterface.write(bookDTO);
    }

    public void delete(String isbn) {
        helperInterface.delete(isbn);
    }

    public List<BookDTO> list() {
        return helperInterface.list();
    }

}
