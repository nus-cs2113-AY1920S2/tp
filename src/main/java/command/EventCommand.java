package command;

import common.Messages;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import tasks.Event;
import tasks.Task;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    public static final String EVENT_COMMAND_WORD = "event";

    protected String eventName;
    protected String eventLocation;
    protected LocalDateTime dateTime;
    protected String comments;

    /**
     * Constructs EventCommand with the supplied parameters.
     * @param eventName String containing name of event
     * @param eventLocation String containing location of event
     * @param dateTime LocalDateTime containing date and time of event
     * @param comments String containing extra comments user might want to tag Event with
     */
    public EventCommand(String eventName, String eventLocation, LocalDateTime dateTime, String comments) {
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.dateTime = dateTime;
        this.comments = comments;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui) {
        Task newEvent = new Event(eventName, eventLocation, dateTime, comments);
        if (isRepeatTask(taskList, newEvent)) {
            return new CommandResult(Messages.REPEAT_TASK_ERROR);
        }
        taskList.addTask(newEvent);
        int listSize = taskList.getListSize();
        return new CommandResult(String.format(Messages.ADD_SUCCESS_MESSAGE,
                newEvent, listSize, listSize == 1 ? "" : "s"));
    }
}
