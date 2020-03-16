package seedu.nuke.data;

import java.util.LinkedList;
import java.util.List;

public class ScreenShotManager {
    private static List<ScreenShot> screenShotList;
    // initial index - 0
    private static int currentPointer = 0;

    public ScreenShotManager() {
        screenShotList = new LinkedList<>();
        currentPointer = 0;
    }

    public ScreenShotManager(List<ScreenShot> screenShotList, int currentPointer) {
        screenShotList = screenShotList;
        currentPointer = currentPointer;
    }

    public static List<ScreenShot> getScreenShotList() {
        return screenShotList;
    }

    public static void setScreenShotList(List<ScreenShot> screenShotList) {
        screenShotList = screenShotList;
    }

    public static int getCurrentPointer() {
        return currentPointer;
    }

    public void setCurrentPointer(int currentPointer) {
        this.currentPointer = currentPointer;
    }

    public static void unDo() {
        if (currentPointer > 0){
            currentPointer = currentPointer - 1;
        }
    }

    public static void setCurrentPointerForward(){
        currentPointer++;
    }

    public static ScreenShot getCurrentScreenShot() {
        return screenShotList.get(currentPointer);
    }
}
