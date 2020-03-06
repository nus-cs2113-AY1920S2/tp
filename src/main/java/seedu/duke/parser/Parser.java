package seedu.duke.parser;

import seedu.duke.commands.ClearCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.ListCommand;

public class Parser {

    private static String[] phrases;
    private static String commandWord;

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        prepareCommand(userInput);
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return prepareAdd(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case HelpCommand.COMMAND_WORD: // Fallthrough

        default:
            return new HelpCommand();
        }
    }

    /**
     * Splits the user-specified command into space-separated strings and initialises the command word.
     *
     * @param command The user-specified command.
     */
    public static void prepareCommand(String command) {
        phrases = command.split(" ");
        commandWord = phrases[0];
    }

}
