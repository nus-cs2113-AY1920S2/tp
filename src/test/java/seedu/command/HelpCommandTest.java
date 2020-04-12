package seedu.command;

import org.junit.jupiter.api.Test;
import seedu.exception.ProjException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.common.Constants.TAB;
import static seedu.command.HelpCommand.HELP_LEGEND;

class HelpCommandTest {

    @Test
    void execute_HelpCommand_returnFeedback() {

        Command command = new HelpCommand();
        String output = null;
        try {
            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String expected = HELP_LEGEND;

        expected += TAB + "Command List:\n\n";

        expected += TAB + TAB + AddCommand.COMMAND_INFO + System.lineSeparator();
        expected += TAB + TAB + "Usage: " + AddCommand.COMMAND_USAGE + System.lineSeparator();
        expected += System.lineSeparator();

        expected += TAB + TAB + EditCommand.COMMAND_INFO + System.lineSeparator();
        expected += TAB + TAB + "Usage: " + EditCommand.COMMAND_USAGE + System.lineSeparator();
        expected += System.lineSeparator();

        expected += TAB + TAB + ListCommand.COMMAND_INFO + System.lineSeparator();
        expected += TAB + TAB + "Usage: " + System.lineSeparator() + TAB + TAB + TAB
                + ListCommand.COMMAND_USAGE + System.lineSeparator();
        expected += System.lineSeparator();

        expected += TAB + TAB + DeleteCommand.COMMAND_INFO + System.lineSeparator();
        expected += TAB + TAB + "Usage: "  + System.lineSeparator() + TAB + TAB + TAB
                + DeleteCommand.COMMAND_USAGE + System.lineSeparator();
        expected += System.lineSeparator();

        expected += TAB + TAB + DoneCommand.COMMAND_INFO + System.lineSeparator();
        expected += TAB + TAB + "Usage: " + DoneCommand.COMMAND_USAGE + System.lineSeparator();
        expected += System.lineSeparator();

        expected += TAB + TAB + FindCommand.COMMAND_INFO + System.lineSeparator();
        expected += TAB + TAB + "Usage: " + FindCommand.COMMAND_USAGE + System.lineSeparator();
        expected += System.lineSeparator();

        expected += TAB + TAB + CalendarCommand.COMMAND_INFO + System.lineSeparator();
        expected += TAB + TAB + "Usage: " + CalendarCommand.COMMAND_USAGE + System.lineSeparator();
        expected += System.lineSeparator();

        expected += TAB + TAB + ClearCommand.COMMAND_INFO + System.lineSeparator();
        expected += TAB + TAB + "Usage: " + ClearCommand.COMMAND_USAGE + System.lineSeparator();
        expected += System.lineSeparator();

        expected += TAB + TAB + ExitCommand.COMMAND_INFO + System.lineSeparator();
        expected += TAB + TAB + "Usage: " + ExitCommand.COMMAND_USAGE + System.lineSeparator();
        expected += System.lineSeparator();

        for (int i = 0; i < output.length(); i++) {
            if (expected.charAt(i) != output.charAt(i)) {
                assertEquals(expected.charAt(i), output.charAt(i));
            }
        }

        boolean isEqual = output.equals(expected);
        assertTrue(isEqual);


    }
}