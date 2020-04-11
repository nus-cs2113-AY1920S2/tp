package seedu.techtoday.joblist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.techtoday.objects.Job;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JobSaverTest {

    @BeforeEach
    void setUp() {
        SavedJobList.savedJobList = new ArrayList<>();
        ViewedJobList.viewedJobList = new ArrayList<>();
        JobAdder.execute(ViewedJobList.viewedJobList, new Job("Test1", "www.test1.com", "job"));
        JobAdder.execute(ViewedJobList.viewedJobList, new Job("Test2", "www.test2.com", "job"));
        JobAdder.execute(ViewedJobList.viewedJobList, new Job("Test3", "www.test3.com", "job"));
    }

    @Test
    void execute() {
        // All system.out.println statements will be in outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // execute command
        JobSaver.execute(SavedJobList.savedJobList, "save job 2");

        String expectedOutput = "Done, saved the job with the following details:"
                + System.lineSeparator()
                + "   Title: Test2"
                + System.lineSeparator()
                + "   Date: "
                + System.lineSeparator()
                + "   Category: job"
                + System.lineSeparator()
                + "   Text: www.test2.com"
                + System.lineSeparator()
                + "   Extract: "
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void constructorTest() {
        JobSaver saver = new JobSaver();
        assertEquals(saver.savedJobList, new ArrayList<Job>());
    }
}