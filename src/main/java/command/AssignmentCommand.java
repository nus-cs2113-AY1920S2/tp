package command;

import common.Messages;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import tasks.Assignment;
import tasks.Task;

import java.time.LocalDateTime;

public class AssignmentCommand extends Command {
    protected String assignmentName;
    protected String moduleName;
    protected LocalDateTime deadline;
    protected String comments;

    /**
     * Constructs an AssignmentCommand object with the parameters provided.
     * @param assignmentName String containing name of Assignment
     * @param moduleName String containing name of module Assignment belongs to
     * @param deadline LocalDateTime object containing the deadline of Assignment
     * @param comments String containing extra comments user might want to tag Assignment with
     */
    public AssignmentCommand(String assignmentName, String moduleName, LocalDateTime deadline, String comments) {
        this.assignmentName = assignmentName;
        this.moduleName = moduleName;
        this.deadline = deadline;
        this.comments = comments;
    }

    /**
     * Creates new Assignment, adds to TaskList, print Ui messages.
     * @param taskList TaskList object that handles adding Task
     * @param ui Ui object that interacts with user
     * @return CommandResult object with acknowledgment message
     */
    @Override
    public CommandResult execute(TaskList taskList, Ui ui) {
        Task newAssignment = new Assignment(assignmentName, moduleName, deadline, comments);
        taskList.addTask(newAssignment);
        int listSize = taskList.getListSize();
        return new CommandResult(String.format(Messages.ADD_SUCCESS_MESSAGE,
                newAssignment, listSize, listSize == 1 ? "" : "s"));
    }
}
