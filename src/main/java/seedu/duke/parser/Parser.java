package seedu.duke.parser;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.ViewCommand;
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
        String args = null;
        String[] words;
        words = fullCommand.split(" ",2);
        taskType = words[0];

        if(words.length > 1){
            args = words[1];
        }

        switch (taskType) {
        case AddCommand.COMMAND_WORD:
            return processAddCommand(args);
        case ViewCommand.COMMAND_WORD:
            return processViewCommand(args);
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

    private static ViewCommand processViewCommand(String args) {
        if (args.contains("/cm")) {
            return new ViewCommand(ViewCommand.VIEW_COMPULSORY_MODULES);
        } else if (args.contains("/dm")) {
            return new ViewCommand(ViewCommand.VIEW_DONE_MODULES);
        } else if (args.contains("/mp")) {
            return new ViewCommand(ViewCommand.VIEW_MODULE_PLAN);
        } else if (args.contains("n/")) {
            String moduleToBeViewed = args.replace("n/","");
            return new ViewCommand(ViewCommand.VIEW_SPECIFIC_MODULE, moduleToBeViewed);
        } else if (args.contains("id/")) {
            String moduleToBeViewed = args.replace("id/","");
            return new ViewCommand(ViewCommand.VIEW_SPECIFIC_MODULE, moduleToBeViewed);
        }
        return null;
    }

    private static ExitCommand processExitCommand() {
        return new ExitCommand();
    }

}
