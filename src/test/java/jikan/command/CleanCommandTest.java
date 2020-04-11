package jikan.command;

import jikan.cleaner.LogCleaner;
import jikan.cleaner.StorageCleaner;
import jikan.exception.InvalidCleanCommandException;
import jikan.exception.NegativeNumberException;
import jikan.storage.Storage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CleanCommandTest {

    Storage storage = new Storage("data/test.csv");
    StorageCleaner storageCleaner = new StorageCleaner(storage);
    LogCleaner logCleaner = new LogCleaner();

    @Test
    void checkIfParameterEmpty() {
        CleanCommand cleanCommand = new CleanCommand("", storageCleaner, logCleaner);
        boolean isEmpty = cleanCommand.isParameterEmpty();
        assertEquals(isEmpty,true);
        cleanCommand = new CleanCommand("  ", storageCleaner, logCleaner);
        isEmpty = cleanCommand.isParameterEmpty();
        assertEquals(isEmpty, true);
        cleanCommand = new CleanCommand("   abc", storageCleaner, logCleaner);
        isEmpty = cleanCommand.isParameterEmpty();
        assertEquals(isEmpty, false);
        cleanCommand = new CleanCommand("abc  ", storageCleaner, logCleaner);
        isEmpty = cleanCommand.isParameterEmpty();
        assertEquals(isEmpty, false);
        cleanCommand = new CleanCommand("  abc  ", storageCleaner, logCleaner);
        isEmpty = cleanCommand.isParameterEmpty();
        assertEquals(isEmpty, false);
    }

    @Test
    void checkGetFirstWord() {
        CleanCommand cleanCommand = new CleanCommand("", storageCleaner, logCleaner);
        String firstWord = cleanCommand.getFirstWord("");
        assertEquals(firstWord,"");
        firstWord = cleanCommand.getFirstWord("abc");
        assertEquals(firstWord, "abc");
        firstWord = cleanCommand.getFirstWord("abc  def geh");
        assertEquals(firstWord, "abc");
        firstWord = cleanCommand.getFirstWord("ab c");
        assertEquals(firstWord, "ab");
    }

    @Test
    void checkGetRemainingParameter() {
        CleanCommand cleanCommand = new CleanCommand("log on", storageCleaner, logCleaner);
        String remainingParameter = cleanCommand.getRemainingParameter("log");
        assertEquals(remainingParameter, "on");
        cleanCommand = new CleanCommand("log  on", storageCleaner, logCleaner);
        remainingParameter = cleanCommand.getRemainingParameter("log");
        assertEquals(remainingParameter, "on");
        cleanCommand = new CleanCommand("log  on /n", storageCleaner, logCleaner);
        remainingParameter = cleanCommand.getRemainingParameter("log");
        assertEquals(remainingParameter, "on /n");
        cleanCommand = new CleanCommand("log  on 123    ", storageCleaner, logCleaner);
        remainingParameter = cleanCommand.getRemainingParameter("log");
        assertEquals(remainingParameter, "on 123");
        cleanCommand = new CleanCommand("log", storageCleaner, logCleaner);
        remainingParameter = cleanCommand.getRemainingParameter("log");
        assertEquals(remainingParameter, "");
    }

    @Test
    void checkGetNumber() {
        CleanCommand cleanCommand = new CleanCommand("", storageCleaner, logCleaner);
        Assertions.assertThrows(InvalidCleanCommandException.class, () -> {
            cleanCommand.getNumber("123 ");
        });
        Assertions.assertThrows(NegativeNumberException.class, () -> {
            cleanCommand.getNumber("-123");
        });
        try {
            int number = cleanCommand.getNumber("123");
            assertEquals(number, 123);
        } catch (InvalidCleanCommandException | NegativeNumberException e) {
            // Should not reach here
        }
        try {
            int number = cleanCommand.getNumber("0");
            assertEquals(number, 0);
        } catch (InvalidCleanCommandException | NegativeNumberException e) {
            // Should not reach here
        }
    }
}
