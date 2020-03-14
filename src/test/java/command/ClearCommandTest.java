package command;

import common.Messages;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.duke.TaskList;
import seedu.duke.Ui;
import tasks.Assignment;
import tasks.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class ClearCommandTest {

    private static TaskList filledTaskList;
    private static TaskList emptyTaskList;
    private static Ui ui;

    /**
     * Initialize hard-coded test cases for testing purposes.
     */
    public ClearCommandTest() {
        filledTaskList = new TaskList();
        emptyTaskList = new TaskList();
        ui = new Ui();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String dateOne = "13/03/2020 18:00";
        String dateTwo = "01/01/2020 00:00";
        LocalDateTime testDateTimeOne = LocalDateTime.parse(dateOne, dateTimeFormatter);
        LocalDateTime testDateTimeTwo = LocalDateTime.parse(dateTwo, dateTimeFormatter);

        Assignment testCaseOne = new Assignment("Assignment 3", "CS2102", testDateTimeOne, " ");
        Assignment testCaseTwo = new Assignment("OP1", "CS2101", testDateTimeTwo, "15%");
        Assignment testCaseThree = new Assignment(null,null,null,null);
        Event testCaseFour = new Event("midterms", "MPSH1A", testDateTimeOne, " ");
        Event testCaseFive = new Event("Countdown", "TimeSquare", testDateTimeTwo, "new year new me");
        Event testCaseSix = new Event(null,null,null,null);
        filledTaskList.addTask(testCaseOne);
        filledTaskList.addTask(testCaseTwo);
        filledTaskList.addTask(testCaseThree);
        filledTaskList.addTask(testCaseFour);
        filledTaskList.addTask(testCaseFive);
        filledTaskList.addTask(testCaseSix);
    }

    @Test
    public void clearAll_filledList() {
        assertEquals(new ClearCommand("all").execute(filledTaskList,ui).feedbackToUser,
                Messages.CLEAR_SUCCESS_MESSAGE);
    }

    @Test
    public void clearAll_emptyList() {
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
}
