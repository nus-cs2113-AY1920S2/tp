package command;

import common.Messages;
import seedu.atas.Parser;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class SearchdCommand extends Command {
    public static final String COMMAND_WORD = "searchd";
<<<<<<< HEAD
    public static final String COMMAND_USAGE = "Search for tasks according to date: "
            + "search t/[TASK TYPE] n/[TASK NAME] d/[DD/MM/YY]";
=======
    public static final String COMMAND_USAGE = "Search for tasks according to date: " +
            "search t/[TASK TYPE] n/[TASK NAME] d/[DD/MM/YY]";
>>>>>>> upstream/branch-clear

    protected static final String allTasks = "all";
    protected static final String eventTasks = "event";
    protected static final String assignmentTasks = "assignment";
    protected String taskType;
    protected String searchParam;
    protected LocalDate date;

<<<<<<< HEAD
    /**
     * Constructs a Searchd command with the parameters supplied.
     * @param taskType types of tasks user is searching for
     * @param searchParam query that user wants to find
     * @param date date that user wants to look for
     */
    public SearchdCommand(String taskType, String searchParam, LocalDate date) {
=======
    public SearchdCommand (String taskType, String searchParam, LocalDate date) {
>>>>>>> upstream/branch-clear
        this.searchParam = searchParam.toLowerCase();
        this.taskType = taskType;
        this.date = date;
    }

    /**
     * Returns an arrayList of all the tasks that matches the search query.
     * @param taskList taskList object containing all the tasks
     * @return arrayList of all tasks that match the search query
     */
    private ArrayList<Task> getSearchQueryAllTasks(TaskList taskList, LocalDate date) {
        ArrayList<Task> tasks = taskList.getTaskArray();
        ArrayList<Task> results = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getName().toLowerCase().contains(searchParam) && task.getDate().equals(date)) {
                results.add(task);
            }
        }
        return results;
    }

    /**
     * Returns an ArrayList of all event objects that matches the search query.
     * @param taskList taskList object containing all the tasks
     * @return ArrayList of all event objects that matches the search query
     */
    private ArrayList<Task> getSearchQueryEvents(TaskList taskList, LocalDate date) {
        ArrayList<Task> events = taskList.getEventsArray();
        assert events.size() == taskList.getEventsArray().size();
        ArrayList<Task> results = new ArrayList<>();
        for (Task event: events) {
            if (event.getName().toLowerCase().contains(searchParam) && event.getDate().equals(date)) {
                results.add(event);
            }
        }
        return results;
    }

    /**
     * Returns an ArrayList of all assignments objects that matches the search query.
     * @param taskList taskList objects containing all assignment tasks
     * @return ArrayList of all assignment object that matches the search query
     */
    private ArrayList<Task> getSearchQueryAssignments(TaskList taskList, LocalDate date) {
        ArrayList<Task> assignments = taskList.getAssignmentsArray();
        ArrayList<Task> results = new ArrayList<>();
        assert assignments.size() == taskList.getAssignmentsArray().size();
        for (Task assignment: assignments) {
            if (assignment.getName().toLowerCase().contains(searchParam) && assignment.getDate().equals(date)) {
                results.add(assignment);
            }
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
        int position = 1;
        StringBuilder searchString = new StringBuilder();
        searchString.append(Messages.SEARCH_SUCCESS_MESSAGE);
        searchString.append(System.lineSeparator());
        for (Task task: results) {
            searchString.append(String.format("%3d.%s", position, task.toString()));
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
                    Parser.capitalize(COMMAND_WORD), COMMAND_USAGE));
        }
    }
}
