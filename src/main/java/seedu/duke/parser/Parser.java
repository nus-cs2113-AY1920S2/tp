package seedu.duke.parser;

import seedu.duke.command.AddCommand;
import seedu.duke.command.AddToDataCommand;
import seedu.duke.command.AddToSemCommand;
import seedu.duke.command.Command;

import seedu.duke.command.CalculateCapCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.DeleteFromSemCommand;
import seedu.duke.command.DeleteFromAvailableCommand;

import seedu.duke.command.ExitCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.HelpingCommand;
import seedu.duke.command.ViewCommand;
import seedu.duke.command.MarkAsDoneCommand;
import seedu.duke.data.Person;
import seedu.duke.exception.InputException;
import seedu.duke.module.Grading;
import seedu.duke.module.NewModule;
import seedu.duke.module.SelectedModule;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into module.
     * @param fullCommand full user input command.
     * @return the module.
     */
    public static String parseTaskType(String fullCommand) throws InputException {
        String taskType;
        String[] argsWords;
        argsWords = fullCommand.split(" ",2);
        taskType = argsWords[0];                                        //.toLowerCase().trim()
        if (!taskType.equals("")) {
            return taskType;
        } else {
            throw new InputException("invalid command");
        }
    }

    public static String parseArgs(String fullCommand) throws InputException {
        String args = "";
        String[] argsWords;
        argsWords = fullCommand.split(" ",2);
        if (argsWords.length > 1) {
            args = argsWords[1];                                        //.toLowerCase().trim()
        }
        if (!args.equals("")) {
            return args;
        } else {
            throw new InputException("invalid command");
        }
    }

}
