package seedu.happypills.commands;

import seedu.happypills.HappyPills;
import seedu.happypills.data.Patient;
import seedu.happypills.data.PatientList;
import seedu.happypills.exception.HappyPillsException;
import seedu.happypills.ui.TextUi;

import java.util.logging.Level;
import java.util.logging.Logger;


public class AddCommand extends Command {
    protected String name;
    protected String nric;
    protected int phoneNumber;
    protected String dateOfBirth;
    protected String bloodType;
    protected String allergies;
    protected String remarks;
    Logger logger = Logger.getLogger(HappyPills.class.getName());
    Level logLevel = Level.INFO;
    
    /**
     * Constructor for AddCommand Class.
     * It creates a new AddCommand Object with the information provided.
     *
     * @param name          Contains the name of the patient.
     * @param nric          Contains the nric of the patient.
     * @param phoneNumber   Contains the phone number of the patient.
     * @param dateOfBirth   Contains the date of birth of the patient.
     * @param bloodType     Contains the blood type of the patient.
     * @param allergies     Contains any allergies the patient has.
     * @param remarks       Contains any remarks for the patient.
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
        logger.log(logLevel, "patient is add");
    }

    private boolean isInList(PatientList patients) {
        for (Patient p : patients) {
            if (p.getNric().equalsIgnoreCase(nric)) {
                logger.log(logLevel, "patient in the list");
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieve the patient from the NRIC of the Add command.
     *
     * @param patients Contains the list of patients to be searched.
     */
    private Patient findPatient(PatientList patients) {
        for (Patient patient : patients) {
            if (patient.getNric().equalsIgnoreCase(nric)) {
                assert !nric.isEmpty() : "No NRIC was provided";
                logger.log(logLevel, "patient detail is found and return to user");
                return patient;
            }
        }
        return null;
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
        String message = "";
        int patientNum = patients.size();
        if (name.equalsIgnoreCase("")) {
            Patient addPatient = findPatient(patients);
            if (!remarks.equalsIgnoreCase("")) {
                String rem = addPatient.getRemarks();
                if (!rem.equalsIgnoreCase("")) {
                    rem += "\n                  " + remarks;
                } else {
                    rem = remarks;
                }
                addPatient.setRemarks(rem);
                message = TextUi.printEditSuccess(addPatient);
            } else if (!allergies.equalsIgnoreCase("")) {
                String patientAllergies = addPatient.getAllergies();
                if (!patientAllergies.equalsIgnoreCase("")) {
                    patientAllergies += "\n                    " + allergies;
                } else {
                    patientAllergies = allergies;
                }
                addPatient.setAllergies(patientAllergies);
                message = TextUi.printEditSuccess(addPatient);
            }
        } else {
            if (isInList(patients)) {
                message = TextUi.patientExists(nric);
            } else {
                patients.add(new Patient(name, nric, phoneNumber, dateOfBirth, bloodType, allergies, remarks));
                message = TextUi.getPatient(patients.get(patientNum), patientNum);
            }
        }
        logger.log(logLevel, "end of addCommand");
        return message;
    }
}
