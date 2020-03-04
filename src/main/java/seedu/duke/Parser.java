package seedu.duke;

import seedu.duke.command.AssignmentCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.DoneCommand;
import seedu.duke.command.EventCommand;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.ListCommand;

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
            + "\\s+n/\\s+(?<assignmentName>[^/]+)"
            + "\\s+m/\\s+(?<moduleName>[^/]+)"
            + "\\s+d/\\s+(?<dateTime>\\d{2}/\\d{2}/\\d{2}\\s+\\d{4})"
            + "\\s+c/\\s+(?<comments>[^/]+)"
    );

    // regex for an add event command
    public static final Pattern EVENT_PARAMETERS_FORMAT = Pattern.compile(
            "(?<taskType>[^/]+)"
            + "\\s+n/\\s+(?<eventName>[^/]+)"
            + "\\s+l/\\s+(?<location>[^/]+)"
            + "\\s+d/\\s+(?<dateTime>\\d{2}/\\d{2}/\\d{2}\\s+\\d{4})"
            + "\\s+c/\\s+(?<comments>[^/]+)"
    );

    public static Command parseCommand(String fullCommand) {
        String commandType = fullCommand.split("\\s+", 2)[0];

        switch (commandType) {
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
            return new IncorrectCommand("Unknown command entered");
        }
    }

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
            return new IncorrectCommand("Incorrect format for Assignment Command");
        }

        LocalDateTime dateTime;
        try {
            dateTime = parseDate(matcher.group("dateTime"));
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            return new IncorrectCommand("Wrong date format or invalid date provided");
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
            return new IncorrectCommand("Please provide an integer as the command parameter");
        } catch (IndexOutOfBoundsException e) {
            return new IncorrectCommand("Insufficient args for Delete Command");
        }
        return new DeleteCommand(deleteIndex);
    }

    private static Command prepareDoneCommand(String fullCommand) {
        String[] tokens = fullCommand.split("\\s+", 2);
        int doneIndex;
        try {
            doneIndex = Integer.parseInt(tokens[1]) - 1;
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Please provide an integer as the command parameter");
        } catch (IndexOutOfBoundsException e) {
            return new IncorrectCommand("Insufficient args for Done Command");
        }
        return new DoneCommand(doneIndex);
    }

    private static Command prepareEventCommand(String fullCommand) {
        final Matcher matcher = EVENT_PARAMETERS_FORMAT.matcher(fullCommand);
        if (!matcher.matches()) {
            return new IncorrectCommand("Incorrect format for Event Command");
        }

        LocalDateTime dateTime;
        try {
            dateTime = parseDate(matcher.group("dateTime"));
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            return new IncorrectCommand("Wrong date format or invalid date provided");
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

    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("bb")) {
                break;
            }
            Command command = parseCommand(input);
            command.execute();
        }
    }
}
