package jikan.storage;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageHandlerTest {

    @Test
    public void removeLine() throws IOException {
        // Generate random suffix for file
        // (quick solution to avoid conflicts with tests in StorageTest
        int random = (int )(Math.random() * 500 + 1);
        String filepath = "data/test" + random + ".txt";
        Storage storage = new Storage(filepath);
        storage.loadFile();

        String line1 = "1. a";
        String line2 = "2. b";
        String line3 = "3. c";
        String writtenString = "";

        storage.writeToFile(line1);
        storage.writeToFile(line2);
        storage.writeToFile(line3);

        StorageHandler.removeLine(2, storage.dataFilePath);

        int i = 0;
        Scanner dataScanner = new Scanner(storage.dataFile);
        while (dataScanner.hasNext()) {
            writtenString = dataScanner.nextLine();
            if (i == 0) {
                assertEquals(line1, writtenString);
            } else {
                assertEquals(line3, writtenString);
            }
            i++;
        }
        storage.dataFile.delete();
    }

    @Test
    public void test_replaceLine() throws IOException {
        List<String> list = Arrays.asList("1. a", "2. b", "10. c");

        String replace = "This string has been replaced.";

        // Generate random suffix for file
        // (quick solution to avoid conflicts with tests in StorageTest)
        int random = (int )(Math.random() * 500 + 1);
        String filepath = "data/test" + random + ".txt";
        Storage storage = new Storage(filepath);
        storage.loadFile();

        for (int i = 0; i < list.size(); i++) {
            storage.writeToFile(list.get(i));
        }

        int j = 0;
        Scanner dataScanner = new Scanner(storage.dataFile);
        StorageHandler.replaceLine(2, replace, storage.dataFilePath);

        String replacedString = "";

        while (dataScanner.hasNext()) {
            replacedString = dataScanner.nextLine();
            // Check second line
            if (j == 1) {
                assertEquals(replace, replacedString);
                j++;
            }
        }
        storage.dataFile.delete();
    }
}
