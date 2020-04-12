package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.exception.ProjException;
import seedu.storage.Storage;
import seedu.tasklist.TaskList;
import seedu.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.common.Constants.TAB;

public class CalendarCommandTest {

    TaskList tasks = new TaskList();
    Ui ui = new Ui();
    Storage storage = new Storage();

    @Test
    void checkNoInput() {
        String input = "add n/i i/n d/2020-05-30";
        Command command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute().getFeedback();
        } catch (Exception e) {
            assertTrue(false);
        }

        command = new CalendarCommand(null);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute().getFeedback();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void checkValidInput() {
        String input = "add n/i i/n d/2020-05-01";
        Command command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute().getFeedback();
        } catch (Exception e) {
            assertTrue(false);
        }

        command = new CalendarCommand("5");
        command.setCommandVariables(tasks, storage, ui);
        try {
            String output = command.execute().getFeedback();
            assertNotNull(output);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void checkExceededMonthInput() {
        Command command = new CalendarCommand("13");
        command.setCommandVariables(tasks, storage, ui);
        try {
            String output = command.execute().getFeedback();
            assertNotNull(output);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

}
