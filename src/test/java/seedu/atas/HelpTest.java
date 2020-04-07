package seedu.atas;

import command.*;
import common.Messages;
import org.junit.jupiter.api.Test;
import tasks.Event;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class HelpTest {
    @Test
    public void testHelpCommandOutput() {
        HelpCommand helpCommand = new HelpCommand();
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        CommandResult commandResult = helpCommand.execute(taskList, ui);
        assertNotNull(commandResult.feedbackToUser);

        String out = commandResult.feedbackToUser;
        assertTrue(out.contains(HelpCommand.COMMAND_USAGE));
        assertTrue(out.contains(AssignmentCommand.COMMAND_USAGE));
        assertTrue(out.contains(EventCommand.COMMAND_USAGE));
        assertTrue(out.contains(ListCommand.COMMAND_USAGE));
        assertTrue(out.contains(DoneCommand.COMMAND_USAGE));
        assertTrue(out.contains(EditCommand.COMMAND_USAGE));
        assertTrue(out.contains(DeleteCommand.COMMAND_USAGE));
        assertTrue(out.contains(ClearCommand.COMMAND_USAGE));
        assertTrue(out.contains(RepeatCommand.COMMAND_USAGE));
        assertTrue(out.contains(SearchCommand.COMMAND_USAGE));
        assertTrue(out.contains(SearchCommand.dCOMMAND_USAGE));
        assertTrue(out.contains(CalendarCommand.COMMAND_USAGE));
        assertTrue(out.contains(ExitCommand.COMMAND_USAGE));
    }

    @Test
    public void testCounter() {
        HelpCommand helpCommand = new HelpCommand();
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        CommandResult commandResult = helpCommand.execute(taskList, ui);
        assertNotNull(commandResult.feedbackToUser);

        int counter = 1;
        String[] split = commandResult.feedbackToUser.split(System.lineSeparator(), 2);
        String[] helplines = split[1].split(System.lineSeparator());
        ArrayList<String> listOfHelp = new ArrayList<>();

        // remove subsections of help command
        for (String str : helplines) {
            if (str.contains(".")) {
                listOfHelp.add(str);
            }
        }

        for (String str : listOfHelp) {
            assertTrue(str.contains(String.format("%3d", counter++)));
        }
    }
}
