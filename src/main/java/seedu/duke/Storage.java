package seedu.duke;

import seedu.exception.EscException;
import seedu.subjects.Subject;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    private static String dir = System.getProperty("user.dir");
    private static Path filepath = Paths.get(dir, "data", "cards.txt");
    private static String filepathStr  = String.valueOf(filepath);
    private static File saveFile = new File(filepathStr);
    private static Path saveDir = Paths.get(dir, "data");

    /**
     * Default constructor.
     */
    public Storage() {

    }

    /**
     * For Unit testing ONLY, saveFile should not be accessed by user.
     * @param altSaveFile the alternate File object
     * @throws EscException if file creation fails
     */
    public Storage(File altSaveFile) throws EscException {
        saveFile = altSaveFile;
    }

    /**
     * Checks if the save file exists and creates a new save file if it does not exist.
     * @throws EscException if File is unable to be created
     */
    private static void ensureFileExists() throws EscException {
        if (!saveFile.exists()) {
            try {
                saveFile.getParentFile().mkdirs();
                saveFile.createNewFile();
            } catch (IOException e) {
                throw new EscException("File creation error in ensure");
            }
        }
    }

    /**
     * Loads any pre-existing cards from the save file and creates a new card list.
     * @return pre-existing card list (if any) or a new list
     */
    public static ArrayList<Subject> loadSubs() throws EscException {
        ArrayList<Subject> loadSubs = new ArrayList<>();
        ArrayList tempList;
        if (!saveFile.exists()) {
            try {
                saveFile.getParentFile().mkdirs();
                saveFile.createNewFile();
            } catch (IOException e) {
                throw new EscException("File creation error ");
            }
        } else {
            try {
                FileInputStream fileRead = new FileInputStream(saveFile);
                ObjectInputStream objRead = new ObjectInputStream(fileRead);
                tempList = (ArrayList) objRead.readObject();
                for (int i = 0; i < tempList.size(); i++) {
                    loadSubs.add((Subject) tempList.get(i));
                    System.out.println("adding");
                }

                objRead.close();
            } catch (IOException | ClassNotFoundException e) {
                throw new EscException("Load error");
            }
        }
        return loadSubs;
    }

    /**
     * Saves the current card list to the save file.
     * @param currSub the current subject list
     */
    public void saveSubs(ArrayList<Subject> currSub) throws EscException {
        ensureFileExists();

        try {
            FileOutputStream fileWrite = new FileOutputStream(saveFile);
            ObjectOutputStream objWrite = new ObjectOutputStream(fileWrite);

            objWrite.writeObject(currSub);
            objWrite.flush();
            objWrite.close();
        } catch (IOException e) {
            throw new EscException("Save error");
        }
    }
}
