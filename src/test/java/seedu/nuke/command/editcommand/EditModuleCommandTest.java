package seedu.nuke.command.editcommand;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ModuleLoader;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.storage.StoragePath;

import java.util.HashMap;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_DUPLICATE_MODULE;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_PROVIDED;
import static seedu.nuke.util.Message.MESSAGE_EDIT_MODULE_SUCCESS;
import static seedu.nuke.util.Message.MESSAGE_NO_EDIT_MODULE;

class EditModuleCommandTest {

    @Test
    void execute() {
        HashMap<String, String> modulesMap = ModuleLoader.load(StoragePath.NUS_MODULE_LIST_PATH);
        ModuleManager.initialise(modulesMap);
        CommandResult result = Executor.executeCommand("addm cs3235");

        // base case
        CommandResult result1 = Executor.executeCommand("edm cs3235 -m cs3230");
        assertEquals(MESSAGE_EDIT_MODULE_SUCCESS, result1.getFeedbackToUser());
        assertEquals(1, ModuleManager.getModuleList().size());
        assertEquals("CS3230", ModuleManager.getModuleList().get(0).getModuleCode());
        assertEquals("Design and Analysis of Algorithms", ModuleManager.getModuleList().get(0).getTitle());

        CommandResult result2 = Executor.executeCommand("edm");
        assertEquals(MESSAGE_NO_EDIT_MODULE, result2.getFeedbackToUser());

        // edit to a non-exist module
        CommandResult result3 = Executor.executeCommand("edm cs3230 -m asdfasdf");
        assertEquals(MESSAGE_MODULE_NOT_PROVIDED, result3.getFeedbackToUser());

        // edit from a non-exist module
        CommandResult result4 = Executor.executeCommand("edm cs3235 -m cs1234");
        assertEquals(MESSAGE_MODULE_NOT_FOUND, result4.getFeedbackToUser());

        //edit to a duplicate module
        CommandResult result5 = Executor.executeCommand("addm ifs4103");
        CommandResult result6 = Executor.executeCommand("edm cs3230 -m ifs4103");
        assertEquals(MESSAGE_DUPLICATE_MODULE, result6.getFeedbackToUser());
    }
}