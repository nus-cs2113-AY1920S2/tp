package seedu.nuke.task;

import seedu.nuke.format.DateTime;

import java.util.ArrayList;

import static seedu.nuke.common.Constants.NO_ICON;
import static seedu.nuke.common.Constants.YES_ICON;

public class Task implements Comparable{
    protected String description;
    protected boolean isDone;
    protected int priority;
    protected DateTime deadline;
    protected ArrayList<String> files;


    /**
     * constructor for the simplest task
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.files = new ArrayList<>();
        this.deadline = null;
        this.priority = -1;
        this.isDone = false;
    }

    public Task(String description, DateTime dateTime, int priority) {
        this.description = description;
        this.files = new ArrayList<>();
        this.deadline = dateTime;
        this.priority = priority;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public DateTime getDeadline() {
        return deadline;
    }

    public int getPriority() {
        return priority;
    }

    public String getStatusIcon() {
        return (isDone ? YES_ICON : NO_ICON);
    }

    public ArrayList<String> getFiles() {
        return files;
    }

    public String toString() {
        StringBuilder printOut = new StringBuilder(getStatusIcon() + " " + getDescription() + " by " + getDeadline());
        for (String file: files) {
            printOut.append(" ").append(file);
        }
        return printOut.toString();
    }

    public void setDeadline(DateTime deadline) {
        this.deadline = deadline;
    }

    public void addFile(String filePath) {
        files.add(filePath);
    }

    public int compareTo(Object o) {
        Task task = (Task) o;
        return (deadline.toString().compareToIgnoreCase(task.deadline.toString()) > 0) ? 1 : 0;
    }

}
