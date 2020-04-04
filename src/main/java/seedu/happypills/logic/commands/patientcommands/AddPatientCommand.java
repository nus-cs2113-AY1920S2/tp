package seedu.happypills.logic.commands.patientcommands;

import seedu.happypills.HappyPills;
import seedu.happypills.logic.parser.Validation;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.storage.Storage;
import seedu.happypills.ui.Messages;
import seedu.happypills.ui.PatientTextUi;
import seedu.happypills.ui.StorageTextUi;
import seedu.happypills.ui.TextUi;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AddPatientCommand extends PatientCommand {
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
     * Constructor for AddPatientCommand Class.
     * It creates a new AddPatientCommand Object with the information provided.
     *
     * @param name        Contains the name of the patient.
     * @param nric        Contains the nric of the patient.
     * @param phoneNumber Contains the phone number of the patient.
     * @param dateOfBirth Contains the date of birth of the patient.
     * @param bloodType   Contains the blood type of the patient.
     * @param allergies   Contains any allergies the patient has.
     * @param remarks     Contains any remarks for the patient.
     */
    public AddPatientCommand(String name, String nric, int phoneNumber, String dateOfBirth,
                             String bloodType, String allergies, String remarks) {
        this.name = name;
        this.nric = nric;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
        this.allergies = allergies;
        this.remarks = remarks;
        logger.log(logLevel, "patient is added");
    }

    /**
     * Adds a new task to the list with the information provided by calling.
     * {} (or) {}
     * (or) {} as require
     *
     * @param patients Contains the list of tasks on which the commands are executed on.
     */
    @Override
    public String execute(
            PatientMap patients, AppointmentMap appointments, PatientRecordMap visits
    ) throws HappyPillsException {
        assert !patients.containsKey(nric) : "New nric can be added";
        Patient tempPatient = new Patient(name, nric, phoneNumber, dateOfBirth, bloodType, allergies, remarks);
        if (Validation.isValidNric(tempPatient.getNric())) {
            return Messages.MESSAGE_INVALID_NRIC;
        }
        patients.add(tempPatient);
        assert patients.containsKey(nric) : "nric added successfully";
        try {
            Storage.addSingleItemToFile(Storage.PATIENT_FILEPATH, tempPatient.toSave());
        } catch (IOException e) {
            logger.info(StorageTextUi.failToAddPatientMsg);
        }
        String message = "";
        message = PatientTextUi.addPatientSuccessMessage(patients.get(nric));
        logger.log(logLevel, "end of addCommand");
        return message;
    }
}
