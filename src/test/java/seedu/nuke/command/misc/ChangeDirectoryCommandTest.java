package seedu.nuke.command.misc;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.directory.DirectoryTraverser;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChangeDirectoryCommandTest {

    @Test
    void execute() {
        // set up
        ModuleManager.initialise();
        DirectoryTraverser.setCurrentLevelToRoot();
        CommandResult result = Executor.executeCommand("addm CS2113");
        result = Executor.executeCommand("addt asdf -m cs2113 -c Assignment");

        // cd cs2113
        result = Executor.executeCommand("cd cs2113");
        assertEquals(DirectoryLevel.MODULE, DirectoryTraverser.getCurrentDirectoryLevel());

        // cd ..
        result = Executor.executeCommand("cd ..");
        assertEquals(DirectoryLevel.ROOT, DirectoryTraverser.getCurrentDirectoryLevel());

        // cd cs2113, cd Assignment
        result = Executor.executeCommand("cd cs2113");
        result = Executor.executeCommand("cd Assignment");
        assertEquals(DirectoryLevel.CATEGORY, DirectoryTraverser.getCurrentDirectoryLevel());

        // cd asdf
        result = Executor.executeCommand("cd asdf");
        assertEquals(DirectoryLevel.TASK, DirectoryTraverser.getCurrentDirectoryLevel());
    }
}