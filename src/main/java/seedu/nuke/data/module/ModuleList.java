package seedu.nuke.data.module;

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

    /**
     * Checks if the list contains an equivalent task as the given description.
     * @param toCheck the task to-check
     * @return true if the task exists
     */
    public boolean contains(Module toCheck) {
        for (Module p : moduleList) {
            if (p.isSameModule(toCheck)) {
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
        if (moduleList.contains(toAdd)) {
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
     * Remove a module to the list.
     * @param toDelete the task to remove
     * @throws ModuleNotFoundException if the to-remove module does not exist
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
        for (Module module : moduleList) {
            if (module.getModuleCode().toUpperCase().equals(moduleCode)) {
                moduleList.remove(module);
                return module;
            }
        }
        throw new ModuleNotFoundException();
    }


    public static class ModuleNotFoundException extends DataNotFoundException {}
    public static class DuplicateModuleException extends DuplicateDataException {}
}
