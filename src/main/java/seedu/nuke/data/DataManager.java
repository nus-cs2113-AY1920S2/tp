package seedu.nuke.data;

import seedu.nuke.Nuke;
import seedu.nuke.module.Module;
import seedu.nuke.task.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * the management system
 */
public class DataManager {

    private ArrayList<Task> allTasks = new ArrayList<>();

    public DataManager(ModuleManager moduleManager) {
        for (Module module:moduleManager.getModuleList()
             ) {
            allTasks.addAll(module.getTaskManager().allTasks);
        }
    }

    public void setAllTasks(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
    }

    public void sortAllTasks() {
        Collections.sort(allTasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t1.getDeadline().toString().compareToIgnoreCase(t2.getDeadline().toString());
            }
        });
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
            deadlines.add(task.getDescription() + task.getModuleCode() + "  deadline: "+task.getDeadline());
        }
        return deadlines;
    }

    public void addTask(Task toAdd){
        this.allTasks.add(toAdd);
    }

    public void removeTask(Task toRemove){
        this.allTasks.remove(toRemove);
    }
}
