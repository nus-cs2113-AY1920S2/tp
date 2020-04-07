package seedu.nuke.command.deletecommand;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.directory.Root;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nuke.util.Message.MESSAGE_NO_FILES_FOUND;

public class DeleteFileCommandTest {

    @Test
    public void testEmptyModuleList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        CommandResult result = Executor.executeCommand("delf");
        assertEquals(MESSAGE_NO_FILES_FOUND, result.getFeedbackToUser());

        result = Executor.executeCommand("delf save.txt");
        assertEquals(MESSAGE_NO_FILES_FOUND, result.getFeedbackToUser());

        result = Executor.executeCommand("delf save.txt -m CS2113");
        assertEquals(MESSAGE_NO_FILES_FOUND, result.getFeedbackToUser());
    }

    /**
     * A JunitTest of DeleteFileCommand when the file list of all tasks of all modules is empty.
     */
    public void testEmptyFileList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        CommandResult result = Executor.executeCommand("addm CS2113");
        result = Executor.executeCommand("delf");
        assertEquals(MESSAGE_NO_FILES_FOUND, result.getFeedbackToUser());

        result = Executor.executeCommand("delf save.txt");
        assertEquals(MESSAGE_NO_FILES_FOUND, result.getFeedbackToUser());

        result = Executor.executeCommand("delf save.txt -m CS2113");
        assertEquals(MESSAGE_NO_FILES_FOUND, result.getFeedbackToUser());
    }
}
