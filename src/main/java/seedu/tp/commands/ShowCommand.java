package seedu.tp.commands;

import seedu.tp.exceptions.InvalidFlashcardIndexException;
import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardList;

/**
 * Command to show the details of a specified flashcard.
 */
public class ShowCommand extends Command {

    private FlashcardList flashcardList;
    private int index;

    /**
     * Constructor for the ShowCommand.
     *
     * @param flashcardList list containing all flashcards
     * @param index         index of the flashcard to show
     */
    public ShowCommand(FlashcardList flashcardList, int index) {
        assert flashcardList != null : "Invalid null FlashcardList!";

        this.flashcardList = flashcardList;
        this.index = index;
    }

    /**
     * Gets index in the show command.
     *
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    @Override
    public CommandFeedback execute() throws InvalidFlashcardIndexException {
        try {
            LOGGER.info("Showing the information of flashcard " + index + "...");
            Flashcard flashcard = flashcardList.getFlashcardAtIdx(index);
            LOGGER.info("Showed the information of the flashcard " + index);
            String feedback = "These are the flashcard details:" + System.lineSeparator() + flashcard;
            return new CommandFeedback(feedback);
        } catch (IndexOutOfBoundsException e) {
            LOGGER.warning("InvalidFlashcardIndexException occurred when executing the show command.");
            throw new InvalidFlashcardIndexException();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ShowCommand)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        ShowCommand otherShowCommand = (ShowCommand) obj;
        return otherShowCommand.getIndex() == this.index;
    }
}