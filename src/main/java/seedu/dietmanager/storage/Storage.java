package seedu.dietmanager.storage;

import seedu.dietmanager.commons.core.LogsCentre;
import seedu.dietmanager.commons.exceptions.InvalidAgeException;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.commons.exceptions.InvalidGenderException;
import seedu.dietmanager.commons.exceptions.InvalidHeightException;
import seedu.dietmanager.commons.exceptions.InvalidNameException;
import seedu.dietmanager.commons.exceptions.InvalidWeightException;
import seedu.dietmanager.logic.parser.AgeParser;
import seedu.dietmanager.logic.parser.GenderParser;
import seedu.dietmanager.logic.parser.HeightParser;
import seedu.dietmanager.logic.parser.NameParser;
import seedu.dietmanager.logic.parser.StorageParser;
import seedu.dietmanager.logic.parser.WeightParser;
import seedu.dietmanager.model.FoodNutritionRecord;
import seedu.dietmanager.model.Profile;
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
import java.util.Optional;
import java.util.Scanner;

/**
 * Storage is the public class responsible for creating and managing the data files generated from the application.
 */

public class Storage {

    /**
     * The object containing the list containing all current tasks.
     */

    private UI ui;

    private LogsCentre logsCentre;

    private Profile profile;

    private FoodNutritionRecord foodNutritionRecord;

    /**
     * The file path of the directory that contains the data file.
     */

    private static String DATA_DIRECTORY_PATH = "data";

    /**
     * The file path of the data file that contains profile information.
     */

    private static String PROFILE_FILE_PATH = DATA_DIRECTORY_PATH
            + File.separator + "profile.txt";

    /**
     * The file path of the data file that contains food record information.
     */

    private static String DAILY_FOOD_RECORD_FILE_PATH = DATA_DIRECTORY_PATH
            + File.separator + "daily-food-record.txt";

    /** To be implemented at a later stage.
     * The file path of the data file that contains food nutritional information.
     */

    private static String FOOD_NUTRITION_RECORD_FILE_PATH = DATA_DIRECTORY_PATH
            + File.separator + "food-nutrition-record.txt";

    /**
     * Constructs the Storage object.
     *
     * @param ui the object containing user interface functions.
     */

    public Storage(UI ui, LogsCentre logsCentre, Profile profile, FoodNutritionRecord foodNutritionRecord) {
        this.ui = ui;
        this.logsCentre = logsCentre;
        this.profile = profile;
        this.foodNutritionRecord = foodNutritionRecord;

        this.loadDataDirectory();
        this.loadProfileFile();
        //this.loadDailyFoodRecordFile();
        //this.loadFoodNutrtionRecordFile();
    }

    /**
     * Searches for the directory, if absent, creates a new directory.
     */

    public void loadDataDirectory() {
        Path directoryPath = Paths.get(DATA_DIRECTORY_PATH);
        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectory(directoryPath);
                logsCentre.writeInfoLog("New Directory created: " + directoryPath.getFileName().toString());
            } catch (IOException e) {
                logsCentre.writeSevereLog("Error in creating new directory");
                ui.displayFileErrorMessage();
            }
        } else {
            logsCentre.writeInfoLog("Existing Directory found: " + directoryPath.getFileName().toString());
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
                logsCentre.writeInfoLog("No existing Profile found, new file created: "
                        + profileData.getName().toString());
            } else {
                logsCentre.writeInfoLog("Existing Profile found: "
                        + profileData.getName().toString());
                this.readProfileFile();
            }
        } catch (IOException e) {
            logsCentre.writeSevereLog("Error in Profile data file");
            ui.displayFileErrorMessage();
        }
    }

    /**
     * Reads the data file and parses the existing data in the file, converting it into tasks which is added into
     * the tasklist such that it is accessible by the user.
     */

    public void readProfileFile() {
        try {
            File profileData = new File(PROFILE_FILE_PATH);
            Scanner myReader = new Scanner(profileData);
            Optional<String> name = Optional.empty();
            Optional<Integer> age = Optional.empty();
            Optional<String> gender = Optional.empty();
            Optional<Double> height = Optional.empty();
            Optional<Double> weight = Optional.empty();
            Optional<Double> weightGoal = Optional.empty();

            while (myReader.hasNextLine()) {
                String dataLine = myReader.nextLine();
                String[] dataLineArray = StorageParser.parseProfileDataLine(dataLine);
                String label = dataLineArray[0];
                String description = dataLineArray[1];
                switch (label) {
                case "Name":
                    name = Optional.of(NameParser.parseName(description));
                    break;
                case "Age":
                    age = Optional.of(AgeParser.parseAge(description));
                    break;
                case "Gender":
                    gender = Optional.of(GenderParser.parseGender(description));
                    break;
                case "Height":
                    height = Optional.of(HeightParser.parseHeight(description));
                    break;
                case "Weight":
                    weight = Optional.of(WeightParser.parseWeight(description));
                    break;
                case "Weight-Goal":
                    weightGoal = Optional.of(WeightParser.parseWeight(description));
                    break;
                default:
                    throw new InvalidFormatException();
                }
            }
            if (name.isPresent() && age.isPresent() && gender.isPresent()
                    && height.isPresent() && weight.isPresent() && weightGoal.isPresent()) {
                String profileName = name.get();
                int profileAge = age.get();
                String profileGender = gender.get();
                double profileHeight = height.get();
                double profileWeight = weight.get();
                double profileWeightGoal = weightGoal.get();
                this.profile.setProfile(profileName, profileAge, profileGender,
                        profileHeight, profileWeight, profileWeightGoal);
            } else {
                throw new InvalidFormatException();
            }
            myReader.close();
        } catch (FileNotFoundException | InvalidFormatException | InvalidNameException | InvalidAgeException
                | InvalidGenderException | InvalidHeightException | InvalidWeightException e) {
            logsCentre.writeInfoLog("Error in loading Profile Information, Profile cleared.");
            clearProfileFile();
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

    public void writeProfileFile() {
        try {
            FileWriter myWriter = new FileWriter(PROFILE_FILE_PATH, false);
            myWriter.write("Name: " + this.profile.getName() + System.lineSeparator());
            myWriter.write("Age: " + this.profile.getAge() + System.lineSeparator());
            myWriter.write("Gender: " + this.profile.getGender() + System.lineSeparator());
            myWriter.write("Height: " + this.profile.getHeight() + System.lineSeparator());
            myWriter.write("Weight: " + this.profile.getWeight() + System.lineSeparator());
            myWriter.write("Weight-Goal: " + this.profile.getWeightGoal());
            myWriter.close();
        } catch (IOException e) {
            ui.displayFileErrorMessage();
        }
    }

}
