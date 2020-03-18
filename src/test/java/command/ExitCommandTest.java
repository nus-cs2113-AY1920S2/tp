package command;

import common.Messages;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.atas.Parser;
import seedu.atas.TaskList;
import seedu.atas.Ui;

public class ExitCommandTest {
    @Test
    public void testExit() {
        Ui testUi = new Ui();
        TaskList testTaskList = new TaskList();
        CommandResult testResult = Parser.parseCommand(ExitCommand.EXIT_COMMAND_WORD).execute(testTaskList, testUi);
        CommandResult compareResult = new CommandResult(Messages.EXIT_MESSAGE);
        assertEquals(testResult.getClass(), compareResult.getClass());
        assertEquals(testResult.feedbackToUser, Messages.EXIT_MESSAGE);
        assertTrue(ExitCommand.isExit());
    }
}
