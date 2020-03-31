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
import seedu.command.event.ListSeminar;
import seedu.command.interpreter.SeminarCommandInterpreter;
import seedu.event.EventList;
import seedu.exception.PACException;

public class SeminarCommandInterpreterTest {
    SeminarCommandInterpreter sci;
    Command command;

    public SeminarCommandInterpreterTest() {
        sci = new SeminarCommandInterpreter(new EventList());
    }

    @Test
    void add() throws PACException {
        assertTrue(sci.decideCommand("add n/hello") instanceof AddEvent);
    }

    @Test
    void editName() throws PACException {
        assertTrue(sci.decideCommand("editname i/1 n/new name") instanceof EditName);
        assertThrows(PACException.class, () -> sci.decideCommand("editname i/-5"));
        assertThrows(PACException.class, () -> sci.decideCommand("editname i/0"));
        assertThrows(PACException.class, () -> sci.decideCommand("editname i/1 i/2"));
        assertThrows(PACException.class, () -> sci.decideCommand("editname i/10000 2"));  
    }

    @Test
    void editDateTime() throws PACException {
        assertTrue(sci.decideCommand("editdatetime i/1 d/2020-11-11 t/1234") instanceof EditDateTime);
        assertThrows(PACException.class, () -> sci.decideCommand("editdatetime d/date t/time"));
        assertThrows(PACException.class, () -> sci.decideCommand("editdatetime i/1 d/date"));
        assertThrows(PACException.class, () -> sci.decideCommand("editdatetime i/1 t/time"));
        assertThrows(PACException.class, () -> sci.decideCommand("editdatetime d/date"));
        assertThrows(PACException.class, () -> sci.decideCommand("editdatetime t/time"));
        assertThrows(PACException.class, () -> sci.decideCommand("editdatetime i/-5"));
        assertThrows(PACException.class, () -> sci.decideCommand("editdatetime i/0"));
        assertThrows(PACException.class, () -> sci.decideCommand("editdatetime i/1 i/2"));
        assertThrows(PACException.class, () -> sci.decideCommand("editdatetime i/10000 2")); 
    }

    @Test
    void editVenue() throws PACException {
        assertTrue(sci.decideCommand("editvenue i/1 v/new venue") instanceof EditVenue);
        assertThrows(PACException.class, () -> sci.decideCommand("editvenue v/new venue"));
        assertThrows(PACException.class, () -> sci.decideCommand("editvenue i/-5"));
        assertThrows(PACException.class, () -> sci.decideCommand("editvenue i/0"));
        assertThrows(PACException.class, () -> sci.decideCommand("editvenue i/1 i/2"));
        assertThrows(PACException.class, () -> sci.decideCommand("editvenue i/10000 2")); 
    }

    @Test
    void editEvent() throws PACException {
        assertTrue(sci.decideCommand("editevent i/1 n/new name v/new venue") instanceof EditEvent);
    }

    @Test
    void delete() throws PACException {
        assertTrue(sci.decideCommand("delete i/1") instanceof DeleteEvent);
    }

    @Test
    void list() throws PACException {
        assertTrue(sci.decideCommand("list") instanceof ListSeminar);
    }

    @Test
    void unknown() throws PACException {
        assertThrows(PACException.class, () -> sci.decideCommand(""));
        assertThrows(PACException.class, () -> sci.decideCommand("..."));
    }

    @Test
    void missingArgument() throws PACException {
        assertThrows(PACException.class, () -> sci.decideCommand("add"));
        assertThrows(PACException.class, () -> sci.decideCommand("editname"));
        assertThrows(PACException.class, () -> sci.decideCommand("editdatetime"));
        assertThrows(PACException.class, () -> sci.decideCommand("editvenue"));
        assertThrows(PACException.class, () -> sci.decideCommand("editevent"));
        assertThrows(PACException.class, () -> sci.decideCommand("delete"));
    }
}