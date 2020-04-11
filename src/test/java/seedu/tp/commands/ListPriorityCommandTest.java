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

public class ListPriorityCommandTest {

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
        EVENT_FLASHCARD.setPriorityLevel(Flashcard.PriorityLevel.HIGH);
        PERSON_FLASHCARD.setPriorityLevel(Flashcard.PriorityLevel.HIGH);
        OTHER_FLASHCARD.setPriorityLevel(Flashcard.PriorityLevel.LOW);
        List<Flashcard> flashcards = Arrays.asList(EVENT_FLASHCARD, PERSON_FLASHCARD, OTHER_FLASHCARD);
        fullFlashcardList = new FlashcardList(flashcards);
    }

    /**
     * Restores standard output and variables.
     */
    @AfterEach
    public void restoreStdoutAndVariables() {
        System.setOut(backupStdout);
        EVENT_FLASHCARD.setPriorityLevel(Flashcard.PriorityLevel.DEFAULT);
        PERSON_FLASHCARD.setPriorityLevel(Flashcard.PriorityLevel.DEFAULT);
        OTHER_FLASHCARD.setPriorityLevel(Flashcard.PriorityLevel.DEFAULT);
    }

    @Test
    public void listPriorityCommand_execute_listsFlashcardsSuccessfully() {
        StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append("Here's the list of flashcards with priority HIGH:" + System.lineSeparator());
        expectedOutput.append("1: Event 1 | Reviewed: X | *** | ID: 1" + System.lineSeparator());
        expectedOutput.append("2: Person 1 | Reviewed: X | *** | ID: 2");

        ListPriorityCommand listPriorityCommand =
            new ListPriorityCommand(fullFlashcardList, Flashcard.PriorityLevel.HIGH);
        CommandFeedback listPriorityCommandFeedback = listPriorityCommand.execute();
        assertEquals(expectedOutput.toString(), listPriorityCommandFeedback.toString());
    }

    @Test
    public void listPriorityCommand_executeEmptyList_listsFlashcardsSuccessfully() {
        String expectedOutput = "There are no flashcards with this priority level! "
            + "Use \"priority INDEX PRIORITY_LEVEL\" to assign priority to a flashcard.";

        ListPriorityCommand listPriorityCommand =
            new ListPriorityCommand(emptyFlashcardList, Flashcard.PriorityLevel.MEDIUM);
        CommandFeedback listPriorityCommandFeedback = listPriorityCommand.execute();
        assertEquals(expectedOutput, listPriorityCommandFeedback.toString());
    }
}
