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
import seedu.exception.DukeException;

public class EventCommandInterpreterTest {
    EventCommandInterpreter eci;
    Command command;

    public EventCommandInterpreterTest() {
        eci = new EventCommandInterpreter(new EventList());
    }

    @Test
    void add() throws DukeException {
        assertTrue(eci.decideCommand("add n/hello") instanceof AddEvent);
    }

    @Test
    void editName() throws DukeException {
        assertTrue(eci.decideCommand("editname i/1 n/new name") instanceof EditName);
    }

    @Test
    void editDateTime() throws DukeException {
        assertTrue(eci.decideCommand("editdatetime i/1 d/2020-11-11 t/1234") instanceof EditDateTime);
    }

    @Test
    void editVenue() throws DukeException {
        assertTrue(eci.decideCommand("editvenue i/1 v/new venue") instanceof EditVenue);
    }

    @Test
    void editEvent() throws DukeException {
        assertTrue(eci.decideCommand("editevent i/1 n/new name v/new venue") instanceof EditEvent);
    }

    @Test
    void delete() throws DukeException {
        assertTrue(eci.decideCommand("delete i/1") instanceof DeleteEvent);
    }

    @Test
    void list() throws DukeException {
        assertTrue(eci.decideCommand("list") instanceof ListEvent);
    }

    @Test
    void unknown() throws DukeException {
        assertThrows(DukeException.class, () -> eci.decideCommand(""));
        assertThrows(DukeException.class, () -> eci.decideCommand("..."));
    }
}