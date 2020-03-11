package seedu.duke;

import command.AssignmentCommand;
import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.EventCommand;
import command.ExitCommand;
import command.HelpCommand;
import command.ListCommand;

import common.Messages;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    /** Date Tests. */
    @Test
    public void parseDate_correctFormat_success() {
        LocalDateTime parsedDateTime = Parser.parseDate("22/02/20 2359");
        LocalDateTime expectedDateTime = LocalDateTime.parse("2020-02-22T23:59:00");
        assertEquals(parsedDateTime, expectedDateTime);
    }

    @Test
    public void parseDate_correctFormatWithSpaces_success() {
        LocalDateTime parsedDateTime = Parser.parseDate("22/02/20       2359");
        LocalDateTime expectedDateTime = LocalDateTime.parse("2020-02-22T23:59:00");
        assertEquals(parsedDateTime, expectedDateTime);
    }

    @Test
    public void parseDate_invalidDateTime_throwDateTimeParseException() {
        assertThrows(DateTimeParseException.class, () -> Parser.parseDate("32/02/20 1111"));
        assertThrows(DateTimeParseException.class, () -> Parser.parseDate("20/02/20 2500"));
        assertThrows(DateTimeParseException.class, () -> Parser.parseDate("32/O2/2O 1111"));
    }

    @Test
    public void parseDate_incorrectFormat_throwIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> Parser.parseDate("22/02/20"));
        assertThrows(IndexOutOfBoundsException.class, () -> Parser.parseDate("1800"));
    }

    /** Unknown Command Tests. */
    @Test
    public void testUnknownCommand() {
        TaskList testTaskList = new TaskList();
        Ui testUi = new Ui();
        assertEquals(Parser.parseCommand("foo 2").execute(testTaskList, testUi).feedbackToUser,
                String.format(Messages.INCORRECT_COMMAND_ERROR, Messages.UNKNOWN_COMMAND_ERROR));
    }

    /** Assignment Command Tests. */
    @Test
    public void parseAssignmentCommand_expectedInput_success() {
        Command parsedCommand = Parser.parseCommand(AssignmentCommand.ASSIGNMENT_COMMAND_WORD
                + " n/name m/cs2113T d/22/01/20 1800 c/comments");
        assertTrue((parsedCommand instanceof AssignmentCommand));
    }

    @Test
    public void parseAssignmentCommand_extraWhitespacePresent_success() {
        Command parsedCommand = Parser.parseCommand(
                AssignmentCommand.ASSIGNMENT_COMMAND_WORD + "  "
                        + "n/   long long name   "
                        + "m/   cs2113T  "
                        + "d/  22/01/20   1800 "
                        + "c/  comments with spaces   "
        );
        assertTrue((parsedCommand instanceof AssignmentCommand));
    }

    @Test
    public void parseAssignmentCommand_missingParameters_returnIncorrectCommand() {
        assertEquals(Parser.parseCommand(AssignmentCommand.ASSIGNMENT_COMMAND_WORD
                        + " n/ASS m/cs1010 d/30/02/20 1111")
                        .execute(new TaskList(), new Ui()).feedbackToUser,
                String.format(Messages.INCORRECT_COMMAND_ERROR, Messages.ASSIGN_INCORRECT_FORMAT_ERROR));
    }

    /** Event Command Tests. */
    @Test
    public void parseEventCommand_expectedInput_success() {
        Command parsedCommand = Parser.parseCommand(EventCommand.EVENT_COMMAND_WORD
                + " n/name l/somewhere ah d/22/01/20 1800 c/comment");
        assertTrue((parsedCommand instanceof EventCommand));
    }

    @Test
    public void parseEventCommand_extraWhitespacePresent_success() {
        Command parsedCommand = Parser.parseCommand(
                EventCommand.EVENT_COMMAND_WORD + " "
                        + "n/   long long name   "
                        + "l/   somewhere over the rainbow   "
                        + "d/  22/01/20   1800  "
                        + "c/  comments with spaces   "
        );
        assertTrue((parsedCommand instanceof EventCommand));
    }

    @Test
    public void parseEventCommand_missingParameters_returnIncorrectCommand() {
        assertEquals(Parser.parseCommand(EventCommand.EVENT_COMMAND_WORD + " n/EVE l/LOC d/30/02/20 1111 c/")
                        .execute(new TaskList(), new Ui()).feedbackToUser,
                String.format(Messages.INCORRECT_COMMAND_ERROR, Messages.EVENT_INCORRECT_FORMAT_ERROR));
    }

    /** Delete Command Tests. */
    @Test
    public void parseDeleteCommand_expectedInput_success() {
        Command parsedCommand = Parser.parseCommand(DeleteCommand.DELETE_COMMAND_WORD + " 123");
        assertTrue(parsedCommand instanceof DeleteCommand);
    }

    @Test
    public void parseDeleteCommand_extraWhitespacePresent_success() {
        Command parsedCommand = Parser.parseCommand(DeleteCommand.DELETE_COMMAND_WORD + "    123    ");
        assertTrue(parsedCommand instanceof DeleteCommand);
    }

    @Test
    public void parseDeleteCommand_missingParameter_returnIncorrectCommand() {
        Command parsedCommand = Parser.parseCommand(DeleteCommand.DELETE_COMMAND_WORD);
        assertEquals(parsedCommand.execute(new TaskList(), new Ui()).feedbackToUser,
                String.format(Messages.INCORRECT_COMMAND_ERROR, Messages.DELETE_INSUFFICIENT_ARGS_ERROR));
    }

    @Test
    public void parseDeleteCommand_invalidParameter_returnIncorrectCommand() {
        Command parsedCommand = Parser.parseCommand(DeleteCommand.DELETE_COMMAND_WORD + " abc");
        assertEquals(parsedCommand.execute(new TaskList(), new Ui()).feedbackToUser,
                String.format(Messages.INCORRECT_COMMAND_ERROR, Messages.NUM_FORMAT_ERROR));
    }

    /** Done Command Tests. */
    @Test
    public void parseDoneCommand_expectedInput_success() {
        Command parsedCommand = Parser.parseCommand(DoneCommand.DONE_COMMAND_WORD + " 123");
        assertTrue(parsedCommand instanceof DoneCommand);
    }

    @Test
    public void parseDoneCommand_extraWhitespacePresent_success() {
        Command parsedCommand = Parser.parseCommand(DoneCommand.DONE_COMMAND_WORD + "    123    ");
        assertTrue(parsedCommand instanceof DoneCommand);
    }

    @Test
    public void parseDoneCommand_missingParameter_returnIncorrectCommand() {
        Command parsedCommand = Parser.parseCommand(DoneCommand.DONE_COMMAND_WORD);
        assertEquals(parsedCommand.execute(new TaskList(), new Ui()).feedbackToUser,
                String.format(Messages.INCORRECT_COMMAND_ERROR, Messages.DONE_INSUFFICIENT_ARGS_ERROR));
    }

    @Test
    public void parseDoneCommand_invalidParameter_returnIncorrectCommand() {
        Command parsedCommand = Parser.parseCommand(DoneCommand.DONE_COMMAND_WORD + " abc");
        assertEquals(parsedCommand.execute(new TaskList(), new Ui()).feedbackToUser,
                String.format(Messages.INCORRECT_COMMAND_ERROR, Messages.NUM_FORMAT_ERROR));
    }

    /** Help Command Tests. */
    @Test
    public void parseHelpCommand_expectedInput_success() {
        Command parsedCommand = Parser.parseCommand(HelpCommand.HELP_COMMAND_WORD);
        assertTrue(parsedCommand instanceof HelpCommand);
    }

    /** List Command Tests. */
    @Test
    public void parseListCommand_expectedInput_success() {
        Command parsedCommand = Parser.parseCommand(ListCommand.LIST_COMMAND_WORD);
        assertTrue(parsedCommand instanceof ListCommand);
        parsedCommand = Parser.parseCommand(ListCommand.LIST_COMMAND_WORD + " today");
        assertTrue(parsedCommand instanceof ListCommand);
        parsedCommand = Parser.parseCommand(ListCommand.LIST_COMMAND_WORD + " week");
        assertTrue(parsedCommand instanceof ListCommand);
        parsedCommand = Parser.parseCommand(ListCommand.LIST_COMMAND_WORD + " incomplete assignments");
        assertTrue(parsedCommand instanceof ListCommand);
        parsedCommand = Parser.parseCommand(ListCommand.LIST_COMMAND_WORD + " upcoming events");
        assertTrue(parsedCommand instanceof ListCommand);
    }

    /** Exit Command Tests. */
    @Test
    public void parseExitCommand_expectedInput_success() {
        Command parsedCommand = Parser.parseCommand(ExitCommand.EXIT_COMMAND_WORD);
        assertTrue(parsedCommand instanceof ExitCommand);
    }
}
