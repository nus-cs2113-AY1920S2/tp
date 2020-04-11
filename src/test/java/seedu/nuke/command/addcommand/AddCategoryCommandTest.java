package seedu.nuke.command.addcommand;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ModuleLoader;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.storage.StoragePath;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_DUPLICATE_CATEGORY;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.nuke.util.Message.messageAddCategorySuccess;

class AddCategoryCommandTest {

    @Test
    void execute() {
        HashMap<String, String> modulesMap = ModuleLoader.load(StoragePath.NUS_MODULE_LIST_PATH);
        ModuleManager.initialise(modulesMap);
        CommandResult result = Executor.executeCommand("addm cs3235");

        // base case
        assertEquals(ModuleManager.getModuleList().get(0).getCategories().getCategoryList().size(), 4);
        CommandResult result1 = Executor.executeCommand("addc test -m cs3235");
        assertEquals(5, ModuleManager.getModuleList().get(0).getCategories().getCategoryList().size());
        assertEquals(messageAddCategorySuccess("test"), result1.getFeedbackToUser());

        // duplicate category
        CommandResult result2 = Executor.executeCommand("addc test -m cs3235");
        assertEquals(ModuleManager.getModuleList().get(0).getCategories().getCategoryList().size(), 5);
        assertEquals(MESSAGE_DUPLICATE_CATEGORY, result2.getFeedbackToUser());

        // wrong module
        CommandResult result3 = Executor.executeCommand("addc test -m cs3230");
        assertEquals(ModuleManager.getModuleList().get(0).getCategories().getCategoryList().size(), 5);
        assertEquals(MESSAGE_MODULE_NOT_FOUND, result3.getFeedbackToUser());

        // a new category
        CommandResult result4 = Executor.executeCommand("addc asdf -m cs3235");
        assertEquals(ModuleManager.getModuleList().get(0).getCategories().getCategoryList().size(), 6);
        assertEquals(messageAddCategorySuccess("asdf"), result4.getFeedbackToUser());
    }
}