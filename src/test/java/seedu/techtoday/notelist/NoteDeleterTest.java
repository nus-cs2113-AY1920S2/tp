package seedu.techtoday.notelist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.techtoday.objects.Note;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoteDeleterTest {

    @BeforeEach
    void setUp() {
        SavedNoteList.savedNoteList = new ArrayList<>();
        NoteAdder.execute(SavedNoteList.savedNoteList, new Note("Test1"));
        NoteAdder.execute(SavedNoteList.savedNoteList, new Note("Test2"));
        NoteAdder.execute(SavedNoteList.savedNoteList, new Note("Test3"));
    }


    @Test
    void execute() {
        // All system.out.println statements will be in outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // execute command
        NoteDeleter.execute(SavedNoteList.savedNoteList, "delete note 2");

        String expectedOutput = "Deleting the following note:"
                + System.lineSeparator()
                + "   Title: Test2"
                + System.lineSeparator()
                + "   Date: "
                + System.lineSeparator()
                + "   Category: null"
                + System.lineSeparator()
                + "   URL: null"
                + System.lineSeparator()
                + "   Extract: "
                + System.lineSeparator();

        assertEquals(expectedOutput, outContent.toString());
    }
}