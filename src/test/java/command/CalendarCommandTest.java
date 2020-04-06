package command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.atas.Parser;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Assignment;
import tasks.Event;
import tasks.RepeatEvent;
import tasks.Task;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@@author
public class CalendarCommandTest {
    public static final DateTimeFormatter INPUT_DATE_ONLY_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yy");
    private static CalendarCommand testCalendarCommand;
    private static TaskList testTaskList;
    private static Ui testUi;
    private static LocalDate testLocalDate;
    private static LocalDate testLocalDate1;
    private static LocalDate testLocalDate2;
    private static LocalDate testRepeatLocalDate;
    private static LocalDate testRepeatLocalDate2;
    private static Calendar testCalendar;
    private static StringBuilder testBuilder;

    // ANSI text colour scheme
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static final String BORDER = ANSI_PURPLE + "*" + ANSI_RESET;
    private static final String STARTING_BORDER = ANSI_PURPLE + "*" + ANSI_RESET;
    private static final String PAD = " ";
    private static final String MORE_TASK_INDICATOR = ANSI_CYAN + "....." + ANSI_RESET;

    private static Assignment testCaseTwo = null;
    private static RepeatEvent testCaseRepeatDaily = null;
    private static Event testCaseFour = null;
    private RepeatEvent testCaseRepeatWeekly = null;
    private RepeatEvent testCaseRepeatMonthly = null;
    private RepeatEvent testCaseRepeatYearly = null;

    // Calendar dimensions
    private static final int DAYS_IN_WEEK = 7;

    // sizing of each Calendar box
    private static final int MAX_CALENDAR_BOX_WIDTH = 20;
    private static final int DATE_PADDING_WIDTH = MAX_CALENDAR_BOX_WIDTH - 3;
    private static final int EMPTY_BOX_PADDING = MAX_CALENDAR_BOX_WIDTH - 1;
    private static final int CONTENT_WIDTH = MAX_CALENDAR_BOX_WIDTH - 1;
    private static final int MIDDLE_JUSTIFIED_WIDTH_PADDING = MAX_CALENDAR_BOX_WIDTH / 2 - 3;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Setup Commands, Calendar before each test.
     */
    @BeforeEach
    public void setup() {
        testTaskList = new TaskList();
        testUi = new Ui();
        testBuilder = new StringBuilder();

        final String date1 = "13/03/2020 18:00";
        final String date2 = "13/03/2020 20:30";
        final String date3 = "01/01/2020 00:00";
        final String date4 = "01/01/2020 02:59";
        final String startDateForRepeat = "01/06/2020 00:00";
        final String endDateForRepeat = "01/06/2020 13:00";
        final LocalDateTime testDateTime1 = LocalDateTime.parse(date1, dateTimeFormatter);
        final LocalDateTime testDateTime2 = LocalDateTime.parse(date2, dateTimeFormatter);
        final LocalDateTime testDateTime3 = LocalDateTime.parse(date3, dateTimeFormatter);
        final LocalDateTime testDateTime4 = LocalDateTime.parse(date4, dateTimeFormatter);
        final LocalDateTime testRepeatStartDateTime = LocalDateTime.parse(startDateForRepeat, dateTimeFormatter);
        final LocalDateTime testRepeatEndDateTime = LocalDateTime.parse(endDateForRepeat, dateTimeFormatter);

        final Assignment testCaseOne = new Assignment("Assignment 3", "CS2102", testDateTime1, " ");
        testCaseTwo = new Assignment("OP1", "CS2101", testDateTime3, "15%");
        final Event testCaseThree = new Event("midterms", "MPSH1A", testDateTime1, testDateTime2, " ");
        // set to repeat every 14 days in the month of Jan 2020, will only see it thrice in month of Jan
        // inclusive of the original event
        testCaseRepeatDaily = new RepeatEvent("Countdown", "TimeSquare", testDateTime3, testDateTime4,
                "new year new me",14, RepeatCommand.DAILY_ICON, testDateTime3, 0);

        testCaseFour = new Event("Thisttttttttttttttttttttttttttttttttttttt", "MPSH1A", testDateTime1,
                testDateTime2, " ");

        testCaseRepeatWeekly = new RepeatEvent("Countdown Week", "TimeSquare", testRepeatStartDateTime,
                testRepeatEndDateTime, "new year new me",1, RepeatCommand.WEEKLY_ICON,
                testDateTime3, 0);

        testCaseRepeatMonthly = new RepeatEvent("Countdown Month", "TimeSquare", testRepeatStartDateTime,
                testRepeatEndDateTime, "new year new me",1, RepeatCommand.MONTHLY_ICON,
                testDateTime3, 0);

        testCaseRepeatYearly = new RepeatEvent("Countdown Year", "TimeSquare", testRepeatStartDateTime,
                testRepeatEndDateTime, "new year new me",1, RepeatCommand.YEARLY_ICON,
                testDateTime3, 0);

        testCalendar = Calendar.getInstance();
        final String testDate = "01/01/20";
        final String testDate1 = "13/03/20";
        final String testDate2 = "02/02/20";
        final String testRepeat = "01/06/20";
        final String testRepeat2 = "01/06/21";
        testLocalDate = LocalDate.parse(testDate, INPUT_DATE_ONLY_FORMAT);
        testLocalDate1 = LocalDate.parse(testDate1, INPUT_DATE_ONLY_FORMAT);
        testLocalDate2 = LocalDate.parse(testDate2, INPUT_DATE_ONLY_FORMAT);
        testRepeatLocalDate = LocalDate.parse(testRepeat, INPUT_DATE_ONLY_FORMAT);
        testRepeatLocalDate2 = LocalDate.parse(testRepeat2, INPUT_DATE_ONLY_FORMAT);
        testCalendarCommand = new CalendarCommand(testLocalDate);


        testTaskList.addTask(testCaseOne);
        testTaskList.addTask(testCaseTwo);
        testTaskList.addTask(testCaseThree);
        testTaskList.addTask(testCaseRepeatDaily);
        testTaskList.addTask(testCaseFour);
        testTaskList.addTask(testCaseRepeatWeekly);
        testTaskList.addTask(testCaseRepeatMonthly);
        testTaskList.addTask(testCaseRepeatYearly);
    }

