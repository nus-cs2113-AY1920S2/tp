package seedu.nuke.directory;

import seedu.nuke.data.TaskFileManager;
import seedu.nuke.format.DateTime;
import seedu.nuke.tag.Tag;

import java.util.ArrayList;

import static seedu.nuke.common.Constants.NO_ICON;
import static seedu.nuke.common.Constants.YES_ICON;

public class Task extends Directory implements Tag {
    private String description;
    private boolean isDone;
    private int priority;
    private DateTime deadline;
    private TaskFileManager files;
    private String moduleCode;
    private ArrayList<String> tags;

    public Task() {
    }

    /**
     * constructor for the simplest task.
     *
     * @param description the description of the task
     * @param moduleCode the module code of the module which the task belongs to
     */
    public Task(Module module, String description, String moduleCode) {
        super(module);
        this.description = description;
        this.files = new TaskFileManager();
        this.deadline = null;
        this.priority = -1;
        this.isDone = false;
        this.moduleCode = moduleCode;
        this.tags = null;
    }

    /**
     * Constructs the task.
     *
     * @param category
     *  The parent category of the task
     * @param description
     *  The description of the task
     * @param deadline
     *  The deadline of the task
     * @param priority
     *  The priority of the task
     */
    public Task(Category category, String description, DateTime deadline, int priority) {
        super(category);
        this.description = description;
        this.isDone = false;
        this.deadline = deadline;
        this.priority = priority;
        this.files = new TaskFileManager();
        this.tags = null;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * moduleCode getter method.
     *
     * @return the module code of the module which the task belongs to
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * description getter method.
     *
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * deadline getter method.
     *
     * @return the deadline of the task
     */
    public DateTime getDeadline() {
        return deadline;
    }

    /**
     * deadline setter method.
     *
     * @param deadline the deadline of the task
     */
    public void setDeadline(DateTime deadline) {
        this.deadline = deadline;
    }

    /**
     * priority getter method.
     *
     * @return the priority of the task
     */
    public int getPriority() {
        return priority;
    }

    /**
     * return an icon representing the status of the task.
     *
     * @return a String of the icon.
     */
    public String getStatusIcon() {
        return (isDone ? YES_ICON : NO_ICON);
    }

    /**
     * files getter method.
     *
     * @return an ArrayList of String representing the files which the task is associated with
     */
    public TaskFileManager getFiles() {
        return files;
    }

    /**
     * Adds a file into the File List of this task.
     * @param fileName
     *  The file name of the file to be added
     * @param filePath
     *  The path to the file to be added
     */
    public void addFile(String fileName, String filePath) {
        files.add(new TaskFile(fileName, filePath));
    }

    /**
     * Checks if one task has the same description as another.
     *
     * @param task
     *  The task to check
     * @return
     *  <code>TRUE</code> if they are the same, and <code>FALSE</code> otherwise
     */
    public boolean isSameTask(Task task) {
        return this.description.equals(task.description);
    }

    /**
     * Prints the task as a string.
     *
     * @return
     *  The string that contains all the task information
     */
    @Override
    public String toString() {
        return String.format("%s %s by %s\n Files:\n%s", getStatusIcon(), getDescription(), getDeadline(), files);
    }

    @Override
    public void setTag(String info) {
        this.tags.add(info);
    }

    @Override
    public void removeTag() {
        this.tags = null;
    }

    @Override
    public String getTag() {
        return this.tags != null ? tags.toString() : null;
    }
}
