package seedu.tp.commands;

import seedu.tp.exceptions.DuplicateFlashcardNameException;
import seedu.tp.exceptions.UnrecognizedFlashcardTypeException;
import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardFactory;
import seedu.tp.flashcard.FlashcardList;

import static seedu.tp.utils.Constants.OTHER_FLASHCARD_COMMAND;

/**
 * Command to create an other flashcard.
 */
public class OtherFlashcardCommand extends ModifyingCommand {

    private FlashcardList flashcardList;
    private FlashcardFactory flashcardFactory;

    /**
     * Constructor for Other Flashcard Command.
     *
     * @param flashcardList    flashcard list for the command to execute on
     * @param flashcardFactory the flashcard factory to be used in the command
     */
    public OtherFlashcardCommand(FlashcardList flashcardList, FlashcardFactory flashcardFactory) {
        assert flashcardList != null : "Invalid null FlashcardList!";
        assert flashcardFactory != null : "Invalid null FlashcardFactory!";

        this.flashcardList = flashcardList;
        this.flashcardFactory = flashcardFactory;
    }

    /**
     * Gets the flashcard list in the other flashcard command.
     *
     * @return the flashcard list
     */
    public FlashcardList getFlashcardList() {
        return flashcardList;
    }

    @Override
    public CommandFeedback execute() throws UnrecognizedFlashcardTypeException, DuplicateFlashcardNameException {
        LOGGER.info("Creating an other flashcard and adding it to the flashcard list...");
        Flashcard flashcard = flashcardFactory.create(OTHER_FLASHCARD_COMMAND);
        flashcardList.addFlashcard(flashcard);
        LOGGER.info("Created an other flashcard and added it to the flashcard list");
        CommandFeedback saveFeedback = save(flashcard);
        save(flashcardList);
        return saveFeedback;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OtherFlashcardCommand)) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        OtherFlashcardCommand otherOtherFlashcardCommand = (OtherFlashcardCommand) obj;
        return otherOtherFlashcardCommand.getFlashcardList().equals(this.flashcardList);
    }
}
