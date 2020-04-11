package notes.modules;

import notes.modules.command.AddCommand;
import notes.modules.command.Command;
import notes.modules.command.CommandStack;
import notes.modules.command.ExitCommand;
import notes.modules.parser.Parser;
import ui.Constants;
import ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

//@@author terrytay
public class ModuleManager {
    private String code;
    private ArrayList<String> messages;
    private CommandStack commandStack;
    private Ui ui;

    /**
     * Manages the module e.g. overall commands and control.
     * @param code module code
     * @param messages the notes in the module
     * @param ui allows for interaction with the user
     * @throws Exception for commandStack
     */
    public ModuleManager(String code, ArrayList<String> messages, Ui ui) throws Exception {
        this.code = code;
        this.messages = messages;
        this.ui = ui;
        commandStack = new CommandStack(ui);
        runInstance();
    }

    private void runInstance() throws Exception {
        this.ui.printMessage(String.format("Notes for %s", code));
        Command command;
        showMenu();
        boolean isExit = false;
        do {
            String userInput = ui.getUserIn();
            command = new Parser().parseCommand(userInput, this, ui);
            if (command != null) {
                executeCommand(command);
            }
            if (ExitCommand.isExit(command)) {
                isExit = true;
            }
        } while (!isExit);
        ui.printLine();
        ui.printMessage(Constants.MODULE_BYE_MSG);
    }

    /**
     * Takes the command and execute. Depending on whether it's add,
     * redo or undo or others, it will run differently.
     * @param command the command to be executed
     */
    public void executeCommand(Command command) {
        if (command instanceof AddCommand) {
            switch (((AddCommand) command).getUserCommandType()) {
            case Constants.NOTES_PARSER_ADD:
                commandStack.execute(command);
                break;
            case Constants.NOTES_PARSER_UNDO:
                commandStack.undo();
                break;
            case Constants.NOTES_PARSER_REDO:
                commandStack.redo();
                break;
            default:
                break;
            }
        } else {
            command.execute();
        }

    }

    private void showMenu() {
        this.ui.printLine();
        this.ui.printMessage(Constants.MODULE_MANAGER_1);
        this.ui.printMessage(Constants.MODULE_MANAGER_2);
        this.ui.printMessage(Constants.MODULE_MANAGER_3);
        this.ui.printMessage(Constants.MODULE_MANAGER_4);
        this.ui.printMessage(Constants.MODULE_MANAGER_5);
        this.ui.printLine();
    }

    /**
     * to add by terry.
     * @param message to add by terry.
     */
    public void addMessage(String message) {
        messages.add(message);
        this.ui.printLine();
        this.ui.printMessage(Constants.ACTION_SUCCESS);
        this.ui.printLine();
    }

    /**
     * To add by terry.
     * @return to add by terry.
     */
    public ArrayList<String> getMessages() {
        return this.messages;
    }

    /**
     * To add by terry.
     * @param message to be add by terry.
     */
    public void removeMessage(String message) {
        messages.remove(message);
        this.ui.printLine();
        this.ui.printMessage(Constants.ACTION_SUCCESS);
        this.ui.printLine();
    }
}
