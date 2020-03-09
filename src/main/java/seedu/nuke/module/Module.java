package seedu.nuke.module;

import seedu.nuke.command.CommandResult;
import seedu.nuke.task.Task;

import java.util.ArrayList;
import java.util.Collections;

public class Module {
    private String moduleCode;
    private String title;
    private String description;
    private ArrayList<Task> moduleTasks;

    public Module(String moduleCode, String title, String description) {
        this.moduleCode = moduleCode;
        this.title = title;
        this.description = description;
        this.moduleTasks = new ArrayList<Task>();
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

    public ArrayList<Task> getStuffs() {
        return moduleTasks;
    }

    public void checkDeadline() {

    }

    public boolean isSameModule(Module module) {
        return this.moduleCode.equals(module.moduleCode);
    }
}
