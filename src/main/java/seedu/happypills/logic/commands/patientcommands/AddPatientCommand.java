package seedu.happypills.logic.commands.patientcommands;

import seedu.happypills.HappyPills;
import seedu.happypills.logic.parser.Checker;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.Patient;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;
import seedu.happypills.storage.Storage;
import seedu.happypills.ui.Messages;
import seedu.happypills.ui.PatientTextUi;
import seedu.happypills.ui.StorageTextUi;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author NyanWunPaing
/*
 * Adds patient into Patient Map.
 */
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
     * @param name        Name of the patient.
     * @param nric        Nric of the patient.
     * @param phoneNumber Phone number of the patient.
     * @param dateOfBirth Date of birth of the patient.
     * @param bloodType   Blood type of the patient.
     * @param allergies   Allergies of the patient.
     * @param remarks     Remarks for the patient.
     */
    public AddPatientCommand(String name, String nric, int phoneNumber, String dateOfBirth,
                             String bloodType, String allergies, String remarks) {
        this.name = name;
        this.nric = nric.toUpperCase();
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.bloodType = bloodType;
        this.allergies = allergies;
        this.remarks = remarks;
        logger.log(logLevel, "patient is added");
    }

    /**
     * Executes the add patient command.
     *
     * @param patients       The list of patients.
     * @param appointments   The list of appointments.
     * @param patientRecords The list of patient records.
     * @return Error Message or Success Message.
     * @throws HappyPillsException If NRIC already exist in the patient list.
     */
    @Override
    public String execute(
            PatientMap patients, AppointmentMap appointments, PatientRecordMap patientRecords
    ) throws HappyPillsException {
        assert !patients.containsKey(nric) : "New NRIC can be added";
        Patient tempPatient = new Patient(name, nric, phoneNumber, dateOfBirth, bloodType, allergies, remarks);
        logger.log(logLevel, "patient is added");
        if (!Checker.isValidNric(tempPatient.getNric())) {
            return Messages.MESSAGE_INVALID_NRIC;
        }
        patients.add(tempPatient);
        assert patients.containsKey(nric) : "NRIC added successfully";
        try {
            Storage.addSingleItemToFile(Storage.PATIENT_FILEPATH, tempPatient.toSave());
        } catch (IOException e) {
            logger.info(StorageTextUi.FAIL_TO_ADD_PATIENT_MSG);
        }
        String message = PatientTextUi.addPatientSuccessMessage(patients.get(nric));
        logger.log(logLevel, "end of add command");
        return message;
    }
}
