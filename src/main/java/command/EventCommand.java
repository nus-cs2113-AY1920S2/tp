package command;

import seedu.duke.TaskList;
import seedu.duke.Ui;
import tasks.Event;
import tasks.Task;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    protected String eventName;
    protected String eventLocation;
    protected LocalDateTime dateTime;
    protected String comments;
    public EventCommand(String eventName, String eventLocation, LocalDateTime dateTime, String comments) {
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.dateTime = dateTime;
        this.comments = comments;
    }

    /**
     * Creates new Event, adds to TaskList, print Ui messages.
     * @param taskList TaskList object that handles adding Task
     * @param ui Ui object that interacts with user
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task newEvent = new Event(eventName, eventLocation, dateTime, comments);
        taskList.addTask(newEvent);
        ui.showAddTaskMessage(newEvent, taskList.getListSize());
    }
}
