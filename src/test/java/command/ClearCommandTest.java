package command;

import common.Messages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Assignment;
import tasks.Event;
import tasks.RepeatEvent;
import tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author joelczk
public class ClearCommandTest {
    private static TaskList filledTaskList;
    private static TaskList emptyTaskList;
    private static Ui ui;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private static final String DATE1 = "13/03/2020 18:00";
    private static final String DATE2 = "13/03/2020 20:30";
    private static final String DATE3 = "01/01/2020 00:00";
    private static final String DATE4 =  "01/01/2020 02:59";
    private static Assignment testCaseOne;
    private static Assignment testCaseTwo;
    private static Assignment testCaseThree;
    private static Event testCaseFour;
    private static Event testCaseFive;
    private static Event testCaseSix;
    private static LocalDateTime testDateTime1;
    private static LocalDateTime testDateTime2;
    private static LocalDateTime testDateTime3;
    private static LocalDateTime testDateTime4;

    /**
     * Initialize variables used for each tests.
     */
    @BeforeEach
    public void setup() {
        filledTaskList = new TaskList();
        emptyTaskList = new TaskList();
        ui = new Ui();
        testDateTime1 = LocalDateTime.parse(DATE1, dateTimeFormatter);
        testDateTime2 = LocalDateTime.parse(DATE2, dateTimeFormatter);
        testDateTime3 = LocalDateTime.parse(DATE3, dateTimeFormatter);
        testDateTime4 = LocalDateTime.parse(DATE4, dateTimeFormatter);
        testCaseOne = new Assignment("Assignment 3", "CS2102", testDateTime1, " ");
        testCaseTwo = new Assignment("OP1", "CS2101", testDateTime3, "15%");
        testCaseThree = new Assignment(null, null, null, null);
        testCaseFour = new Event("midterms", "MPSH1A", testDateTime1, testDateTime2, " ");
        testCaseFive = new Event("Countdown", "TimeSquare", testDateTime3, testDateTime4, "new year new me");
        testCaseSix = new Event(null,null,null, null,null);
        filledTaskList.addTask(testCaseOne);
        filledTaskList.addTask(testCaseTwo);
        filledTaskList.addTask(testCaseThree);
        filledTaskList.addTask(testCaseFour);
        filledTaskList.addTask(testCaseFive);
        filledTaskList.addTask(testCaseSix);
    }

    @Test
    public void clearAll_FilledList_success() {
        assertEquals(new ClearCommand("all").execute(filledTaskList,ui).feedbackToUser,
                Messages.CLEAR_SUCCESS_MESSAGE);
    }

    @Test
    public void clearAll_EmptyList_noTaskMessage() {
        assertEquals(filledTaskList.getListSize(),6);
        assertEquals(new ClearCommand("all").execute(emptyTaskList,ui).feedbackToUser,
                Messages.NO_TASKS_MSG);
    }


    @Test
    public void clearDone_filledList_success() {
        filledTaskList.markTaskAsDone(2);
        filledTaskList.markTaskAsDone(4);
        assertEquals(new ClearCommand("done").execute(filledTaskList,ui).feedbackToUser,
                Messages.CLEAR_DONE_SUCCESS_MESSAGE);

    }

    @Test
    public void clearDone_filledList_failure() {
        assertEquals(new ClearCommand("done").execute(filledTaskList,ui).feedbackToUser,
                Messages.EMPTY_DONE_CLEAR_ERROR);
    }


    @Test
    public void clearDone_EmptyList() {
        assertEquals(new ClearCommand("done").execute(emptyTaskList,ui).feedbackToUser,
                Messages.EMPTY_TASKLIST_MESSAGE);
    }

    //@@author e0309556
    @Test
    public void repeatEventMarkedDone_clearDone_repeatEventNotCleared() {
        LocalDateTime testDateTime = LocalDateTime.of(2020, 02, 14, 5, 30);
        RepeatEvent testRepeatEvent = new RepeatEvent("Bathe2", "Toilet", testDateTime,
                testDateTime.plusMinutes(15), "before sleep", 4, RepeatCommand.DAILY_ICON,
                testDateTime, 0);
        TaskList repeatTestTaskList = filledTaskList;
        repeatTestTaskList.addTask(testRepeatEvent);
        for (Task task : repeatTestTaskList.getTaskArray()) {
            task.setDone();
        }
        CommandResult clearDone = new ClearCommand("done").execute(repeatTestTaskList, ui);
        assertEquals(repeatTestTaskList.getListSize(), 1);
    }
}
