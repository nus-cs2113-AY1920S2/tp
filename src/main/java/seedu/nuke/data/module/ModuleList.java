package seedu.nuke.data.module;

import seedu.nuke.exception.ModuleNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static seedu.nuke.util.Message.MESSAGE_DUPLICATE_MODULE_ADD;
import static seedu.nuke.util.Message.MESSAGE_MODULE_NOT_FOUND;

public class ModuleList {
    private ArrayList<Module> moduleList;

    public ModuleList() {
        this.moduleList = new ArrayList<>();
    }

    /**
     * @return all moduleList
     */
    public ArrayList<Module> getModuleList() {
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
     * @param toAdd the module to-add
     */
    public void add(Module toAdd)  {
        //check duplicate
        if (moduleList.contains(toAdd)){
            //display duplicate message
            System.out.println(MESSAGE_DUPLICATE_MODULE_ADD);
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
    public void delete(Module toDelete) throws ModuleNotFoundException {
        boolean isModuleFoundAndDeleted = moduleList.remove(toDelete);
        if (!isModuleFoundAndDeleted) {
            throw new ModuleNotFoundException(MESSAGE_MODULE_NOT_FOUND);
        }
    }
}
