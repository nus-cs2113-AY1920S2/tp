package seedu.duke.storage;

import seedu.duke.data.AvailableModulesList;
import seedu.duke.data.SemModulesList;
import seedu.duke.data.SemesterList;
import seedu.duke.exception.StorageException;
import seedu.duke.module.Module;
import seedu.duke.module.SelectedModule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StorageSemesterList {

    public static final String DEFAULT_FILEPATH = "data/semesterList.csv";
    public static final String FOLDER_PATH = "data";

    public StorageSemesterList() {

    }

    /**
     * Loads the file of Module Manager's available modules list.
     * @return available modules list.
     * @throws StorageException if file path is wrong.
     */
    public static SemesterList load() throws StorageException {
        SemesterList semesterList = new SemesterList();
        try {
            loadFileContents(semesterList);
        } catch (FileNotFoundException e) {
            throw new StorageException("No file exits. I will make a new one later!");
        } finally {
            return semesterList;
        }
    }

    /**
     * Uses scanner to get the data in file and gets the stored available modules list.
     * @throws FileNotFoundException if file path is wrong.
     */
    public static void loadFileContents(SemesterList semesterList)
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
            processModuleInfoString(moduleInfo, semesterList);
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
                                               SemesterList semesterList) throws StorageException {

        String[] moduleInfoWords = moduleInfo.split(",");
        String id = moduleInfoWords[0];
        String name = moduleInfoWords[1];
        int mc = Integer.parseInt(moduleInfoWords[2]);
        String sem = moduleInfoWords[3];
        boolean isDone = Boolean.valueOf(moduleInfoWords[4]).booleanValue();
        String grade = moduleInfoWords[5];
        SelectedModule selectedModule = new SelectedModule(id, name, sem, mc, isDone, grade);
        checkAvailableModulesList(selectedModule);
        addToSemesterList(semesterList, selectedModule);
    }

    private static void checkAvailableModulesList(SelectedModule selectedModule) {
        for (Module availableModule: AvailableModulesList.availableModulesList) {
            boolean isSameName = availableModule.getName().equals(selectedModule.getName());
            boolean isSameId = availableModule.getId().equals(selectedModule.getId());
            if (isSameName || isSameId) {
                selectedModule.setModuleConfig(availableModule);
            }
        }
    }

    private static void addToSemesterList(SemesterList semesterList, SelectedModule selectedModule) {

        for (SemModulesList sem: semesterList) {
            if (sem.getSem().equals(selectedModule.getSem())) {
                sem.add(selectedModule);
                return;
            }
        }

        SemModulesList sem = new SemModulesList(selectedModule.getSem());
        sem.add(selectedModule);
        semesterList.add(sem);
    }

    /**
     * Save available modules list into a CSV file.
     * @throws StorageException if gets a IOException when saving.
     */
    public static void save(SemesterList semesterList) throws StorageException {
        try {
            writeToFile(semesterList);
        } catch (IOException e) {
            throw new StorageException("Some thing wrong when saving.");
        }
    }

    /**
     * Writes the available modules list to a CSV file.
     * @throws IOException if something wrong when writes the file.
     */
    public static void writeToFile(SemesterList semesterList) throws IOException {
        FileWriter fw = new FileWriter(DEFAULT_FILEPATH);
        fw.write("id,name,mc,semester,isDone,grade" + System.lineSeparator());
        for (SemModulesList sem: semesterList) {
            for(SelectedModule module: sem) {
                fw.write(module.toStorageString() + System.lineSeparator());
            }
        }
        fw.close();
    }

}


