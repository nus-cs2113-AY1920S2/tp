package seedu.atas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import common.Messages;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;


public class UiTest {
    private PrintStream consoleOut;
    private InputStream consoleIn;
    private final String lineSeparator = System.lineSeparator();

    public UiTest() {
        consoleOut = System.out;
        consoleIn = System.in;
    }

    @Test
    public void testDivider() {
        ByteArrayOutputStream testOutput;
        try {
            testOutput = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOutput));
            Ui testUi = new Ui();
            testUi.printDividerLine();
        } finally {
            System.setOut(consoleOut);
        }
        assertEquals(Messages.DIVIDER + lineSeparator, testOutput.toString());
    }

    @Test
    public void testWelcomeMessage() {
        ByteArrayOutputStream testOutput;
        try {
            testOutput = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOutput));
            Ui testUi = new Ui();
            testUi.printWelcomeMessage();
        } finally {
            System.setOut(consoleOut);
        }
        assertEquals(Messages.LOGO + lineSeparator
                + Messages.DIVIDER + lineSeparator, testOutput.toString());
    }

    @Test
    public void testGetUserInput() {
        ByteArrayOutputStream testOutput;
        ByteArrayInputStream testInput;

        String resultUserIn;
        String exampleUserIn = "testing 123          ";
        String expectedUserIn = exampleUserIn.trim();
        try {
            testInput = new ByteArrayInputStream(exampleUserIn.getBytes());
            System.setIn(testInput);

            testOutput = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOutput));

            Ui testUi = new Ui();
            resultUserIn = testUi.getUserInput();
        } finally {
            System.setOut(consoleOut);
            System.setIn(consoleIn);
        }
        assertEquals(expectedUserIn, resultUserIn);
        assertEquals(Messages.PROMPT_FOR_USER_INPUT, testOutput.toString());
    }

    @Test
    public void testShowToUser() {
        ByteArrayOutputStream testOutput;

        String exampleUserIn = "testing line 1";
        String exampleUserIn2 = "testing line 2";
        String exampleUserIn3 = "testing line 3";
        String expectedUserIn = exampleUserIn + lineSeparator
                + exampleUserIn2 + lineSeparator
                + exampleUserIn3 + lineSeparator;

        try {
            testOutput = new ByteArrayOutputStream();
            System.setOut(new PrintStream(testOutput));

            Ui testUi = new Ui();
            testUi.showToUser(exampleUserIn, exampleUserIn2, exampleUserIn3);
        } finally {
            System.setOut(consoleOut);
            System.setIn(consoleIn);
        }
        assertEquals(expectedUserIn, testOutput.toString());
    }
}
