
package seedu.nuke.data;

import seedu.nuke.directory.Root;
import seedu.nuke.exception.DuplicateDataException;
import seedu.nuke.exception.ModuleNotProvidedException;
import seedu.nuke.exception.ModuleNotFoundException;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

/**
 * a manager that manages all modules.
 * have an arraylist that contains all modules
 * deals with operations that are related to modules
 */
public class ModuleManager implements Iterable<Module> {
    private static Root root;
    private static ArrayList<Module> moduleList;
    private ArrayList<String> moduleCodes;
    private ArrayList<Task> allTasks;
    private HashMap<String, String> modulesMap;

    public ModuleManager(Root root, HashMap<String, String> modulesMap) {
        this.modulesMap = modulesMap;
        this.root = root;
    }

    /**
     * This mothod is to restore the list of modules when loading from the json data file.
     * @param moduleList an ArrayList of Module objects parsed from the json string in the data file
     */
    public void setModuleList(ArrayList<Module> moduleList) {
        this.moduleCodes = new ArrayList<>();
        this.allTasks = new ArrayList<>();
        this.moduleList = moduleList;
        for (Module module: moduleList) {
            moduleCodes.add(module.getModuleCode());
            for (Task task: module.getTaskManager().getAllTasks()) {
                allTasks.add(task);
            }
        }
        assert moduleList.size() == moduleCodes.size() : "moduleList size does not equal to moduleCodes size, check!";
    }

    /**
     * method to return all the modules.
     * @return all modules
     */
    public ArrayList<Module> getModuleList() {
        return moduleList;
    }

    public ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    /**
     * return total number of tasks in all modules.
     */
    public int countAllTasks() {
        return allTasks.size();
    }

    //}

    /**
     * Checks if the list contains an equivalent task as the given description.
     * @param moduleCode the module code to check if provided by NUS currently
     * @return true if NUS is providing the module currently
     */
    public static boolean contains(String moduleCode) {
        for (Module p : moduleList) {
            if (p.getModuleCode().equals(moduleCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add a module to the module List.
     *
     * @param moduleCode the module code of the module to add
     */
    public void add(String moduleCode) throws DuplicateModuleException, ModuleNotProvidedException {
        //check duplicate
        if (moduleCodes.contains(moduleCode)) {
            throw new DuplicateModuleException();
        } else if (!modulesMap.containsKey(moduleCode)) {
            throw new ModuleNotProvidedException();
        } else {
            Module toAdd = new Module(root, moduleCode, modulesMap.get(moduleCode), null);
            moduleList.add(toAdd);
            moduleCodes.add(moduleCode);
        }
    }

    /**
     * return an orders lists of all tasks of all modules.
     * @return an ArrayList of String which represents the ordered list
     */
    public ArrayList<String> checkDeadline() {
        ArrayList<String> deadlines = new ArrayList<>();
        sortAllTasks();

        for (Task task: allTasks) {
            deadlines.add(String.format("%-30s", task.getDescription()) + " "
                    + String.format("%-8s", task.getModuleCode()) + "   deadline: " + task.getDeadline());
        }
        return deadlines;
    }

    /**
     * sort all tasks of all modules in ascending order of deadlines.
     */
    public void sortAllTasks() {
        Collections.sort(allTasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                String t1Deadline = t1.getDeadline() == null ? "" : t1.getDeadline().toString();
                String t2Deadline = t2.getDeadline() == null ? "" : t2.getDeadline().toString();
                return t1Deadline.compareToIgnoreCase(t2Deadline);
            }
        });
    }

    /**
     * Clears all tasks in list.
     */
    public void clear() {
        moduleList.clear();
    }

    public Module getLastAddedModule() {
        return moduleList.get(moduleList.size() - 1);
    }

    /**
     * Remove a module to the list.
     * @param toDelete the task to remove
     * @throws ModuleNotFoundException if the to-remove module does not exist
     */
    public void delete(Module toDelete) throws ModuleNotFoundException {
        boolean isModuleFoundAndDeleted = moduleList.remove(toDelete);
        if (!isModuleFoundAndDeleted) {
            throw new ModuleNotFoundException("");
        }
    }

    /**
     * Deletes a <b>Module</b> with <code>module code</code> in the <b>Module List</b>.
     *
     * @param moduleCode    The module code of the <b>Module</b> to be deleted
     * @throws ModuleNotFoundException
     * If the module with the specified module code is not found in the <b>Module List</b>
     * @see Module
     */
    public Module delete(String moduleCode) throws ModuleNotFoundException {
        if (getModuleWithCode(moduleCode) != null) {
            Module toDelete = getModuleWithCode(moduleCode);
            allTasks.removeIf(task -> task.getModuleCode().toUpperCase().equals(moduleCode));
            moduleList.removeIf(module -> module.getModuleCode().equalsIgnoreCase(moduleCode));
            return toDelete;
        } else {
            throw new ModuleNotFoundException("");
        }
    }

    @Override
    public Iterator<Module> iterator() {
        return moduleList.iterator();
    }

    /**
     * get the index of new-to-add task.
     * @return the next-to-add task index
     */
    public int getNextTaskIndex() {
        return moduleList.size();
    }

    public void addTaskToModule(TaskManager taskManager, Task taskToAdd) {
        taskManager.addTask(taskToAdd);
        allTasks.add(taskToAdd);
    }

    public void removeTask(TaskManager taskManager, Task taskToDelete) {
        taskManager.removeTask(taskToDelete);
        allTasks.remove(taskToDelete);
    }

    public class DuplicateModuleException extends DuplicateDataException {
    }

    /**
     * get a module object according to moduleCode.
     * @param moduleCode the moduleCode of the module
     * @return a module object that has the moduleCode
     */
    public static Module getModuleWithCode(String moduleCode) {
        for (Module module: moduleList
             ) {
            if (module.getModuleCode().equals(moduleCode)) {
                assert module.getModuleCode().equals(moduleCode);
                return module;
            }
        }
        return null;
    }

    public static Root getRoot() {
        return root;
    }
}