    @Test
    public void testCalendarCommand() {
        assertNotNull(testCalendarCommand.execute(testTaskList, testUi).feedbackToUser);
        assertEquals(testCalendarCommand.execute(testTaskList, testUi).feedbackToUser.getClass(), String.class);
        assertTrue(testCalendarCommand.execute(testTaskList, testUi).feedbackToUser.contains("January 2020"));
    }

    @Test
    public void testBuildMonthCalendar() {
        assertNotNull(testCalendarCommand.buildMonthCalendar(testLocalDate, testTaskList));
        assertEquals(testCalendarCommand.buildMonthCalendar(testLocalDate, testTaskList).getClass(), String.class);
        assertTrue(testCalendarCommand.buildMonthCalendar(testLocalDate, testTaskList).contains("January 2020"));

        StringBuilder testBorder = new StringBuilder();
        testCalendarCommand.addCalendarBorder(testBorder);
        assertTrue(testCalendarCommand.buildMonthCalendar(testLocalDate, testTaskList)
                .contains(testBorder.toString()));

        StringBuilder testCalendarLegend = new StringBuilder();
        testCalendarCommand.addCalendarBorder(testCalendarLegend);
        assertTrue(testCalendarCommand.buildMonthCalendar(testLocalDate, testTaskList)
                .contains(testCalendarLegend.toString()));

        StringBuilder testCalendarTitle = new StringBuilder();
        testCalendarCommand.addCalendarBorder(testCalendarTitle);
        assertTrue(testCalendarCommand.buildMonthCalendar(testLocalDate, testTaskList)
                .contains(testCalendarTitle.toString()));
    }

    @Test
    public void testCalibrateCalendar() {
        testCalendarCommand.calibrateCalendar(testLocalDate, testCalendar);
        assertEquals(testCalendar.get(Calendar.MONTH), testLocalDate.getMonthValue() - 1);
        assertEquals(testCalendar.get(Calendar.YEAR), testLocalDate.getYear());
        assertEquals(testCalendar.get(Calendar.DAY_OF_MONTH), 1);
    }

    @Test
    public void testGetTaskByYearMonth() {
        // Repeated Event is in Jan
        ArrayList<Task> resultList = testCalendarCommand.getTasksByYearMonth(testLocalDate, testTaskList);
        assertEquals(2, resultList.size());
        ArrayList<Task> resultList1 = testCalendarCommand.getTasksByYearMonth(testLocalDate1, testTaskList);
        assertEquals(4, resultList1.size());
        ArrayList<Task> resultList2 = testCalendarCommand.getTasksByYearMonth(testLocalDate2, testTaskList);
        assertEquals(1, resultList2.size());
        ArrayList<Task> resultListRepeatEvents = testCalendarCommand.getTasksByYearMonth(testRepeatLocalDate,
                testTaskList);
        assertEquals(4, resultListRepeatEvents.size());
        ArrayList<Task> resultListRepeatEvents2 = testCalendarCommand.getTasksByYearMonth(testRepeatLocalDate2,
                testTaskList);
        assertEquals(4, resultListRepeatEvents2.size());
    }

