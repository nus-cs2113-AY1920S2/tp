package command;

import common.Messages;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Event;
import tasks.Task;

public class RepeatCommand extends Command {
    public static final String REPEAT_COMMAND_WORD = "repeat";
    private static final String REPEAT_EVENT_COMMAND_USAGE = "Make event recur: repeat [EVENT INDEX] "
            + "[NUM OF PERIOD] [TYPE OF PERIOD]";
    private static final String NO_REPEAT_EVENT_COMMAND_USAGE = "Stop event recur: repeat [EVENT INDEX] "
            + "0 D";
    public static final String COMMAND_USAGE =
            "Repeat commands that are available: (Available Periods are: D, W, M, Y)" + System.lineSeparator()
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

    @Override
    public CommandResult execute(TaskList taskList, Ui ui) {
        try {
            Task task = taskList.getTask(eventIndex);
            //set to not repeat if numOfPeriod = 0, ignoring typeOfPeriod
            if (numOfPeriod == 0) {
                ((Event) task).setNoRepeat();
                return new CommandResult(String.format(Messages.REMOVE_REPEATING_SUCCESS_MESSAGE, task.getName()));
            //set to repeat otherwise
            } else if (task instanceof Event) {
                ((Event) task).setRepeat(numOfPeriod, typeOfPeriod);
                return new CommandResult(String.format(Messages.ADD_REPEATING_SUCCESS_MESSAGE,
                        task.getName(), numOfPeriod, typeOfPeriod, numOfPeriod <= 1 ? "" : "s"));
            }
            return new CommandResult(Messages.INVALID_EVENT_REPEAT_ERROR);
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(Messages.INVALID_REPEAT_ERROR);
        }
    }
}
