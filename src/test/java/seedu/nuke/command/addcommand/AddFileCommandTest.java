package seedu.nuke.command.addcommand;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.directory.Root;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_ADD_FILE_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_INCORRECT_DIRECTORY_LEVEL;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_TASK_NOT_FOUND;
import static seedu.nuke.util.Message.MESSAGE_FILE_EXCEED_LIMIT;
import static seedu.nuke.util.Message.MESSAGE_MISSING_FILE_PATH;

public class AddFileCommandTest {

    @Test
    public void testEmptyModuleList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        CommandResult result = Executor.executeCommand("addf save.txt -f C:\\save.txt");
        assertEquals(MESSAGE_INCORRECT_DIRECTORY_LEVEL, result.getFeedbackToUser());

        result = Executor.executeCommand("addf save.txt -m CS2113 -f C:\\save.txt");
        assertEquals(MESSAGE_INCORRECT_DIRECTORY_LEVEL, result.getFeedbackToUser());

        result = Executor.executeCommand("addf save.txt -m CS2113 -c Lab -f C:\\save.txt");
        assertEquals(MESSAGE_INCORRECT_DIRECTORY_LEVEL, result.getFeedbackToUser());

        result = Executor.executeCommand("addf save.txt -m CS2113 -c Lab -t read -f C:\\save.txt");
        assertEquals(MESSAGE_MODULE_NOT_FOUND, result.getFeedbackToUser());
    }

    @Test
    public void testEmptyTaskList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        Executor.executeCommand("addm CS2113");
        Executor.executeCommand("cd CS2113");

        CommandResult result = Executor.executeCommand("addf save.txt -f C:\\save.txt");
        assertEquals(MESSAGE_INCORRECT_DIRECTORY_LEVEL, result.getFeedbackToUser());

        CommandResult resultSecond = Executor.executeCommand("addf save.txt -t read -f C:\\save.txt");
        assertEquals(MESSAGE_INCORRECT_DIRECTORY_LEVEL, resultSecond.getFeedbackToUser());

        CommandResult resultThird = Executor.executeCommand("addf save.txt -c Lab -t read -f C:\\save.txt");
        assertEquals(MESSAGE_TASK_NOT_FOUND, resultThird.getFeedbackToUser());
    }

    @Test
    public void testLengthExceedFile() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        Executor.executeCommand("addm CS2113");
        Executor.executeCommand("addt read -m CS2113 -c Lab -d tmr 2359");

        Executor.executeCommand("cd CS2113");
        Executor.executeCommand("cd Lab");
        Executor.executeCommand("cd read");
        CommandResult result = Executor.executeCommand("addf abcdefghijklmnopqrstuvwxyzaaaaa");
        assertEquals(MESSAGE_FILE_EXCEED_LIMIT, result.getFeedbackToUser());
    }

    @Test
    public void testNoFilePath() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        Executor.executeCommand("addm CS2113");
        Executor.executeCommand("addt read -m CS2113 -c Lab -d tmr 2359");

        Executor.executeCommand("cd CS2113");
        Executor.executeCommand("cd Lab");
        Executor.executeCommand("cd read");
        CommandResult result = Executor.executeCommand("addf save.txt");
        assertEquals(MESSAGE_MISSING_FILE_PATH, result.getFeedbackToUser());
    }

    @Test
    public void testInvalidPath() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        Executor.executeCommand("addm CS2113");
        Executor.executeCommand("addt read -m CS2113 -c Lab -d tmr 2359");


        Executor.executeCommand("cd CS2113");
        Executor.executeCommand("cd Lab");
        Executor.executeCommand("cd read");
        CommandResult result = Executor.executeCommand("addf save.txt -f C:\\save.txt");
        assertEquals(MESSAGE_ADD_FILE_NOT_FOUND, result.getFeedbackToUser());
    }
}
