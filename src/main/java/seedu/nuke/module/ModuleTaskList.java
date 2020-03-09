package seedu.nuke.module;

import seedu.nuke.task.Task;

import java.util.ArrayList;

public class ModuleTaskList {
    ArrayList<Task> taskList;

    public ModuleTaskList() {
        taskList = new ArrayList<>();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void delete(int index) {
        taskList.remove(index);
    }

    public void delete(Task taskToDelete) {
        taskList.remove(taskToDelete);
    }

    public boolean delete(String taskName) {
        for (Task task : taskList) {
            if (task.getDescription().equals(taskName)) {
                taskList.remove(task);
                return true;
            }
        }
        return false;
    }

}
