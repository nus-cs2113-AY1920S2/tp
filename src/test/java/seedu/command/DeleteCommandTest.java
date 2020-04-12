package seedu.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;
import seedu.exception.ProjException;
import seedu.storage.Storage;
import seedu.tasklist.TaskList;
import seedu.tasks.Task;
import seedu.ui.Ui;


public class DeleteCommandTest {
    @Test
    public void testDeleteDeadLine() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        String input = "add n/task t/11:15-13:15 d/2021-12-31";
        Command command = new AddCommand(input);

        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String input1 = "add n/homework i/cs2113_homework_team_project "
                + "t/00:20-23:00 d/2021-12-30 l/com2 r/reminder";
        command = new AddCommand(input1);

        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }
        String deleteInput = "delete 2";
        command = new DeleteCommand(deleteInput);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }
        String expected = "[TODO] Title: task | 2021-12-31 : 11:15 - 13:15";
        String output = tasks.getTask(0).toString();
        boolean isEqual = output.equals(expected);
        assertTrue(isEqual);
    }


    @Test
    public void testDeleteByCategory() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        String input = "add n/task t/11:15-13:15 d/2021-12-31";
        Command command = new AddCommand(input);

        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
            command.execute();
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String input1 = "add n/homework i/cs2113_homework_team_project "
                + "t/00:20-23:00 d/2021-12-30 c/deadline l/com2 r/reminder";
        command = new AddCommand(input1);

        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
            command.execute();
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }


        String input2 = "add n/team_work i/CS2106 "
                + "t/00:20-12:00 d/2042-02-10 l/com2 r/reminder";
        command = new AddCommand(input2);

        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }


        String deleteInput = "delete c/deadline";
        command = new DeleteCommand(deleteInput);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }
        String expected = "[TODO] Title: task | 2021-12-31 : 11:15 - 13:15";
        String output = tasks.getTask(0).toString();
        assertEquals(expected, output);

        String expected1 = "[TODO] Title: team_work | Description: CS2106  "
                + "| Reminder: reminder | 2042-02-10 : 00:20 - 12:00 ( com2 )";
        String output1 = tasks.getTask(5).toString();
        assertEquals(expected1, output1);
    }

    @Test
    public void testDeleteByDate() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        String input = "add n/task t/11:15-13:15 d/2021-12-31";
        Command command = new AddCommand(input);

        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String input1 = "add n/homework i/cs2113_homework_team_project "
                + "t/00:20-23:00 d/2021-12-30 l/com2 r/reminder";
        command = new AddCommand(input1);

        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String input2 = "add n/homework i/cs2113_homework_team_project "
                + "t/00:20-23:00 d/2021-12-31 l/com2 r/reminder";
        command = new AddCommand(input2);

        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String deleteInput = "delete d/2021-12-31";
        command = new DeleteCommand(deleteInput);
        command.setCommandVariables(tasks, storage, ui);

        try {
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String expected = "[TODO] Title: homework | Description:"
                + " cs2113_homework_team_project  | Reminder: reminder"
                + " | 2021-12-30 : 00:20 - 23:00 ( com2 )";

        for (Task task : tasks.getList()) {

            assertEquals(expected, task.toString());
        }
    }

    @Test
    public void testDeleteByDateTime() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        String input = "add n/task t/11:15-13:15 d/2021-12-31";
        Command command = new AddCommand(input);

        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String input1 = "add n/homework i/cs2113_homework_team_project "
                + "t/00:20-23:00 d/2021-12-30 l/com2 r/reminder";
        command = new AddCommand(input1);

        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String input2 = "add n/homework i/cs2113_homework_team_project "
                + "t/00:20-23:00 d/2021-12-31 l/com2 r/reminder";
        command = new AddCommand(input2);

        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String deleteInput = "delete t/00:20-23:00";
        command = new DeleteCommand(deleteInput);
        command.setCommandVariables(tasks, storage, ui);

        try {
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String expected = "[TODO] Title: task | 2021-12-31 : 11:15 - 13:15";

        for (Task task : tasks.getList()) {
            assertEquals(expected, task.toString());
        }
    }

    @Test
    public void testDeleteByCategoryDateTime() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        String input1 = "add n/homework i/cs2106_project "
                + "t/00:20-23:00 d/2021-12-31 l/com2 r/reminder c/deadline";
        Command command = new AddCommand(input1);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String input2 = "add n/homework i/cs2113_homework_team_project "
                + "t/00:20-23:00 d/2021-12-31 l/com2 r/reminder";
        command = new AddCommand(input2);

        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String deleteInput = "delete c/deadline t/00:20-23:00 d/2021-12-31";
        command = new DeleteCommand(deleteInput);
        command.setCommandVariables(tasks, storage, ui);

        try {
            command.execute();
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String expected = "[TODO] Title: homework | Description:"
                + " cs2113_homework_team_project  | Reminder: reminder"
                + " | 2021-12-31 : 00:20 - 23:00 ( com2 )";

        for (Task task : tasks.getList()) {
            assertEquals(expected, task.toString());
        }
    }

    @Test
    public void testDeleteZeroTask() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        String deleteInput = "delete 2";
        Command command = new DeleteCommand(deleteInput);
        command.setCommandVariables(tasks, storage, ui);
        String output = "";

        try {
            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }
        String expected = "  [Alert][delete] There is nothing to delete, the list is empty\n";
        assertEquals(expected, output);
    }
}
