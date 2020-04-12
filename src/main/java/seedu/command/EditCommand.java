package seedu.command;

import seedu.common.Messages;
import seedu.exception.ProjException;
import seedu.storage.Storage;

import static seedu.common.Constants.CLASS_CATEGORY;
import static seedu.common.Constants.TAB;

public class EditCommand extends Command {
    private String taskEdited;
    private String userInput;

    public static final String COMMAND_WORD = "edit";
    public static final String COMMAND_INFO = COMMAND_WORD + ": edits the specified task";
    public static final String COMMAND_USAGE = COMMAND_WORD + " TASK_INDEX t/[HH:MM-HH:MM] l/[LOCATION] "
            + "d/[YYYY-MM-DD] i/[INFORMATION] r/[REMINDER] c/[CATEGORY]";

    public EditCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Checks if a string can be casted to a number.
     *
     * @param str input string.
     * @return true if it can be casted.
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the user has input that field.
     *
     * @param input a field.
     * @return true if user has input that field.
     */
    private Boolean hasInput(String input) {
        if (input.length() == 0) {
            return false;
        }
        return true;
    }

    /**
     * Edit a task by the task index number.
     * Cannot edit title of a class.
     *
     * @return result of command.
     * @throws ProjException if input invalid task index.
     */
    public CommandResult execute() throws ProjException {

        Boolean isEdit = false;

        String[] commandSections = this.userInput.split(" ");

        if (commandSections.length < 2) {
            throw new ProjException(TAB + Messages.MESSAGE_EDIT_MISSING_INDEX);
        }

        this.taskEdited = commandSections[1];
        if (!isNumeric(taskEdited.trim())) {
            throw new ProjException(TAB + Messages.MESSAGE_EDIT_MISSING_INDEX);
        }

        Integer taskEdited = Integer.parseInt(this.taskEdited.trim()) - 1;
        if (taskEdited < 0 || taskEdited >= taskList.getListSize()) {
            throw new ProjException(TAB + String.format(Messages.MESSAGE_EDIT_OUT_OF_BOUNDS, (taskEdited + 1)));
        }


        String title = getTitle(userInput);
        if (hasInput(title)) {
            throw new ProjException(TAB + Messages.MESSAGE_EDIT_TITLE_ERROR);
        }

        String description = getDescription(userInput);
        if (hasInput(description)) {
            taskList.changeDescription(taskEdited,description);
            isEdit = true;
        }

        String reminder = getReminder(userInput);
        if (hasInput(reminder)) {
            taskList.changeReminder(taskEdited,reminder);
            isEdit = true;
        }

        //When both date and time are inputted, need to check the matching format.
        String date = getDate(userInput);
        String time = getTime(userInput);
        checkDateTimeFormat(date,time);
        if (hasInput(date) & hasInput(time)) {
            taskList.changeDate(taskEdited,date);
            taskList.changeTime(taskEdited,time);
            isEdit = true;
        }

        //When only either date/time is inputted, need to check if date&time match after modifying.
        if (hasInput(date) & !hasInput(time)) {

            if (taskList.getTask(taskEdited).getTime().size() == 0) {
                // When previously no time is set by user so it is okay to edit the number of date
                taskList.changeDate(taskEdited, date);
                isEdit = true;
            } else if (date.split("\\s+").length != taskList.getTask(taskEdited).getTime().size() / 2) {
                throw new ProjException(TAB + Messages.MESSAGE_EDIT_DATE_TIME_MISMATCH);
            } else {
                taskList.changeDate(taskEdited, date);
                isEdit = true;
            }
        }

        if (hasInput(time) & !hasInput(date)) {
            if (!taskList.getTask(taskEdited).isDateSetByUser()) {
                // When previously no date is set by user so it is okay to edit the number of time
                taskList.changeTime(taskEdited,time);
                isEdit = true;
            } else if (time.split("\\s+").length != taskList.getTask(taskEdited).getDate().size()) {
                throw new ProjException(TAB + Messages.MESSAGE_EDIT_DATE_TIME_MISMATCH);
            } else {
                taskList.changeTime(taskEdited,time);
                isEdit = true;
            }
        }

        String location = getLocation(userInput);
        if (hasInput(location)) {
            taskList.changeLocation(taskEdited,location);
            isEdit = true;
        }

        String category = getCategory(userInput).trim().toUpperCase();
        if (hasInput(category)) {
            //If it is class, cannot change to task. If it is task, cannot change to class.
            if (category.equals(CLASS_CATEGORY)) {
                throw new ProjException(TAB + Messages.MESSAGE_TASK_TO_CLASS_ERROR);
            } else if (taskList.getTask(taskEdited).getCategory().equals(CLASS_CATEGORY)) {
                throw new ProjException(TAB + Messages.MESSAGE_CLASS_TO_TASK_ERROR);
            }
            taskList.changeCategory(taskEdited,category);
            isEdit = true;
        }

        //post condition for checking no title input.
        assert (!hasInput(title));

        if (isEdit) {
            String feedback = TAB + "Task " + (taskEdited + 1) + " edited\n";
            feedback += TAB + TAB + taskList.getTask(taskEdited).toString() + "\n";

            Storage.overwriteFile(taskList.getList());

            return new CommandResult(feedback);
        } else {
            throw new ProjException(TAB + Messages.MESSAGE_NO_TASK_MODIFIED);
        }
    }
}
