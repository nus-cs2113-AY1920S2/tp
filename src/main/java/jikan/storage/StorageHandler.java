package jikan.storage;

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

    /**
     * Removes the line whose index matches lineNumber from file at dataFilePath.
     *
     * @param lineNumber Index of line to remove.
     * @param storage Storage object which contains path to save file.
     * @throws IOException If an error occurs while writing the new list to file.
     */
    public static void removeLine(int lineNumber, Storage storage) throws IOException {
        // Read file into list of strings, where each string is a line in the file
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(storage.dataFilePath),
                StandardCharsets.UTF_8));

        //int removedIndex = 0;

        fileContent.remove(lineNumber);

        saveNewList(fileContent, storage.dataFile);

        /*
        // Remove line from fileContent list
        removedIndex = removeLineFromList(lineNumber, fileContent, removedIndex);

        // Rewrite list into data file
        Files.write(Paths.get(dataFilePath), fileContent, StandardCharsets.UTF_8);

        // Update indexes of subsequent lines
        updateIndexes(fileContent, removedIndex, dataFilePath);

         */
    }

    /**
     * Saves a the updated activity list to the csv file.
     *
     * @param newList The list containing the updated data.
     * @param dataFile The file to save to.
     * @throws IOException If an error occurs while writing the new list to file.
     */
    public static void saveNewList(List<String> newList, File dataFile) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(dataFile);
        PrintWriter printWriter = new PrintWriter(fileOutputStream);

        for (String s : newList) {
            printWriter.println(s);
        }
        printWriter.close();
        fileOutputStream.close();
    }

    /**
     * Removes line from a list based on its index. Returns index at which the line was removed.
     *
     * @param lineNumber Index of line to remove.
     * @param list List of strings containing lines, each with a starting index.
     * @param removedIndex Index at which line was removed.
     * @return Index of the removed line.
     */
    private static int removeLineFromList(int lineNumber, List<String> list, int removedIndex) {
        // Iterate through the lines
        for (int i = 0; i < list.size(); i++) {
            // If the current line matches the taskId
            if (list.get(i).startsWith(String.valueOf(lineNumber))) {
                // Remove current line
                removedIndex = i; // Store index of removed line to use as initial index of next loop
                list.remove(i);
                break;
            }
        }
        return removedIndex;
    }

    /**
     * After a line has been removed, updates subsequent indexes.
     *
     * @param fileContent List of strings from the data file.
     * @param removedIndex Index at which line was removed.
     * @param dataFilePath Path to data file.
     * @throws IOException If an error occurs while writing to file.
     */
    private static void updateIndexes(List<String> fileContent, int removedIndex, String dataFilePath)
            throws IOException {
        // Update indexes of subsequent tasks (e.g. if you remove task 2, task 3 becomes task 2)
        for (int i = removedIndex; i < fileContent.size(); i++) {
            // Get current comma separated string
            String currString = fileContent.get(i);

            String updatedString = decrementIndex(currString, i);

            // Replace the line with the updated string
            replaceLine(i + 1, updatedString, dataFilePath);
        }
    }

    /**
     * Decrements index at beginning of string by 1.
     *
     * @param currString String whose index must be decremented.
     * @param i Index that the currString's index must be set to.
     * @return Original string with its index decremented by 1.
     */
    private static String decrementIndex(String currString, int i) {

        // Split into different cells
        List<String> cells = Arrays.asList(currString.split(","));

        // Change first cell (taskID) to new ID (taskID-1)
        // Task 3 becomes task 2, task 4 becomes task 3, until end of list
        cells.set(0, String.valueOf(i));

        // Recombine the comma separated string
        return String.join(",", cells);
    }

    /**
     * Replaces line in a data file.
     *
     * @param lineNumber Index of the line to replace.
     * @param newString String that replaces the original line.
     * @param dataFilePath Path to data file.
     * @throws IOException If an error occurs while writing to file.
     */
    public static void replaceLine(int lineNumber, String newString, String dataFilePath) throws IOException {
        // Read file into list of strings, where each string is a line in the file
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(dataFilePath), StandardCharsets.UTF_8));

        // Replace line in the list of strings
        replaceLineInList(lineNumber, newString, fileContent);

        // Write new list of strings to file
        Files.write(Paths.get(dataFilePath), fileContent, StandardCharsets.UTF_8);
    }

    /**
     * Replaces a line in a list of Strings.
     *
     * @param lineNumber Number at which line must be replaced.
     * @param newString String that replaces the original string.
     * @param list List of strings.
     */
    private static void replaceLineInList(int lineNumber, String newString, List<String> list) {
        // Iterate through the lines
        for (int i = 0; i < list.size(); i++) {
            // If the current line matches the taskId
            if (list.get(i).startsWith(String.valueOf(lineNumber))) {
                // Replace it with the new task string
                list.set(i, newString);
                break;
            }
        }
    }
}
