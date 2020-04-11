package seedu.nuke.command.addcommand;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.directory.Root;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_INCORRECT_DIRECTORY_LEVEL;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_TASK_NOT_FOUND;
import static seedu.nuke.util.Message.MESSAGE_TAG_ADDED;
import static seedu.nuke.util.Message.messageAddTagSuccess;


public class AddTagCommandTest {
    @Test
    public void testEmptyModuleList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        CommandResult result = Executor.executeCommand("addg ur");
        assertEquals(MESSAGE_INCORRECT_DIRECTORY_LEVEL, result.getFeedbackToUser());

        result = Executor.executeCommand("addg ur -m CS2113");
        assertEquals(MESSAGE_INCORRECT_DIRECTORY_LEVEL, result.getFeedbackToUser());

        result = Executor.executeCommand("addg ur -m CS2113 -c Lab");
        assertEquals(MESSAGE_INCORRECT_DIRECTORY_LEVEL, result.getFeedbackToUser());

        result = Executor.executeCommand("addg ur -m CS2113 -c Lab -t read");
        assertEquals(MESSAGE_MODULE_NOT_FOUND, result.getFeedbackToUser());
    }

    @Test
    public void testEmptyTaskList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        CommandResult result = Executor.executeCommand("addm CS2113");
        result = Executor.executeCommand("cd CS2113");

        result = Executor.executeCommand("addg ur");
        assertEquals(MESSAGE_INCORRECT_DIRECTORY_LEVEL, result.getFeedbackToUser());

        result = Executor.executeCommand("addg ur -t read");
        assertEquals(MESSAGE_INCORRECT_DIRECTORY_LEVEL, result.getFeedbackToUser());

        result = Executor.executeCommand("addg ur -c Lab -t read");
        assertEquals(MESSAGE_TASK_NOT_FOUND, result.getFeedbackToUser());
    }

    @Test
    public void testNormalTaskList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        CommandResult result = Executor.executeCommand("addm CS2113");
        result = Executor.executeCommand("cd CS2113");
        result = Executor.executeCommand("addt read -c Lab");

        result = Executor.executeCommand("addg ur -c Lab -t read");
        assertEquals(messageAddTagSuccess("ur"), result.getFeedbackToUser());

        result = Executor.executeCommand("cd Lab");
        result = Executor.executeCommand("addg ur -t read");
        assertEquals(messageAddTagSuccess("ur"), result.getFeedbackToUser());

        result = Executor.executeCommand("addg ur -t tp");
        System.out.println(result.getFeedbackToUser());
        assertEquals(MESSAGE_TASK_NOT_FOUND, result.getFeedbackToUser());

        result = Executor.executeCommand("addt quiz -c Assignment");
        result = Executor.executeCommand("addg ur -c Assignment -t quiz");
        assertEquals(messageAddTagSuccess("ur"), result.getFeedbackToUser());

    }

}
