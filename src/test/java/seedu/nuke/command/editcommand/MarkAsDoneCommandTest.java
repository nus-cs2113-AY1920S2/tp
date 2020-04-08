package seedu.nuke.command.editcommand;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.directory.Root;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_CATEGORY_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_INCORRECT_DIRECTORY_LEVEL;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_MODULE_NOT_FOUND;
import static seedu.nuke.util.ExceptionMessage.MESSAGE_TASK_NOT_FOUND;
import static seedu.nuke.util.Message.MESSAGE_DONE_TASK;

public class MarkAsDoneCommandTest {

    @Test
    public void testEmptyModuleList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        CommandResult result = Executor.executeCommand("done");
        assertEquals(MESSAGE_INCORRECT_DIRECTORY_LEVEL, result.getFeedbackToUser());

        result = Executor.executeCommand("done read");
        assertEquals(MESSAGE_INCORRECT_DIRECTORY_LEVEL, result.getFeedbackToUser());

        result = Executor.executeCommand("done read -m CS2113 -c Lab");
        assertEquals(MESSAGE_MODULE_NOT_FOUND, result.getFeedbackToUser());
    }

    @Test
    public void testEmptyTaskList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        CommandResult result = Executor.executeCommand("addm CS2113");

        result = Executor.executeCommand("done");
        assertEquals(MESSAGE_INCORRECT_DIRECTORY_LEVEL, result.getFeedbackToUser());

        result = Executor.executeCommand("done read");
        assertEquals(MESSAGE_INCORRECT_DIRECTORY_LEVEL, result.getFeedbackToUser());

        result = Executor.executeCommand("cd CS2113");
        result = Executor.executeCommand("done read -c Big Lab");
        assertEquals(MESSAGE_CATEGORY_NOT_FOUND, result.getFeedbackToUser());
        result = Executor.executeCommand("cd Lab");
        result = Executor.executeCommand("done read");
        assertEquals(MESSAGE_TASK_NOT_FOUND, result.getFeedbackToUser());

        result = Executor.executeCommand("done read -m CS2113 -c Lab");
        assertEquals(MESSAGE_TASK_NOT_FOUND, result.getFeedbackToUser());

    }

    @Test
    public void testNormalTaskList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        CommandResult result = Executor.executeCommand("addm CS2113");
        result = Executor.executeCommand("addm CS3235");
        result = Executor.executeCommand("addt Big Lab Work -m CS2113 -c Lab -d 25/03/2020 0600");
        result = Executor.executeCommand("addt lab1 -m CS3235 -c Lab");
        result = Executor.executeCommand("addt watch webcast -m CS3235 -c Lecture");
        result = Executor.executeCommand("addt Tutorial 6 -m CS2113 -c Tutorial");

        result = Executor.executeCommand("cd CS2113");
        result = Executor.executeCommand("cd Lab");

        result = Executor.executeCommand("done Big Lab Work");
        assertEquals(MESSAGE_DONE_TASK, result.getFeedbackToUser());

        result = Executor.executeCommand("done Tutorial 6");
        assertEquals(MESSAGE_TASK_NOT_FOUND, result.getFeedbackToUser());

        result = Executor.executeCommand("done lab1 -m CS3235");
        assertEquals(MESSAGE_INCORRECT_DIRECTORY_LEVEL, result.getFeedbackToUser());

        result = Executor.executeCommand("done lab1 -m CS3235 -c Lab");
        assertEquals(MESSAGE_DONE_TASK, result.getFeedbackToUser());

    }
}
