package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.exception.ProjException;
import seedu.storage.Storage;
import seedu.tasklist.TaskList;
import seedu.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.common.Constants.TAB;

public class ClearCommandTest {

    TaskList tasks = new TaskList();
    Ui ui = new Ui();
    Storage storage = new Storage();

    @Test
    public void checkClearCommand() {

        Command command = new ClearCommand();
        command.setCommandVariables(tasks, storage, ui);
        String output = "";
        try {
            command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }
    }
}
