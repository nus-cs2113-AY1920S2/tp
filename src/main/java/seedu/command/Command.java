package seedu.command;

import seedu.common.Messages;
import seedu.exception.ProjException;
import seedu.storage.Storage;
import seedu.tasklist.TaskList;
import seedu.ui.Ui;

import static seedu.common.Constants.TAB;

/**
 * Represents an executable command.
 */
public abstract class Command {

    protected TaskList taskList;
    protected Storage storage;
    protected Ui ui;

    protected static final String TITLE = "n/";
    protected static final String TIME = "t/";
    protected static final String DATE = "d/";
    protected static final String DESCRIPTION = "i/";
    protected static final String LOCATION = "l/";
    protected static final String REMINDER = "r/";
    protected static final String CATEGORY = "c/";

    /**
     * Called to check if exit command is given.
     *
     * @return condition whether program should continue looping or exit
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Supplies the objects other commands will call upon.
     *
     * @param taskList  current instance of tasks and corresponding tasklist methods
     * @param storage instance of storage object
     * @param ui instance of user interaction object
     */
    public void setCommandVariables(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }


    /**
     * Gets the title, if any, from the user input.
     *
     * @param userInput raw user input
     * @return title
     */
    public String getTitle(String userInput) {

        String title = "";

        if (userInput.contains(TITLE)) {
            int index = userInput.indexOf(TITLE);

            title = findField(userInput, index);
            return title;
        }

        return title;
    }

    /**
     * Gets the description, if any, from the user input.
     *
     * @param userInput raw user input
     * @return description
     */
    public String getDescription(String userInput) {

        String description = "";

        if (userInput.contains(DESCRIPTION)) {
            int index = userInput.indexOf(DESCRIPTION);

            description = findField(userInput, index);
            return description;
        }

        return description;
    }


    /**
     * Gets the date, if any, from the user input.
     *
     * @param userInput raw user input
     * @return date
     */
    public String getDate(String userInput) {

        String date = "";

        if (userInput.contains(DATE)) {
            int index = userInput.indexOf(DATE);

            date = findField(userInput, index);
            return date;
        }
        return date;
    }

    /**
     * Gets the reminder, if any, from the user input.
     *
     * @param userInput raw user input.
     * @return reminder.
     */
    public String getReminder(String userInput) {
        String reminder = "";
        if (userInput.contains(REMINDER)) {
            int index = userInput.indexOf(REMINDER);
            reminder = findField(userInput, index);
            return reminder;
        }
        return reminder;
    }

    /**
     * Gets the time in format hh:mm, if any, from the user input.
     *
     * @param userInput raw user input.
     * @return time.
     */
    public String getTime(String userInput) {
        String time = "";
        if (userInput.contains(TIME)) {
            int index = userInput.indexOf(TIME);
            time = findField(userInput, index);
            return time;
        }
        return time;
    }

    /**
     * Gets the location, if any, from the user input.
     *
     * @param userInput raw user input.
     * @return location.
     */
    public String getLocation(String userInput) {
        String location = "";
        if (userInput.contains(LOCATION)) {
            int index = userInput.indexOf(LOCATION);
            location = findField(userInput, index);
            return location;
        }
        return location;
    }

    /**
     * Gets the category.Default one is TODO.
     *
     * @param userInput raw user input.
     * @return category.
     */
    public String getCategory(String userInput) {

        String category = "";

        if (userInput.contains(CATEGORY)) {
            int index = userInput.indexOf(CATEGORY);

            category = findField(userInput, index);
            return category;
        }

        return category;
    }

    /**
     * Scans the raw user input to search for the input.
     * for a field (e.g. "essay" in event n/essay i/world religions)
     *
     * @param userInput raw user input
     * @param fromIndex index marking the beginning of the field
     * @return expected field
     */
    private String findField(String userInput, int fromIndex) {

        String field = "";

        char nextField = '/';
        int i = fromIndex + 2;

        while (i < userInput.length() && userInput.charAt(i) != nextField) {
            field += userInput.charAt(i);
            i++;
        }

        if (i < userInput.length() && userInput.charAt(i) == nextField) {
            return field.substring(0, field.length() - 1);
        }

        return field;
    }

    /**
     * Check format for date and time.
     *
     * @param date User input date.
     * @param time User input time.
     * @throws ProjException Prompt message to advice users how to input the correct format.
     */
    protected void checkDateTimeFormat(String date, String time) throws ProjException {
        // First check: if time follows the format: hh:mm-hh:mm
        if (time.length() != 0) {
            String[] timeRanges = time.split("\\s+");
            for (String timeRange : timeRanges) {
                if (!timeRange.contains("-")) {
                    throw new ProjException(TAB + Messages.MESSAGE_INCORRECT_TIME_FORMAT);
                }
                Integer timePointCount = timeRange.split("-").length;
                if (timePointCount != 2) {
                    throw new ProjException(TAB + Messages.MESSAGE_INCORRECT_TIME_FORMAT);
                }
            }
        }

        // Second check: both date and time are inputted
        if (date.length() == 0 | time.length() == 0) {
            return;
        }

        // Third check: if number of time range match with the number of date
        Integer dateCount = date.split("\\s+").length;
        Integer timeCount = time.split("\\s+").length;
        if (dateCount != timeCount) {
            throw new ProjException(TAB + Messages.MESSAGE_GENERAL_DATE_TIME_MISMATCH);
        }
    }

    /**
     * Checks for invalid date/time format: E.g. 2020/07/04.
     * / is used in delimiter.
     *
     * @param input userInput string.
     * @return true if there exists "digit/" format.
     */
    protected boolean hasWrongDelimiterPattern(String input) {
        return input.matches(".*[0-9]+/.*");
    }

    /**
     * Executes user command processed by parser.
     */
    public abstract CommandResult execute() throws ProjException;

}

