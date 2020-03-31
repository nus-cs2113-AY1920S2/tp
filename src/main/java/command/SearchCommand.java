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
     * Constructor for search command.
     * @param searchParam search query that has to be searched
     * @param taskType type of task to search through
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
     * Returns an arrayList of all the tasks that matches the search query.
     * @param taskList taskList object containing all the tasks
     * @return arrayList of all tasks that match the search query
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
     * Returns an ArrayList of all event objects that matches the search query.
     * @param taskList taskList object containing all the tasks
     * @return ArrayList of all event objects that matches the search query
     */
    private ArrayList<Task> getSearchQueryEvents(TaskList taskList, LocalDate date) {
        LinkedHashMap<Task, Integer> events = taskList.getEventsHashMap();
        assert events.size() == taskList.getEventsHashMap().size();
        ArrayList<Task> results = new ArrayList<>();
        if (date == null) {
            results = loopArrayNoDateEventAssignments(events,results);
        } else {

            results = loopArrayWithDateEventAssignments(events, results, date);
        }
        return results;
    }

    /**
     * Returns an ArrayList of all assignments objects that matches the search query.
     * @param taskList taskList objects containing all assignment tasks
     * @return ArrayList of all assignment object that matches the search query
     */
    private ArrayList<Task> getSearchQueryAssignments(TaskList taskList, LocalDate date) {
        LinkedHashMap<Task, Integer> assignments = taskList.getAssignmentsHashMap();
        ArrayList<Task> results = new ArrayList<>();
        assert assignments.size() == taskList.getAssignmentsHashMap().size();
        if (date == null) {
            results = loopArrayNoDateEventAssignments(assignments,results);
        } else {
            results = loopArrayWithDateEventAssignments(assignments, results, date);
        }
        return results;
    }

    /**
     * Returns list of search queries.
     * @param results ArrayList containing the results of search query
     * @return list of search queries
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
