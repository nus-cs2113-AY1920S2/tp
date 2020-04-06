package seedu.nuke.command.addcommand;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleLoader;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.TaskManager;
import seedu.nuke.data.storage.StoragePath;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_CATEGORY_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_DUPLICATE_TASK;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.nuke.util.Message.MESSAGE_TASK_EXCEED_LIMIT;
import static seedu.nuke.util.Message.messageAddTaskSuccess;

class AddTaskCommandTest {

    @Test
    void execute() {
        HashMap<String, String> modulesMap = ModuleLoader.load(StoragePath.NUS_MODULE_LIST_PATH);
        ModuleManager.initialise(modulesMap);
        CommandResult result = Executor.executeCommand("addm cs3235");

        // base case
        CategoryManager categoryManager = ModuleManager.getModuleList().get(0).getCategories();
        TaskManager taskManager = categoryManager.getCategoryList().get(0).getTasks();
        assertEquals(0, taskManager.getTaskList().size());

        // add a task with no deadline, priority
        CommandResult result1 = Executor.executeCommand("addt asdf -m cs3235 -c Lecture");
        assertEquals(messageAddTaskSuccess("asdf"), result1.getFeedbackToUser());
        assertEquals(1, taskManager.getTaskList().size());
        assertEquals("asdf", taskManager.getTaskList().get(0).getDescription(), "asdf");
        assertEquals("[N]", taskManager.getTaskList().get(0).getStatusIcon());
        assertEquals(1, taskManager.getTaskList().get(0).getPriority());

        // add a task with deadline, no priority
        CommandResult result2 = Executor.executeCommand("addt qwer -m cs3235 -c Lecture -d 12/4/2020 12:00");
        assertEquals(2, taskManager.getTaskList().size());
        assertEquals(1, taskManager.getTaskList().get(1).getPriority());
        assertEquals("12/04/2020 12:00PM", taskManager.getTaskList().get(1).getDeadline().toString());

        // add a task with priority, no deadline
        CommandResult result3 = Executor.executeCommand("addt zxcv -m cs3235 -c Lecture -p 10");
        assertEquals(3, taskManager.getTaskList().size());
        assertEquals(10, taskManager.getTaskList().get(2).getPriority());

        // add a task with non-exist module
        CommandResult result4 = Executor.executeCommand("addt asdf -m cs1111 -c Lecture");
        assertEquals(MESSAGE_MODULE_NOT_FOUND, result4.getFeedbackToUser());

        // add a task with non-exist category
        CommandResult result5 = Executor.executeCommand("addt asdf -m cs3235 -c asdfasdf");
        assertEquals(MESSAGE_CATEGORY_NOT_FOUND, result5.getFeedbackToUser());

        // add a duplicate task
        CommandResult result6 = Executor.executeCommand("addt asdf -m cs3235 -c Lecture");
        assertEquals(MESSAGE_DUPLICATE_TASK, result6.getFeedbackToUser());

        CommandResult result7 = Executor.executeCommand("addt aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + " -m cs3235 -c Lecture");
        assertEquals(MESSAGE_TASK_EXCEED_LIMIT, result7.getFeedbackToUser());

    }
}