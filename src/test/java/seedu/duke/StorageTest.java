package seedu.duke;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.cards.Card;
import seedu.cards.CardList;
import seedu.exception.EscException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/* TODO
    --> List of things to test for
    1. test for if creating file works
    2. test if loading from file works
    3. test if saving to file works
 */

class StorageTest {
    private Storage storage;
    private CardList cardList;

    private ByteArrayOutputStream byteOut;

    @TempDir
    File tempDir;

    @BeforeEach
    void setUp() throws IOException {
        cardList = new CardList();
        Card newCard = new Card("test question", "test answer");
        cardList.addCard(newCard);

        byteOut = new ByteArrayOutputStream();
        ObjectOutputStream objWriteToBytes = new ObjectOutputStream(byteOut);
        objWriteToBytes.writeObject(cardList.getCards());
        objWriteToBytes.flush();
        objWriteToBytes.close();
    }

    @Test
    void testSaveCards(@TempDir Path tempDir) throws IOException {
        Path tempPath = tempDir.resolve("temp.txt");

        //write to temp file
        Files.write(tempPath, byteOut.toByteArray());
        assertTrue(Files.exists(tempPath), "file exists");

        //Check bytes written are the same
        assertEquals(byteOut.toByteArray(), Files.readAllBytes(tempPath));
    }

    @Test
    public void testLoadCards(@TempDir Path tempDir) throws EscException, IOException {
        Path tempPath = tempDir.resolve("temp.txt");
        Files.write(tempPath, byteOut.toByteArray());
        assertTrue(Files.exists(tempPath), "file exists");

        ArrayList<Card> loadCards = new ArrayList<>();
        ArrayList tempList;
        try {
            FileInputStream fileRead = new FileInputStream(String.valueOf(Files.readAllBytes(tempPath)));
            ObjectInputStream objRead = new ObjectInputStream(fileRead);
            tempList = (ArrayList) objRead.readObject();

            for (int i = 0; i < tempList.size(); i++) {
                loadCards.add((Card) tempList.get(i));
            }

            objRead.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new EscException("Load error");
        }
        assertEquals(loadCards, cardList);
    }

    @Test
    public void sampleTest() {
        assertTrue(true);
    }
}