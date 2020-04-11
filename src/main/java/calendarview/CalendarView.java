package calendarview;

import exception.CalendarException;
import task.Task;
import ui.Ui;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static ui.Constants.ASSERT_FIRST_DAY_MISMATCH;
import static ui.Constants.ASSERT_MONTH_NOT_SET;
import static ui.Constants.ASSERT_YEAR_NOT_SET;
import static ui.Constants.BACK_IN_MAIN_INTERFACE;
import static ui.Constants.BYE;
import static ui.Constants.CALENDAR_HAS_BEEN_PRINTED_SUCCESSFULLY;
import static ui.Constants.COL;
import static ui.Constants.COMMA;
import static ui.Constants.DAYS;
import static ui.Constants.DEC;
import static ui.Constants.DIVIDER;
import static ui.Constants.ENTER_DESIRED_MONTH;
import static ui.Constants.INVALID_MONTH;
import static ui.Constants.INVALID_MONTH_LOGGER;
import static ui.Constants.INVALID_MONTH_RANGE;
import static ui.Constants.INVALID_MONTH_RANGE_LOGGER;
import static ui.Constants.INVALID_YEAR;
import static ui.Constants.INVALID_YEAR_LOGGER;
import static ui.Constants.JAN;
import static ui.Constants.MAP_CANNOT_BE_EMPTY;
import static ui.Constants.MAX_COL;
import static ui.Constants.MAX_LIST_SIZE;
import static ui.Constants.MAX_ROW;
import static ui.Constants.MULTIPLE_WHITE_SPACES;
import static ui.Constants.NON_INTEGER_YEAR;
import static ui.Constants.NON_INTEGER_YEAR_LOGGER;
import static ui.Constants.NOW;
import static ui.Constants.ONLY_MONTH_AND_YEAR;
import static ui.Constants.ONLY_MONTH_AND_YEAR_LOGGER;
import static ui.Constants.PADDING;
import static ui.Constants.PADDING1;
import static ui.Constants.PADDING2;
import static ui.Constants.PADDING3;
import static ui.Constants.PAST_MONTH;
import static ui.Constants.PAST_MONTH_LOGGER;
import static ui.Constants.SPACE;
import static ui.Constants.SPACES;
import static ui.Constants.TASKS_FOR;
import static ui.Constants.USER_EXITING_CALENDAR_VIEW;

//@@author NizarMohd
/**
 * This class deals with converting Tasks into Calendar view.
 */
public class CalendarView {
    private HashMap<Integer, LinkedList<Task>> map;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static boolean exit = false;
    private  LocalDate firstDay;
    public static int month = -1;
    public static int year = -1;
    private  int lastDay;
    private  int[] daysOfMonths = new int[35];
    private static Ui ui;

    /**
     * This constructs the class.
     * @param inUi allows for interaction with the user.
     */
    public CalendarView(Ui inUi)  {
        ui = inUi;
        getInput();                                     // year and month will be set if user enters correctly.
        if (year != -1 && month != -1) {       // check if year and month is set
            this.setFirstDay();
            this.setLastDay();
            processDates();
        }
    }

    /**
     * This method retrieves the month set by the user.
     * @return int value associated to the month entered.
     */
    public int getMonth() {
        return month;
    }

    /**
     * This method retrieves the year set by the user.
     * @return int value associated to the year of the user.
     */
    public int getYear() {
        return year;
    }

    /**
     * This sets the mapping between days to Tasks.
     * @param inMap this is the incoming created by CalendarCommand.
     */
    public void setMap(HashMap<Integer, LinkedList<Task>> inMap) {
        this.map = inMap;
    }


    /**
     * This method sets the first day val. This prevent double error messages when user enters invalid command.
     */
    private void setFirstDay() {
        assert year != -1 : ASSERT_YEAR_NOT_SET;
        assert month != -1 : ASSERT_MONTH_NOT_SET;
        this.firstDay = YearMonth.of(year, month).atDay(1);
    }

    /**
     * This method sets the last day val. This prevent double error messages when user enters invalid command.
     */
    private void setLastDay() {
        assert this.firstDay.getDayOfMonth() == 1 : ASSERT_FIRST_DAY_MISMATCH;
        this.lastDay = this.firstDay.getMonth().length(this.firstDay.isLeapYear());
    }


