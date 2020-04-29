package seedu.nuke.command.editcommand;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.DirectoryTraverser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_CATEGORY_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_DUPLICATE_TASK_FILE;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_INCORRECT_DIRECTORY_LEVEL;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_TASK_FILE_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_TASK_NOT_FOUND;
import static seedu.nuke.util.Message.MESSAGE_EDIT_FILE_SUCCESS;
import static seedu.nuke.util.Message.MESSAGE_FILE_EXCEED_LIMIT;

class EditFileCommandTest {

    @Test
    void execute() {
        // set up
        ModuleManager.initialise();
        DirectoryTraverser.setCurrentLevelToRoot();
        CommandResult result = Executor.executeCommand("addm CS2113");
        result = Executor.executeCommand("addt read -m CS2113 -c Lab -d tmr 2359");
        result = Executor.executeCommand("cd CS2113");
        result = Executor.executeCommand("cd Lab");
        result = Executor.executeCommand("cd read");
        result = Executor.executeCommand("addf save.txt -f data/save.txt");
        result = Executor.executeCommand("addf asdf.txt -f data/save.txt");

        // base case
        result = Executor.executeCommand("edf save.txt -f test.txt");
        assertEquals(MESSAGE_EDIT_FILE_SUCCESS, result.getFeedbackToUser());

        // no such module
        result = Executor.executeCommand("edf test.txt -m cs1010 -c Lab -t read -f asdf.txt");
        assertEquals(MESSAGE_MODULE_NOT_FOUND, result.getFeedbackToUser());

        // no such category
        result = Executor.executeCommand("edf test.txt -m cs2113 -c asdf -t read -f asdf.txt");
        assertEquals(MESSAGE_CATEGORY_NOT_FOUND, result.getFeedbackToUser());

        // no such task
        result = Executor.executeCommand("edf test.txt -m cs2113 -c Lab -t asdf -f asdf.txt");
        assertEquals(MESSAGE_TASK_NOT_FOUND, result.getFeedbackToUser());

        // no such file
        result = Executor.executeCommand("edf qwer.txt -f test.txt");
        assertEquals(MESSAGE_TASK_FILE_NOT_FOUND, result.getFeedbackToUser());

        // duplicate file
        result = Executor.executeCommand("edf test.txt -f asdf.txt");
        assertEquals(MESSAGE_DUPLICATE_TASK_FILE, result.getFeedbackToUser());

        // wrong directory
        result = Executor.executeCommand("cd ..");
        result = Executor.executeCommand("edf test.txt -m cs2113 -c Lab -f asdf.txt");
        assertEquals(MESSAGE_INCORRECT_DIRECTORY_LEVEL, result.getFeedbackToUser());
        result = Executor.executeCommand("edf test.txt -m cs2113 -f asdf.txt");
        assertEquals(MESSAGE_INCORRECT_DIRECTORY_LEVEL, result.getFeedbackToUser());
        result = Executor.executeCommand("edf test.txt -f asdf.txt");
        assertEquals(MESSAGE_INCORRECT_DIRECTORY_LEVEL, result.getFeedbackToUser());
    }
}