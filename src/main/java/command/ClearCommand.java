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
     * Deletes all completed tasks.
     * @param taskList ArrayList containing all the tasks
     * @return ArrayList containing the new taskList
     */
    public ArrayList<Task> deleteCompletedTask(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTaskArray();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getIsDone()) {
                tasks.remove(i);
            }
        }
        return tasks;
    }

    /**
     * Returns the total number of completed tasks.
     * @param taskList ArrayList storing all the tasks
     * @return number of completed tasks
     */
    public int getNumberCompletedTask(TaskList taskList) {
        int count = 0;
        ArrayList<Task> tasks = taskList.getTaskArray();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getIsDone()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Deletes all the completed tasks and displays user messages.
     * @param taskList list storing all the tasks.
     * @return user messages
     */
    public CommandResult clearDone(TaskList taskList) {
        if (taskList.getListSize() == 0) {
            return new CommandResult(Messages.EMPTY_TASKLIST_MESSAGE);
        } else if (getNumberCompletedTask(taskList) == 0) {
            return new CommandResult(Messages.EMPTY_DONE_CLEAR_ERROR);
        } else {
            int size = taskList.getListSize();
            int completed = getNumberCompletedTask(taskList);
            ArrayList<Task> newTaskList = deleteCompletedTask(taskList);
            taskList.updateTaskList(newTaskList);
            assert taskList.getListSize() == size - completed;
            return new CommandResult(Messages.CLEAR_DONE_SUCCESS_MESSAGE);
        }
    }
}
