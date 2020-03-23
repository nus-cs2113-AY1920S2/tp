package seedu.nuke.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.nuke.gui.io.GuiExecutor;
import seedu.nuke.gui.io.GuiParser;
import seedu.nuke.gui.ui.TextUI;

import java.net.URL;
import java.util.ResourceBundle;

import static seedu.nuke.util.Message.*;


public class MainController implements Initializable {
    @FXML
    private TextField console;

    @FXML
    private TextFlow consoleMask;

    @FXML
    private TextFlow consoleScreen;

    @FXML
    private ScrollPane consoleScreenScrollPane;

    public TextField getConsole() {
        return console;
    }

    public TextFlow getConsoleScreen() {
        return consoleScreen;
    }

    public ScrollPane getConsoleScreenScrollPane() {
        return consoleScreenScrollPane;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        consoleScreen.setStyle("-fx-font-family: Consolas; -fx-font-size: 12pt");
        consoleMask.setStyle("-fx-font-family: Consolas; -fx-font-size: 12pt; -fx-background-color: aliceblue;"
                + "-fx-border-color: lightgrey; -fx-border-radius: 3");
        // Auto scroll-down
        consoleScreenScrollPane.vvalueProperty().bind(consoleScreen.heightProperty());

        Text logo = TextUI.createText(MESSAGE_LOGO, Color.MAGENTA);
        Text welcomeMessage = TextUI.createText(String.format("%s\n%s\n\n", MESSAGE_WELCOME_1, MESSAGE_WELCOME_2), Color.BLUE);
        Text divider = TextUI.createText(DIVIDER + "\n");

        consoleScreen.getChildren().addAll(logo, divider, welcomeMessage);
    }

    public void submitInput() {
        String userInput = console.getText().trim();
        new GuiExecutor(console, consoleScreen).executeAction(userInput);
        console.clear();
    }

    public void onEnterKey(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
        case UP:
            keyEvent.consume();
            System.out.println("View last command.");
            break;
        case DOWN:
            System.out.println("View earlier command.");
            break;
        case TAB:
            System.out.println("Auto-complete here.");
            break;
        default:
            String currentUserInput = console.getText();
            consoleMask.getChildren().clear();
            consoleMask.getChildren().add(TextUI.createText(currentUserInput, new GuiParser().parseCommandWord(currentUserInput)));
            break;
        }
    }
}
