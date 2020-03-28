package seedu.atas;

import common.Messages;
import exceptions.AtasException;
import tasks.Assignment;
import tasks.Event;
import tasks.RepeatEvent;
import tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.StringJoiner;

public class Storage {
    protected static final String DEFAULT_FILEPATH = "./atasData.txt";
    protected final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Storage() {
        this(DEFAULT_FILEPATH);
    }

    /**
     * Reads data from the local save file specified by filePath, and returns the corresponding TaskList.
     * @return TaskList containing all tasks in the save file
     * @throws AtasException if the save file format is incorrect
     * @throws IOException if no save file is found
     */
    public TaskList load() throws AtasException, IOException {
        File saveFile = new File(filePath);
        Scanner scanner = new Scanner(saveFile);
        TaskList taskList = new TaskList();
        while (scanner.hasNextLine()) {
            String encodedTask = scanner.nextLine();
            Task task = decodeTask(encodedTask);
            assert task != null;
            taskList.addTask(task);
        }
        return taskList;
    }

    private Task decodeTask(String encodedTask) throws AtasException {
        Task task = null;
        String taskType = encodedTask.substring(0, 1);
        try {
            switch (taskType) {
            case Assignment.ASSIGNMENT_ICON:
                task = Assignment.decodeTask(encodedTask);
                break;
            case Event.EVENT_ICON:
                task = Event.decodeTask(encodedTask);
                break;
            case RepeatEvent.REPEAT_ICON:
                task = RepeatEvent.decodeTask(encodedTask);
                break;
            default:
                throw new AtasException(Messages.INCORRECT_STORAGE_FORMAT_ERROR);
            }
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            throw new AtasException(Messages.INCORRECT_STORAGE_FORMAT_ERROR);
        }
        return task;
    }

    /**
     * Saves the taskList's current state into a local file located at filePath.
     * @param taskList TaskList to be stored
     * @throws IOException if an IO error occurs in FileWriter methods
     */
    public void save(TaskList taskList) throws IOException {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        for (Task task : taskList.getTaskArray()) {
            sj.add(task.encodeTask());
        }
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(sj.toString());
        fileWriter.close();
    }
}
