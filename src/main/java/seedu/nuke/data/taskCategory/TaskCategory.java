package seedu.nuke.data.taskCategory;

import seedu.nuke.data.task.TaskList;

public class TaskCategory {
    private String categoryName;
    private int categoryPriority;
    private TaskList tasks;

    public TaskCategory(String categoryName, int categoryPriority) {
        this.categoryName = categoryName;
        this.categoryPriority = categoryPriority;
        this.tasks = new TaskList();
    }

    public TaskCategory(String categoryName) {
        this.categoryName = categoryName;
        this.categoryPriority = 0;
        this.tasks = new TaskList();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public TaskList getTasks() {
        return tasks;
    }

    public int getCategoryPriority() {
        return categoryPriority;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryPriority(int categoryPriority) {
        this.categoryPriority = categoryPriority;
    }
}
