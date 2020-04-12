package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.exception.ProjException;
import seedu.storage.Storage;
import seedu.tasklist.TaskList;
import seedu.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.common.Constants.TAB;

public class FindCommandTest {

    TaskList tasks = new TaskList();
    Ui ui = new Ui();
    Storage storage = new Storage();

    @Test
    public void testFindTaskInTitle() {

        String input = "add n/testForfInd t/11:15-13:15 d/2024-02-29";
        Command command = new AddCommand(input);

        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        command = new FindCommand("find find");
        command.setCommandVariables(tasks, storage, ui);
        String output = "";
        try {
            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }
        String expectedOutput = TAB + "Found the followings tasks:\n" + TAB + TAB
                + "1. [TODO] Title: testForfInd | 2024-02-29 : 11:15 - 13:15\n";
        assertEquals(expectedOutput,output);
    }

    @Test
    public void testFindTaskInDescription() {

        String input = "add n/test i/FinDDes t/11:15-13:15 d/2024-02-29";
        Command command = new AddCommand(input);

        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        command = new FindCommand("find dEs");
        command.setCommandVariables(tasks, storage, ui);
        String output = "";
        try {
            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }
        String expectedOutput = TAB + "Found the followings tasks:\n" + TAB + TAB
                + "1. [TODO] Title: test | Description: FinDDes  | 2024-02-29 : 11:15 - 13:15\n";
        assertEquals(expectedOutput,output);
    }

    @Test
    public void testFindTaskInLocation() {

        String input = "add n/test l/NUScom2";
        Command command = new AddCommand(input);

        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        command = new FindCommand("find COM");
        command.setCommandVariables(tasks, storage, ui);
        String output = "";
        try {
            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }
        String expectedOutput = TAB + "Found the followings tasks:\n" + TAB + TAB
                + "1. [TODO] Title: test | Location: NUScom2\n";

        assertEquals(expectedOutput,output);
    }

    @Test
    public void findTask_noPatternInput_outputError() {

        String input = "add n/testForfInd";
        Command command = new AddCommand(input);

        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        command = new FindCommand("find");
        command.setCommandVariables(tasks, storage, ui);
        String output = "";
        try {
            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }
        String expectedOutput = TAB + "[Error][find] Please enter a pattern to look for \n";
        assertEquals(output,expectedOutput);
    }

    @Test
    public void findTask_noFindTask_outputPromptMessage() {

        String input = "add n/testForfInd";
        Command command = new AddCommand(input);

        command.setCommandVariables(tasks, storage, ui);
        try {
            command.execute();
        } catch (ProjException e) {
            assertTrue(false);
        }

        command = new FindCommand("find nothing");
        command.setCommandVariables(tasks, storage, ui);
        String output = "";
        try {
            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }
        String expectedOutput = TAB + "[Alert][find] No tasks found with \"nothing\" in "
                + "their title, description, or location\n";
        assertEquals(output,expectedOutput);
    }


}
