package seedu.nuke.module;

import seedu.nuke.data.Directory;
import seedu.nuke.data.TaskManager;
import seedu.nuke.task.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Module {
    private String moduleCode;
    private String title;
    private String description;
    private TaskManager taskManager;


    /**
     * initialize a module with module code, title, and description.
     * @param moduleCode the module code of the module
     * @param title the title of the module
     * @param description the description of the module
     */
    public Module(String moduleCode, String title, String description) {
        this.moduleCode = moduleCode.toUpperCase();
        this.title = title;
        this.description = description;
        this.taskManager = new TaskManager();
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTaskManager(TaskManager taskManager) {
        this.taskManager = taskManager;
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

    /**
     * The method to check the deadline of the tasks in a module.
     * @return an ArrayList of String representing the deadline of tasks in order.
     */
    public ArrayList<String> checkDeadline() {
        ArrayList<String> deadlines = new ArrayList<>();
        ArrayList<Task> tasks = taskManager.getAllTasks();
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                String t1Deadline = t1.getDeadline() == null ? "" : t1.getDeadline().toString();
                String t2Deadline = t2.getDeadline() == null ? "" : t2.getDeadline().toString();
                return t1Deadline.compareToIgnoreCase(t2Deadline);
            }
        });
        for (Task task: tasks) {
            String deadline = task.getDeadline() == null ? "" : task.getDeadline().toString();
            deadlines.add(String.format("%-30s", task.getDescription()) + "   Deadline: " + task.getDeadline());
        }
        return deadlines;
    }

    public boolean isSameModule(Module module) {
        return this.moduleCode.equals(module.moduleCode.toUpperCase());
    }

}
