package command;

import calendarview.CalendarView;
import calendarview.TaskComparator;
import exception.MisuseOfCalendarCommandException;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

//@@author NizarMohd
/**
 * This class deals with the command used for Calendar View.
 */
public class CalendarCommand extends Command {

    public static final String YEAR_AND_MONTH_NOT_SET = "Year and Month not set!";

    /**
     * This constructs the command.
     * @param isOneWordCommand checks to see if the user entered the command correctly.
     * @throws Exception is thrown if the user entered the command wrongly.
     */
    public CalendarCommand(boolean isOneWordCommand) throws Exception {
        if (!isOneWordCommand) {
            throw new MisuseOfCalendarCommandException();
        }
    }


    /**
     * This method retrieves the mapping between the days to the current tasks for that month.
     * @param tasks this is the list of tasks in taskList.
     * @param thisMonth is the month that user wants to view
     * @param thisYear is the year, with respect to the month that user wants to view
     * @return a hashMap that maps the days to its designated tasks.
     */
    public static HashMap<Integer, LinkedList<Task>> checkExistingTasks(ArrayList<Task> tasks, int thisMonth,
                                                                        int thisYear) {
        HashMap<Integer, LinkedList<Task>> dayToTaskHashMap = new HashMap<>();
        for (Task task : tasks) {
            LocalDate date = task.getDate();
            if (date.getMonthValue() == thisMonth && date.getYear() == thisYear) {
                int day = date.getDayOfMonth();
                dayToTaskHashMap.computeIfAbsent(day, k -> new LinkedList<Task>());
                dayToTaskHashMap.get(day).add(task);
                dayToTaskHashMap.get(day).sort(new TaskComparator());
            }
        }
        return dayToTaskHashMap;
    }


    @Override
    public void executeCommand(TaskList taskList, Ui ui) {
        CalendarView c = new CalendarView(ui);
        int thisMonth = c.getMonth();
        int thisYear = c.getYear();
        assert thisMonth != -1 && thisYear != -1 : YEAR_AND_MONTH_NOT_SET;
        HashMap<Integer, LinkedList<Task>> map = checkExistingTasks(taskList.tasks, thisMonth, thisYear);
        c.setMap(map);
        c.printCalendar();
        ui.printLine();
    }
}
