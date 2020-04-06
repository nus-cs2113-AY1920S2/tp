package jikan.cleaner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class Cleaner {
    protected static String STATUS_FILE_PATH;
    protected static String DATA_FILE_PATH;
    protected File status;
    protected File recycledData;
    public boolean toClean;
    private static final int defaultNumberOfDataToClean = 5;

    /**
     * Initialise a data file containing the deleted logs.
     */
    protected void initialiseDataFile() {
        try {
            loadFile(recycledData);
        } catch (IOException e) {
            System.out.println("Error loading/creating recycled file");
        }
    }

    /**
     * Loads the status file and checks if it exists or not.
     * @param file status file.
     * @return true if the file exists and false otherwise.
     * @throws IOException if there is an error with the creation/loading of the status file.
     */
    protected boolean loadCleaner(File file) throws IOException {
        if (!file.exists()) {
            createFile(file);
            return false;
        } else {
            return true;
        }
    }

    /**
     * Loads the data file that contains deleted logs.
     * @param file data file with the deleted logs.
     * @throws IOException if there is an error with the creation/loading of the data file.
     */
    protected void loadFile(File file) throws IOException {
        if (!file.exists()) {
            createFile(file);
        }
    }

    /**
     * Creates a new file if the specified file cannot be found in the given path.
     * @param file the file to be created if it does not exist.
     * @throws IOException if there is an error with the creation of the file.
     */
    protected void createFile(File file) throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Activates/De-activates the auto clean up by checking the status file.
     * @return the number of lines of data to automatically clean.
     */
    protected int initialiseCleaner() {
        try {
            if (loadCleaner(status)) {
                Scanner sc = new Scanner(status);
                String status = sc.nextLine();
                int value = Integer.parseInt(status);
                assert value == 0 || value == 1;
                if (value == 1) {
                    this.toClean = true;
                } else {
                    this.toClean = false;
                }
                String line = sc.nextLine();
                return Integer.parseInt(line);
            } else {
                FileWriter fw = new FileWriter(status);
                fw.write("0" + "\n");
                fw.write("5" + "\n");
                fw.close();
                return defaultNumberOfDataToClean;
            }
        } catch (IOException e) {
            System.out.println("Error loading/creating cleaning file.");
            return -1;
        }
    }

    /**
     * Method to activate/de-activate the auto cleanup.
     * @param status a boolean specifying whether the cleaner should be activated or not.
     * @param number an integer specifying the number of lines of data to automatically clean.
     * @throws IOException if there is an error with reading/writing to the status file.
     */
    public void setStatus(boolean status, int number) throws IOException {
        this.toClean = status;
        File dataFile = new File(STATUS_FILE_PATH);
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));
        if (this.toClean) {
            writer.write("1" + "\n");
        } else {
            writer.write("0" + "\n");
        }
        writer.write(Integer.toString(number) + "\n");
        writer.close();
    }

}
