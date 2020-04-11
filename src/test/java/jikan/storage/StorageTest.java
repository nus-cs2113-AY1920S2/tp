package jikan.storage;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {

    @Test
    public void loadFile_fileDoesNotExist() throws IOException {
        Storage storage = new Storage("data/test.txt");
        // First time calling loadFile, so file does not exist yet
        assertFalse(storage.loadFile());
        storage.dataFile.delete();
    }

    @Test
    public void loadFile_fileExists() throws IOException {
        Storage storage = new Storage("data/test.txt");

        // First time calling loadFile, so file does not exist yet
        storage.loadFile();

        // loadFile was called once already, so now file exists
        assertTrue(storage.loadFile());

        storage.dataFile.delete();
    }

    @Test
    public void testCreateDataFile() throws IOException {
        Storage storage = new Storage("data/test.txt");
        storage.loadFile();
        assertTrue(storage.dataFile.exists());
        storage.dataFile.delete();
    }

    @Test
    public void testCreateDataFile_IoException() throws IOException {
        Storage storage = new Storage("//\\-@#4/\\/**3");

        IOException thrown = assertThrows(
                IOException.class,
                storage::loadFile,
                "IOException during file creation"
        );
        storage.dataFile.delete();
    }

    @Test
    public void testWriteToFile() throws IOException {
        // Generate random suffix for file
        // (quick solution to avoid conflicts with other tests)
        int random = (int )(Math.random() * 500 + 1);
        String filepath = "data/test" + random + ".txt";
        Storage storage = new Storage(filepath);
        storage.loadFile();
        String originalString = "This is a test string.";
        String writtenString = "";
        storage.writeToFile(originalString);
        Scanner dataScanner = new Scanner(storage.dataFile);
        while (dataScanner.hasNext()) {
            writtenString = dataScanner.nextLine();
        }
        assertEquals(originalString, writtenString);
        storage.dataFile.delete();
    }
}
