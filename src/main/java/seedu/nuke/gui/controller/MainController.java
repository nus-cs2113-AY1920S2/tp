package seedu.nuke.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.gui.component.AutoCompleteTextField;
import seedu.nuke.gui.component.SyntaxConsole;
import seedu.nuke.gui.io.GuiExecutor;
import seedu.nuke.gui.io.GuiParser;
import seedu.nuke.gui.util.TextUtil;

import java.net.URL;
import java.util.ResourceBundle;

import static seedu.nuke.util.Message.*;


public class MainController implements Initializable {
    @FXML
    private TextField spareConsole;

    @FXML
    private TextFlow consoleScreen;

    @FXML
    private ScrollPane consoleScreenScrollPane;

    @FXML
    private VBox consoleBox;

    @FXML
    private Label directoryPathLabel;

    private SyntaxConsole consoleMask;

    private AutoCompleteTextField console;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Auto scroll-down
        consoleScreenScrollPane.vvalueProperty().bind(consoleScreen.heightProperty());
        consoleScreen.setStyle("-fx-font-family: Consolas; -fx-font-size: 12pt");

        consoleMask = new SyntaxConsole();
        console = new AutoCompleteTextField();
        directoryPathLabel.setTextFill(Color.BLACK);

        console.addEventFilter(KeyEvent.ANY, this::onKeyType);

        console.textProperty().addListener((observable, oldValue, newValue) -> {
            String currentUserInput = console.getText();
            consoleMask.getChildren().clear();
            consoleMask.getChildren().add(new GuiParser(console).smartParse(currentUserInput));
        });

        console.setOnAction(this::onSubmitInput);

        consoleBox.getChildren().addAll(consoleMask, console);

        refreshScene();
        welcomeUser();
    }

    private void welcomeUser() {
        Text logo = TextUtil.createText(MESSAGE_LOGO, Color.MAGENTA);
        Text welcomeMessage = TextUtil.createText(String.format("%s\n%s\n\n", MESSAGE_WELCOME_1, MESSAGE_WELCOME_2), Color.BLUE);
        Text divider = TextUtil.createText(DIVIDER + "\n");
        consoleScreen.getChildren().addAll(logo, divider, welcomeMessage);
    }

    private void onSubmitInput(ActionEvent actionEvent) {
        String userInput = console.getText().trim();
        new GuiExecutor(consoleScreen).executeAction(userInput);
        refreshScene();
    }

    public void submitInput() {
        String userInput = spareConsole.getText().trim();
        new GuiExecutor(consoleScreen).executeAction(userInput);
        spareConsole.clear();
    }


    public void onKeyType(KeyEvent keyEvent) {
        // Remove unnecessary action
        if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED) {
            keyEvent.consume();
            return;
        }

        switch (keyEvent.getCode()) {
        case UP:
            keyEvent.consume();
            System.out.println("View last command.");
            break;
        case DOWN:
            System.out.println("View earlier command.");
            break;
        case TAB:
            keyEvent.consume();
            consoleMask.showCommandFormat(console.getText());
            break;
        default:
            break;
        }
    }

    private void refreshScene() {
        console.clear();
        directoryPathLabel.setText(DirectoryTraverser.getFullPath());
    }
}