    @Test
    public void testStartBorder() {
        testCalendarCommand.addCalendarStartBorder(testBuilder);
        assertEquals(testBuilder.toString(), STARTING_BORDER);
    }

    @Test
    public void testCalendarNewLine() {
        testCalendarCommand.addCalendarNewLine(testBuilder);
        assertEquals(testBuilder.toString(), System.lineSeparator());
    }

    @Test
    public void testAddTaskToCalendar() {
        testCalendarCommand.calibrateCalendar(testLocalDate, testCalendar);
        ArrayList<Task> arrayList = testCalendarCommand.getTasksByYearMonth(testLocalDate, testTaskList);
        testCalendarCommand.addTaskToCalendar(arrayList, testBuilder, testCaseTwo);
        assertEquals(1, arrayList.size());
        String taskDetails = testCaseTwo.getTime().format(Parser.PRINT_TIME_FORMAT) + testCaseTwo.getName();
        String testString = ANSI_RED + taskDetails + ANSI_RESET
                + PAD.repeat((CONTENT_WIDTH - taskDetails.length())) + BORDER;
        assertEquals(testBuilder.toString(), testString);
    }

    @Test
    public void testAddTaskToCalendarWithLongEventInput() {
        // test Event and long name
        testCalendarCommand.calibrateCalendar(testLocalDate1, testCalendar);
        ArrayList<Task> arrayList = testCalendarCommand.getTasksByYearMonth(testLocalDate1, testTaskList);
        testCalendarCommand.addTaskToCalendar(arrayList, testBuilder, testCaseFour);
        String taskDetailFive = testCaseFour.getTime().format(Parser.PRINT_TIME_FORMAT) + testCaseFour.getName();
        taskDetailFive = taskDetailFive.substring(0, CONTENT_WIDTH);
        String testStringFive = ANSI_GREEN + taskDetailFive + ANSI_RESET + BORDER;
        assertEquals(testStringFive, testBuilder.toString());
    }

    @Test
    public void testAddCalendarBorder() {
        testCalendarCommand.addCalendarBorder(testBuilder);
        String testString = BORDER.repeat(MAX_CALENDAR_BOX_WIDTH * DAYS_IN_WEEK + 1) + System.lineSeparator();
        assertEquals(testBuilder.toString(), testString);
    }

    @Test
    public void testAddEmptyBody() {
        testCalendarCommand.addEmptyCalendarBody(testBuilder);
        String testString = PAD.repeat(EMPTY_BOX_PADDING) + BORDER;
        assertEquals(testBuilder.toString(), testString);
    }

    @Test
    public void testAddCalendarDate() {
        final int testDaySingleDigit = 9;
        testCalendarCommand.addCalendarDate(testBuilder, testDaySingleDigit);
        String testString = PAD.repeat(DATE_PADDING_WIDTH) + ANSI_CYAN
                + testDaySingleDigit + ANSI_RESET + PAD + BORDER;
        assertEquals(testString, testBuilder.toString());

        testBuilder.delete(0, testBuilder.length());
        final int testDayDoubleDigit = 30;
        testCalendarCommand.addCalendarDate(testBuilder, testDayDoubleDigit);
        String testString2 = PAD.repeat(DATE_PADDING_WIDTH) + ANSI_CYAN
                + testDayDoubleDigit + ANSI_RESET + BORDER;
        assertEquals(testBuilder.toString(), testString2);
    }

    @Test
    public void testCalendarLegend() {
        testCalendarCommand.addCalendarLegend(testBuilder);

        StringBuilder calendarView = new StringBuilder();
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        calendarView.append(BORDER.repeat(MAX_CALENDAR_BOX_WIDTH * DAYS_IN_WEEK + 1))
                .append(System.lineSeparator());
        calendarView.append(STARTING_BORDER);
        for (int dayRepresented = 0; dayRepresented < DAYS_IN_WEEK; dayRepresented++) {
            calendarView.append(PAD.repeat(CONTENT_WIDTH - days[dayRepresented].length()));
            calendarView.append(ANSI_CYAN).append(days[dayRepresented]).append(ANSI_RESET).append(BORDER);
        }
        calendarView.append(System.lineSeparator());

        assertEquals(testBuilder.toString(), calendarView.toString());
    }

