package seedu.techtoday.notelist;

import seedu.techtoday.objects.Note;
import java.util.ArrayList;

/** Represents command that is used to call a method to delete note from taskList. */
public class NoteDeleter {
    /**
     * Deletes the task at index taskNumber from the tasklist.
     *
     * @param savedNoteList - List of all notes saved as of now.
     * @param command - Command inputted by the user.
     */
    public static void execute(ArrayList<Note> savedNoteList, String command) {
        String[] commandArray = command.split(" ");
        int noteNumber = Integer.parseInt(commandArray[2]) - 1;
        Note noteToDelete = SavedNoteList.savedNoteList.get(noteNumber);
        System.out.println("Deleting the following note:");
        NotePrinter.printIsolatedNote(noteToDelete);
        SavedNoteList.savedNoteList.remove(noteToDelete);
    }
}
