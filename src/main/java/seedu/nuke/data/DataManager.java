package seedu.nuke.data;

import seedu.nuke.Nuke;
import seedu.nuke.module.Module;
import seedu.nuke.task.Task;

import java.util.ArrayList;
import java.util.Collections;

/**
 * the management system
 */
public class DataManager {

    private ArrayList<Task> allTasks = new ArrayList<>();

    public DataManager(ModuleManager moduleManager) {
        for (Module module:ModuleManager.getModuleList()
             ) {
            allTasks.addAll(module.getTaskManager().allTasks);
        }
    }

    public void sortAllTasks() {
        Collections.sort(allTasks);
    }

    public ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    public int countAllTasks() {
        return allTasks.size();
    }

    public ArrayList<String> checkDeadline() {
        ArrayList<String> deadlines = new ArrayList<>();
        sortAllTasks();
        for (Task task: allTasks) {
            deadlines.add("Task: " + task.getDescription() + "  deadline: "+task.getDeadline());
        }
        return deadlines;
    }

    public void addTask(Task toAdd){
        this.allTasks.add(toAdd);
    }
}
