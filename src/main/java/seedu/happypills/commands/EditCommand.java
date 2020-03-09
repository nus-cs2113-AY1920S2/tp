package seedu.happypills.commands;

import org.w3c.dom.Text;
import seedu.happypills.data.Patient;
import seedu.happypills.data.PatientList;
import seedu.happypills.exception.HappyPillsException;
import seedu.happypills.ui.TextUi;

public class EditCommand extends Command {
    protected String name;
    protected String nric;
    protected int phoneNumber;
    protected String dateOfBirth;
    protected String bloodType;
    protected String allergies;
    protected String remarks;
    protected String editField;
    protected String newContent;

    /**
     * Constructor for EditCommand Class.
     * It creates a new EditCommand Object with the information provided.
     *
     * @param nric Contains the nric of the patient that is to be retrieved.
     * @param editField Contains the attribute that is to be edited.
     * @param newContent Contains the string that the attribute is to be updated to.
     */
    public EditCommand(String nric, String editField, String newContent) {
        this.nric = nric;
        this.editField = editField;
        this.newContent = newContent;
    }

    /**
     * Edit the attribute of the patient according to the given attribute.
     *
     * @param patients Contains the list of tasks on which the commands are executed on.
     * @throws HappyPillsException Throws an exception if patient does not exist in patient list.
     */
    private void editChanges(PatientList patients) throws HappyPillsException {
        boolean inList = false;
        for (Patient patient : patients) {
            if (patient.getNric().equals(nric)) {
                inList = true;
                if (editField.equals("phone")) {
                    patient.setPhoneNumber(Integer.parseInt(newContent));
                } else if (editField.equals("allergies")) {
                    patient.setAllergies(newContent);
                } else if (editField.equals("remarks")) {
                    patient.setRemarks(newContent);
                }
                TextUi.printEditSuccess(patient);
                break;
            }
        }
        if (!inList) {
            throw new HappyPillsException("NRIC not in patient list.");
        }
    }

    /**
     * Adds a new task to the list with the information provided by calling.
     * {} (or) {}
     * (or) {} as require
     *
     * @param patients Contains the list of tasks on which the commands are executed on.
     * @throws HappyPillsException Throws an exception if the edit field is not valid.
     */
    @Override
    public void execute(PatientList patients) throws HappyPillsException {
        if (editField.equals("phone") || editField.equals("allergies") || editField.equals("remarks")) {
            editChanges(patients);
        } else {
            throw new HappyPillsException("Edit field is not valid.");
        }
    }
}
