package seedu.nuke.command.filtercommand.listcommand;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.TaskManager;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.directory.Root;
import seedu.nuke.directory.Task;
import seedu.nuke.parser.Parser;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nuke.util.Message.MESSAGE_NO_TASKS_TO_SHOW;
import static seedu.nuke.util.Message.messageTaskSuccessfullyList;

/**
 * Junit test class to test ListAllTasksDeadlineCommand.
 */
public class ListTaskSortedCommandTest {

    @Test
    public void testTasksCounting() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());
        Command command = new ListTaskSortedCommand("", "", true);
        CommandResult result = Executor.execute(command);
        if (result.getDirectoryLevel() == DirectoryLevel.TASK) {
            assertEquals(ModuleManager.countAllTasks(), result.getShownList().size());
        } else {
            assertEquals(0, ModuleManager.countAllTasks());
        }

    }

    @Test
    public void testEmptyTaskList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());
        Command command = new Parser().parseCommand(ListTaskSortedCommand.COMMAND_WORD);
        CommandResult result = Executor.execute(command);
        if (ModuleManager.countAllTasks() == 0) {
            assertEquals(MESSAGE_NO_TASKS_TO_SHOW, result.getFeedbackToUser());
        }

        result = Executor.executeCommand("addm CS2113");
        result = Executor.executeCommand("lsts -m CS2113");
        assertEquals(MESSAGE_NO_TASKS_TO_SHOW, result.getFeedbackToUser());

        result = Executor.executeCommand("cd CS2113");
        result = Executor.executeCommand("lsts");
        assertEquals(MESSAGE_NO_TASKS_TO_SHOW, result.getFeedbackToUser());
    }

    @Test
    public void testNormalTaskList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        CommandResult result = Executor.executeCommand("addm CS2113");

        result = Executor.executeCommand("addt read -m CS2113 -c Lab -d 28-3-2020 2359");
        result = Executor.executeCommand("addt quiz -m CS2113 -c Assignment -d 28-3-2020 1200");
        result = Executor.executeCommand("addt write -m CS2113 -c Lab");

        ArrayList<Task> tasks = new ArrayList<>();
        try {
            tasks.add(ModuleManager.getTask("CS2113", "Assignment", "quiz"));
            tasks.add(ModuleManager.getTask("CS2113", "Lab", "read"));
            tasks.add(ModuleManager.getTask("CS2113", "Lab", "write"));
        } catch (ModuleManager.ModuleNotFoundException | CategoryManager.CategoryNotFoundException
                | TaskManager.TaskNotFoundException e) {
            System.out.println("Cannot find something");
        }

        result = Executor.executeCommand("lsts -m CS2113");
        assertEquals(messageTaskSuccessfullyList(3), result.getFeedbackToUser());
        assertEquals(DirectoryLevel.TASK, result.getDirectoryLevel());
        assertEquals(null, result.getHelpGuide());
        assertEquals(tasks, result.getShownList());

    }
}
