package seedu.nuke.module;

import seedu.nuke.command.CheckDeadlineCommand;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.data.TaskManager;
import seedu.nuke.task.Task;
import seedu.nuke.ui.TextUi;

import java.util.ArrayList;
import java.util.Collections;

public class Module {
    private String moduleCode;
    private String title;
    private String description;
    private TaskManager taskManager;

    /**
     * initialize a module with module code, title, and description
     * @param moduleCode
     * @param title
     * @param description
     */
    public Module(String moduleCode, String title, String description) {
        this.moduleCode = moduleCode;
        this.title = title;
        this.description = description;
        this.taskManager = new TaskManager();
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

    public TaskManager getTaskManager() {
        return this.taskManager;
    }

    public void checkDeadline() {
        ArrayList<String> deadlines = new ArrayList<>();
        ArrayList<Task> tasks = taskManager.getTaskList();
        Collections.sort(tasks);
        for(Task task: tasks) {
            deadlines.add("Task: " + task.getDescription()+"  Deadline: " + task.getDeadline().toString());
        }
        TextUi.listDeadlines(deadlines);
    }

    public boolean isSameModule(Module module) {
        return this.moduleCode.equals(module.moduleCode);
    }
}
