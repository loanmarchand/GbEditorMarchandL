package org.helmo.gbeditor.infrastructure;

import com.google.gson.reflect.TypeToken;
import org.helmo.gbeditor.model.BookDTO;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper implements HelperInterface {
    private final Gson gson;
    private final File dossierpath = new File(System.getProperty("user.home")+"/ue36");
    private final File filePath =  new File(Paths.get(dossierpath.getPath(),"e200249.json").toUri());

    public JsonHelper() {
        this.gson = new Gson();
        testfile();
    }

    private void testfile() {
        // Create the directory if it does not exist
        if (!dossierpath.exists()) {
            dossierpath.mkdir();
        }
        // Create the file if it does not exist
        if (!filePath.exists()) {
            try {
                filePath.createNewFile();
                FileWriter writer = new FileWriter(filePath);
                writer.write("[]");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(BookDTO bookDTO) {
        List<BookDTO> books = list();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equals(bookDTO.getIsbn())) {
                books.set(i, bookDTO); // Replace the existing book
                writeBooks(books);
                return;
            }
        }
        // If the book was not found, add it to the list
        books.add(bookDTO);
        writeBooks(books);
    }

    public BookDTO read(String isbn) {
        List<BookDTO> books = list();
        for (BookDTO book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null; // Return null if no book with the given ISBN was found
    }

    public List<BookDTO> list() {
        try (FileReader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<ArrayList<BookDTO>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void writeBooks(List<BookDTO> books) {
        String json = gson.toJson(books);
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(String isbn) {
        List<BookDTO> books = list();
        books.removeIf(book -> book.getIsbn().equals(isbn)); // Remove the book with the given ISBN
        writeBooks(books); // Write the updated list back to the file
    }
}
