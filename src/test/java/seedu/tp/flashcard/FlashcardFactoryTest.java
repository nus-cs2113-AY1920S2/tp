package seedu.tp.flashcard;

import org.junit.jupiter.api.Test;
import seedu.tp.exceptions.UnrecognizedFlashcardTypeException;
import seedu.tp.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tp.utils.Constants.EVENT_FLASHCARD_COMMAND;
import static seedu.tp.utils.Constants.OTHER_FLASHCARD_COMMAND;
import static seedu.tp.utils.Constants.PERSON_FLASHCARD_COMMAND;
import static seedu.tp.utils.ExampleInputConstants.DETAILS;
import static seedu.tp.utils.ExampleInputConstants.END_LOCAL_DATE;
import static seedu.tp.utils.ExampleInputConstants.FLASHCARD_NAME;
import static seedu.tp.utils.ExampleInputConstants.FULL_SIMULATED_INPUT;
import static seedu.tp.utils.ExampleInputConstants.NEWLINE;
import static seedu.tp.utils.ExampleInputConstants.PARTIAL_SIMULATED_INPUT;
import static seedu.tp.utils.ExampleInputConstants.START_LOCAL_DATE;
import static seedu.tp.utils.ExampleInputConstants.SUMMARY;
import static seedu.tp.utils.InputTestUtil.getFlashcardFactoryWithInput;

public class FlashcardFactoryTest {
    @Test
    public void flashcardFactory_eventFlashcard_correctlyConstructed() throws UnrecognizedFlashcardTypeException {
        Flashcard expectedFlashcard = new EventFlashcard(FLASHCARD_NAME, START_LOCAL_DATE,
            END_LOCAL_DATE, SUMMARY, DETAILS);
        FlashcardFactory flashcardFactory = getFlashcardFactoryWithInput(FULL_SIMULATED_INPUT);
        Flashcard actualFlashcard = flashcardFactory.create(EVENT_FLASHCARD_COMMAND);

        assertTrue(actualFlashcard instanceof EventFlashcard);
        assertEquals(expectedFlashcard, actualFlashcard);
    }

    @Test
    public void flashcardFactory_personFlashcard_correctlyConstructed() throws UnrecognizedFlashcardTypeException {
        Flashcard expectedFlashcard = new PersonFlashcard(FLASHCARD_NAME, START_LOCAL_DATE,
            END_LOCAL_DATE, SUMMARY, DETAILS);
        FlashcardFactory flashcardFactory = getFlashcardFactoryWithInput(FULL_SIMULATED_INPUT);
        Flashcard actualFlashcard = flashcardFactory.create(PERSON_FLASHCARD_COMMAND);

        assertTrue(actualFlashcard instanceof PersonFlashcard);
        assertEquals(expectedFlashcard, actualFlashcard);
    }

    @Test
    public void flashcardFactory_otherFlashcard_correctlyConstructed() throws UnrecognizedFlashcardTypeException {
        Flashcard expectedFlashcard = new OtherFlashcard(FLASHCARD_NAME, SUMMARY, DETAILS);
        FlashcardFactory flashcardFactory = getFlashcardFactoryWithInput(PARTIAL_SIMULATED_INPUT);
        Flashcard actualFlashcard = flashcardFactory.create(OTHER_FLASHCARD_COMMAND);

        assertTrue(actualFlashcard instanceof OtherFlashcard);
        assertEquals(expectedFlashcard, actualFlashcard);
    }

    @Test
    public void flashcardFactory_unrecognizedFlashcardType_throwsException() {
        final String[] unrecognizedFlashcardTypes = {"eventbds", "PeRson123", "OTHERASDF", "", "List", NEWLINE};
        FlashcardFactory flashcardFactory = new FlashcardFactory(new Ui());
        for (String unrecognizedFlashcardType : unrecognizedFlashcardTypes) {
            assertThrows(
                UnrecognizedFlashcardTypeException.class,
                () -> flashcardFactory.create(unrecognizedFlashcardType)
            );
        }
    }
}
