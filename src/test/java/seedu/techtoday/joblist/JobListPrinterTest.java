package seedu.techtoday.joblist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.techtoday.objects.Job;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JobListPrinterTest {

    @BeforeEach
    void setUp() {
        SavedJobList.savedJobList = new ArrayList<>();
        JobAdder.execute(SavedJobList.savedJobList, new Job("Test1", "www.test1.com", "job"));
        JobAdder.execute(SavedJobList.savedJobList, new Job("Test2", "www.test2.com", "job"));
        JobAdder.execute(SavedJobList.savedJobList, new Job("Test3", "www.test3.com", "job"));
    }

    @Test
    void execute() {
        // All system.out.println statements will be in outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // execute command
        JobListPrinter.execute(SavedJobList.savedJobList);

        String expectedOutput = "1. Title: Test1"
                + System.lineSeparator()
                + "   Date: "
                + System.lineSeparator()
                + "   Category: job"
                + System.lineSeparator()
                + "   Text: www.test1.com..."
                + System.lineSeparator()
                + "   Extract: ..."
                + System.lineSeparator()
                + ""
                + System.lineSeparator()
                + ""
                + System.lineSeparator()
                + "2. Title: Test2"
                + System.lineSeparator()
                + "   Date: "
                + System.lineSeparator()
                + "   Category: job"
                + System.lineSeparator()
                + "   Text: www.test2.com..."
                + System.lineSeparator()
                + "   Extract: ..."
                + System.lineSeparator()
                + ""
                + System.lineSeparator()
                + ""
                + System.lineSeparator()
                + "3. Title: Test3"
                + System.lineSeparator()
                + "   Date: "
                + System.lineSeparator()
                + "   Category: job"
                + System.lineSeparator()
                + "   Text: www.test3.com..."
                + System.lineSeparator()
                + "   Extract: ..."
                + System.lineSeparator()
                + ""
                + System.lineSeparator()
                + ""
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
}