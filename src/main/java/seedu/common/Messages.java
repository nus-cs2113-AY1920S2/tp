package seedu.common;

import static seedu.common.Constants.TAB;

/**
 * Container class for repeated static Strings.
 */
public class Messages {

    /** Messages for find command. */
    public static final String MESSAGE_SUCCESS = "Found the followings tasks:\n%s";
    public static final String MESSAGE_EMPTY_PATTERN = "[Error][find] Please enter a pattern to look for \n";
    public static final String MESSAGE_NO_FOUND = "[Alert][find] No tasks found with \"%s\" in their title, "
            + "description, or location\n";

    /** Messages for delete command. */
    public static final String MESSAGE_MISSING_NUMBER = "[Error][delete] Missing task number to delete\n";
    public static final String MESSAGE_DELETE_SUCCESS = "The following task has been removed:\n %s";
    public static final String MESSAGE_DELETE_NO_TASK_FOUND = "[Alert][delete] No task found to delete";

    /** Messages for list command. */
    public static final String MESSAGE_LIST_IS_EMPTY = "[Alert][%s] There is nothing to %s, the list is empty\n";
    public static final String MESSAGE_OUT_OF_BOUNDS = "[Error][%s] Task number \"%s\" doesn't exit. There are %s"
            + " task/tasks in the list\n";
    public static final String MESSAGE_PRESENT_OR_FUTURE_DATE = "[Alert][list] Please enter a present or future date";

    /** Messages for add command. */
    public static final String MESSAGE_PAST_DATE_Error = "[Alert][add] Not allowed to add task "
            + "in the past";
    public static final String MESSAGE_DAY_OR_WEEK = "[Error][add] Integer that represents day"
            + " of week must within 1 to 7";

    /** Messages for edit command. */
    public static final String MESSAGE_EDIT_MISSING_INDEX = "[Error][edit] Missing task number to edit "
            + "(e.g: edit 2 r/reminder)";
    public static final String MESSAGE_EDIT_TITLE_ERROR = "[Alert][edit] Not allowed to change title";
    public static final String MESSAGE_TASK_TO_CLASS_ERROR = "[Alert][edit] Not allowed to change from "
            + "other categories to CLASS category";
    public static final String MESSAGE_CLASS_TO_TASK_ERROR = "[Alert][edit] Not allowed to change from "
            + "CLASS category to other category";
    public static final String MESSAGE_NO_TASK_MODIFIED = "[Alert][edit] No task has been edited"
            + " (No valid field input)";
    public static final String MESSAGE_EDIT_DATE_TIME_MISMATCH = "[Alert][edit] The number of time range must match "
            + "with the number of date(day of a week).\n";
    public static final String MESSAGE_EDIT_OUT_OF_BOUNDS = "[Error][edit] Task number \"%s\" doesn't exit";


    /** General messages. */
    public static final String MESSAGE_REMAINING_TASKS = "Now you have %d task/tasks in your calendar\n";
    public static final String MESSAGE_INVALID_INDEX = "[Error][%s] The entered index \"%s\" is invalid. "
            + "Please enter a valid task number\n";
    public static final String MESSAGE_DATETIME_ERROR = TAB + "[Error] Expected format for date/time:"
            + " yyyy-mm-dd; time: hh:mm";
    public static final String MESSAGE_INVALID_COMMAND = "[Alert] Command \"%s\" not recognized\n";
    public static final String MESSAGE_GENERAL_DATE_TIME_MISMATCH = "[Alert] The number of time range must match "
            + "with the number of date(day of a week).\n";
    public static final String MESSAGE_INCORRECT_TIME_FORMAT = "[Alert] Please follow the format when input "
            + "time: hh:mm-hh:mm\n";
    public static final String MESSAGE_END_BEFORE_START_TIME = "[Error][Add/Edit]: Please enter a valid time range: "
            + "the end time should be after the start time\n";
    public static final String MESSAGE_GENERAL_ERROR_MESSAGE = "[Error] Unexpected input for parameter\n";

    /** Farewell message. */
    public static final String BYE_MESSAGE = "CAFS: Bye, hope to see you again!\n";
}
