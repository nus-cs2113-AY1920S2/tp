package command;

import common.Messages;
import seedu.atas.Parser;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

//@@author joelczk
public class SearchCommand extends Command {
    public static final String COMMAND_WORD = "search";
    public static final String COMMAND_USAGE = "Search for tasks: search t/[TASK TYPE] n/[TASK NAME]";

    public static final String dCOMMAND_WORD = "searchd";
    public static final String dCOMMAND_USAGE = "Search for tasks according to date: "
            + "searchd t/[TASK TYPE] n/[TASK NAME] d/[DD/MM/YY]";

    protected static String CURRENT_COMMAND_WORD = "";
    protected static String CURRENT_COMMAND_USAGE = "";
    protected static final String allTasks = "all";
    protected static final String eventTasks = "event";
    protected static final String assignmentTasks = "assignment";
    protected ArrayList<Integer> storeIndex;
    protected String taskType;
    protected String searchParam;
    protected LocalDate date;

    /**
     * Constructor for search and searchd command.
     * @param searchParam search query that has to be searched
     * @param taskType type of task to search through
     * @param date date to find search query
     */
    public SearchCommand(String searchParam, String taskType, LocalDate date) {
        this.searchParam = searchParam.toLowerCase();
        this.taskType = taskType;
        storeIndex = new ArrayList<>();
        if (date == null) {
            CURRENT_COMMAND_WORD = COMMAND_WORD;
            CURRENT_COMMAND_USAGE = COMMAND_USAGE;
        } else {
            this.date = date;
            CURRENT_COMMAND_WORD = dCOMMAND_WORD;
            CURRENT_COMMAND_USAGE = dCOMMAND_USAGE;
        }
    }

    /**
     *  Loops through the task list to find all tasks that matches search query for search command.
     * @param tasks task list containing all tasks
     * @param results ArrayList storing the results
     * @return ArrayList containing results that match the search query
     */
    private ArrayList<Task> loopArrayNoDateAllTasks(ArrayList<Task> tasks, ArrayList<Task> results) {
        int index = 1;
        for (Task task: tasks) {
            if (task.getName().toLowerCase().contains(searchParam)) {
                results.add(task);
                storeIndex.add(index);
            }
            index++;
        }
        return results;
    }

    /**
     * Loops through the task list to find all tasks that match the search query and date for searchd command.
     * @param tasks task list containing all tasks
     * @param results ArrayList storing the results
     * @param date query date
     * @return ArrayList containing results that match the search query and the date
     */
    private ArrayList<Task> loopArrayWithDateAllTasks(ArrayList<Task> tasks, ArrayList<Task> results, LocalDate date) {
        int index = 1;
        for (Task task: tasks) {
            if (task.getName().toLowerCase().contains(searchParam) && task.getDate().equals(date)) {
                results.add(task);
                storeIndex.add(index);
            }
            index++;
        }
        return results;
    }

    /**
     * Loops through the task list to find the Assignment or Event objects that match the search query.
     * @param tasks task list containing all the tasks
     * @param results ArrayList storing the results
     * @return ArrayList storing Assignments objects that match the search query
     */
    private ArrayList<Task> loopArrayNoDateEventAssignments(HashMap<Task, Integer> tasks, ArrayList<Task> results) {
        for (Map.Entry<Task,Integer> entry: tasks.entrySet()) {
            Task task = entry.getKey();
            if (task.getName().toLowerCase().contains(searchParam)) {
                results.add(task);
                storeIndex.add(tasks.get(task));
            }
        }
        return results;
    }

    /**
     * Loops through the task list to find Assignment or Event objects that match the search query and the date.
     * @param tasks task list containing all the tasks
     * @param results ArrayList storing all the results
     * @param date query date
     * @return ArrayList storing Assignment objects that match the search query and date
     */
    private ArrayList<Task> loopArrayWithDateEventAssignments(HashMap<Task, Integer> tasks, ArrayList<Task> results,
                                                              LocalDate date) {
        for (Map.Entry<Task,Integer> entry: tasks.entrySet()) {
            Task task = entry.getKey();
            if (task.getName().toLowerCase().contains(searchParam) && task.getDate().equals(date)) {
                results.add(task);
                storeIndex.add(tasks.get(task));
            }
        }
        return results;
    }

