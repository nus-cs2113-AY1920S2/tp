package seedu.duke.parser;

import seedu.duke.command.AddCommand;
import seedu.duke.command.AddToAvailableCommand;
import seedu.duke.command.AddToSemCommand;
import seedu.duke.command.CalculateCapCommand;
import seedu.duke.command.ClearCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.DeleteFromAvailableCommand;
import seedu.duke.command.DeleteFromSemCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.HelpingCommand;
import seedu.duke.command.MarkAsDoneCommand;
import seedu.duke.command.ViewCommand;
import seedu.duke.exception.InputException;
import seedu.duke.module.Grading;
import seedu.duke.module.NewModule;
import seedu.duke.module.SelectedModule;
import seedu.duke.ui.Ui;

import java.util.regex.Pattern;

public class Controller {
    /**
     * Parses user input into module.
     * @param fullCommand full user input command.
     * @return the module.
     */
    public static Command control(String fullCommand) throws InputException {
        String[] argsWords;
        String args = "";
        argsWords = fullCommand.split(" ",2);
        String taskType = Parser.parseTaskType(fullCommand);
        if (argsWords.length > 1) {
            args = Parser.parseArgs(fullCommand);
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
        case ClearCommand.COMMAND_WORD:
            return processClearCommand();
        default:
            throw new InputException("invalid command");
        }
    }

    private static Command processClearCommand() {
        return new ClearCommand();
    }

