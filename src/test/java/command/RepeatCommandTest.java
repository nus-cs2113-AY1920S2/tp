package command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.atas.Parser;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Assignment;
import tasks.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RepeatCommandTest {
    private static Event testEvent;
    private static TaskList testTaskList;
    private static Ui testUi;

    /**
     *  Initializing tasklisk, ui and event to be added and tested for all test cases.
     */
    @BeforeEach
    public void setup() {
        testTaskList = new TaskList();
        testUi = new Ui();

        testEvent = new Event("Daily Work", "CS2113T", Parser.parseDate("20/03/20 0000"),
                Parser.parseDate("20/03/20 1200"), "testing");
        testTaskList.addTask(testEvent);
    }

    //whatIsBeingTested_descriptionOfTestInputs_expectedOutcome
    @Test
    public void nonRecurringEvent_updateDateTime_failure() {
        testEvent.updateDate();
        assertNotEquals(testEvent.getIsRepeat(), true);
    }

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
        testEvent.updateDate();
        assertEquals(testEvent.getNumOfPeriod(), 1);
    }

    @Test
    public void typeOfPeriod_getTypeOfPeriod_success() {
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 1, "d");
        testRepeatCommand.execute(testTaskList, testUi);
        testEvent.updateDate();
        assertEquals(testEvent.getTypeOfPeriod(), "d");
    }

    @Test
    public void repeatingTask_getDateOfRepeatTask_tomorrowDate() {
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 1, "d");
        testRepeatCommand.execute(testTaskList, testUi);
        testEvent.updateDate();
        assertEquals(testEvent.getDate(), LocalDateTime.now().plusDays(1).toLocalDate());
    }

    @Test
    public void repeatingTask_getDateOfRepeatTask_nextWeekDate() {
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 1, "w");
        testRepeatCommand.execute(testTaskList, testUi);
        LocalDate taskDate = testEvent.getDate();
        testEvent.updateDate();
        assertEquals(testEvent.getDate(), taskDate.plusWeeks(1));
    }

    @Test
    public void repeatingTask_getDateOfRepeatTask_nextMonthDate() {
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 1, "m");
        testRepeatCommand.execute(testTaskList, testUi);
        LocalDate taskDate = testEvent.getDate();
        testEvent.updateDate();
        assertEquals(testEvent.getDate(), taskDate.plusMonths(1));
    }

    @Test
    public void repeatingTask_getDateOfRepeatTask_nextYearDate() {
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 1, "y");
        testRepeatCommand.execute(testTaskList, testUi);
        LocalDate taskDate = testEvent.getDate();
        testEvent.updateDate();
        assertEquals(testEvent.getDate(), taskDate.plusYears(1));
    }

    @Test
    public void repeatingTask_setNoRepeat_taskNotRepeating() {
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 1, "y");
        testRepeatCommand.execute(testTaskList, testUi);
        assertEquals(testEvent.getIsRepeat(), true);
        RepeatCommand testNoRepeatCommand = new RepeatCommand(0, 0, null);
        testNoRepeatCommand.execute(testTaskList, testUi);
        assertEquals(testEvent.getIsRepeat(), false);
    }

    @Test
    public void repeatingTask_invalidIndex_failure() {
        String expectedOutput = "Please provide a valid task number from 1 to 1";
        RepeatCommand testRepeatCommand = new RepeatCommand(5, 1, "y");
        assertEquals(testRepeatCommand.execute(testTaskList, testUi).feedbackToUser, expectedOutput);
    }
}
