package jikan.cleaner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * A log cleaner class that does automated cleaning
 * for log files under the user's request.
 */
public class LogCleaner extends Cleaner {

    private static final String LOG_FILE_PATH = "data/LogRecord.txt";
    public int numberOfLogsToClean;


    /**
     * Constructor for the log cleaner.
     */
    public LogCleaner() {
        super.STATUS_FILE_PATH = "data/recycled/logStatus.txt";
        super.status = new File(STATUS_FILE_PATH);
        super.DATA_FILE_PATH = "data/recycled/logData.txt";
        super.recycledData = new File(DATA_FILE_PATH);
        initialiseDataFile();
        int value = initialiseCleaner();
        if (value != -1) {
            this.numberOfLogsToClean = value;
        }
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
    public void logAutoClean() throws IOException {
        List<String> logsForRecycling = new ArrayList<>();
        List<String> logsLeftInData = new ArrayList<>();
        if (this.toClean) {
            File liveData = recycleLog(logsForRecycling, logsLeftInData);

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

    /**
     * Method to clean up log file and move them to the recycled folder.
     * @param logsForRecycling an array list consisting of logs to be written to recycled folder.
     * @param logsLeftInData an array list consisting of the logs left after clean up.
     * @return a log file that holds all the logging at run time.
     * @throws FileNotFoundException if file could not be found at the filepath.
     */
    private File recycleLog(List<String> logsForRecycling, List<String> logsLeftInData) throws FileNotFoundException {
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
        return liveData;
    }
}
