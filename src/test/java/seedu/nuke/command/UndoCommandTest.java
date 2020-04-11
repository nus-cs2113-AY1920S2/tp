package seedu.nuke.command;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.command.misc.UndoCommand;
import seedu.nuke.data.ModuleLoader;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.ScreenShotManager;
import seedu.nuke.data.storage.StoragePath;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UndoCommandTest {

    @Test
    void execute() {
        HashMap<String, String> modulesMap = ModuleLoader.load(StoragePath.NUS_MODULE_LIST_PATH);
        ModuleManager.initialise(modulesMap);
        ScreenShotManager.saveScreenShot();

        /*
        CommandResult result = Executor.executeCommand(UndoCommand.COMMAND_WORD);
        ScreenShotManager.saveScreenShot();
        assertEquals(MESSAGE_UNDO_AT_BEGINNING, result.getFeedbackToUser());
        */

        CommandResult result1 = Executor.executeCommand("addm cs3235");
        ScreenShotManager.saveScreenShot();
        assertEquals(1, ModuleManager.getModuleList().size());

        CommandResult result2 = Executor.executeCommand("addm cs3230");
        ScreenShotManager.saveScreenShot();
        assertEquals(2, ModuleManager.getModuleList().size());

        CommandResult result3 = Executor.executeCommand(UndoCommand.COMMAND_WORD);
        ScreenShotManager.saveScreenShot();
        assertEquals(1, ModuleManager.getModuleList().size());
        assertEquals("CS3235", ModuleManager.getModuleList().get(0).getModuleCode());
    }
}