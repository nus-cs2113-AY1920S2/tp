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
            assert taskList.getListSize() == 0;
            return new CommandResult(Messages.CLEAR_SUCCESS_MESSAGE);
        }
    }

    /**
     * Removes all completed tasks in 1 iteration of loop.
     * @param tasks TaskList containing all the tasks
     * @param deleted number of tasks that have already been deleted
     * @return updated number of tasks that have been deleted
     */
    public int getDeleted(ArrayList<Task> tasks, int deleted) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getIsDone()) {
                tasks.remove(i);
                deleted++;
            }
        }
        return deleted;
    }

    /**
     * Deletes all completed tasks.
     * @param taskList ArrayList containing all the tasks
     * @return ArrayList containing the new taskList
     */
    public ArrayList<Task> deleteCompletedTask(TaskList taskList, ArrayList<Integer> doneIndex) {
        ArrayList<Task> tasks = taskList.getTaskArray();
        int deleted = 0;
        while (deleted < doneIndex.size()) {
            deleted = getDeleted(tasks,deleted);
        }
        return tasks;
    }

    /**
     * Get all the index of tasks that have been completed.
     * @param taskList list of tasks
     * @return ArrayList of index of completed tasks
     */
    public ArrayList<Integer> getCompletedIndex(TaskList taskList) {
        int count = 0;
        ArrayList<Integer> doneIndex = new ArrayList<Integer>();
        for (Task task: taskList.getTaskArray()) {
            if (task.getIsDone()) {
                doneIndex.add(count);
            }
            count++;
        }
        return doneIndex;
    }

    /**
     * Deletes all the completed tasks and displays user messages.
     * @param taskList list storing all the tasks.
     * @return user messages
     */
    public CommandResult clearDone(TaskList taskList) {
        int originalTaskSize = taskList.getListSize();
        ArrayList<Integer> doneIndex = getCompletedIndex(taskList);
        if (taskList.getListSize() == 0) {
            return new CommandResult(Messages.EMPTY_TASKLIST_MESSAGE);
        } else if (doneIndex.size() == 0) {
            return new CommandResult(Messages.EMPTY_DONE_CLEAR_ERROR);
        } else {
            ArrayList<Task> tasks = deleteCompletedTask(taskList, doneIndex);
            taskList.updateTaskList(tasks);
            assert taskList.getListSize() == originalTaskSize - doneIndex.size();
            return new CommandResult(Messages.CLEAR_DONE_SUCCESS_MESSAGE);
        }
    }
}
