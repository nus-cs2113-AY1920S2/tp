package seedu.duke.storage;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.exception.StorageException;
import seedu.duke.module.Module;
import seedu.duke.module.NewModule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Saves and loads the file of available modules list.
 */
public class StorageAvailableModulesList {

    public static final String DEFAULT_FILEPATH = "data/availableModulesList.csv";
    public static final String FOLDER_PATH = "data";

    public StorageAvailableModulesList() {

    }

    /**
     * Loads the file of Module Manager's available modules list.
     * @return available modules list.
     * @throws StorageException if file path is wrong.
     */
    public static AvailableModulesList load() throws StorageException {
        AvailableModulesList availableModulesList = new AvailableModulesList();
        try {
            loadFileContents(availableModulesList);
        } catch (FileNotFoundException e) {
            throw new StorageException("No file exits. I will make a new one later!");
        } finally {
            return availableModulesList;
        }
    }

    /**
     * Uses scanner to get the data in file and gets the stored available modules list.
     * @throws FileNotFoundException if file path is wrong.
     */
    public static void loadFileContents(AvailableModulesList availableModulesList)
            throws FileNotFoundException, StorageException {

        checkDir();
        File fileToBeLoad = new File(DEFAULT_FILEPATH);
        Scanner s = new Scanner(fileToBeLoad);

        /* skip head */
        if (s.hasNext()) {
            s.nextLine();
        }

        while (s.hasNext()) {
            String moduleInfo = s.nextLine();
            processModuleInfoString(moduleInfo, availableModulesList);
        }
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

    public static void processModuleInfoString(String moduleInfo,
            AvailableModulesList availableModulesList) throws StorageException {

        String[] moduleInfoWords = moduleInfo.split(",");
        if (moduleInfoWords.length < 3) {
            throw new StorageException("Invalid module!");
        }
        String id = moduleInfoWords[0];
        String name = moduleInfoWords[1];
        int mc = Integer.parseInt(moduleInfoWords[2]);
        if (moduleInfoWords.length < 4) {
            addToAvailableModulesList(availableModulesList,new NewModule(id, name, mc));
        } else if (moduleInfoWords.length == 4) {
            String[] preReq = moduleInfoWords[3].split(" ");
            addToAvailableModulesList(availableModulesList, new NewModule(id, name, mc, preReq));
        }
    }

    private static void addToAvailableModulesList(AvailableModulesList availableModulesList, NewModule newModule) {
        for (Module module : availableModulesList) {
            boolean hasSameId = newModule.getId().equals(module.getId());
            boolean hasSameName = newModule.getName().equals(module.getName());
            if (hasSameId && hasSameName) {
                return;
            } else if (hasSameId) {
                module.updateName(newModule.getName());
            } else if (hasSameName) {
                module.updateId(newModule.getId());
            }
        }
        availableModulesList.add(newModule);
    }

    /**
     * Save available modules list into a CSV file.
     * @throws StorageException if gets a IOException when saving.
     */
    public static void save(AvailableModulesList availableModulesList) throws StorageException {
        try {
            writeToFile(availableModulesList);
        } catch (IOException e) {
            throw new StorageException("Some thing wrong when saving.");
        }
    }

    /**
     * Writes the available modules list to a CSV file.
     * @throws IOException if something wrong when writes the file.
     */
    public static void writeToFile(AvailableModulesList availableModulesList) throws IOException {
        FileWriter fw = new FileWriter(DEFAULT_FILEPATH);
        fw.write("id,name,mc,prerequisites" + System.lineSeparator());
        for (Module module: availableModulesList) {
            fw.write(module.toStorageString() +  System.lineSeparator());
        }
        fw.close();
    }

}


