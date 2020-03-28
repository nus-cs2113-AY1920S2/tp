package command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.atas.Parser;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Assignment;
import tasks.Event;
import tasks.RepeatEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author e0309556
public class RepeatCommandTest {
    private static TaskList testTaskList;
    private static Ui testUi;
    //whatIsBeingTested_descriptionOfTestInputs_expectedOutcome

    /**
     * Initializing tasklisk, ui and event to be added and tested for all test cases.
     */
    @BeforeEach
    public void setup() {
        testTaskList = new TaskList();
        testUi = new Ui();

        Event testEvent = new Event("Daily Work", "CS2113T", Parser.parseDate("31/01/20 0800"),
                Parser.parseDate("20/03/20 1200"), "testing");
        Assignment testAssign = new Assignment("Daily Work", "CS2113T",
                Parser.parseDate("20/03/20 0000"), "testing");
        RepeatEvent testRepeatEvent = new RepeatEvent("Bathe", "Toilet", Parser.parseDate("01/01/20 2200"),
                Parser.parseDate("01/01/20 2220"), "before sleep", 1, RepeatCommand.DAILY_ICON,
                Parser.parseDate("01/01/20 2200"), 0);
        testTaskList.addTask(testEvent);
        testTaskList.addTask(testAssign);
        testTaskList.addTask(testRepeatEvent);
    }

    @Test
    public void executeMethod_assignTask_failure() {
        RepeatCommand testRepeatCommand = new RepeatCommand(1, 1, "d");
        assertEquals(testRepeatCommand.execute(testTaskList, testUi).feedbackToUser,
                "Daily Work is not an event. Please choose an event.");
    }

    @Test
    public void executeMethod_eventTask_success() {
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 1, "d");
        testRepeatCommand.execute(testTaskList, testUi);
        assertTrue(testTaskList.getTask(0) instanceof RepeatEvent);
    }

    @Test
    public void executeMethod_unsetRepeatTask_success() {
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 0, "d");
        testRepeatCommand.execute(testTaskList, testUi);
        assertTrue(testTaskList.getTask(2) instanceof Event);
    }

    @Test
    public void executeMethod_oobIndex_failure() {
        RepeatCommand testRepeatCommand = new RepeatCommand(5, 4, "d");
        assertEquals(testRepeatCommand.execute(testTaskList, testUi).feedbackToUser,
                "Please provide a valid task number from 1 to 3");
    }

    @Test
    public void executeMethod_unsetEventTask_failure() {
        RepeatCommand testRepeatCommand = new RepeatCommand(0, 0, "d");
        assertEquals(testRepeatCommand.execute(testTaskList, testUi).feedbackToUser,
                "Daily Work is not set to repeat.");
    }
}
