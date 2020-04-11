package seedu.tp.commands;

import seedu.tp.exceptions.DuplicateFlashcardNameException;
import seedu.tp.exceptions.UnrecognizedFlashcardTypeException;
import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardFactory;
import seedu.tp.flashcard.FlashcardList;

import static seedu.tp.utils.Constants.EVENT_FLASHCARD_COMMAND;

/**
 * Command to create an event flashcard.
 */
public class EventFlashcardCommand extends ModifyingCommand {

    private FlashcardList flashcardList;
    private FlashcardFactory flashcardFactory;

    /**
     * Constructor for Event Flashcard Command.
     *
     * @param flashcardList    flashcard list for the command to execute on
     * @param flashcardFactory the flashcard factory to be used in the command
     */
    public EventFlashcardCommand(FlashcardList flashcardList, FlashcardFactory flashcardFactory) {
        assert flashcardList != null : "Invalid null FlashcardList!";
        assert flashcardFactory != null : "Invalid null FlashcardFactory!";

        this.flashcardList = flashcardList;
        this.flashcardFactory = flashcardFactory;
    }

    /**
     * Gets the flashcard list in the event flashcard command.
     *
     * @return the flashcard list
     */
    public FlashcardList getFlashcardList() {
        return flashcardList;
    }

    @Override
    public CommandFeedback execute() throws UnrecognizedFlashcardTypeException, DuplicateFlashcardNameException {
        LOGGER.info("Creating an event flashcard and adding it to the flashcard list...");
        Flashcard flashcard = flashcardFactory.create(EVENT_FLASHCARD_COMMAND);
        flashcardList.addFlashcard(flashcard);
        LOGGER.info("Created an event flashcard and added it to the flashcard list");
        CommandFeedback saveFeedback = save(flashcard);
        save(flashcardList);
        return saveFeedback;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EventFlashcardCommand)) {
            return false;
        }
        EventFlashcardCommand otherEventFlashcardCommand = (EventFlashcardCommand) obj;
        return otherEventFlashcardCommand.getFlashcardList().equals(this.flashcardList);
    }
}
