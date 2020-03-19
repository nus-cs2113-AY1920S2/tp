package seedu.happypills.commands;

import seedu.happypills.data.PatientList;
import seedu.happypills.ui.TextUi;

/**
 * Displays full help instructions for every command.
 */
public class HelpCommand extends Command {
    protected String command;

    public HelpCommand(String command) {
        this.command = command;
    }

    /**
     * Return help command instructions.
     *
     * @param patients Contains the list of tasks on which the commands are executed on.
     * @return message The command instructions.
     */
    public String execute(PatientList patients) {
        String message;
        if (command.equals("")) {
            message = TextUi.getHelp();
        } else if (command.equalsIgnoreCase("add")) {
            message = TextUi.printAddHelp();
        } else if (command.equalsIgnoreCase("list")) {
            message = TextUi.printListHelp();
        } else if (command.equalsIgnoreCase("get")) {
            message = TextUi.printGetHelp();
        } else if (command.equalsIgnoreCase("edit")) {
            message = TextUi.printEditHelp();
        } else if (command.equalsIgnoreCase("delete")) {
            message = TextUi.printDeleteHelp();
        } else if (command.equalsIgnoreCase("help")) {
            message = TextUi.printHelpHelp();
        } else if (command.equalsIgnoreCase("exit")) {
            message = TextUi.printExitHelp();
        } else {
            message = "    The command \"" + command + "\" does not exist. Please try again.\n"
                    + TextUi.DIVIDER;
        }
        return message;
    }
}
