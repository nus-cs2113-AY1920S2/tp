package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.record.Patient;
import seedu.duke.storage.PatientList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindPatientCommandTest {
    private Ui ui;
    private Storage storage;

    @Test
    void findPatientCommand_WhitespaceException() throws Exception {
        PatientList patientList = new PatientList();
        boolean checkIsExceptionThrown = false;
        patientList.getPatientList().add(new Patient("peony", 33, "clementi road", "61234567",123));
        patientList.getPatientList().add(new Patient("yoshino", 8, "shibuya", "13182371",456));
        patientList.getPatientList().add(new Patient("kurumi", 15, "hokkaido", "31732913",789));
        patientList.getPatientList().add(new Patient("ren", 17, "tokyo", "31093991",10));
        patientList.getPatientList().add(new Patient("ren", 17, "tokyo", "31093991",12));
        FindAppointmentCommand testCommand = new FindAppointmentCommand("\\name           ");
        try {
            testCommand.execute(ui, storage);
        } catch (Exception e) {
            checkIsExceptionThrown = true;
        }
        assertTrue(checkIsExceptionThrown);
    }
}
