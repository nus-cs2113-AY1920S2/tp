package seedu.duke.command;

import seedu.duke.converter.TimeConverter;
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
 * Edit the information of the appointment existed in the appointment's list.
 *
 * @version 1.0
 * @since 2020-03-14
 */
public class EditAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "edita";
    public static final String EXAMPLE = "edita \\index 5  \\date 01/03/2020 \\time 10am";
    public static final String APPOINTMENT_INDEX = "index";
    public static final String APPOINTMENT_DATE = "date";
    public static final String APPOINTMENT_TIME = "time";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edit the information of appointment in the list.\n"
            + "Example: " + EXAMPLE;
    public static final String BLANK_STRING = "";

    private int index;
    private String date;
    private String time;

    /**
     * Constructor which pass a hash map with keys as fields to change and values
     * as content in that fields that needs to be changed. If there is no need to
     * change in a field in the appointment's record, it will be automatically set as
     * null.
     *
     * @param fieldsToChange a hash map which pass all the fields needed to be changed
     *                       as key and content as values
     */
    public EditAppointmentCommand(Map<String, String> fieldsToChange) throws InvalidFormatException {
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

        this.date = fieldsToChange.get(APPOINTMENT_DATE);
        this.time = fieldsToChange.get(APPOINTMENT_TIME);
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
     * Method to check if the right date is returned to the class.
     *
     * @return date that needs to be updated
     */
    public String getDate() {
        return date;
    }

    /**
     * Method to check if the right time is returned to the class.
     *
     * @return time needs to be updated
     */
    public String getTime() {
        return time;
    }

    /**
     * Method to update the appointment by getting the appointment's record based on its index
     * and update it based on the queries by users, and auto-save it to the data file.
     *
     * @param ui      the ui object which can be used to display text
     * @param storage the storage object for auto saving function
     * @throws IOException when there is error in the index's input
     * @see IOException
     * @see AppointmentList#getAppointmentRecord
     * @see Appointment#setAppointmentInfo
     * @see Storage#saveAppointmentsList
     * @see Ui#showUpdateAppointmentSuccess
     */
    @Override
    public void execute(Ui ui, Storage storage) throws IOException, ParseException {
        try {
            // Get the appointment's record based on its index from the list
            Appointment appointment = AppointmentList.getAppointmentRecord(index - 1);

            // Updating the information
            appointment.setAppointmentInfo(date, time);

            // Updating it back to its corresponding index in the appointment's list
            AppointmentList.getAppointmentList().set(index - 1, appointment);

            // Check with assertions to make sure that the updated fields are correct
            assert date.equals(BLANK_STRING)
                    || AppointmentList.getAppointmentRecord(index - 1).getDate()
                    .equals(TimeConverter.oldDate(this.date)) : "Wrong date!";
            assert time.equals(BLANK_STRING)
                    || AppointmentList.getAppointmentRecord(index - 1).getTime()
                    .equals(TimeConverter.oldTime(this.time)) : "Wrong time!";

            // Auto-save the changes
            storage.saveAppointmentsList();

            // Show updated successfully message
            Ui.showUpdateAppointmentSuccess();
        } catch (IndexOutOfBoundsException e) {
            return;
        }
    }
}