    @Test
    public void testCalendarTitle() {
        testCalendarCommand.calibrateCalendar(testLocalDate, testCalendar);
        testCalendarCommand.addCalendarTitle(testCalendar, testBuilder);

        String testString = ANSI_RED + "Assignments are represented in red" + ANSI_RESET + System.lineSeparator();
        testString += ANSI_GREEN + "Events are represented in green" + ANSI_RESET + System.lineSeparator();
        String dayFormat = new SimpleDateFormat("MMMM YYYY").format(testCalendar.getTime());
        testString += ANSI_YELLOW + dayFormat + ANSI_RESET + System.lineSeparator();

        assertEquals(testBuilder.toString(), testString);
    }

    @Test
    public void testDuplicateRepeatEvents() {
        ArrayList<Task> resultList = testCalendarCommand.getTasksByYearMonth(testLocalDate, testTaskList);
        ArrayList<Task> resultTaskList = testCalendarCommand
                .duplicateRepeatEvents(YearMonth.from(testLocalDate).atEndOfMonth(), resultList);
        assertTrue(resultTaskList.size() > resultList.size());
        assertEquals(resultTaskList.size(), resultList.size()
                + testLocalDate.until(YearMonth.from(testLocalDate).atEndOfMonth()).getDays()
                / testCaseRepeatDaily.getNumOfPeriod());
    }

    @Test
    public void testAddRepeatEvent_Daily() {
        ArrayList<Task> resultList = testCalendarCommand.getTasksByYearMonth(testLocalDate, testTaskList);
        LocalDate endOfMonth = YearMonth.from(testLocalDate).atEndOfMonth();
        ArrayList<Task> finalTaskList = new ArrayList<>();
        for (Task task : resultList) {
            if (task instanceof RepeatEvent && task.equals(testCaseRepeatDaily)) {
                testCalendarCommand.addRepeatEvents(endOfMonth, finalTaskList, testCaseRepeatDaily);
            }
        }
        assertEquals(finalTaskList.size(), testLocalDate.until(endOfMonth).getDays()
                / testCaseRepeatDaily.getNumOfPeriod());
    }

    @Test
    public void testAddRepeatEvent_Weekly() {
        LocalDate endOfMonth = YearMonth.from(testRepeatLocalDate).atEndOfMonth();
        ArrayList<Task> resultList = new ArrayList<>();

        testCalendarCommand.addRepeatEvents(endOfMonth, resultList, testCaseRepeatWeekly);
        assertEquals(resultList.size(), testLocalDate.until(endOfMonth).getDays()
                / (testCaseRepeatWeekly.getNumOfPeriod() * DAYS_IN_WEEK));
    }

    @Test
    public void testAddRepeatEvent_Monthly_Yearly() {
        LocalDate endOfMonth = YearMonth.from(testRepeatLocalDate).atEndOfMonth();
        ArrayList<Task> resultList = new ArrayList<>();
        testCalendarCommand.addRepeatEvents(endOfMonth, resultList, testCaseRepeatMonthly);
        assertEquals(resultList.size(), 0);

    }

    @Test
    public void testAddRepeatEvent_Yearly() {
        LocalDate endOfMonth = YearMonth.from(testRepeatLocalDate).atEndOfMonth();
        ArrayList<Task> resultList = new ArrayList<>();
        testCalendarCommand.addRepeatEvents(endOfMonth, resultList, testCaseRepeatYearly);
        assertEquals(resultList.size(), 0);
    }

    @Test
    public void testAppendTaskToView() {
        ArrayList<Task> emptyArrayList = new ArrayList<>();
        testCalendarCommand.appendTaskToView(emptyArrayList, testBuilder, 2, 3);
        assertEquals(testBuilder.toString(), PAD.repeat(EMPTY_BOX_PADDING) + BORDER);

        ArrayList<Task> resultList = testCalendarCommand.getTasksByYearMonth(testLocalDate, testTaskList);
        testBuilder = new StringBuilder();
        testCalendarCommand.appendTaskToView(resultList, testBuilder, 5, 1);
        String test2 = PAD.repeat(MIDDLE_JUSTIFIED_WIDTH_PADDING) + MORE_TASK_INDICATOR
                + PAD.repeat(CONTENT_WIDTH - MIDDLE_JUSTIFIED_WIDTH_PADDING - 5) + BORDER;
        assertEquals(test2, testBuilder.toString());
    }

    @Test
    public void testTaskNotShown() {
        testCalendarCommand.addTaskNotShownIndicator(testBuilder);
        String test = PAD.repeat(MIDDLE_JUSTIFIED_WIDTH_PADDING)
                + MORE_TASK_INDICATOR + PAD.repeat(CONTENT_WIDTH - MIDDLE_JUSTIFIED_WIDTH_PADDING - 5)
                + BORDER;
        assertEquals(test, testBuilder.toString());
    }
}
