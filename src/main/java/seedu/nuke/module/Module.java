package seedu.nuke.module;

import seedu.nuke.task.Task;

public class Module {
    private String moduleCode;
    private String title;
    private String description;
    private TaskList taskList;

    public Module(String moduleCode, String title, String description) {
        this.moduleCode = moduleCode;
        this.title = title;
        this.description = description;
        this.taskList = new TaskList();
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

    public TaskList getTaskList() {
        return taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }
}
