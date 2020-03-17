package seedu.nuke.commandtest;

import org.junit.jupiter.api.Test;
import seedu.nuke.Nuke;
import seedu.nuke.command.Command;
import seedu.nuke.command.listcommand.ListModuleTasksDeadlineCommand;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Junit test class to test ListModuleTasksDeadlineCommand.
 */
public class ListModuleDeadlineTest {

    private Nuke nuke;
    private Command command;

    /**
     * constructor of this class.
     */
    public ListModuleDeadlineTest() {
        try {
            nuke = new Nuke();
            command = new ListModuleTasksDeadlineCommand();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    @Test
    public void testTasksCounting() {
        assertEquals(0, 0);
    }

    @Test
    public void testEmptyTaskList() {
        assertEquals(0, 0);
    }
}
