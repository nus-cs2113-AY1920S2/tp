package seedu.tp.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tp.exceptions.DuplicateFlashcardNameException;
import seedu.tp.exceptions.InvalidFlashcardIndexException;
import seedu.tp.flashcard.EventFlashcard;
import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.flashcard.OtherFlashcard;
import seedu.tp.flashcard.PersonFlashcard;
import seedu.tp.ui.Ui;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DeleteCommandTest {
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
    private static Ui ui = new Ui();
    private FlashcardList emptyFlashcardList;
    private FlashcardList fullFlashcardList;

    /**
     * Set up variables before each test.
     */
    @BeforeEach
    public void setup() {
        emptyFlashcardList = new FlashcardList();
        List<Flashcard> flashcards = Arrays.asList(EVENT_FLASHCARD, PERSON_FLASHCARD, OTHER_FLASHCARD);
        fullFlashcardList = new FlashcardList(flashcards);
    }

    @Test
    public void execute_delete_successful() throws InvalidFlashcardIndexException, DuplicateFlashcardNameException {
        FlashcardList expectedFlashcardList = new FlashcardList();
        expectedFlashcardList.addFlashcard(EVENT_FLASHCARD);
        expectedFlashcardList.addFlashcard(OTHER_FLASHCARD);

        FlashcardList flashcardList = new FlashcardList(fullFlashcardList);
        DeleteCommand deleteCommand = new DeleteCommand(flashcardList, 1);
        deleteCommand.execute();

        assertEquals(expectedFlashcardList, flashcardList);
    }

    @Test
    public void execute_deleteFromEmptyList_throwsInvalidFlashcardIndexException() {
        DeleteCommand deleteCommand = new DeleteCommand(emptyFlashcardList, 1);
        assertThrows(
            InvalidFlashcardIndexException.class,
            deleteCommand::execute,
            "Expected InvalidFlashcardIndexException"
        );
    }

    @Test
    public void execute_deleteNegativeIndex_throwsInvalidFlashcardIndexException() {
        DeleteCommand deleteCommand = new DeleteCommand(fullFlashcardList, -10);
        assertThrows(
            InvalidFlashcardIndexException.class,
            deleteCommand::execute,
            "Expected InvalidFlashcardIndexException"
        );
    }

    @Test
    public void execute_deleteOutOfBoundIndex_throwsInvalidFlashcardIndexException() {
        DeleteCommand deleteCommand = new DeleteCommand(fullFlashcardList, 100);
        assertThrows(
            InvalidFlashcardIndexException.class,
            deleteCommand::execute,
            "Expected InvalidFlashcardIndexException"
        );
    }
}
