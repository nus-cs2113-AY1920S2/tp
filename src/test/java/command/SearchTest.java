package command;

import common.Messages;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Assignment;
import tasks.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SearchTest {
    private static TaskList filledTaskList;
    private static TaskList emptyTaskList;
    private static Ui ui;

    /**
     * Initialize hard-coded test cases.
     */
    public SearchTest() {
        emptyTaskList = new TaskList();
        filledTaskList = new TaskList();
        ui = new Ui();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        String date1 = "13/03/2020 18:00";
        String date2 = "13/03/2020 20:30";
        String date3 = "01/01/2020 00:00";
        String date4 = "01/01/2020 02:59";
        LocalDateTime testDateTime1 = LocalDateTime.parse(date1, dateTimeFormatter);
        LocalDateTime testDateTime2 = LocalDateTime.parse(date2, dateTimeFormatter);
        LocalDateTime testDateTime3 = LocalDateTime.parse(date3, dateTimeFormatter);
        LocalDateTime testDateTime4 = LocalDateTime.parse(date4, dateTimeFormatter);

        Assignment testCaseOne = new Assignment("Assignment 3", "CS2102", testDateTime1, " ");
        Assignment testCaseTwo = new Assignment("Assignment 5", "CS2102", testDateTime1, " ");
        Assignment testCaseThree = new Assignment("OP1", "CS2101", testDateTime3, "15%");
        Event testCaseFour = new Event("midterms", "MPSH1A", testDateTime1, testDateTime2, " ");
        Event testCaseFive = new Event("Countdown", "TimeSquare", testDateTime3, testDateTime4, "new year new me");
        Event testCaseSix = new Event("mid", "MPSH1A", testDateTime1, testDateTime2, " ");
        filledTaskList.addTask(testCaseOne);
        filledTaskList.addTask(testCaseTwo);
        filledTaskList.addTask(testCaseThree);
        filledTaskList.addTask(testCaseFour);
        filledTaskList.addTask(testCaseFive);
        filledTaskList.addTask(testCaseSix);
    }

    @Test
    public void testSearchExecuteEmptyTaskList() {
        assertEquals(new SearchCommand("test", "all").execute(emptyTaskList,ui).feedbackToUser,
                Messages.EMPTY_TASKLIST_MESSAGE);
        assertEquals(new SearchCommand("test", "assignment").execute(emptyTaskList, ui).feedbackToUser,
                Messages.EMPTY_TASKLIST_MESSAGE);
        assertEquals(new SearchCommand("test", "event").execute(emptyTaskList, ui).feedbackToUser,
                Messages.EMPTY_TASKLIST_MESSAGE);
    }

    @Test
    public void testSearchExecuteInvalidSearchFormat() {
        assertEquals(new SearchCommand("test", "abcd").execute(filledTaskList, ui).feedbackToUser,
                Messages.INVALID_SEARCH_FORMAT);
    }

    @Test
    public void testSearchExecuteEvent() {
        assertEquals(new SearchCommand("midterms", "event").execute(filledTaskList, ui).feedbackToUser,
                String.format(Messages.SEARCH_SUCCESS_MESSAGE, 1));
        assertEquals(new SearchCommand("mid", "event").execute(filledTaskList, ui).feedbackToUser,
                String.format(Messages.SEARCH_SUCCESS_MESSAGE, 2));
    }

    @Test
    public void testSearchExecuteAssignment() {
        assertEquals(new SearchCommand("assignment 3", "assignment").execute(filledTaskList,ui).feedbackToUser,
                String.format(Messages.SEARCH_SUCCESS_MESSAGE,1));
        assertEquals(new SearchCommand("assignment", "assignment").execute(filledTaskList,ui).feedbackToUser,
                String.format(Messages.SEARCH_SUCCESS_MESSAGE,2));
    }

    @Test
    public void testSearchExecute_emptyResults() {
        assertEquals(new SearchCommand("abcd", "event").execute(filledTaskList, ui).feedbackToUser,
                String.format(Messages.SEARCH_SUCCESS_MESSAGE, 0));
        assertEquals(new SearchCommand("abcd", "assignment").execute(filledTaskList, ui).feedbackToUser,
                String.format(Messages.SEARCH_SUCCESS_MESSAGE, 0));
        assertEquals(new SearchCommand("abcd", "all").execute(filledTaskList, ui).feedbackToUser,
                String.format(Messages.SEARCH_SUCCESS_MESSAGE, 0));
    }
}
