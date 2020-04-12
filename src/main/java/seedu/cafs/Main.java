package seedu.cafs;

import seedu.command.Command;
import seedu.command.CommandResult;
import seedu.common.Messages;
import seedu.exception.ProjException;
import seedu.parser.Parser;
import seedu.storage.Storage;
import seedu.tasklist.TaskList;
import seedu.ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.common.Constants.TAB;


/**
 * Entry point of the CAFS app.
 * Initializes the application and starts the interaction with the user.
 */
public class Main {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static Logger logger = Logger.getLogger("Foo");

    /**
     * Main constructor to set up required classes and check for data file.
     */
    public Main() {

        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();

        storage.checkFolderPath();

        if (storage.checkFileExists()) {
            // log a message at INFO level
            // TODO Redirect log messages to log file
            // logger.log(Level.INFO, "Attempting to read from data file");
            storage.loadFromFile(tasks);
        }

    }

    public void run() {
        ui.showWelcome();
        runCommandLoopUntilExitCommand();
    }

    /**
     * Reads the user command and executes it, until the user issues the exit command.
     */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.readCommand();
            command = new Parser().parseCommand(userCommandText);
            command.setCommandVariables(tasks, storage, ui);

            try {
                CommandResult result = command.execute();
                ui.showResultToUser(result);
            } catch (ProjException e) {
                ui.showError(e.getMessage());
            } catch (DateTimeParseException e) {
                ui.showError(Messages.MESSAGE_DATETIME_ERROR);
            } catch (NumberFormatException e) {
                if (e.getMessage().equals(Messages.MESSAGE_PAST_DATE_Error)) {
                    ui.showError(TAB + Messages.MESSAGE_PAST_DATE_Error);
                } else if (e.getMessage().equals(Messages.MESSAGE_DAY_OR_WEEK)) {
                    ui.showError(TAB + Messages.MESSAGE_DAY_OR_WEEK);
                } else {
                    ui.showError(TAB + Messages.MESSAGE_GENERAL_ERROR_MESSAGE);
                }
            }

        } while (!command.isExit());
        logger.log(Level.INFO, "end of processing");
    }


    public static void main(String[] args) {
        new Main().run();
    }
}