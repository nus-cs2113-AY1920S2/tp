package command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.atas.Parser;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Assignment;
import tasks.Event;
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

public class CalendarCommandTest {
    public static final DateTimeFormatter INPUT_DATE_ONLY_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yy");
    private static CalendarCommand testCalendarCommand;
    private static TaskList testTaskList;
    private static Ui testUi;
    private static LocalDate testLocalDate;
    private static LocalDate testLocalDate1;
    private static LocalDate testLocalDate2;
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

    // ANSI background colour scheme
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private static final String BORDER = ANSI_PURPLE + "*" + ANSI_RESET;
    private static final String STARTING_BORDER = ANSI_PURPLE + "*" + ANSI_RESET;
    private static final String PAD = " ";
    private static Assignment testCaseTwo = null;
    private static Event testCaseFour = null;

    // Calendar dimensions
    private static final int MAX_CALENDAR_ROWS = 30;
    private static final int CALENDAR_BOX_HEIGHT = 6;
    private static final int DAYS_IN_WEEK = 7;

    // sizing of each Calendar box
    private static final int MAX_CALENDAR_BOX_WIDTH = 20;
    private static final int DATE_PADDING_WIDTH = MAX_CALENDAR_BOX_WIDTH - 3;
    private static final int EMPTY_BOX_PADDING = MAX_CALENDAR_BOX_WIDTH - 1;
    private static final int CONTENT_WIDTH = MAX_CALENDAR_BOX_WIDTH - 1;

    /**
     * Setup Commands, Calendar before each test.
     */
    @BeforeEach
    public void setup() {
        testTaskList = new TaskList();
        testUi = new Ui();
        testBuilder = new StringBuilder();

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

        final Assignment testCaseOne = new Assignment("Assignment 3", "CS2102", testDateTime1, " ");
        testCaseTwo = new Assignment("OP1", "CS2101", testDateTime3, "15%");
        final Event testCaseThree = new Event("midterms", "MPSH1A", testDateTime1, testDateTime2, " ");
        testCaseFour = new Event("Countdown", "TimeSquare", testDateTime3, testDateTime4, "new year new me");

        testCalendar = Calendar.getInstance();
        String testDate = "01/01/20";
        String testDate1 = "13/03/20";
        String testDate2 = "02/02/20";
        testLocalDate = LocalDate.parse(testDate, INPUT_DATE_ONLY_FORMAT);
        testLocalDate1 = LocalDate.parse(testDate1, INPUT_DATE_ONLY_FORMAT);
        testLocalDate2 = LocalDate.parse(testDate2, INPUT_DATE_ONLY_FORMAT);
        testCalendarCommand = new CalendarCommand(testLocalDate);

        // set to repeat every 14 days in the month of Jan 2020, will only see it thrice in month of Jan
        // inclusive of the original event
        testCaseFour.setRepeat(14, RepeatCommand.DAILY_ICON);
        testTaskList.addTask(testCaseOne);
        testTaskList.addTask(testCaseTwo);
        testTaskList.addTask(testCaseThree);
        testTaskList.addTask(testCaseFour);
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
        ArrayList<Task> resultList = testCalendarCommand.getTasksByYearMonth(testLocalDate, testTaskList);
        assertEquals(2, resultList.size());
        ArrayList<Task> resultList1 = testCalendarCommand.getTasksByYearMonth(testLocalDate1, testTaskList);
        assertEquals(3, resultList1.size());
        ArrayList<Task> resultList2 = testCalendarCommand.getTasksByYearMonth(testLocalDate2, testTaskList);
        assertEquals(1, resultList2.size());
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
        assertTrue(arrayList.size() == 1);
        String taskDetails = testCaseTwo.getTime().format(Parser.PRINT_TIME_FORMAT) + testCaseTwo.getName();
        if (taskDetails.length() > CONTENT_WIDTH) {
            taskDetails = taskDetails.substring(0, CONTENT_WIDTH);
        }
        String testString = ANSI_RED + taskDetails + ANSI_RESET
                + PAD.repeat((CONTENT_WIDTH - taskDetails.length())) + BORDER;
        assertEquals(testBuilder.toString(), testString);
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
        final int testDayDoubleDigit = 30;
        testCalendarCommand.addCalendarDate(testBuilder, testDaySingleDigit);
        String testString = PAD.repeat(DATE_PADDING_WIDTH) + ANSI_CYAN
                + testDaySingleDigit + ANSI_RESET + PAD + BORDER;
        assertEquals(testString, testBuilder.toString());

        // reset string builder
        testBuilder.delete(0, testBuilder.length());
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
        testCalendarCommand.addCalendarBorder(calendarView);
        testCalendarCommand.addCalendarStartBorder(calendarView);
        for (int dayRepresented = 0; dayRepresented < DAYS_IN_WEEK; dayRepresented++) {
            calendarView.append(PAD.repeat(CONTENT_WIDTH - days[dayRepresented].length()));
            calendarView.append(ANSI_CYAN).append(days[dayRepresented]).append(ANSI_RESET).append(BORDER);
        }
        testCalendarCommand.addCalendarNewLine(calendarView);

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
                + testLocalDate.until(YearMonth.from(testLocalDate).atEndOfMonth()).getDays() / 14);
    }

    @Test
    public void testAddRepeatEvent() {
        ArrayList<Task> resultList = testCalendarCommand.getTasksByYearMonth(testLocalDate, testTaskList);
        LocalDate endOfMonth = YearMonth.from(testLocalDate).atEndOfMonth();
        ArrayList<Task> finalTaskList = new ArrayList<>();
        for (Task task : resultList) {
            if (task instanceof Event && ((Event) task).getIsRepeat() && ((Event)task).equals(testCaseFour)) {
                testCalendarCommand.addRepeatEvents(endOfMonth, finalTaskList, testCaseFour);
            }
        }
        assertEquals(finalTaskList.size(), testLocalDate.until(endOfMonth).getDays() / 14);
    }
}
