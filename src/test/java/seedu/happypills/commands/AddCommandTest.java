package seedu.happypills.commands;

import org.junit.jupiter.api.Test;
import seedu.happypills.data.PatientMap;
import seedu.happypills.exception.HappyPillsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddCommandTest {
    @Test
    void testExecute() {
        PatientMap patients = new PatientMap();
        AddCommand testAddCommand = new AddCommand(
                "kesin", "S0618", 912, "22aug", "B-","", ""
        );

        try {
            testAddCommand.execute(patients);
        } catch (HappyPillsException e) {
            e.printStackTrace();
        }
        assertEquals(1,patients.size());
        assertTrue(patients.containsKey("S0618"));
    }
}