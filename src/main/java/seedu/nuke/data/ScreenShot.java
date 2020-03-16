package seedu.nuke.data;

import seedu.nuke.data.DataManager;
import seedu.nuke.data.ModuleManager;


public class ScreenShot {
    private ModuleManager moduleManager;
    private DataManager dataManager;

    public ScreenShot(ModuleManager moduleManager, DataManager dataManager) {

    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public void setModuleManager(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void takeScreenShot(ModuleManager moduleManager, DataManager dataManager) {

    }
}

