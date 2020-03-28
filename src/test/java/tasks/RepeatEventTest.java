package tasks;

import command.RepeatCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.atas.Parser;
import seedu.atas.TaskList;
import seedu.atas.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
                Parser.parseDate("20/03/20 1200"), "testing");
        testTaskList.addTask(testEvent);
    }

    //whatIsBeingTested_descriptionOfTestInputs_expectedOutcome
    @Test
    public void testAssignment_setToRepeat_invalidEventRepeatErrorMessage() {
        Assignment testAssign = new Assignment("Daily Work", "CS2113T", Parser.parseDate("20/03/20 0000"),
                "testing");
        testTaskList.addTask(testAssign);
        RepeatCommand testRepeatCommand = new RepeatCommand(1, 1, "d");
        assertEquals(testRepeatCommand.execute(testTaskList, testUi).feedbackToUser,
                "Daily Work is not an event. Please choose an event.");
    }

    @Test
    public void numOfPeriod_getNumOfPeriod_success() {
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 1, "d");
        testRepeatCommand.execute(testTaskList, testUi);
        RepeatEvent repeatEvent = (RepeatEvent) testTaskList.getTask(0);
        repeatEvent.updateDate();
        assertEquals(repeatEvent.getNumOfPeriod(), 1);
    }

    @Test
    public void typeOfPeriod_getTypeOfPeriod_success() {
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 1, "d");
        testRepeatCommand.execute(testTaskList, testUi);
        RepeatEvent repeatEvent = (RepeatEvent) testTaskList.getTask(0);
        repeatEvent.updateDate();
        assertEquals(repeatEvent.getTypeOfPeriod(), "d");
    }

    @Test
    public void repeatingTask_getDateOfRepeatTask_tomorrowDate() {
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 1, "d");
        testRepeatCommand.execute(testTaskList, testUi);
        RepeatEvent repeatEvent = (RepeatEvent) testTaskList.getTask(0);
        repeatEvent.updateDate();
        assertEquals(repeatEvent.getDate(), LocalDateTime.now().toLocalDate());
    }

    @Test
    public void repeatingTask_getDateOfRepeatTask_nextWeekDate() {
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 1, "w");
        testRepeatCommand.execute(testTaskList, testUi);
        RepeatEvent repeatEvent = (RepeatEvent) testTaskList.getTask(0);
        LocalDate taskDate = repeatEvent.getDate();
        repeatEvent.updateDate();
        assertEquals(repeatEvent.getDate(), taskDate.plusWeeks(
                repeatEvent.getPeriodCounter() * repeatEvent.getNumOfPeriod()));
    }

    @Test
    public void repeatingTask_getDateOfRepeatTask_nextMonthDate() {
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 1, "m");
        testRepeatCommand.execute(testTaskList, testUi);
        RepeatEvent repeatEvent = (RepeatEvent) testTaskList.getTask(0);
        LocalDate taskDate = repeatEvent.getDate();
        repeatEvent.updateDate();
        assertEquals(repeatEvent.getDate(), taskDate.plusMonths(
                repeatEvent.getPeriodCounter() * repeatEvent.getNumOfPeriod()));
    }

    @Test
    public void repeatingTask_getDateOfRepeatTask_nextYearDate() {
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 1, "y");
        testRepeatCommand.execute(testTaskList, testUi);
        RepeatEvent repeatEvent = (RepeatEvent) testTaskList.getTask(0);
        LocalDate taskDate = repeatEvent.getDate();
        repeatEvent.updateDate();
        assertEquals(repeatEvent.getDate(), taskDate.plusYears(
                repeatEvent.getPeriodCounter() * repeatEvent.getNumOfPeriod()));
    }
}
