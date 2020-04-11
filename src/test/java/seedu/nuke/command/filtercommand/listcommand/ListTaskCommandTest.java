package seedu.nuke.command.filtercommand.listcommand;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.TaskManager;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.directory.Root;
import seedu.nuke.directory.Task;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nuke.util.Message.MESSAGE_NO_TASKS_TO_SHOW;
import static seedu.nuke.util.Message.MESSAGE_SHOW_LIST;

public class ListTaskCommandTest {

    @Test
    public void testEmptyTaskList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        CommandResult result = Executor.executeCommand("lst");
        assertEquals(MESSAGE_NO_TASKS_TO_SHOW, result.getFeedbackToUser());

        result = Executor.executeCommand("lst lab");
        assertEquals(MESSAGE_NO_TASKS_TO_SHOW, result.getFeedbackToUser());
    }

    @Test
    public void testNormalTaskList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        CommandResult result = Executor.executeCommand("addm CS2113");
        result = Executor.executeCommand("addm CS3235");

        result = Executor.executeCommand("lst");
        assertEquals(MESSAGE_NO_TASKS_TO_SHOW, result.getFeedbackToUser());

        result = Executor.executeCommand("addt Big Lab Work -m CS2113 -c Lab -d 25/03/2020 0600");
        result = Executor.executeCommand("addt lab1 -m CS3235 -c Lab");
        result = Executor.executeCommand("addt watch webcast -m CS3235 -c Lecture");
        result = Executor.executeCommand("addt Tutorial -m CS2113 -c Tutorial");
        result = Executor.executeCommand("lst Tu -e");
        assertEquals(MESSAGE_NO_TASKS_TO_SHOW, result.getFeedbackToUser());

        result = Executor.executeCommand("lst watch");
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            tasks.add(ModuleManager.getTask("CS3235", "Lecture", "watch webcast"));
        } catch (ModuleManager.ModuleNotFoundException | CategoryManager.CategoryNotFoundException
                | TaskManager.TaskNotFoundException e) {
            System.out.println("Cannot find the task");
        }
        assertEquals(MESSAGE_SHOW_LIST, result.getFeedbackToUser());
        assertEquals(tasks, result.getShownList());
        assertEquals(DirectoryLevel.TASK, result.getDirectoryLevel());

        result = Executor.executeCommand("lst lab");
        tasks.clear();
        try {
            tasks.add(ModuleManager.getTask("CS2113", "Lab", "Big Lab Work"));
            tasks.add(ModuleManager.getTask("CS3235", "Lab", "lab1"));
        } catch (ModuleManager.ModuleNotFoundException | CategoryManager.CategoryNotFoundException
                | TaskManager.TaskNotFoundException e) {
            System.out.println("Cannot find the task");
        }
        assertEquals(MESSAGE_SHOW_LIST, result.getFeedbackToUser());
        assertEquals(tasks, result.getShownList());
        assertEquals(DirectoryLevel.TASK, result.getDirectoryLevel());
    }
}
