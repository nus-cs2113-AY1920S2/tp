package seedu.duke;

import seedu.events.Event;
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
    private static Path filepath = Paths.get(dir, "data", "escData.txt");
    private static String filepathStr  = String.valueOf(filepath);
    private static File saveFile = new File(filepathStr);

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
                throw new EscException("File creation error");
            }
        }
        assert saveFile.exists() : "the save file should be created";
    }

    /**
     * Loads any pre-existing cards & events from the save file into an ArrayList to be initiated.
     * @return ArrayList of pre-existing subject & event ArrayLists (if any) OR blank ArrayList of ArrayLists
     * @throws EscException if load fails
     */
    @SuppressWarnings("unchecked")
    public static ArrayList loadObjects() throws EscException {
        ArrayList tempSub;
        ArrayList tempEvent;

        ArrayList returnList = new ArrayList<>();
        ArrayList<Subject> loadSubs = new ArrayList<>();
        ArrayList<Event> loadEvent = new ArrayList<>();

        if (!saveFile.exists()) {
            ensureFileExists();
            returnList.add(loadSubs);
            returnList.add(loadEvent);
        } else {
            try {
                FileInputStream fileRead = new FileInputStream(saveFile);
                ObjectInputStream objRead = new ObjectInputStream(fileRead);

                tempSub = (ArrayList) objRead.readObject();
                for (int i = 0; i < tempSub.size(); i++) {
                    loadSubs.add((Subject) tempSub.get(i));
                }

                tempEvent = (ArrayList) objRead.readObject();
                for (int i = 0; i < tempEvent.size(); i++) {
                    loadEvent.add((Event) tempEvent.get(i));
                }
                objRead.close();

                returnList.add(loadSubs);
                returnList.add(loadEvent);
            } catch (IOException | ClassNotFoundException e) {
                throw new EscException("Load error");
            }
        }
        return returnList;
    }

    /**
     * Saves the current subjectList and eventList to the save file.
     * @param currSub ArrayList(S) of current subjects
     * @param currEvent ArrayList(E) of current events
     * @throws EscException if the ArrayLists are unable to be saved
     */
    public void saveSubs(ArrayList<Subject> currSub, ArrayList<Event> currEvent) throws EscException {
        ensureFileExists();

        try {
            FileOutputStream fileWrite = new FileOutputStream(saveFile);
            ObjectOutputStream objWrite = new ObjectOutputStream(fileWrite);

            objWrite.writeObject(currSub);
            objWrite.writeObject(currEvent);
            objWrite.flush();
            objWrite.close();
        } catch (IOException e) {
            throw new EscException("Save error");
        }
    }
}
