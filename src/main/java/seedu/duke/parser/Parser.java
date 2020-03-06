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
        case DeleteCommand.COMMAND_WORD:
            createDeleteCommand();
            break;
        case SetBudgetCommand.COMMAND_WORD:
            createSetBudgetCommand();
            break;
        case ExitCommand.COMMAND_WORD:
            createExitCommand();
            break;
        }
        return newCommand;
    }

    public static void createExitCommand() {
        newCommand = new ExitCommand();
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
