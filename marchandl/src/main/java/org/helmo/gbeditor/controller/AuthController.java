package org.helmo.gbeditor.controller;

import org.helmo.gbeditor.model.AuthServices;
import org.helmo.gbeditor.model.Author;

public class AuthController {
    private final AuthServices authServices;

    public AuthController(AuthServices authServices) {
        this.authServices = authServices;
    }

    public boolean authenticate(String firstName, String lastName) {
        if (firstName == null || lastName == null || firstName.isEmpty() || lastName.isEmpty())
            return false;
        else
            authServices.authenticate(firstName, lastName);
        return true;
    }

    public Author getAuthor() {
        return authServices.getAuthor();
    }
}