    /**
     * This print the days header.
     */
    public void printDays() {
        ui.printOut(DIVIDER, true);
        for (int i = 0; i < MAX_COL; i++) {
            ui.printOut(COL + DAYS[i] + PADDING1, false);
        }
        ui.printOut(COL, true);
        ui.printOut(DIVIDER, true);
    }

    /**
     * This process the dates of the month into its supposed box.
     */
    public void processDates() {
        int index = this.firstDay.getDayOfWeek().getValue();
        index = index % MAX_COL;
        for (int i = 1; i <= this.lastDay; i++) {
            if (index >= 35) {
                index = 0;
            }
            this.daysOfMonths[index] = i;
            index++;
        }
    }

    private LinkedList<Task> processList(int day) {
        LinkedList<Task> tasks = this.map.get(day);
        if (tasks.size() > MAX_LIST_SIZE) {
            LinkedList<Task> temp = new LinkedList<>();
            for (int i = 0; i < MAX_LIST_SIZE; i++) {
                temp.add(tasks.get(i));
            }
            this.map.get(day).clear();
            this.map.get(day).addAll(temp);
            assert this.map.get(day) != null : MAP_CANNOT_BE_EMPTY;
        }
        return this.map.get(day);
    }


    /**
     * This prints the content to be displayed in the box.
     * @param row this is the row the box is at.
     * @param i this is the height of the box that is currently being printed.
     * @param j this is the day which the box is at.
     */
    public void printContent(int row, int i, int j) {
        int index = row * MAX_COL + j;
        int day = daysOfMonths[index];
        if (i == 0) {
            if (this.daysOfMonths[index] == 0) {
                ui.printOut(COL + SPACES + PADDING, false);
            } else if (this.daysOfMonths[index] > 9) {
                ui.printOut(COL + SPACES + PADDING3 + this.daysOfMonths[index], false);
            } else {
                ui.printOut(COL + SPACES + PADDING2 + this.daysOfMonths[index], false);
            }
        } else {
            if (map.containsKey(day)) {
                if (map.get(day).isEmpty()) {
                    ui.printOut(COL + SPACES + PADDING, false);
                } else {
                    LinkedList<Task> tasks = processList(day);
                    Task task = tasks.get(0);
                    tasks.removeFirst();
                    ui.printOut(COL + SPACE + task.getCalendarTaskDetails() + SPACE, false);
                }
            } else {
                ui.printOut(COL + SPACES + PADDING, false);
            }
        }
    }

    /**
     * This method prints the box itself.
     * @param row this is the row that the box is currently being printed.
     */
    public void printBox(int row) {
        for (int i = 0; i < MAX_ROW; i++) {
            for (int j = 0; j < MAX_COL; j++) {
                printContent(row, i, j);
            }
            ui.printOut(COL, true);
        }
        ui.printOut(DIVIDER, true);
    }

    /**
     * This prints the month which the calendar is being printed.
     */
    public void printCurrentMonth() {
        ui.printOut(TASKS_FOR + this.firstDay.getMonth().toString() + COMMA +  this.firstDay.getYear(),
                true);
    }

    /**
     * This checks if the month entered by user is correct.
     * @param in this is the input by user.
     * @throws CalendarException this is the exception thrown when user entered the month wrongly.
     */
    public static void setMonth(String in) throws CalendarException {
        try {
            int inMonth = Integer.parseInt(in);
            if (inMonth < JAN || inMonth > DEC) {
                LOGGER.log(Level.INFO, INVALID_MONTH_RANGE_LOGGER);
                throw new CalendarException(INVALID_MONTH_RANGE);
            }
            month = inMonth;
        } catch (NumberFormatException e) {
            LOGGER.log(Level.INFO, INVALID_MONTH_LOGGER);
            throw new CalendarException(INVALID_MONTH);
        }
    }

