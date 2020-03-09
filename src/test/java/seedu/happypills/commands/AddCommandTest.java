package seedu.happypills.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.happypills.data.PatientList;

import static org.junit.jupiter.api.Assertions.*;

class AddCommandTest {
    @Test
    void testExecute() {
        PatientList patients = new PatientList();
        AddCommand testAddCommand = new AddCommand(
                "kesin", "S0618", 912, "22aug", "B-","meat", "Strong"
        );
        testAddCommand.execute(patients);
        assertEquals(1,patients.size());
    }
}