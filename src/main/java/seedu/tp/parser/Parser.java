package seedu.tp.parser;

import seedu.tp.commands.AddFlashcardToGroupCommand;
import seedu.tp.commands.ByeCommand;
import seedu.tp.commands.Command;
import seedu.tp.commands.DeleteCommand;
import seedu.tp.commands.DeleteGroupCommand;
import seedu.tp.commands.DeleteStudyPlanCommand;
import seedu.tp.commands.DisplayStudyPlanCommand;
import seedu.tp.commands.EventFlashcardCommand;
import seedu.tp.commands.FindCommand;
import seedu.tp.commands.GroupCommand;
import seedu.tp.commands.HelpCommand;
import seedu.tp.commands.ListCommand;
import seedu.tp.commands.ListFlashcardsInGroupCommand;
import seedu.tp.commands.ListPriorityCommand;
import seedu.tp.commands.ListReviewedCommand;
import seedu.tp.commands.OtherFlashcardCommand;
import seedu.tp.commands.PersonFlashcardCommand;
import seedu.tp.commands.PriorityCommand;
import seedu.tp.commands.RandomCommand;
import seedu.tp.commands.ResetReviewedCommand;
import seedu.tp.commands.ReviewedCommand;
import seedu.tp.commands.ShowCommand;
import seedu.tp.commands.ShowGroupsCommand;
import seedu.tp.commands.TimelineCommand;
import seedu.tp.commands.UpdateStudyPlanCommand;
import seedu.tp.exceptions.HistoryFlashcardException;
import seedu.tp.exceptions.InvalidDateFormatException;
import seedu.tp.exceptions.InvalidFlashcardIndexException;
import seedu.tp.exceptions.InvalidInputFormatException;
import seedu.tp.exceptions.ReversedDateOrderException;
import seedu.tp.exceptions.UnknownCommandException;
import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardFactory;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.group.GroupFactory;
import seedu.tp.group.GroupList;
import seedu.tp.studyplan.StudyPlanList;
import seedu.tp.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.tp.utils.Constants.ADD_FLASHCARD_TO_GROUP_COMMAND;
import static seedu.tp.utils.Constants.BYE_COMMAND;
import static seedu.tp.utils.Constants.DELETE_COMMAND;
import static seedu.tp.utils.Constants.DELETE_GROUP_COMMAND;
import static seedu.tp.utils.Constants.DELETE_STUDY_PLAN_COMMAND;
import static seedu.tp.utils.Constants.DISPLAY_STUDY_PLAN_COMMAND;
import static seedu.tp.utils.Constants.EMPTY_SPACE;
import static seedu.tp.utils.Constants.EVENT_FLASHCARD_COMMAND;
import static seedu.tp.utils.Constants.FIND_FLASHCARD_COMMAND;
import static seedu.tp.utils.Constants.GROUP_COMMAND;
import static seedu.tp.utils.Constants.HELP_COMMAND;
import static seedu.tp.utils.Constants.LIST_COMMAND;
import static seedu.tp.utils.Constants.LIST_FLASHCARDS_IN_GROUP_COMMAND;
import static seedu.tp.utils.Constants.LIST_PRIORITY_COMMAND;
import static seedu.tp.utils.Constants.LIST_REVIEWED_COMMAND;
import static seedu.tp.utils.Constants.LOG_FOLDER;
import static seedu.tp.utils.Constants.OTHER_FLASHCARD_COMMAND;
import static seedu.tp.utils.Constants.PERSON_FLASHCARD_COMMAND;
import static seedu.tp.utils.Constants.PRIORITY_COMMAND;
import static seedu.tp.utils.Constants.RANDOM_COMMAND;
import static seedu.tp.utils.Constants.RESET_REVIEWED_COMMAND;
import static seedu.tp.utils.Constants.REVIEWED_COMMAND;
import static seedu.tp.utils.Constants.SHOW_COMMAND;
import static seedu.tp.utils.Constants.SHOW_GROUPS_COMMAND;
import static seedu.tp.utils.Constants.TIMELINE_COMMAND;
import static seedu.tp.utils.Constants.UPDATE_STUDY_PLAN_COMMAND;

/**
 * Parser class to handle parsing of user input.
 */
public class Parser {

    private static final String FILE_PATH = LOG_FOLDER + "parser.log";
    private static final Logger LOGGER = Logger.getLogger(Parser.class.getName());

    private FlashcardFactory flashcardFactory;
    private FlashcardList flashcardList;
    private GroupFactory groupFactory;
    private GroupList groupList;
    private StudyPlanList studyPlanList;
    private Ui ui;

