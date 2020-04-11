package seedu.parser;

import seedu.cards.Card;
import seedu.commands.Command;
import seedu.commands.AddSubjectCommand;
import seedu.commands.AddCardCommand;
import seedu.commands.EditCardCommand;
import seedu.commands.DeleteCardCommand;
import seedu.commands.DeleteSubjectCommand;
import seedu.commands.ListCardCommand;
import seedu.commands.ListSubjectCommand;
import seedu.commands.ScoreCommand;
import seedu.commands.QuizCommand;
import seedu.commands.HelpCommand;
import seedu.commands.ExitCommand;
import seedu.commands.ShowUpcomingCommand;
import seedu.commands.AddEventCommand;
import seedu.commands.DeleteEventCommand;
import seedu.commands.ListEventCommand;
import seedu.events.Event;
import seedu.exception.EscException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Contains methods to parse user inputs into sensible commands.
 */
public class Parser {

    private static final String QUESTION_ARG = " q/";

    private static final String ANSWER_ARG = " a/";

    private static final String SUBJECT_ARG = " s/";

    private static final String CARD_ARG = " c/";

    private static final String QUIZ_ARG = " n/";

    private static final String EVENT_ARG = " e/";

    private static final String DATE_ARG = " d/";

    public static final String INCORRECT_COMMAND = "Incorrect Command\n";

