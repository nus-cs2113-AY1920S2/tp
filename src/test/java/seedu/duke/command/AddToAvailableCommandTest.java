package seedu.duke.command;

import org.junit.jupiter.api.Test;
import seedu.duke.module.NewModule;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddToAvailableCommandTest {

    @Test
    void addToAvailableCommand() {
        NewModule newModule = new NewModule("CS1231", "Discrete Structures", 4);
        AddToAvailableCommand addToAvailableCommand = new AddToAvailableCommand(newModule);
    }


}
