package seedu.techtoday.notelist;

import org.junit.jupiter.api.Test;
import seedu.techtoday.objects.Note;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoteAdderTest {

    @Test
    void execute() {
        ArrayList<Note> noteList = new ArrayList<Note>();
        NoteAdder.execute(noteList, new Note("Test"));
        assertEquals(1, noteList.size());
    }

    @Test
    public void constructorTest() {
        NoteAdder note = new NoteAdder();
        assertEquals(note.savedNoteList, new ArrayList<Note>());
    }
}