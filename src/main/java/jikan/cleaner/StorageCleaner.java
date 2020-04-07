package jikan.cleaner;

import jikan.storage.Storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A storage cleaner class that does automated cleaning
 * for data files under the user's request.
 */
public class StorageCleaner extends Cleaner {

    public int numberOfActivitiesToClean;
    public Storage storage;

    /**
     * Constructor for the storage cleaner.
     * @param storage an object that holds the data on the list of activities.
     */
    public StorageCleaner(Storage storage) {
        this.storage = storage;
        super.statusFilePath = "data/recycled/status.txt";
        super.status = new File(statusFilePath);
        super.dataFilePath = "data/recycled/data.csv";
        super.recycledData = new File(dataFilePath);
        initialiseDataFile();
        int value = initialiseCleaner();
        if (value != -1) {
            this.numberOfActivitiesToClean = value;
        }
    }

    /**
     * Method to set a value for the number of activities to clean.
     * @param value an integer representing the number to clean.
     * @throws IOException if there is an error with reading/writing to the status file.
     */
    public void setNumberOfActivitiesToClean(int value) throws IOException {
        boolean status = this.toClean;
        File dataFile = new File(statusFilePath);
        this.numberOfActivitiesToClean = value;
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));
        if (status) {
            writer.write("1" + "\n");
        } else {
            writer.write("0" + "\n");
        }
        writer.write(Integer.toString(value) + "\n");
        writer.close();
    }

    /**
     * Method to clear up the live data file and move them to the recycled data file.
     * @throws IOException if there is an error with reading/writing to the live data file and
     *         recycled data file.
     */
    public void storageAutoClean() throws IOException {
        List<String> activitiesForRecycling = new ArrayList<>();
        List<String> activitiesLeftInData = new ArrayList<>();
        if (this.toClean) {
            File liveData = recycleData(activitiesForRecycling, activitiesLeftInData);

            BufferedWriter recycledDataWriter = new BufferedWriter(new FileWriter(recycledData));
            for (String line : activitiesForRecycling) {
                recycledDataWriter.write(line + "\n");
            }
            recycledDataWriter.close();
            BufferedWriter liveDataWriter = new BufferedWriter(new FileWriter(liveData));
            for (String line : activitiesLeftInData) {
                liveDataWriter.write(line + "\n");
            }
            liveDataWriter.close();
        }
    }

    /**
     * Method to clean up data file and move them to the recycled folder.
     * @param activitiesForRecycling an array list consisting of data to be written to recycled folder.
     * @param activitiesLeftInData an array list consisting of the data left after clean up.
     * @return a storage file that holds the activity list at run time.
     * @throws FileNotFoundException if file could not be found at the filepath.
     */
    private File recycleData(List<String> activitiesForRecycling, List<String> activitiesLeftInData)
            throws FileNotFoundException {
        String filePath = storage.dataFilePath;
        File liveData = new File(filePath);
        Scanner recycledDataScanner = new Scanner(recycledData);
        Scanner liveDataScanner = new Scanner(liveData);
        while (recycledDataScanner.hasNext()) {
            String line = recycledDataScanner.nextLine();
            activitiesForRecycling.add(line);
        }
        parseLiveData(activitiesForRecycling, activitiesLeftInData, liveDataScanner);
        return liveData;
    }

    /**
     * Method to check the activities in storage line by line and see if they should be cleaned or not.
     * @param activitiesForRecycling an array list consisting of data to be written to recycled folder.
     * @param activitiesLeftInData an array list consisting of the data left after clean up.
     * @param liveDataScanner used to scan the activities in storage line by line.
     */
    private void parseLiveData(List<String> activitiesForRecycling, List<String> activitiesLeftInData,
                               Scanner liveDataScanner) {
        while (numberOfActivitiesToClean != 0) {
            if (!liveDataScanner.hasNext()) {
                break;
            }
            String line = liveDataScanner.nextLine();
            String[] tokenizedLine = line.split(",");
            Duration duration = Duration.parse(tokenizedLine[3]);
            Duration allocatedTime = Duration.parse(tokenizedLine[4]);
            int result = duration.compareTo(allocatedTime);
            if (result >= 0 && allocatedTime != Duration.parse("PT0S")) {
                activitiesForRecycling.add(line);
                numberOfActivitiesToClean -= 1;
            } else {
                activitiesLeftInData.add(line);
            }
        }
        while (liveDataScanner.hasNext()) {
            String line = liveDataScanner.nextLine();
            activitiesLeftInData.add(line);
        }
    }
}
