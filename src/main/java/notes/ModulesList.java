package notes;

import notes.modules.ModuleManager;
import ui.Constants;
import ui.Ui;
import java.util.ArrayList;
import java.util.HashMap;

import static ui.Constants.NOTES_KEY_WORD;
import static ui.Constants.NOTES_KEY_WORD_ERROR;


//@@author terrytay
public class ModulesList {
    static ModulesList instance = null;
    private HashMap<String, ArrayList<String>> modules;
    private Ui ui;

    private ModulesList(Ui ui) {
        this.modules = new HashMap<>();
        this.ui = ui;
    }

    /**
     * Singleton check for null instance before initializing.
     * @param ui allows for interaction with the user
     * @return ModuleList
     */
    public static ModulesList getInstance(Ui ui) {
        if (instance == null) {
            instance = new ModulesList(ui);
        }
        return instance;
    }

    /**
     * Create a module with code.
     *
     * @param code Module Code.
     */
    public void createModule(String code) {
        if (code.equals(NOTES_KEY_WORD)) {
            this.ui.printMessage(NOTES_KEY_WORD_ERROR);
        } else if (getModule(code) == null) {
            modules.putIfAbsent(code, new ArrayList<>());
            String success = String.format("%s has been created\n", code);
            this.ui.printMessage(success);
        } else {
            this.ui.printMessage(Constants.HAS_BEEN_CREATED_BEFORE);
        }

    }

    /**
     * Delete a module using its code.
     *
     * @param code Module code.
     */
    public void deleteModule(String code) {
        if (modules.containsKey(code)) {
            this.ui.printMessage("Are you sure you want to remove " + code
                    + "? [Y/N]");
            this.ui.printLine();
            String input = ui.getUserIn();
            this.ui.printLine();
            if (input.toUpperCase().equals("Y")) {
                modules.remove(code);
                this.ui.printMessage(code + " has been removed");
            } else {
                this.ui.printMessage(Constants.CANCEL_OPERATION);
            }
        } else {
            this.ui.printMessage(Constants.MODULE_NOT_FOUND);
        }
    }

    /**
     * Retrieves a module's content from the module given the module code.
     * @param code module code
     * @return module's content
     */
    public ArrayList<String> getModule(String code) {
        if (!modules.containsKey(code)) {
            return null;
        }
        return modules.get(code);
    }

    /**
     * Enters the module interface of moduleManager.
     *
     * @param code module code
     * @throws Exception for ModuleManager
     */
    public void enterModule(String code) throws Exception {
        if (getModule(code) == null) {
            this.ui.printMessage(Constants.MODULE_NOT_FOUND);
        } else {
            new ModuleManager(code, getModule(code), this.ui);
        }
    }

    /**
     * Print all saved modules.
     */
    public void listModules() {
        if (modules.isEmpty()) {
            this.ui.printMessage(Constants.NO_MODULE_FOUND);
        } else {
            this.ui.printMessage(modules.keySet().toString());
        }
    }

    public void importModules(HashMap<String, ArrayList<String>> modules) {
        this.modules = modules;
    }

    public HashMap<String, ArrayList<String>> exportModules() {
        return this.modules;
    }
}
