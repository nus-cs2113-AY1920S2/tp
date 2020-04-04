package seedu.techtoday.notelist;

import seedu.techtoday.common.CommonMethods;
import seedu.techtoday.common.TimeStampToDateConverter;
import seedu.techtoday.objects.Note;

/** Represents command that is used to call a method to print note from noteList. */
public class NotePrinter {

    /**
     * Prints a particular note.
     * @param taskCounter - Index of the note in noteList.
     * @param note - Objecting representing note.
     */
    public static void execute(int taskCounter, Note note) {
        String timeStamp = note.getTimeStamp();
        String date = TimeStampToDateConverter.execute(timeStamp);
        String title = note.getTitle();
        String extract = note.getExtract();
        String upToNCharacters = CommonMethods.returnUptoNcharacters(extract);
        String category = note.getCategory();
        String url = note.getUrl();
        System.out.println(taskCounter + ". Title: " + title + System.lineSeparator()
                                + "   Date: " + date + System.lineSeparator()
                                + "   Category: " + category + System.lineSeparator()
                                + "   URL: " + url + System.lineSeparator()
                                + "   Extract: " + upToNCharacters);
    }

    /**
     * Prints a note outside the scope of a noteList.
     * @param note - Object representing note.
     */
    public static void printIsolatedNote(Note note) {
        String timeStamp = note.getTimeStamp();
        String date = TimeStampToDateConverter.execute(timeStamp);
        String title = note.getTitle();
        String extract = note.getExtract();
        String upToNCharacters = CommonMethods.returnUptoNcharacters(extract);
        String category = note.getCategory();
        String url = note.getUrl();
        System.out.println("   Title: " + title + System.lineSeparator()
                + "   Date: " + date + System.lineSeparator()
                + "   Category: " + category + System.lineSeparator()
                + "   URL: " + url + System.lineSeparator()
                + "   Extract: " + upToNCharacters);
    }
}

