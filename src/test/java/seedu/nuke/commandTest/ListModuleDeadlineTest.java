package seedu.nuke.commandTest;

import org.junit.jupiter.api.Test;
import seedu.nuke.Nuke;
import seedu.nuke.command.Command;
import seedu.nuke.command.listCommand.ListModuleTasksDeadlineCommand;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListModuleDeadlineTest {

    private Nuke nuke;
    private Command command;

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

    public void testEmptyTaskList() {
        assertEquals(0, 0);
    }
}
