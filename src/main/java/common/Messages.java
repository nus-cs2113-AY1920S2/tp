package common;

/**
 * Container for all default messages printed to user.
 */
public class Messages {
    // Start up and Exit Print Messages
    public static final String LOGO =
            " _______        _______        _______        _______ \n"
                    + "|   _   |      |       |      |   _   |      |       |\n"
                    + "|  |_|  |      |_     _|      |  |_|  |      |  _____|\n"
                    + "|       |        |   |        |       |      | |_____ \n"
                    + "|       | ___    |   |   ___  |       | ___  |_____  |\n"
                    + "|   _   ||   |   |   |  |   | |   _   ||   |  _____| |\n"
                    + "|__| |__||___|   |___|  |___| |__| |__||___| |_______|\n";
    public static final String EXIT_MESSAGE = "Exiting A.T.A.S";

    // Common Print Messages
    public static final String DIVIDER = "_______________________________________________________________________";
    public static final String NEWLINE_INDENT = "     ";
    public static final String COMMENTS_INDENT = "            notes: ";
    public static final String PROMPT_FOR_USER_INPUT = "> ";

    // Help Print Messages
    public static final String DATE_FORMAT_HELP = "Date Format: dd/MM/yy HHmm";
    public static final String START_END_DATE_FORMAT_HELP = "Date Format: dd/MM/yy HHmm - HHmm";
    public static final String EVENT_FORMAT_HELP = "Add Event: "
            + "event n/[EVENT NAME] l/[LOCATION] d/[dd/MM/yy HHmm - HHmm] c/[COMMENTS]";
    public static final String ASSIGNMENT_FORMAT_HELP = "Add Assignment: "
            + "assignment n/[ASSIGNMENT NAME] m/[MODULE NAME] d/[dd/MM/yy HHmm] c/[COMMENTS]";
    public static final String DONE_FORMAT_HELP = "Mark Task as Done: done [TASK NUMBER]";
    public static final String DELETE_FORMAT_HELP = "Delete a Task: delete [TASK NUMBER]";
    public static final String LIST_FORMAT_HELP = "List All Tasks: list";
    public static final String LIST_TODAY_FORMAT_HELP = "List Today's Tasks: list today";
    public static final String LIST_WEEK_FORMAT_HELP = "List This Week's Tasks: list week";
    public static final String LIST_UPCOMING_EVENT_FORMAT_HELP = "List Upcoming Events: "
            + "list upcoming events";
    public static final String LIST_INCOMPLETE_ASSIGN_FORMAT_HELP = "List Incomplete Assignments: "
            + "list incomplete assignments";
    public static final String CLEAR_FORMAT_HELP = "Clear all tasks: clear all";
    public static final String CLEAR_DONE_FORMAT_HELP = "Clear all completed tasks: clear done";
    public static final String SEARCH_FORMAT_HELP = "Search for tasks: search t/[TASK TYPE] n/[TASK NAME]";
    public static final String EXIT_FORMAT_HELP = "Exit ATAS: exit";
    public static final String HELP_FORMAT_MESSAGE =
            "Following is the list of commands available:" + System.lineSeparator()
                    + "1.  Help Format: help" + System.lineSeparator()
                    + "2.  " + ASSIGNMENT_FORMAT_HELP + System.lineSeparator()
                    + "3.  " + EVENT_FORMAT_HELP + System.lineSeparator()
                    + "4.  " + LIST_TODAY_FORMAT_HELP + System.lineSeparator()
                    + "5.  " + LIST_WEEK_FORMAT_HELP + System.lineSeparator()
                    + "6.  " + LIST_FORMAT_HELP + System.lineSeparator()
                    + "7.  " + LIST_INCOMPLETE_ASSIGN_FORMAT_HELP + System.lineSeparator()
                    + "8.  " + LIST_UPCOMING_EVENT_FORMAT_HELP + System.lineSeparator()
                    + "9.  " + DONE_FORMAT_HELP + System.lineSeparator()
                    + "10. " + CLEAR_FORMAT_HELP + System.lineSeparator()
                    + "11. " + CLEAR_DONE_FORMAT_HELP + System.lineSeparator()
                    + "12. " + DELETE_FORMAT_HELP + System.lineSeparator()
                    + "13. " + SEARCH_FORMAT_HELP + System.lineSeparator()
                    + "13. " + EXIT_FORMAT_HELP;


