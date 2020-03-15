package seedu.nuke.data;

import java.util.LinkedList;
import java.util.List;

public class ScreenShotManager {
    private List<ScreenShot> screenShotList;
    private int currentPointer;

    public ScreenShotManager() {
        screenShotList = new LinkedList<>();
        currentPointer = 0;
    }

    public ScreenShotManager(List<ScreenShot> screenShotList, int currentPointer) {
        this.screenShotList = screenShotList;
        this.currentPointer = currentPointer;
    }

    public List<ScreenShot> getScreenShotList() {
        return screenShotList;
    }

    public void setScreenShotList(List<ScreenShot> screenShotList) {
        this.screenShotList = screenShotList;
    }

    public int getCurrentPointer() {
        return currentPointer;
    }

    public void setCurrentPointer(int currentPointer) {
        this.currentPointer = currentPointer;
    }

    public ScreenShot unDo(){
        currentPointer = currentPointer -1;
        return screenShotList.get(currentPointer - 1);
    }

    public ScreenShot getCurrentScreenShot() {
        return screenShotList.get(currentPointer);
    }
}
