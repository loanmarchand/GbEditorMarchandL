package org.helmo.gbeditor.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.helmo.gbeditor.controller.AuthController;
import org.helmo.gbeditor.controller.BookController;
import org.helmo.gbeditor.model.BookServices;

public class AuthView {
    private final AuthController controller;

    public AuthView(AuthController controller) {
        this.controller = controller;
    }

    /**
     * Affiche la vue de connexion
     * Contient un formulaire de connexion avec deux champs (firstname et lastname) et un bouton de connexion
     * @param stage la fenêtre principale
     */
    public void display(Stage stage) {
        Label titleLabel = new Label("Welcome to GBEditor");
        titleLabel.setFont(new Font("Arial", 24));

        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First name");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last name");

        Label errorLabel = new Label();

        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #007BFF; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 10;");
        submitButton.setOnAction(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            if (controller.authenticate(firstName, lastName)){
                BookServices bookService = new BookServices(controller.getAuthor());
                BookController bookController = new BookController(bookService);
                BookView bookView = new BookView(bookController);
                bookView.show(stage);
            }
            else {
                //Show error
                errorLabel.setText("Please enter your first and last name");
                errorLabel.setStyle("-fx-text-fill: red;");
            }

        });

        VBox layout = new VBox(10, titleLabel, firstNameField, lastNameField,errorLabel, submitButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #F0F0F0;");

        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);
        stage.show();
    }
}
