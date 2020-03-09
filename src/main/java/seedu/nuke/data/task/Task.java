package seedu.nuke.data.task;

import seedu.nuke.data.file.TaskFile;
import seedu.nuke.data.file.TaskFileList;
import seedu.nuke.format.DateTime;

import static seedu.nuke.common.Constants.NO_ICON;
import static seedu.nuke.common.Constants.YES_ICON;

public class Task {
    protected String description;
    protected boolean isDone;
    protected DateTime deadline;
    protected int priority;
    protected TaskFileList files;

    public Task(String description, DateTime deadline, int priority) {
        this.description = description;
        this.isDone = false;
        this.deadline = deadline;
        this.priority = priority;
        this.files = new TaskFileList();
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

    public TaskFileList getFiles() {
        return files;
    }

    public void setDeadline(DateTime deadline) {
        this.deadline = deadline;
    }

    public void addFile(String fileName, String filePath) {
        files.add(new TaskFile(fileName, filePath));
    }

    @Override
    public String toString() {
        StringBuilder printOut = new StringBuilder(getStatusIcon() + " " + getDescription() + " by " + getDeadline());
        printOut.append(files);
        return printOut.toString();
    }
}
