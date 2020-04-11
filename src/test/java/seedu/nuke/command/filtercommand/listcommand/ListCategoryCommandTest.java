package seedu.nuke.command.filtercommand.listcommand;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ModuleLoader;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.storage.StoragePath;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.directory.Root;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nuke.util.Message.MESSAGE_NO_CATEGORIES_TO_SHOW;
import static seedu.nuke.util.Message.MESSAGE_SHOW_LIST;

class ListCategoryCommandTest {

    @Test
    void execute() {
        // set up
        HashMap<String, String> modulesMap = ModuleLoader.load(StoragePath.NUS_MODULE_LIST_PATH);
        ModuleManager.initialise(modulesMap);
        DirectoryTraverser.traverseTo(new Root());
        CommandResult result = Executor.executeCommand("addm cs3235");
        result = Executor.executeCommand("addm cs3230");
        result = Executor.executeCommand("addc test -m cs3235");
        result = Executor.executeCommand("addc test1 -m cs3230");
        result = Executor.executeCommand("addc test2 -m cs3235");

        // lsc
        result = Executor.executeCommand("lsc");
        assertEquals(MESSAGE_SHOW_LIST, result.getFeedbackToUser());
        //assertEquals(11, result.getShownList().size());

        // lsc asdf
        result = Executor.executeCommand("lsc asdf");
        assertEquals(MESSAGE_NO_CATEGORIES_TO_SHOW, result.getFeedbackToUser());

        // lsc test
        result = Executor.executeCommand("lsc test");
        assertEquals(3, result.getShownList().size());

        // lsc test -m cs3230
        result = Executor.executeCommand("lsc test -m cs3230");
        assertEquals(1, result.getShownList().size());

        // lsc test -m cs3235
        result = Executor.executeCommand("lsc test -m cs3235");
        assertEquals(2, result.getShownList().size());

        // lsc test -e
        result = Executor.executeCommand("lsc test -e");
        assertEquals(1, result.getShownList().size());
    }
}