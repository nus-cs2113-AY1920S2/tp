package command;

import common.Messages;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import seedu.atas.Parser;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Assignment;
import tasks.Event;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test in alphanumeric order instead of random order to ensure that
 * testing function "repeatingEvent_filledList_allTaskListMsg"
 * is the last to run and will not affect the other hard coded test cases.
 */
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class ListCommandTest {

    private static TaskList filledTasklist;
    private static TaskList emptyTasklist;
    private static TaskList filledWeeklyTaskList;

    private static Ui ui;

    private static LocalDateTime currDateTime1 = LocalDateTime.now();
    private static LocalDateTime currDateTime2 = LocalDateTime.now().plusSeconds(60);
    private static LocalDateTime oneWeekDateTime1 = currDateTime1.plusDays(7);
    private static LocalDateTime oneWeekDateTime2 = currDateTime2.plusDays(7);
    private static LocalDateTime afterCurrButSameDayDateTime1 = currDateTime1.plusSeconds(30);
    private static LocalDateTime afterCurrButSameDayDateTime2 = currDateTime1.plusSeconds(300);
    private static String beforeCurrDateTimeString1 = "13/02/20 1800";
    private static String beforeCurrDateTimeString2 = "13/02/20 2030";
    private static String afterCurrDateTimeString1 = "01/01/21 0000";
    private static String afterCurrDateTimeString2 = "01/01/21 0259";

    private static LocalDateTime beforeCurrDateTime1 =
            LocalDateTime.parse(beforeCurrDateTimeString1, Parser.INPUT_DATE_FORMAT);
    private static LocalDateTime beforeCurrDateTime2 =
            LocalDateTime.parse(beforeCurrDateTimeString2, Parser.INPUT_DATE_FORMAT);
    private static LocalDateTime afterCurrDateTime1 =
            LocalDateTime.parse(afterCurrDateTimeString1, Parser.INPUT_DATE_FORMAT);
    private static LocalDateTime afterCurrDateTime2 =
            LocalDateTime.parse(afterCurrDateTimeString2, Parser.INPUT_DATE_FORMAT);
    private static String currDateTimeStringForPrint1 = currDateTime1.format(Parser.PRINT_DATE_FORMAT);
    private static String currDateTimeStringForPrint2 = currDateTime2.format(Parser.PRINT_TIME_FORMAT);
    private static String nextWeekDateTimeStringForPrint1 = oneWeekDateTime1.format(Parser.PRINT_DATE_FORMAT);
    private static String nextWeekDateTimeStringForPrint2 = oneWeekDateTime2.format(Parser.PRINT_TIME_FORMAT);
    private static String afterCurrButSameDayStringForPrint1 =
            afterCurrButSameDayDateTime1.format(Parser.PRINT_DATE_FORMAT);
    private static String afterCurrButSameDayStringForPrint2 =
            afterCurrButSameDayDateTime2.format(Parser.PRINT_TIME_FORMAT);


    private static String expectedOutputFromFilledTasklist = "Here are the relevant tasks:"
            + System.lineSeparator()
            + "  1. [A][/] Assignment 3 (by: Thu 13 Feb 2020 18:00 | mod: CS2109)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "-"
            + System.lineSeparator()
            + "  2. [A][X] Quiz 1 (by: Fri 01 Jan 2021 00:00 | mod: CS2173)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "15%"
            + System.lineSeparator()
            + "  3. [E][X] midterms (at: MPSH1A | Thu 13 Feb 2020 18:00 - 20:30)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "-"
            + System.lineSeparator()
            + "  4. [E][X] Countdown (at: TimeSquare | Fri 01 Jan 2021 00:00 - 02:59)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "new year new me"
            + System.lineSeparator()
            + "  5. [E][X] Bathe (at: Toilet | " + afterCurrButSameDayStringForPrint1 + " - "
            + afterCurrButSameDayStringForPrint2 + ")"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "-";

    private static String expectedOutputFromUpcomingEvent = "Here are the relevant tasks:"
            + System.lineSeparator()
            + "  4. [E][X] Countdown (at: TimeSquare | Fri 01 Jan 2021 00:00 - 02:59)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "new year new me"
            + System.lineSeparator()
            + "  5. [E][X] Bathe (at: Toilet | " + afterCurrButSameDayStringForPrint1 + " - "
            + afterCurrButSameDayStringForPrint2 + ")"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "-";

    private static String expectedOutputFromIncompleteAssign = "Here are the relevant tasks:"
            + System.lineSeparator()
            + "  2. [A][X] Quiz 1 (by: Fri 01 Jan 2021 00:00 | mod: CS2173)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "15%";

    private static String expectedOutputFromListToday = "Here are the relevant tasks:"
            + System.lineSeparator()
            + "  1. [A][X] Assignment 1 (by: " + currDateTimeStringForPrint1 + " | mod: CS2113)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "Assignment 1 Notes"
            + System.lineSeparator()
            + "  2. [E][X] Event 1 (at: Classroom | " + currDateTimeStringForPrint1 + " - "
            + currDateTimeStringForPrint2 + ")"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "Event 1 Notes";

    private static String expectedOutputFromListWeek = "Here are the relevant tasks:"
            + System.lineSeparator()
            + "  1. [A][X] Assignment 1 (by: " + currDateTimeStringForPrint1 + " | mod: CS2113)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "Assignment 1 Notes"
            + System.lineSeparator()
            + "  2. [E][X] Event 1 (at: Classroom | " + currDateTimeStringForPrint1 + " - "
            + currDateTimeStringForPrint2 + ")"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "Event 1 Notes"
            + System.lineSeparator()
            + "  3. [A][X] Assignment 2 (by: " + nextWeekDateTimeStringForPrint1 + " | mod: CS2113)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "Assignment 2 Notes"
            + System.lineSeparator()
            + "  4. [E][X] Event 2 (at: Classroom | " + nextWeekDateTimeStringForPrint1 + " - "
            + nextWeekDateTimeStringForPrint2 + ")"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "Event 2 Notes";

    private static String expectedOutputFromFilledTasklistForRepeating = "Here are the relevant tasks:"
            + System.lineSeparator()
            + "  1. [A][/] Assignment 3 (by: Thu 13 Feb 2020 18:00 | mod: CS2109)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "-"
            + System.lineSeparator()
            + "  2. [A][X] Quiz 1 (by: Fri 01 Jan 2021 00:00 | mod: CS2173)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "15%"
            + System.lineSeparator()
            + "  3. [E][X] midterms (at: MPSH1A | Thu 13 Aug 2020 18:00 - 20:30)"
            + System.lineSeparator() + String.format(Messages.REPEAT_EVENT_WITH_COMMENTS_INDENT, "6m") + "-"
            + System.lineSeparator()
            + "  4. [E][X] Countdown (at: TimeSquare | Fri 01 Jan 2021 00:00 - 02:59)"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "new year new me"
            + System.lineSeparator()
            + "  5. [E][X] Bathe (at: Toilet | " + afterCurrButSameDayStringForPrint1 + " - "
            + afterCurrButSameDayStringForPrint2 + ")"
            + System.lineSeparator() + Messages.COMMENTS_INDENT + "-";

    /**
     * Initialize hardcoded test cases for testing.
     */
    @BeforeAll
    public static void setup() {
        filledTasklist = new TaskList();
        emptyTasklist = new TaskList();
        filledWeeklyTaskList = new TaskList();
        ui = new Ui();

        Assignment assignBeforeCurrDateTime = new Assignment("Assignment 3", "CS2109", beforeCurrDateTime1, "-");
        Assignment assignAfterCurrDateTime = new Assignment("Quiz 1", "CS2173", afterCurrDateTime1, "15%");
        Event eventBeforeCurrDateTime = new Event("midterms", "MPSH1A", beforeCurrDateTime1, beforeCurrDateTime2, "-");
        Event eventAfterCurrDateTime = new Event("Countdown", "TimeSquare", afterCurrDateTime1,
                afterCurrDateTime2, "new year new me");
        Event eventOnSameDayAfterCurrTime = new Event("Bathe", "Toilet", afterCurrButSameDayDateTime1,
                afterCurrButSameDayDateTime2, "-");

        filledTasklist.addTask(assignBeforeCurrDateTime);
        filledTasklist.addTask(assignAfterCurrDateTime);
        filledTasklist.addTask(eventBeforeCurrDateTime);
        filledTasklist.addTask(eventAfterCurrDateTime);
        filledTasklist.addTask(eventOnSameDayAfterCurrTime);
        filledTasklist.markTaskAsDone(0);

        Assignment currDateTimeAssignment = new Assignment("Assignment 1", "CS2113", currDateTime1,
                "Assignment 1 Notes");
        Event currDateTimeEvent = new Event("Event 1", "Classroom", currDateTime1, currDateTime2, "Event 1 Notes");
        Assignment nextWeekAssignment = new Assignment("Assignment 2", "CS2113", oneWeekDateTime1,
                "Assignment 2 Notes");
        Event nextWeekEvent = new Event("Event 2", "Classroom", oneWeekDateTime1, oneWeekDateTime2, "Event 2 Notes");
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
    public void printList_invalidArgs_errorMsg() {
        assertEquals("Invalid arguments for List Command" + System.lineSeparator() + ListCommand.COMMAND_USAGE,
                new ListCommand("everything i want").execute(filledTasklist, ui).feedbackToUser);
    }

    @Test
    public void printList_filledList_incompleteAssignOnly() {
        assertEquals(expectedOutputFromIncompleteAssign,
                new ListCommand("incomplete assignments").execute(filledTasklist, ui).feedbackToUser);
    }

    @Test
    public void printList_filledList_allTaskListMsg() {
        assertEquals(expectedOutputFromFilledTasklist,
                new ListCommand(null).execute(filledTasklist, ui).feedbackToUser);
    }

    @Test
    public void printList_filledList_upcomingEventsOnly() {
        assertEquals(expectedOutputFromUpcomingEvent,
                new ListCommand("upcoming events").execute(filledTasklist, ui).feedbackToUser);
    }

    @Test
    public void printList_filledWeeklyList_todayTasks() {
        assertEquals(expectedOutputFromListToday,
                new ListCommand("today").execute(filledWeeklyTaskList, ui).feedbackToUser);
    }

    @Test
    public void printList_filledWeeklyList_weeklyTasks() {
        assertEquals(expectedOutputFromListWeek,
                new ListCommand("week").execute(filledWeeklyTaskList, ui).feedbackToUser);
    }

    @Test
    public void repeatingEvent_filledList_allTaskListMsg() {
        RepeatCommand testRepeatCommand = new RepeatCommand(2, 6, "m");
        testRepeatCommand.execute(filledTasklist, ui);
        ((Event) filledTasklist.getTask(2)).updateDate();
        assertEquals(expectedOutputFromFilledTasklistForRepeating,
                new ListCommand("").execute(filledTasklist, ui).feedbackToUser);
    }
}
