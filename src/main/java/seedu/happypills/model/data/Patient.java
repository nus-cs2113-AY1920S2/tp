package seedu.happypills.model.data;

import seedu.happypills.logic.parser.Checker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

//@@author NyanWunPaing
/**
 * Represents a patient.
 * It also functions as base class for patients from which specialised tasks are inherited from.
 */
public class Patient {

    /**
     * Stores the description of the patient.
     */
    protected String name;
    protected String nric;
    protected int phoneNumber;
    protected String dateOfBirth;
    protected String bloodType;
    protected String allergies = null;
    protected String remarks = null;
    protected ArrayList<Appointment> appointments = null;

    /**
     * Constructor for Patient Class.
     * Creates a new patient with the description provided by the user.
     *
     * @param name          Name of the patient.
     * @param nric          NRIC of the patient.
     * @param phoneNumber   Phone number of the patient.
     * @param dateOfBirth   Date of birth of the patient.
     * @param bloodType     Blood type of the patient.
     * @param allergies     Allergies the patient may have.
     * @param remarks       Remarks for the patient.
     */
    public Patient(String name, String nric, int phoneNumber, String dateOfBirth,
                   String bloodType, String allergies, String remarks) {

        if (dateValidation(dateOfBirth)) {
            this.name = name;
            this.nric = nric;
            this.phoneNumber = phoneNumber;
            this.dateOfBirth = dateOfBirth;
            this.bloodType = bloodType;
            this.allergies = allergies;
            this.remarks = remarks;
            this.appointments = new ArrayList<Appointment>();
        } else {
            System.out.println("    Date of birth is invalid. Please try again in this format:"
                                + "\n    DD/MM/YYYY");
        }
    }

    /**
     * Getter function for name of the patient.
     *
     * @return name Represents the name of the patient.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter function for Nric of the patient.
     *
     * @return nric Represents the nric of the patient.
     */
    public String getNric() {
        return this.nric;
    }

    /**
     * Getter function for the phone number of the patient.
     *
     * @return phoneNumber Represents the phone number of the patient.
     */
    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Getter function for the date of birth of the patient.
     *
     * @return dateOfBirth Represents the date of birth of the patient.
     */
    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    /**
     * Getter function for the blood type of the patient.
     *
     * @return bloodType Represents the blood type of the patient.
     */
    public String getBloodType() {
        return this.bloodType;
    }

    /**
     * Getter function for the allergies of the patient.
     *
     * @return allergies Represents the allergies of the patient.
     */
    public String getAllergies() {
        return this.allergies;
    }

    /**
     * Getter function for the remarks of the patient.
     *
     * @return remarks Represents the remarks of the patient.
     */
    public String getRemarks() {
        return this.remarks;
    }

    /**
     * Getter function for the ArrayList of appointments of the patient.
     *
     * @return appointments Represents the ArrayList of appointments of the patient.
     */
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment appt) {
        this.appointments.add(appt);
    }

    /**
     * Returns the information related to the patient in form of a string.
     *
     * @return string The string that consists the patient's detailed information.
     */
    @Override
    public String toString() {
        String text = "        Name : " + this.name + "\n"
                + "        NRIC : " + this.nric + "\n"
                + "        Phone Number : " + this.phoneNumber + "\n"
                + "        DOB : " + this.dateOfBirth + "\n"
                + "        Blood Type : " + this.bloodType + "\n"
                + "        Allergies : " + this.allergies + "\n"
                + "        Remarks : " + this.remarks + "\n";
        return text;
    }

    /**
     * Setter for phone number of the patient.
     *
     * @param phoneNumber The phone number of the patient.
     */
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Setter for allergies of the patient.
     *
     * @param allergies The allergies of the patient.
     */
    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    /**
     * Setter for remarks of the patient.
     *
     * @param remarks The remarks of the patient.
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * Setter for name of the patient.
     *
     * @param name The name of the patient.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for allergies of the patient.
     *
     * @param dateOfBirth The date of birth of the patient.
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Setter for blood type of the patient.
     *
     * @param bloodType The blood type of the patient.
     */
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    /**
     * Creates a string with all the patient's data for storage to a text file.
     * Each variable is separated with | as a divider.
     *
     * @return text A formatted string with patient's details.
     */
    public String toSave() {
        String text = this.name + "|" + this.nric + "|"
                + this.phoneNumber + "|" + this.dateOfBirth + "|"
                + this.bloodType + "|" + this.allergies + "|" + this.remarks + System.lineSeparator();
        return text;
    }

    /**
     * Checks whether the date is in correct format.
     *
     * @return status The boolean to indicate the correctness of the date.
     */
    private static boolean dateValidation(String date) {
        boolean status = false;
        if (Checker.isValidDate(date)) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(date);
                status = true;
            } catch (Exception e) {
                status = false;
            }
        }
        return status;
    }
}