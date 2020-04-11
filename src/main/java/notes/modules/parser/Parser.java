package notes.modules.parser;

import exception.NotesInvalidAddMessageException;
import notes.modules.ModuleManager;
import notes.modules.command.AddCommand;
import notes.modules.command.Command;
import notes.modules.command.ExitCommand;
import notes.modules.command.ListCommand;
import ui.Constants;
import ui.Ui;

import static ui.Constants.SPACE2;

//@@author terrytay
public class Parser {
    private String userCommandText;
    private String userInput;

    /**
     * Takes in the user input and parse it into understandable
     * commands and return the command needed.
     *
     * @param moduleManager ModuleManager object used to manage modules.
     * @param userInput input from the user
     * @param ui allows for interaction with the user
     * @return method to call Command of specific type
     * @throws Exception for invalid commands
     */
    public Command parseCommand(String userInput, ModuleManager moduleManager, Ui ui) throws Exception {
        try {
            this.userInput = userInput;
            userCommandText = userInput.split(Constants.SPACE2)[0];

            switch (userCommandText) {
            case Constants.NOTES_PARSER_REDO:
            case Constants.NOTES_PARSER_UNDO:
                if (!isIncorrectLength(userInput, 1)) {
                    return new AddCommand(moduleManager, userCommandText);
                } else {
                    ui.printLine();
                    ui.printMessage(Constants.NOTES_PARSER_INVALID_INPUT_2);
                    ui.printLine();
                    return null;
                }
            case Constants.NOTES_PARSER_ADD:
                if (hasMinimumLength(userInput, 2)) {
                    String message = userInput.split(userCommandText + Constants.SPACE)[1];
                    if (message.equals(Constants.NOTES_KEY_WORD)) {
                        ui.printLine();
                        ui.printMessage(Constants.NOTES_KEY_WORD_ERROR);
                        ui.printLine();
                        return null;
                    } else {
                        return new AddCommand(moduleManager, message, userCommandText);
                    }
                } else {
                    ui.printLine();
                    ui.printMessage(Constants.NOTES_PARSER_INVALID_INPUT);
                    ui.printLine();
                    return null;
                }
            case Constants.NOTES_PARSER_LIST:
                if (!isIncorrectLength(userInput, 1)) {
                    return new ListCommand(moduleManager.getMessages(), ui);
                } else {
                    ui.printLine();
                    ui.printMessage(Constants.NOTES_PARSER_INVALID_INPUT_2);
                    ui.printLine();
                    return null;
                }
            case Constants.NOTES_PARSER_BACK:
                if (!isIncorrectLength(userInput, 1)) {
                    return new ExitCommand();
                } else {
                    ui.printLine();
                    ui.printMessage(Constants.NOTES_PARSER_INVALID_INPUT_2);
                    ui.printLine();
                    return null;
                }
            default:
                throw new Exception();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printLine();
            ui.printMessage(Constants.NOTES_PARSER_INVALID_INPUT);
            ui.printLine();
        } catch (Exception e) {
            ui.printLine();
            ui.printMessage(Constants.NOTES_PARSER_INVALID_INPUT_2);
            ui.printLine();
        }
        return null;
    }

    private boolean isIncorrectLength(String userInput, int requiredLength) {
        if (userInput.split(SPACE2).length != requiredLength) {
            return true;
        }
        return false;
    }

    private boolean hasMinimumLength(String userInput, int minimumLength) {
        if (userInput.split(SPACE2).length >= minimumLength) {
            return true;
        }
        return false;
    }
}
