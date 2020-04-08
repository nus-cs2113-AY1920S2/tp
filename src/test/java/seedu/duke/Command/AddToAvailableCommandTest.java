package seedu.duke.Command;

import org.junit.jupiter.api.Test;
import seedu.duke.module.NewModule;
import seedu.duke.command.AddToAvailableCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddToAvailableCommandTest {

    @Test
    void AddToAvailableCommand() {
        NewModule newModule = new NewModule("CS1231", "Discrete Structures", 4);
        AddToAvailableCommand addToAvailableCommand = new AddToAvailableCommand(newModule);
    }


}
