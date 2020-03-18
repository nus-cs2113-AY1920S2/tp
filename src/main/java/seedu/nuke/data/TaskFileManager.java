package seedu.nuke.data;

import seedu.nuke.directory.TaskFile;
import seedu.nuke.exception.DataNotFoundException;

import java.util.ArrayList;

public class TaskFileManager {
    ArrayList<TaskFile> fileList;

    public TaskFileManager() {
        fileList = new ArrayList<>();
    }

    /**
     * Finds a file with the specified file name in the File List.
     *
     * @param fileName
     *  The file name of the task
     * @return
     *  The file with the specified file name if found
     * @throws TaskFileNotFoundException
     *  If the file is not found in the File List
     */
    public TaskFile getFile(String fileName) throws TaskFileNotFoundException {
        for (TaskFile file : fileList) {
            if (file.getFileName().equals(fileName)) {
                return file;
            }
        }
        throw new TaskFileNotFoundException();
    }

    public void add(TaskFile toAdd) {
        fileList.add(toAdd);
    }

    public void delete(TaskFile toDelete) throws TaskFileNotFoundException {
        boolean isFileFoundAndDeleted = fileList.remove(toDelete);
        if (!isFileFoundAndDeleted) {
            throw new TaskFileNotFoundException();
        }
    }

    public void delete(String fileName) throws TaskFileNotFoundException {
        for (TaskFile file : fileList) {
            if (file.getFileName().equals(fileName)) {
                fileList.remove(file);
                return;
            }
        }
        throw new TaskFileNotFoundException();
    }

    @Override
    public String toString() {
        StringBuilder listToString = new StringBuilder();
        for (TaskFile file : fileList) {
            String arrayString = "[\"" + file.getFileName() + "\", \"" + file.getFilePath() + "\"]";
            listToString.append(arrayString).append(" ");
        }
        return listToString.toString();
    }

    public static class TaskFileNotFoundException extends DataNotFoundException {}
}
