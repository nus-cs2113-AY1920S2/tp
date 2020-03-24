package command;

import common.Messages;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Event;
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
        try {
            Task task = taskList.getTask(eventIndex);
            //set to not repeat if numOfPeriod = 0, ignoring typeOfPeriod
            if (numOfPeriod == 0) {
                ((Event) task).setNoRepeat();
                return new CommandResult(String.format(Messages.STOP_REPEATING_SUCCESS_MESSAGE, task.getName()));
            //set to repeat otherwise
            } else if (task instanceof Event) {
                ((Event) task).setRepeat(numOfPeriod, typeOfPeriod);
                return new CommandResult(String.format(Messages.REPEATING_SUCCESS_MESSAGE, task.getName(),
                        numOfPeriod == 1 ? "" : numOfPeriod + " ", iconToString(typeOfPeriod),
                        numOfPeriod <= 1 ? "" : "s"));
            }
            return new CommandResult(Messages.REPEAT_ASSIGN_ERROR);
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(String.format(Messages.INVALID_ID_ERROR, getRangeOfValidIndex(taskList)));
        }
    }
}
