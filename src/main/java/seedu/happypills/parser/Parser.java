package seedu.happypills.parser;

import seedu.happypills.commands.*;
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
                throw new HappyPillsException("    Patient's detail is empty");
            }
            String[] patientDetail = userCommand[1].split(",");
            if (patientDetail.length != 7) {
                throw new HappyPillsException("    Please ensure to input your patient's details");
            }
            return new AddCommand(patientDetail[0], patientDetail[1],
                    Integer.parseInt(patientDetail[2]), patientDetail[3],
                    patientDetail[4], patientDetail[5], patientDetail[6]);
        } else if (userCommand[0].equalsIgnoreCase("help")) {
            return new HelpCommand();
        } else if (userCommand[0].equalsIgnoreCase("get")) {
            if (userCommand.length == 1 || userCommand[1].trim().isEmpty()) {
                throw new HappyPillsException("    Please input the NRIC of the patient you are searching for");
            }
            return new GetCommand(userCommand[1]);
        } else if (userCommand[0].equalsIgnoreCase("edit")) {
            String[] edit = fullCommand.split(" ", 3);
            if (edit.length < 3) {
                throw new HappyPillsException("    Please input your patient's details correctly");
            }
            return new EditCommand(edit[1], edit[2]);
        } else if (userCommand[0].equalsIgnoreCase("exit")) {
            return new ExitCommand();
        } else if (userCommand[0].equalsIgnoreCase("delete")) {
            return new DeleteCommand(userCommand[1]);
        } else {
            throw new HappyPillsException("    Invalid Command");
        }
    }
}
