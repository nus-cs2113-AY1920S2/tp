package seedu.techtoday.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.techtoday.articlelist.SavedArticleList;
import seedu.techtoday.joblist.SavedJobList;
import seedu.techtoday.notelist.SavedNoteList;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTerminatorTest {

    @BeforeEach
    void setUp() {
        SavedArticleList.savedArticleList = new ArrayList<>();
        SavedJobList.savedJobList = new ArrayList<>();
        SavedNoteList.savedNoteList = new ArrayList<>();
    }

    @Test
    void execute() {
        // All system.out.println statements will be in outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // execute command
        ApplicationTerminator.execute();

        String expectedOutput = "_                                Saving your data...                               _"
                + System.lineSeparator()
                + "_                           We are exiting the program...                          _"
                + System.lineSeparator()
                + "_                            Bye. Come back again later.                           _"
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
}