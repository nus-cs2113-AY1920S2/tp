package seedu.nuke.data.module;

import seedu.nuke.data.category.Category;
import seedu.nuke.data.category.CategoryList;
import seedu.nuke.data.task.Task;
import seedu.nuke.data.task.TaskList;
import seedu.nuke.exception.DataNotFoundException;
import seedu.nuke.exception.DuplicateDataException;

import java.util.ArrayList;

public class ModuleList {
    /* The module list containing all the modules the user is enrolled in */
    public static ArrayList<Module> moduleList = new ArrayList<>();

    /**
     * @return all moduleList
     */
    public static ArrayList<Module> getModuleList() {
        return moduleList;
    }

    public static Module getModule(String moduleCode) throws ModuleNotFoundException {
        for (Module module : moduleList) {
            if (module.getModuleCode().equals(moduleCode.toUpperCase())) {
                return module;
            }
        }
        throw new ModuleNotFoundException();
    }

    public static Category getCategory(String moduleCode, String categoryName)
            throws ModuleNotFoundException, CategoryList.CategoryNotFoundException {
        return getModule(moduleCode).getCategories().getCategory(categoryName);
    }

    /**
     * Checks if the list contains the same module.
     * @param toCheck The module to check
     * @return true if the task exists
     */
    private static boolean contains(Module toCheck) {
        for (Module module : moduleList) {
            if (module.isSameModule(toCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add a module to the module List.
     *
     * @param toAdd the module to add
     */
    public static void add(Module toAdd) throws DuplicateModuleException {
        //check duplicate
        if (contains(toAdd)) {
            throw new DuplicateModuleException();
        } else {
            moduleList.add(toAdd);
        }
    }

    /**
     * Clears all tasks in list.
     */
    public void clear() {
        moduleList.clear();
    }

    /**
     * Deletes a module to the list.
     * @param toDelete the task to remove
     * @throws ModuleNotFoundException If the to delete module does not exist
     */
    public static void delete(Module toDelete) throws ModuleNotFoundException {
        boolean isModuleFoundAndDeleted = moduleList.remove(toDelete);
        if (!isModuleFoundAndDeleted) {
            throw new ModuleNotFoundException();
        }
    }

    /**
     * Deletes a <b>Module</b> with the specified <code>module code</code> in the <b>Module List</b>.
     *
     * @param moduleCode    The module code of the <b>Module</b> to be deleted
     * @throws ModuleNotFoundException  If the module with the specified module code is not found in the <b>Module List</b>
     * @see Module
     */
    public static Module delete(String moduleCode) throws ModuleNotFoundException {
        Module toDelete = getModule(moduleCode);
        moduleList.remove(toDelete);
        return toDelete;
    }

    public static CategoryList filterExact(String moduleCode) throws ModuleNotFoundException {
        return getModule(moduleCode).getCategories();
    }

    public static TaskList filterExact(String moduleCode, String categoryName)
            throws ModuleNotFoundException, CategoryList.CategoryNotFoundException {
        return filterExact(moduleCode).filterExact(categoryName);
    }

    public static ArrayList<Module> filter(String moduleKeyword) {
        ArrayList<Module> filteredModuleList = new ArrayList<>();
        for (Module module : moduleList) {
            if (module.getModuleCode().toLowerCase().contains(moduleKeyword.toLowerCase())) {
                filteredModuleList.add(module);
            }
        }
        return filteredModuleList;
    }

    public static ArrayList<Category> filter(String moduleKeyword, String categoryKeyword) {
        ArrayList<Category> filteredCategoryList = new ArrayList<>();
        for (Module module : filter(moduleKeyword)) {
            filteredCategoryList.addAll(module.getCategories().filter(categoryKeyword));
        }
        return filteredCategoryList;
    }

    public static ArrayList<Task> filter(String moduleKeyword, String categoryKeyword, String taskKeyword) {
        ArrayList<Task> filteredTaskList = new ArrayList<>();
        for (Category category : filter(moduleKeyword, categoryKeyword)) {
            filteredTaskList.addAll(category.getTasks().filter(taskKeyword));
        }
        return filteredTaskList;
    }

    public static class ModuleNotFoundException extends DataNotFoundException {}
    public static class DuplicateModuleException extends DuplicateDataException {}
}
