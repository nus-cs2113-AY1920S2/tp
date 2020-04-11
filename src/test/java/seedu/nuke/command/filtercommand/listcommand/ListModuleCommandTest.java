package seedu.nuke.command.filtercommand.listcommand;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Root;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nuke.util.Message.MESSAGE_NO_MODULES_TO_SHOW;
import static seedu.nuke.util.Message.MESSAGE_SHOW_LIST;

public class ListModuleCommandTest {

    @Test
    public void testEmptyModuleList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        CommandResult result = Executor.executeCommand("lsm");
        assertEquals(MESSAGE_NO_MODULES_TO_SHOW, result.getFeedbackToUser());

        result = Executor.executeCommand("lsm CS");
        assertEquals(MESSAGE_NO_MODULES_TO_SHOW, result.getFeedbackToUser());
    }

    @Test
    public void testNormalModuleList() {
        ModuleManager.initialise();
        DirectoryTraverser.traverseTo(new Root());

        CommandResult result = Executor.executeCommand("addm CS2113");
        result = Executor.executeCommand("addm CS3235");
        result = Executor.executeCommand("addm LAK3202");

        ArrayList<Module> modules = new ArrayList<>();
        try {
            modules.add(ModuleManager.getModule("CS2113"));
            modules.add(ModuleManager.getModule("CS3235"));
            modules.add(ModuleManager.getModule("LAK3202"));
        } catch (ModuleManager.ModuleNotFoundException e) {
            System.out.println("Cannot find the module");
        }
        result = Executor.executeCommand("lsm");
        assertEquals(MESSAGE_SHOW_LIST, result.getFeedbackToUser());
        assertEquals(DirectoryLevel.MODULE, result.getDirectoryLevel());
        assertEquals(modules, result.getShownList());
        assertEquals(null, result.getHelpGuide());

        result = Executor.executeCommand("lsm CS");
        modules.remove(2);
        assertEquals(MESSAGE_SHOW_LIST, result.getFeedbackToUser());
        assertEquals(DirectoryLevel.MODULE, result.getDirectoryLevel());
        assertEquals(modules, result.getShownList());
        assertEquals(null, result.getHelpGuide());

        result = Executor.executeCommand("lsm CS -e");
        assertEquals(MESSAGE_NO_MODULES_TO_SHOW, result.getFeedbackToUser());

        result = Executor.executeCommand("lsm LAK3202 -e");
        modules.clear();
        try {
            modules.add(ModuleManager.getModule("LAK3202"));
        } catch (ModuleManager.ModuleNotFoundException e) {
            System.out.println("Cannot find the module");
        }
        assertEquals(MESSAGE_SHOW_LIST, result.getFeedbackToUser());
        assertEquals(DirectoryLevel.MODULE, result.getDirectoryLevel());
        assertEquals(modules, result.getShownList());
        assertEquals(null, result.getHelpGuide());
    }
}
