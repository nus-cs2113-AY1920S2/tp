package command;

import common.Messages;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import tasks.Assignment;
import tasks.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

//whatIsBeingTested_descriptionOfTestInputs_expectedOutcome
public class ListCommandTest {

    private static TaskList filledTasklist;
    private static TaskList emptyTasklist;
    private static Ui ui;
    private static Assignment testCaseOne;
    private static Assignment testCaseTwo;
    private static Event testCaseThree;
    private static Event testCaseFour;
    private static String expectedOutputFromFilledTasklist = "Here are the relevant tasks:"
            + System.lineSeparator()
            + "  1. [A][X] Assignment 3 (by: Fri 13 Mar 2020 18:00 | mod: CS2102)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + " "
            + System.lineSeparator()
            + "  2. [A][X] OP1 (by: Wed 01 Jan 2020 00:00 | mod: CS2101)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "15%"
            + System.lineSeparator()
            + "  3. [E][X] midterms (at: MPSH1A | Fri 13 Mar 2020 18:00)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + " "
            + System.lineSeparator()
            + "  4. [E][X] Countdown (at: TimeSquare | Wed 01 Jan 2020 00:00)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "new year new me";

    /**
     * Initialize hardcoded test cases for testing.
     */
    @BeforeAll
    public static void setup() {
        filledTasklist = new TaskList();
        emptyTasklist = new TaskList();
        ui = new Ui();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String dateOne = "13/03/2020 18:00";
        String dateTwo = "01/01/2020 00:00";
        LocalDateTime testDateTimeOne = LocalDateTime.parse(dateOne, dateTimeFormatter);
        LocalDateTime testDateTimeTwo = LocalDateTime.parse(dateTwo, dateTimeFormatter);


        Assignment testCaseOne = new Assignment("Assignment 3", "CS2102", testDateTimeOne, " ");
        Assignment testCaseTwo = new Assignment("OP1", "CS2101", testDateTimeTwo, "15%");
        Event testCaseThree = new Event("midterms", "MPSH1A", testDateTimeOne, " ");
        Event testCaseFour = new Event("Countdown", "TimeSquare", testDateTimeTwo, "new year new me");

        filledTasklist.addTask(testCaseOne);
        filledTasklist.addTask(testCaseTwo);
        filledTasklist.addTask(testCaseThree);
        filledTasklist.addTask(testCaseFour);
    }

    @Test
    public void printList_emptyList_emptyListMsg() {
        assertEquals(Messages.EMPTY_TASKLIST_MESSAGE,
                new ListCommand(null).execute(emptyTasklist, ui).feedbackToUser);
    }

    @Test
    public void printList_filledList_filledListMsg() {
        assertEquals(expectedOutputFromFilledTasklist,
                new ListCommand(null).execute(filledTasklist, ui).feedbackToUser);
    }
}
