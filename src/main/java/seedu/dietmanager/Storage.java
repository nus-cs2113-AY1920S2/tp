package seedu.dietmanager;

import seedu.dietmanager.ui.UI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Storage is the public class responsible for creating and managing the data files generated from the application.
 */

public class Storage {

    private AppLogger appLogger;
    /**
     * The object containing the list containing all current tasks.
     */

    private UI ui;

    /**
     * The file path of the directory that contains the data file.
     */

    private static String DIRECTORY_PATH = "data";

    /**
     * The file path of the data file that contains profile information.
     */

    private static String PROFILE_FILE_PATH = DIRECTORY_PATH + File.separator + "profile.txt";

    /* To be implemented at a later stage
     * The file path of the data file that contains food record information.
     */

    /* To be implemented at a later stage
    private static String FOOD_RECORD_FILE_PATH = DIRECTORY_PATH + File.separator + "food-record.csv";
    */

    /* To be implemented at a later stage.
     * The file path of the data file that contains food nutritional information.
     */

    /* To be implemented at a later stage.
    private static String NUTRITION_INFO_FILE_PATH = "data" + File.separator + "nutrition-info.csv";
     */

    /**
     * Constructs the Storage object.
     * @param ui the object containing user interface functions.
     */

    public Storage(UI ui, AppLogger appLogger) {
        this.ui = ui;
        this.appLogger = appLogger;
        this.loadDataDirectory();
        this.loadProfileFile();
        //this.loadFoodRecordFile();
    }

    /**
     * Searches for the directory, if absent, creates a new directory.
     */

    public void loadDataDirectory() {
        Path directoryPath = Paths.get(DIRECTORY_PATH);
        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectory(directoryPath);
                appLogger.writeInfoLog("New Directory created: " + directoryPath.getFileName().toString());
            } catch (IOException e) {
                appLogger.writeSevereLog("Error in creating new directory");
                ui.displayFileErrorMessage();
            }
        } else {
            appLogger.writeInfoLog("Existing Directory found: " + directoryPath.getFileName().toString());
        }
    }

    /**
     * Searches for the data file in the directory, if absent, creates a new data file. <br>
     * If data file is present, loads the existing data from the file such that it is accessible by the user.
     */

    public void loadProfileFile() {
        try {
            File profileData = new File(PROFILE_FILE_PATH);
            if (profileData.createNewFile()) {
                appLogger.writeInfoLog("No existing Profile found, new file created: "
                        + profileData.getName().toString());
            } else {
                this.readProfileFile();
                appLogger.writeInfoLog("Existing Profile found: "
                        + profileData.getName().toString());
            }
        } catch (IOException e) {
            appLogger.writeSevereLog("Error in Profile data file");
            ui.displayFileErrorMessage();
        }
    }

    /**
     * Reads the data file and parses the existing data in the file, converting it into tasks which is added into
     * the tasklist such that it is accessible by the user.
     */

    public void readProfileFile() {
        try {
            File dukeData = new File(PROFILE_FILE_PATH);
            Scanner myReader = new Scanner(dukeData);
            while (myReader.hasNextLine()) {
                String dataLine = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            ui.displayFileErrorMessage();
        }
    }

    /**
     * Clears all content in the data file.
     */

    public void clearProfileFile() {
        try {
            PrintWriter pw = new PrintWriter(PROFILE_FILE_PATH);
            pw.close();
        } catch (FileNotFoundException e) {
            ui.displayFileErrorMessage();
        }
    }

    /**
     * Rewrites the data file to reflect the current data.
     */

    public void rewriteProfileFile() {
        try {
            FileWriter myWriter = new FileWriter(PROFILE_FILE_PATH, false);
            myWriter.write("");
            myWriter.close();
        } catch (IOException e) {
            ui.displayFileErrorMessage();
        }
    }

    /**
     * Appends a new line of data into the data file.
     */

    public void writeProfileFileLine() {
        try {
            BufferedWriter myWriter = new BufferedWriter(new FileWriter(PROFILE_FILE_PATH, true));
            myWriter.write("");
            myWriter.close();
        } catch (IOException e) {
            ui.displayFileErrorMessage();
        }
    }

}
