package command;

import common.Messages;
import seedu.atas.Parser;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Task;
import tasks.Assignment;
import tasks.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EditCommand extends Command {

    public static final String EDIT_COMMAND_WORD = "edit";
    public static final String ASSIGNMENT_COMMAND = "assignment";
    public static final String EVENT_COMMAND = "event";

    //Regex for Assignment Command
    public static final Pattern ASSIGNMENT_PARAMETERS_FORMAT = Pattern.compile(
            "(?<taskType>[^/]+)"
                    + "\\s+n/\\s*(?<assignmentName>[^/]+)"
                    + "\\s+m/\\s*(?<moduleName>[^/]+)"
                    + "\\s+d/\\s*(?<dateTime>\\d{2}/\\d{2}/\\d{2}\\s+\\d{4})"
                    + "\\s+c/\\s*(?<comments>.+)$"
    );

    //Regex for Event Command
    public static final Pattern EVENT_PARAMETERS_FORMAT = Pattern.compile(
            "(?<taskType>[^/]+)"
                    + "\\s+n/\\s*(?<eventName>[^/]+)"
                    + "\\s+l/\\s*(?<location>[^/]+)"
                    + "\\s+d/\\s*(?<dateTime>\\d{2}/\\d{2}/\\d{2}\\s+\\d{4}\\s*-\\s*\\d{4})"
                    + "\\s+c/\\s*(?<comments>.+)$"
    );

    protected int editIndex;

    /**
     * Default constructor for EditCommand class.
     * @param editIndex Integer of task index to be edited
     */
    public EditCommand(int editIndex) {
        this.editIndex = editIndex;
    }

    /**
     * Executes the edit command function.
     * Takes in a new input from the user and formats input.
     * Replaces task from the tasklist at index specified by user.
     * @param taskList TaskList object that handles adding Task
     * @param ui Ui object that interacts with user
     * @return CommandResult object based on result
     */
    @Override
    public CommandResult execute(TaskList taskList, Ui ui) {
        if (taskList.getListSize() == 0) {
            return new CommandResult(Messages.NO_TASKS_MSG);
        }
        try {
            ui.showToUser(Messages.EDIT_PROMPT);
            ui.showToUser(Messages.DIVIDER);
            String userInput = ui.getUserInput();
            String commandType = userInput.split("\\s+", 2)[0].trim().toLowerCase();
            switch (commandType) {
            case ASSIGNMENT_COMMAND:
                Task editedAssignment = editAssignment(userInput, ui);
                taskList.editTask(editIndex, editedAssignment);
                return new CommandResult(String.format(Messages.EDIT_SUCCESS_MESSAGE, editedAssignment));
            case EVENT_COMMAND:
                Task editedEvent = editEvent(userInput, ui);
                taskList.editTask(editIndex, editedEvent);
                return new CommandResult(String.format(Messages.EDIT_SUCCESS_MESSAGE, editedEvent));
            default:
                return new CommandResult(Messages.UNKNOWN_COMMAND_ERROR);
            }
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(String.format(Messages.INVALID_ID_ERROR,
                    getRangeOfValidIndex(taskList)));
        }

    }

    /**
     * Creates an assignment object by formatting the string supplied by user.
     * @param userInput String supplied by user
     * @param ui Formats output to display error messages to user
     * @return Assignment object
     */
    public Assignment editAssignment(String userInput, Ui ui) {
        final Matcher matcher = ASSIGNMENT_PARAMETERS_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            ui.showToUser(Messages.ASSIGN_INCORRECT_FORMAT_ERROR);
        }

        LocalDateTime dateTime = null;
        try {
            dateTime = Parser.parseDate(matcher.group("dateTime"));
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            ui.showToUser(Messages.DATE_INCORRECT_OR_INVALID_ERROR);
        }

        String assignmentName = Parser.capitalize(matcher.group("assignmentName"));
        String moduleName = matcher.group("moduleName");
        String comments = Parser.capitalize(matcher.group("comments"));

        return new Assignment(assignmentName, moduleName, dateTime, comments);
    }

    /**
     * Creates an event object by formatting the string supplied by user.
     * @param userInput String supplied by user
     * @param ui Formats output to display error messages to user
     * @return Event object
     */
    public Event editEvent(String userInput, Ui ui) {
        final Matcher matcher = EVENT_PARAMETERS_FORMAT.matcher(userInput);
        if (!matcher.matches()) {
            ui.showToUser(Messages.EVENT_INCORRECT_FORMAT_ERROR);
        }

        LocalDateTime startDateTime = null;
        LocalDateTime endDateTime = null;
        try {
            String startEndDateTime = matcher.group("dateTime");
            String[] dateTimeTokens = startEndDateTime.split("\\s+", 2);
            String[] timeTokens = dateTimeTokens[1].split("-", 2);
            startDateTime = Parser.parseDate(dateTimeTokens[0] + " " + timeTokens[0].trim());
            endDateTime = Parser.parseDate(dateTimeTokens[0] + " " + timeTokens[1].trim());
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            ui.showToUser(Messages.START_END_DATE_INCORRECT_OR_INVALID_ERROR);
        }

        if (!endDateTime.isAfter(startDateTime)) {
            ui.showToUser(Messages.INCORRECT_START_END_TIME_ERROR);
        }

        String eventName = Parser.capitalize(matcher.group("eventName"));
        String location = matcher.group("location");
        String comments = Parser.capitalize(matcher.group("comments"));

        return new Event(eventName, location, startDateTime, endDateTime, comments);
    }



}
