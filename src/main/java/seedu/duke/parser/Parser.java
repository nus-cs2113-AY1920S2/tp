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
        case AddCommand.COMMAND_WORD:
            return processAddCommand(args);
        case ViewCommand.COMMAND_WORD:
            return processViewCommand(args);
        case ExitCommand.COMMAND_WORD:
            return processExitCommand();
        case MarkAsDoneCommand.COMMAND_WORD:
            return processMarkAsDone(args);
        case HelpingCommand.COMMAND_WORD:
            return processHelpCommand();
        case FindCommand.COMMAND_WORD:
            return processFindCommand(args);
        case CalculateCapCommand.COMMAND_WORD:
            return processCalculateCapCommand();
        case DeleteCommand.COMMAND_WORD:
            return processDeleteCommand(args);
        default:
            throw new InputException("invalid command");
        }
    }

    private static MarkAsDoneCommand processMarkAsDone(String args) throws InputException {
        String[] moduleWords;
        moduleWords = args.split(" g/");
        if (moduleWords.length < 2) {
            throw new InputException("invalid 'done' command", "done n/NAME g/GRADE | done id/ID g/GRADE");
        }
        String grade = moduleWords[1];
        Grading grading;
        String module = moduleWords[0];
        switch (grade) {
        case "A+":
            grading = Grading.APLUS;
            break;
        case "A":
            grading = Grading.A;
            break;
        case "A-":
            grading = Grading.AMINUS;
            break;
        case "B+":
            grading = Grading.BPLUS;
            break;
        case "B":
            grading = Grading.B;
            break;
        case "B-":
            grading = Grading.BMINUS;
            break;
        case "C+":
            grading = Grading.CPLUS;
            break;
        case "C":
            grading = Grading.C;
            break;
        case "D+":
            grading = Grading.DPLUS;
            break;
        case "D":
            grading = Grading.D;
            break;
        case "F":
            grading = Grading.F;
            break;
        case "CS":
            grading = Grading.CS;
            break;
        case "CU":
            grading = Grading.CU;
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + grade);
        }
        if (module.contains("n/")) {
            String moduleName = module.replace("n/","");
            return new MarkAsDoneCommand(moduleName, grading);
        } else if (module.contains("id/")) {
            String moduleId = module.replace("id/","");
            return new MarkAsDoneCommand(moduleId, grading);
        }
        return null;
    }

    private static Command processAddCommand(String args) throws InputException {
        if (args.contains("s/")) {
            return processAddToSemCommand(args);
        } else {
            return processAddToDataCommand(args);
        }
    }

    private static AddToSemCommand processAddToSemCommand(String args) throws InputException {
        String[] moduleWords;
        moduleWords = args.split(" s/");
        if (moduleWords.length < 2) {
            throw new InputException("invalid 'add' command",
                    "add id/ID s/SEMESTER mc/MODULE_CREDIT | " + "add n/Name s/SEMESTER mc/MODULE_CREDIT "
                    + "| add id/ID n/Name s/SEMESTER mc/MODULE_CREDIT");
        }
        String module = moduleWords[0];
        String semester;
        String[] moduleDetails = moduleWords[1].split(" mc/");
        if (moduleDetails.length < 2) {
            throw new InputException("invalid 'add' command",
                    "add id/ID s/SEMESTER mc/MODULE_CREDIT | add n/Name s/SEMESTER mc/MODULE_CREDIT "
                    + "| add id/ID n/Name s/SEMESTER mc/MODULE_CREDIT");
        }
        semester = convertSemToStandardFormat(moduleDetails[0]);
        int moduleCredit = Integer.parseInt(moduleDetails[1]);
        if (module.contains("id/")) {
            String moduleId = module.replace("id/","");
            if (moduleId.contains("n/")) {
                String[] idAndName = moduleId.split("n/");
                String id = idAndName[0];
                String name = idAndName[1];
                return new AddToSemCommand(new SelectedModule("Both", id, name, semester, moduleCredit));
            }
            return new AddToSemCommand(new SelectedModule("id", moduleId, semester, moduleCredit));
        } else if (module.contains("n/")) {
            String moduleName = module.replace("n/","");
            return new AddToSemCommand(new SelectedModule("name", moduleName, semester, moduleCredit));
        }
        return null;
    }

    private static AddToDataCommand processAddToDataCommand(String args) throws InputException {
        String[] moduleWords;
        moduleWords = args.split("id/");
        if (moduleWords.length < 2) {
            throw new InputException("invalid 'add' command",
                    "add id/ID n/NAME mc/MODULE_CREDIT pre/PREREQMODULES");
        }
        moduleWords = moduleWords[1].split(" n/");
        if (moduleWords.length < 2) {
            return null;
        }
        String moduleId = moduleWords[0];
        moduleWords = moduleWords[1].split(" mc/");
        String moduleName = moduleWords[0];
        moduleWords = moduleWords[1].split(" pre/");
        int moduleCredit = Integer.parseInt(moduleWords[0]);
        if (moduleWords.length < 2) {
            return new AddToDataCommand(new NewModule(moduleId, moduleName, moduleCredit));
        }
        String[] preRequisiteModules;
        preRequisiteModules = moduleWords[1].split(" ");
        return new AddToDataCommand((new NewModule(moduleId, moduleName, moduleCredit, preRequisiteModules)));
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
        } else if (args.contains("/cc")) {
            return new ViewCommand(ViewCommand.VIEW_COMPLETED_CREDITS);
        }
        return new ViewCommand(ViewCommand.VIEW_AVAILABLE_MODULES);
    }

    private static ExitCommand processExitCommand() {
        return new ExitCommand();
    }

    private static HelpingCommand processHelpCommand() {
        return new HelpingCommand();
    }

    private static FindCommand processFindCommand(String args) {
        return new FindCommand(args);
    }

    private static CalculateCapCommand processCalculateCapCommand() {
        return new CalculateCapCommand();
    }

    private static DeleteCommand processDeleteCommand(String args) throws InputException {
        if (args.contains("s/")) {
            return processDeleteFromSemCommand(args);
        } else {
            return processDeleteFromAvailableCommand(args);
        }
    }

    private static DeleteFromSemCommand processDeleteFromSemCommand(String args) throws InputException {
        String[] moduleWords = args.split(" s/");
        if (args.contains("id/")) {
            String moduleId = moduleWords[0].replace("id/", "");
            String semester = convertSemToStandardFormat(moduleWords[1]);
            return new DeleteFromSemCommand(moduleId, semester, "id");
        } else if (args.contains("n/")) {
            String moduleName = moduleWords[0].replace("n/", "");
            String semester = convertSemToStandardFormat(moduleWords[1]);
            return new DeleteFromSemCommand(moduleName, semester, "name");
        }
        throw new InputException("invalid 'delete' command to delete from Selected Modules",
                "delete id/ID s/SEM OR delete n/NAME s/SEM");
    }

    private static DeleteFromAvailableCommand processDeleteFromAvailableCommand(String args) throws InputException {
        String[] moduleWords = args.split("/", 2);
        if (moduleWords.length != 2) {
            throw new InputException("invalid 'delete' command to delete from Available Modules",
                    "delete id/ID OR delete n/NAME");
        }
        String moduleIdentifier = moduleWords[1].strip();
        if (moduleWords[0].equals("id")) {
            return new DeleteFromAvailableCommand(moduleIdentifier, "id");
        } else if (moduleWords[0].equals("n")) {
            return new DeleteFromAvailableCommand(moduleIdentifier, "name");
        }
        throw new InputException("invalid 'delete' command to delete from Available Modules",
                "delete id/ID OR delete n/NAME");
    }

    private static String convertSemToStandardFormat(String semester) {
        String standardSemFormat;
        int year = Person.getMatricYear() + (Integer.parseInt(semester) - 1) / 2;
        int sem = (Integer.parseInt(semester) + 1) % 2 + 1;
        standardSemFormat = year + "/" + (year + 1) + " SEM" + sem;
        return standardSemFormat;
    }

}
