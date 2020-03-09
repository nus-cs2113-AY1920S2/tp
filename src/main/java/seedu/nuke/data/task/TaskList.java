package seedu.nuke.data.task;

import seedu.nuke.exception.DataNotFoundException;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void add(Task toAdd) {
        taskList.add(toAdd);
    }

    public void delete(int index) {
        taskList.remove(index);
    }

    public void delete(Task toDelete) throws TaskNotFoundException {
        boolean isTaskFoundAndDeleted = taskList.remove(toDelete);
        if (!isTaskFoundAndDeleted) {
            throw new TaskNotFoundException();
        }
    }

    public void delete(String taskName) throws TaskNotFoundException {
        for (Task task : taskList) {
            if (task.getDescription().equals(taskName)) {
                taskList.remove(task);
                return;
            }
        }
        throw new TaskNotFoundException();
    }

    public static class TaskNotFoundException extends DataNotFoundException {}
}
