package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.storage.Storage;
import seedu.tasklist.TaskList;
import seedu.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.common.Constants.TAB;

public class ExitCommandTest {

    TaskList tasks = new TaskList();
    Ui ui = new Ui();
    Storage storage = new Storage();

    @Test
    void checkValidCommand() {

        String input = "add n/i i/n d/2020-05-10";
        Command command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute().getFeedback();
        } catch (Exception e) {
            assertTrue(false);
        }

        command = new ExitCommand();
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute().getFeedback();
        } catch (Exception e) {
            assertTrue(false);
        }
    }
}
