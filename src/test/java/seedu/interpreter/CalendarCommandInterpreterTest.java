package seedu.interpreter;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.command.interpreter.CalendarCommandInterpreter;
import seedu.event.EventList;
import seedu.exception.PacException;

public class CalendarCommandInterpreterTest {
    CalendarCommandInterpreter cci;

    public CalendarCommandInterpreterTest() {
        cci = new CalendarCommandInterpreter(new EventList());
    }

    @Test
    void calendarView() {
        assertThrows(PacException.class, () -> cci.decideCommand("s/1 s/2"));
        assertThrows(PacException.class, () -> cci.decideCommand("s/1 s/2 ay/19-20"));
        assertThrows(PacException.class, () -> cci.decideCommand("s/1 ay/19-20 ay/20-21"));
        assertThrows(PacException.class, () -> cci.decideCommand("s/-1"));
        assertThrows(PacException.class, () -> cci.decideCommand("s/1 ay/199-300"));
        assertThrows(PacException.class, () -> cci.decideCommand("s/1 ay/3-4"));
        assertThrows(PacException.class, () -> cci.decideCommand("ay/20-30"));
        assertThrows(PacException.class, () -> cci.decideCommand("ay/50000"));
        assertThrows(PacException.class, () -> cci.decideCommand("s/1 ay/-3--4"));
    }

    @Test
    void missingArgument() {
        assertThrows(PacException.class, () -> cci.decideCommand("s/ ay/ "));
        assertThrows(PacException.class, () -> cci.decideCommand("       "));
    }

}
