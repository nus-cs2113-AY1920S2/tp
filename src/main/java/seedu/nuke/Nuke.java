package seedu.nuke;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import seedu.nuke.command.Command;
import seedu.nuke.ui.TextUi;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;
import static seedu.nuke.ui.TextUi.printDivider;

public class Nuke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        TextUi.clearScreen();
        TextUi.displayLogo();
        new Nuke().run();
    }

    private void run() {
        TextUi.showWelcomeMessage();
        runCommandLoopUntilExitCommand();
    }

    private void runCommandLoopUntilExitCommand() {
        Command command;
        Scanner scanner = new Scanner(System.in);
        do {
            String userCommandText = scanner.nextLine();
            printDivider();
            command = new Parser().parseCommand(taskManager, userCommandText);
            executeCommand(command);
        } while (!ExitCommand.isExit(command));
    }

    /**
     * initialize the taskManager system, execute command and save the list to the file
     * @param command the parsed Command object
     * @return commandResult that contains the execute output information
     */
    private CommandResult executeCommand(Command command) throws StorageOperationException {
        try {
            // supplies the data the command will operate on.
            // if there is no file to load or the file is empty, setData will initialize a new taskManager system
            command.setData(taskManager);
            // Execute according to the command itself
            commandResult = command.execute();
            // save the taskManager to a file
            taskManager.getStorager().save(taskManager);
            StorageFile.saveJson(taskManager);
        } catch (Exception ex) {
            // the out layer exception handler
            System.out.println(ex);
        }
        return commandResult;
    }
}
