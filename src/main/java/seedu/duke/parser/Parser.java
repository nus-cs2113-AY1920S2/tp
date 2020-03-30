package seedu.duke.parser;


import seedu.duke.exception.InputException;

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
