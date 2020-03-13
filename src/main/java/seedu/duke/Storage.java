package seedu.duke;

import seedu.cards.Card;
import seedu.exception.EscException;

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

    /**
     * Loads any pre-existing cards from the save file and creates a new card list.
     * @return pre-existing card list (if any) or a new list
     */
    public static ArrayList<Card> loadCards() throws EscException {
        ArrayList<Card> loadCards = new ArrayList<>();
        ArrayList tempList;
        if (!saveFile.exists()) {
            try {
                saveFile.getParentFile().mkdirs();
                saveFile.createNewFile();
            } catch (IOException e) {
                throw new EscException("File creation error");
            }
        } else {
            try {
                FileInputStream fileRead = new FileInputStream(saveFile);
                ObjectInputStream objRead = new ObjectInputStream(fileRead);
                tempList = (ArrayList) objRead.readObject();

                for (int i = 0; i < tempList.size(); i++) {
                    loadCards.add((Card) tempList.get(i));
                }

                objRead.close();
            } catch (IOException | ClassNotFoundException e) {
                throw new EscException("Load error");
            }
        }
        return loadCards;
    }

    /**
     * Saves the current card list to the save file.
     * @param currCards the current card list
     */
    public void saveCards(ArrayList<Card> currCards) throws EscException {
        try {
            FileOutputStream fileWrite = new FileOutputStream(saveFile);
            ObjectOutputStream objWrite = new ObjectOutputStream(fileWrite);

            objWrite.writeObject(currCards);
            objWrite.flush();
            objWrite.close();
        } catch (IOException e) {
            throw new EscException("Save error");
        }
    }
}
