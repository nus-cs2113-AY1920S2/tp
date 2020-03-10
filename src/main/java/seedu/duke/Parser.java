package seedu.duke;

import command.AssignmentCommand;
import command.Command;
import command.CommandResult;
import command.DeleteCommand;
import command.DoneCommand;
import command.EventCommand;
import command.IncorrectCommand;
import command.ListCommand;
import command.HelpCommand;
import common.Messages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
    public static final DateTimeFormatter PRINT_DATE_FORMAT = DateTimeFormatter.ofPattern("EEE dd MMM yyyy HH':'mm");
    public static final DateTimeFormatter PRINT_TIME_FORMAT = DateTimeFormatter.ofPattern("HH':'mm");

    // regex for an add assignment command
    public static final Pattern ASSIGNMENT_PARAMETERS_FORMAT = Pattern.compile(
            "(?<taskType>[^/]+)"
            + "\\s+n/\\s*(?<assignmentName>[^/]+)"
            + "\\s+m/\\s*(?<moduleName>[^/]+)"
            + "\\s+d/\\s*(?<dateTime>\\d{2}/\\d{2}/\\d{2}\\s+\\d{4})"
            + "\\s+c/\\s*(?<comments>[^/]+)"
    );

    // regex for an add event command
    public static final Pattern EVENT_PARAMETERS_FORMAT = Pattern.compile(
            "(?<taskType>[^/]+)"
            + "\\s+n/\\s*(?<eventName>[^/]+)"
            + "\\s+l/\\s*(?<location>[^/]+)"
            + "\\s+d/\\s*(?<dateTime>\\d{2}/\\d{2}/\\d{2}\\s+\\d{4})"
            + "\\s+c/\\s*(?<comments>[^/]+)"
    );

    /**
     * Returns a Command object depending on the command input by the user.
     * @param fullCommand line input by the user, which represents a command
     * @return Command depending on user input, with the appropriate arguments set
     */
    public static Command parseCommand(String fullCommand) {
        String commandType = fullCommand.split("\\s+", 2)[0];

        switch (commandType) {
        case "help":
            return new HelpCommand();
        case "assignment":
            return prepareAssignmentCommand(fullCommand);
        case "delete":
            return prepareDeleteCommand(fullCommand);
        case "done":
            return prepareDoneCommand(fullCommand);
        case "event":
            return prepareEventCommand(fullCommand);
        case "list":
            return prepareListCommand(fullCommand);
        default:
            return new IncorrectCommand(Messages.UNKNOWN_COMMAND_ERROR);
        }
    }

    /**
     * Returns a LocalDateTime object based on an input String with the format INPUT_DATE_FORMAT.
     * @param dateTimeString String representing a date with the format dd/MM/yy HHmm
     * @return LocalDateTime representing the date and time specified in dateTimeString
     * @throws DateTimeParseException if dateTimeString does not follow INPUT_DATE_FORMAT
     * @throws IndexOutOfBoundsException if dateTimeString does not follow INPUT_DATE_FORMAT
     */
    public static LocalDateTime parseDate(String dateTimeString)
            throws DateTimeParseException, IndexOutOfBoundsException {
        // handle issue where there are multiple spaces between the date and the time
        String[] dateAndTime = dateTimeString.split("\\s+", 2);
        String formattedDateTimeString = dateAndTime[0] + " " + dateAndTime[1];
        return LocalDateTime.parse(formattedDateTimeString, INPUT_DATE_FORMAT);
    }

    private static Command prepareAssignmentCommand(String fullCommand) {
        final Matcher matcher = ASSIGNMENT_PARAMETERS_FORMAT.matcher(fullCommand);
        if (!matcher.matches()) {
            return new IncorrectCommand(Messages.ASSIGN_INCORRECT_FORMAT_ERROR);
        }

        LocalDateTime dateTime;
        try {
            dateTime = parseDate(matcher.group("dateTime"));
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            return new IncorrectCommand(Messages.DATE_INCORRECT_OR_INVALID_ERROR);
        }

        String assignmentName = matcher.group("assignmentName");
        String moduleName = matcher.group("moduleName");
        String comments = matcher.group("comments");
        return new AssignmentCommand(assignmentName, moduleName, dateTime, comments);
    }

    private static Command prepareDeleteCommand(String fullCommand) {
        String[] tokens = fullCommand.split("\\s+", 2);
        int deleteIndex;
        try {
            deleteIndex = Integer.parseInt(tokens[1]) - 1;
        } catch (NumberFormatException e) {
            return new IncorrectCommand(Messages.NUM_FORMAT_ERROR);
        } catch (IndexOutOfBoundsException e) {
            return new IncorrectCommand(Messages.DELETE_INSUFFICIENT_ARGS_ERROR);
        }
        return new DeleteCommand(deleteIndex);
    }

    private static Command prepareDoneCommand(String fullCommand) {
        String[] tokens = fullCommand.split("\\s+", 2);
        int doneIndex;
        try {
            doneIndex = Integer.parseInt(tokens[1]) - 1;
        } catch (NumberFormatException e) {
            return new IncorrectCommand(Messages.NUM_FORMAT_ERROR);
        } catch (IndexOutOfBoundsException e) {
            return new IncorrectCommand(Messages.DONE_INSUFFICIENT_ARGS_ERROR);
        }
        return new DoneCommand(doneIndex);
    }

    private static Command prepareEventCommand(String fullCommand) {
        final Matcher matcher = EVENT_PARAMETERS_FORMAT.matcher(fullCommand);
        if (!matcher.matches()) {
            return new IncorrectCommand(Messages.EVENT_INCORRECT_FORMAT_ERROR);
        }

        LocalDateTime dateTime;
        try {
            dateTime = parseDate(matcher.group("dateTime"));
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            return new IncorrectCommand(Messages.DATE_INCORRECT_OR_INVALID_ERROR);
        }

        String eventName = matcher.group("eventName");
        String location = matcher.group("location");
        String comments = matcher.group("comments");
        return new EventCommand(eventName, location, dateTime, comments);
    }

    private static Command prepareListCommand(String fullCommand) {
        String[] tokens = fullCommand.trim().split("\\s+", 2);
        if (tokens.length == 1) {
            // check if list has no parameters
            return new ListCommand(null);
        }
        return new ListCommand(tokens[1]);
    }

    /**
     * Main function to test class.
     * @param args unused
     */
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        ui.showToUser(Messages.HELP_FORMAT_MESSAGE);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("bb")) {
                break;
            }
            try {
                Command command = parseCommand(input);
                CommandResult result = command.execute(taskList, ui);
                ui.showToUser(result.feedbackToUser);
            } catch (Exception errorMsg) {
                ui.showToUser(errorMsg.toString());
            }
        }
    }
}
