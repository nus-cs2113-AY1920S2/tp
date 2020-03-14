package command;

import common.Messages;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import tasks.Task;

import java.util.ArrayList;

public class ClearCommand  extends Command {
    public static final String CLEAR_COMMAND_WORD = "clear";
    private static final String ALL_CLEAR_COMMAND = "";
    protected final String clearAllParam  = "all";
    protected final String clearDoneParam = "done";
    protected final String clearParam;
    protected ArrayList<Integer> doneIndex;

    /**
     * Constructs the clear command.
     */
    public ClearCommand(String clearParam) {
        this.clearParam = clearParam;
    }


    @Override
    public CommandResult execute(TaskList taskList, Ui ui) {
        switch (clearParam == null ? ALL_CLEAR_COMMAND : clearParam) {
        case (clearAllParam):
            return clearAll(taskList);
        case (clearDoneParam):
            return clearDone(taskList);
        default:
            return new CommandResult(Messages.CLEAR_INCORRECT_FORMAT_ERROR);
        }
    }

    /**
     * Clears all the tasks in the taskList.
     * @param taskList list storing all the tasks
     * @return messages for user
     */
    public CommandResult clearAll(TaskList taskList) {
        if (taskList.getListSize() == 0) {
            return new CommandResult(Messages.NO_TASKS_MSG);
        } else {
            taskList.clearList();
            return new CommandResult(Messages.CLEAR_SUCCESS_MESSAGE);
        }
    }

    /**
     * Returns an array of index of tasks that are completed.
     * @param taskList list storing all the tasks
     * @return an array for index of completed tasks
     */
    public ArrayList<Integer> getDoneIndex(TaskList taskList) {
        ArrayList<Integer> index = new ArrayList<Integer>();
        int indexNumber = 0;
        for (Task task: taskList.getTaskArray()) {
            if (task.getIsDone() == true) {
                index.add(indexNumber);
            }
            indexNumber++;
        }
        return index;
    }

    /**
     * Deletes all the completed tasks.
     * @param taskList list storing all the tasks
     */
    public void deleteDoneTasks(TaskList taskList) {
        doneIndex = getDoneIndex(taskList);
        for (int i: doneIndex) {
            taskList.deleteTask(i);
        }
    }

    /**
     * Deletes all the completed tasks and displays user messages.
     * @param taskList list storing all the tasks.
     * @return user messages
     */
    public CommandResult clearDone(TaskList taskList) {
        doneIndex = getDoneIndex(taskList);
        System.out.println(doneIndex.size());
        if (taskList.getListSize() == 0) {
            return new CommandResult(Messages.EMPTY_TASKLIST_MESSAGE);
        } else if (doneIndex.size() == 0) {
            return new CommandResult(Messages.EMPTY_DONE_CLEAR_ERROR);
        } else {
            deleteDoneTasks(taskList);
            return new CommandResult(Messages.CLEAR_DONE_SUCCESS_MESSAGE);
        }
    }
}
