package seedu.nuke.data;

import com.alibaba.fastjson.JSON;
import seedu.nuke.directory.Module;

import java.util.ArrayList;

public class ScreenShotManager {
    private static ArrayList<ScreenShot> screenShotList = new ArrayList<>();
    private static int pointer = -1;

    public ScreenShotManager() {

    }

    public static ArrayList<ScreenShot> getScreenShotList() {
        return ScreenShotManager.screenShotList;
    }

    public static void setScreenShotList(ArrayList<ScreenShot> screenShotList) {
        ScreenShotManager.screenShotList = screenShotList;
    }

    public static int getCurrentPointer() {
        return ScreenShotManager.pointer;
    }

    public static ScreenShot getCurrentScreenShot(){
        if (getCurrentPointer() < 0){
            return screenShotList.get(0);
        }
        return screenShotList.get(getCurrentPointer());
    }

    public static void setCurrentPointer(int pointer) {
        ScreenShotManager.pointer = pointer;
    }

    public static void movePointerForward(){
        ScreenShotManager.pointer ++;
    }

    public static void movePointerBackWard(){
        if (ScreenShotManager.pointer > 0){
            ScreenShotManager.pointer --;
        }
    }

    public static void saveNewScreenShot (ScreenShot toAdd){
        movePointerForward();
        screenShotList.add(toAdd);
    }

    public static void cutTailNodes(){
        screenShotList = (ArrayList<ScreenShot>) screenShotList.subList(0, pointer);
    }

    /**
     * save the screen shot
     */
    public static ScreenShot takeNewScreenShot(ModuleManager moduleManager) {
        String jsonStrModuleList = StorageManager.saveModuleToString(moduleManager.getModuleList());
        return new ScreenShot(jsonStrModuleList);
    }

    /**
     * set module manager data according to screen shot manager data
     */
    public static ArrayList<Module> readFromScreenShot(String jsonStr) {
        return (ArrayList<Module>) JSON.parseArray(jsonStr, Module.class);
    }
}
