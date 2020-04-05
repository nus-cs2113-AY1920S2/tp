package seedu.nuke.commandtest;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.command.UndoCommand;
import seedu.nuke.data.ModuleLoader;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.ScreenShot;
import seedu.nuke.data.ScreenShotManager;
import seedu.nuke.data.storage.StoragePath;
import seedu.nuke.directory.Module;
import seedu.nuke.parser.Parser;

import java.io.FileNotFoundException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.nuke.command.UndoCommand.COMMAND_WORD;
import static seedu.nuke.command.UndoCommand.MESSAGE_UNDO_AT_BEGINNING;

class UndoCommandTest {

    @Test
    void execute() throws FileNotFoundException {
        HashMap<String, String> modulesMap = ModuleLoader.load(StoragePath.NUS_MODULE_LIST_PATH);
        ModuleManager.initialise(modulesMap);
        ScreenShotManager.saveScreenShot();

        CommandResult result = Executor.executeCommand(UndoCommand.COMMAND_WORD);
        ScreenShotManager.saveScreenShot();
        assertEquals(result.getFeedbackToUser(), MESSAGE_UNDO_AT_BEGINNING);

        CommandResult result1 = Executor.executeCommand("addm cs3235");
        ScreenShotManager.saveScreenShot();
        assertEquals(ModuleManager.getModuleList().size(), 1);

        CommandResult result2 = Executor.executeCommand("addm cs3230");
        ScreenShotManager.saveScreenShot();
        assertEquals(ModuleManager.getModuleList().size(), 2);

        CommandResult result3 = Executor.executeCommand(UndoCommand.COMMAND_WORD);
        ScreenShotManager.saveScreenShot();
        assertEquals(ModuleManager.getModuleList().size(), 1);
    }
}