package common;

/**
 * Contains all the messages when exception is thrown.
 */
public class Messages {
    public static final String FORMAT_TIMETABLE = "To display timetable:\n\ttimetable \n\ttimetable <Member Number 1>"
            + "\n\ttimetable <Member Number 1> <Member Number 2>";

    public static final String MESSAGE_STARTENDTIME_OUT_OF_RANGE = "Start and end time must be in HH:MM format in the "
            + "range 00:00-23:30.";
    public static final String MESSAGE_STARTENDTIME_WRONG_FORMAT = "Start and end time must be in 30 minutes interval.";
    public static final String MESSAGE_STARTENDDAY_OUT_OF_RANGE = "Start and end day must be within this week and next week.";
    public static final String MESSAGE_WEEK_RANGE_EMPTY = "Class does not have lessons.";
    public static final String MESSAGE_INVALID_MEETING = "Range entered is not within free slots. Check timetable.";
    public static final String MESSAGE_INVALID_EDIT = "You cannot edit your timetable over a scheduled meeting. Check meetings.";
    public static final String MESSAGE_INVALID_SLOT_RANGE = "Slot entered is not of valid range.";
    public static final String MESSAGE_INVALID_NUMBER = "You have entered an invalid number.";
    public static final String MESSAGE_INVALID_NUSMODLINK = "You have entered an invalid nusmods link.";
    public static final String MESSAGE_BROKEN_NUSMODLINK = "You have entered a broken nusmods link.";
    public static final String MESSAGE_MODULECODE_IN_BLACKLIST = " module information unable to be captured from API, "
            + "please key it in manually using [edit] command.";
    public static final String MESSAGE_EMPTY_MODULE = "Module either can't be fetched from NUSMOD API or has been deprecated "
            + "from NUS.";
    public static final String MESSAGE_RETURN_SUCCESS = "SUCCESS";
    public static final String MESSAGE_INDEX_OUT_OF_BOUNDS = "Index you entered cannot be found.";
    public static final String MESSAGE_WRONG_COMMAND_SCHEDULE = "Follow format for schedule command:";
    public static final String MESSAGE_WRONG_COMMAND_DELETE = "Follow format for delete command:";
    public static final String MESSAGE_WRONG_COMMAND_MEETING = "Follow format for meeting command:";
    public static final String MESSAGE_WRONG_DATE = "Cannot schedule meeting before today.";
}
