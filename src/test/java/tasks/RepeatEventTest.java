package tasks;

import command.RepeatCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.atas.Parser;
import seedu.atas.TaskList;
import seedu.atas.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author e0309556
public class RepeatEventTest {
    private static TaskList testTaskList;
    private static Ui testUi;

    /**
     * Initializing tasklisk, ui and event to be added and tested for all test cases.
     */
    @BeforeEach
    public void setup() {
        testTaskList = new TaskList();
        testUi = new Ui();

        Event testEvent = new Event("Daily Work", "CS2113T", Parser.parseDate("31/01/20 0800"),
                Parser.parseDate("31/01/20 1200"), "testing");
        Assignment testAssign = new Assignment("Daily Work", "CS2113T", Parser.parseDate("20/03/20 0000"),
                "testing");
        testTaskList.addTask(testEvent);
        testTaskList.addTask(testAssign);
    }

    //whatIsBeingTested_descriptionOfTestInputs_expectedOutcome
    @Test
    public void testAssignment_setToRepeat_invalidEventRepeatErrorMessage() {
        RepeatCommand testRepeatCommand = new RepeatCommand(1, 1, RepeatCommand.DAILY_ICON);
        assertEquals(testRepeatCommand.execute(testTaskList, testUi).feedbackToUser,
                "Daily Work is not an event. Please choose an event.");
    }

    @Test
    public void numOfPeriod_getNumOfPeriod_success() {
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 1, RepeatCommand.DAILY_ICON);
        testRepeatCommand.execute(testTaskList, testUi);
        RepeatEvent repeatEvent = (RepeatEvent) testTaskList.getTask(0);
        assertEquals(repeatEvent.getNumOfPeriod(), 1);
    }

    @Test
    public void typeOfPeriod_getTypeOfPeriod_success() {
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 1, RepeatCommand.DAILY_ICON);
        testRepeatCommand.execute(testTaskList, testUi);
        RepeatEvent repeatEvent = (RepeatEvent) testTaskList.getTask(0);
        assertEquals(repeatEvent.getTypeOfPeriod(), "d");
    }

    @Test
    public void repeatingTask_getDateOfRepeatTask_todayDate() {
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 1, RepeatCommand.DAILY_ICON);
        testRepeatCommand.execute(testTaskList, testUi);
        RepeatEvent repeatEvent = (RepeatEvent) testTaskList.getTask(0);
        assertEquals(repeatEvent.getDate(), LocalDateTime.now().toLocalDate());
    }

    @Test
    public void repeatingTask_getDateOfRepeatTask_nextWeekDate() {
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 1, RepeatCommand.WEEKLY_ICON);
        testRepeatCommand.execute(testTaskList, testUi);
        RepeatEvent repeatEvent = (RepeatEvent) testTaskList.getTask(0);
        LocalDate taskDate = repeatEvent.getOriginalDateAndTime().toLocalDate();
        assertEquals(repeatEvent.getDate(), taskDate.plusWeeks(
                repeatEvent.getPeriodCounter() * repeatEvent.getNumOfPeriod()));
    }

    @Test
    public void repeatingTask_getDateOfRepeatTask_nextMonthDate() {
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 1, RepeatCommand.MONTHLY_ICON);
        testRepeatCommand.execute(testTaskList, testUi);
        RepeatEvent repeatEvent = (RepeatEvent) testTaskList.getTask(0);
        LocalDate taskOriginalDate = repeatEvent.getOriginalDateAndTime().toLocalDate();
        assertEquals(repeatEvent.getDate(), taskOriginalDate.plusMonths(
                repeatEvent.getPeriodCounter() * repeatEvent.getNumOfPeriod()));
    }

    @Test
    public void repeatingTask_getDateOfRepeatTask_nextYearDate() {
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 1, RepeatCommand.YEARLY_ICON);
        testRepeatCommand.execute(testTaskList, testUi);
        RepeatEvent repeatEvent = (RepeatEvent) testTaskList.getTask(0);
        LocalDate taskOriginalDate = repeatEvent.getOriginalDateAndTime().toLocalDate();
        assertEquals(repeatEvent.getDate(), taskOriginalDate.plusYears(
                repeatEvent.getPeriodCounter() * repeatEvent.getNumOfPeriod()));
    }

    /**
     * Check that RepeatEvent does not break upon changes in numOfPeriod and typeOfPeriod
     * i.e using repeat command multiple times
     * Set date to 8 days ago. Then set repeat at 1w. Then set repeat to 1d. The NextDateTime should be next week.
     */
    @Test
    public void repeatingTask_changeRepeatPeriod_nextWeekDate() {
        LocalDate testDate = LocalDateTime.now().minusWeeks(1).minusDays(1).toLocalDate();
        LocalTime testTime = LocalTime.of(8, 30);
        LocalDateTime testDateTime = LocalDateTime.of(testDate, testTime);
        Event testEvent = new Event("8 days ago", "CS2113T", testDateTime, testDateTime.plusHours(4),
                "testing");
        testTaskList.addTask(testEvent);
        // Set to 1w
        RepeatCommand testRepeatCommand = new RepeatCommand(2, 1, RepeatCommand.WEEKLY_ICON);
        testRepeatCommand.execute(testTaskList, testUi);
        RepeatEvent repeatEvent = (RepeatEvent) testTaskList.getTask(2);
        // Set to 1d
        testRepeatCommand = new RepeatCommand(2, 1, RepeatCommand.DAILY_ICON);
        testRepeatCommand.execute(testTaskList, testUi);
        repeatEvent = (RepeatEvent) testTaskList.getTask(2);

        assertEquals(repeatEvent.getPeriodCounter(), 0);
        assertEquals(repeatEvent.getNextDateTime(), LocalDateTime.of(LocalDate.now().plusWeeks(1), testTime));
    }

    @Test
    public void repeatingTask_CheckDateOfEndOfMonth_lastDayOfMonthDate() {
        // Make arrays 1-based indexing for easy comparison.
        int[] endOfMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] endOfMonthLeapYear = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 1, RepeatCommand.MONTHLY_ICON);
        testRepeatCommand.execute(testTaskList, testUi);
        RepeatEvent repeatEvent = (RepeatEvent) testTaskList.getTask(0);
        LocalDate eventDate = repeatEvent.getDate();

        int year = LocalDate.now().getYear();
        // Check if this year is a leap year
        if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
            assertEquals(eventDate.getDayOfMonth(), endOfMonthLeapYear[eventDate.getMonthValue()]);
        } else {
            assertEquals(eventDate.getDayOfMonth(), endOfMonth[eventDate.getMonthValue()]);
        }
    }
}
