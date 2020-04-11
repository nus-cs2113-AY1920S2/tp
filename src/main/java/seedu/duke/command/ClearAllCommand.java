package seedu.duke.command;

import seedu.duke.exceptions.DukeExceptions;
import seedu.duke.exceptions.EmptyListsException;
import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.generator.PatientIdManager;
import seedu.duke.storage.AppointmentList;
import seedu.duke.storage.PatientList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.text.ParseException;

//@@author thanhduc2000
/**
 * Clear all the patients from patients' list and appointments from appointments' list.
 *
 * @version 2.0
 * @since 2020-03-29
 */
public class ClearAllCommand extends Command {

    public static final String COMMAND_WORD = "clearall";
    public static final String EXAMPLE = "clearall";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clear all patients and appointments from both lists.\n"
            + "Example: " + EXAMPLE;

    /**
     * Empty constructor to throw exception.
     *
     * @throws InvalidFormatException inherit from the Command class
     */
    public ClearAllCommand() throws InvalidFormatException {

    }

    /**
     * Method to clear all the items in both lists if available, if
     * there is nothing to clear return a warning to users.
     *
     * @param ui      the ui object which can be used to display text
     * @param storage the storage object for auto saving function
     * @throws IOException    when there is error in the index's input
     * @throws ParseException when there is error in the index's input
     * @see IOException
     * @see ParseException
     * @see DukeExceptions#checkEmptyLists
     * @see AppointmentList#getAppointmentList
     * @see PatientList#getPatientList
     * @see Storage#saveAppointmentsList
     * @see Storage#savePatientList
     */
    @Override
    public void execute(Ui ui, Storage storage) throws IOException, ParseException {
        try {
            //Check if the list is empty or not
            DukeExceptions.checkEmptyLists();

            // Clear all the items in appointments' list
            AppointmentList.getAppointmentList().clear();

            // Clear all the items in patients' list
            PatientList.getPatientList().clear();

            // Reset the patient id manager state
            PatientIdManager.clearPatientId();

            // Make sure that the size of both patients' list and appointments' list are 0
            assert AppointmentList.getTotalAppointments() == 0 && PatientList.getTotalPatients() == 0;

            // Auto save the change in appointments' list
            storage.saveAppointmentsList();

            // Auto save the change in patients' list
            storage.savePatientList();

            // Show all items in both lists deleted message
            ui.showAllItemsDeleted();
        } catch (EmptyListsException e) {
            ui.showNothingToClearBothLists();
        }
    }
}
