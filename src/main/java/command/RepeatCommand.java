package command;

import common.Messages;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Assignment;
import tasks.Event;
import tasks.RepeatEvent;
import tasks.Task;

public class RepeatCommand extends Command {
    public static final String COMMAND_WORD = "repeat";
    public static final String DAILY_ICON = "d";
    public static final String WEEKLY_ICON = "w";
    public static final String MONTHLY_ICON = "m";
    public static final String YEARLY_ICON = "y";
    public static final String DAILY_STRING = "day";
    public static final String WEEKLY_STRING = "week";
    public static final String MONTHLY_STRING = "month";
    public static final String YEARLY_STRING = "year";

    private static final String REPEAT_EVENT_COMMAND_USAGE = "- Make event recur: repeat id/[EVENT INDEX] "
            + "p/[NUM OF PERIOD][TYPE OF PERIOD]";
    private static final String NO_REPEAT_EVENT_COMMAND_USAGE = "- Stop event recur: repeat id/[EVENT INDEX] p/0";
    public static final String COMMAND_USAGE =
            "Repeat commands that are available: (Available Periods are: d, w, m, y)" + System.lineSeparator()
            + Messages.NEWLINE_INDENT + REPEAT_EVENT_COMMAND_USAGE + System.lineSeparator()
            + Messages.NEWLINE_INDENT + NO_REPEAT_EVENT_COMMAND_USAGE;

    private int eventIndex;
    private int numOfPeriod;
    private String typeOfPeriod;

    /**
     * Construct a RepeatCommand class to initialize eventIndex, numOfPeriod and typeOfPeriod.
     * @param eventIndex index of the event that is being changed
     * @param numOfPeriod number of period before event repeats
     * @param typeOfPeriod type of period which event repeats - could be daily, weekly, monthly or yearly
     */
    public RepeatCommand(int eventIndex, int numOfPeriod, String typeOfPeriod) {
        this.eventIndex = eventIndex;
        this.numOfPeriod = numOfPeriod;
        this.typeOfPeriod = typeOfPeriod;
    }

    private String iconToString(String type) {
        switch (type) {
        case(DAILY_ICON):
            return DAILY_STRING;
        case(WEEKLY_ICON):
            return WEEKLY_STRING;
        case(MONTHLY_ICON):
            return MONTHLY_STRING;
        case(YEARLY_ICON):
            return YEARLY_STRING;
        default:
            assert false;
        }
        assert false;
        return type;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui) {
        Task task;
        try {
            task = taskList.getTask(eventIndex);
            if (task instanceof Assignment) {
                return new CommandResult(String.format(Messages.REPEAT_ASSIGN_ERROR, task.getName()));
            }
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(String.format(Messages.INVALID_ID_ERROR, getRangeOfValidIndex(taskList)));
        }
        assert (task instanceof Event);

        //unset repeat if numOfPeriod = 0, ignoring typeOfPeriod
        if (numOfPeriod == 0) {
            return unsetRepeat(taskList, (Event) task);
        //set to repeat otherwise
        } else {
            return setRepeat(taskList, ((Event) task));
        }
    }

    //String name, String location, LocalDateTime startDateTime, LocalDateTime endDateTime, String comments,
    //                       int numOfPeriod, String typeOfPeriod, LocalDateTime originalDateAndTime, int periodCounter

    private CommandResult setRepeat(TaskList taskList, Event event) {
        RepeatEvent newRepeatEvent = new RepeatEvent(event.getName(), event.getLocation(), event.getDateAndTime(),
                event.getEndDateAndTime(), event.getComments(), numOfPeriod, typeOfPeriod, event.getDateAndTime(), 0);
        taskList.editTask(eventIndex, newRepeatEvent);
        return new CommandResult(String.format(Messages.REPEATING_SUCCESS_MESSAGE, newRepeatEvent.getName(),
                numOfPeriod == 1 ? "" : numOfPeriod + " ", iconToString(typeOfPeriod),
                numOfPeriod <= 1 ? "" : "s"));
    }

    private CommandResult unsetRepeat(TaskList taskList, Event event) {
        if (event instanceof RepeatEvent) {
            Event newEvent = new Event(event.getName(), event.getLocation(), event.getDateAndTime(),
                    event.getEndDateAndTime(), event.getComments());
            taskList.editTask(eventIndex, newEvent);
            return new CommandResult(String.format(Messages.STOP_REPEATING_SUCCESS_MESSAGE, newEvent.getName()));
        }
        return new CommandResult(String.format(Messages.REPEAT_NOT_SET_ERROR, event.getName()));
    }
}