    /**
     * Constructs the Parser class.
     *
     * @param flashcardFactory flashcard factory to be passed in as argument to commands
     * @param flashcardList    flashcard list to be passed in as argument to commands
     * @param groupFactory     group factory to be passes in as argument to commands
     * @param groupList        group list to be passed in as argument to commands
     * @param ui               UI to be passed in as argument to commands
     */
    public Parser(FlashcardFactory flashcardFactory, FlashcardList flashcardList,
                  GroupFactory groupFactory, GroupList groupList, StudyPlanList studyPlanList, Ui ui) {
        this.flashcardFactory = flashcardFactory;
        this.flashcardList = flashcardList;
        this.groupFactory = groupFactory;
        this.groupList = groupList;
        this.studyPlanList = studyPlanList;
        this.ui = ui;
    }

    /**
     * Set up the Parser logger. Call once at the start of the program.
     *
     * @throws IOException when logger set up failed
     */
    public static void setupLogger() throws IOException {
        LOGGER.setLevel(Level.ALL);
        LOGGER.setUseParentHandlers(false);
        FileHandler fileHandler = new FileHandler(FILE_PATH, true);
        fileHandler.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(fileHandler);
    }

    /**
     * Attempt to parse a string representing a date by matching it with formatters.
     *
     * @param date the string to be parsed
     * @return LocalDate if the string was parsable, null if not
     */
    public static LocalDate parseDate(String date) throws InvalidDateFormatException {
        assert date != null : "Invalid null date!";

        final DateTimeFormatter[] dateTimeFormatters = {
            DateTimeFormatter.ofPattern("d M yyyy"),
            new DateTimeFormatterBuilder()
                .appendPattern("M yyyy")
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter(),
            new DateTimeFormatterBuilder()
                .appendPattern("yyyy")
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .toFormatter(),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            new DateTimeFormatterBuilder()
                .appendPattern("M/yyyy")
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter(),
            DateTimeFormatter.ofPattern("d-M-yyyy"),
            new DateTimeFormatterBuilder()
                .appendPattern("M-yyyy")
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter(),
        };

        for (DateTimeFormatter formatter : dateTimeFormatters) {
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException ignored) {
                continue;
            }
        }

