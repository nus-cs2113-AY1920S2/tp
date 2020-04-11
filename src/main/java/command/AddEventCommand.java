package command;

import exception.command.EventCreationEndTimeMissingException;
import exception.command.EventCreationFormatNotFollowedException;
import exception.command.EventCreationStartTimeMissingException;
import exception.command.EventDetailsNotProvidedException;
import exception.command.TaskCreationDateMissingException;
import exception.command.TaskCreationDescriptionMissingException;
import exception.command.TaskCreationPriorityMissingException;
import task.Event;
import task.TaskList;
import ui.Constants;
import ui.Ui;

import java.time.LocalDate;

//@@author GanapathySanathBalaji
/**
 * This command is used to add new events to the TaskList.
 */
public class AddEventCommand extends Command {

    public static final String SLASH_SYMBOL = "/";
    public static final int INDEX_OF_DESCRIPTION = 0;
    public static final int INDEX_OF_DATE = 1;
    public static final int INDEX_OF_START_TIME = 2;
    public static final int INDEX_OF_END_TIME = 3;
    public static final int INDEX_OF_PRIORITY = 4;
    public static final int INDEX_OF_DETAILS = 1;
    public static final int NUMBER_OF_FIELDS_REQUIRED = 5;
    public static final char PRIORITY_IDENTIFIER = 'p';
    public static final char SINGLE_SPACE_CHARACTER = ' ';
    public static final char END_TIME_IDENTIFIER = 'e';
    public static final char START_TIME_IDENTIFIER = 's';
    public static final char DATE_IDENTIFIER = 'd';

    /** The new event to be added. */
    Event newEvent;

    /**
     * Constructor for the AddCommand Object.
     * Abstracts out the details from the user input and uses it to create a new event object.
     *
     * @param eventDetails The array contains the information regarding the new event to be added.
     * @param isOneWordCommand Denotes whether the user given input is single or multi worded.
     * @throws Exception If the wrong format is used to create an event.
     */
    public AddEventCommand(String[] eventDetails, boolean isOneWordCommand) throws Exception {
        if (isOneWordCommand) {
            throw new EventDetailsNotProvidedException();
        }
        String[] details = eventDetails[INDEX_OF_DETAILS].split(SLASH_SYMBOL);
        isCorrectFormat(details);
        String description = details[INDEX_OF_DESCRIPTION];
        String date = details[INDEX_OF_DATE].substring(2);
        String startTime = details[INDEX_OF_START_TIME].substring(2);
        String endTime = details[INDEX_OF_END_TIME].substring(2);
        String priority = details[INDEX_OF_PRIORITY].substring(2);
        newEvent =  new Event(description,date,startTime,endTime,priority);
        assert !newEvent.getStartTime().isAfter(newEvent.getEndTime()) :
                Constants.START_TIME_AFTER_END_TIME_ERROR_MESSAGE;
        assert !newEvent.getDate().isBefore(LocalDate.now()) : Constants.DATE_BEFORE_CURRENT_DATE_ERROR_MESSAGE;
    }

    /**
     * Checks if the correct format is used to create an event, if not an exception is thrown.
     *
     * @param details Details entered by user.
     * @throws Exception If any of the fields are missing.
     */
    private void isCorrectFormat(String[] details) throws Exception {
        if (details.length != NUMBER_OF_FIELDS_REQUIRED) {
            throw new EventCreationFormatNotFollowedException();
        }
        try {
            String description = details[INDEX_OF_DESCRIPTION];
            checkDescription(description);
            String date = details[INDEX_OF_DATE];
            checkDate(date);
            String startTime = details[INDEX_OF_START_TIME];
            checkStartTime(startTime);
            String endTime = details[INDEX_OF_END_TIME];
            checkEndTime(endTime);
            String priority = details[INDEX_OF_PRIORITY];
            checkPriority(priority);
        } catch (IndexOutOfBoundsException e) {
            throw new EventCreationFormatNotFollowedException();
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
            throw new EventCreationFormatNotFollowedException();
        }
        if (priority.substring(2).isBlank()) {
            throw new TaskCreationPriorityMissingException();
        }
    }

    /**
     * Checks if the end time is provided in the correct format, throws an exception if wrong format is used.
     *
     * @param endTime String entered by user for end time.
     * @throws Exception If wrong format is used.
     */
    private void checkEndTime(String endTime) throws Exception {
        if (endTime.charAt(0) != END_TIME_IDENTIFIER || endTime.charAt(1) != SINGLE_SPACE_CHARACTER) {
            throw new EventCreationFormatNotFollowedException();
        }
        if (endTime.substring(2).isBlank()) {
            throw new EventCreationEndTimeMissingException();
        }
    }

    /**
     * Checks if the start time is provided in the correct format, throws an exception if wrong format is used.
     *
     * @param startTime String entered by user for start time.
     * @throws Exception If wrong format is used.
     */
    private void checkStartTime(String startTime) throws Exception {
        if (startTime.charAt(0) != START_TIME_IDENTIFIER || startTime.charAt(1) != SINGLE_SPACE_CHARACTER) {
            throw new EventCreationFormatNotFollowedException();
        }
        if (startTime.substring(2).isBlank()) {
            throw new EventCreationStartTimeMissingException();
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
            throw new EventCreationFormatNotFollowedException();
        }
        if (date.substring(2).isBlank()) {
            throw new TaskCreationDateMissingException();
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

    @Override
    public void executeCommand(TaskList taskList, Ui ui)  {
        taskList.addTask(newEvent, ui);
    }

}
