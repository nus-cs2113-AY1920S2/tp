package seedu.duke.command;

import seedu.duke.exceptions.DukeExceptions;
import seedu.duke.exceptions.EmptyAppointmentsException;
import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.storage.AppointmentList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.text.ParseException;

//@@author thanhduc2000
/**
 * Clear all appointments from appointments' list.
 *
 * @version 2.0
 * @since 2020-03-29
 */
public class ClearAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "cleara";
    public static final String EXAMPLE = "cleara";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clear all appointments from the list.\n"
            + "Example: " + EXAMPLE;

    /**
     * Empty constructor to throw exception.
     *
     * @throws InvalidFormatException inherit from the Command class
     */
    public ClearAppointmentCommand() throws InvalidFormatException {

    }

    /**
     * Method to clear all appointments in the list if available, if
     * there is nothing to clear return a warning to users.
     *
     * @param ui      the ui object which can be used to display text
     * @param storage the storage object for auto saving function
     * @throws IOException    when there is error in the index's input
     * @throws ParseException when there is error in the index's input
     * @see IOException
     * @see ParseException
     * @see DukeExceptions#checkEmptyAppointments
     * @see AppointmentList#getAppointmentList
     * @see Storage#saveAppointmentsList
     */
    @Override
    public void execute(Ui ui, Storage storage) throws IOException, ParseException {
        try {
            //Check if appointments' list is empty
            DukeExceptions.checkEmptyAppointments();

            // Clear all the items in appointments' list
            AppointmentList.getAppointmentList().clear();

            // Make sure that the size of appointments' list is 0
            assert AppointmentList.getTotalAppointments() == 0;

            // Auto save the change in appointments' list
            storage.saveAppointmentsList();

            // Show all items in both lists deleted message
            ui.showAppointmentsDeleted();

        } catch (EmptyAppointmentsException e) {
            ui.showNothingToClearAppointments();
        }
    }
}
