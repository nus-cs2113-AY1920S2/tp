package seedu.duke;

import seedu.cards.Card;
import seedu.cards.CardList;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage  {

    private static String dir = System.getProperty("user.dir");
    private static Path filepath = Paths.get(dir, "data", "cards.txt"); //todo change filepath
    private static String filepathStr  = String.valueOf(filepath);;
    private static File saveFile = new File(filepathStr);

    /**
     * Loads any pre-existing cards from the save file and creates a new card list.
     * @return pre-existing card list (if any) or a new list
     */
    public static ArrayList<Card> loadCards() {
        ArrayList<Card> loadCards = new ArrayList<>();
        if(!saveFile.exists()) {
            try {
                // creates all sub dir if not exist
                saveFile.getParentFile().mkdirs();
                saveFile.createNewFile();
            }
            catch (IOException e){
                System.out.println("File creation error");
            }
        }
        else {
            try{
                FileInputStream fileRead = new FileInputStream(saveFile);
                ObjectInputStream objRead = new ObjectInputStream(fileRead);

                loadCards = (ArrayList<Card>) objRead.readObject();
                objRead.close();
            }
            catch (IOException | ClassNotFoundException e) {
                System.out.println("\nLoad error");
            }
        }
        return loadCards;
    }

    /**
     * Saves the current card list to the save file.
     * @param currCards the current card list
     */
    public void saveCards(CardList currCards) {
        try{
            FileOutputStream fileWrite = new FileOutputStream(saveFile);
            ObjectOutputStream objWrite = new ObjectOutputStream(fileWrite);

            objWrite.writeObject(currCards.getCards());
            objWrite.flush();
            objWrite.close();
        }
        catch (IOException e) {
            System.out.println("\nSave error");
        }
    }
}
