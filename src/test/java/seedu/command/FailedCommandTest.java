package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.common.Constants;
import seedu.exception.ProjException;
import seedu.storage.Storage;
import seedu.tasklist.TaskList;
import seedu.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.command.ExitCommand.ERROR_FEEDBACK;
import static seedu.parser.Parser.COMMAND_LENGTH;

public class FailedCommandTest {

    TaskList tasks = new TaskList();
    Ui ui = new Ui();
    Storage storage = new Storage();

    @Test
    public void checkFailedCommand() {

        String userCommand = "done ";
        String[] commandSections = userCommand.split(" ");

        Command command = new FailedCommand(DoneCommand.COMMAND_WORD,
                commandSections.length - COMMAND_LENGTH, DoneCommand.ARGUMENT_COUNT);
        command.setCommandVariables(tasks, storage, ui);
        String output = "";
        try {
            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }
        String expectedOutput = "  [Error][done]: Wrong number of arguments,\n"
                + "  Received: 0 Expected: 1\n";
        assertEquals(output,expectedOutput);
    }

    @Test
    public void checkAnotherFailedCommand() {

        String userCommand = "bye 1 ";
        String[] commandSections = userCommand.split(" ");

        Command command = new FailedCommand(ExitCommand.COMMAND_WORD, ExitCommand.ERROR_FEEDBACK);
        command.setCommandVariables(tasks, storage, ui);
        String output = "";
        try {
            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }
        String expectedOutput = "  [Error][bye]: No arguments required" + Constants.NEW_LINE;
        assertEquals(output,expectedOutput);
    }

}
