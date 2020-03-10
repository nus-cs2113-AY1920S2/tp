package seedu.nuke.data;

import seedu.nuke.Nuke;
import seedu.nuke.module.Module;
import seedu.nuke.task.Task;

import java.util.ArrayList;

/**
 * the management system
 */
public class DataManager {

    private ArrayList<Task> allTasks;

    public DataManager(ModuleManager moduleManager) {
        for (Module module:ModuleManager.getModuleList()
             ) {
            allTasks.addAll(module.getTaskManager().allTasks);
        }
    }

    public ArrayList<Task> getAllTasks() {
        return allTasks;
    }
}
