package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.record.Patient;
import seedu.duke.storage.PatientList;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeletePatientCommandTest {

    //Test case 1
    @Test
    void execute_1() throws Exception {
        PatientList patientList = new PatientList();
        PatientList.getPatientList().add(new Patient("peony", 33, "clementi road", "61234567", 123));
        PatientList.getPatientList().add(new Patient("yoshino", 8, "shibuya", "13182371", 456));
        PatientList.getPatientList().add(new Patient("kurumi", 15, "hokkaido", "31732913", 789));
        PatientList.getPatientList().add(new Patient("ren", 17, "tokyo", "31093991", 10));
        PatientList.getPatientList().add(new Patient("ren", 17, "tokyo", "31093991", 12));
        Map<String, String> fieldsToChange = new HashMap<>();
        fieldsToChange.put("index", "3");
        DeletePatientCommand command = new DeletePatientCommand(fieldsToChange);
        assertEquals(3, command.getPatientIndex());
    }
}