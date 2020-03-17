
package seedu.nuke.data;

import seedu.nuke.directory.Category;
import seedu.nuke.directory.Root;
import seedu.nuke.exception.DataNotFoundException;
import seedu.nuke.exception.DuplicateDataException;
import seedu.nuke.exception.ModuleNotProvidedException;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

/**
 * A manager that manages all modules.
 * Contains a Module List and performs operations related to modules
 */
public class ModuleManager implements Iterable<Module> {
    private static Root root;
    private static ArrayList<Module> moduleList;
    private static ArrayList<Task> allTasks;
    private static HashMap<String, String> modulesMap;

    public ModuleManager(Root root, HashMap<String, String> modulesMap) {
        ModuleManager.modulesMap = modulesMap;
        ModuleManager.root = root;
    }

    public static Root getRoot() {
        return root;
    }

    public static HashMap<String, String> getModulesMap() {
        return modulesMap;
    }

    /**
     * This method is to restore the list of modules when loading from the json data file.
     * @param moduleList an ArrayList of Module objects parsed from the json string in the data file
     */
    public void setModuleList(ArrayList<Module> moduleList) {
        ModuleManager.moduleList = moduleList;
    }

    /**
     * method to return all the modules.
     * @return all modules
     */
    public ArrayList<Module> getModuleList() {
        return moduleList;
    }

    /**
     * Searches the Module List for the module with the specified module code.
     *
     * @param moduleCode
     *  The module code of the module to be found
     * @return
     *  The found module with the specified module code
     * @throws ModuleNotFoundException
     *  If the module is not found
     */
    public static Module getModule(String moduleCode) throws ModuleNotFoundException {
        for (Module module : moduleList) {
            if (module.getModuleCode().equals(moduleCode.toUpperCase())) {
                return module;
            }
        }
        throw new ModuleNotFoundException();
    }

    /**
     * Searches the Module List for the module with the specified module code, then searches the module's
     * Category List for the category with the specified name.
     *
     * @param moduleCode
     *  The module code of the module to be found
     * @param categoryName
     *  The name of the category to be found
     * @return
     *  The found category with the specified name, with its parent module with the specified module code
     * @throws ModuleNotFoundException
     *  If the module is not found
     * @throws CategoryManager.CategoryNotFoundException
     *  If the category is not found
     */
    public static Category getCategory(String moduleCode, String categoryName)
            throws ModuleNotFoundException, CategoryManager.CategoryNotFoundException {
        return getModule(moduleCode).getCategories().getCategory(categoryName);
    }

    /**
     * Searches the Module List for the module with the specified module code, then searches the module's
     * Category List for the category with the specified name, then searches the category's Task List for
     * the task with the specified description.
     *
     * @param moduleCode
     *  The module code of the module to be found
     * @param categoryName
     *  The name of the category to be found
     * @param taskDescription
     *  The description of the task to be found
     * @return
     *  The found task with the specified description, with its parent category and module with the specified
     *  module code and category name respectively
     * @throws ModuleNotFoundException
     *  If the module is not found
     * @throws CategoryManager.CategoryNotFoundException
     *  If the category is not found
     * @throws TaskManager.TaskNotFoundException
     *  If the task is not found
     */
    public static Task getTask(String moduleCode, String categoryName, String taskDescription)
            throws ModuleNotFoundException, CategoryManager.CategoryNotFoundException,
            TaskManager.TaskNotFoundException {
        return getCategory(moduleCode, categoryName).getTasks().getTask(taskDescription);
    }

