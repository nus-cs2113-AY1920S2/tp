package command;

import common.Messages;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Event;
import tasks.Task;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    public static final String EVENT_COMMAND_WORD = "event";
    public static final String COMMAND_USAGE = "Add Event: "
            + "event n/[EVENT NAME] l/[LOCATION] d/[dd/MM/yy HHmm - HHmm] c/[COMMENTS]";

    protected String eventName;
    protected String eventLocation;
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;
    protected String comments;

    /**
     * Constructs EventCommand with the supplied parameters.
     * @param eventName String containing name of event
     * @param eventLocation String containing location of event
     * @param startDateTime LocalDateTime containing the starting date and time of event
     * @param endDateTime LocalDateTime containing the ending date and time of event
     * @param comments String containing extra comments user might want to tag Event with
     */
    public EventCommand(String eventName, String eventLocation, LocalDateTime startDateTime,
                        LocalDateTime endDateTime, String comments) {
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.comments = comments;
    }

    @Override
    public CommandResult execute(TaskList taskList, Ui ui) {
        Task newEvent = new Event(eventName, eventLocation, startDateTime, endDateTime, comments);
        if (isRepeatTask(taskList, newEvent)) {
            return new CommandResult(Messages.REPEAT_TASK_ERROR);
        }
        taskList.addTask(newEvent);
        int listSize = taskList.getListSize();
        return new CommandResult(String.format(Messages.ADD_SUCCESS_MESSAGE,
                newEvent, listSize, listSize == 1 ? "" : "s"));
    }
}
