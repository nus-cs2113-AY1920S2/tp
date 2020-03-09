package seedu.duke.parser;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.module.Module;

/**
 * Parses user input.
 */
public class Parser {
    /**
     * Parses user input into module.
     * @param fullCommand full user input command.
     * @return the module.
     */
    public static Command parse(String fullCommand) {
        String taskType;
        String args;
        String[] words;
        words = fullCommand.split(" ",2);
        taskType = words[0];
        switch (taskType) {
            case AddCommand.COMMAND_WORD:
                args = words[1];
                return processAddCommand(args);
            case ExitCommand.COMMAND_WORD:
                return processExitCommand();
            default:
                return null;
        }
    }

    private static AddCommand processAddCommand(String args) {
        String[] moduleWords;
        moduleWords = args.split(" s/");
        boolean isWrongArgs = moduleWords.length < 2;
        if (isWrongArgs) {
            return null;
        }
        String module = moduleWords[0];
        String semester = moduleWords[1];
        if (module.contains("n/")) {
            String moduleName = module.replace("n/","");
            return new AddCommand(new Module("name", moduleName, semester));
        } else if (module.contains("id/")) {
            String moduleId = module.replace("id/","");
            return new AddCommand(new Module("id", moduleId, semester));
        }
        return null;
    }

    private static ExitCommand processExitCommand() {
        return new ExitCommand();
    }

}
