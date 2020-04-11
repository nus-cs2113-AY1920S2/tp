package seedu.duke.command;

import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.record.Appointment;
import seedu.duke.storage.AppointmentList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

//@@author thanhduc2000
/**
 * Delete an appointment's record with certain index in the appointment's list.
 *
 * @version 1.0
 * @since 2020-03-14
 */
public class DeleteAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "deletea";
    public static final String EXAMPLE = "deletea \\index 12";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete an appointment from the list.\n"
            + "Example: " + EXAMPLE;
    private static final String APPOINTMENT_INDEX = "index";
    private int index;

    /**
     * Constructor which pass a hash map with only 1 item containing the index of
     * the patient that needs to be removed.
     *
     * @param fieldsToChange a hash map with only 1 item which is a field called
     *                       "index" and the value of the index needed to delete
     */
    public DeleteAppointmentCommand(Map<String, String> fieldsToChange) throws IndexOutOfBoundsException,
            InvalidFormatException {

        try {
            this.index = Integer.parseInt(fieldsToChange.get(APPOINTMENT_INDEX));
            if (index > AppointmentList.getTotalAppointments() || index <= 0) {
                throw new IndexOutOfBoundsException();
            }

        } catch (NumberFormatException e) {
            Ui.showNumberError();

        } catch (IndexOutOfBoundsException e) {
            Ui.showIndexError();
        }
    }

    /**
     * Method to check if the right index is returned to the class.
     *
     * @return index index in the list that information needs to be updated
     */
    public int getIndex() {
        return index;
    }


    /**
     * Method to delete the appointment from the list by getting that appointment's index then
     * remove it and auto-save the changes.
     *
     * @param ui      the ui object which can be used to display text
     * @param storage the storage object for auto saving function
     * @throws IOException when there is error in the index's input
     * @see IOException
     * @see AppointmentList#getAppointmentRecord
     * @see Storage#saveAppointmentsList
     * @see Ui#showDeleteAppointmentSuccess()
     */
    @Override
    public void execute(Ui ui, Storage storage) throws IOException {
        try {
            // Get the appointment's record based on its index from the list
            Appointment appointment = AppointmentList.getAppointmentRecord(index - 1);

            // Get the original appointment's list size
            int originalSize = AppointmentList.getTotalAppointments();

            // Remove the appointment's information from the patient's list
            AppointmentList.getAppointmentList().remove(appointment);

            // Check with assertions that the size has been decremented
            assert AppointmentList.getTotalAppointments() == originalSize - 1;

            // Auto-save the changes
            storage.saveAppointmentsList();

            // Show deleted appointment successfully message
            Ui.showDeleteAppointmentSuccess();

        } catch (IndexOutOfBoundsException | ParseException e) {
            return;
        }
    }
}
