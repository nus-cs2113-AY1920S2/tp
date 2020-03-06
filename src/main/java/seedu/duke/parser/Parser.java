package seedu.duke.parser;

import seedu.duke.commands.BoughtCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;

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

        case BoughtCommand.COMMAND_WORD:
            return prepareBought(arguments);

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

    private Command prepareBought(String arguments) {
        String[] words = arguments.trim().split(" ");
        if (words.length != 1) {
            throw new IncompleteCommandException;
        }
        int index = Integer.parseInt(words[0]);
        return new BoughtCommand(index);
    }
}
