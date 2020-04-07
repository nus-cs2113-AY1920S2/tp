package command;

import common.Messages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.atas.TaskList;
import tasks.Assignment;
import tasks.Event;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import seedu.atas.Parser;
import seedu.atas.Ui;
import tasks.RepeatEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author jichngan
public class EditCommandTest {
    private static TaskList filledTaskList;
    private static String dateStringOne = "12/03/20 1600";
    private static String dateStringTwo = "12/03/20 1800";

    private static String dateStringThree = "13/03/20 1600";
    private static String dateStringFour = "13/03/20 1800";

    private static LocalDateTime dateOne = LocalDateTime.parse(dateStringOne, Parser.INPUT_DATE_TIME_FORMAT);
    private static LocalDateTime dateTwo = LocalDateTime.parse(dateStringTwo, Parser.INPUT_DATE_TIME_FORMAT);
    private static LocalDateTime dateThree = LocalDateTime.parse(dateStringThree, Parser.INPUT_DATE_TIME_FORMAT);
    private static LocalDateTime dateFour = LocalDateTime.parse(dateStringFour, Parser.INPUT_DATE_TIME_FORMAT);

    private static Ui ui;

    /**
     * Initialise TaskList for testing.
     */
    @BeforeEach
    public void setup() {
        filledTaskList = new TaskList();
        Assignment firstAssignment = new Assignment("One", "cs2113", dateOne, "None");
        Assignment secondAssignment = new Assignment("two", "cs2113", dateOne, "None");
        Event firstEvent = new Event("meeting one", "cs2113", dateOne, dateTwo,"None");
        Event secondEvent = new Event("meeting two", "cs2113", dateThree, dateFour,"None");

        filledTaskList.addTask(firstAssignment);
        filledTaskList.addTask(secondAssignment);
        filledTaskList.addTask(firstEvent);
        filledTaskList.addTask(secondEvent);
    }

    @Test
    public void editAssignment_filledList_success() {
        Assignment editedAssignment = new Assignment("three", "cs2113", dateOne, "None");
        filledTaskList.editTask(0, editedAssignment);
        assertEquals(filledTaskList.getTask(0), editedAssignment);
    }

    @Test
    public void editEvent_filledList_success() {
        Event editedEvent = new Event("meeting three", "cs2113", dateOne, dateTwo, "None");
        filledTaskList.editTask(2, editedEvent);
        assertEquals(filledTaskList.getTask(2), editedEvent);
    }

    @Test
    public void editTask_emptyList_failure() {
        TaskList emptyTaskList = new TaskList();
        assertEquals(new EditCommand(1).execute(emptyTaskList,ui).feedbackToUser,
                Messages.NO_TASKS_MSG);
    }

    @Test
    public void editTask_filledList_failure() {
        assertEquals(new EditCommand(6).execute(filledTaskList, ui).feedbackToUser,
                "Please provide a valid task number from 1 to 4");
    }

    //@@author Keith-JK
    @Test
    public void testEditTask_sameTaskError() {
        final PrintStream consoleOut = System.out;
        final InputStream consoleIn = System.in;
        ByteArrayInputStream testInput;
        ByteArrayOutputStream testOutput;
        final String firstAssignmentUserInput = "assignment n/One m/cs2113 d/12/03/20 1600 c/Non";
        final String lineSeparator = System.lineSeparator();
        String feedback;
        try {
            testOutput = new ByteArrayOutputStream();
            testInput = new ByteArrayInputStream(firstAssignmentUserInput.getBytes());
            System.setIn(testInput);
            System.setOut(new PrintStream(testOutput));

            Ui ui = new Ui();
            feedback = new EditCommand(0).execute(filledTaskList, ui).feedbackToUser;
        } finally {
            System.setIn(consoleIn);
            System.setOut(consoleOut);
        }
        assertEquals(Messages.EDIT_PROMPT + lineSeparator + Messages.DIVIDER
                + lineSeparator + Messages.PROMPT_FOR_USER_INPUT, testOutput.toString());
        assertEquals(Messages.SAME_TASK_ERROR, feedback);
    }

    @Test
    public void testEditTask_erroneousInput() {
        final PrintStream consoleOut = System.out;
        final InputStream consoleIn = System.in;
        ByteArrayInputStream testInput;
        ByteArrayOutputStream testOutput;
        final String userInput = "even n/changed l/changed d/12/03/20 1600 - 1800 c/changed";

        final String lineSeparator = System.lineSeparator();
        String feedback;
        try {
            testOutput = new ByteArrayOutputStream();
            testInput = new ByteArrayInputStream(userInput.getBytes());
            System.setIn(testInput);
            System.setOut(new PrintStream(testOutput));

            Ui ui = new Ui();
            feedback = new EditCommand(3).execute(filledTaskList, ui).feedbackToUser;
        } finally {
            System.setIn(consoleIn);
            System.setOut(consoleOut);
        }
        assertEquals(Messages.EDIT_PROMPT + lineSeparator + Messages.DIVIDER
                + lineSeparator + Messages.PROMPT_FOR_USER_INPUT, testOutput.toString());
        assertEquals(Messages.UNKNOWN_COMMAND_ERROR, feedback);
    }

