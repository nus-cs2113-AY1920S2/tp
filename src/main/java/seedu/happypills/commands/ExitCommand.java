package seedu.happypills.commands;

import seedu.happypills.HappyPills;
import seedu.happypills.data.PatientMap;
import seedu.happypills.storage.Storage;
import seedu.happypills.ui.TextUi;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExitCommand extends Command {
    Logger logger = Logger.getLogger(HappyPills.class.getName());
    
    public ExitCommand() {
    }

    /**
     * Adds a new task to the list with the information provided by calling.
     * {} (or) {}
     * (or) {} as require
     *
     * @param patients Contains the list of tasks on which the commands are executed on.
     */
    @Override
    public String execute(PatientMap patients) {
        try {
            Storage.writeAllToFile(Storage.PATIENT_FILEPATH,TextUi.getFormattedPatientString(patients));
        } catch (IOException e) {
            logger.info("Adding patient list to file failed.");
        }
        TextUi.printExit();
        System.exit(0);
        assert false;
        return null;
    }
}
