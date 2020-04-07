package command;

import common.Messages;
import org.junit.jupiter.api.Test;
import seedu.atas.Parser;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Assignment;
import tasks.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author joelczk
public class SearchTest {
    private static TaskList filledTaskList;
    private static TaskList emptyTaskList;
    private static Ui ui;
    private StringBuilder searchString;

    /**
     * Initialize hard-coded test cases.
     */
    public SearchTest() {
        emptyTaskList = new TaskList();
        filledTaskList = new TaskList();
        ui = new Ui();
        searchString = new StringBuilder();
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

        Assignment testCaseOne = new Assignment("Test 3", "CS2102", testDateTime1, "-");
        Assignment testCaseTwo = new Assignment("Assignment 5", "CS2102", testDateTime1, "-");
        Assignment testCaseThree = new Assignment("OP1", "CS2101", testDateTime3, "15%");
        Event testCaseFour = new Event("midterms", "MPSH1A", testDateTime1, testDateTime2, "-");
        Event testCaseFive = new Event("Countdown", "TimeSquare", testDateTime3, testDateTime4, "new year new me");
        Event testCaseSix = new Event("mid", "MPSH1A", testDateTime1, testDateTime2, "-");
        Assignment testCaseSeven = new Assignment("Test 5", "CS2102", testDateTime1, "-");
        filledTaskList.addTask(testCaseOne);
        filledTaskList.addTask(testCaseTwo);
        filledTaskList.addTask(testCaseThree);
        filledTaskList.addTask(testCaseFour);
        filledTaskList.addTask(testCaseFive);
        filledTaskList.addTask(testCaseSix);
        filledTaskList.addTask(testCaseSeven);
    }

    private String searchSingleEvent() {
        searchString.append(Messages.SEARCH_SUCCESS_MESSAGE);
        searchString.append(System.lineSeparator());
        searchString.append("  4.[E][X] midterms (at: MPSH1A | Fri 13 Mar 2020 18:00 - 20:30)");
        searchString.append(System.lineSeparator());
        searchString.append("            notes: -");
        searchString.append(System.lineSeparator());
        return searchString.toString();
    }

    private String searchMultipleEvents() {
        searchString.append(Messages.SEARCH_SUCCESS_MESSAGE);
        searchString.append(System.lineSeparator());
        searchString.append("  4.[E][X] midterms (at: MPSH1A | Fri 13 Mar 2020 18:00 - 20:30)");
        searchString.append(System.lineSeparator());
        searchString.append("            notes: -");
        searchString.append(System.lineSeparator());
        searchString.append("  6.[E][X] mid (at: MPSH1A | Fri 13 Mar 2020 18:00 - 20:30)");
        searchString.append(System.lineSeparator());
        searchString.append("            notes: -");
        searchString.append(System.lineSeparator());
        return searchString.toString();
    }

    private String searchSingleAssignment() {
        searchString.append(Messages.SEARCH_SUCCESS_MESSAGE);
        searchString.append(System.lineSeparator());
        searchString.append("  1.[A][X] Test 3 (by: Fri 13 Mar 2020 18:00 | mod: CS2102)");
        searchString.append(System.lineSeparator());
        searchString.append("            notes: -");
        searchString.append(System.lineSeparator());
        return searchString.toString();
    }

    private String searchMultipleAssignments() {
        searchString.append(Messages.SEARCH_SUCCESS_MESSAGE);
        searchString.append(System.lineSeparator());
        searchString.append("  1.[A][X] Test 3 (by: Fri 13 Mar 2020 18:00 | mod: CS2102)");
        searchString.append(System.lineSeparator());
        searchString.append("            notes: -");
        searchString.append(System.lineSeparator());
        searchString.append("  7.[A][X] Test 5 (by: Fri 13 Mar 2020 18:00 | mod: CS2102)");
        searchString.append(System.lineSeparator());
        searchString.append("            notes: -");
        searchString.append(System.lineSeparator());
        return searchString.toString();
    }

    @Test
    public void searchExecuteMethod_emptyTaskList() {
        assertEquals(new SearchCommand("test", "all",null).execute(emptyTaskList,ui).feedbackToUser,
                Messages.EMPTY_TASKLIST_MESSAGE);
        assertEquals(new SearchCommand("test", "assignment",null).execute(emptyTaskList, ui).feedbackToUser,
                Messages.EMPTY_TASKLIST_MESSAGE);
        assertEquals(new SearchCommand("test", "event",null).execute(emptyTaskList, ui).feedbackToUser,
                Messages.EMPTY_TASKLIST_MESSAGE);
    }

    @Test
    public void searchExecuteMethod_invalidSearchArgument() {
        assertEquals(new SearchCommand("test", "abcd",null).execute(filledTaskList, ui).feedbackToUser,
                String.format(Messages.INCORRECT_ARGUMENT_ERROR,
                        Parser.capitalize(SearchCommand.COMMAND_WORD), SearchCommand.COMMAND_USAGE));
    }

    @Test
    public void searchExecuteMethod_searchOneEvent_success() {
        assertEquals(new SearchCommand("midterms", "event",null).execute(filledTaskList, ui).feedbackToUser,
                searchSingleEvent());
    }

    @Test
    public void searchExecuteMethod_searchMultipleEvents_success() {
        assertEquals(new SearchCommand("mid", "event",null).execute(filledTaskList, ui).feedbackToUser,
                searchMultipleEvents());
    }

    @Test
    public void searchExecuteMethod_searchOneAssignment_success() {
        assertEquals(new SearchCommand("Test 3", "assignment",null).execute(filledTaskList,ui).feedbackToUser,
                searchSingleAssignment());
    }

    @Test
    public void searchExecuteMethod_searchMultipleAssignments_success() {
        assertEquals(new SearchCommand("test", "assignment",null).execute(filledTaskList,ui).feedbackToUser,
                searchMultipleAssignments());
    }

    @Test
    public void searchExecuteMethod_emptyResults() {
        assertEquals(new SearchCommand("abcd", "event",null).execute(filledTaskList, ui).feedbackToUser,
                Messages.EMPTY_SEARCH_RESULTS_ERROR);
        assertEquals(new SearchCommand("abcd", "assignment",null).execute(filledTaskList, ui).feedbackToUser,
                Messages.EMPTY_SEARCH_RESULTS_ERROR);
        assertEquals(new SearchCommand("abcd", "all",null).execute(filledTaskList, ui).feedbackToUser,
                Messages.EMPTY_SEARCH_RESULTS_ERROR);
    }
}