    /**
     * Checks for duplicates of the specified module in the Module List.
     * @param toCheck
     *  The module to check
     * @return
     *  <code>TRUE</code> if there exists a duplicate, and <code>FALSE</code> otherwise
     */
    public static boolean contains(Module toCheck) {
        for (Module module : moduleList) {
            if (module.isSameModule(toCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add a module to the Module List.
     *
     * @param toAdd
     *  The module to be added
     */
    public void add(Module toAdd) throws DuplicateModuleException, ModuleNotProvidedException {
        //check duplicate
        if (contains(toAdd)) {
            throw new DuplicateModuleException();
        } else if (!modulesMap.containsKey(toAdd.getModuleCode())) {
            throw new ModuleNotProvidedException();
        } else {
            String moduleTitle = modulesMap.get(toAdd.getModuleCode());
            toAdd.setTitle(moduleTitle);
            moduleList.add(toAdd);
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

    /**
     * Delete a module to the Module List.
     * @param toDelete
     *  The module to be deleted
     */
    public static void delete(Module toDelete) {
        moduleList.remove(toDelete);
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
        if (getModuleWithCode(moduleCode)!= null){
            Module toDelete = getModuleWithCode(moduleCode);
            allTasks.removeIf(task -> task.getModuleCode().toUpperCase().equals(moduleCode));
            moduleList.removeIf(module -> module.getModuleCode().equalsIgnoreCase(moduleCode));
            return toDelete;
        } else {
            throw new ModuleNotFoundException();
        }
    }

    /* Retrieve a specific Data (Category / Task / File) List. Only 1 list is retrieved */

    /**
     * Retrieves the Category List of the module with the specified module code
     *
     * @param moduleCode
     *  The module code of the module to retrieve the Category List from
     * @return
     *  The Category List of the found module
     * @throws ModuleNotFoundException
     *  If the module with the specified module code is not found in the Module List
     */
    public static CategoryManager retrieve(String moduleCode) throws ModuleNotFoundException {
        return getModule(moduleCode).getCategories();
    }

    /**
     * Retrieves the Task List of the category with the specified name and has its parent module with the
     * specified module code
     *
     * @param moduleCode
     *  The module code of the module containing the category to retrieve the Task List from
     * @param categoryName
     *  The name of the category to retrieve the Task List from
     * @return
     *  The Task List of the found category
     * @throws ModuleNotFoundException
     *  If the module with the specified module code is not found in the Module List
     * @throws CategoryManager.CategoryNotFoundException
     *  If the category with the specified name is not found in the Category List
     */
    public static TaskManager retrieve(String moduleCode, String categoryName)
            throws ModuleNotFoundException, CategoryManager.CategoryNotFoundException {
        return retrieve(moduleCode).retrieve(categoryName);
    }

    /* Filters for data (module / task / category) that *contains* given keywords (i.e. not exact match)
     *  in a case-insensitive manner. There may be multiple data that matches. */

    /**
     * Filter for modules in the Module List with module code that contains the specified keyword.
     * Filtering is done in a case-insensitive manner.
     *
     * @param moduleKeyword
     *  The keyword to filter the modules
     * @return
     *  The list of filtered modules
     */
    public static ArrayList<Module> filter(String moduleKeyword) {
        ArrayList<Module> filteredModuleList = new ArrayList<>();
        for (Module module : moduleList) {
            if (module.getModuleCode().toLowerCase().contains(moduleKeyword.toLowerCase())) {
                filteredModuleList.add(module);
            }
        }
        return filteredModuleList;
    }

    /**
     * Filter for modules in the Module List with module code that contains the specified module keyword,
     * then for categories in the Category List of the filtered modules with name that contains the specified
     * category keyword.
     * Filtering is done in a case-insensitive manner.
     *
     * @param moduleKeyword
     *  The keyword to filter the modules
     * @param categoryKeyword
     *  The keyword to filter the categories
     * @return
     *  The list of filtered categories
     */
    public static ArrayList<Category> filter(String moduleKeyword, String categoryKeyword) {
        ArrayList<Category> filteredCategoryList = new ArrayList<>();
        for (Module module : filter(moduleKeyword)) {
            filteredCategoryList.addAll(module.getCategories().filter(categoryKeyword));
        }
        return filteredCategoryList;
    }

    /**
     * Filter for modules in the Module List with module code that contains the specified module keyword,
     * then for categories in the Category List of the filtered modules with name that contains the specified
     * category keyword, then for tasks in the Task List of the filtered categories with description that
     * contains the specified task keyword.
     * Filtering is done in a case-insensitive manner.
     *
     * @param moduleKeyword
     *  The keyword to filter the modules
     * @param categoryKeyword
     *  The keyword to filter the categories
     * @param taskKeyword
     * The keyword to filter the tasks
     * @return
     *  The list of filtered tasks
     */
    public static ArrayList<Task> filter(String moduleKeyword, String categoryKeyword, String taskKeyword) {
        ArrayList<Task> filteredTaskList = new ArrayList<>();
        for (Category category : filter(moduleKeyword, categoryKeyword)) {
            filteredTaskList.addAll(category.getTasks().filter(taskKeyword));
        }
        return filteredTaskList;
    }

    /* Filters for data (module / task / category) that *matches exactly* the given keywords in a case-insensitive
     *  manner. Empty keywords, however, will instead collect all instances of the data.
     *  There may be multiple data that matches. */

    /**
     * Filter for modules in the Module List with module code that matches <b>exactly</b> the specified keyword.
     * Filtering is done in a case-insensitive manner.
     *
     * @param moduleKeyword
     *  The keyword to filter the modules
     * @return
     *  The list of filtered modules
     */
    public static ArrayList<Module> filterExact(String moduleKeyword) {
        // Returns all modules in the Module List if no keyword is provided.
        if (moduleKeyword.isEmpty()) {
            return moduleList;
        }

        ArrayList<Module> filteredModuleList = new ArrayList<>();
        for (Module module : moduleList) {
            if (module.getModuleCode().toLowerCase().equals(moduleKeyword.toLowerCase())) {
                filteredModuleList.add(module);
            }
        }
        return filteredModuleList;
    }

    /**
     * Filter for modules in the Module List with module code that matches <b>exactly</b> the specified module
     * keyword, then for categories in the Category List of the filtered modules with name that that matches
     * <b>exactly</b> the specified category keyword.
     * Filtering is done in a case-insensitive manner.
     *
     * @param moduleKeyword
     *  The keyword to filter the modules
     * @param categoryKeyword
     *  The keyword to filter the categories
     * @return
     *  The list of filtered categories
     */
    public static ArrayList<Category> filterExact(String moduleKeyword, String categoryKeyword) {
        ArrayList<Category> filteredCategoryList = new ArrayList<>();
        for (Module module : filterExact(moduleKeyword)) {
            filteredCategoryList.addAll(module.getCategories().filterExact(categoryKeyword));
        }
        return filteredCategoryList;
    }

    /**
     * Filter for modules in the Module List with module code that matches <b>exactly</b> the specified module
     * keyword, then for categories in the Category List of the filtered modules with name that that matches
     * <b>exactly</b> the specified category keyword, then for tasks in the Task List of the filtered categories
     * with description that that matches <b>exactly</b> the specified task keyword
     * Filtering is done in a case-insensitive manner.
     *
     * @param moduleKeyword
     *  The keyword to filter the modules
     * @param categoryKeyword
     *  The keyword to filter the categories
     * @param taskKeyword
     *  The keyword to filter the tasks
     * @return
     *  The list of filtered tasks
     */
    public static ArrayList<Task> filterExact(String moduleKeyword, String categoryKeyword, String taskKeyword) {
        ArrayList<Task> filteredTaskList = new ArrayList<>();
        for (Category category : filterExact(moduleKeyword, categoryKeyword)) {
            filteredTaskList.addAll(category.getTasks().filterExact(taskKeyword));
        }
        return filteredTaskList;
    }

    @Override
    public Iterator<Module> iterator() {
        return moduleList.iterator();
    }

    public void addTaskToModule(TaskManager taskManager, Task taskToAdd) throws TaskManager.DuplicateTaskException {
        taskManager.add(taskToAdd);
        allTasks.add(taskToAdd);
    }

    public void removeTask(TaskManager taskManager, Task taskToDelete) {
        taskManager.delete(taskToDelete);
        allTasks.remove(taskToDelete);
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

    /**
     * return total number of tasks in all modules.
     */
    public int countAllTasks() {
        final String NONE = "";
        return filter(NONE, NONE, NONE).size();
    }

    public static class ModuleNotFoundException extends DataNotFoundException {}
    public static class DuplicateModuleException extends DuplicateDataException {}
}