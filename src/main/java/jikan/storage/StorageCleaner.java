package jikan.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StorageCleaner {
    private static final String STATUS_FILE_PATH = "data/recycled/status.txt";
    private static final String DATA_FILE_PATH = "data/recycled/data.csv";
    private File status;
    private File recycledData;
    public boolean toClean;
    private int numberOfActivitiesToClean = 3;
    public Storage storage;

    public StorageCleaner(Storage storage) {
        this.storage = storage;
        status = new File(STATUS_FILE_PATH);
        recycledData = new File(DATA_FILE_PATH);
        initialiseCleaner();
        initialiseDataFile();
    }

    private void initialiseDataFile() {
        try {
            loadFile(recycledData);
        } catch (IOException e) {
            System.out.println("Error loading/creating recycled file");
        }
    }

    private void initialiseCleaner() {
        try {
            if (loadCleaner(status)) {
                Scanner sc = new Scanner(status);
                String status = sc.nextLine();
                int value = Integer.parseInt(status);
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

    private boolean loadCleaner(File file) throws IOException {
        if (!file.exists()) {
            createFile(file);
            return false;
        } else {
            return true;
        }
    }

    private void loadFile(File file) throws IOException {
        if (!file.exists()) {
            createFile(file);
        }
    }

    private void createFile(File file) throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

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
