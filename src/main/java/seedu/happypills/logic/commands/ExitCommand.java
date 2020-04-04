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
     * Execute the exit command and write data to the file.
     *
     * @param patients       the list of patients
     * @param appointments  the list of appointments
     * @param patientRecords the list of patient records
     */
    @Override
    public String execute(PatientMap patients, AppointmentMap appointments, PatientRecordMap patientRecords) {
        try {
            Storage.writeAllToFile(Storage.PATIENT_FILEPATH, StorageTextUi.getFormattedPatientString(patients));
        } catch (IOException e) {
            logger.info("Adding patient list to file failed.");
        }
        TextUi.printExit();
        System.exit(0);
        assert false;
        return null;
    }
}
