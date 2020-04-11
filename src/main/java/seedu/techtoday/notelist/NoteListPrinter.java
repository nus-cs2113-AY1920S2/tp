package seedu.techtoday.notelist;

import seedu.techtoday.common.Messages;
import seedu.techtoday.objects.Note;
import java.util.ArrayList;

/** Represents command that is used to call a method to print noteList. */
public class NoteListPrinter {

    /**
     * Prints all available notes.
     * @param noteList List that stores the jobs mentioned until now.
     */
    public static void execute(ArrayList<Note> noteList) {
        if (noteList.size() == 0) {
            Messages.printInCenter("There is nothing in the list currently.");
        }
        int taskCounter = 1;
        for (Note job : noteList) {
            NotePrinter.execute(taskCounter, job);
            taskCounter += 1;
            System.out.println("\n");
        }
    }
}
