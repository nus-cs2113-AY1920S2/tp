package seedu.nuke.command.filtercommand.listcommand;

import org.junit.jupiter.api.Test;
import seedu.nuke.Executor;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ModuleLoader;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.storage.StoragePath;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nuke.util.Message.MESSAGE_NO_TASKS_TO_SHOW;
import static seedu.nuke.util.Message.MESSAGE_SHOW_LIST;

class DueCommandTest {

    @Test
    void execute() {
        // set up
        HashMap<String, String> modulesMap = ModuleLoader.load(StoragePath.NUS_MODULE_LIST_PATH);
        ModuleManager.initialise(modulesMap);
        CommandResult result = Executor.executeCommand("addm cs3235");
        result = Executor.executeCommand("addt test1 -m cs3235 -c Assignment -d 4/2/2020 12:00");
        result = Executor.executeCommand("addt test2 -m cs3235 -c Assignment -d 5/2/2020 12:00");
        result = Executor.executeCommand("addt test3 -m cs3235 -c Assignment -d 6/2/2020 12:00");
        result = Executor.executeCommand("addt test4 -m cs3235 -c Assignment -d 7/2/2020 12:00");
        result = Executor.executeCommand("addt test5 -m cs3235 -c Assignment -d 8/2/2020 12:00");

        //overdue
        result = Executor.executeCommand("due over");
        assertEquals(MESSAGE_SHOW_LIST, result.getFeedbackToUser());
        assertEquals(5, result.getShownList().size());

        // on 6/2/2020
        result = Executor.executeCommand("due on 6/2/2020");
        assertEquals(MESSAGE_SHOW_LIST, result.getFeedbackToUser());
        assertEquals(1, result.getShownList().size());

        // before 6/2/2020
        result = Executor.executeCommand("due before 6/2/2020");
        assertEquals(MESSAGE_SHOW_LIST, result.getFeedbackToUser());
        assertEquals(2, result.getShownList().size());

        // after 6/2/2020
        result = Executor.executeCommand("due after 6/2/2020");
        assertEquals(MESSAGE_SHOW_LIST, result.getFeedbackToUser());
        assertEquals(2, result.getShownList().size());

        // on a impossible day
        result = Executor.executeCommand("due on 6/2/2220");
        assertEquals(MESSAGE_NO_TASKS_TO_SHOW, result.getFeedbackToUser());

        // after a impossible day
        result = Executor.executeCommand("due on 6/2/2220");
        assertEquals(MESSAGE_NO_TASKS_TO_SHOW, result.getFeedbackToUser());

        // before a impossible day
        result = Executor.executeCommand("due on 6/2/1999");
        assertEquals(MESSAGE_NO_TASKS_TO_SHOW, result.getFeedbackToUser());
    }
}