package seedu.nuke.command.deletecommand;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Root;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nuke.util.Message.MESSAGE_DELETE_MODULE_SUCCESS;
import static seedu.nuke.util.Message.MESSAGE_INVALID_DELETE_INDICES;
import static seedu.nuke.util.Message.MESSAGE_NO_MODULES_FOUND;
import static seedu.nuke.util.Message.MESSAGE_PROMPT_FORMAT;
import static seedu.nuke.util.Message.messageConfirmDeleteModule;
import static seedu.nuke.util.Message.messagePromptDeleteModuleIndices;

public class DeleteModuleCommandTest {

    @Test
    public void testEmptyModuleList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        CommandResult result = Executor.executeCommand("delm CS2113");
        assertEquals(MESSAGE_NO_MODULES_FOUND, result.getFeedbackToUser());

        result = Executor.executeCommand("delm");
        assertEquals(MESSAGE_NO_MODULES_FOUND, result.getFeedbackToUser());
    }

    @Test
    public void testNormalModuleList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        CommandResult result = Executor.executeCommand("addm CS2113");
        result = Executor.executeCommand("addm CS3235");

        ArrayList<Module> modules = new ArrayList<>();
        try {
            modules.add(ModuleManager.getModule("CS2113"));
            modules.add(ModuleManager.getModule("CS3235"));
        } catch (ModuleManager.ModuleNotFoundException e) {
            System.out.println("Cannot find the module");
        }
        result = Executor.executeCommand("delm CS");
        assertEquals(messagePromptDeleteModuleIndices(new ArrayList<>(modules)), result.getFeedbackToUser());
        result = Executor.executeCommand("n");
        assertEquals(MESSAGE_INVALID_DELETE_INDICES, result.getFeedbackToUser());

        result = Executor.executeCommand("delm CS -e");
        assertEquals(MESSAGE_NO_MODULES_FOUND, result.getFeedbackToUser());

        result = Executor.executeCommand("delm CS3235");
        try {
            assertEquals(messageConfirmDeleteModule(ModuleManager.getModule("CS3235")), result.getFeedbackToUser());
        } catch (ModuleManager.ModuleNotFoundException e) {
            System.out.println("Cannot find the module");
        }
        result = Executor.executeCommand("x");
        assertEquals(MESSAGE_PROMPT_FORMAT, result.getFeedbackToUser());
        result = Executor.executeCommand("y");
        assertEquals(MESSAGE_DELETE_MODULE_SUCCESS, result.getFeedbackToUser());
    }
}
