package seedu.tp.commands;

import org.junit.jupiter.api.Test;
import seedu.tp.exceptions.DuplicateFlashcardNameException;
import seedu.tp.exceptions.UnrecognizedFlashcardTypeException;
import seedu.tp.flashcard.FlashcardFactory;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.flashcard.OtherFlashcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tp.utils.ExampleInputConstants.DETAILS;
import static seedu.tp.utils.ExampleInputConstants.FLASHCARD_NAME;
import static seedu.tp.utils.ExampleInputConstants.PARTIAL_SIMULATED_INPUT;
import static seedu.tp.utils.ExampleInputConstants.SUMMARY;
import static seedu.tp.utils.InputTestUtil.getFlashcardFactoryWithInput;

public class OtherFlashcardCommandTest {
    @Test
    public void otherFlashcardCommand_execute_addsFlashcardSuccessfully() throws UnrecognizedFlashcardTypeException,
        DuplicateFlashcardNameException {
        FlashcardList expectedFlashcardList = new FlashcardList();
        expectedFlashcardList.addFlashcard(new OtherFlashcard(FLASHCARD_NAME, SUMMARY, DETAILS));

        FlashcardList actualFlashcardList = new FlashcardList();
        FlashcardFactory flashcardFactory = getFlashcardFactoryWithInput(PARTIAL_SIMULATED_INPUT);
        OtherFlashcardCommand otherFlashcardCommand = new OtherFlashcardCommand(actualFlashcardList, flashcardFactory);
        otherFlashcardCommand.execute();

        assertEquals(expectedFlashcardList, actualFlashcardList);
        assertTrue(actualFlashcardList.getFlashcardAtIdx(0) instanceof OtherFlashcard);
    }
}
