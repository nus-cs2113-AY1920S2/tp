package seedu.parser;

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

public class Parser {

    public static final int COMMAND_LENGTH = 1;

    /**
     * Parses user input into command for execution.
     *
     * @param userCommand user input including command word and parameters
     * @return  command based on user input
     */
    public static Command parseCommand(String userCommand) {
        String[] commandSections = userCommand.split(" ");
        String command = commandSections[0].trim().toLowerCase();

        switch (command) {

        case ListCommand.COMMAND_WORD:
            return new ListCommand(userCommand);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(userCommand);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddCommand.COMMAND_WORD:
            return new AddCommand(userCommand);

        case FindCommand.COMMAND_WORD:
            return new FindCommand(userCommand);

        case EditCommand.COMMAND_WORD:
            return new EditCommand(userCommand);

        case ExitCommand.COMMAND_WORD:
            if (commandSections.length != COMMAND_LENGTH) {
                return new FailedCommand(ExitCommand.COMMAND_WORD,ExitCommand.ERROR_FEEDBACK);
            }
            return new ExitCommand();

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case DoneCommand.COMMAND_WORD:
            if (commandSections.length < 2 || commandSections.length > 2) {
                return new FailedCommand(DoneCommand.COMMAND_WORD,
                        commandSections.length - COMMAND_LENGTH, DoneCommand.ARGUMENT_COUNT);
            }
            return new DoneCommand(commandSections[1]);

        case CalendarCommand.COMMAND_WORD:
            if (commandSections.length == 2) {
                return new CalendarCommand(commandSections[1]);
            }
            if (commandSections.length == 1) {
                return new CalendarCommand(null);
            }
            return new FailedCommand(CalendarCommand.COMMAND_WORD,
                    commandSections.length - COMMAND_LENGTH, CalendarCommand.ARGUMENT_COuNT);

        default:
            return new HelpCommand(String.format(Messages.MESSAGE_INVALID_COMMAND + Constants.NEW_LINE, command));
        }
    }


    /**
     * Check if String is a valid integer.
     *
     * @param value Input string for checking
     * @return boolean if string is valid
     */
    public static boolean isInteger(String value) {
        for (char c : value.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

}
