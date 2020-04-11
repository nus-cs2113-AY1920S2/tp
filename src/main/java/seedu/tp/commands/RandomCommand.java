package seedu.tp.commands;

import seedu.tp.exceptions.HistoryFlashcardException;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.ui.Ui;

public class RandomCommand extends Command {

    Ui ui;
    FlashcardList flashcardList;

    /**
     * Constructor for RandomCommand.
     *
     * @param flashcardList the FlashcardList to be used in the command
     * @param ui            the UI class to be used in the command
     */
    public RandomCommand(FlashcardList flashcardList, Ui ui) {
        assert flashcardList != null : "Invalid null FlashcardList!";
        assert ui != null : "Invalid null Ui!";

        this.flashcardList = flashcardList;
        this.ui = ui;
    }

    @Override
    public CommandFeedback execute() throws HistoryFlashcardException {
        LOGGER.info("Randomizing flashcards for reviewing ...");
        flashcardList.reviewRandomFlashcards(ui);
        LOGGER.info("Randomized flashcards and finished a review");
        return new CommandFeedback();
    }
}
