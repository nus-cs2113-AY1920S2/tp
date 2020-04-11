package seedu.nuke.command.editcommand;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.command.Command;
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
import static seedu.nuke.util.ExceptionMessage.MESSAGE_INVALID_DATETIME_FORMAT;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_INVALID_PRIORITY;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_TASK_NOT_FOUND;
import static seedu.nuke.util.Message.MESSAGE_EDIT_TASK_SUCCESS;

class EditTaskCommandTest {

    @Test
    void execute() throws CategoryManager.CategoryNotFoundException {
        HashMap<String, String> modulesMap = ModuleLoader.load(StoragePath.NUS_MODULE_LIST_PATH);
        ModuleManager.initialise(modulesMap);
        CommandResult result = Executor.executeCommand("addm cs3235");
        result = Executor.executeCommand("addt asdf -m cs3235 -c Assignment -d 4/4/2020 12:00 -p 20");

        CategoryManager categoryManager = ModuleManager.getModuleList().get(0).getCategories();
        TaskManager taskManager = categoryManager.getCategory("Assignment").getTasks();

        // base case
        CommandResult result1 = Executor.executeCommand("edt asdf -m cs3235 -c Assignment -t qwer");
        assertEquals(MESSAGE_EDIT_TASK_SUCCESS, result1.getFeedbackToUser());
        assertEquals("qwer", taskManager.getTaskList().get(0).getDescription());

        result1 = Executor.executeCommand("edt qwer -m cs3235 -c Assignment -d 3/4/2020 12:00");
        assertEquals(MESSAGE_EDIT_TASK_SUCCESS, result1.getFeedbackToUser());
        assertEquals("03/04/2020 12:00PM", taskManager.getTaskList().get(0).getDeadline().toString());

        result1 = Executor.executeCommand("edt qwer -m cs3235 -c Assignment -p 15");
        assertEquals(MESSAGE_EDIT_TASK_SUCCESS, result1.getFeedbackToUser());
        assertEquals(15, taskManager.getTaskList().get(0).getPriority());

        // invalid priority
        CommandResult result2 = Executor.executeCommand("edt qwer -m cs3235 -c Assignment -p 30");
        assertEquals(MESSAGE_INVALID_PRIORITY, result2.getFeedbackToUser());
        assertEquals(15, taskManager.getTaskList().get(0).getPriority());

        // invalid deadline
        CommandResult result3 = Executor.executeCommand("edt qwer -m cs3235 -c Assignment -d asdfasdf");
        assertEquals(MESSAGE_INVALID_DATETIME_FORMAT, result3.getFeedbackToUser());
        assertEquals("03/04/2020 12:00PM", taskManager.getTaskList().get(0).getDeadline().toString());

        // non-exist module
        CommandResult result4 = Executor.executeCommand("edt qwer -m cs1234 -c Assignment -t asdf");
        assertEquals(MESSAGE_MODULE_NOT_FOUND, result4.getFeedbackToUser());

        // non-exist category
        CommandResult result5 = Executor.executeCommand("edt qwer -m cs3235 -c asdf -t asdf");
        assertEquals(MESSAGE_CATEGORY_NOT_FOUND, result5.getFeedbackToUser());

        // non-exist task
        CommandResult result6 = Executor.executeCommand("edt aaaaaaaaa -m cs3235 -c Assignment -t asdf");
        assertEquals(MESSAGE_TASK_NOT_FOUND, result6.getFeedbackToUser());

        // edit to duplicate task
        result1 = Executor.executeCommand("addt asdf -m cs3235 -c Assignment -d 4/4/2020 12:00 -p 20");
        CommandResult result7 = Executor.executeCommand("edt qwer -m cs3235 -c Assignment -t asdf");
        assertEquals(MESSAGE_DUPLICATE_TASK, result7.getFeedbackToUser());
    }
}