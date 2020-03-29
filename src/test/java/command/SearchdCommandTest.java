package command;

import common.Messages;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.atas.Parser;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Assignment;
import tasks.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@@author joelczk
public class SearchdCommandTest {
    public static final DateTimeFormatter INPUT_DATE_ONLY_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yy");
    private static TaskList filledTaskList;
    private static TaskList emptyTaskList;
    private static Ui ui;
    private StringBuilder searchString;
    private static String stringDate1 = "13/03/20";
    private static LocalDate date1 = LocalDate.parse(stringDate1,INPUT_DATE_ONLY_FORMAT);

    /**
     * Initialize hard-coded test cases.
     */
    public SearchdCommandTest() {
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

        Assignment testCaseOne = new Assignment("Assignment 3", "CS2102", testDateTime1, "-");
        Assignment testCaseTwo = new Assignment("Assignment 5", "CS2102", testDateTime1, "-");
        Assignment testCaseThree = new Assignment("OP1", "CS2101", testDateTime3, "15%");
        Event testCaseFour = new Event("midterms", "MPSH1A", testDateTime1, testDateTime2, "-");
        Event testCaseFive = new Event("Countdown", "TimeSquare", testDateTime3, testDateTime4, "new year new me");
        Event testCaseSix = new Event("mid", "MPSH1A", testDateTime1, testDateTime2, "-");
        filledTaskList.addTask(testCaseOne);
        filledTaskList.addTask(testCaseTwo);
        filledTaskList.addTask(testCaseThree);
        filledTaskList.addTask(testCaseFour);
        filledTaskList.addTask(testCaseFive);
        filledTaskList.addTask(testCaseSix);
    }

    private String eventSingleResultString() {
        searchString.append(Messages.SEARCH_SUCCESS_MESSAGE);
        searchString.append(System.lineSeparator());
        searchString.append("  1.[E][X] midterms (at: MPSH1A | Fri 13 Mar 2020 18:00 - 20:30)");
        searchString.append(System.lineSeparator());
        searchString.append("            notes: -");
        searchString.append(System.lineSeparator());
        return searchString.toString();
    }

    private String eventMultipleResultsString() {
        searchString.append(Messages.SEARCH_SUCCESS_MESSAGE);
        searchString.append(System.lineSeparator());
        searchString.append("  1.[E][X] midterms (at: MPSH1A | Fri 13 Mar 2020 18:00 - 20:30)");
        searchString.append(System.lineSeparator());
        searchString.append("            notes: -");
        searchString.append(System.lineSeparator());
        searchString.append("  2.[E][X] mid (at: MPSH1A | Fri 13 Mar 2020 18:00 - 20:30)");
        searchString.append(System.lineSeparator());
        searchString.append("            notes: -");
        searchString.append(System.lineSeparator());
        return searchString.toString();
    }

    private String assignmentSingleResultString() {
        searchString.append(Messages.SEARCH_SUCCESS_MESSAGE);
        searchString.append(System.lineSeparator());
        searchString.append("  1.[A][X] Assignment 3 (by: Fri 13 Mar 2020 18:00 | mod: CS2102)");
        searchString.append(System.lineSeparator());
        searchString.append("            notes: -");
        searchString.append(System.lineSeparator());
        return searchString.toString();
    }

    private String assignmentMultipleResultsString() {
        searchString.append(Messages.SEARCH_SUCCESS_MESSAGE);
        searchString.append(System.lineSeparator());
        searchString.append("  1.[A][X] Assignment 3 (by: Fri 13 Mar 2020 18:00 | mod: CS2102)");
        searchString.append(System.lineSeparator());
        searchString.append("            notes: -");
        searchString.append(System.lineSeparator());
        searchString.append("  2.[A][X] Assignment 5 (by: Fri 13 Mar 2020 18:00 | mod: CS2102)");
        searchString.append(System.lineSeparator());
        searchString.append("            notes: -");
        searchString.append(System.lineSeparator());
        return searchString.toString();
    }

    @Test
    public void executeMethod_EmptyTaskList() {
        assertEquals(new SearchdCommand("test", "all", date1).execute(emptyTaskList,ui).feedbackToUser,
                Messages.EMPTY_TASKLIST_MESSAGE);
        assertEquals(new SearchdCommand("test", "assignment", date1).execute(emptyTaskList, ui).feedbackToUser,
                Messages.EMPTY_TASKLIST_MESSAGE);
        assertEquals(new SearchdCommand("test", "event", date1).execute(emptyTaskList, ui).feedbackToUser,
                Messages.EMPTY_TASKLIST_MESSAGE);
    }

    @Test
    public void executeMethod_invalidSearchFormat() {
        assertEquals(new SearchdCommand("test", "abcd", date1).execute(filledTaskList, ui).feedbackToUser,
                String.format(Messages.INCORRECT_ARGUMENT_ERROR,
                        Parser.capitalize(SearchdCommand.COMMAND_WORD), SearchdCommand.COMMAND_USAGE));
    }

    @Test
    public void executeMethod_searchOneEvent_success() {
        String stringDate1 = "13/03/20";
        LocalDate date1 = LocalDate.parse(stringDate1,INPUT_DATE_ONLY_FORMAT);
        assertEquals(new SearchdCommand("event","midterms", date1).execute(filledTaskList, ui).feedbackToUser,
                eventSingleResultString());
    }

    @Test
    public void executeMethod_searchMultipleEvents_success() {
        String stringDate1 = "13/03/20";
        LocalDate date1 = LocalDate.parse(stringDate1,INPUT_DATE_ONLY_FORMAT);
        assertEquals(new SearchdCommand("event", "mid", date1).execute(filledTaskList, ui).feedbackToUser,
                eventMultipleResultsString());
    }

    @Test
    public void executeMethod_searchOneAssignment_success() {
        String stringDate1 = "13/03/20";
        LocalDate date1 = LocalDate.parse(stringDate1,INPUT_DATE_ONLY_FORMAT);
        assertEquals(new SearchdCommand("assignment", "assignment 3", date1).execute(filledTaskList,ui).feedbackToUser,
                assignmentSingleResultString());
    }

    @Test
    public void executeMethod_searchMultipleAssignments_success() {
        assertEquals(new SearchdCommand("assignment", "assignment", date1).execute(filledTaskList,ui).feedbackToUser,
                assignmentMultipleResultsString());
    }

    @Test
    public void  executeMethod_emptyResults() {
        assertEquals(new SearchdCommand("event","abcd", date1).execute(filledTaskList, ui).feedbackToUser,
                Messages.EMPTY_SEARCH_RESULTS_ERROR);
        assertEquals(new SearchdCommand("assignment", "abcd", date1).execute(filledTaskList, ui).feedbackToUser,
                Messages.EMPTY_SEARCH_RESULTS_ERROR);
        assertEquals(new SearchdCommand("all", "abcd", date1).execute(filledTaskList, ui).feedbackToUser,
                Messages.EMPTY_SEARCH_RESULTS_ERROR);
    }
}
