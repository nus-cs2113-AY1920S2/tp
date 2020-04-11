package seedu.tp.commands;

import seedu.tp.flashcard.FlashcardList;
import seedu.tp.ui.Ui;

public class ResetReviewedCommand extends Command {

    private Ui ui;
    private FlashcardList flashcardList;

    /**
     * Constructor for ResetReviewedCommand.
     *
     * @param ui            the UI class to be used in the command
     * @param flashcardList the FlashcardList to be used in the command
     */
    public ResetReviewedCommand(Ui ui, FlashcardList flashcardList) {
        assert ui != null : "Invalid null Ui!";
        assert flashcardList != null : " Invalid null FlashcardList!";

        this.ui = ui;
        this.flashcardList = flashcardList;
    }

    @Override
    public CommandFeedback execute() {
        LOGGER.info("Resetting all the flashcards as unreviewed...");
        flashcardList.resetAsUnreviewed(ui);
        LOGGER.info("Reset all the flashcards as unreviewed.");
        return new CommandFeedback();
    }
}
