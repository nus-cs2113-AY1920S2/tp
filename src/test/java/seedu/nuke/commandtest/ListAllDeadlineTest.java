package seedu.nuke.commandtest;

import org.junit.jupiter.api.Test;
import seedu.nuke.Nuke;
import seedu.nuke.command.Command;
<<<<<<< HEAD
import seedu.nuke.command.filterCommand.listcommand.ListAllTasksDeadlineCommand;
=======
import seedu.nuke.parser.Parser;
>>>>>>> b0ace6250f9ac63d77fc89fc571ae602a0d0b04d

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Junit test class to test ListAllTasksDeadlineCommand.
 */
public class ListAllDeadlineTest {

    @Test
    public void testTasksCounting() {
        Nuke nuke;
        Command command;
        try {
            nuke = new Nuke();
            command = new ListAllTasksDeadlineCommand();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        nuke.executeCommand(command);
        if (nuke.getCommandResult().isShowTasks()) {
            assertEquals(nuke.getModuleManager().countAllTasks(), nuke.getCommandResult().getShownList().size());
        } else {
            assertEquals(0, nuke.getModuleManager().countAllTasks());
        }

    }

    @Test
    public void testEmptyTaskList() {
        Nuke nuke;
        try {
            nuke = new Nuke();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        nuke.executeCommand(new Parser().parseCommand("ls", nuke.getModuleManager()));
        if (nuke.getModuleManager().countAllTasks() == 0) {
            assertEquals(true, nuke.getCommandResult().isShowTasks());
            //assertEquals(MESSAGE_NO_TASK_IN_LIST, new ListAllTasksDeadlineCommand().execute().getFeedbackToUser());
        }
    }
}
