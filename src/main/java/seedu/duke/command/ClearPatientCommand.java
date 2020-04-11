package seedu.duke.command;

import seedu.duke.exceptions.DukeExceptions;
import seedu.duke.exceptions.EmptyPatientsException;
import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.generator.PatientIdManager;
import seedu.duke.storage.PatientList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.text.ParseException;

//@@author thanhduc2000
/**
 * Clear all patients from patients' list.
 *
 * @version 2.0
 * @since 2020-03-29
 */
public class ClearPatientCommand extends Command {

    public static final String COMMAND_WORD = "clearp";
    public static final String EXAMPLE = "clearp";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clear all patients from the list.\n"
            + "Example: " + EXAMPLE;

    /**
     * Empty constructor to throw exception.
     *
     * @throws InvalidFormatException inherit from the Command class
     */
    public ClearPatientCommand() throws InvalidFormatException {

    }

    /**
     * Method to clear all patients in the list if available, if
     * there is nothing to clear return a warning to users.
     *
     * @param ui      the ui object which can be used to display text
     * @param storage the storage object for auto saving function
     * @throws IOException    when there is error in the index's input
     * @throws ParseException when there is error in the index's input
     * @see IOException
     * @see ParseException
     * @see DukeExceptions#checkEmptyAppointments
     * @see PatientList#getPatientList
     * @see Storage#savePatientList
     * @see PatientIdManager#clearPatientId()
     */
    @Override
    public void execute(Ui ui, Storage storage) throws IOException, ParseException {
        try {
            // Check if patients' list is empty
            DukeExceptions.checkEmptyPatients();

            // Clear all the items in patients' list
            PatientList.getPatientList().clear();

            // CLear all the appointments as well
            new ClearAppointmentCommand().execute(ui, storage);

            // Reset the patient id manager state
            PatientIdManager.clearPatientId();

            // Make sure that the size of patients' list is 0
            assert PatientList.getTotalPatients() == 0;

            // Auto save the change in patients' list
            storage.savePatientList();

            // Show all patients deleted message
            ui.showPatientsDeleted();

        } catch (EmptyPatientsException | InvalidFormatException e) {
            ui.showNothingToClearPatients();
        }
    }
}
