package seedu.tp.commands;

import org.junit.jupiter.api.Test;
import seedu.tp.exceptions.DuplicateFlashcardNameException;
import seedu.tp.exceptions.UnrecognizedFlashcardTypeException;
import seedu.tp.flashcard.FlashcardFactory;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.flashcard.PersonFlashcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tp.utils.ExampleInputConstants.DETAILS;
import static seedu.tp.utils.ExampleInputConstants.END_LOCAL_DATE;
import static seedu.tp.utils.ExampleInputConstants.FLASHCARD_NAME;
import static seedu.tp.utils.ExampleInputConstants.FULL_SIMULATED_INPUT;
import static seedu.tp.utils.ExampleInputConstants.START_LOCAL_DATE;
import static seedu.tp.utils.ExampleInputConstants.SUMMARY;
import static seedu.tp.utils.InputTestUtil.getFlashcardFactoryWithInput;

public class PersonFlashcardCommandTest {
    @Test
    public void personFlashcardCommand_execute_addsFlashcardSuccessfully() throws UnrecognizedFlashcardTypeException,
        DuplicateFlashcardNameException {
        FlashcardList expectedFlashcardList = new FlashcardList();
        expectedFlashcardList.addFlashcard(new PersonFlashcard(FLASHCARD_NAME, START_LOCAL_DATE,
            END_LOCAL_DATE, SUMMARY, DETAILS));

        FlashcardList actualFlashcardList = new FlashcardList();
        FlashcardFactory flashcardFactory = getFlashcardFactoryWithInput(FULL_SIMULATED_INPUT);
        PersonFlashcardCommand personFlashcardCommand = new PersonFlashcardCommand(actualFlashcardList,
            flashcardFactory);
        personFlashcardCommand.execute();

        assertEquals(expectedFlashcardList, actualFlashcardList);
        assertTrue(actualFlashcardList.getFlashcardAtIdx(0) instanceof PersonFlashcard);
    }
}
