package seedu.parser;

import seedu.cards.Card;
import seedu.commands.Command;
import seedu.commands.AddCardCommand;
import seedu.commands.DeleteCommand;
import seedu.commands.ExitCommand;
import seedu.commands.HelpCommand;
import seedu.commands.ListCommand;
import seedu.commands.QuizCommand;
import seedu.exception.EscException;

/**
 * Contains methods to parse user inputs into sensible commands.
 */
public class Parser {

    private static final String QUESTION_ARG = "q/";

    private static final String ANSWER_ARG = "a/";

    public static final String INCORRECT_COMMAND = "Incorrect Command\n";

    /**
     * Parses the user input into commands.
     * @return The command issued by the user.
     * @throws EscException An exception is issued if the command issued by the user is invalid.
     */
    public static Command parse(String userInput) throws EscException {
        String[] arguments = userInput.split(" ",2);
        String command = arguments[0].trim();

        switch (command) {
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
            throw new EscException(INCORRECT_COMMAND + HelpCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Parses the user input into arguments for the Add command.
     * @return Add Command.
     */
    private static Command prepareAddCard(String[] arguments) throws EscException {
        if (arguments.length < 2) {
            throw new EscException(AddCardCommand.MESSAGE_USAGE);
        }

        String [] details;

        if (arguments[1].contains(QUESTION_ARG) && arguments[1].contains(ANSWER_ARG)) {
            details = arguments[1].split(ANSWER_ARG);
        } else {
            throw new EscException(AddCardCommand.MESSAGE_USAGE);
        }

        if (details.length < 2) {
            throw new EscException(AddCardCommand.MESSAGE_USAGE);
        }

        details[0] = details[0].replace(QUESTION_ARG, "").trim();
        details[1] = details[1].trim();

        if (details[0].isEmpty() || details[1].isEmpty()) {
            throw new EscException(AddCardCommand.MESSAGE_USAGE);
        }

        Card cardToAdd = new Card(details[0],details[1]);

        return new AddCardCommand(cardToAdd);
    }

    /**
     * Parses the user input into arguments for the Delete command.
     * @return Delete Command.
     */
    private static Command prepareDeleteCards(String[] arguments) throws EscException {
        if (arguments.length < 2) {
            throw new EscException(DeleteCommand.MESSAGE_USAGE);
        }

        if (arguments[1].trim().isEmpty()) {
            throw new EscException(DeleteCommand.MESSAGE_USAGE);
        }

        try {
            int itemNumber = Integer.parseInt(arguments[1]);
            return new DeleteCommand(itemNumber - 1);
        } catch (NumberFormatException  e) {
            throw new EscException("The card item has to be an integer.");
        }
    }
}
