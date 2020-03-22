package command;

import common.Messages;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Event;
import tasks.Task;

public class RepeatCommand extends Command {
    public static final String REPEAT_COMMAND_WORD = "repeat";

    private int eventIndex;
    private int numOfPeriod;
    private String typeOfPeriod;

    public RepeatCommand (int eventIndex, int numOfPeriod, String typeOfPeriod) {
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