    private static MarkAsDoneCommand processMarkAsDone(String args) throws InputException {
        String[] moduleWords;
        moduleWords = args.split(" g/");
        if (moduleWords.length < 2) {
            throw new InputException("invalid 'done' command", "done n/NAME g/GRADE | done id/ID g/GRADE");
        }
        String grade = moduleWords[1].toUpperCase();
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
            throw new InputException("Unexpected value: " + grade
                    + System.lineSeparator()
                    + "Please use A+ | A | A- | B+ | B | B- | C+ | C | D+ | D | F | CS | CU.");
        }
        if (module.contains("n/")) {
            String moduleName = module.replace("n/","");
            return new MarkAsDoneCommand(moduleName, grading);
        } else if (module.contains("id/")) {
            String moduleId = module.replace("id/","").toUpperCase();
            return new MarkAsDoneCommand(moduleId, grading);
        }
        return null;
    }

    private static Command processAddCommand(String args) throws InputException {
        try {
            if (args.contains("s/")) {
                return processAddToSemCommand(args);
            } else {
                return processAddToDataCommand(args);
            }
        } catch (NullPointerException e) {
            throw new InputException("Wrong add command!");
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
        semester = moduleDetails[0].trim();
        try {
            if (Integer.parseInt(semester) < 1 || Integer.parseInt(semester) > 10) {
                throw new InputException("Semester should be between 1 - 10.");
            }

            int moduleCredit = Integer.parseInt(moduleDetails[1]);
            if (moduleCredit < 1 || moduleCredit > 10) {
                throw new InputException("Module credit should be between 1 - 10.");
            }

            if (module.contains("id/")) {
                String moduleId = module.replace("id/","");

                /* if contains module name */
                if (moduleId.contains("n/")) {
                    String[] idAndName = moduleId.split("n/");
                    String id = idAndName[0].toUpperCase().trim();

                    if (id.equals("")) {
                        throw new InputException("invalid add command.");
                    }

                    /* if module code is invalid */
                    if (!isStandardCodeFormat(id)) {
                        throw new InputException("invalid module code: " + id);
                    }

                    String name = idAndName[1].trim();
                    return new AddToSemCommand(new SelectedModule("Both", id, name, semester, moduleCredit));
                } else {
                    moduleId = moduleId.toUpperCase();
                }
                /* if module code is invalid */
                if (!isStandardCodeFormat(moduleId)) {
                    throw new InputException("invalid module code: " + moduleId);
                }
                moduleId = moduleId.toUpperCase();
                return new AddToSemCommand(new SelectedModule("id", moduleId, semester, moduleCredit));
            } else if (module.contains("n/")) {
                String moduleName = module.replace("n/","");
                return new AddToSemCommand(new SelectedModule("name", moduleName, semester, moduleCredit));
            }
        } catch (NumberFormatException e) {
            throw new InputException("Invalid 'add' command.");
        }
        return null;
    }

    private static AddToAvailableCommand processAddToDataCommand(String args) throws InputException {
        String[] moduleWords;
        moduleWords = args.split("id/");
        if (moduleWords.length < 2) {
            throw new InputException("invalid 'add' command",
                    "add id/ID n/NAME mc/MODULE_CREDIT pre/PREREQMODULES]");
        }
        moduleWords = moduleWords[1].split(" n/");
        if (moduleWords.length < 2) {
            throw new InputException("invalid 'add' command",
                    "add id/ID n/NAME mc/MODULE_CREDIT pre/PREREQMODULES");
        }

        String moduleId = moduleWords[0].toUpperCase().trim();
        if (!isStandardCodeFormat(moduleId)) {
            throw new InputException("invalid module code: " + moduleId);
        }

        moduleWords = moduleWords[1].split(" mc/");
        if (moduleWords.length < 2) {
            throw new InputException("invalid 'add' command",
                    "add id/ID n/NAME mc/MODULE_CREDIT pre/PREREQMODULES");
        }
        String moduleName = moduleWords[0].trim();
        moduleWords = moduleWords[1].split(" pre/");
        try {
            int moduleCredit = Integer.parseInt(moduleWords[0]);
            if (moduleCredit < 1 || moduleCredit > 10) {
                throw new InputException("Module credit should be between 1 - 10.");
            }
            if (moduleWords.length < 2) {
                return new AddToAvailableCommand(new NewModule(moduleId, moduleName, moduleCredit));
            }
            String[] preRequisiteModules;
            preRequisiteModules = moduleWords[1].toUpperCase().split(" ");
            checkPreReqValidity(preRequisiteModules, moduleId);
            return new AddToAvailableCommand((new NewModule(moduleId, moduleName, moduleCredit, preRequisiteModules)));
        } catch (NumberFormatException e) {
            throw new InputException("Invalid 'add' command.");
        }
    }

    private static void checkPreReqValidity(String[] preRequisiteModules, String moduleId) throws InputException {
        for (String preReq : preRequisiteModules) {
            if (preReq.equals(moduleId) || !isStandardCodeFormat(preReq)) {
                throw new InputException(String.format("Prerequisites of <%s> is invalid.", moduleId));
            }
        }
    }

    private static ViewCommand processViewCommand(String args) throws InputException {
        if (args.equalsIgnoreCase("/cm")) {
            return new ViewCommand(ViewCommand.VIEW_COMPULSORY_MODULES);
        } else if (args.equalsIgnoreCase("/dm")) {
            return new ViewCommand(ViewCommand.VIEW_DONE_MODULES);
        } else if (args.equalsIgnoreCase("/mp")) {
            return new ViewCommand(ViewCommand.VIEW_MODULE_PLAN);
        } else if (args.contains("n/")) {
            String moduleToBeViewed = args.replace("n/","").trim();
            return new ViewCommand(ViewCommand.VIEW_SPECIFIC_MODULE, moduleToBeViewed);
        } else if (args.contains("id/")) {
            String moduleToBeViewed = args.replace("id/","").toUpperCase().trim();
            return new ViewCommand(ViewCommand.VIEW_SPECIFIC_MODULE, moduleToBeViewed);
        } else if (args.equalsIgnoreCase("/cc")) {
            return new ViewCommand(ViewCommand.VIEW_COMPLETED_CREDITS);
        } else if (args.equals("")) {
            return new ViewCommand(ViewCommand.VIEW_AVAILABLE_MODULES);
        }
        throw new InputException("It seems like you are trying to view something, "
                + "but your command is not completely right. "
                + "Enter \"help\" to look at the possible view commands available");
    }

    private static ExitCommand processExitCommand() {
        return new ExitCommand();
    }

    private static HelpingCommand processHelpCommand() {
        return new HelpingCommand();
    }

    private static FindCommand processFindCommand(String args) throws InputException {
        if (args.length() < 1) {
            throw new InputException("Please enter your keyword to find.");
        }
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
        if (moduleWords.length < 2) {
            throw new InputException("invalid 'delete' command to delete from Selected Modules",
                    "delete id/ID s/SEM OR delete n/NAME s/SEM");
        }
        if (args.contains("id/")) {
            String moduleId = moduleWords[0].replace("id/", "").toUpperCase().trim();
            String semester = moduleWords[1].trim();
            return new DeleteFromSemCommand(moduleId, semester);
        } else if (args.contains("n/")) {
            String moduleName = moduleWords[0].replace("n/", "");
            String semester = moduleWords[1].trim();
            return new DeleteFromSemCommand(moduleName, semester);
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
        String moduleIdentifier = moduleWords[1];
        if (moduleWords[0].equals("id")) {
            return new DeleteFromAvailableCommand(moduleIdentifier.toUpperCase().trim(), "id");
        } else if (moduleWords[0].equals("n")) {
            return new DeleteFromAvailableCommand(moduleIdentifier.trim(), "name");
        }
        throw new InputException("invalid 'delete' command to delete from Available Modules",
                "delete id/ID OR delete n/NAME");
    }

    /**
     * Checks whether the module code is valid.
     * @param moduleId module's id.
     * @return whether the module code is valid.
     */
    public static boolean isStandardCodeFormat(String moduleId) {
        String pattern = "^[A-Za-z]{2,3}\\d{4}[A-Za-z]?$";
        boolean isMatch = Pattern.matches(pattern, moduleId);
        return isMatch;
    }
}