    /**
     * Returns an ArrayList of all the Events objects that matches the search query.
     * @param taskList TaskList object containing all the tasks
     * @return ArrayList of all Event objects that match the search query
     */
    private ArrayList<Task> getSearchQueryAllTasks(TaskList taskList, LocalDate date) {
        ArrayList<Task> tasks = taskList.getTaskArray();
        ArrayList<Task> results = new ArrayList<>();
        if (date == null) {
            results = loopArrayNoDateAllTasks(tasks,results);
        } else {
            results = loopArrayWithDateAllTasks(tasks, results, date);
        }
        return results;
    }

    /**
     * Helper function to get all the results of search query for Event and Assignment objects.
     * @param date date of search query
     * @param eventsOrAssignments LinkedHashMap for Event or Assignment object
     * @return ArrayList containing all the results
     */
    private ArrayList<Task> getEventOrAssignmentResults(LocalDate date, LinkedHashMap<Task, Integer>
            eventsOrAssignments) {
        ArrayList<Task> results = new ArrayList<>();
        if (date == null) {
            results = loopArrayNoDateEventAssignments(eventsOrAssignments, results);
        } else {
            results = loopArrayWithDateEventAssignments(eventsOrAssignments, results, date);
        }
        return results;
    }

    /**
     * Returns an ArrayList of all Event objects that matches the search query.
     * @param taskList TaskList object containing all the tasks
     * @return ArrayList of all Event objects that matches the search query
     */
    private ArrayList<Task> getSearchQueryEvents(TaskList taskList, LocalDate date) {
        LinkedHashMap<Task, Integer> events = taskList.getEventsHashMap();
        assert events.size() == taskList.getEventsHashMap().size();
        return getEventOrAssignmentResults(date, events);
    }

    /**
     * Returns an ArrayList of all Assignment objects that matches the search query.
     * @param taskList TaskList objects containing all assignment tasks
     * @return ArrayList of all Assignment objects that matches the search query
     */
    private ArrayList<Task> getSearchQueryAssignments(TaskList taskList, LocalDate date) {
        LinkedHashMap<Task, Integer> assignments = taskList.getAssignmentsHashMap();
        assert assignments.size() == taskList.getAssignmentsHashMap().size();
        return getEventOrAssignmentResults(date,assignments);
    }

    /**
     * Returns list of search queries.
     * @param results ArrayList containing the results of search query
     * @return ArrayList of search queries
     */
    private String searchList(ArrayList<Task> results) {
        assert results.size() > 0;
        int position = 0;
        StringBuilder searchString = new StringBuilder();
        searchString.append(Messages.SEARCH_SUCCESS_MESSAGE);
        searchString.append(System.lineSeparator());
        for (Task task: results) {
            searchString.append(String.format("%3d.%s", storeIndex.get(position), task.toString()));
            searchString.append(System.lineSeparator());
            position++;
        }
        return searchString.toString();
    }

    /**
     * Returns String format of search queries.
     * @param results ArrayList of the results of search queries.
     * @return String format of search queries
     */
    private String resultsList(ArrayList<Task> results) {
        if (results.size() == 0) {
            return (Messages.EMPTY_SEARCH_RESULTS_ERROR);
        } else {
            return (searchList(results));
        }
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui) {
        if (taskList.getListSize() == 0) {
            return new CommandResult(Messages.EMPTY_TASKLIST_MESSAGE);
        }
        assert taskList.getListSize() > 0;
        switch (taskType) {
        case allTasks:
            ArrayList<Task> results = getSearchQueryAllTasks(taskList, date);
            return new CommandResult(resultsList(results));
        case eventTasks:
            ArrayList<Task> eventResults = getSearchQueryEvents(taskList, date);
            return new CommandResult(resultsList(eventResults));
        case assignmentTasks:
            ArrayList<Task> assignmentResults = getSearchQueryAssignments(taskList, date);
            return new CommandResult(resultsList(assignmentResults));
        default:
            return new CommandResult(String.format(Messages.INCORRECT_ARGUMENT_ERROR,
                    Parser.capitalize(CURRENT_COMMAND_WORD), CURRENT_COMMAND_USAGE));
        }
    }
}
