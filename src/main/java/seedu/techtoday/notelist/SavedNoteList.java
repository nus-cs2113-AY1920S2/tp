package seedu.techtoday.notelist;

import seedu.techtoday.objects.Note;

import java.util.ArrayList;

/** Represents the data structure, i.e a list of Tasks, that stores the tasks.*/
public class SavedNoteList {
    public static ArrayList<Note> savedNoteList;

    /** Initializes a new taskList.  */
    public SavedNoteList() {
        savedNoteList = new ArrayList<Note>();
    }

}
