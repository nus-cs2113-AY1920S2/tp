package seedu.tp.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tp.flashcard.EventFlashcard;
import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.flashcard.OtherFlashcard;
import seedu.tp.flashcard.PersonFlashcard;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {

    public static final EventFlashcard EVENT_FLASHCARD = new EventFlashcard(
        "Event 1",
        LocalDate.of(1843, 7, 31),
        LocalDate.of(1892, 12, 25),
        "This is an event summary",
        Arrays.asList("Detail 1", "Detail 2")
    );
    public static final PersonFlashcard PERSON_FLASHCARD = new PersonFlashcard(
        "Person 1",
        LocalDate.of(1843, 7, 31),
        LocalDate.of(1892, 12, 25),
        "This is a person's summary",
        Arrays.asList("Detail 1", "Detail 2")
    );
    public static final OtherFlashcard OTHER_FLASHCARD = new OtherFlashcard(
        "Title 1",
        "This is a summary",
        Arrays.asList("Detail 1", "Detail 2")
    );
    private final ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
    private final PrintStream backupStdout = System.out;
    private FlashcardList emptyFlashcardList;
    private FlashcardList fullFlashcardList;

    /**
     * Set up variables before each test.
     */
    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(capturedOut));
        emptyFlashcardList = new FlashcardList();
        List<Flashcard> flashcards = Arrays.asList(EVENT_FLASHCARD, PERSON_FLASHCARD, OTHER_FLASHCARD);
        fullFlashcardList = new FlashcardList(flashcards);
    }

    @AfterEach
    public void restoreStdout() {
        System.setOut(backupStdout);
    }

    @Test
    public void listCommand_execute_listsFlashcardsSuccessfully() {
        StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append("Here's the list of flashcards you have:" + System.lineSeparator());
        expectedOutput.append("1: " + EVENT_FLASHCARD.getShortDescription() + System.lineSeparator());
        expectedOutput.append("2: " + PERSON_FLASHCARD.getShortDescription() + System.lineSeparator());
        expectedOutput.append("3: " + OTHER_FLASHCARD.getShortDescription());

        ListCommand listCommand = new ListCommand(fullFlashcardList);
        CommandFeedback listCommandFeedback = listCommand.execute();
        assertEquals(expectedOutput.toString(), listCommandFeedback.toString());
    }

    @Test
    public void listCommand_executeEmptyList_listsFlashcardsSuccessfully() {
        String expectedOutput = "You have no flashcard at this moment!";
        ListCommand listCommand = new ListCommand(emptyFlashcardList);
        CommandFeedback listCommandFeedback = listCommand.execute();
        assertEquals(expectedOutput, listCommandFeedback.toString());
    }
}
