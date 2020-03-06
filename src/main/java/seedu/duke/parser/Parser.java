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
    public Command parseCommand(String userInput) {

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return prepareAdd(arguments);

        case MarkCommand.COMMAND_WORD:
            return prepareMark(arguments);

        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(arguments);

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

    private Command prepareMark(String arguments) {
        String[] words = arguments.trim().split(" ");
        if (words.length != 1) {
            throw new IncompleteCommandException;
        }
        int index = Integer.parseInt(words[0]) - 1;
        return new MarkCommand(index);
    }

    private Command prepareUnmark(String arguments) {
        String[] words = arguments.trim().split(" ");
        if (words.length != 1) {
            throw new IncompleteCommandException;
        }
        int index = Integer.parseInt(words[0]) - 1;
        return new UnmarkCommand(index);
    }
}
