package jikan.storage;

import jikan.activity.Activity;
import jikan.activity.ActivityList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class containing useful functions for modifying the data file.
 */
public class StorageHandler {
    Storage storage;

    /**
     * Constructs a StorageHandler object for the input Storage.
     *
     * @param storage The data file's file path.
     */
    public StorageHandler(Storage storage) {
        assert storage != null : "Input Storage must not be a null pointer";
        this.storage = storage;
    }

    /**
     * Removes the line whose index matches lineNumber from file at dataFilePath.
     *
     * @param lineNumber Index of line to remove.
     * @param storage Storage object which contains path to save file.
     * @throws IOException If an error occurs while writing the new list to file.
     */
    public void removeLine(int lineNumber, Storage storage) throws IOException {
        assert storage != null : "Input Storage must not be a null pointer";
        assert lineNumber >= 0 : "lineNumber cannot be negative";
        // Read file into list of strings, where each string is a line in the file
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(storage.dataFilePath),
                StandardCharsets.UTF_8));

        fileContent.remove(lineNumber);

        saveNewList(fileContent, storage.dataFile);
    }

    /**
     * Saves the updated activity list to a list of strings to write to the save file.
     * @param activities New activity list.
     * @param storage Storage object to obtain file path.
     * @throws IOException If an error occurs while writing the new list to file.
     */
    public void updateField(ArrayList<Activity> activities, Storage storage) throws IOException {
        assert storage != null : "Input Storage must not be a null pointer";
        List<String> fileContent = new ArrayList<>();
        for (Activity a : activities) {
            fileContent.add(a.toData());
        }
        saveNewList(fileContent, storage.dataFile);
    }

    /**
     * Saves a the updated activity list to the csv file.
     *
     * @param newList The list containing the updated data.
     * @param dataFile The file to save to.
     * @throws IOException If an error occurs while writing the new list to file.
     */
    public void saveNewList(List<String> newList, File dataFile) throws IOException {
        assert dataFile != null;
        FileOutputStream fileOutputStream = new FileOutputStream(dataFile);
        PrintWriter printWriter = new PrintWriter(fileOutputStream);

        for (String s : newList) {
            printWriter.println(s);
        }
        printWriter.close();
        fileOutputStream.close();
    }
}
