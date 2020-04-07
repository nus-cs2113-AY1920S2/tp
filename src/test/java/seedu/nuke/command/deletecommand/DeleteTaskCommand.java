package seedu.nuke.command.deletecommand;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.TaskManager;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.directory.Root;
import seedu.nuke.directory.Task;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nuke.util.Message.MESSAGE_DELETE_ABORTED;
import static seedu.nuke.util.Message.MESSAGE_DELETE_TASK_SUCCESS;
import static seedu.nuke.util.Message.MESSAGE_INVALID_DELETE_INDICES;
import static seedu.nuke.util.Message.MESSAGE_NO_TASKS_FOUND;
import static seedu.nuke.util.Message.messageConfirmDeleteTask;
import static seedu.nuke.util.Message.messagePromptDeleteTaskIndices;

public class DeleteTaskCommand {

    @Test
    public void testEmptyModuleList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        CommandResult result = Executor.executeCommand("delt");
        assertEquals(MESSAGE_NO_TASKS_FOUND, result.getFeedbackToUser());

        result = Executor.executeCommand("delt read");
        assertEquals(MESSAGE_NO_TASKS_FOUND, result.getFeedbackToUser());

        result = Executor.executeCommand("delt read -m CS2113");
        assertEquals(MESSAGE_NO_TASKS_FOUND, result.getFeedbackToUser());
    }

    @Test
    public void testEmptyTaskList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        CommandResult result = Executor.executeCommand("addm CS2113");
        result = Executor.executeCommand("addm CS3235");

        result = Executor.executeCommand("delt");
        assertEquals(MESSAGE_NO_TASKS_FOUND, result.getFeedbackToUser());

        result = Executor.executeCommand("delt read -e");
        assertEquals(MESSAGE_NO_TASKS_FOUND, result.getFeedbackToUser());

        result = Executor.executeCommand("delt read -m CS2113");
        assertEquals(MESSAGE_NO_TASKS_FOUND, result.getFeedbackToUser());
    }

    @Test
    public void testNormalTaskList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        CommandResult result = Executor.executeCommand("addm CS2113");
        result = Executor.executeCommand("addm CS3235");
        result = Executor.executeCommand("addt read -m CS2113 -c Lab");
        result = Executor.executeCommand("addt read handouts -m CS3235 -c Lecture");
        result = Executor.executeCommand("addt quiz -m CS2113 -c Tutorial");
        result = Executor.executeCommand("addt assignment2 -m CS3235 -c Assignment");

        result = Executor.executeCommand("delt");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.addAll(ModuleManager.filterExact("", "", ""));
        Comparator<Task> sortByModule =
                Comparator.comparing(task -> task.getParent().getParent().getModuleCode());
        Comparator<Task> sortByCategory =
                Comparator.comparing(task -> task.getParent().getCategoryName());
        Comparator<Task> sortByTask =
                Comparator.comparing(Task::getDescription);
        Comparator<Task> sortByDeadline =
                Comparator.comparing(task -> task.getDeadline().getDateTimeInSortFormat());
        Comparator<Task> sortByPriority =
                Comparator.comparing(Task::getPriority, Comparator.reverseOrder());
        tasks.sort(sortByModule.thenComparing(sortByCategory).thenComparing(sortByTask));
        assertEquals(messagePromptDeleteTaskIndices(new ArrayList<>(tasks)), result.getFeedbackToUser());
        result = Executor.executeCommand("n");
        assertEquals(MESSAGE_INVALID_DELETE_INDICES, result.getFeedbackToUser());

        result = Executor.executeCommand("delt read");
        tasks.clear();
        try {
            tasks.add(ModuleManager.getTask("CS2113", "Lab", "read"));
            tasks.add(ModuleManager.getTask("CS3235", "Lecture", "read handouts"));
        } catch (ModuleManager.ModuleNotFoundException | CategoryManager.CategoryNotFoundException
                | TaskManager.TaskNotFoundException e) {
            System.out.println("Cannot find the something");
        }
        assertEquals(messagePromptDeleteTaskIndices(new ArrayList<>(tasks)), result.getFeedbackToUser());
        result = Executor.executeCommand("1");
        assertEquals("Confirm delete these tasks?\nread\n", result.getFeedbackToUser());
        result = Executor.executeCommand("n");
        assertEquals(MESSAGE_DELETE_ABORTED, result.getFeedbackToUser());

        result = Executor.executeCommand("delt read -e");
        try {
            assertEquals(messageConfirmDeleteTask(ModuleManager.getTask("CS2113", "Lab", "read")),
                    result.getFeedbackToUser());
        } catch (ModuleManager.ModuleNotFoundException | CategoryManager.CategoryNotFoundException
                | TaskManager.TaskNotFoundException e) {
            System.out.println("Cannot find the something");
        }
        result = Executor.executeCommand("y");
        assertEquals(MESSAGE_DELETE_TASK_SUCCESS, result.getFeedbackToUser());
    }
}
