package seedu.happypills.logic.parser;

import seedu.happypills.HappyPills;
import seedu.happypills.logic.commands.appointmentcommands.AppointmentCommand;

import seedu.happypills.logic.commands.appointmentcommands.AddAppointmentCommand;
import seedu.happypills.logic.commands.appointmentcommands.DoneAppointmentCommand;
import seedu.happypills.logic.commands.appointmentcommands.EditAppointmentCommand;
import seedu.happypills.logic.commands.appointmentcommands.DeleteAppointmentCommand;
import seedu.happypills.logic.commands.appointmentcommands.FindAppointmentCommand;
import seedu.happypills.logic.commands.appointmentcommands.IncorrectAppointmentCommand;
import seedu.happypills.logic.commands.appointmentcommands.ListAppointmentCommand;

import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.ui.TextUi;

import java.util.Scanner;

public class AppointmentParser {

    /**
     * Parses the command given by the user to appointment commands.
     * @param fullCommand the full command entered by the user.
     * @return the command that the user has entered.
     * @throws HappyPillsException throws an exception for invalid commands.
     */
    public static AppointmentCommand parse(String fullCommand) throws HappyPillsException {
        String[] userCommand = fullCommand.trim().split(" ", 3);

        if (userCommand[0].equalsIgnoreCase("add")) {
            return parseAddCommand(userCommand[2]);
        } else if (userCommand[0].equalsIgnoreCase("edit")) {
            String[] detailedCommand = userCommand[2].trim().split(" ", 3);
            if (detailedCommand.length == 3) {
                return new EditAppointmentCommand(detailedCommand[0].trim(), detailedCommand[1].trim(),
                        detailedCommand[2]);
            } else {
                throw new HappyPillsException(TextUi.incompleteCommandString(" help edit appt"));
            }
        } else if (userCommand[0].equalsIgnoreCase("done")) {
            String [] detailedCommand = userCommand[2].trim().split(" ",2);
            if (detailedCommand.length == 2) {
                return new DoneAppointmentCommand(detailedCommand[0].trim(),detailedCommand[1].trim());
            } else {
                throw new HappyPillsException(TextUi.incompleteCommandString(" help done appt"));
            }
        } else if (userCommand[0].equalsIgnoreCase("list")) {
            return new ListAppointmentCommand();
        } else if (userCommand[0].equalsIgnoreCase("delete")) {
            String [] detailedCommand = userCommand[2].trim().split(" ",2);
            if (detailedCommand.length == 2) {
                return new DeleteAppointmentCommand(detailedCommand[0].trim(), detailedCommand[1].trim());
            } else {
                throw new HappyPillsException(TextUi.incompleteCommandString("help delete appt"));
            }
        } else if (userCommand[0].equalsIgnoreCase("find")) {
            return new FindAppointmentCommand(userCommand[2]);
        } else {
            throw new HappyPillsException(TextUi.printIncorrectCommand(userCommand[0]));
        }
    }

    private static AppointmentCommand parseAddCommand(String content) throws HappyPillsException {
        String[] details;
        if (content.startsWith("/")) {
            details = content.substring(1).split(" /");
        } else {
            content = "@" + content;
            details = content.split(" /");
        }
        String[] parseInput = {"", "", "", ""};
        for (String detail : details) {
            if (detail.startsWith("ic") && parseInput[0].equalsIgnoreCase("")) {
                parseInput[0] = detail.substring(1).trim();
            } else if (detail.startsWith("d") && parseInput[1].equalsIgnoreCase("")) {
                parseInput[1] = detail.substring(1).trim();
            } else if (detail.startsWith("t") && parseInput[2].equalsIgnoreCase("")) {
                parseInput[2] = detail.substring(1).trim();
            } else if (detail.startsWith("r") && parseInput[3].equalsIgnoreCase("")) {
                parseInput[3] = detail.substring(1).trim();
            }  else {
                System.out.println("    " + detail + " is not a valid input.\n"
                        + "    " + detail + " will not be added\n" + TextUi.DIVIDER);
            }
        }

        while (parseInput[0].equalsIgnoreCase("") || parseInput[1].equalsIgnoreCase("")
                || parseInput[2].equalsIgnoreCase("") || parseInput[3].equalsIgnoreCase("")) {
            System.out.println("    Please input your missing detail listed below");
            if (parseInput[0].equalsIgnoreCase("")) {
                System.out.println("    /ic[NRIC]");
            }
            if (parseInput[1].equalsIgnoreCase("")) {
                System.out.println("    /d[DATE] DD-MM-YYYY format");
            }
            if (parseInput[2].equalsIgnoreCase("") || !isInteger(parseInput[2].trim())) {
                System.out.println("    /t[TIME] HH:mm:ss format");
            }
            if (parseInput[3].equalsIgnoreCase("")) {
                System.out.println("    /r[REASONS]");
            }
            String input = promptUser().trim();
            if (input.equalsIgnoreCase("clear")) {
                return new IncorrectAppointmentCommand("    Appointment is not added\n");
            }
            String[] updates;
            if (input.startsWith("/")) {
                updates = input.substring(1).split(" /");
            } else {
                input = "@" + input;
                updates = input.split(" /");
            }
            for (String update : updates) {
                if (update.trim().startsWith("ic") && parseInput[0].equalsIgnoreCase("")) {
                    parseInput[0] = update.trim().substring(1);
                } else if (update.trim().startsWith("d") && parseInput[1].equalsIgnoreCase("")) {
                    parseInput[1] = update.trim().substring(2);
                } else if (update.trim().startsWith("t") && (parseInput[2].equalsIgnoreCase(""))) {
                    parseInput[2] = update.trim().substring(1);
                } else if (update.trim().startsWith("r") && parseInput[3].equalsIgnoreCase("")) {
                    parseInput[3] = update.trim().substring(1);
                }
            }
        }

        boolean userConfirmation = false;
        System.out.println(promptConfirmation(parseInput));
        while (!userConfirmation) {
            String confirmation = promptUser();
            System.out.println(TextUi.DIVIDER);
            if (confirmation.equalsIgnoreCase("y")) {
                userConfirmation = true;
            } else if (confirmation.equalsIgnoreCase("n")) {
                return new IncorrectAppointmentCommand("    Appointment is not added.\n");
            } else {
                System.out.println("    Please input [y] for yes or [n] for no");
            }
        }
        return new AddAppointmentCommand(parseInput[0].toUpperCase().substring(1).trim(), parseInput[1].trim(),
                parseInput[2].trim(), parseInput[3].trim());
    }

    private static String promptUser() {
        System.out.println(TextUi.DIVIDER);
        Scanner in = HappyPills.scanner;
        String reInput = in.nextLine();
        return reInput;
    }

    /**
     * Check if the String can be converted to Integer.
     *
     * @param input value to check if is integer
     * @return true if is an integer, false otherwise
     */
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * Prompt user for conformation with this message.
     *
     * @param parseInput details to be displayed to user for confirmation
     * @return string to be displayed to user for confirmation
     */
    public static String promptConfirmation(String[] parseInput) {
        String text = "        Are you sure all the listed details are correct?\n"
                + "        NRIC : " + parseInput[0].toUpperCase().trim().substring(1) + "\n"
                + "        Date : " + parseInput[1].trim() + "\n"
                + "        Time : " + parseInput[2].trim() + "\n"
                + "        Reason : " + parseInput[3].trim() + "\n"
                + "                                                   (Y/N)?";
        return text;
    }
}
