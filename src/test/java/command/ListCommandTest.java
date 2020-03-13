package command;

import common.Messages;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import tasks.Assignment;
import tasks.Event;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {

    private static TaskList filledTasklist;
    private static TaskList emptyTasklist;
    private static Ui ui;

    private static LocalDateTime currDateTime = LocalDateTime.now();
    private static String beforeCurrDateTime = "13/02/20 1800";
    private static String afterCurrDateTime = "01/01/21 0000";
    private static LocalDateTime testDateTimeOne = LocalDateTime.parse(beforeCurrDateTime, Parser.INPUT_DATE_FORMAT);
    private static LocalDateTime testDateTimeTwo = LocalDateTime.parse(afterCurrDateTime, Parser.INPUT_DATE_FORMAT);

    private static String expectedOutputFromFilledTasklist = "Here are the relevant tasks:"
            + System.lineSeparator()
            + "  1. [A][/] Assignment 3 (by: Thu 13 Feb 2020 18:00 | mod: CS2109)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "-"
            + System.lineSeparator()
            + "  2. [A][X] Quiz 1 (by: Fri 01 Jan 2021 00:00 | mod: CS2173)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "15%"
            + System.lineSeparator()
            + "  3. [E][X] midterms (at: MPSH1A | Thu 13 Feb 2020 18:00)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "-"
            + System.lineSeparator()
            + "  4. [E][X] Countdown (at: TimeSquare | Fri 01 Jan 2021 00:00)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "new year new me"
            + System.lineSeparator()
            + "  5. [E][X] Bathe (at: Toilet | " + currDateTime.plusSeconds(45).format(Parser.PRINT_DATE_FORMAT) + ")"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "-";

    private static String expectedOutputFromUpcomingEvent = "Here are the relevant tasks:"
            + System.lineSeparator()
            + "  1. [E][X] Countdown (at: TimeSquare | Fri 01 Jan 2021 00:00)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "new year new me"
            + System.lineSeparator()
            + "  2. [E][X] Bathe (at: Toilet | " + currDateTime.plusSeconds(45).format(Parser.PRINT_DATE_FORMAT) + ")"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "-";

    private static String expectedOutputFromIncompleteAssign = "Here are the relevant tasks:"
            + System.lineSeparator()
            + "  1. [A][X] Quiz 1 (by: Fri 01 Jan 2021 00:00 | mod: CS2173)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "15%";

    /**
     * Initialize hardcoded test cases for testing.
     */
    @BeforeAll
    public static void setup() {
        filledTasklist = new TaskList();
        emptyTasklist = new TaskList();
        ui = new Ui();

        Assignment assignBeforeCurrDateTime = new Assignment("Assignment 3", "CS2109", testDateTimeOne, "-");
        Assignment assignAfterCurrDateTime = new Assignment("Quiz 1", "CS2173", testDateTimeTwo, "15%");
        Event eventBeforeCurrDateTime = new Event("midterms", "MPSH1A", testDateTimeOne, "-");
        Event eventAfterCurrDateTime = new Event("Countdown", "TimeSquare", testDateTimeTwo, "new year new me");
        Event eventOnSameDayAfterCurrTime = new Event ("Bathe", "Toilet", currDateTime.plusSeconds(45), "-");

        filledTasklist.addTask(assignBeforeCurrDateTime);
        filledTasklist.addTask(assignAfterCurrDateTime);
        filledTasklist.addTask(eventBeforeCurrDateTime);
        filledTasklist.addTask(eventAfterCurrDateTime);
        filledTasklist.addTask(eventOnSameDayAfterCurrTime);
        filledTasklist.markTaskAsDone(0);
    }

    @Test
    public void printList_emptyList_emptyListMsg() {
        assertEquals(Messages.EMPTY_TASKLIST_MESSAGE,
                new ListCommand(null).execute(emptyTasklist, ui).feedbackToUser);
    }

    @Test
    public void printList_filledList_allTasksList() {
        assertEquals(expectedOutputFromFilledTasklist,
                new ListCommand(null).execute(filledTasklist, ui).feedbackToUser);
    }

    @Test
    public void printList_filledList_upcomingEventOnly() {
        assertEquals(expectedOutputFromUpcomingEvent,
                new ListCommand("upcoming events").execute(filledTasklist, ui).feedbackToUser);
    }

    @Test
    public void printList_filledList_incompleteAssignOnly() {
        assertEquals(expectedOutputFromIncompleteAssign,
                new ListCommand("incomplete assignments").execute(filledTasklist, ui).feedbackToUser);
    }
}
