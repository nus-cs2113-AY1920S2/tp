package seedu.duke.command;

import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;

//@@author thanhduc2000
/**
 * Show a list of commands and what is the correct input's format.
 *
 * @version 0.1
 * @since 2020-03-08
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String COMMANDS_MESSAGE_USAGE = AddAppointmentCommand.MESSAGE_USAGE
            + "\n" + "\n" + AddPatientCommand.MESSAGE_USAGE
            + "\n" + "\n" + ClearAllCommand.MESSAGE_USAGE
            + "\n" + "\n" + ClearAppointmentCommand.MESSAGE_USAGE
            + "\n" + "\n" + ClearPatientCommand.MESSAGE_USAGE
            + "\n" + "\n" + EditAppointmentCommand.MESSAGE_USAGE
            + "\n" + "\n" + EditPatientCommand.MESSAGE_USAGE
            + "\n" + "\n" + FindAppointmentCommand.MESSAGE_USAGE
            + "\n" + "\n" + FindPatientCommand.MESSAGE_USAGE
            + "\n" + "\n" + DeleteAppointmentCommand.MESSAGE_USAGE
            + "\n" + "\n" + DeletePatientCommand.MESSAGE_USAGE
            + "\n" + "\n" + ListAppointmentCommand.MESSAGE_USAGE
            + "\n" + "\n" + ListPatientCommand.MESSAGE_USAGE
            + "\n" + "\n" + HelpCommand.MESSAGE_USAGE
            + "\n" + "\n" + ExitCommand.MESSAGE_USAGE;

    public HelpCommand() throws InvalidFormatException {

    }

    /**
     * Method to print out all the commands that user can input with usage and examples of them.
     *
     * @param ui      the ui object which can be used to display text
     * @param storage the storage object for auto saving function
     * @throws IOException when there is error in the index's input
     * @see IOException
     * @see Ui#showHelpUsage
     */
    @Override
    public void execute(Ui ui, Storage storage) throws IOException {
        Ui.showHelpUsage(COMMANDS_MESSAGE_USAGE);
    }
}
