package seedu.nuke.module;

import seedu.nuke.task.Task;

import java.util.ArrayList;

public class Module {
    private String moduleCode;
    private String title;
    private String description;
    private ArrayList<Task> tasks;

    public Module(String moduleCode, String title, String description) {
        this.moduleCode = moduleCode;
        this.title = title;
        this.description = description;
        this.tasks = new ArrayList<Task>();
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

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
