package seedu.nuke.commandTest;

import seedu.nuke.command.listCommand.ListAllTasksDeadlineCommand;
import seedu.nuke.data.ModuleManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nuke.util.Message.MESSAGE_NO_TASK_IN_LIST;

public class ListAllDeadlineTest {

    private ModuleManager moduleManager;

    public ListAllDeadlineTest(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    public void testTasksCounting() {
        assertEquals(moduleManager.countAllTasks(),
                new ListAllTasksDeadlineCommand().execute().getShownList().size());
    }

    public void testEmptyTaskList() {
        if(moduleManager.countAllTasks() == 0) {
            assertEquals(MESSAGE_NO_TASK_IN_LIST, new ListAllTasksDeadlineCommand().execute().getFeedbackToUser());
        }
    }
}
