package jikan.cleaner;

import jikan.storage.Storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A storage cleaner class that does automated cleaning
 * for data files under the user's request.
 */
public class StorageCleaner extends Cleaner {
    //private static final String STATUS_FILE_PATH = "data/recycled/status.txt";
    //private static final String DATA_FILE_PATH = "data/recycled/data.csv";
    //private File status;
    //private File recycledData;
    //public boolean toClean;


    public int numberOfActivitiesToClean;
    public Storage storage;

    /**
     * Constructor for the storage cleaner.
     * @param storage an object that holds the data on the list of activities.
     */
    public StorageCleaner(Storage storage) {
        this.storage = storage;
        super.STATUS_FILE_PATH = "data/recycled/status.txt";
        super.status = new File(STATUS_FILE_PATH);
        super.DATA_FILE_PATH = "data/recycled/data.csv";
        super.recycledData = new File(DATA_FILE_PATH);
        initialiseDataFile();
        int value = initialiseCleaner();
        if (value != -1) {
            this.numberOfActivitiesToClean = value;
        }
        /*this.storage = storage;
        status = new File(STATUS_FILE_PATH);
        recycledData = new File(DATA_FILE_PATH);
        initialiseCleaner();
        initialiseDataFile();*/
    }

    /**
     * Activates/De-activates the auto cleanup by checking the status file.
     */
    /*private void initialiseCleaner() {
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
                this.numberOfActivitiesToClean = Integer.parseInt(line);
            } else {
                FileWriter fw = new FileWriter(status);
                fw.write("0" + "\n");
                fw.write("3" + "\n");
                fw.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading/creating cleaning file.");
        }
    }*/

    /**
     * Method to activate/de-activate the auto cleanup.
     * @param status a boolean specifying whether the cleaner should be activated or not.
     * @throws IOException if there is an error with reading/writing to the status file.
     */
    /*public void setStatus(boolean status) throws IOException {
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
        writer.write(Integer.toString(this.numberOfActivitiesToClean) + "\n");
        writer.close();
    }*/

    /**
     * Method to set a value for the number of activities to clean.
     * @param value an integer representing the number to clean.
     * @throws IOException if there is an error with reading/writing to the status file.
     */
    public void setNumberOfActivitiesToClean(int value) throws IOException {
        boolean status = this.toClean;
        File dataFile = new File(STATUS_FILE_PATH);
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
    public void autoClean() throws IOException {
        List<String> activitiesForRecycling = new ArrayList<>();
        List<String> activitiesLeftInData = new ArrayList<>();
        if (this.toClean) {
            String filePath = storage.dataFilePath;
            File liveData = new File(filePath);
            Scanner recycledDataScanner = new Scanner(recycledData);
            Scanner liveDataScanner = new Scanner(liveData);
            while (recycledDataScanner.hasNext()) {
                String line = recycledDataScanner.nextLine();
                activitiesForRecycling.add(line);
            }
            while (numberOfActivitiesToClean != 0) {
                if (!liveDataScanner.hasNext()) {
                    break;
                }
                String line = liveDataScanner.nextLine();
                activitiesForRecycling.add(line);
                numberOfActivitiesToClean -= 1;
            }
            while (liveDataScanner.hasNext()) {
                String line = liveDataScanner.nextLine();
                activitiesLeftInData.add(line);
            }

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
}
