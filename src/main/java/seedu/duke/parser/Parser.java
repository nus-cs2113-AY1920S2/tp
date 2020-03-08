package seedu.duke.parser;

import seedu.duke.commands.MarkCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.UnmarkCommand;

public class Parser {
    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */

    private static Command newCommand;

    public Command parseCommand(String userInput) {

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return prepareAdd(arguments);

        case MarkCommand.COMMAND_WORD:
            createMarkCommand();
            break;

        case UnmarkCommand.COMMAND_WORD:
            createUnmarkCommand();
            break;

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case "bye":
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD: // Fallthrough
        default:
            return new HelpCommand();
        }
    }

    public static void createUnmarkCommand() {
        String[] words = arguments.trim().split(" ");
        if (words.length != 1) {
            throw new IncompleteCommandException;
        }
        int index = Integer.parseInt(words[0]) - 1;
        newCommand = new UnmarkCommand(index);
    }

    public static void createMarkCommand() {
        String[] words = arguments.trim().split(" ");
        if (words.length != 1) {
            throw new IncompleteCommandException;
        }
        int index = Integer.parseInt(words[0]) - 1;
        newCommand = new MarkCommand(index);
    }

}
