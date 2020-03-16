package seedu.duke.parser;

import seedu.duke.command.AddToDataCommand;
import seedu.duke.command.AddToSemCommand;
import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.MarkAsDoneCommand;
import seedu.duke.command.ViewCommand;
import seedu.duke.exception.InputException;
import seedu.duke.module.Module;
import seedu.duke.module.NewModule;

/**
 * Parses user input.
 */
public class Parser {
    /**
     * Parses user input into module.
     * @param fullCommand full user input command.
     * @return the module.
     */
    public static Command parse(String fullCommand) throws InputException {
        String taskType;
        String args = "";
        String[] argsWords;
        argsWords = fullCommand.split(" ",2);
        taskType = argsWords[0];

        if (argsWords.length > 1) {
            args = argsWords[1];
        }

        switch (taskType) {
        case AddToSemCommand.COMMAND_WORD:
            return processAddToSemCommand(args);
        case AddToDataCommand.COMMAND_WORD:
            return processAddToDataCommand(args);
        case ViewCommand.COMMAND_WORD:
            return processViewCommand(args);
        case ExitCommand.COMMAND_WORD:
            assert taskType.equals("bye");
            return processExitCommand();
        case MarkAsDoneCommand.COMMAND_WORD:
            return processMarkAsDone(args);
        default:
            throw new InputException("invalid command");
        }
    }

    private static MarkAsDoneCommand processMarkAsDone(String args) throws InputException {
        String[] moduleWords;
        moduleWords = args.split(" s/");
        if (moduleWords.length < 2) {
            throw new InputException("invalid 'done' command", "done n/NAME s/SEMESTER | done id/ID s/SEMESTER");
        }
        String module = moduleWords[0];
        String semester = moduleWords[1];
        if (module.contains("n/")) {
            String moduleName = module.replace("n/","");
            return new MarkAsDoneCommand(moduleName, semester);
        } else if (module.contains("id/")) {
            String moduleId = module.replace("id/","");
            return new MarkAsDoneCommand(moduleId, semester);
        }
        return null;
    }

    private static AddToSemCommand processAddToSemCommand(String args) throws InputException {
        String[] moduleWords;
        moduleWords = args.split(" s/");
        if (moduleWords.length < 2) {
            throw new InputException("invalid 'addtosem' command",
                    "addtosem id/ID s/SEMESTER | addtosem n/Name s/SEMESTER ");
        }
        String module = moduleWords[0];
        String semester = moduleWords[1];
        if (module.contains("n/")) {
            String moduleName = module.replace("n/","");
            return new AddToSemCommand(new Module("name", moduleName, semester));
        } else if (module.contains("id/")) {
            String moduleId = module.replace("id/","");
            return new AddToSemCommand(new Module("id", moduleId, semester));
        }
        return null;
    }

    private static AddToDataCommand processAddToDataCommand(String args) throws InputException {
        String[] moduleWords;
        moduleWords = args.split("id/");
        if (moduleWords.length < 2) {
            throw new InputException("invalid 'addtodata' command",
                    "addtodata id/ID n/NAME pre/PREREQMODULES");
        }
        moduleWords = moduleWords[1].split(" n/");
        if (moduleWords.length < 2) {
            return null;
        }
        String moduleId = moduleWords[0];
        moduleWords = moduleWords[1].split(" pre/");
        String moduleName = moduleWords[0];
        if (moduleWords.length < 2) {
            return new AddToDataCommand(new NewModule(moduleId, moduleName));
        }
        String[] preRequisiteModules;
        preRequisiteModules = moduleWords[1].split(" ");
        return new AddToDataCommand((new NewModule(moduleId, moduleName, preRequisiteModules)));


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
        return new ViewCommand(ViewCommand.VIEW_AVAILABLE_MODULES);
    }

    private static ExitCommand processExitCommand() {
        return new ExitCommand();
    }

}
