package seedu.tp.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tp.exceptions.InvalidFlashcardIndexException;
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
import static seedu.tp.flashcard.Flashcard.PriorityLevel;

public class PriorityCommandTest {
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
    private List<Flashcard> flashcards = Arrays.asList(EVENT_FLASHCARD, PERSON_FLASHCARD, OTHER_FLASHCARD);
    private FlashcardList flashcardList = new FlashcardList(flashcards);

    @BeforeEach
    public void captureStdout() {
        System.setOut(new PrintStream(capturedOut));
    }

    @AfterEach
    public void restoreStdout() {
        System.setOut(backupStdout);
    }

    @Test
    public void priorityCommand_execute_setDefaultPrioritySuccessfully() throws InvalidFlashcardIndexException {
        StringBuilder expectedEventOutputDefault = new StringBuilder();
        expectedEventOutputDefault.append("Priority has been updated:" + System.lineSeparator());
        expectedEventOutputDefault.append("Event 1 | New priority: Not indicated");

        PriorityCommand priorityCommand = new PriorityCommand(flashcardList, 0, PriorityLevel.DEFAULT);
        CommandFeedback priorityCommandFeedback = priorityCommand.execute();
        assertEquals(expectedEventOutputDefault.toString(), priorityCommandFeedback.toString());
    }

    @Test
    public void priorityCommand_execute_setHighPrioritySuccessfully() throws InvalidFlashcardIndexException {
        StringBuilder expectedEventOutputHigh = new StringBuilder();
        expectedEventOutputHigh.append("Priority has been updated:" + System.lineSeparator());
        expectedEventOutputHigh.append("Event 1 | New priority: ***");

        PriorityCommand priorityCommand = new PriorityCommand(flashcardList, 0, PriorityLevel.HIGH);
        CommandFeedback priorityCommandFeedback = priorityCommand.execute();
        assertEquals(expectedEventOutputHigh.toString(), priorityCommandFeedback.toString());
    }

    @Test
    public void priorityCommand_execute_setMediumPrioritySuccessfully() throws InvalidFlashcardIndexException {
        StringBuilder expectedPersonOutput = new StringBuilder();
        expectedPersonOutput.append("Priority has been updated:" + System.lineSeparator());
        expectedPersonOutput.append("Person 1 | New priority: **");

        PriorityCommand priorityCommand = new PriorityCommand(flashcardList, 1, PriorityLevel.MEDIUM);
        CommandFeedback priorityCommandFeedback = priorityCommand.execute();
        assertEquals(expectedPersonOutput.toString(), priorityCommandFeedback.toString());
    }

    @Test
    public void priorityCommand_execute_setLowPrioritySuccessfully() throws InvalidFlashcardIndexException {
        StringBuilder expectedOtherOutput = new StringBuilder();
        expectedOtherOutput.append("Priority has been updated:" + System.lineSeparator());
        expectedOtherOutput.append("Title 1 | New priority: *");

        PriorityCommand priorityCommand = new PriorityCommand(flashcardList, 2, PriorityLevel.LOW);
        CommandFeedback priorityCommandFeedback = priorityCommand.execute();
        assertEquals(expectedOtherOutput.toString(), priorityCommandFeedback.toString());
    }
}
