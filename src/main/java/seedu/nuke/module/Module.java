package seedu.nuke.module;

import seedu.nuke.task.Stuff;
import seedu.nuke.task.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Module {
    private String moduleCode;
    private String title;
    private String description;
    private ArrayList<Stuff> stuffs;

    public Module(String moduleCode, String title, String description) {
        this.moduleCode = moduleCode;
        this.title = title;
        this.description = description;
        this.stuffs = new ArrayList<Stuff>();
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Stuff> getStuffs() {
        return stuffs;
    }

}
