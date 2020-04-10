package seedu.happypills.logic.commands;

import seedu.happypills.HappyPills;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.storage.Storage;
import seedu.happypills.ui.StorageTextUi;
import seedu.happypills.ui.TextUi;

import java.io.IOException;
import java.util.logging.Logger;

public class ExitCommand implements Command {
    Logger logger = Logger.getLogger(HappyPills.class.getName());

    public ExitCommand() {
    }

    /**
     * Executes the exit command and write data to the file.
     *
     * @param patients       The list of patients.
     * @param appointments  The list of appointments.
     * @param patientRecords The list of patient records.
     */
    @Override
    public String execute(PatientMap patients, AppointmentMap appointments, PatientRecordMap patientRecords) {
        try {
            Storage.writeAllToFile(Storage.PATIENT_FILEPATH, StorageTextUi.getFormattedPatientString(patients));
        } catch (IOException e) {
            logger.info(StorageTextUi.FAIL_TO_WRITE_PATIENT_MSG);
        }
        try {
            Storage.writeAllToFile(Storage.APPOINTMENT_FILEPATH, StorageTextUi.getFormattedApptString(appointments));
        } catch (IOException e) {
            logger.info(StorageTextUi.FAIL_TO_WRITE_APPOINTMENT_MSG);
        }
        try {
            Storage.writeAllToFile(Storage.PATIENT_RECORD_FILEPATH,
                    StorageTextUi.getFormattedPrString(patientRecords,patients));
        } catch (IOException e) {
            logger.info(StorageTextUi.FAIL_TO_WRITE_PR_MSG);
        }
        TextUi.printExit();
        System.exit(0);
        assert false;
        return null;
    }
}
