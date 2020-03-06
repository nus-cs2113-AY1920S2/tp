package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandAdd;
import seedu.duke.commands.ExitCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */

    public Command parseCommand(String userInput) {

        String[] words = userInput.trim().split(" ", 2);  // split the input into command and arguments

        final String commandWord = words[0];
        final String arguments = userInput.replaceFirst(commandWord, "").trim();

        switch (commandWord) {

        case CommandAdd.COMMAND_WORD:

            return prepareAdd(arguments);

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

    private Command prepareAdd(String arguments) {

        final int indexOfAtPrefix = arguments.indexOf("i/");
        final int indexOfAtPrefix_2 = arguments.indexOf("p/");
        String description = arguments.substring(indexOfAtPrefix, indexOfAtPrefix_2);
        String prices = arguments.substring(indexOfAtPrefix_2 + 3).trim();
        double price = Double.parseDouble(prices);

        return new CommandAdd(description,price);
    }
}
