package seedu.nuke.data;

import seedu.nuke.exception.ModuleNotFoundException;
import seedu.nuke.module.Module;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static seedu.nuke.util.Message.*;

public class ModuleManager implements Iterable<Module> {
    private ArrayList<Module> modules;

    /**
     * initialize with empty module list
     */
    public ModuleManager() {
        this.modules  = new ArrayList<Module>();
    }

    /**
     *
     * @param modules initialize with existed module list
     */
    public ModuleManager(ArrayList<Module> modules) {
        this.modules = modules;
    }

    /**
     * @return all modules
     */
    public List<Module> getModuleList() {
        return modules;
    }

    /**
     * Checks if the list contains an equivalent task as the given description.
     * @param toCheck the task to-check
     * @return true if the task exists
     */
    public boolean contains(Module toCheck) {
        for (Module p : modules) {
            if (p.isSameModule(toCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add a module to the moduleList.
     * @param toAdd the module to-add
     */
    public void addModule(Module toAdd)  {
        //check duplicate
        if (modules.contains(toAdd)){
            //display duplicate message
            System.out.println(MESSAGE_DUPLICATE_MODULE_ADD);
        } else {
            modules.add(toAdd);
        }
    }

    /**
     * Clears all tasks in list.
     */
    public void clear() {
        modules.clear();
    }

    /**
     * Remove a module to the list.
     * @param toRemove the task to-remove
     * @throws ModuleNotFoundException if the to-remove module does not exist
     */
    public void remove(Module toRemove) throws ModuleNotFoundException {
        final boolean moduleFoundAndDeleted = modules.remove(toRemove);
        if (!moduleFoundAndDeleted) {
            throw new ModuleNotFoundException(MESSAGE_MODULE_NOT_FOUND);
        }
    }

    @Override
    public Iterator<Module> iterator() {
        return modules.iterator();
    }

    /**
     * @return the next-to-add task index
     */
    public int getNextTaskIndex(){
        return modules.size();
    }
}
