package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.exception.ProjException;
import seedu.storage.Storage;
import seedu.tasklist.TaskList;
import seedu.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.common.Constants.TAB;

public class AddCommandTest {

    @Test
    public void testAddDeadlineYeapYear() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        String input = "add n/task t/11:15-13:15 d/2024-02-29";
        String expected = "[TODO] Title: task | 2024-02-29 : 11:15 - 13:15";

        AddCommand command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }
        String output = tasks.getTask(0).toString();
        boolean isEqual = output.equals(expected);
        assertTrue(isEqual);
    }

    @Test
    public void testAddDeadline() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        String input;
        input = "add n/homework i/cs2113_home_work "
                + "t/00:20-23:00 d/2021-12-31 l/com2 "
                + "r/reminder";

        String expected;
        expected = "[TODO] Title: homework "
                + "| Description: cs2113_home_work  | "
                + "Reminder: reminder | 2021-12-31 : "
                + "00:20 - 23:00 ( com2 )";

        AddCommand command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String output = tasks.getTask(0).toString();
        assertEquals(expected, output);
    }

    @Test
    public void addCommand_noTitle_exceptionThrown() {

        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        try {
            String firstInput = "add";
            AddCommand firstCommand = new AddCommand(firstInput);
            firstCommand.setCommandVariables(tasks, storage, ui);
            firstCommand.execute();

        } catch (ProjException e) {
            String expected = TAB + "[Error][Add] Please input a title for the task.\n";
            assertEquals(expected, e.getMessage());
        }
    }


}
