package seedu.techtoday.creator;

import seedu.techtoday.notelist.NotePrinter;
import seedu.techtoday.objects.Note;

import seedu.techtoday.notelist.SavedNoteList;

import seedu.techtoday.ui.Ui;

/** Class representing a method used to create a note based on user input. */
public class ManualNoteCreator {

    /** Executes the creation of a note based on user input. */
    public static void execute() {
        String title = getNoteTitle();
        String url = getNoteUrl();
        String category = getNoteCategory();
        String extract = getNoteExtract();
        String epochSecond = CurrentTimeFetcher.execute();
        Note note = new Note(title, extract, epochSecond);
        note.setUrl(url);
        note.setCategory(category);
        SavedNoteList.savedNoteList.add(note);
        System.out.println("Done, we have added the following note to your list of saved notes");
        NotePrinter.printIsolatedNote(note);
    }

    /**
     * Function that asks and gets title of note.
     * @return String representation of note title from user input.
     */
    public static String getNoteTitle() {
        System.out.println("Enter the title you would like to give this Note?");
        return Ui.getCommand();
    }

    /**
     * Function that asks and gets url of note.
     * @return String representation of url from user input.
     */
    public static String getNoteUrl() {
        System.out.println("Paste any URLs associated with this note:");
        return Ui.getCommand();
    }

    /**
     * Function that asks and gets category of note.
     * @return String representation of category from user input.
     */
    public static String getNoteCategory() {
        System.out.println("What is the category of the note? (type \"default\" if you don't know it)");
        return Ui.getCommand();
    }

    /**
     * Function that asks and gets note extract.
     * @return String representation of extract from user input.
     */
    public static String getNoteExtract() {
        System.out.println("What would you like to add to the extract of this note?");
        return Ui.getCommand();
    }
}
