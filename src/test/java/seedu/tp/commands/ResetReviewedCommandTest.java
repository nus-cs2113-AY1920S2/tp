package seedu.tp.commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.tp.exceptions.HistoryFlashcardException;
import seedu.tp.flashcard.EventFlashcard;
import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.flashcard.OtherFlashcard;
import seedu.tp.flashcard.PersonFlashcard;
import seedu.tp.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.tp.utils.ExampleInputConstants.DETAILS;
import static seedu.tp.utils.ExampleInputConstants.END_LOCAL_DATE;
import static seedu.tp.utils.ExampleInputConstants.START_LOCAL_DATE;

public class ResetReviewedCommandTest {

    private static final EventFlashcard EVENT_FLASHCARD = new EventFlashcard(
        "Event 1",
        START_LOCAL_DATE,
        END_LOCAL_DATE,
        "This is an event summary",
        DETAILS
    );
    private static final PersonFlashcard PERSON_FLASHCARD = new PersonFlashcard(
        "Person 1",
        START_LOCAL_DATE,
        END_LOCAL_DATE,
        "This is a person's summary",
        DETAILS
    );
    private static final OtherFlashcard OTHER_FLASHCARD = new OtherFlashcard(
        "Title 1",
        "This is a summary",
        DETAILS
    );
    private final ByteArrayOutputStream capturedOut = new ByteArrayOutputStream();
    private final PrintStream backupStdout = System.out;
    private FlashcardList fullFlashcardList;
    private Ui ui = new Ui();

    /**
     * Set up variables before each test.
     */
    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(capturedOut));
        List<Flashcard> flashcards = Arrays.asList(EVENT_FLASHCARD, PERSON_FLASHCARD, OTHER_FLASHCARD);
        fullFlashcardList = new FlashcardList(flashcards);
        fullFlashcardList.getFlashcardAtIdx(1).setReviewStatus(true);
        fullFlashcardList.getFlashcardAtIdx(2).setReviewStatus(true);
    }

    @AfterEach
    public void restoreStdout() {
        System.setOut(backupStdout);
    }

    @Test
    public void resetReviewedCommand_execute_Successfully() throws HistoryFlashcardException {
        StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append("All the flashcards have been reset as unreviewed successfully.");
        expectedOutput.append(System.lineSeparator());

        ResetReviewedCommand resetReviewedCommand = new ResetReviewedCommand(ui, fullFlashcardList);
        resetReviewedCommand.execute();
        for (Flashcard flashcard : fullFlashcardList.getFlashcards()) {
            assertFalse(flashcard.isReviewed());
        }

        assertEquals(expectedOutput.toString(), capturedOut.toString());
    }
}
