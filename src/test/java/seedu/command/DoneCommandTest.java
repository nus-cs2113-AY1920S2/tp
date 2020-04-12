package seedu.command;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.common.Constants.NEW_LINE;
import static seedu.common.Constants.TAB;

import org.junit.jupiter.api.Test;
import seedu.exception.ProjException;
import seedu.storage.Storage;
import seedu.tasklist.TaskList;
import seedu.ui.Ui;

class DoneCommandTest {

    TaskList tasks = new TaskList();
    Ui ui = new Ui();
    Storage storage = new Storage();

    @Test
    void checkNegativeNumberInput() {

        Command command = new DoneCommand("-1");
        command.setCommandVariables(tasks, storage, ui);
        String output = "";
        try {
            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }
        String expected = TAB + "[Error][Done] Please insert a valid positive number\n";
        assertEquals(expected, output);
    }

    @Test
    void checkValidInput() {

        String input = "add n/task t/11:15-13:15 d/2024-02-29";
        Command command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }

        command = new DoneCommand("0");
        command.setCommandVariables(tasks, storage, ui);
        String output = "";
        try {
            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }
        String expected = TAB + "[Error][Done] List numbering starts from 1\n";
        assertEquals(expected, output);
    }

    @Test
    void checkEmptyList() {

        Command command = new DoneCommand("1");
        command.setCommandVariables(tasks, storage, ui);
        String output = "";
        try {
            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }
        String expected = TAB + "[Alert][Done]: There are no tasks to mark completed!\n";
        assertEquals(expected, output);
    }

    @Test
    void checkFilledList() {

        String input = "add n/task1 i/desc1";
        Command command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }

        command = new DoneCommand("1");
        command.setCommandVariables(tasks, storage, ui);
        String output = "";
        try {
            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }
        String expected = TAB + "Task marked as done: " + NEW_LINE + TAB + TAB
                + "[Y] [TODO] Title: task1 | Description: desc1\n";
        assertEquals(expected, output);
    }

    @Test
    void checkInvalidIndexFilledList() {
        String input = "add n/task2 i/desc2";
        Command command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }

        command = new DoneCommand("2");
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }
    }

    @Test
    void checkSpellIndexFilledList() {
        String input = "add n/task2 i/desc2";
        Command command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }

        command = new DoneCommand("one");
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute().getFeedback();
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void checkAlreadyDoneList() {
        String input = "add n/task2 i/desc2";
        Command command = new AddCommand(input);
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }

        command = new DoneCommand("1");
        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute().getFeedback();
        } catch (Exception e) {
            assertTrue(false);
        }

        command = new DoneCommand("1");
        command.setCommandVariables(tasks, storage, ui);
        String output = "";
        try {
            output = command.execute().getFeedback();
        } catch (Exception e) {
            assertTrue(false);
        }

        String expected = TAB + "[Alert][Done]: Task is already done\n";
        assertEquals(expected, output);
    }
}