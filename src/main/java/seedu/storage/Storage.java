package seedu.storage;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import seedu.tasklist.TaskList;
import seedu.tasks.Task;
import seedu.tasks.Class;
import seedu.tasks.TaskNonclass;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import static seedu.common.Constants.CLASS_CATEGORY;

public class Storage {

    private static Gson gson = new Gson();

    private static final String WORKING_DIRECTORY = System.getProperty("user.dir");
    private static final java.nio.file.Path FOLDER_PATH = java.nio.file.Paths.get(WORKING_DIRECTORY, "Save");
    private static final java.nio.file.Path FILE_PATH = java.nio.file.Paths.get(WORKING_DIRECTORY, "Save", "data.txt");

    /**
     * Locate folder location and check availability.
     * If missing create folder.
     */
    public static void checkFolderPath() {
        boolean directoryExists = java.nio.file.Files.exists(FOLDER_PATH);
        if (!directoryExists) {
            try {
                Files.createDirectory(FOLDER_PATH);
            } catch (IOException e) {
                System.out.println("Error creating folder!\n");
            }
        }
    }

    /**
     * Check whether data file has is present in directory.
     *
     * @return status of file availability
     */
    public static boolean checkFileExists() {
        boolean fileExists = java.nio.file.Files.exists(FILE_PATH);
        return fileExists;
    }

    public static String convertToGson(Task task) {
        return gson.toJson(task);
    }


    /**
     * Over writes saved data file with latest task list.
     *
     * @param taskList current task list for saving
     */
    public static void overwriteFile(ArrayList<Task> taskList) {

        try {
            File file = new File(String.valueOf(FILE_PATH));

            FileWriter myWriter = new FileWriter(file, false);

            for (Task task: taskList) {
                String formattedTask = convertToGson(task);

                myWriter.write(formattedTask);
                myWriter.write(System.getProperty("line.separator"));
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("[Error] File cannot be written!\n");
        }
    }


    /**
     * Reading in data file and storing into tasklist.
     * @param taskList tasklist object created in main
     */
    public void loadFromFile(TaskList taskList) {

        File file = new File(String.valueOf(FILE_PATH));

        try {
            Scanner loader = new Scanner(file);

            while (loader.hasNextLine()) {
                String gsonTask = loader.nextLine();
                JsonElement jsonElement = JsonParser.parseString(gsonTask);
                JsonObject obj = jsonElement.getAsJsonObject();

                Task taskToAdd = createClassFromJson(obj);
                taskList.addTask(taskToAdd);
            }

        } catch (IOException e) {
            System.out.println("Error reading data file! \n");
        }
    }


    /**
     * Creates a Task object from a given Json object.
     *
     * @param obj Json object containing the Task object.
     * @return Task object based on task type.
     */
    private Task createClassFromJson(JsonObject obj) {

        String taskType = obj.get("category").toString();

        if (taskType.contains(CLASS_CATEGORY)) {
            return gson.fromJson(obj.toString(), Class.class);

        } else {
            return gson.fromJson(obj.toString(), TaskNonclass.class);
        }

    }
}
