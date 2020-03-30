package seedu.happypills.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
     * It creates a new patient with the description provided by the user.
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
     * Returns the status of the task in form of an icon.
     * "\u2713" is returned to denote that the task is complete.
     * "\u2718" is returned to denote that the task is incomplete.
     *
     * @return statusIcon Represents the current status of the task as described above.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the nric of the patient.
     *
     * @return nric Represents the nric of the patient.
     */
    public String getNric() {
        return this.nric;
    }

    /**
     * Returns the phone number of the patient.
     *
     * @return phoneNumber Represents the phone number of the patient.
     */
    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Returns the date of birth of the patient.
     *
     * @return phoneNumber Represents the date of birth of the patient.
     */
    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    /**
     * Returns the blood type of the patient.
     *
     * @return phoneNumber Represents the blood type of the patient.
     */
    public String getBloodType() {
        return this.bloodType;
    }

    /**
     * Returns the allergies of the patient.
     *
     * @return allergies Represents the allergies of the patient.
     */
    public String getAllergies() {
        return this.allergies;
    }

    /**
     * Returns the remarks of the patient.
     *
     * @return remarks Represents the remarks of the patient.
     */
    public String getRemarks() {
        return this.remarks;
    }

    /**
     * Returns the ArrayList of appointments of the patient.
     *
     * @return appointments Represents the ArrayList of appointments of the patient.
     */
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment appt) {
        this.appointments.add(appt);
    }

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

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    /**
     * Create a string with all the patient's data for storage to a text file.
     * Each variable is separated with | as a divider.
     *
     * @return a formatted string with patient's data.
     */
    public String toSave() {
        String text = this.name + "|" + this.nric + "|"
                + this.phoneNumber + "|" + this.dateOfBirth + "|"
                + this.bloodType + "|" + this.allergies + "|" + this.remarks + System.lineSeparator();
        return text;
    }

    private static boolean dateValidation(String date) {
        boolean status = false;
        if (checkDate(date)) {
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

    static boolean checkDate(String date) {
        String pattern = "(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[0-2])\\/([0-9]{4})";
        boolean flag = false;
        if (date.matches(pattern)) {
            flag = true;
        }
        return flag;
    }
}