    // Command Print Messages
    public static final String ADD_SUCCESS_MESSAGE = "Added task:" + System.lineSeparator() + NEWLINE_INDENT
            + "%s" + System.lineSeparator() + "Now you have %d task%s in the list!";
    public static final String EMPTY_TASKLIST_MESSAGE = "No tasks were found";
    public static final String SHOW_TASKLIST_MESSAGE = "Here are the relevant tasks:%s%s";
    public static final String DONE_SUCCESS_MESSAGE = "[%s] has been marked done!";
    public static final String DELETE_SUCCESS_MESSAGE = "[%s] has been deleted!";
    public static final String CLEAR_SUCCESS_MESSAGE = "All tasks have been deleted";
    public static final String CLEAR_DONE_SUCCESS_MESSAGE = "All completed tasks have been removed";
    public static final String SEARCH_SUCCESS_MESSAGE = "There are a total of %d result(s) found";
    public static final String EDIT_SUCCESS_MESSAGE = "Task edited successfully:" + System.lineSeparator()
            + NEWLINE_INDENT + "%s";
    public static final String EDIT_PROMPT = "Please edit your chosen task";
    // Others
    public static final String NO_TASKS_MSG = "You have no tasks at the moment";
    public static final String RANGE_OF_VALID_TASK_INDEX_MSG = "1 to %1$s";

    // Error Messages
    public static final String INCORRECT_COMMAND_ERROR = "Oh no. %s";
    public static final String UNKNOWN_COMMAND_ERROR = "Unknown command entered";
    public static final String DATE_INCORRECT_OR_INVALID_ERROR = "Wrong date format or invalid date provided"
            + System.lineSeparator() + DATE_FORMAT_HELP;
    public static final String START_END_DATE_INCORRECT_OR_INVALID_ERROR = "Wrong date format or invalid date provided"
            + System.lineSeparator() + START_END_DATE_FORMAT_HELP;
    public static final String NUM_FORMAT_ERROR = "Please provide an integer as the command parameter";
    public static final String INVALID_ID_ERROR = "Please provide a valid task number from %1$s";
    public static final String COMPLETED_TASK_ERROR = "Task is already completed";
    public static final String REPEAT_TASK_ERROR = "Please use a different name. Task already exists in list";
    public static final String EMPTY_DONE_CLEAR_ERROR = "There are no completed tasks at the moment";
    public static final String INCORRECT_START_END_TIME_ERROR = "The end time should come after the start time";
    public static final String INCORRECT_STORAGE_FORMAT_ERROR = "The local save file is of an unknown format. "
            + "Exit now using <Ctrl C> to manually fix the save file, "
            + "or the save file will be overwritten with the new session data";
    public static final String NO_SAVE_FILE_MESSAGE = "No existing save file found. A new save file will be created";
    public static final String SAVE_FAILED_MESSAGE = "Oh no. Something went wrong while saving, please try again later";

    public static final String ASSIGN_INCORRECT_FORMAT_ERROR = "Incorrect format for Assignment Command"
            + System.lineSeparator() + ASSIGNMENT_FORMAT_HELP;
    public static final String EVENT_INCORRECT_FORMAT_ERROR = "Incorrect format for Event Command"
            + System.lineSeparator() + EVENT_FORMAT_HELP;
    public static final String LIST_INCORRECT_FORMAT_ERROR = "Invalid argument for List Command";
    public static final String DONE_INSUFFICIENT_ARGS_ERROR = "Insufficient arguments for Done Command"
            + System.lineSeparator() + DONE_FORMAT_HELP;
    public static final String DELETE_INSUFFICIENT_ARGS_ERROR = "Insufficient arguments for Delete Command"
            + System.lineSeparator() + DELETE_FORMAT_HELP;
    public static final String CLEAR_INCORRECT_FORMAT_ERROR = "Invalid argument for Clear Command";
    public static final String EMPTY_SEARCH_RESULTS_ERROR = "There are no matching tasks for the search query";
    public static final String INVALID_SEARCH_FORMAT = "Invalid Argument for Search Command";
    public static final String SEARCH_INSUFFICIENT_ARGS = "Insufficient argument for Search Command"
            + System.lineSeparator() + SEARCH_FORMAT_HELP;
}
