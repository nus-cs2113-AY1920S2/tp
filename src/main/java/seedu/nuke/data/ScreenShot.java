package seedu.nuke.data;

public class ScreenShot {
    private String moduleListString;

    public ScreenShot(String moduleListInJsonStr) {
        this.moduleListString = moduleListInJsonStr;
    }

    public String getModuleListString() {
        return moduleListString;
    }
}

