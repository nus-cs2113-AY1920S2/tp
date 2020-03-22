package seedu.happypills.parser;

import seedu.happypills.commands.AddCommand;
import seedu.happypills.commands.DeleteCommand;
import seedu.happypills.commands.EditCommand;
import seedu.happypills.commands.ExitCommand;
import seedu.happypills.commands.Command;
import seedu.happypills.commands.HelpCommand;
import seedu.happypills.commands.IncorrectCommand;
import seedu.happypills.commands.ListCommand;
import seedu.happypills.commands.GetCommand;
import seedu.happypills.exception.HappyPillsException;
import seedu.happypills.ui.TextUi;

import java.util.Scanner;

/**
 * Parses user input.
 */
public class Parser {
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
        String[] details = content.substring(1).split(" /");
        String[] parseInput = {"", "", "", "", "", "", "", ""};

        for (String detail : details) {
            if (detail.startsWith("ic") && parseInput[0].equalsIgnoreCase("")) {
                parseInput[0] += detail.substring(2);
            } else if (detail.startsWith("n") && parseInput[1].equalsIgnoreCase("")) {
                parseInput[1] += detail.substring(1);
            } else if (detail.startsWith("p") && parseInput[2].equalsIgnoreCase("")) {
                parseInput[2] += detail.substring(1);
            } else if (detail.startsWith("d") && parseInput[3].equalsIgnoreCase("")) {
                parseInput[3] += detail.substring(1);
            } else if (detail.startsWith("b") && parseInput[4].equalsIgnoreCase("")) {
                parseInput[4] += detail.substring(1);
            } else if (detail.startsWith("a") && parseInput[5].equalsIgnoreCase("")) {
                parseInput[5] += detail.substring(1);
            } else if (detail.startsWith("r") && parseInput[6].equalsIgnoreCase("")) {
                parseInput[6] += detail.substring(1);
            } else {
                System.out.println(":P");
            }
        }

        while (parseInput[0].equalsIgnoreCase("") || parseInput[1].equalsIgnoreCase("")
                || parseInput[2].equalsIgnoreCase("") || parseInput[3].equalsIgnoreCase("")
                || !isInteger(parseInput[2].trim())) {
            System.out.println("Please input your missing detail as listed below");
            if (parseInput[0].equalsIgnoreCase("")) {
                System.out.println("/ic[NRIC]");
            }
            if (parseInput[1].equalsIgnoreCase("")) {
                System.out.println("/n[NAME]");
            }
            if (parseInput[2].equalsIgnoreCase("") || !isInteger(parseInput[2].trim())) {
                System.out.println("/p[PHONE] only number");
            }
            if (parseInput[3].equalsIgnoreCase("")) {
                System.out.println("/d[DOB]");
            }
            String input = promptUser().trim();
            if (input.equalsIgnoreCase("clear")) {
                return new IncorrectCommand("stop");
            }
            String[] updates = input.substring(1).split(" /");

            for (String update : updates) {
                if (update.trim().startsWith("ic") && parseInput[0].equalsIgnoreCase("")) {
                    parseInput[0] = update.trim().substring(2);
                } else if (update.trim().startsWith("n") && parseInput[1].equalsIgnoreCase("")) {
                    parseInput[1] = update.trim().substring(1);
                } else if (update.trim().startsWith("p") && (parseInput[2].equalsIgnoreCase("")
                        || !isInteger(parseInput[2].trim()))) {
                    parseInput[2] = update.trim().substring(1);
                } else if (update.trim().startsWith("d") && parseInput[3].equalsIgnoreCase("")) {
                    parseInput[3] = update.trim().substring(1);
                }
            }
        }

        return new AddCommand(parseInput[0].trim(), parseInput[1].trim(), Integer.parseInt(parseInput[2].trim()),
                parseInput[3], parseInput[4], parseInput[5], parseInput[6]);
    }

    private static String promptUser() {
        System.out.println(TextUi.DIVIDER);
        Scanner in = new Scanner(System.in);
        String reInput = in.nextLine();
        return reInput;
    }

    /**
     * Check if the String can be converted to Integer.
     */
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
