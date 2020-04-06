package seedu.nuke.command.editcommand;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.CategoryManager;
import seedu.nuke.data.ModuleLoader;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.storage.StoragePath;
import seedu.nuke.directory.Module;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_CATEGORY_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_DUPLICATE_CATEGORY;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.nuke.util.Message.MESSAGE_EDIT_CATEGORY_SUCCESS;

class EditCategoryCommandTest {

    @Test
    void execute() {
        HashMap<String, String> modulesMap = ModuleLoader.load(StoragePath.NUS_MODULE_LIST_PATH);
        ModuleManager.initialise(modulesMap);
        CommandResult result = Executor.executeCommand("addm cs3235");
        result = Executor.executeCommand("addt asdf -m cs3235 -c Assignment");

        CategoryManager categoryManager = ModuleManager.getModuleList().get(0).getCategories();

        // base case.
        CommandResult result1 = Executor.executeCommand("edc Assignment -m cs3235 -c assignment");
        assertEquals(MESSAGE_EDIT_CATEGORY_SUCCESS, result1.getFeedbackToUser());
        assertEquals("assignment", categoryManager.getCategoryList().get(2).getCategoryName());
        assertEquals(1, categoryManager.getCategoryList().get(2).getTasks().getTaskList().size());

        // non-exist module
        CommandResult result2 = Executor.executeCommand("edc assignment1 -m asdf -c assignment");
        assertEquals(MESSAGE_MODULE_NOT_FOUND, result2.getFeedbackToUser());

        // non-exist category
        CommandResult result3 = Executor.executeCommand("edc assignment1 -m cs3235 -c asdfasdf");
        assertEquals(MESSAGE_CATEGORY_NOT_FOUND, result3.getFeedbackToUser());

        // duplicate category
        CommandResult result4 = Executor.executeCommand("edc Lecture -m cs3235 -c assignment");
        assertEquals(MESSAGE_DUPLICATE_CATEGORY, result4.getFeedbackToUser());

    }
}