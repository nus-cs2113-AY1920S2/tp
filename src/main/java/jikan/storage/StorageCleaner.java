package jikan.storage;

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
public class StorageCleaner {
    private static final String STATUS_FILE_PATH = "data/recycled/status.txt";
    private static final String DATA_FILE_PATH = "data/recycled/data.csv";
    private File status;
    private File recycledData;
    public boolean toClean;
    private int numberOfActivitiesToClean = 3;
    public Storage storage;

    /**
     * Constructor for the storage cleaner.
     * @param storage an object that holds the data on the list of activities.
     */
    public StorageCleaner(Storage storage) {
        this.storage = storage;
        status = new File(STATUS_FILE_PATH);
        recycledData = new File(DATA_FILE_PATH);
        initialiseCleaner();
        initialiseDataFile();
    }

    /**
     * Initialises a data file contained the deleted entries.
     */
    private void initialiseDataFile() {
        try {
            loadFile(recycledData);
        } catch (IOException e) {
            System.out.println("Error loading/creating recycled file");
        }
    }

    /**
     * Activates/De-activates the auto cleanup by checking the status file.
     */
    private void initialiseCleaner() {
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
            } else {
                FileWriter fw = new FileWriter(status);
                fw.write("0");
                fw.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading/creating cleaning file.");
        }
    }

    /**
     * Loads the status file and checks if the file exists or not.
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
     * Loads the data file that contains deleted entries.
     * @param file data file with the deleted entries.
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
     * Method to activate/de-activate the auto cleanup.
     * @param status a boolean specifying whether the cleaner should be activated or not.
     * @throws IOException if there is an error with reading/writing to the status file
     */
    public void setStatus(boolean status) throws IOException {
        this.toClean = status;
        File dataFile = new File(STATUS_FILE_PATH);
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));
        if (this.toClean) {
            writer.write("1");
        } else {
            writer.write("0");
        }
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
