package seedu.duke.command;

import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.record.Patient;
import seedu.duke.storage.PatientList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class deals with the command relating to finding the list of appointments containing a specific keyword.
 * <p></p>
 * <p>
 * This command utilises the functions in {@link seedu.duke.parser.Parser} and searches {@link Patient} objects
 * within the saved {@link PatientList} that is managed by {@link Storage}.
 * </p>
 * <p></p>
 * <p>
 * To elaborate, it converts the output of {@link seedu.duke.parser.Parser} into a {@link Patient} object, after
 * which the {@link Patient} object is then
 * added into the {@link PatientList} list. Finally, the {@link Storage} class will be used to save the updated
 * list into offline data to reflect the change.
 * </p>
 *
 * @author Brandon Chong
 * @version v2.0
 * @since 28/3/2020
 */
public class FindPatientCommand extends Command {

    public static final String COMMAND_WORD = "findp";
    private static final String EXAMPLE = "findp john tan";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Find a patient by keyword.\n"
            + "Example: " + EXAMPLE;
    private String searchValue;

    /**
     * Constructor for the find patient command class.
     *
     * @param searchValue the <code>String</code> object representing the keyword to search for in
     *                    each Patient object's fields
     */
    public FindPatientCommand(String searchValue) throws InvalidFormatException {
        this.searchValue = searchValue;
    }

    public String getsearchValue() {
        return searchValue;
    }

    /**
     * For this execution, the existing list of Patient records is searched for a keyword.
     * Records that contain the keyword will be added to a separate List and printed out in a readable format.
     *
     * @param ui      the ui object which can be used to display text
     * @param storage the storage object for auto saving function
     * @throws IOException this exception is thrown by the {@link Storage} class if it fails to save the current
     *                     Patient list into offline data.
     */
    @Override
    public void execute(Ui ui, Storage storage) throws IOException {
        List<Patient> searchResults = new ArrayList<>();
        /*get the list of all Patients from Storage to conduct search*/
        for (Patient patient : PatientList.getPatientList()) {
            if (patient.getName().contains(this.getsearchValue())
                    || Integer.toString(patient.getAge()).contains(this.getsearchValue())
                    || patient.getAddress().contains(this.getsearchValue())
                    || patient.getContactNumber().contains(this.getsearchValue())
                    || Integer.toString(patient.getPatientID()).contains(this.getsearchValue())) {
                searchResults.add(patient);
            }
        }
        ui.printPatientSearchResults(searchResults);
    }

}
