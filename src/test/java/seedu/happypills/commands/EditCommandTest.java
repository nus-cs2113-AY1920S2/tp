package seedu.happypills.commands;

import org.junit.jupiter.api.Test;
import seedu.happypills.data.Patient;
import seedu.happypills.data.PatientList;
import seedu.happypills.data.PatientMap;
import seedu.happypills.exception.HappyPillsException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EditCommandTest {
    @Test
    void testExecute() {
        PatientMap patients = new PatientMap();
        AddCommand testAddCommand = new AddCommand(
                "kesin", "S0618", 912, "22aug", "B-","meat", "Strong"
        );
        try {
            testAddCommand.execute(patients);
        } catch (HappyPillsException e) {
            // catch exception
            e.printStackTrace();
        }

        EditCommand testEditCommand = new EditCommand("S0618", "/rWeak");
        try {
            testEditCommand.execute(patients);
        } catch (HappyPillsException e) {
            // catch exception
        }
        assertEquals(1, patients.size());
        if (patients.containsKey("S0618")) {
            Patient patient = patients.get("S0618");
            assertEquals("Weak", patient.getRemarks());
        }
    }

}
