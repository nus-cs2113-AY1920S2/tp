package command;

import common.Messages;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import seedu.duke.Parser;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import tasks.Assignment;
import tasks.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {

    private static TaskList filledTasklist;
    private static TaskList emptyTasklist;
    private static TaskList filledWeeklyTaskList;

    private static Ui ui;

    private static LocalDateTime currDateTime = LocalDateTime.now();
    private static String beforeCurrDateTime = "13/02/20 1800";
    private static String afterCurrDateTime = "01/01/21 0000";
    private static LocalDateTime testDateTimeOne = LocalDateTime.parse(beforeCurrDateTime, Parser.INPUT_DATE_FORMAT);
    private static LocalDateTime testDateTimeTwo = LocalDateTime.parse(afterCurrDateTime, Parser.INPUT_DATE_FORMAT);
    private static LocalDateTime oneWeekDateTime = currDateTime.plusDays(7);
    private static String currDateTimeStringForPrint = currDateTime.format(Parser.PRINT_DATE_FORMAT);
    private static String nextWeekDateTimeStringForPrint = oneWeekDateTime.format(Parser.PRINT_DATE_FORMAT);
    
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
            + "  4. [E][X] Countdown (at: TimeSquare | Fri 01 Jan 2021 00:00)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "new year new me"
            + System.lineSeparator()
            + "  5. [E][X] Bathe (at: Toilet | " + currDateTime.plusSeconds(45).format(Parser.PRINT_DATE_FORMAT) + ")"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "-";

    private static String expectedOutputFromIncompleteAssign = "Here are the relevant tasks:"
            + System.lineSeparator()
            + "  2. [A][X] Quiz 1 (by: Fri 01 Jan 2021 00:00 | mod: CS2173)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "15%";

    private static String expectedOutputFromListToday = "Here are the relevant tasks:"
            + System.lineSeparator()
            + "  1. [A][X] Assignment 1 (by: " + currDateTimeStringForPrint + " | mod: CS2113)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "Assignment 1 Notes"
            + System.lineSeparator()
            + "  2. [E][X] Event 1 (at: Classroom | " + currDateTimeStringForPrint + ")"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "Event 1 Notes";

    private static String expectedOutputFromListWeek = "Here are the relevant tasks:"
            + System.lineSeparator()
            + "  1. [A][X] Assignment 1 (by: " + currDateTimeStringForPrint + " | mod: CS2113)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "Assignment 1 Notes"
            + System.lineSeparator()
            + "  2. [E][X] Event 1 (at: Classroom | " + currDateTimeStringForPrint + ")"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "Event 1 Notes"
            + System.lineSeparator()
            + "  3. [A][X] Assignment 2 (by: " + nextWeekDateTimeStringForPrint + " | mod: CS2113)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "Assignment 2 Notes"
            + System.lineSeparator()
            + "  4. [E][X] Event 2 (at: Classroom | " + nextWeekDateTimeStringForPrint + ")"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "Event 2 Notes";

    /**
     * Initialize hardcoded test cases for testing.
     */
    @BeforeAll
    public static void setup() {
        filledTasklist = new TaskList();
        emptyTasklist = new TaskList();
        filledWeeklyTaskList = new TaskList();
        ui = new Ui();

        Assignment assignBeforeCurrDateTime = new Assignment("Assignment 3", "CS2109", testDateTimeOne, "-");
        Assignment assignAfterCurrDateTime = new Assignment("Quiz 1", "CS2173", testDateTimeTwo, "15%");
        Event eventBeforeCurrDateTime = new Event("midterms", "MPSH1A", testDateTimeOne, "-");
        Event eventAfterCurrDateTime = new Event("Countdown", "TimeSquare", testDateTimeTwo, "new year new me");
        Event eventOnSameDayAfterCurrTime = new Event("Bathe", "Toilet", currDateTime.plusSeconds(45), "-");

        Assignment currDateTimeAssignment = new Assignment("Assignment 1", "CS2113", currDateTime, "Assignment 1 Notes");
        Event currDateTimeEvent = new Event("Event 1", "Classroom", currDateTime, "Event 1 Notes");

        Assignment nextWeekAssignment = new Assignment("Assignment 2", "CS2113", oneWeekDateTime, "Assignment 2 Notes");
        Event nextWeekEvent = new Event("Event 2", "Classroom", oneWeekDateTime, "Event 2 Notes");


        filledTasklist.addTask(assignBeforeCurrDateTime);
        filledTasklist.addTask(assignAfterCurrDateTime);
        filledTasklist.addTask(eventBeforeCurrDateTime);
        filledTasklist.addTask(eventAfterCurrDateTime);
        filledTasklist.addTask(eventOnSameDayAfterCurrTime);
        filledTasklist.markTaskAsDone(0);

        filledWeeklyTaskList.addTask(currDateTimeAssignment);
        filledWeeklyTaskList.addTask(currDateTimeEvent);
        filledWeeklyTaskList.addTask(nextWeekAssignment);
        filledWeeklyTaskList.addTask(nextWeekEvent);

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

    @Test
    public void printList_filledWeeklyList_todayTasks() {
        assertEquals(expectedOutputFromListToday,
                new ListCommand("today").execute(filledWeeklyTaskList,ui).feedbackToUser);
    }

    @Test
    public void printList_filledWeeklyList_weeklyTasks() {
        assertEquals(expectedOutputFromListWeek,
                new ListCommand("week").execute(filledWeeklyTaskList,ui).feedbackToUser );
    }
}
