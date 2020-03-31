package seedu.interpreter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.command.Bye;
import seedu.command.Help;
import seedu.command.interpreter.CommandInterpreter;
import seedu.event.EventList;
import seedu.exception.PACException;

public class CommandInterpreterTest {
    CommandInterpreter ci;

    public CommandInterpreterTest() {
        this.ci = new CommandInterpreter(new EventList());
    }

    @Test
    void byeCommand() throws PACException {
        assertTrue(ci.decideCommand("bye") instanceof Bye);
        assertTrue(ci.decideCommand("  bye  ") instanceof Bye);
        assertTrue(ci.decideCommand("bYe") instanceof Bye);
    }

    @Test
    void helpCommand() throws PACException {
        assertTrue(ci.decideCommand("help") instanceof Help);
        assertTrue(ci.decideCommand("  help  ") instanceof Help);
        assertTrue(ci.decideCommand("heLp") instanceof Help);
    }
    
    void unknownCommand() {
        assertThrows(PACException.class, () -> ci.decideCommand(""));
        assertThrows(PACException.class, () -> ci.decideCommand("hi"));
    }
}