    /**
     * This method checks the year entered by the user.
     * @param in This is the input entered by the user.
     * @throws CalendarException is thrown when user enters the year wrongly.
     */
    public static void setYear(String in) throws CalendarException {
        try {
            int inYear = Integer.parseInt(in);
            int thisYear = LocalDate.now().getYear();
            if (inYear < thisYear) {
                LOGGER.log(Level.INFO, INVALID_YEAR_LOGGER);
                throw new CalendarException(INVALID_YEAR);
            }
            year = inYear;
        } catch (NumberFormatException e) {
            LOGGER.log(Level.INFO, NON_INTEGER_YEAR_LOGGER);
            throw new CalendarException(NON_INTEGER_YEAR);
        }
    }

    /**
     * This method checks if the input length is 2, else it will throw an exception.
     * @param input this is the input entered by the user.
     * @throws CalendarException if user enters input of length more than 2.
     */
    public static void checkInputLength(String[] input) throws CalendarException {
        if (input.length != 2) {
            LOGGER.log(Level.INFO, ONLY_MONTH_AND_YEAR_LOGGER);
            throw new CalendarException(ONLY_MONTH_AND_YEAR);
        }
    }

    /**
     * This method will validate month and year input sequentially and prints an error message based on the combination
     * of wrong input pattern.
     * @param input this is the input entered by the user for the month and year.
     * @return a string of error message, if user enters input wrongly, else return null.
     */
    public static String setMonthAndYear(String[] input) {
        StringBuilder errMessage = null;
        try {                               // try setting month
            setMonth(input[0]);
        } catch (CalendarException e) {         // if error, catch the message
            errMessage = new StringBuilder(e.getMessage());
        }

        try {                                    // try setting year
            setYear(input[1]);
        } catch (CalendarException e1) {        // if error, catch message also.
            if (errMessage != null) {
                errMessage.append(" Also, ").append(e1.getMessage());
            } else {
                errMessage = new StringBuilder(e1.getMessage());
            }
        }
        if (errMessage != null) {
            return errMessage.toString();
        } else {
            return null;
        }
    }

    /**
     * This method checks if the month entered is current or future.
     * @throws CalendarException if month entered is not current and not future.
     */
    public static void checkIfPast() throws CalendarException {
        if (month == -1 || year == -1) {     // if month or year not set
            return;
        }
        int thisYear = LocalDate.now().getYear();
        if (year == thisYear) {
            int thisMonth = LocalDate.now().getMonthValue();
            if (month < thisMonth) {
                LOGGER.log(Level.INFO, PAST_MONTH_LOGGER);
                throw new CalendarException(PAST_MONTH);
            }
        }
    }


    /**
     * This method gets the input from the user.
     */
    public static void getInput() {
        ui.printLine();
        ui.printMessage(ENTER_DESIRED_MONTH);
        ui.printLine();
        boolean isWrongCommand = true;

        while (isWrongCommand) {
            String temp = ui.getUserIn().toLowerCase();
            String[] input = temp.split(MULTIPLE_WHITE_SPACES);
            if (input[0].equals(NOW)) {
                month = LocalDate.now().getMonthValue();
                year = LocalDate.now().getYear();
                isWrongCommand = false;
            } else if (input[0].equals(BYE)) {
                exit = true;
                isWrongCommand = false;
            } else {
                try {
                    checkInputLength(input);
                    String errMessage = setMonthAndYear(input);
                    checkIfPast();                                       // throws cal exception
                    if (errMessage == null) {
                        isWrongCommand = false;
                    } else {
                        ui.printLine();
                        ui.printMessage(errMessage);
                        ui.printLine();
                    }
                } catch (Exception e) {
                    ui.printLine();
                    ui.printMessage(e.getMessage());
                    ui.printLine();
                }
            }
        }
    }

    /**
     * This method prints the calendar.
     */
    public void printCalendar() {
        if (exit) {
            LOGGER.log(Level.INFO, USER_EXITING_CALENDAR_VIEW);
            ui.printLine();
            ui.printMessage(BACK_IN_MAIN_INTERFACE);
            return;
        }
        ui.printLine();
        printCurrentMonth();
        printDays();
        for (int i = 0; i < MAX_ROW; i++) {
            printBox(i);
        }
        LOGGER.log(Level.INFO, CALENDAR_HAS_BEEN_PRINTED_SUCCESSFULLY);
    }

}
