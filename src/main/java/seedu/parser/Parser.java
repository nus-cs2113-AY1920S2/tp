package seedu.parser;

import seedu.commands.Command;
import seedu.commands.AddCardCommand;
import seedu.commands.DeleteCommand;
import seedu.commands.ExitCommand;
import seedu.commands.HelpCommand;
import seedu.commands.ListCommand;
import seedu.commands.QuizCommand;

/**
 * Contains methods to parse user inputs into sensible commands.
 */
public class Parser {

    private static final String QUESTION_ARG = "q/";

    private static final String ANSWER_ARG = "a/";

    private static final String INCORRECT_COMMAND = "Incorrect Command.";

    /**
     * Parses the user input into commands.
     * @return The command issued by the user.
     * @throws Exception An exception is issued if the command issued by the user is invalid.
     */
    public static Command parse(String userInput) throws Exception {
        String[] arguments = userInput.split(" ",2);

        switch (arguments[0]) {
        case AddCardCommand.COMMAND_WORD:
            return prepareAddCard(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDeleteCards(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case QuizCommand.COMMAND_WORD:
            return new QuizCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            throw new Exception(INCORRECT_COMMAND);
        }
    }

    /**
     * Parses the user input into arguments for the Add command.
     * @return Add Command.
     */
    private static Command prepareAddCard(String[] arguments) throws Exception {
        if (arguments.length < 2) {
            throw new Exception(INCORRECT_COMMAND);
        }

        String [] details;

        if (arguments[1].contains(QUESTION_ARG) && arguments[1].contains(ANSWER_ARG)) {
            details = arguments[1].split(ANSWER_ARG);
            details[0] = details[0].replace(QUESTION_ARG, "");
        } else {
            throw new Exception(INCORRECT_COMMAND);
        }

        return new AddCardCommand(details[0], details[1]);
    }

    /**
     * Parses the user input into arguments for the Delete command.
     * @return Delete Command.
     */
    private static Command prepareDeleteCards(String[] arguments) throws Exception {
        if (arguments.length < 2) {
            throw new Exception(INCORRECT_COMMAND);
        }

        try {
            int itemNumber = Integer.parseInt(arguments[1]);
            return new DeleteCommand(itemNumber - 1);
        } catch (NumberFormatException  e) {
            throw new Exception("The task item has to be an integer.");
        }
    }
}
