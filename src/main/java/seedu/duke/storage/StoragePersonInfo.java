package seedu.duke.storage;

import seedu.duke.data.Person;
import seedu.duke.exception.StorageException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Saves and loads the file of Person information.
 */
public class StoragePersonInfo {
    public static final String DEFAULT_FILEPATH = "data/personInfo.csv";
    public static final String FOLDER_PATH = "data";

    public StoragePersonInfo() {

    }

    /**
     * Loads the file of Module Manager's person information.
     * @return Person information.
     * @throws StorageException if file path is wrong.
     */
    public static Person load() throws StorageException {
        try {
            return loadFileContents();
        } catch (FileNotFoundException e) {
            throw new StorageException("No person information file exits. I will make a new one later!");
        }
    }

    /**
     * Uses scanner to get the data in file and gets the stored person information.
     * @return Person.
     * @throws FileNotFoundException if file path is wrong.
     */
    public static Person loadFileContents() throws FileNotFoundException, StorageException {
        checkDir();
        File fileToBeLoad = new File(DEFAULT_FILEPATH);
        Scanner s = new Scanner(fileToBeLoad);
        Person person;

        if (!s.hasNext()) {
            throw new StorageException("Invalid person information!");
        }

        String personInfo = s.nextLine();
        person = processPersonInfoString(personInfo);
        return person;
    }

    /**
     * Checks whether the data folder exits. If not, make this dir.
     */
    private static void checkDir() {
        File dir = new File(FOLDER_PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    public static Person processPersonInfoString(String personInfo) throws StorageException {
        String[] personWords = personInfo.split(",");
        if (personWords.length < 2) {
            throw new StorageException("Invalid person information!");
        }
        String name = personWords[0];
        int matricYear = Integer.parseInt(personWords[1]);
        return new Person(name, matricYear);
    }

    /**
     * Save person information into a local file.
     * @throws StorageException if gets a IOException when saving.
     */
    public static void save() throws StorageException {
        try {
            writeToFile();
        } catch (IOException e) {
            throw new StorageException("Some thing wrong when saving.");
        }
    }

    /**
     * Writes the person information to the local file.
     * @throws IOException if something wrong when writes the file.
     */
    public static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(DEFAULT_FILEPATH);
        fw.write(Person.toStorageString());
        fw.close();
    }

}

