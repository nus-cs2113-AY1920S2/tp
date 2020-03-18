package seedu.parser;

import seedu.cards.Card;
import seedu.commands.*;
import seedu.exception.EscException;

/**
 * Contains methods to parse user inputs into sensible commands.
 */
public class Parser {

    private static final String QUESTION_ARG = "q/";

    private static final String ANSWER_ARG = "a/";

    private static final String SUBJECT_ARG = "s/";

    private static final String CARD_ARG = "c/";

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
        case AddSubjectCommand.COMMAND_WORD:
            return prepareAddSubject(arguments);

        case AddCardCommand.COMMAND_WORD:
            return prepareAddCard(arguments);

        case DeleteSubjectCommand.COMMAND_WORD:
            return prepareDeleteSubject(arguments);

        case DeleteCardCommand.COMMAND_WORD:
            return prepareDeleteCard(arguments);

        case ListSubjectCommand.COMMAND_WORD:
            return new ListSubjectCommand();

        case ListCardCommand.COMMAND_WORD:
            return prepareListCard(arguments);

        case QuizCommand.COMMAND_WORD:
            return prepareQuiz(arguments);

        case ScoreCommand.COMMAND_WORD:
            return prepareScore(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        default:
            throw new EscException(INCORRECT_COMMAND + HelpCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Parses the user input into arguments for the AddSubject command.
     * @return AddSubject Command.
     */
    private static Command prepareAddSubject(String[] arguments) throws EscException {
        checkNumberOfArguments(arguments, AddSubjectCommand.MESSAGE_USAGE);
        checkArgumentPrefixes(arguments[1], AddSubjectCommand.MESSAGE_USAGE, SUBJECT_ARG);

        String subjectName = arguments[1].replace(SUBJECT_ARG, "");

        if (subjectName.trim().isEmpty()) {
            throw new EscException("A subject name is required.");
        }

        return new AddSubjectCommand(subjectName);
    }

    /**
     * Parses the user input into arguments for the AddCard command.
     * @return AddCard Command.
     */
    private static Command prepareAddCard(String[] arguments) throws EscException {
        checkNumberOfArguments(arguments, AddCardCommand.MESSAGE_USAGE);
        checkArgumentPrefixes(arguments[1], AddCardCommand.MESSAGE_USAGE, SUBJECT_ARG, QUESTION_ARG, ANSWER_ARG);

        int subjectIndex = getSubjectIndex(arguments[1]);

        String[] cardArgs = getQuestionAndAnswer(arguments[1],AddCardCommand.MESSAGE_USAGE);

        Card cardToAdd = new Card(cardArgs[0],cardArgs[1]);

        return new AddCardCommand(subjectIndex, cardToAdd);
    }

    /**
     * Parses the user input into arguments for the DeleteSubject command.
     * @return DeleteSubject Command.
     */
    private static Command prepareDeleteSubject(String[] arguments) throws EscException {
        checkNumberOfArguments(arguments, DeleteSubjectCommand.MESSAGE_USAGE);
        checkArgumentPrefixes(arguments[1], DeleteSubjectCommand.MESSAGE_USAGE, SUBJECT_ARG);
        int subjectIndex = getSubjectIndex(arguments[1]);

        return new DeleteSubjectCommand(subjectIndex);
    }

    /**
     * Parses the user input into arguments for the DeleteCard command.
     * @return DeleteCard Command.
     */
    private static Command prepareDeleteCard(String[] arguments) throws EscException {
        checkNumberOfArguments(arguments, DeleteCardCommand.MESSAGE_USAGE);
        checkArgumentPrefixes(arguments[1], DeleteCardCommand.MESSAGE_USAGE, SUBJECT_ARG, CARD_ARG);

        int subjectIndex = getSubjectIndex(arguments[1]);
        int cardIndex = getCardIndex(arguments[1]);
        return new DeleteCardCommand(subjectIndex, cardIndex);
    }

    /**
     * Parses the user input into arguments for the ListCard command.
     * @return ListCard Command.
     */
    private static Command prepareListCard(String[] arguments) throws EscException {
        checkNumberOfArguments(arguments, ListCardCommand.MESSAGE_USAGE);
        checkArgumentPrefixes(arguments[1], ListCardCommand.MESSAGE_USAGE, SUBJECT_ARG);
        int subjectIndex = getSubjectIndex(arguments[1]);

        return new ListCardCommand(subjectIndex);
    }

    /**
     * Parses the user input into arguments for the Quiz command.
     * @return Quiz Command.
     */
    private static Command prepareQuiz(String[] arguments) throws EscException {
        checkNumberOfArguments(arguments, QuizCommand.MESSAGE_USAGE);
        checkArgumentPrefixes(arguments[1], QuizCommand.MESSAGE_USAGE, SUBJECT_ARG);
        int subjectIndex = getSubjectIndex(arguments[1]);

        return new QuizCommand(subjectIndex);
    }

    /**
     * Parses the user input into arguments for the Quiz command.
     * @return Quiz Command.
     */
    private static Command prepareScore(String[] arguments) throws EscException {
        checkNumberOfArguments(arguments, ScoreCommand.MESSAGE_USAGE);
        checkArgumentPrefixes(arguments[1], ScoreCommand.MESSAGE_USAGE, SUBJECT_ARG);
        int subjectIndex = getSubjectIndex(arguments[1]);

        return new ScoreCommand(subjectIndex);
    }

    private static int getSubjectIndex(String argument) throws EscException{
        String argWithoutPrefixes = argument.split(QUESTION_ARG)[0].split(CARD_ARG)[0];
        String subjectIndexString = argWithoutPrefixes.replace(SUBJECT_ARG,"").trim();

        if (subjectIndexString.trim().isEmpty()) {
            throw new EscException("The subject index is required.");
        }

        try {
            return Integer.parseInt(subjectIndexString);
        } catch (NumberFormatException  e) {
            throw new EscException("The subject index has to be an integer.");
        }
    }

    private static int getCardIndex(String argument) throws EscException{
        String argWithoutPrefixes = argument.split(CARD_ARG)[1];
        String cardIndexString = argWithoutPrefixes.replace(CARD_ARG,"").trim();

        if (cardIndexString.trim().isEmpty()) {
            throw new EscException("The card index is required");
        }

        try {
            return Integer.parseInt(cardIndexString);
        } catch (NumberFormatException  e) {
            throw new EscException("The card index has to be an integer.");
        }
    }

    private static void checkNumberOfArguments(String[] arguments, String errorMessage) throws EscException {
        if (arguments.length < 2) {
            throw new EscException(errorMessage);
        }
    }

    private static void checkArgumentPrefixes(String argument, String errorMessage, String ...prefixes) throws EscException {
        for (String p : prefixes) {
            if (!argument.contains(p)) {
                throw new EscException(errorMessage);
            }
        }
    }

    private static String[] getQuestionAndAnswer(String argument, String errorMessage) throws EscException {
        String secondaryArgs = argument.split(QUESTION_ARG)[1];
        String [] cardArgs = secondaryArgs.split(ANSWER_ARG);

        if (cardArgs.length < 2){
            throw new EscException(errorMessage);
        }

        cardArgs[0] = cardArgs[0].trim();
        cardArgs[1] = cardArgs[1].trim();

        if (cardArgs[0].isEmpty() || cardArgs[1].isEmpty()) {
            throw new EscException(errorMessage);
        }
        return cardArgs;
    }
}
