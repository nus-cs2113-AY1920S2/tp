package seedu.happypills.parser;

import seedu.happypills.commands.Command;
import seedu.happypills.commands.AddCommand;
import seedu.happypills.commands.DeleteCommand;
import seedu.happypills.commands.EditCommand;
import seedu.happypills.commands.ExitCommand;
import seedu.happypills.commands.GetCommand;
import seedu.happypills.commands.HelpCommand;
import seedu.happypills.commands.IncorrectCommand;
import seedu.happypills.commands.ListCommand;
import seedu.happypills.exception.HappyPillsException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses user input.
 */
public class Parser {

    public static final Pattern PATIENT_DATA_ARGS_FORMAT =
            Pattern.compile("/ic(?<nric>[^/]+)"
                    + " /n(?<name>[^/]+)"
                    + " /p(?<phone>[^/]+)"
                    + " /d(?<dob>[^/]+)"
                    + " /b(?<blood>[^/]+)"
                    + "(?<tagArguments>(?: t/[^/]+)*)");

    public static final Pattern PATIENT_ALLERGIES_ARGS_FORMAT =
            Pattern.compile("/ic(?<nric>[^/]+)"
                    + " /a(?<allergies>[^/]+)"
                    + "(?<tagArguments>(?: t/[^/]+)*)");

    public static final Pattern PATIENT_REMARKS_ARGS_FORMAT =
            Pattern.compile("/ic(?<nric>[^/]+)"
                    + " /r(?<remarks>[^/]+)"
                    + "(?<tagArguments>(?: t/[^/]+)*)");

    /**
     * Parses user input into command for execution.
     *
     * @param fullCommand Full user input string
     * @return the command Based on the user input
     * @throws HappyPillsException Errors base on invalid input or insufficient input
     */
    public static Command parse(String fullCommand) throws HappyPillsException {
        String[] userCommand = fullCommand.split(" ", 2);

        if (userCommand[0].equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (userCommand[0].equalsIgnoreCase("add")) {
            if (userCommand.length == 1 || userCommand[1].trim().isEmpty()) {
                throw new HappyPillsException("    Patient's detail is empty.");
            }
            return parseAddCommand(userCommand[1]);
        } else if (userCommand[0].equalsIgnoreCase("help")) {
            if (userCommand.length == 1) {
                return new HelpCommand("");
            } else if (userCommand.length == 2) {
                return new HelpCommand(userCommand[1]);
            } else {
                throw new HappyPillsException("    " + userCommand[1] + " command does not exist. Please try again.");
            }
        } else if (userCommand[0].equalsIgnoreCase("get")) {
            if (userCommand.length == 1 || userCommand[1].trim().isEmpty()) {
                throw new HappyPillsException("    NRIC of the patient not provided");
            }
            return new GetCommand(userCommand[1]);
        } else if (userCommand[0].equalsIgnoreCase("edit")) {
            String[] edit = fullCommand.split(" ", 3);
            if (edit.length < 3) {
                throw new HappyPillsException("    Please input your patient's details correctly.");
            }
            return new EditCommand(edit[1], edit[2]);
        } else if (userCommand[0].equalsIgnoreCase("exit")) {
            return new ExitCommand();
        } else if (userCommand[0].equalsIgnoreCase("delete")) {
            return new DeleteCommand(userCommand[1]);
        } else {
            throw new HappyPillsException("    Invalid Command.");
        }
    }

    private static Command parseAddCommand(String content) {
        Matcher matcher = PATIENT_DATA_ARGS_FORMAT.matcher(content.trim());
        if (matcher.matches()) {
            int phoneNum = Integer.parseInt(matcher.group("phone"));
            return new AddCommand(
                    matcher.group("name"),
                    matcher.group("nric"),
                    phoneNum,
                    matcher.group("dob"),
                    matcher.group("blood"), "", ""
                );

        }
        matcher = PATIENT_ALLERGIES_ARGS_FORMAT.matcher((content.trim()));
        if (matcher.matches()) {
            return new AddCommand(
                    "",
                    matcher.group("nric"),
                    0,
                    "", "",
                    matcher.group("allergies"), ""
            );
        }
        matcher = PATIENT_REMARKS_ARGS_FORMAT.matcher((content.trim()));
        if (matcher.matches()) {
            return new AddCommand(
                    "",
                    matcher.group("nric"),
                    0,
                    "", "",
                    "", matcher.group("remarks")
            );
        }
        return new IncorrectCommand("    Command is invalid. Please try again");
    }
}