    /**
     * Parses the user input into commands.
     * @return The command issued by the user.
     * @throws EscException An exception is issued if the command issued by the user is invalid.
     */
    public static Command parse(String userInput) throws EscException {
        String userInputTrimmed = userInput.trim();
        String[] arguments = userInputTrimmed.split(" ",2);
        String command = arguments[0].trim().toLowerCase();

        switch (command) {
        case AddSubjectCommand.COMMAND_WORD:
            return prepareAddSubject(arguments);

        case AddCardCommand.COMMAND_WORD:
            return prepareAddCard(arguments);

        case EditCardCommand.COMMAND_WORD:
            return prepareEditCard(arguments);

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

        case AddEventCommand.COMMAND_WORD:
            return prepareAddEvent(arguments);

        case DeleteEventCommand.COMMAND_WORD:
            return prepareDeleteEvent(arguments);

        case ListEventCommand.COMMAND_WORD:
            return new ListEventCommand();

        case ShowUpcomingCommand.COMMAND_WORD:
            return prepareShowUpcoming(arguments);

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
        arguments[1] = " " + arguments[1];
        checkArgumentPrefixes(arguments[1], AddSubjectCommand.MESSAGE_USAGE, SUBJECT_ARG);

        String subjectName = arguments[1].replaceFirst(SUBJECT_ARG, "").trim();

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
        arguments[1] = " " + arguments[1];
        checkArgumentPrefixes(arguments[1], AddCardCommand.MESSAGE_USAGE, SUBJECT_ARG, QUESTION_ARG, ANSWER_ARG);

        int subjectIndex = getSubjectIndex(arguments[1]);

        String[] cardArgs = getQuestionAndAnswer(arguments[1],AddCardCommand.MESSAGE_USAGE);

        Card cardToAdd = new Card(cardArgs[0],cardArgs[1]);

        return new AddCardCommand(subjectIndex, cardToAdd);
    }

    private static Command prepareEditCard(String[] arguments) throws EscException {
        checkNumberOfArguments(arguments, EditCardCommand.MESSAGE_USAGE);
        arguments[1] = " " + arguments[1];

        checkArgumentPrefixes(arguments[1], EditCardCommand.MESSAGE_USAGE, SUBJECT_ARG,
                            CARD_ARG, QUESTION_ARG, ANSWER_ARG);


        int subjectIndex = getSubjectIndex(arguments[1]);
        int cardIndex = getCardIndex(arguments[1]);


        String[] cardArgs = getQuestionAndAnswer(arguments[1],EditCardCommand.MESSAGE_USAGE);

        Card cardToAdd = new Card(cardArgs[0],cardArgs[1]);

        return new EditCardCommand(subjectIndex, cardIndex, cardToAdd);
    }

    /**
     * Parses the user input into arguments for the DeleteSubject command.
     * @return DeleteSubject Command.
     */
    private static Command prepareDeleteSubject(String[] arguments) throws EscException {
        checkNumberOfArguments(arguments, DeleteSubjectCommand.MESSAGE_USAGE);
        arguments[1] = " " + arguments[1];
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
        arguments[1] = " " + arguments[1];
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
        arguments[1] = " " + arguments[1];
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
        arguments[1] = " " + arguments[1];
        checkArgumentPrefixes(arguments[1], QuizCommand.MESSAGE_USAGE, SUBJECT_ARG);
        int subjectIndex = getSubjectIndex(arguments[1]);
        int numToQuiz = getNumberToQuiz(arguments[1]);

        return new QuizCommand(subjectIndex, numToQuiz);
    }

    /**
     * Parses the user input into arguments for the Quiz command.
     * @return Quiz Command.
     */
    private static Command prepareScore(String[] arguments) throws EscException {
        checkNumberOfArguments(arguments, ScoreCommand.MESSAGE_USAGE);
        arguments[1] = " " + arguments[1];
        checkArgumentPrefixes(arguments[1], ScoreCommand.MESSAGE_USAGE, SUBJECT_ARG);

        String[] trimmedArguments = arguments[1].trim().split(" ");

        if (trimmedArguments.length > 1) {
            throw new EscException("Too many inputs. " + ScoreCommand.MESSAGE_USAGE);
        }

        int subjectIndex = getSubjectIndex(" " + trimmedArguments[0]);
        return new ScoreCommand(subjectIndex);
    }

    /**
     * Parses the user input into arguments for the AddEvent command.
     * @return AddEventDate Command.
     */
    private static Command prepareAddEvent(String[] arguments) throws EscException {
        checkNumberOfArguments(arguments, AddEventCommand.MESSAGE_USAGE);
        arguments[1] = " " + arguments[1];
        checkArgumentPrefixes(arguments[1], AddEventCommand.MESSAGE_USAGE, DATE_ARG, EVENT_ARG);

        String eventTopic = getEventTopic(arguments[1]);
        LocalDate eventDate = getEventDate(arguments[1]);

        Event event = new Event(eventTopic, eventDate);

        return new AddEventCommand(event);
    }

    /**
     * Parses the user input into arguments for the DeleteEvent command.
     * @return DeleteEvent Command.
     */
    private static Command prepareDeleteEvent(String[] arguments) throws EscException {
        checkNumberOfArguments(arguments, DeleteEventCommand.MESSAGE_USAGE);
        arguments[1] = " " + arguments[1];
        checkArgumentPrefixes(arguments[1], DeleteEventCommand.MESSAGE_USAGE, EVENT_ARG);

        int eventIndex = getEventIndex(arguments[1]);

        return new DeleteEventCommand(eventIndex);
    }

    /**
     * Parses the user input into arguments for the ShowUpcoming command.
     * @return ShowUpcoming Command.
     */
    private static Command prepareShowUpcoming(String[] arguments) throws EscException {
        checkNumberOfArguments(arguments, ShowUpcomingCommand.MESSAGE_USAGE);
        arguments[1] = " " + arguments[1];
        checkArgumentPrefixes(arguments[1], ShowUpcomingCommand.MESSAGE_USAGE, DATE_ARG);

        int dateRange = getDateRange(arguments[1]);

        return new ShowUpcomingCommand(dateRange);
    }

    /**
     * Returns the number of questions to quiz from the user input.
     * @return the number of questions to quiz.
     * @throws EscException if the input number is a non-integer.
     */
    private static int getNumberToQuiz(String argument) throws EscException {
        if (!argument.contains(QUIZ_ARG)) {
            return -1;
        } else {
            int numToQuiz = 0;
            String num;
            try {
                num = argument.split(QUIZ_ARG)[1].trim();
                numToQuiz = Integer.parseInt(num.split(" ")[0]);
            } catch (NumberFormatException e) {
                throw new EscException("Number of questions to quiz has to be an integer.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new EscException("The number of questions to quiz is needed.");
            }
            if (numToQuiz <= 0) {
                throw new EscException("Number of questions to quiz has to be a positive integer.");
            } else if (num.split(" ").length > 1) {
                throw new EscException("Too many inputs. " + QuizCommand.MESSAGE_USAGE);
            }
            return numToQuiz;
        }
    }

    /**
     * Returns the subject index from the user input.
     * @return the subject index integer.
     * @throws EscException if the subject index is absent or non-integer.
     */
    private static int getSubjectIndex(String argument) throws EscException {
        String argWithoutPrefixes = argument.split(QUESTION_ARG)[0].split(CARD_ARG)[0].split(QUIZ_ARG)[0];
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

    /**
     * Returns the card index from the user input.
     * @return the card index integer.
     * @throws EscException if the card index is absent or non-integer.
     */
    private static int getCardIndex(String argument) throws EscException {
        try {
            String argWithoutPrefixes = argument.split(CARD_ARG)[1];
            int space = argument.indexOf(" ");
            String cardIndexString = argWithoutPrefixes.replace(CARD_ARG,"").substring(0, space + 1).trim();
            return Integer.parseInt(cardIndexString);
        } catch (NumberFormatException e) {
            throw new EscException("The card index has to be an integer.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EscException("The card index is required.");
        }
    }

    /**
     * Returns the event topic from the user input.
     * @return the event topic.
     * @throws EscException if the event topic is absent.
     */
    private static String getEventTopic(String argument) throws EscException {
        String argWithoutPrefixes = argument.split(DATE_ARG)[0];
        String topicString = argWithoutPrefixes.replace(EVENT_ARG,"").trim();

        if (topicString.trim().isEmpty()) {
            throw new EscException("The event description is required.");
        }

        return topicString;
    }

    /**
     * Returns the event index from the user input.
     * @return the event index integer.
     * @throws EscException if the event index is absent or non-integer.
     */
    private static int getEventIndex(String argument) throws EscException {
        String eventIndexString = argument.replace(EVENT_ARG,"").trim();

        if (eventIndexString.trim().isEmpty()) {
            throw new EscException("The event index is required.");
        }
        try {
            return Integer.parseInt(eventIndexString);
        } catch (NumberFormatException e) {
            throw new EscException("The event index has to be an integer.");
        }
    }

    /**
     * Returns the event date from the user input.
     * @return the event date.
     * @throws EscException if the event date format is wrong or absent.
     */
    private static LocalDate getEventDate(String argument) throws EscException {
        DateTimeFormatter dateKey = DateTimeFormatter.ofPattern("[dd/MM/yyyy][d/M/yyyy][dd/MM/yy][d/M/yy]"
                + "[dd-MM-yyyy][d-M-yyyy][dd-MM-yy][d-M-yy]"
                + "[dd.MM.yyyy][d.M.yyyy][dd.MM.yy][d.M.yy]"
                + "[dd-MMM-yyyy][d-MMM-yyyy][d-MMM-yy]");

        LocalDate parsedDate;
        try {
            String dateString = argument.split(DATE_ARG)[1].trim();
            parsedDate = LocalDate.parse(dateString, dateKey);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EscException("The event date is required.");
        } catch (DateTimeParseException e) {
            throw new EscException("Wrong date format.");
        }

        return parsedDate;
    }

    /**
     * Returns the date range of upcoming events to show from the user input.
     * @return the date range integer.
     * @throws EscException if the date range is absent or non-integer.
     */
    private static int getDateRange(String argument) throws EscException {
        String dateRangeString = argument.replace(DATE_ARG,"").trim();

        if (dateRangeString.trim().isEmpty()) {
            throw new EscException("The date range is required.");
        }
        int dateRange;
        try {
            dateRange = Integer.parseInt(dateRangeString.split(" ")[0]);
        } catch (NumberFormatException  e) {
            throw new EscException("The date range has to be an integer.");
        }

        if (dateRange < 0) {
            throw new EscException("The date range has to be a positive integer.");
        } else if (dateRangeString.split(" ").length > 1) {
            throw new EscException("Too many inputs. " + ShowUpcomingCommand.MESSAGE_USAGE);
        }
        return dateRange;
    }

    /**
     * Ensure that the number of arguments in the user input is valid.
     * @throws EscException if the user input contains the wrong number of inputs.
     */
    private static void checkNumberOfArguments(String[] arguments, String errorMessage) throws EscException {
        if (arguments.length < 2) {
            throw new EscException(errorMessage);
        }
    }

    /**
     * Checks that the user input has the required prefixes.
     * @throws EscException if there are missing prefixes.
     */
    private static void checkArgumentPrefixes(String argument, String errorMessage, String...prefixes)
            throws EscException {
        for (String p : prefixes) {
            if (!argument.contains(p)) {
                throw new EscException(errorMessage);
            }
        }
    }

    /**
     * Returns the question and answer from the user input.
     * @return String array containing the question and the answer.
     * @throws EscException if the user input is missing the question or answer.
     */
    private static String[] getQuestionAndAnswer(String argument, String errorMessage) throws EscException {
        String secondaryArgs = argument.split(QUESTION_ARG,2)[1];
        String [] cardArgs = secondaryArgs.split(ANSWER_ARG,2);

        if (cardArgs.length < 2) {
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