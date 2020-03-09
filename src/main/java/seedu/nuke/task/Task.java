package seedu.nuke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static seedu.nuke.common.Constants.NO_ICON;
import static seedu.nuke.common.Constants.YES_ICON;

public class Task {
    protected String description;
    protected boolean isDone;
    protected ArrayList<String> files;
    protected LocalDateTime deadline;
    protected int priority;

    public Task(String description, ArrayList<String> files, LocalDateTime dateTime, int priority) {
        this.description = description;
        this.files = new ArrayList<String>();
        for (String file: files) {
            this.files.add(file);
        }
        this.deadline = dateTime;
        this.priority = priority;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDeadline() {
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

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}
