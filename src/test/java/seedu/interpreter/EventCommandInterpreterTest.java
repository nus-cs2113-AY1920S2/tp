package seedu.interpreter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.command.Command;
import seedu.command.event.AddEvent;
import seedu.command.event.DeleteEvent;
import seedu.command.event.EditDateTime;
import seedu.command.event.EditEvent;
import seedu.command.event.EditName;
import seedu.command.event.EditVenue;
import seedu.command.event.ListEvent;
import seedu.command.interpreter.EventCommandInterpreter;
import seedu.event.EventList;
import seedu.exception.PacException;

public class EventCommandInterpreterTest {
    EventCommandInterpreter eci;
    Command command;

    public EventCommandInterpreterTest() {
        eci = new EventCommandInterpreter(new EventList());
    }

    @Test
    void add() throws PacException {
        assertTrue(eci.decideCommand("add n/hello") instanceof AddEvent);
        assertThrows(PacException.class, () -> eci.decideCommand("add n/1 n/2"));
    }

    @Test
    void editName() throws PacException {
        assertTrue(eci.decideCommand("editname i/1 n/new name") instanceof EditName);
        assertThrows(PacException.class, () -> eci.decideCommand("editname n/new name"));
        assertThrows(PacException.class, () -> eci.decideCommand("editname i/-5"));
        assertThrows(PacException.class, () -> eci.decideCommand("editname i/0"));
        assertThrows(PacException.class, () -> eci.decideCommand("editname i/1 i/2"));
        assertThrows(PacException.class, () -> eci.decideCommand("editname i/10000 2"));        
    }

    @Test
    void editDateTime() throws PacException {
        assertTrue(eci.decideCommand("editdatetime i/1 d/2020-11-11 t/1234") instanceof EditDateTime);
        assertThrows(PacException.class, () -> eci.decideCommand("editdatetime d/date t/time"));
        assertThrows(PacException.class, () -> eci.decideCommand("editdatetime i/1 d/date"));
        assertThrows(PacException.class, () -> eci.decideCommand("editdatetime i/1 t/time"));
        assertThrows(PacException.class, () -> eci.decideCommand("editdatetime d/date"));
        assertThrows(PacException.class, () -> eci.decideCommand("editdatetime t/time"));
        assertThrows(PacException.class, () -> eci.decideCommand("editdatetime i/-5"));
        assertThrows(PacException.class, () -> eci.decideCommand("editdatetime i/0"));
        assertThrows(PacException.class, () -> eci.decideCommand("editdatetime i/1 i/2"));
        assertThrows(PacException.class, () -> eci.decideCommand("editdatetime i/10000 2"));        
    }

    @Test
    void editVenue() throws PacException {
        assertTrue(eci.decideCommand("editvenue i/1 v/new venue") instanceof EditVenue);
        assertThrows(PacException.class, () -> eci.decideCommand("editvenue v/new venue"));
        assertThrows(PacException.class, () -> eci.decideCommand("editvenue i/-5"));
        assertThrows(PacException.class, () -> eci.decideCommand("editvenue i/0"));
        assertThrows(PacException.class, () -> eci.decideCommand("editvenue i/1 i/2"));
        assertThrows(PacException.class, () -> eci.decideCommand("editvenue i/10000 2"));        
    }

    @Test
    void editEvent() throws PacException {
        assertTrue(eci.decideCommand("editevent i/1 n/new name v/new venue") instanceof EditEvent);
    }

    @Test
    void delete() throws PacException {
        assertTrue(eci.decideCommand("delete i/1") instanceof DeleteEvent);
        assertThrows(PacException.class, () -> eci.decideCommand("delete i/-5"));
        assertThrows(PacException.class, () -> eci.decideCommand("delete i/0"));
        assertThrows(PacException.class, () -> eci.decideCommand("delete i/1 i/2"));
        assertThrows(PacException.class, () -> eci.decideCommand("delete i/10000 2"));
    }

    @Test
    void list() throws PacException {
        assertTrue(eci.decideCommand("list") instanceof ListEvent);
    }

    @Test
    void unknown() {
        assertThrows(PacException.class, () -> eci.decideCommand(""));
        assertThrows(PacException.class, () -> eci.decideCommand("..."));
    }

    @Test
    void missingArgument() {
        assertThrows(PacException.class, () -> eci.decideCommand("add"));
        assertThrows(PacException.class, () -> eci.decideCommand("editname"));
        assertThrows(PacException.class, () -> eci.decideCommand("editdatetime"));
        assertThrows(PacException.class, () -> eci.decideCommand("editvenue"));
        assertThrows(PacException.class, () -> eci.decideCommand("editevent"));
        assertThrows(PacException.class, () -> eci.decideCommand("delete"));
    }
}