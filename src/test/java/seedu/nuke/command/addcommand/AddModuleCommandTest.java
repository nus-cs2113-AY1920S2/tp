package seedu.nuke.command.addcommand;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ModuleLoader;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.storage.StoragePath;

import java.util.HashMap;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_DUPLICATE_MODULE;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_PROVIDED;
import static seedu.nuke.util.Message.messageAddModuleSuccess;

class AddModuleCommandTest {

    @Test
    void execute() {
        HashMap<String, String> modulesMap = ModuleLoader.load(StoragePath.NUS_MODULE_LIST_PATH);
        ModuleManager.initialise(modulesMap);

        CommandResult result = Executor.executeCommand("addm cs3235");
        assertEquals(1, ModuleManager.getModuleList().size());
        assertEquals(messageAddModuleSuccess("CS3235", "Computer Security"), result.getFeedbackToUser());

        // duplicate module
        CommandResult result1 = Executor.executeCommand("addm cs3235");
        assertEquals(1, ModuleManager.getModuleList().size());
        assertEquals(result1.getFeedbackToUser(), MESSAGE_DUPLICATE_MODULE);

        // non-exist module
        CommandResult result2 = Executor.executeCommand("addm cs12321");
        assertEquals(1, ModuleManager.getModuleList().size());
        assertEquals(result2.getFeedbackToUser(), MESSAGE_MODULE_NOT_PROVIDED);

        // another valid module
        CommandResult result3 = Executor.executeCommand("addm cs3230");
        assertEquals(2, ModuleManager.getModuleList().size());
    }
}