    @Test
    public void testEditTask_assignmentSuccess() {
        final PrintStream consoleOut = System.out;
        final InputStream consoleIn = System.in;
        ByteArrayInputStream testInput;
        ByteArrayOutputStream testOutput;
        final String firstAssignmentUserInput = "assignment n/changed m/changed d/12/03/20 1600 c/changed";
        Assignment edited = new Assignment("Changed", "changed", dateOne, "Changed");

        final String lineSeparator = System.lineSeparator();
        String feedback;
        try {
            testOutput = new ByteArrayOutputStream();
            testInput = new ByteArrayInputStream(firstAssignmentUserInput.getBytes());
            System.setIn(testInput);
            System.setOut(new PrintStream(testOutput));

            Ui ui = new Ui();
            feedback = new EditCommand(0).execute(filledTaskList, ui).feedbackToUser;
        } finally {
            System.setIn(consoleIn);
            System.setOut(consoleOut);
        }
        assertEquals(Messages.EDIT_PROMPT + lineSeparator + Messages.DIVIDER
                + lineSeparator + Messages.PROMPT_FOR_USER_INPUT, testOutput.toString());
        assertEquals(String.format(Messages.EDIT_SUCCESS_MESSAGE, edited), feedback);
    }

    @Test
    public void testEditTask_eventSuccess() {
        final PrintStream consoleOut = System.out;
        final InputStream consoleIn = System.in;
        ByteArrayInputStream testInput;
        ByteArrayOutputStream testOutput;
        final String userInput = "event n/changed l/changed d/12/03/20 1600 - 1800 c/changed";
        Event edited = new Event("Changed", "changed", dateOne, dateTwo,"Changed");

        final String lineSeparator = System.lineSeparator();
        String feedback;
        try {
            testOutput = new ByteArrayOutputStream();
            testInput = new ByteArrayInputStream(userInput.getBytes());
            System.setIn(testInput);
            System.setOut(new PrintStream(testOutput));

            Ui ui = new Ui();
            feedback = new EditCommand(3).execute(filledTaskList, ui).feedbackToUser;
        } finally {
            System.setIn(consoleIn);
            System.setOut(consoleOut);
        }
        assertEquals(Messages.EDIT_PROMPT + lineSeparator + Messages.DIVIDER
                + lineSeparator + Messages.PROMPT_FOR_USER_INPUT, testOutput.toString());
        assertEquals(String.format(Messages.EDIT_SUCCESS_MESSAGE, edited), feedback);
    }

    @Test
    public void testEditTask_repeatEventSuccess() {
        final PrintStream consoleOut = System.out;
        final InputStream consoleIn = System.in;
        ByteArrayInputStream testInput;
        ByteArrayOutputStream testOutput;

        RepeatEvent original = new RepeatEvent("Bathe", "Toilet", Parser.parseDate("01/01/20 2200"),
                Parser.parseDate("01/01/20 2220"), "before sleep", 1, RepeatCommand.DAILY_ICON,
                Parser.parseDate("01/01/20 2200"), 0);
        filledTaskList.addTask(original);

        final String userInput = "event n/changed l/changed d/12/03/20 1600 - 1800 c/changed";
        RepeatEvent edited = new RepeatEvent("Changed", "changed", dateOne, dateTwo,"Changed",
                1, RepeatCommand.DAILY_ICON, Parser.parseDate("01/01/20 2200"), 0);

        final String lineSeparator = System.lineSeparator();
        String feedback;
        try {
            testOutput = new ByteArrayOutputStream();
            testInput = new ByteArrayInputStream(userInput.getBytes());
            System.setIn(testInput);
            System.setOut(new PrintStream(testOutput));

            Ui ui = new Ui();
            feedback = new EditCommand(4).execute(filledTaskList, ui).feedbackToUser;
        } finally {
            System.setIn(consoleIn);
            System.setOut(consoleOut);
        }
        assertEquals(Messages.EDIT_PROMPT + lineSeparator + Messages.DIVIDER
                + lineSeparator + Messages.PROMPT_FOR_USER_INPUT, testOutput.toString());
        assertEquals(String.format(Messages.EDIT_SUCCESS_MESSAGE, edited), feedback);
    }
}
