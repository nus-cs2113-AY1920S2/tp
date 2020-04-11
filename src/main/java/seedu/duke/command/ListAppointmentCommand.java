package seedu.duke.command;

import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;


/**
 * Command class to list the list of appointment.
 *
 * @author Andy
 */
public class ListAppointmentCommand extends Command {

    public static final String MESSAGE_USAGE = "lista : List all the appointments.\n"
            + "Example: lista";

    /**
     * Empty constructor. Only used to create the ListAppointmentCommand object.
     */

    public ListAppointmentCommand() throws InvalidFormatException {
    }

    /**
     * For this execution, it shows the list of appointments that are stored.
     *
     * @param ui      the ui object which can be used to display text
     * @param storage the storage object for auto saving function
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        Ui.showEntireAppointmentList();
    }
}

