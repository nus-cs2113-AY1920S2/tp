package jikan.storage;

import jikan.activity.ActivityList;

import java.io.*;

/**
 * Class that holds the path and File object for the current data file.
 */
public class Storage {

    /** Path to current data file. */
    public String dataFilePath;

    /** File object for current data file. */
    public File dataFile;

    public ActivityList activityList;

    /**
     * Constructs a Storage object for the input file path.
     *
     * @param dataFilePath The data file's file path.
     */
    public Storage(String dataFilePath) {
        this.dataFilePath = dataFilePath;
        dataFile = new File(dataFilePath);
    }

    /**
     * Writes the input string to file.
     *
     * @param s The input string.
     * @throws IOException If an error occurs while writing.
     */
    public void writeToFile(String s) throws IOException {
        FileWriter fw = new FileWriter(dataFilePath, true);
        fw.write(s + System.lineSeparator());
        fw.close();
    }

    /**
     * Loads the data file. Creates file and directories if data file did not previously exist.
     *
     * @return True if file previously existed (and was not created); False if file did not exist and was created.
     */
    public boolean loadFile() throws IOException {

        // Create data file if it does not exist already
        if (!dataFile.exists()) {
            createDataFile();
            return false; // false = file didn't previously exist, so it was created
        }
        return true; // true = file previously existed, and was not created
    }

    /**
     * Creates a file and any non-existing directories to that file.
     *
     * @throws IOException If an error occurs while creating the file or directories.
     */
    private void createDataFile() throws IOException {
        dataFile.getParentFile().mkdirs(); // Create data directory (does nothing if directory already exists)
        dataFile.createNewFile();
    }


    /**
     * Creates ActivityList and loads data from data file if the data file previously existed.
     * Otherwise, an empty task list is initialized.
     * @return an ActivityList object containing a list of activities provided by the data file.
     */
    public ActivityList createActivityList() {
        try {
            if (loadFile()) {
                activityList = new ActivityList(dataFile);
            } else {
                activityList = new ActivityList();
            }
        } catch (IOException e) {
            System.out.println("Error loading/creating data file.");
        }
        return activityList;
    }

    public void clearFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(dataFile);
        writer.print("");
        writer.close();
    }

}
