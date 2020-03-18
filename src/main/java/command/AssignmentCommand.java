package command;

import common.Messages;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Assignment;
import tasks.Task;

import java.time.LocalDateTime;

public class AssignmentCommand extends Command {
    public static final String ASSIGNMENT_COMMAND_WORD = "assignment";

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
        if (isRepeatTask(taskList, newAssignment)) {
            return new CommandResult(Messages.REPEAT_TASK_ERROR);
        }
        taskList.addTask(newAssignment);
        int listSize = taskList.getListSize();
        return new CommandResult(String.format(Messages.ADD_SUCCESS_MESSAGE,
                newAssignment, listSize, listSize == 1 ? "" : "s"));
    }
}
