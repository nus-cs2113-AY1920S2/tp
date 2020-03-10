package seedu.duke;

import common.Messages;
import org.junit.jupiter.api.Test;
import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testUnknownCommand() throws Exception {
        TaskList testTaskList = new TaskList();
        Ui testUi = new Ui();
        assertEquals(Parser.parseCommand("foo 2").execute(testTaskList, testUi).feedbackToUser,
                String.format(Messages.INCORRECT_COMMAND_ERROR, Messages.UNKNOWN_COMMAND_ERROR));
    }

    @Test
    public void testInvalidDate() {
        assertThrows(DateTimeParseException.class, () -> Parser.parseDate("32/02/20 1111"));
        assertThrows(DateTimeParseException.class, () -> Parser.parseDate("32/O2/2O 1111"));
    }

    @Test
    public void testInvalidAssignParams() throws Exception {
        TaskList testTaskList = new TaskList();
        Ui testUi = new Ui();
        assertEquals(Parser.parseCommand("assignment n/ASS m/cs1010 d/30/02/20 1111")
                        .execute(testTaskList, testUi).feedbackToUser,
                String.format(Messages.INCORRECT_COMMAND_ERROR, Messages.ASSIGN_INCORRECT_FORMAT_ERROR));
    }

    @Test
    public void testInvalidEventParams() throws Exception {
        TaskList testTaskList = new TaskList();
        Ui testUi = new Ui();
        assertEquals(Parser.parseCommand("event n/EVE l/LOC d/30/02/20 1111 c/")
                        .execute(testTaskList, testUi).feedbackToUser,
                String.format(Messages.INCORRECT_COMMAND_ERROR, Messages.EVENT_INCORRECT_FORMAT_ERROR));
    }
}
