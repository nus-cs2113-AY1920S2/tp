package seedu.techtoday.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HelperTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void execute() {
        // All system.out.println statements will be in outContent
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // execute command
        Helper.execute();
        String expectedOutput = "_                    Your queries can be of the following forms:                   _"
                + System.lineSeparator()
                + "_                                      1. help                                     _"
                + System.lineSeparator()
                + "_                              2. view [article / job]                             _"
                + System.lineSeparator()
                + "_                       3. save [article / job] INDEX_NUMBER                       _"
                + System.lineSeparator()
                + "_                         4. create [article / job / note]                         _"
                + System.lineSeparator()
                + "_                          5. list [article / job / note]                          _"
                + System.lineSeparator()
                + "_                   6. delete [article / job / note] INDEX_NUMBER                  _"
                + System.lineSeparator()
                + "_              7. addinfo [article / job / note] INDEX_NUMBER EXTRACT              _"
                + System.lineSeparator()
                + "_                                      8. exit                                     _"
                + System.lineSeparator()
                + "_                                                                                  _"
                + System.lineSeparator()
                + "__________________________________________________________________________________________"
                + System.lineSeparator()
                + ""
                + System.lineSeparator()
                + ""
                + System.lineSeparator()
                + ""
                + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }
}