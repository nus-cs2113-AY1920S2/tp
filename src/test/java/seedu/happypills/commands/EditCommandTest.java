package seedu.happypills.commands;

import org.junit.jupiter.api.Test;
import seedu.happypills.data.Patient;
import seedu.happypills.data.PatientList;
import seedu.happypills.exception.HappyPillsException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EditCommandTest {
    @Test
    void testExecute() {
        PatientList patients = new PatientList();
        AddCommand testAddCommand = new AddCommand(
                "kesin", "S0618", 912, "22aug", "B-","meat", "Strong"
        );
        testAddCommand.execute(patients);
        EditCommand testEditCommand = new EditCommand("S0618", "/rWeak");
        try {
            testEditCommand.execute(patients);
        } catch (HappyPillsException e) {
            // catch exception
        }
        assertEquals(1, patients.size());
        for (Patient patient : patients) {
            if (patient.getNric().equals("S0618")) {
                assertEquals("Weak", patient.getRemarks());
            }
        }
    }

}
