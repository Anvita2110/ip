package ponderpika.gui;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ponderpika.PonderPika;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private PonderPika ponderPika;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/pikabot.png"));

    /**
     * Helps in initializing GUI
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greeting = "Hello I'm ponderpika" + "\nIt is a great day to ponder! How may I help you?";
        dialogContainer.getChildren().add(DialogBox.getPikaDialog(greeting, botImage));
    }

    /** Injects the PonderPika instance */
    public void setDuke(PonderPika p) {
        ponderPika = p;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String pikaText = ponderPika.getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getPikaDialog(pikaText, botImage)
        );
        userInput.clear();
        if (userText.equals("bye")) {
            Platform.exit();
        }
    }
}
