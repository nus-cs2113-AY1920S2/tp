package command;

import seedu.atas.Parser;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Assignment;
import tasks.Event;
import tasks.RepeatEvent;
import tasks.Task;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;

//@@author
public class CalendarCommand extends Command {
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
    private static final String MORE_TASK_INDICATOR = ANSI_CYAN + "....." + ANSI_RESET;

    // Calendar dimensions
    private static final int MAX_CALENDAR_ROWS = 30;
    private static final int CALENDAR_BOX_HEIGHT = 6;
    private static final int DAYS_IN_WEEK = 7;

    // sizing of each Calendar box
    private static final int MAX_CALENDAR_BOX_WIDTH = 20;
    private static final int DATE_PADDING_WIDTH = MAX_CALENDAR_BOX_WIDTH - 3;
    private static final int EMPTY_BOX_PADDING = MAX_CALENDAR_BOX_WIDTH - 1;
    private static final int CONTENT_WIDTH = MAX_CALENDAR_BOX_WIDTH - 1;
    private static final int MIDDLE_JUSTIFIED_WIDTH_PADDING = MAX_CALENDAR_BOX_WIDTH / 2 - 3;

    public static final String COMMAND_WORD = "calendar";
    public static final String COMMAND_USAGE = "Get a Calendar view: calendar d/[dd/MM/YY]";

    private LocalDate date;

    public CalendarCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes the Calendar command.
     *
     * @param taskList TaskList object that handles adding Task
     * @param ui       Ui object that interacts with user
     * @return CommandResult object with acknowledgment message
     */
    @Override
    public CommandResult execute(TaskList taskList, Ui ui) {
        String calendarView = buildMonthCalendar(date, taskList);
        return new CommandResult(calendarView);
    }

    /**
     * Build and return the calendar view in String format.
     * @param dateTime date provided to base calendar view on
     * @param taskList TaskList object that handles tasks operations
     * @return String object that contains the calendar view
     */
    private String buildMonthCalendar(LocalDate dateTime, TaskList taskList) {
        Calendar calendar = Calendar.getInstance();
        calibrateCalendar(dateTime, calendar);

        // Get calendar parameters
        final int year       = calendar.get(Calendar.YEAR);
        final int month      = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
        assert month == (dateTime.getMonthValue() - 1);
        final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        assert dayOfMonth == 1;
        final int startingDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // get day of week {1 = sunday, 7 = saturday}
        final int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        final int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
        final int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // maximum no. days in given month

        ArrayList<Task> monthlyTaskList = duplicateRepeatEvents(dateTime, getTasksByYearMonth(dateTime, taskList));

        StringBuilder calendarView = new StringBuilder();
        addCalendarTitle(calendar, calendarView);
        addCalendarLegend(calendarView);
        addCalendarBody(startingDayOfWeek, daysInMonth, monthlyTaskList, calendarView);

        return calendarView.toString();
    }

    /**
     * Sets the calendar to the given date.
     * @param dateTime user specified date to base calendar on
     * @param calendar Calendar object that calendar view is based of on
     */
    public void calibrateCalendar(LocalDate dateTime, Calendar calendar) {
        int givenDay = dateTime.getDayOfMonth();
        int givenMonth = dateTime.getMonthValue();
        int givenYear = dateTime.getYear();

        calendar.set(Calendar.YEAR, givenYear);
        calendar.set(Calendar.MONTH, givenMonth - 1); // month starts from 0 - 11
        calendar.set(Calendar.DATE, givenDay);

        // set the day to the first day of given month
        calendar.set(Calendar.DAY_OF_MONTH, 1);
    }

    /**
     * Gets an ArrayList of Tasks that falls within the given date.
     * @param dateTime dateTime object given by user
     * @param taskList TaskList object that handles tasks operations
     * @return ArrayList of tasks that falls within given date
     */
    public ArrayList<Task> getTasksByYearMonth(LocalDate dateTime, TaskList taskList) {
        YearMonth yearMonth = YearMonth.from(dateTime);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        LocalDate startOfMonth = yearMonth.atDay(1);

        return taskList.getTasksByRange(startOfMonth, endOfMonth);
    }

    /**
     * Add repeating Events to resultTaskList as a separate event for easier implementation of calendar.
     * @param dateTime LocalDate that is given
     * @param unrepeatedTaskList ArrayList of Tasks that contains task from TaskList
     * @return ArrayList of Tasks that contains duplicated tasks of repeat events
     */
    public ArrayList<Task> duplicateRepeatEvents(LocalDate dateTime, ArrayList<Task> unrepeatedTaskList) {
        ArrayList<Task> resultTaskList = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(dateTime);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();

        for (Task task : unrepeatedTaskList) {
            resultTaskList.add(task);
            if (task instanceof RepeatEvent) {
                addRepeatEvents(endOfMonth, resultTaskList, (RepeatEvent) task);
            }
        }
        return resultTaskList;
    }

