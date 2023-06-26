package org.helmo.gbeditor.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BookServices {
    private final Author author;
    private String urlImage;
    public BookServices(Author author) {
        this.author = author;
    }

    public String getIsbn(String lastTwoDigits) {
        String isbn = "2200249" + lastTwoDigits;
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Integer.parseInt(isbn.substring(i, i + 1));
            sum += digit * (10 - i);
        }
        int checkDigit = 11 - (sum % 11);
        if (checkDigit == 11) {
            checkDigit = 0;
        } else if (checkDigit == 10) {
            return isbn + "X";
        }
        return isbn + checkDigit;
    }

    public Book createBook(String title, String isbn, String resume) {
        return new Book(title, isbn, resume, author, urlImage);
    }

    public boolean testFile(File selectedFile) throws IOException {
        FileInputStream fis = new FileInputStream(selectedFile);
        byte[] header = new byte[8];
        fis.read(header, 0, 8);
        fis.close();

        if (header[0] == (byte) 0xFF && header[1] == (byte) 0xD8 && header[2] == (byte) 0xFF) {
            urlImage = selectedFile.getAbsolutePath();
            return true;
        } else if(header[0] == (byte) 0x89 && header[1] == (byte) 0x50 && header[2] == (byte) 0x4E && header[3] == (byte) 0x47 && header[4] == (byte) 0x0D && header[5] == (byte) 0x0A && header[6] == (byte) 0x1A && header[7] == (byte) 0x0A){
            urlImage = selectedFile.getAbsolutePath();
            return true;
        }
        return false;



    }
}
