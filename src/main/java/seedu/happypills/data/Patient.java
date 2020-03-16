package seedu.happypills.data;

/**
 * Represents a task.
 * It also functions as base class from which specialised tasks are inherited from.
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


    /**
     * Constructor for Task Class.
     * It creates a new task with the description provided by the user.
     * It also sets isDone to false as it is a newly created task.
     *
     * @param name Name of the patient.
     * @param nric NRIC of the patient.
     */
    /**
     * Constructor for Task Class.
     * It creates a new task with the description provided by the user.
     * It also sets isDone to false as it is a newly created task.
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
        this.name = name;
        this.nric = nric;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
        this.allergies = allergies;
        this.remarks = remarks;
    }

    /**

     *
     * @param name Name of the patient.
     * @param nric NRIC of the patient.
     */
    /**
     * Constructor for Task Class.
     * It creates a new task with the description provided by the user.
     * It also sets isDone to false as it is a newly created task.
     *
     * @param name          Name of the patient.
     * @param nric          NRIC of the patient.
     * @param phoneNumber   Phone number of the patient.
     * @param dateOfBirth   Date of birth of the patient.
     * @param bloodType     Blood type of the patient.
     * @param allergies     Allergies the patient may have.
     */
    public Patient(String name, String nric, int phoneNumber, String dateOfBirth,
                   String bloodType, String allergies) {
        this.name = name;
        this.nric = nric;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
        this.allergies = allergies;
    }

    /**
     * Constructor for Task Class.
     * It creates a new task with the description provided by the user.
     * It also sets isDone to false as it is a newly created task.
     *
     * @param name          Name of the patient.
     * @param nric          NRIC of the patient.
     * @param phoneNumber   Phone number of the patient.
     * @param dateOfBirth   Date of birth of the patient.
     * @param bloodType     Blood type of the patient.
     * @param remarks       Remarks for the patient.
     */
    public Patient(String name, String nric, String dateOfBirth,
                   int phoneNumber, String bloodType, String remarks) {
        this.name = name;
        this.nric = nric;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
        this.remarks = remarks;
    }

    /**
     * Constructor for Task Class.
     * It creates a new task with the description provided by the user.
     * It also sets isDone to false as it is a newly created task.
     *
     * @param name          Name of the patient.
     * @param nric          NRIC of the patient.
     * @param phoneNumber   Phone number of the patient.
     * @param dateOfBirth   Date of birth of the patient.
     * @param bloodType     Blood type of the patient.
     */
    public Patient(String name, String nric, String dateOfBirth,
                   int phoneNumber, String bloodType) {
        this.name = name;
        this.nric = nric;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
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

    /**
     * Create a string with all the patient's data for storage to a text file.
     * Each variable is separated with | as a divider.
     *
     * @return a formatted string with patient's data.
     */
    public String toSave() {
        String text = this.name + "|" + this.nric + "|"
                + this.phoneNumber + "|" + this.dateOfBirth + "|"
                + this.bloodType + "|" + this.allergies + "|" + this.remarks;
        return text;
    }
}