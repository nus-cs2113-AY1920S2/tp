package seedu.happypills.commands;

import seedu.happypills.data.Patient;
import seedu.happypills.data.PatientList;
import seedu.happypills.ui.TextUi;


public class AddCommand extends Command {
    protected String name;
    protected String nric;
    protected int phoneNumber;
    protected String dateOfBirth;
    protected String bloodType;
    protected String allergies;
    protected String remarks;

    /**
     * Constructor for AddCommand Class.
     * It creates a new AddCommand Object with the information provided.
     *
     * @param name        Contains the name of the patient.
     * @param nric        Contains the nric of the patient.
     * @param phoneNumber Contains the phone number of the patient.
     */
    public AddCommand(String name, String nric, int phoneNumber, String dateOfBirth,
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
     * Adds a new task to the list with the information provided by calling.
     * {} (or) {}
     * (or) {} as require
     *
     * @param patients Contains the list of tasks on which the commands are executed on.
     */
    @Override
    public String execute(PatientList patients) {
        String message;
        int patientNum = patients.size();
        patients.add(new Patient(name, nric, phoneNumber, dateOfBirth, bloodType, allergies, remarks));
        message = TextUi.getPatient(patients.get(patientNum), patientNum);
        return message;
    }
}
