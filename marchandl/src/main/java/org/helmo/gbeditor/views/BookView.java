package org.helmo.gbeditor.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.helmo.gbeditor.controller.BookController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BookView {
    private final BookController bookController;
    private ImageView imageView;

    public BookView(BookController bookController) {
        this.bookController = bookController;
    }

    public void show(Stage stage) {
        Button listButton = new Button("Create book");
        listButton.setStyle("-fx-background-color: #007BFF; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 10;");
        listButton.setOnAction(e -> showForm(stage));
        VBox layout = new VBox(10, listButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 500, 400);
        stage.setScene(scene);
        stage.show();

    }

    private void showForm(Stage stage) {
        Label titleLabel = new Label("Create a new book");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField titleField = new TextField();
        titleField.setPromptText("Title");
        //Limit the number of characters a 150
        titleField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 150) {
                titleField.setText(oldValue);
            }
        });

        TextField lastTwoDigitsOfIsbn = new TextField();
        lastTwoDigitsOfIsbn.setPromptText("last two digits of ISBN");
        //limit the number of characters a 2
        lastTwoDigitsOfIsbn.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 2) {
                lastTwoDigitsOfIsbn.setText(oldValue);
            }
        });
        Label isbnLabel = new Label();
        isbnLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        //Dès que le champ lastTwoDigitsOfIsbn change, on met à jour le label isbnLabel
        lastTwoDigitsOfIsbn.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 1){
                isbnLabel.setText(bookController.getIsbn("0"+newValue));
            }
            else if (newValue.length() == 2){
                isbnLabel.setText(bookController.getIsbn(newValue));
            }
            else {
                isbnLabel.setText("");
            }
        });

        TextArea resumeField = new TextArea();
        resumeField.setPromptText("Resume");
        resumeField.setWrapText(true);
        resumeField.setMaxHeight(200);
        resumeField.setMaxWidth(400);
        //Limit the number of characters a 500
        resumeField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 500) {
                resumeField.setText(oldValue);
            }
        });


        imageView = new ImageView();

        Button imageButton = new Button("Select Image");
        imageButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JPG Files", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG Files", "*.png")
            );
            File selectedFile = fileChooser.showOpenDialog(stage);
            try {
                if (!bookController.testFile(selectedFile)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("The type of the file is not supported");
                    alert.showAndWait();
                }
                else {
                    Image image = new Image(new FileInputStream(selectedFile));
                    imageView.setImage(image);
                    imageView.setFitHeight(200);
                    imageView.setPreserveRatio(true);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px; -fx-font-weight: bold;");

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #007BFF; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 10;");
        backButton.setOnAction(e -> show(stage));

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            String title = titleField.getText();
            String isbn = isbnLabel.getText();
            String resume = resumeField.getText();
            if (bookController.createBook(title, isbn, resume)){
                show(stage);
            }
            else {
                errorLabel.setText("Tous les champs doivent être remplis");
            }
        });
        submitButton.setStyle("-fx-background-color: #007BFF; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 10;");



        VBox layout = new VBox(10, titleLabel, titleField, lastTwoDigitsOfIsbn,isbnLabel, resumeField, imageView, imageButton,errorLabel, submitButton,backButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 500, 600);
        stage.setScene(scene);
    }
}
