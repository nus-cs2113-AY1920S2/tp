package seedu.duke.parser;


import seedu.duke.exception.InputException;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into task type.
     * @param fullCommand full user input command.
     * @return the task type.
     */
    public static String parseTaskType(String fullCommand) throws InputException {
        String taskType;
        String[] argsWords;
        argsWords = fullCommand.split(" ",2);
        taskType = argsWords[0];
        if (!taskType.equals("")) {
            return taskType;
        } else {
            throw new InputException("invalid command");
        }
    }

    /**
     * returns the argument portion of the user input.
     * @param fullCommand full user input command.
     * @return the argument portion of the user input.
     */
    public static String parseArgs(String fullCommand) throws InputException {
        String args = "";
        String[] argsWords;
        argsWords = fullCommand.split(" ",2);
        if (argsWords.length > 1) {
            args = argsWords[1];
        }
        if (!args.equals("")) {
            return args;
        } else {
            throw new InputException("invalid command");
        }
    }

}
