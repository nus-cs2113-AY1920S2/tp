package seedu.duke.command;

import seedu.duke.data.Address;
import seedu.duke.data.Age;
import seedu.duke.data.Name;
import seedu.duke.data.Phone;
import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.record.Patient;
import seedu.duke.storage.PatientList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.io.IOException;
import java.util.Map;

//@@author thanhduc2000
/**
 * Edit the information of the patient existed in the patient's list.
 *
 * @version 0.1
 * @since 2020-03-08
 */
public class EditPatientCommand extends Command {

    public static final String COMMAND_WORD = "editp";
    public static final String EXAMPLE = "editp \\index 5  \\address Clementi \\phone 83487846";
    public static final String PATIENT_INDEX = "index";
    public static final String PATIENT_NAME = "name";
    public static final String AGE = "age";
    public static final String ADDRESS = "address";
    public static final String CONTACT_NUMBER = "phone";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Update the information of patient in the list.\n"
            + "Example: " + EXAMPLE;
    public static final String BLANK_STRING = "";
    public static final int INVALID_AGE = 0;

    private int patientIndex;
    private String patientName;
    private int age;
    private String address;
    private String contactNumber;

    /**
     * Constructor which pass a hash map with keys as fields to change and values
     * as content in that fields that needs to be changed. If there is no need to
     * change in a field in the patient's record, it will be automatically set as
     * null.
     *
     * @param fieldsToChange a hash map which pass all the fields needed to be changed
     *                       as key and content as values
     */
    public EditPatientCommand(Map<String, String> fieldsToChange) throws InvalidFormatException {
        try {
            this.patientIndex = Integer.parseInt(fieldsToChange.get(PATIENT_INDEX));
            if (patientIndex > PatientList.getTotalPatients() || patientIndex <= 0) {
                throw new IndexOutOfBoundsException();
            }

        } catch (NumberFormatException e) {
            Ui.showNumberError();

        } catch (IndexOutOfBoundsException e) {
            Ui.showIndexError();
        }
        this.patientName = new Name(fieldsToChange.get(PATIENT_NAME)).toString();
        this.age = new Age(fieldsToChange.get(AGE)).getAge();
        this.address = new Address(fieldsToChange.get(ADDRESS)).toString();
        this.contactNumber = new Phone(fieldsToChange.get(CONTACT_NUMBER)).toString();
    }

    /**
     * Method to check if the right index is returned to the class.
     *
     * @return patientIndex index of patient that needs to be updated
     */
    public int getPatientIndex() {
        return patientIndex;
    }

    /**
     * Method to check if the right patient's name is returned to the class.
     *
     * @return patientName name of patient that needs to be updated
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * Method to check if the right age is returned to the class.
     *
     * @return age age that needs to be updated
     */
    public int getAge() {
        return age;
    }

    /**
     * Method to check if the right address is returned to the class.
     *
     * @return address that needs to be updated
     */
    public String getAddress() {
        return address;
    }

    /**
     * Method to check if the right phone number is returned to the class.
     *
     * @return contactNumber that needs to be updated
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Method to update the patient by getting the patient's record based on its index
     * and update it based on the queries by users, and auto-save it to the data file.
     *
     * @param ui      the ui object which can be used to display text
     * @param storage the storage object for auto saving function
     * @throws IOException when there is error in the index's input
     * @see IOException
     * @see PatientList#getPatientRecord
     * @see Patient#setPatientInfo
     * @see Storage#savePatientList
     * @see Ui#showUpdatePatientSuccess
     */
    @Override
    public void execute(Ui ui, Storage storage) throws IOException, IndexOutOfBoundsException {
        try {
            // Get the patient's record based on its index from the list
            Patient patient = PatientList.getPatientRecord(patientIndex - 1);

            // Updating the information
            patient.setPatientInfo(patientName, age, address, contactNumber);

            // Updating it back to its corresponding index in the patient's list
            PatientList.getPatientList().set(patientIndex - 1, patient);

            // Check with assertions to make sure that the updated fields are correct
            assert patientName.equals(BLANK_STRING)
                    || PatientList.getPatientRecord(patientIndex - 1).getName().equals(patientName);
            assert age < INVALID_AGE
                    || PatientList.getPatientRecord(patientIndex - 1).getAge() == age;
            assert address.equals(BLANK_STRING)
                    || PatientList.getPatientRecord(patientIndex - 1).getAddress().equals(address);
            assert contactNumber.equals(BLANK_STRING)
                    || PatientList.getPatientRecord(patientIndex - 1).getContactNumber()
                    .equals(contactNumber);

            // Auto-save the changes
            storage.savePatientList();

            // Show updated successfully patient's list message
            Ui.showUpdatePatientSuccess();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return;
        }
    }
}