    /**
     * Add repeating Event as separate Event to resultTaskList.
     * @param endOfMonth LocalDate that represents the last day of the month
     * @param resultTaskList ArrayList of Task that contains duplicated tasks of repeat events
     * @param event Event to repeat and add to resultTaskList
     */
    public void addRepeatEvents(LocalDate endOfMonth, ArrayList<Task> resultTaskList, RepeatEvent event) {
        int numOfPeriod = event.getNumOfPeriod();
        String typeOfPeriod = event.getTypeOfPeriod();
        LocalDate eventDate = event.getDateAndTime().toLocalDate();
        int daysToAdd = 0;

        switch (typeOfPeriod) {
        case RepeatCommand.DAILY_ICON:
            daysToAdd = numOfPeriod;
            for (int timesRepeated = 1; eventDate.plusDays(daysToAdd * timesRepeated).compareTo(endOfMonth) <= 0;
                 timesRepeated++) {
                resultTaskList.add(new Event(event.getName(), event.getLocation(),
                        event.getDateAndTime().plusDays(daysToAdd  * timesRepeated),
                        event.getEndDateAndTime().plusDays(daysToAdd * timesRepeated),
                        event.getComments()));
            }
            break;
        case RepeatCommand.WEEKLY_ICON:
            daysToAdd = numOfPeriod * DAYS_IN_WEEK;
            for (int timesRepeated = 1; eventDate.plusDays(daysToAdd * timesRepeated).compareTo(endOfMonth) <= 0;
                 timesRepeated++) {
                resultTaskList.add(new Event(event.getName(), event.getLocation(),
                        event.getDateAndTime().plusDays(daysToAdd * timesRepeated),
                        event.getEndDateAndTime().plusDays(daysToAdd * timesRepeated),
                        event.getComments()));
            }
            break;
        case RepeatCommand.MONTHLY_ICON:
        case RepeatCommand.YEARLY_ICON:
            break;
        default:
            assert false;
        }
    }

    /**
     * Formats and appends the calendar body to calendarView.
     * @param startingDayOfWeek the starting day of the first week of given month
     * @param daysInMonth maximum number of days in the given month
     * @param monthlyTaskList ArrayList of tasks that falls within the given month
     * @param calendarView StringBuilder object that is used to format the calendar view
     */
    private void addCalendarBody(int startingDayOfWeek, int daysInMonth,
                                 ArrayList<Task> monthlyTaskList, StringBuilder calendarView) {
        for (int calendarRow = 0; calendarRow <= MAX_CALENDAR_ROWS; calendarRow++) {
            if (calendarRow % CALENDAR_BOX_HEIGHT == 0) {
                addCalendarBorder(calendarView);
                continue;
            }

            // starting border for each calendar row
            addCalendarStartBorder(calendarView);

            for (int calendarBoxIndex = 1; calendarBoxIndex <= DAYS_IN_WEEK; calendarBoxIndex++) {
                int currentDayRepresented = Math.floorDiv(calendarRow, CALENDAR_BOX_HEIGHT)
                        * DAYS_IN_WEEK + calendarBoxIndex - startingDayOfWeek + 1;

                // print empty calendar box
                if (currentDayRepresented <= 0 || currentDayRepresented > daysInMonth) {
                    addEmptyCalendarBody(calendarView);
                    continue;
                }

                // print date of calendar
                if (calendarRow % CALENDAR_BOX_HEIGHT == 1) {
                    addCalendarDate(calendarView, currentDayRepresented);
                    continue;
                }

                boolean hasPrintedTask = false;
                for (Task task : monthlyTaskList) {
                    if (task.getDate().getDayOfMonth() == currentDayRepresented) {
                        if (calendarRow % CALENDAR_BOX_HEIGHT == 5) {
                            addTaskNotShownIndicator(calendarView);
                        } else {
                            hasPrintedTask = true;
                            addTaskToCalendar(monthlyTaskList, calendarView, task);
                        }
                        break;
                    }
                }

                if (!hasPrintedTask) {
                    addEmptyCalendarBody(calendarView);
                }
            }
            addCalendarNewLine(calendarView);
        }
    }

