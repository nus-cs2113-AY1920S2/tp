package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.record.Patient;
import seedu.duke.storage.PatientList;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EditPatientCommandTest {

    //Test case 1
    @Test
    void execute_1() throws Exception {
        PatientList patientList = new PatientList();
        PatientList.getPatientList().add(new Patient("peony", 33, "clementi road", "61234567", 1312));
        PatientList.getPatientList().add(new Patient("yoshino", 8, "shibuya", "13182371", 1112));
        PatientList.getPatientList().add(new Patient("kurumi", 15, "hokkaido", "31732913", 190));
        PatientList.getPatientList().add(new Patient("ren", 17, "tokyo", "31093991", 998));
        PatientList.getPatientList().add(new Patient("ren", 17, "tokyo", "31093991", 1212));
        Map<String, String> fieldsToChange = new HashMap<>();
        fieldsToChange.put("index", "3");
        fieldsToChange.put("name", "Joe Harris");
        fieldsToChange.put("age", "20");
        fieldsToChange.put("address", "Jurong East");
        fieldsToChange.put("phone", "82253819");
        EditPatientCommand command = new EditPatientCommand(fieldsToChange);
        assertEquals(3, command.getPatientIndex());
        assertEquals("Joe Harris", command.getPatientName());
        assertEquals(20, command.getAge());
        assertEquals("Jurong East", command.getAddress());
        assertEquals("82253819", command.getContactNumber());
    }

    //Test case 2
    @Test
    void execute_2() throws Exception {
        Map<String, String> fieldsToChange = new HashMap<>();
        fieldsToChange.put("index", "3");
        fieldsToChange.put("name", "");
        fieldsToChange.put("age", "");
        fieldsToChange.put("address", "");
        fieldsToChange.put("phone", "82253819");
        EditPatientCommand command = new EditPatientCommand(fieldsToChange);
        assertEquals(3, command.getPatientIndex());
        assertEquals("", command.getPatientName());
        assertEquals(-1, command.getAge());
        assertEquals("", command.getAddress());
        assertEquals("82253819", command.getContactNumber());
    }
}