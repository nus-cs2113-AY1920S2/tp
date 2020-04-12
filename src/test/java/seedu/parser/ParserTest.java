package seedu.parser;

import org.junit.jupiter.api.Test;


import seedu.command.AddCommand;
import seedu.command.CalendarCommand;
import seedu.command.ClearCommand;
import seedu.command.DeleteCommand;
import seedu.command.DoneCommand;
import seedu.command.EditCommand;
import seedu.command.ExitCommand;
import seedu.command.FindCommand;
import seedu.command.FailedCommand;
import seedu.command.HelpCommand;
import seedu.command.ListCommand;
import seedu.command.Command;
import seedu.common.Constants;
import seedu.common.Messages;
import seedu.exception.ProjException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.command.HelpCommand.HELP_LEGEND;
import static seedu.common.Constants.TAB;


class ParserTest {

    @Test
    public void isInteger_invalidInteger_returnFalse() {

        String invalidNumber = "1568..122";
        boolean isInvalidInt = Parser.isInteger(invalidNumber);
        assertFalse(isInvalidInt);

        invalidNumber = "abcde";
        isInvalidInt = Parser.isInteger(invalidNumber);
        assertFalse(isInvalidInt);

        invalidNumber = "1234.12";
        isInvalidInt = Parser.isInteger(invalidNumber);
        assertFalse(isInvalidInt);

        invalidNumber = "12.";
        isInvalidInt = Parser.isInteger(invalidNumber);
        assertFalse(isInvalidInt);

        invalidNumber = "0x123";
        isInvalidInt = Parser.isInteger(invalidNumber);
        assertFalse(isInvalidInt);

    }


    @Test
    public void parseCommand_ListCommand_createCommand() {

        Command command;
        String input = "list";

        command = Parser.parseCommand(input);
        boolean isListCommand = command instanceof ListCommand;
        assertTrue(isListCommand);


        input = "LiST t/12:00-13:00";
        command = Parser.parseCommand(input);
        isListCommand = command instanceof ListCommand;
        assertTrue(isListCommand);

        input = "LIST c/Category";
        command = Parser.parseCommand(input);
        isListCommand = command instanceof ListCommand;
        assertTrue(isListCommand);
    }

    @Test
    public void parseCommand_DeleteCommand_createCommand() {

        Command command;
        String input = "delete";

        command = Parser.parseCommand(input);
        boolean isAddCommand = command instanceof DeleteCommand;
        assertTrue(isAddCommand);


        input = "DElEte 2";
        command = Parser.parseCommand(input);
        isAddCommand = command instanceof DeleteCommand;
        assertTrue(isAddCommand);
    }

    @Test
    public void parseCommand_HelpCommand_createCommand() {

        Command command;
        String input = "help";

        command = Parser.parseCommand(input);
        boolean isHelpCommand = command instanceof HelpCommand;
        assertTrue(isHelpCommand);


        input = "HeLP";
        command = Parser.parseCommand(input);
        isHelpCommand = command instanceof HelpCommand;
        assertTrue(isHelpCommand);
    }

    @Test
    public void parseCommand_AddCommand_createCommand() {

        Command command;
        String input = "ADD n/meeting i/project 3";

        command = Parser.parseCommand(input);
        boolean isAddCommand = command instanceof AddCommand;
        assertTrue(isAddCommand);


        input = "add";
        command = Parser.parseCommand(input);
        isAddCommand = command instanceof AddCommand;
        assertTrue(isAddCommand);
    }


    @Test
    public void parseCommand_FindCommand_createCommand() {

        Command command;
        String input = "fINd hello";

        command = Parser.parseCommand(input);
        boolean isFindCommand = command instanceof FindCommand;
        assertTrue(isFindCommand);


        input = "FIND meeting";
        command = Parser.parseCommand(input);
        isFindCommand = command instanceof FindCommand;
        assertTrue(isFindCommand);

        input = "FINDmeeting";
        command = Parser.parseCommand(input);
        isFindCommand = command instanceof FindCommand;
        assertFalse(isFindCommand);
    }

