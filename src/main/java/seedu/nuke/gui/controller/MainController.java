package seedu.nuke.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.nuke.gui.component.AutoCompleteTextField;
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
    private TextFlow consoleScreen;

    @FXML
    private ScrollPane consoleScreenScrollPane;

    @FXML
    private VBox consoleBox;

    private TextFlow consoleMask;
    private AutoCompleteTextField autoCompleteTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        consoleScreen.setStyle("-fx-font-family: Consolas; -fx-font-size: 12pt");
        Text logo = TextUI.createText(MESSAGE_LOGO, Color.MAGENTA);
        Text welcomeMessage = TextUI.createText(String.format("%s\n%s\n\n", MESSAGE_WELCOME_1, MESSAGE_WELCOME_2), Color.BLUE);
        Text divider = TextUI.createText(DIVIDER + "\n");
        consoleScreen.getChildren().addAll(logo, divider, welcomeMessage);
        // Auto scroll-down
        consoleScreenScrollPane.vvalueProperty().bind(consoleScreen.heightProperty());

        consoleMask = new TextFlow();
        consoleMask.setStyle("-fx-font-family: Consolas; -fx-font-size: 12pt; -fx-background-color: aliceblue;"
                + "-fx-border-color: lightgrey; -fx-border-radius: 3");
        consoleMask.setMinHeight(30);
        consoleMask.setPadding(new Insets(5));

        autoCompleteTextField = new AutoCompleteTextField();
        autoCompleteTextField.setMinHeight(30);
        autoCompleteTextField.setPadding(new Insets(5));
        autoCompleteTextField.setStyle("-fx-font-family: Consolas; -fx-font-size: 12pt; -fx-background-color: white;"
                + "-fx-border-color: lightgrey; -fx-border-radius: 3");

        autoCompleteTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            String currentUserInput = autoCompleteTextField.getText();
            consoleMask.getChildren().clear();
            consoleMask.getChildren().add(new GuiParser(autoCompleteTextField).smartParse(currentUserInput));
        });

        autoCompleteTextField.setOnAction(this::onSubmitInput);

        consoleBox.getChildren().addAll(consoleMask, autoCompleteTextField);
    }

    private void onSubmitInput(ActionEvent actionEvent) {
        String userInput = autoCompleteTextField.getText().trim();
        new GuiExecutor(consoleScreen).executeAction(userInput);
        autoCompleteTextField.clear();
    }

    public void submitInput() {
        String userInput = console.getText().trim();
        new GuiExecutor(consoleScreen).executeAction(userInput);
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
            consoleMask.getChildren().add(new GuiParser(autoCompleteTextField).smartParse(currentUserInput));
            break;
        }
    }
}
