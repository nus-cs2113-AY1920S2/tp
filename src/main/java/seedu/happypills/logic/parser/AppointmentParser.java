package seedu.happypills.logic.parser;

import seedu.happypills.logic.commands.appointmentcommands.AppointmentCommand;

import seedu.happypills.logic.commands.appointmentcommands.AddAppointmentCommand;
import seedu.happypills.logic.commands.appointmentcommands.DoneAppointmentCommand;
import seedu.happypills.logic.commands.appointmentcommands.EditAppointmentCommand;
import seedu.happypills.logic.commands.appointmentcommands.DeleteAppointmentCommand;
import seedu.happypills.logic.commands.appointmentcommands.FindAppointmentCommand;
import seedu.happypills.logic.commands.appointmentcommands.ListAppointmentCommand;

import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.HelpTextUi;
import seedu.happypills.ui.Messages;
import seedu.happypills.ui.TextUi;

//@@author sitinadiah25
public class AppointmentParser extends Parser {

    /**
     * Parses the command given by the user to appointment commands.
     *
     * @param fullCommand the full command entered by the user.
     * @return the command that the user has entered.
     * @throws HappyPillsException throws an exception for invalid commands.
     */
    public static AppointmentCommand parse(String fullCommand) throws HappyPillsException {
        String[] userCommand = fullCommand.trim().split("\\s+", 3);
        if (userCommand[0].equalsIgnoreCase("add")) {
            return parseAddCommand(userCommand[2]);
        } else if (userCommand[0].equalsIgnoreCase("edit")) {
            String[] detailedCommand = userCommand[2].trim().split("\\s+", 3);
            if (detailedCommand.length == 3) {
                return new EditAppointmentCommand(detailedCommand[0].trim(), detailedCommand[1].trim(),
                        detailedCommand[2].trim());
            } else {
                throw new HappyPillsException(HelpTextUi.incompleteCommandString("help edit appt"));
            }
        } else if (userCommand[0].equalsIgnoreCase("done")) {
            String[] detailedCommand = userCommand[2].trim().split("\\s+", 2);
            if (detailedCommand.length == 2) {
                return new DoneAppointmentCommand(detailedCommand[0].trim(), detailedCommand[1].trim());
            } else {
                throw new HappyPillsException(HelpTextUi.incompleteCommandString("help done appt"));
            }
        } else if (userCommand[0].equalsIgnoreCase("list")) {
            return new ListAppointmentCommand();
        } else if (userCommand[0].equalsIgnoreCase("delete")) {
            String[] detailedCommand = userCommand[2].trim().split("\\s+", 2);
            if (detailedCommand.length == 2) {
                return new DeleteAppointmentCommand(detailedCommand[0].trim(), detailedCommand[1].trim());
            } else {
                throw new HappyPillsException(HelpTextUi.incompleteCommandString("help delete appt"));
            }
        } else if (userCommand[0].equalsIgnoreCase("find")) {
            return new FindAppointmentCommand(userCommand[2]);
        } else {
            throw new HappyPillsException(TextUi.incorrectCommandMessage(userCommand[0]));
        }
    }

    private static AppointmentCommand parseAddCommand(String content) throws HappyPillsException {
        String[] details = splitInput(content);
        String[] parseInput = {"", "", "", ""};
        parseInput = parseInput(details, parseInput);

        // ensure that all details are not missing
        while (parseInput[0].equalsIgnoreCase("") || parseInput[1].equalsIgnoreCase("")
                || parseInput[2].equalsIgnoreCase("") || parseInput[3].equalsIgnoreCase("")
                || !Checker.isValidDate(parseInput[1].trim()) || !Checker.isValidTime(parseInput[2].trim())
                || !Checker.isValidNric(parseInput[0].trim())) {
            printMissingInput(parseInput);
            String input = promptUser().trim();
            if (input.equalsIgnoreCase("clear")) {
                throw new HappyPillsException(Messages.MESSAGE_COMMAND_ABORTED);
            }
            System.out.println(TextUi.DIVIDER);
            String[] updates = splitInput(input);
            parseInput = parseInput(updates, parseInput);
        }
        if (!loopPrompt(promptConfirmation(parseInput))) {
            throw new HappyPillsException("    Appointment is not added.");
        }
        return new AddAppointmentCommand(parseInput[0].toUpperCase().trim(), parseInput[1].trim(),
                parseInput[2].trim(), parseInput[3].trim());
    }

    private static String[] parseInput(String[] details, String[] parseInput) {
        for (String detail : details) {
            if (detail.trim().startsWith("ic") && detail.trim().length() > 3) {
                parseInput[0] = detail.trim().substring(2).trim();
            } else if (detail.trim().startsWith("d") && detail.trim().length() > 3) {
                parseInput[1] = detail.trim().substring(2);
            } else if (detail.trim().startsWith("t") && detail.trim().length() > 3) {
                parseInput[2] = detail.trim().substring(1);
            } else if (detail.trim().startsWith("r") && detail.trim().length() > 3) {
                parseInput[3] = detail.trim().substring(1);
            }
        }
        return parseInput;
    }

    private static void printMissingInput(String[] parseInput) {
        System.out.println("    Please input your missing/incorrect detail listed below");
        if (parseInput[0].equalsIgnoreCase("") || !Checker.isValidNric(parseInput[0].trim())) {
            System.out.println(Messages.MESSAGE_NRIC_FORMAT);
        }
        if (parseInput[1].equalsIgnoreCase("") || !Checker.isValidDate(parseInput[1].trim())) {
            System.out.println(Messages.MESSAGE_DATE_FORMAT);
        }
        if (parseInput[2].equalsIgnoreCase("") || !Checker.isValidTime(parseInput[2].trim())) {
            System.out.println(Messages.MESSAGE_TIME_FORMAT);
        }
        if (parseInput[3].equalsIgnoreCase("")) {
            System.out.println("      /r [REASONS]");
        }
        System.out.println(Messages.MESSAGE_COMMAND_ABORTED);
    }

    /**
     * Prompt user for conformation with this message.
     *
     * @param parseInput details to be displayed to user for confirmation
     * @return string to be displayed to user for confirmation
     */
    public static String promptConfirmation(String[] parseInput) {
        String text = "        Are you sure all the listed details are correct?\n"
                + "        NRIC : " + parseInput[0].toUpperCase().trim() + "\n"
                + "        Date : " + parseInput[1].trim() + "\n"
                + "        Time : " + parseInput[2].trim() + "\n"
                + "        Reason : " + parseInput[3].trim() + "\n"
                + "                                                   (Y/N)?";
        return text;
    }
}
