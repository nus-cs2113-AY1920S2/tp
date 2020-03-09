package common;

/**
 * Container for all default messages printed to user.
 */
public class Messages {
    // Generic Print Messages
    public static final String LOGO =
            " _____ ______ _   _ _____ ______ \n"
            + " / ____|  ____| \\ | |_   _|  ____|\n"
            + "| |  __| |__  |  \\| | | | | |__   \n"
            + "| | |_ |  __| | . ` | | | |  __|  \n"
            + "| |__| | |____| |\\  |_| |_| |____ \n"
            + " \\_____|______|_| \\_|_____|______|";
    public static final String DIVIDER = "____________________________________________________________";
    public static final String EXIT_MESSAGE = "Exiting DUKE" + System.lineSeparator() + Messages.LOGO;

    // Command Print Messages
    public static final String ADD_SUCCESS_MESSAGE = "Added task:" + System.lineSeparator() + "%s" +
            System.lineSeparator() + "Now you have %d tasks in the list!";
    public static final String DELETE_SUCCESS_MESSAGE = "%d. %s is deleted!";
    public static final String DONE_SUCCESS_MESSAGE = "%d. %s is marked done!";
    public static final String INCORRECT_COMMAND_ERROR = "Oh no. %s";
    public static final String EMPTY_TASKLIST_MESSAGE = "There are no tasks found from your query";
    public static final String SHOW_TASKLIST_MESSAGE = "Here are the relevant tasks from your query:%s%s";

    // Error Messages
    public static final String UNKNOWN_COMMAND_ERROR = "Unknown command entered";
    public static final String ASSIGN_INCORRECT_FORMAT_ERROR = "Incorrect format for Assignment Command";
    public static final String EVENT_INCORRECT_FORMAT_ERROR = "Incorrect format for Event Command";

    public static final String DATE_INCORRECT_OR_INVALID_ERROR = "Wrong date format or invalid date provided";
    public static final String NUM_FORMAT_ERROR = "Please provide an integer as the command parameter";
    public static final String DELETE_INSUFFICIENT_ARGS_ERROR = "Insufficient args for Delete Command";
    public static final String DONE_INSUFFICIENT_ARGS_ERROR = "Insufficient args for Done Command";

    public static final String INVALID_ID_ERROR = "Please provide a valid index. %1$s";
    public static final String NO_TASK_MSG = "Please add tasks first.";
    public static final String RANGE_OF_VALID_TASKS_INDEX_MSG = "Any number from 1 to %1$s";
    public static final String COMPLETED_TASK_ERROR = "Task is already completed";
}
