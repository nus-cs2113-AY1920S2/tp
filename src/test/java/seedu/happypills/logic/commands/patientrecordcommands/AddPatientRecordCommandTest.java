package seedu.happypills.logic.commands.patientrecordcommands;

import org.junit.jupiter.api.Test;
import seedu.happypills.logic.commands.patientcommands.AddPatientCommand;
import seedu.happypills.model.data.AppointmentMap;
import seedu.happypills.model.data.PatientMap;
import seedu.happypills.model.data.PatientRecordMap;
import seedu.happypills.model.exception.HappyPillsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddPatientRecordCommandTest {

    private static AppointmentMap newAppointmentMap;
    PatientRecordMap newPatientRecordMap = new PatientRecordMap();

    @Test
    void testExecute() {
        PatientMap patients = new PatientMap();
        AddPatientCommand testAddCommand = new AddPatientCommand(
                "Eve", "S9876543F", 91234567, "22/09/1998",
                "B-","-", "-"
        );

        try {
            testAddCommand.execute(patients, newAppointmentMap, newPatientRecordMap);
        } catch (HappyPillsException e) {
            e.printStackTrace();
        }
        assertEquals(1,patients.size());
        assertTrue(patients.containsKey("S9876543F"));
    }



}