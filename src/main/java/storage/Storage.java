package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class that holds the path and File object for the current data file.
 */
public class Storage {

    /** Path to current data file. */
    public String dataFilePath;

    /** File object for current data file. */
    public File dataFile;

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

    // Return true if file previously existed, false if it didn't exist (and it created a new one)

    /**
     * Loads the data file. Creates file and directories if data file did not previously exist.
     *
     * @return True if file previously existed (and was not created); False if file did not exist and was created.
     */
    public boolean loadFile() {

        // Create data file if it does not exist already
        if (!dataFile.exists()) {
            try  {
                createDataFile();
                return false; // false = file didn't previously exist, so it was created
            } catch (IOException e) {
                System.out.println("Error loading data file.");
            }
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
}
