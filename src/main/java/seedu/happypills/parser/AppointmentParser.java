package seedu.happypills.parser;

import seedu.happypills.commands.appointment_commands.AddAppointmentCommand;
import seedu.happypills.commands.appointment_commands.AppointmentCommand;
import seedu.happypills.commands.appointment_commands.DeleteAppointmentCommand;
import seedu.happypills.commands.appointment_commands.IncorrectAppointmentCommand;
import seedu.happypills.exception.HappyPillsException;
import seedu.happypills.ui.TextUi;

import java.util.Scanner;

public class AppointmentParser {

    public static AppointmentCommand parse(String fullCommand) throws HappyPillsException {
        String[] userCommand = fullCommand.split(" ", 3);

        if (userCommand[0].equalsIgnoreCase("add")) {
            return parseAddCommand(userCommand[2]);
        } else if (userCommand[0].equalsIgnoreCase("edit")) {

        } else if (userCommand[0].equalsIgnoreCase("done")) {

        } else if (userCommand[0].equalsIgnoreCase("find")) {

        } else if (userCommand[0].equalsIgnoreCase("list")) {

        } else if (userCommand[0].equalsIgnoreCase("delete")) {
            return new DeleteAppointmentCommand(userCommand[2]);
        }
        return new IncorrectAppointmentCommand();
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
                parseInput[1] = detail.substring(2).trim();
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
                return new IncorrectAppointmentCommand();
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

        boolean userConformation = false;
        System.out.println(promptConfirmation(parseInput));
        while (!userConformation) {
            String conformation = promptUser();
            System.out.println(TextUi.DIVIDER);
            if (conformation.equalsIgnoreCase("y")) {
                userConformation = true;
            } else if (conformation.equalsIgnoreCase("n")) {
                return new IncorrectAppointmentCommand();
            } else {
                System.out.println("    Please input [y] for yes or [n] for no");
            }
        }
        //change to AddAppointmentCommand
        return new AddAppointmentCommand(parseInput[0].trim(), parseInput[1].trim(),
                parseInput[2].trim(), parseInput[3].trim());
    }

    private static String promptUser() {
        System.out.println(TextUi.DIVIDER);
        Scanner in = new Scanner(System.in);
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
        String text = "        NRIC : " + parseInput[0].toUpperCase().trim() + "\n"
                + "        Date : " + parseInput[1].trim() + "\n"
                + "        Time : " + parseInput[2].trim() + "\n"
                + "        Reason : " + parseInput[3].trim() + "\n"
                + "                                                   (Y/N)?";
        return text;
    }
}
