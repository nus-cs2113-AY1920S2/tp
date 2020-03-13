package seedu.nuke.data;

import seedu.nuke.data.DataManager;
import seedu.nuke.data.ModuleManager;


public class ScreenShot {
    private ModuleManager moduleManager;
    private DataManager dataManager;
    private int screenShotIndex;

    public ScreenShot(ModuleManager moduleManager, DataManager dataManager, int screenShotIndex) {
        this.moduleManager = moduleManager;
        this.dataManager = dataManager;
        this.screenShotIndex = screenShotIndex;
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

    public int getScreenShotIndex() {
        return screenShotIndex;
    }

    public void setScreenShotIndex(int screenShotIndex) {
        this.screenShotIndex = screenShotIndex;
    }

    public void takeScreenShot (ModuleManager moduleManager, DataManager dataManager){
        this.moduleManager = moduleManager;
        this.dataManager = dataManager;
        this.screenShotIndex++;
    }
}

