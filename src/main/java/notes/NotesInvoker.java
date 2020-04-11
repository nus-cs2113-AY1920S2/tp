package notes;

import ui.Constants;
import ui.Ui;

import java.io.IOException;

import static ui.Constants.SPACE2;
import static ui.Constants.TAB;
import static ui.Constants.SPACE;


//@@author terrytay
public class NotesInvoker {

    /**
     * Constructor to invoke Notes app.
     * @param ui allows for interaction with the user
     * @throws IOException exception for module import/export
     */
    public NotesInvoker(Ui ui) throws Exception {
        Notes notes = new Notes(ui);
        ui.printLine();
        ui.printMessage(Constants.NOTES_WELCOME_MESSAGE);
        ui.printLine();
        displayMenu(ui);
        ui.printLine();
        boolean isExit = false;
        String choice;

        do {
            try {
                String userInput = ui.getUserIn();
                ui.printLine();
                choice = userInput.split(SPACE2)[0];
                String code;

                switch (choice) {
                case Constants.NOTES_ADD:
                    if (!isIncorrectLength(userInput, 2)) {
                        code = userInput.split(SPACE2)[1];
                        notes.createModule(code);
                    } else {
                        ui.printMessage(Constants.NOTES_EXCEPTION_MSG);
                    }
                    break;
                case Constants.NOTES_REMOVE:
                    if (!isIncorrectLength(userInput, 2)) {
                        code = userInput.split(SPACE2)[1];
                        notes.deleteModule(code);
                    } else {
                        ui.printMessage(Constants.NOTES_EXCEPTION_MSG);
                    }
                    break;
                case Constants.NOTES_ENTER:
                    if (!isIncorrectLength(userInput, 2)) {
                        code = userInput.split(SPACE2)[1];
                        notes.enterModule(code);
                    } else {
                        ui.printMessage(Constants.NOTES_EXCEPTION_MSG);
                    }
                    break;
                case Constants.NOTES_LIST:
                    if (!isIncorrectLength(userInput, 1)) {
                        notes.listModules();
                    } else {
                        ui.printMessage(Constants.NOTES_EXCEPTION_MSG);
                    }
                    break;
                case Constants.NOTES_BYE:
                    if (!isIncorrectLength(userInput, 1)) {
                        notes.exportModules();
                        isExit = true;
                    } else {
                        ui.printMessage(Constants.NOTES_EXCEPTION_MSG);
                    }
                    break;
                case Constants.NOTES_HELP:
                    if (!isIncorrectLength(userInput, 1)) {
                        displayMenu(ui);
                    } else {
                        ui.printMessage(Constants.NOTES_EXCEPTION_MSG);
                    }
                    break;
                default:
                    ui.printMessage(Constants.INVALID_NOTES_COMMAND_MESSAGE);
                    break;
                }
                if (!isExit) {
                    ui.printLine();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.printLine();
                ui.printMessage(Constants.NOTES_EXCEPTION_MSG);
                ui.printLine();
            }
        } while (!isExit);
        ui.printMessage(Constants.NOTES_BYE_MSG);
        ui.printLine();
    }

    private void displayMenu(Ui ui) {
        ui.printOut(TAB + SPACE + Constants.ADD_MODULE_MESSAGE, true);
        ui.printOut(TAB + SPACE + Constants.REMOVE_MODULE_MESSAGE, true);
        ui.printOut(TAB + SPACE + Constants.ENTER_MODULE_MESSAGE, true);
        ui.printOut(TAB + SPACE + Constants.LIST_MODULE_MESSAGE, true);
        ui.printOut(TAB + SPACE + Constants.EXIT_MODULE_MESSAGE, true);
        ui.printOut(TAB + SPACE + Constants.HELP_MODULE_MESSAGE, true);
    }

    private boolean isIncorrectLength(String userInput, int requiredLength) {
        if (userInput.split(SPACE2).length != requiredLength) {
            return true;
        }
        return false;
    }
}
