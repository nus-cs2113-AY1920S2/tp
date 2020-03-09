package seedu.nuke.data;

import seedu.nuke.Nuke;
import seedu.nuke.task.Task;

import java.util.ArrayList;

/**
 * the management system
 */
public class DataManager {

    private ArrayList<Task> taskList;

    public DataManager(ArrayList<Module> modules) {
        taskList = new ArrayList<>();
        for(Module module: modules) {
            
        }
    }


}
