package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.SetBudgetCommand;
import seedu.duke.commands.ExitCommand;

public class Parser {

    private static Command newCommand;
    private static String[] phrases;
    private static String commandWord;
    private static int index;
    private static double amount;

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
            createDeleteCommand();
            return newCommand;
            break;

        case SetBudgetCommand.COMMAND_WORD:
            if (phrases[1].contains("b/")) {
                createSetBudgetCommand();
                return newCommand;
            }
            break;

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

    /**
     * Initialises the SetBudgetCommand.
     */
    public static void createSetBudgetCommand() {
        amount = Double.parseDouble(phrases[1].substring(2));
        newCommand = new SetBudgetCommand(amount);
    }

    /**
     * Initialises the DeleteCommand.
     */
    public static void createDeleteCommand() {
        index = Integer.parseInt(phrases[1]);
        newCommand = new DeleteCommand(index);
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
