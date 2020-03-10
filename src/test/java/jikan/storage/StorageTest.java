package jikan.storage;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testCreateDataFile() throws IOException{
        Storage storage = new Storage("data/test.txt");
        storage.loadFile();
        assertTrue(storage.dataFile.exists());
        storage.dataFile.delete();
    }

    @Test
    public void testCreateDataFile_IOException() {
        Storage storage = new Storage("//\\-@#4/\\/**3");

        IOException thrown = assertThrows(
                IOException.class,
                storage::loadFile,
                "IOException during file creation"
        );
    }

    @Test
    public void testWriteToFile() throws IOException {
        Storage storage = new Storage("data/test.txt");
        storage.loadFile();
        String originalString = "This is a test string.";
        String writtenString = "";
        storage.writeToFile(originalString);
        Scanner dataScanner = new Scanner(storage.dataFile);
        while (dataScanner.hasNext()) {
            writtenString = dataScanner.nextLine();
        }
        assertEquals(originalString, writtenString);
    }
}
