package seedu.techtoday.creator;

import seedu.techtoday.notelist.NotePrinter;
import seedu.techtoday.objects.Note;

import seedu.techtoday.notelist.SavedNoteList;

import seedu.techtoday.ui.Ui;

/** Class representing a method used to create a note based on user input. */
public class ManualNoteCreator {

    /** Executes the creation of a note based on user input. */
    public static void execute() {
        System.out.println("Enter the title you would like to give this Note?");
        String title = Ui.getCommand();
        System.out.println("What would you like to add to the extract of this note?");
        String extract = Ui.getCommand();
        System.out.println("Paste any URLs associated with this note:");
        String url = Ui.getCommand();
        System.out.println("What is the category (type \"default\" if you don't know it)");
        String category = Ui.getCommand();
        String epochSecond = CurrentTimeFetcher.execute();
        Note note = new Note(title, extract, epochSecond);
        note.setUrl(url);
        note.setCategory(category);
        SavedNoteList.savedNoteList.add(note);
        System.out.println("Done, we have added the following note to your list of saved notes");
        NotePrinter.printIsolatedNote(note);
    }
}
