package command;

import common.Messages;
import seedu.atas.Parser;
import seedu.atas.TaskList;
import seedu.atas.Ui;
import tasks.Task;

import java.util.ArrayList;

//@@author
public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    private static final String CLEAR_ALL_COMMAND_USAGE = "- Clear All Tasks: clear all";
    private static final String CLEAR_DONE_COMMAND_USAGE = "- Clear All Completed Tasks: clear done";
    public static final String COMMAND_USAGE = "Clear commands that are available:" + System.lineSeparator()
            + Messages.NEWLINE_INDENT + CLEAR_ALL_COMMAND_USAGE + System.lineSeparator()
            + Messages.NEWLINE_INDENT + CLEAR_DONE_COMMAND_USAGE;

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
            return new CommandResult(String.format(Messages.INCORRECT_ARGUMENT_ERROR,
                    Parser.capitalize(COMMAND_WORD), COMMAND_USAGE));
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
     * Get all the index of tasks that have been completed.
     * @param taskList list of tasks
     * @return ArrayList of index of completed tasks
     */
    public ArrayList<Integer> getCompletedIndex(TaskList taskList) {
        int count = 0;
        ArrayList<Integer> doneIndex = new ArrayList<>();
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
            taskList.deleteAllDoneTask(doneIndex);
            assert taskList.getListSize() == originalTaskSize - doneIndex.size();
            return new CommandResult(Messages.CLEAR_DONE_SUCCESS_MESSAGE);
        }
    }
}
