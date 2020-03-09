package seedu.happypills.parser;

import seedu.happypills.commands.AddCommand;
import seedu.happypills.commands.RetrieveCommand;
import seedu.happypills.commands.HelpCommand;
import seedu.happypills.commands.ListCommand;
import seedu.happypills.commands.EditCommand;
import seedu.happypills.commands.Command;
import seedu.happypills.exception.HappyPillsException;

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
                throw new HappyPillsException("    detail is empty");
            }
            String[] patientDetail = userCommand[1].split(",");
            if (patientDetail.length != 7) {
                throw new HappyPillsException("    please ensure to input your details");
            }
            return new AddCommand(patientDetail[0], patientDetail[1],
                    Integer.parseInt(patientDetail[2]), patientDetail[3],
                    patientDetail[4], patientDetail[5], patientDetail[6]);
        } else if (userCommand[0].equalsIgnoreCase("help")) {
            return new HelpCommand();
        } else if (userCommand[0].equalsIgnoreCase("get")) {
            return new RetrieveCommand(userCommand[1]);
        } else if (userCommand[0].equalsIgnoreCase("edit")) {
            return new EditCommand(userCommand[1], userCommand[2], userCommand[3]);
        } else {
            throw new HappyPillsException("    Invalid Command");
        }
    }
}