        LOGGER.warning("User entered invalid date time: ");
        LOGGER.warning(date);
        throw new InvalidDateFormatException();
    }

    /**
     * Converts local date type object to string representation.
     *
     * @param localDate the local date object
     * @return string representation of the LocalDate object
     */
    public static String localDateToString(LocalDate localDate) {
        assert localDate != null : "Invalid null LocalDate!";

        final DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(Locale.US);
        return localDate.format(formatter);
    }

    /**
     * Parses user input and return the command parsed.
     *
     * @param userInput the user input
     * @return the command parsed from user input
     * @throws HistoryFlashcardException exception that occurred when parsing user input
     */
    public Command parseCommand(String userInput) throws HistoryFlashcardException {
        assert userInput != null : "Invalid null user input!";

        String[] splitInput = userInput.split(EMPTY_SPACE, 3);
        String commandType = splitInput[0].toLowerCase();

        switch (commandType) {
        case EVENT_FLASHCARD_COMMAND:
            return new EventFlashcardCommand(flashcardList, flashcardFactory);
        case PERSON_FLASHCARD_COMMAND:
            return new PersonFlashcardCommand(flashcardList, flashcardFactory);
        case OTHER_FLASHCARD_COMMAND:
            return new OtherFlashcardCommand(flashcardList, flashcardFactory);
        case LIST_COMMAND:
            return new ListCommand(flashcardList);
        case LIST_REVIEWED_COMMAND:
            return new ListReviewedCommand(flashcardList);
        case LIST_FLASHCARDS_IN_GROUP_COMMAND:
            try {
                return new ListFlashcardsInGroupCommand(groupList, splitInput[1] + " " + splitInput[2]);
            } catch (IndexOutOfBoundsException e1) {
                try {
                    return new ListFlashcardsInGroupCommand(groupList, splitInput[1]);
                } catch (IndexOutOfBoundsException e2) {
                    LOGGER.warning("InvalidInputFormatException occurred when parsing: " + userInput);
                    throw new InvalidInputFormatException();
                }
            }
        case SHOW_COMMAND:
            try {
                return new ShowCommand(flashcardList, Integer.parseInt(splitInput[1]) - 1);
            } catch (NumberFormatException e) {
                LOGGER.warning("InvalidFlashcardIndexException occurred when parsing: " + userInput);
                throw new InvalidFlashcardIndexException();
            } catch (IndexOutOfBoundsException e) {
                LOGGER.warning("InvalidInputFormatException occurred when parsing: " + userInput);
                throw new InvalidInputFormatException();
            }
        case REVIEWED_COMMAND:
            try {
                return new ReviewedCommand(flashcardList, Integer.parseInt(splitInput[1]) - 1);
            } catch (NumberFormatException e) {
                LOGGER.warning("InvalidFlashcardIndexException occurred when parsing: " + userInput);
                throw new InvalidFlashcardIndexException();
            } catch (IndexOutOfBoundsException e) {
                LOGGER.warning("InvalidInputFormatException occurred when parsing: " + userInput);
                throw new InvalidInputFormatException();
            }
        case RANDOM_COMMAND:
            return new RandomCommand(flashcardList, ui);
        case DELETE_COMMAND:
            try {
                return new DeleteCommand(flashcardList, Integer.parseInt(splitInput[1]) - 1);
            } catch (NumberFormatException e) {
                LOGGER.warning("InvalidFlashcardIndexException occurred when parsing: " + userInput);
                throw new InvalidFlashcardIndexException();
            } catch (IndexOutOfBoundsException e) {
                LOGGER.warning("InvalidInputFormatException occurred when parsing: " + userInput);
                throw new InvalidInputFormatException();
            }
        case PRIORITY_COMMAND:
            try {
                Flashcard.PriorityLevel pl = Flashcard.PriorityLevel.valueOf(splitInput[2]);
                return new PriorityCommand(flashcardList, Integer.parseInt(splitInput[1]) - 1, pl);
            } catch (NumberFormatException e) {
                LOGGER.warning("InvalidFlashcardIndexException occurred when parsing: " + userInput);
                throw new InvalidFlashcardIndexException();
            } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                LOGGER.warning("InvalidInputFormatException occurred when parsing: " + userInput);
                throw new InvalidInputFormatException();
            }
        case LIST_PRIORITY_COMMAND:
            try {
                Flashcard.PriorityLevel pl = Flashcard.PriorityLevel.valueOf(splitInput[1]);
                return new ListPriorityCommand(flashcardList, pl);
            } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                LOGGER.warning("InvalidInputFormatException occurred when parsing: " + userInput);
                throw new InvalidInputFormatException();
            }
        case TIMELINE_COMMAND:
            try {
                return new TimelineCommand(flashcardList, splitInput[1], splitInput[2]);
            } catch (IndexOutOfBoundsException e) {
                return new TimelineCommand(flashcardList);
            } catch (InvalidDateFormatException e) {
                LOGGER.warning("InvalidInputFormatException occurred when parsing: " + userInput);
                throw new InvalidDateFormatException();
            } catch (ReversedDateOrderException e) {
                LOGGER.warning("ReversedDateOrderException occurred when parsing: " + userInput);
                throw new ReversedDateOrderException();
            }
        case GROUP_COMMAND:
            return new GroupCommand(groupFactory, groupList);
        case DELETE_GROUP_COMMAND:
            try {
                return new DeleteGroupCommand(groupList, splitInput[1] + " " + splitInput[2]);
            } catch (IndexOutOfBoundsException e1) {
                try {
                    return new DeleteGroupCommand(groupList, splitInput[1]);
                } catch (IndexOutOfBoundsException e2) {
                    LOGGER.warning("InvalidInputFormatException occurred when parsing: " + userInput);
                    throw new InvalidInputFormatException();
                }
            }
        case ADD_FLASHCARD_TO_GROUP_COMMAND:
            return new AddFlashcardToGroupCommand(ui, groupList, flashcardList);
        case SHOW_GROUPS_COMMAND:
            return new ShowGroupsCommand(groupList);
        case UPDATE_STUDY_PLAN_COMMAND:
            return new UpdateStudyPlanCommand(ui, studyPlanList, flashcardList);
        case DELETE_STUDY_PLAN_COMMAND:
            return new DeleteStudyPlanCommand(ui, studyPlanList);
        case DISPLAY_STUDY_PLAN_COMMAND:
            return new DisplayStudyPlanCommand(studyPlanList, flashcardList);
        case FIND_FLASHCARD_COMMAND:
            try {
                return new FindCommand(flashcardList, splitInput[1]);
            } catch (IndexOutOfBoundsException e) {
                LOGGER.warning("InvalidInputFormatException occurred when parsing: " + userInput);
                throw new InvalidInputFormatException();
            }
        case RESET_REVIEWED_COMMAND:
            return new ResetReviewedCommand(ui, flashcardList);
        case HELP_COMMAND:
            return new HelpCommand();
        case BYE_COMMAND:
            return new ByeCommand();
        default:
            LOGGER.warning("UnknownCommandException occurred when parsing: " + userInput);
            throw new UnknownCommandException();
        }
    }
}
