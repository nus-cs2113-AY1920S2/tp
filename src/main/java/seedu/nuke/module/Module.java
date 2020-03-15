package seedu.nuke.module;

import seedu.nuke.data.TaskManager;
import seedu.nuke.task.Task;
import seedu.nuke.ui.TextUi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        this.moduleCode = moduleCode.toUpperCase();
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

    public int countTasks() {
        return taskManager.countTotalTasks();
    }

    public ArrayList<String> checkDeadline() {
        ArrayList<String> deadlines = new ArrayList<>();
        ArrayList<Task> tasks = taskManager.getTaskList();
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t1.getDeadline().toString().compareToIgnoreCase(t2.getDeadline().toString());
            }
        });
        for(Task task: tasks) {
            deadlines.add("Task: " + task.getDescription()+"  Deadline: " + task.getDeadline().toString());
        }
        return deadlines;
    }

    public boolean isSameModule(Module module) {
        return this.moduleCode.equals(module.moduleCode.toUpperCase());
    }
}