    /**
     * Adds an indicator to indicate that there are Tasks are not shown.
     * @param calendarView StringBuilder object that is used to format the calendar view
     */
    public void addTaskNotShownIndicator(StringBuilder calendarView) {
        calendarView.append(PAD.repeat(MIDDLE_JUSTIFIED_WIDTH_PADDING))
                .append(MORE_TASK_INDICATOR)
                .append(PAD.repeat(CONTENT_WIDTH - MIDDLE_JUSTIFIED_WIDTH_PADDING - 5))
                .append(BORDER);
    }

    /**
     * Add a newline to calendar view.
     * @param calendarView StringBuilder object that is used to format the calendar view
     */
    public void addCalendarNewLine(StringBuilder calendarView) {
        calendarView.append(System.lineSeparator());
    }

    /**
     * Appends and formats Tasks to the calendarView.
     * @param monthlyTaskList ArrayList of tasks that falls within the given month
     * @param calendarView StringBuilder object that is used to format the calendar view
     * @param task task that is being appended to calendarView
     */
    public void addTaskToCalendar(ArrayList<Task> monthlyTaskList, StringBuilder calendarView, Task task) {
        final int taskListSize = monthlyTaskList.size();
        String taskDetails = task.getTime().format(Parser.PRINT_TIME_FORMAT) + task.getName();
        if (taskDetails.length() > CONTENT_WIDTH) {
            taskDetails = taskDetails.substring(0, CONTENT_WIDTH);
        }

        if (task instanceof Assignment) {
            calendarView.append(ANSI_RED).append(taskDetails).append(ANSI_RESET);
        } else {
            calendarView.append(ANSI_GREEN).append(taskDetails).append(ANSI_RESET);
        }
        monthlyTaskList.remove(task);
        assert monthlyTaskList.size() == taskListSize - 1;
        calendarView.append(PAD.repeat(CONTENT_WIDTH - taskDetails.length())).append(BORDER);
    }

    /**
     * Appends a starting border to the Calendar.
     * @param calendarView StringBuilder object that is used to format the calendar view
     */
    public void addCalendarStartBorder(StringBuilder calendarView) {
        calendarView.append(STARTING_BORDER);
    }

    /**
     * Appends an empty calendar slot to the calendarView.
     * @param calendarView StringBuilder object that is used to format the calendar view
     */
    public void addEmptyCalendarBody(StringBuilder calendarView) {
        calendarView.append(PAD.repeat(EMPTY_BOX_PADDING)).append(BORDER);
    }

    /**
     * Appends and formats the date to add to calendarView.
     * @param calendarView StringBuilder object that is used to format the calendar view
     * @param currentDayRepresented day of month to append to calendarView
     */
    public void addCalendarDate(StringBuilder calendarView, int currentDayRepresented) {
        calendarView.append(PAD.repeat(DATE_PADDING_WIDTH)).append(ANSI_CYAN)
                .append(currentDayRepresented).append(ANSI_RESET);

        if (currentDayRepresented < 10) {
            calendarView.append(PAD + BORDER);
        } else {
            calendarView.append(BORDER);
        }
    }

    /**
     * Appends a legend for the calendar.
     * @param calendarView StringBuilder object that is used to format the calendar view
     */
    public void addCalendarLegend(StringBuilder calendarView) {
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        addCalendarBorder(calendarView);

        addCalendarStartBorder(calendarView);
        for (int dayRepresented = 0; dayRepresented < DAYS_IN_WEEK; dayRepresented++) {
            calendarView.append(PAD.repeat(CONTENT_WIDTH - days[dayRepresented].length()));
            calendarView.append(ANSI_CYAN).append(days[dayRepresented]).append(ANSI_RESET).append(BORDER);
        }
        addCalendarNewLine(calendarView);
    }

    /**
     * Appends a title to the calendar.
     * @param calendar Calendar object that calendar view is based of on
     * @param calendarView StringBuilder object that is used to format the calendar view
     */
    public void addCalendarTitle(Calendar calendar, StringBuilder calendarView) {
        calendarView.append(ANSI_RED + "Assignments are represented in red" + ANSI_RESET)
                .append(System.lineSeparator());
        calendarView.append(ANSI_GREEN + "Events are represented in green" + ANSI_RESET)
                .append(System.lineSeparator());
        calendarView.append(ANSI_YELLOW).append(new SimpleDateFormat("MMMM YYYY").format(calendar.getTime()))
                .append(ANSI_RESET).append(System.lineSeparator());
    }

    /**
     * Appends a horizontal border for the calendarView.
     * @param calendarView StringBuilder object that is used to format the calendar view
     */
    public void addCalendarBorder(StringBuilder calendarView) {
        calendarView.append(BORDER.repeat(MAX_CALENDAR_BOX_WIDTH * DAYS_IN_WEEK + 1))
                .append(System.lineSeparator());
    }
}
