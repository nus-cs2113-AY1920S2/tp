package command;

import exception.command.DeadlineCreationDueTimeMissingException;
import exception.command.DeadlineCreationFormatNotFollowedException;
import exception.command.DeadlineDetailsNotProvidedException;
import exception.command.TaskCreationDateMissingException;
import exception.command.TaskCreationDescriptionMissingException;
import exception.command.TaskCreationPriorityMissingException;
import task.Deadline;
import task.TaskList;
import ui.Ui;

import java.time.LocalDate;

import static ui.Constants.DATE_BEFORE_CURRENT_DATE_ERROR_MESSAGE;

//@@author GanapathySanathBalaji
/**
 * This command is used to add new deadlines to the TaskList.
 */
public class AddDeadlineCommand extends Command {

    public static final String SLASH_SYMBOL = "/";
    public static final char DUE_TIME_IDENTIFIER = 't';
    public static final char SINGLE_SPACE_CHARACTER = ' ';
    public static final char PRIORITY_IDENTIFIER = 'p';
    public static final char DATE_IDENTIFIER = 'd';
    public static final int INDEX_OF_DESCRIPTION = 0;
    public static final int INDEX_OF_DATE = 1;
    public static final int INDEX_OF_DUE_TIME = 2;
    public static final int INDEX_OF_PRIORITY = 3;
    public static final int NUMBER_OF_FIELDS_REQUIRED = 4;
    public static final int INDEX_OF_DETAILS = 1;
    public static final boolean DEFAULT_STATUS_ON_CREATION = false;

    /** The new event to be added. */
    Deadline newDeadline;

    /**
     * Constructor for the AddCommand Object.
     * Abstracts out the details from the user input and uses it to create a new deadline object.
     *
     * @param deadlineDetails The array contains the information regarding the new deadline to be added.
     * @param isOneWordCommand Denotes whether the user given input is single or multi worded.
     * @throws Exception If the wrong format is used to create an event.
     */
    public AddDeadlineCommand(String[] deadlineDetails, boolean isOneWordCommand) throws Exception {
        if (isOneWordCommand) {
            throw new DeadlineDetailsNotProvidedException();
        }
        String[] details = deadlineDetails[INDEX_OF_DETAILS].split(SLASH_SYMBOL);
        isCorrectFormat(details);
        String description = details[INDEX_OF_DESCRIPTION];
        String date = details[INDEX_OF_DATE].substring(2);
        String dueTime = details[INDEX_OF_DUE_TIME].substring(2);
        String priority = details[INDEX_OF_PRIORITY].substring(2);
        newDeadline =  new Deadline(description, date, dueTime, priority, DEFAULT_STATUS_ON_CREATION);
        assert !newDeadline.getDate().isBefore(LocalDate.now()) : DATE_BEFORE_CURRENT_DATE_ERROR_MESSAGE;
    }

    /**
     * Checks if the correct format is used to create a deadline, if not an exception is thrown.
     *
     * @param details Details entered by user.
     * @throws Exception If any of the fields are missing.
     */
    private void isCorrectFormat(String[] details) throws Exception {
        if (details.length != NUMBER_OF_FIELDS_REQUIRED) {
            throw new DeadlineCreationFormatNotFollowedException();
        }
        try {
            String description = details[INDEX_OF_DESCRIPTION];
            checkDescription(description);
            String date = details[INDEX_OF_DATE];
            checkDate(date);
            String dueTime = details[INDEX_OF_DUE_TIME];
            checkDueTime(dueTime);
            String priority = details[INDEX_OF_PRIORITY];
            checkPriority(priority);
            if (details[INDEX_OF_DESCRIPTION].isBlank()) {
                throw new TaskCreationDescriptionMissingException();
            }
            if (details[INDEX_OF_DATE].substring(2).isBlank()) {
                throw new TaskCreationDateMissingException();
            }
            if (details[INDEX_OF_DUE_TIME].substring(2).isBlank()) {
                throw new DeadlineCreationDueTimeMissingException();
            }
            if (details[INDEX_OF_PRIORITY].substring(2).isBlank()) {
                throw new TaskCreationPriorityMissingException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DeadlineCreationFormatNotFollowedException();
        }
    }

    /**
     * Checks if the description is provided in the correct format, throws an exception if wrong format is used.
     *
     * @param description String entered by user for description.
     * @throws Exception If wrong format is used.
     */
    private void checkDescription(String description) throws Exception {
        if (description.isBlank()) {
            throw new TaskCreationDescriptionMissingException();
        }
    }

    /**
     * Checks if the date is provided in the correct format, throws an exception if wrong format is used.
     *
     * @param date String entered by user for date.
     * @throws Exception If wrong format is used.
     */
    private void checkDate(String date) throws Exception {
        if (date.charAt(0) != DATE_IDENTIFIER || date.charAt(1) != SINGLE_SPACE_CHARACTER) {
            throw new DeadlineCreationFormatNotFollowedException();
        }
        if (date.substring(2).isBlank()) {
            throw new TaskCreationDateMissingException();
        }
    }

    /**
     * Checks if the priority is provided in the correct format, throws an exception if wrong format is used.
     *
     * @param priority String entered by user for priority.
     * @throws Exception If wrong format is used.
     */
    private void checkPriority(String priority) throws Exception {
        if (priority.charAt(0) != PRIORITY_IDENTIFIER || priority.charAt(1) != SINGLE_SPACE_CHARACTER) {
            throw new DeadlineCreationFormatNotFollowedException();
        }
        if (priority.substring(2).isBlank()) {
            throw new TaskCreationPriorityMissingException();
        }
    }

    /**
     * Checks if the due time is provided in the correct format, throws an exception if wrong format is used.
     *
     * @param dueTime String entered by user for due time.
     * @throws Exception If wrong format is used.
     */
    private void checkDueTime(String dueTime) throws Exception {
        if (dueTime.charAt(0) != DUE_TIME_IDENTIFIER || dueTime.charAt(1) != SINGLE_SPACE_CHARACTER) {
            throw new DeadlineCreationFormatNotFollowedException();
        }
        if (dueTime.substring(2).isBlank()) {
            throw new DeadlineCreationDueTimeMissingException();
        }
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui)   {
        taskList.addTask(newDeadline, ui);
    }

}
