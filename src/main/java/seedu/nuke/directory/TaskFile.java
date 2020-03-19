package seedu.nuke.directory;

import seedu.nuke.data.TaskFileManager;
import seedu.nuke.util.DateTime;

import java.util.ArrayList;

public class TaskFile extends Directory {
    private String fileName;
    private String filePath;

    /**
     * Constructs the task file.
     *
     * @param task
     *  The parent task of the task file
     * @param fileName
     *  The name of the file
     * @param filePath
     *  The path to the file
     */
    public TaskFile(Task task, String fileName, String filePath) {
        super(task);
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    @Override
    public Task getParent() {
        return (Task) this.parent;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
