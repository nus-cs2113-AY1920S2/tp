package seedu.happypills.controller.parser;

import seedu.happypills.controller.commands.visitcommands.AddVisitCommand;
import seedu.happypills.controller.commands.visitcommands.IncorrectVisitCommand;
import seedu.happypills.controller.commands.visitcommands.VisitCommand;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.view.ui.TextUi;

import java.util.Scanner;


public class VisitParser {

    /**
     * Parses the command given by the user to visit commands.
     * @param fullCommand the full command entered by the user.
     * @return the command that the user has entered.
     * @throws HappyPillsException throws an exception for invalid commands.
     */
    public static VisitCommand parse(String fullCommand) throws HappyPillsException {
        String[] userCommand = fullCommand.split(" ", 3);

        if (userCommand[0].equalsIgnoreCase("list")) {
            //return new ListVisitCommand();
        } else if (userCommand[0].equalsIgnoreCase("add")) {
            if (userCommand.length == 1 || userCommand[1].trim().isEmpty()) {
                throw new HappyPillsException("    Patient's detail is empty.");
            }
            return parseAddCommand(userCommand[2]);
        } else if (userCommand[0].equalsIgnoreCase("get")) {
            if (userCommand.length == 1 || userCommand[1].trim().isEmpty()) {
                throw new HappyPillsException("    NRIC of the patient not provided");
            }
            //return new GetVisitCommand(userCommand[2].trim().toUpperCase());
        } else if (userCommand[0].equalsIgnoreCase("edit")) {
            String[] edit = fullCommand.split(" ", 4);
            if (edit.length < 3) {
                throw new HappyPillsException("    Please input your patient's details correctly.");
            }
            //return new EditVisitCommand(edit[2].substring(3), edit[3]);
        } else if (userCommand[0].equalsIgnoreCase("delete")) {
            //return new DeleteVisitCommand(userCommand[2]);
        } else {
            throw new HappyPillsException("    Invalid Command.");
        }
        return null;
    }

    private static VisitCommand parseAddCommand(String content) throws HappyPillsException {
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
                parseInput[0] = detail.substring(2).trim();
            } else if (detail.startsWith("d") && parseInput[1].equalsIgnoreCase("")) {
                parseInput[1] = detail.substring(1).trim();
            } else if (detail.startsWith("t") && parseInput[2].equalsIgnoreCase("")) {
                parseInput[2] = detail.substring(1).trim();
            } else if (detail.startsWith("r") && parseInput[3].equalsIgnoreCase("")) {
                parseInput[3] = detail.substring(1).trim();
            } else {
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
                System.out.println("    /d[DATE] in dd/mm/yyyy");
            }
            if (parseInput[2].equalsIgnoreCase("")) {
                System.out.println("    /t[TIME]");
            }
            if (parseInput[3].equalsIgnoreCase("")) {
                System.out.println("    /r[reason]");
            }
            String input = promptUser().trim();
            if (input.equalsIgnoreCase("clear")) {
                return new IncorrectVisitCommand(TextUi.DIVIDER + "\n    Command has been aborted.\n"
                        + TextUi.DIVIDER);
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
                    parseInput[0] = update.trim().substring(2);
                } else if (update.trim().startsWith("d") && (parseInput[1].equalsIgnoreCase(""))) {
                    parseInput[1] = update.trim().substring(1);
                } else if (update.trim().startsWith("t") && parseInput[2].equalsIgnoreCase("")) {
                    parseInput[2] = update.trim().substring(1);
                } else if (update.trim().startsWith("r") && parseInput[3].equalsIgnoreCase("")) {
                    parseInput[3] = update.trim().substring(1);
                }
            }
        }

        boolean userConformation = false;
        System.out.println(promptConformation(parseInput));
        while (!userConformation) {
            String conformation = promptUser();
            System.out.println(TextUi.DIVIDER);
            if (conformation.equalsIgnoreCase("y")) {
                userConformation = true;
            } else if (conformation.equalsIgnoreCase("n")) {
                return new IncorrectVisitCommand("    The current information is not added.\n"
                        + "    Please add all the details again! Thank you.\n"
                        + TextUi.DIVIDER);
            } else {
                System.out.println("    Please input [y] for yes or [n] for no");
            }
        }
        return new AddVisitCommand(parseInput[0].toUpperCase().trim(), parseInput[1].toUpperCase().trim(),
                parseInput[2].trim(), parseInput[3].trim());
    }

    private static String promptUser() {
        System.out.println(TextUi.DIVIDER);
        Scanner in = new Scanner(System.in);
        String reInput = in.nextLine();
        return reInput;
    }

    /**
     * Prompt user for conformation with this message.
     *
     * @param parseInput details to be displayed to user for confirmation
     * @return string to be displayed to user for confirmation
     */
    public static String promptConformation(String[] parseInput) {
        String text = TextUi.DIVIDER
                + "\n        Name : " + parseInput[0].trim() + "\n"
                + "        NRIC : " + parseInput[1].toUpperCase().trim() + "\n"
                + "        Phone Number : " + parseInput[2].trim() + "\n"
                + "        DOB : " + parseInput[3].trim() + "\n"
                + "        Blood Type : " + parseInput[4].trim() + "\n"
                + "        Allergies : " + parseInput[5].trim() + "\n"
                + "        Remarks : " + parseInput[6].trim() + "\n"
                + "                                                   (Y/N)?";
        return text;
    }
}
