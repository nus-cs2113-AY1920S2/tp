package command;

import common.Messages;
import seedu.atas.Parser;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Task;

import java.util.ArrayList;

public class SearchCommand extends Command {
    public static final String COMMAND_WORD = "search";
    public static final String COMMAND_USAGE = "Search for tasks: search t/[TASK TYPE] n/[TASK NAME]";

    protected static final String allTasks = "all";
    protected static final String eventTasks = "event";
    protected static final String assignmentTasks = "assignment";
    protected ArrayList<Integer> storeIndex;
    protected String taskType;
    protected String searchParam;

    /**
     * Constructor for search command.
     * @param searchParam search query that has to be searched
     * @param taskType type of task to search through
     */
    public SearchCommand(String searchParam, String taskType) {
        this.searchParam = searchParam.toLowerCase();
        this.taskType = taskType;
        storeIndex = new ArrayList<>();
    }

    /**
     * Returns an arrayList of all the tasks that matches the search query.
     * @param taskList taskList object containing all the tasks
     * @return arrayList of all tasks that match the search query
     */
    private ArrayList<Task> getSearchQueryAllTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTaskArray();
        ArrayList<Task> results = new ArrayList<>();
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
     * Returns an ArrayList of all event objects that matches the search query.
     * @param taskList taskList object containing all the tasks
     * @return ArrayList of all event objects that matches the search query
     */
    private ArrayList<Task> getSearchQueryEvents(TaskList taskList) {
        ArrayList<Task> events = taskList.getEventsArray();
        assert events.size() == taskList.getEventsArray().size();
        ArrayList<Task> results = new ArrayList<>();
        int index = 1;
        for (Task event: events) {
            if (event.getName().toLowerCase().contains(searchParam)) {
                results.add(event);
                storeIndex.add(index);
            }
            index++;
        }
        return results;
    }

    /**
     * Returns an ArrayList of all assignments objects that matches the search query.
     * @param taskList taskList objects containing all assignment tasks
     * @return ArrayList of all assignment object that matches the search query
     */
    private ArrayList<Task> getSearchQueryAssignments(TaskList taskList) {
        ArrayList<Task> assignments = taskList.getAssignmentsArray();
        ArrayList<Task> results = new ArrayList<>();
        int index = 1;
        assert assignments.size() == taskList.getAssignmentsArray().size();
        for (Task assignment: assignments) {
            if (assignment.getName().toLowerCase().contains(searchParam)) {
                results.add(assignment);
                storeIndex.add(index);
            }
            index++;
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
            ArrayList<Task> results = getSearchQueryAllTasks(taskList);
            return new CommandResult(resultsList(results));
        case eventTasks:
            ArrayList<Task> eventResults = getSearchQueryEvents(taskList);
            return new CommandResult(resultsList(eventResults));
        case assignmentTasks:
            ArrayList<Task> assignmentResults = getSearchQueryAssignments(taskList);
            return new CommandResult(resultsList(assignmentResults));
        default:
            return new CommandResult(String.format(Messages.INCORRECT_ARGUMENT_ERROR,
                    Parser.capitalize(COMMAND_WORD), COMMAND_USAGE));
        }
    }
}
