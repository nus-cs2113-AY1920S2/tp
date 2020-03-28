package common;

/**
 * TESTING SUMMARY DOC.
 */
public class Messages {
    public static final String FORMAT_TIMETABLE = "To display timetable:\n\ttimetable \n\ttimetable <Member Number 1>"
            + "\n\ttimetable <Member Number 1> <Member Number 2>";

    public static final String MESSAGE_STARTENDTIME_OUT_OF_RANGE = "Start and end time must be between of 00:00-23:30!";
    public static final String MESSAGE_STARTENDTIME_WRONG_FORMAT = "Start and end time must be in 30 minutes interval!";
    public static final String MESSAGE_STARTENDDAY_OUT_OF_RANGE = "Start and end day must be between 0 and 6!";
    public static final String MESSAGE_INVALID_MEETING = "Range entered is not within free slots!";
    public static final String MESSAGE_INVALID_NUMBER = "You have entered an invalid number!";
    public static final String MESSAGE_INVALID_NUSMODLINK = "You have entered an invalid nusmods link!";
    public static final String MESSAGE_MODULECODE_IN_BLACKLIST = "Module's information from NUSMOD API is ill-formatted.";
    public static final String MESSAGE_EMPTY_MODULE = "Module either can't be fetched from NUSMOD API or has been deprecated from NUS";
    public static final String MESSAGE_RETURN_SUCCESS = "SUCCESS";
    public static final String MESSAGE_INDEX_OUT_OF_BOUNDS = "You have entered an index out of bounds!";
}
