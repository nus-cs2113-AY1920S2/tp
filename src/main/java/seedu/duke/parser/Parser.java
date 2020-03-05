package seedu.duke.parser;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
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
        words = fullCommand.split("\\s+",2);
        taskType = words[0];
        args = words[1];
        switch (taskType) {
        case AddCommand.COMMAND_WORD:
            return processAddCommand(args);
        default:
            return null;
        }
    }

    private static AddCommand processAddCommand(String args) {
        String[] moduleWords;
        moduleWords = args.split(" s/");
        if (moduleWords.length < 2) {
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


}
