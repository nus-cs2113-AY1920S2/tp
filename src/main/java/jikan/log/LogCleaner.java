package jikan.log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * A log cleaner class that does automated cleaning
 * for log files under the user's request.
 */
public class LogCleaner {
    private static final String STATUS_FILE_PATH = "data/recycled/logStatus.txt";
    private static final String DATA_FILE_PATH = "data/recycled/logData.txt";
    private static final String LOG_FILE_PATH = "data/LogRecord.txt";
    private File status;
    private File recycledData;
    public boolean toClean;
    private int numberOfLogsToClean;


    /**
     * Constructor for the log cleaner.
     */
    public LogCleaner() {
        status = new File(STATUS_FILE_PATH);
        recycledData = new File(DATA_FILE_PATH);
        initialiseCleaner();
        initialiseDataFile();
    }

    /**
     * Initialise a data file containing the deleted logs.
     */
    private void initialiseDataFile() {
        try {
            loadFile(recycledData);
        } catch (IOException e) {
            System.out.println("Error loading/creating recycled file");
        }
    }

    /**
     * Activates/De-activates the auto cleanup of logs by checking the status file.
     */
    private void initialiseCleaner() {
        try {
            if (loadCleaner(status)) {
                Scanner sc = new Scanner(status);
                String status = sc.nextLine();
                int value = Integer.parseInt(status);
                assert value == 0 | value == 1;
                if (value == 1) {
                    this.toClean = true;
                } else {
                    this.toClean = false;
                }
                String line = sc.nextLine();
                this.numberOfLogsToClean = Integer.parseInt(line);
            } else {
                FileWriter fw = new FileWriter(status);
                fw.write("0" + "\n");
                fw.write("10" + "\n");
                fw.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading/creating cleaning file.");
        }
    }

    /**
     * Loads the status file and checks if it exists or not.
     * @param file status file.
     * @return true if the file exists and false otherwise.
     * @throws IOException if there is an error with the creation/loading of the status file.
     */
    private boolean loadCleaner(File file) throws IOException {
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
    private void loadFile(File file) throws IOException {
        if (!file.exists()) {
            createFile(file);
        }
    }

    /**
     * Creates a new file if the specified file cannot be found in the given path.
     * @param file the file to be created if it does not exist.
     * @throws IOException if there is an error with the creation of the file.
     */
    private void createFile(File file) throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Method to activate/de-activate the auto cleanup for logs.
     * @param status a boolean specifying whether the log cleaner should be activated or not.
     * @throws IOException if there is an error with reading/writing to the status file.
     */
    public void setStatus(boolean status) throws IOException {
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
        writer.write(Integer.toString(this.numberOfLogsToClean) + "\n");
        writer.close();
    }

    /**
     * Method to set a value for the number of logs to clean.
     * @param value an integer representing the number to clean.
     * @throws IOException if there is an error with reading/writing to the status file.
     */
    public void setNumberOfLogsToClean(int value) throws IOException {
        boolean status = this.toClean;
        File dataFile = new File(STATUS_FILE_PATH);
        this.numberOfLogsToClean = value;
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter((dataFile)));
        if (status) {
            writer.write("1" + "\n");
        } else {
            writer.write("0" + "\n");
        }
        writer.write(Integer.toString(value) + "\n");
        writer.close();
    }

    /**
     * Method to clear up the live log file and move them to the recycled log file.
     * @throws IOException if there is an error with reading/writing to the live log file and
     *         recycled log file.
     */
    public void autoClean() throws IOException {
        List<String> logsForRecycling = new ArrayList<>();
        List<String> logsLeftInData = new ArrayList<>();
        if (this.toClean) {
            File liveData = new File(LOG_FILE_PATH);
            Scanner recycledDataScanner = new Scanner(recycledData);
            Scanner liveDataScanner = new Scanner(liveData);
            while (recycledDataScanner.hasNext()) {
                String line = recycledDataScanner.nextLine();
                logsForRecycling.add(line);
            }
            while (numberOfLogsToClean != 0) {
                if (!liveDataScanner.hasNext()) {
                    break;
                }
                String line = liveDataScanner.nextLine();
                logsForRecycling.add(line);
                numberOfLogsToClean -= 1;
            }
            while (liveDataScanner.hasNext()) {
                String line = liveDataScanner.nextLine();
                logsLeftInData.add(line);
            }

            BufferedWriter recycledDataWriter = new BufferedWriter(new FileWriter(recycledData));
            for (String line : logsForRecycling) {
                recycledDataWriter.write(line + "\n");
            }
            recycledDataWriter.close();
            BufferedWriter liveDataWriter = new BufferedWriter(new FileWriter(liveData));
            for (String line : logsLeftInData) {
                liveDataWriter.write(line + "\n");
            }
            liveDataWriter.close();
        }
    }
}