    @Test
    public void parseCommand_EditCommand_createCommand() {

        Command command;
        String input = "EDit 8 n/meeting i/project 3";

        command = Parser.parseCommand(input);
        boolean isEditCommand = command instanceof EditCommand;
        assertTrue(isEditCommand);


        input = "ediT";
        command = Parser.parseCommand(input);
        isEditCommand = command instanceof EditCommand;
        assertTrue(isEditCommand);
    }

    @Test
    public void parseCommand_ClearCommand_createCommand() {

        Command command;
        String input = "ClC";

        command = Parser.parseCommand(input);
        boolean isClearCommand = command instanceof ClearCommand;
        assertTrue(isClearCommand);


        input = "clear";
        command = Parser.parseCommand(input);
        isClearCommand = command instanceof ClearCommand;
        assertFalse(isClearCommand);
    }


    @Test
    public void parseCommand_DoneCommand_createCommand() {

        Command command;
        String input = "DoNe 1";

        command = Parser.parseCommand(input);
        boolean isDoneCommand = command instanceof DoneCommand;
        assertTrue(isDoneCommand);


        input = "donE 3";
        command = Parser.parseCommand(input);
        isDoneCommand = command instanceof DoneCommand;
        assertTrue(isDoneCommand);
    }

    @Test
    public void parserCommand_InvalidDoneCommand_returnFailedCommand() {

        Command command;
        String input = "DoNe";

        command = Parser.parseCommand(input);
        boolean isFailedCommand = command instanceof FailedCommand;
        assertTrue(isFailedCommand);

        input = "DoNe 3 2 3";

        command = Parser.parseCommand(input);
        isFailedCommand = command instanceof FailedCommand;
        assertTrue(isFailedCommand);

    }


    @Test
    public void parseCommand_ExitCommand_createCommand() {

        Command command;
        String input = "BYE";

        command = Parser.parseCommand(input);
        boolean isExitCommand = command instanceof ExitCommand;
        assertTrue(isExitCommand);


        input = "byE";
        command = Parser.parseCommand(input);
        isExitCommand = command instanceof ExitCommand;
        assertTrue(isExitCommand);
    }


    @Test
    public void parseCommand_ExitCommandWithParameter_exceptionThrown() {

        Command command;
        String input = "bye 3";

        command = Parser.parseCommand(input);

        String output = "";
        try {
            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String expected = "  [Error][bye]: No arguments required" + Constants.NEW_LINE;
        assertEquals(output,expected);

    }

    @Test
    public void parseCommand_CalendarCommand_createCommand() {

        Command command;
        String input = "calenDAr";

        command = Parser.parseCommand(input);
        boolean isCalendarCommand = command instanceof CalendarCommand;
        assertTrue(isCalendarCommand);


        input = "CALENDAR 14";
        command = Parser.parseCommand(input);
        isCalendarCommand = command instanceof CalendarCommand;
        assertTrue(isCalendarCommand);

    }

    @Test
    public void parseCommand_InvalidCalendarCommand_returnFailedCommand() {

        Command command;
        String input = "calenDAr 1 2 3 4 5";

        command = Parser.parseCommand(input);
        boolean isFailedCommand = command instanceof FailedCommand;
        assertTrue(isFailedCommand);

    }

    @Test
    public void parseCommand_InvalidCommand_errorMessage() {

        Command command;
        String input = "exit";

        command = Parser.parseCommand(input);

        String output = "";
        try {
            output = command.execute().getFeedback();
        } catch (ProjException e) {
            assertTrue(false);
        }

        String expected = TAB + String.format(Messages.MESSAGE_INVALID_COMMAND, input) + Constants.NEW_LINE;

        expected += HELP_LEGEND;

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

        assertEquals(expected, output);

    }


}