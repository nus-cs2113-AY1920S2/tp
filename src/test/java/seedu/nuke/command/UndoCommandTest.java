package seedu.nuke.command;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.data.ModuleLoader;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.ScreenShotManager;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nuke.command.UndoCommand.MESSAGE_UNDO_AT_BEGINNING;

class UndoCommandTest {

    @Test
    void execute() {
        HashMap<String, String> modulesMap = ModuleLoader.load("");
        ModuleManager.initialise(modulesMap);
        ScreenShotManager.saveScreenShot();

        CommandResult result = Executor.executeCommand(UndoCommand.COMMAND_WORD);
        ScreenShotManager.saveScreenShot();
        assertEquals(MESSAGE_UNDO_AT_BEGINNING, result.getFeedbackToUser());

        CommandResult result1 = Executor.executeCommand("addm cs3235");
        ScreenShotManager.saveScreenShot();
        assertEquals(1, ModuleManager.getModuleList().size());

        CommandResult result2 = Executor.executeCommand("addm cs3230");
        ScreenShotManager.saveScreenShot();
        assertEquals(2, ModuleManager.getModuleList().size());

        CommandResult result3 = Executor.executeCommand(UndoCommand.COMMAND_WORD);
        ScreenShotManager.saveScreenShot();
        assertEquals(1, ModuleManager.getModuleList().size());
    }
}