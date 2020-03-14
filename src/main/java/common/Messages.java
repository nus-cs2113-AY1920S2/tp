package common;

/**
 * Container for all default messages printed to user.
 */
public class Messages {
    // Generic Print Messages
    public static final String LOGO =
            " _______        _______        _______        _______ \n"
            + "|   _   |      |       |      |   _   |      |       |\n"
            + "|  |_|  |      |_     _|      |  |_|  |      |  _____|\n"
            + "|       |        |   |        |       |      | |_____ \n"
            + "|       | ___    |   |   ___  |       | ___  |_____  |\n"
            + "|   _   ||   |   |   |  |   | |   _   ||   |  _____| |\n"
            + "|__| |__||___|   |___|  |___| |__| |__||___| |_______|\n";


    public static final String DIVIDER = "_______________________________________________________________________";
    public static final String EXIT_MESSAGE = "Exiting A.T.A.S";
    public static final String NEWLINE_INDENT = "     ";
    public static final String COMMENTS_INDENT = "            notes: ";

    // Help Messages
    public static final String DATE_FORMAT_HELP = "Date Format: dd/MM/yy HHmm";
    public static final String EVENT_FORMAT_HELP = "Add Event: "
            + "event n/[EVENT NAME] l/[LOCATION] d/[dd/MM/yy HHmm] c/[COMMENTS]";
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
    public static final String CLEAR_FORMAT_HELP = "Clear all tasks: clear";
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
            + "10.  " + CLEAR_FORMAT_HELP + System.lineSeparator()
            + "12. " + DELETE_FORMAT_HELP;

    // Command Print Messages
    public static final String ADD_SUCCESS_MESSAGE = "Added task:" + System.lineSeparator() + NEWLINE_INDENT
            + "%s" + System.lineSeparator() + "Now you have %d task%s in the list!";
    public static final String DELETE_SUCCESS_MESSAGE = "[%s] has been deleted!";
    public static final String DONE_SUCCESS_MESSAGE = "[%s] has been marked done!";
    public static final String INCORRECT_COMMAND_ERROR = "Oh no. %s";
    public static final String EMPTY_TASKLIST_MESSAGE = "No tasks were found";
    public static final String SHOW_TASKLIST_MESSAGE = "Here are the relevant tasks:%s%s";
    public static final String CLEAR_SUCCESS_MESSAGE = "All tasks have been deleted";

    // Error Messages
    public static final String UNKNOWN_COMMAND_ERROR = "Unknown command entered";
    public static final String ASSIGN_INCORRECT_FORMAT_ERROR = "Incorrect format for Assignment Command"
            + System.lineSeparator() + ASSIGNMENT_FORMAT_HELP;
    public static final String EVENT_INCORRECT_FORMAT_ERROR = "Incorrect format for Event Command"
            + System.lineSeparator() + EVENT_FORMAT_HELP;
    public static final String LIST_INCORRECT_FORMAT_ERROR = "Invalid argument for List Command";

    public static final String DATE_INCORRECT_OR_INVALID_ERROR = "Wrong date format or invalid date provided"
            + System.lineSeparator() + DATE_FORMAT_HELP;
    public static final String NUM_FORMAT_ERROR = "Please provide an integer as the command parameter";
    public static final String DELETE_INSUFFICIENT_ARGS_ERROR = "Insufficient arguments for Delete Command"
            + System.lineSeparator() + DELETE_FORMAT_HELP;
    public static final String DONE_INSUFFICIENT_ARGS_ERROR = "Insufficient arguments for Done Command"
            + System.lineSeparator() + DONE_FORMAT_HELP;

    public static final String INVALID_ID_ERROR = "Please provide a valid task number from %1$s";
    public static final String NO_TASKS_MSG = "You have no tasks at the moment";
    public static final String RANGE_OF_VALID_TASK_INDEX_MSG = "1 to %1$s";
    public static final String COMPLETED_TASK_ERROR = "Task is already completed";
    public static final String REPEAT_TASK_ERROR = "Please use a different name. Task already exists in list";
}
