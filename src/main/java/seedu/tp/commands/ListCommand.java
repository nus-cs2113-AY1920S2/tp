package seedu.tp.commands;

import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardList;

/**
 * Command to list all existing flashcards.
 */
public class ListCommand extends Command {

    public static final String LINE_SEPARATOR = System.lineSeparator();
    private FlashcardList flashcardList;

    /**
     * Constructor for list command.
     *
     * @param flashcardList flashcard list for the command to execute on
     */
    public ListCommand(FlashcardList flashcardList) {
        assert flashcardList != null : "Invalid null FlashcardList!";

        this.flashcardList = flashcardList;
    }

    /**
     * Gets the flashcard list in the list command.
     *
     * @return the flashcard list
     */
    public FlashcardList getFlashcardList() {
        return flashcardList;
    }

    @Override
    public CommandFeedback execute() {
        LOGGER.info("Listing flashcards...");
        String feedback = getFeedback(flashcardList);
        return new CommandFeedback(feedback);
    }

    private String getFeedback(FlashcardList flashcardList) {
        if (flashcardList.isEmpty()) {
            return "You have no flashcard at this moment!";
        }

        StringBuilder feedback = new StringBuilder("Here's the list of flashcards you have:" + LINE_SEPARATOR);
        for (int i = 0; i < flashcardList.getTotalFlashcardNum(); i++) {
            Flashcard flashcard = flashcardList.getFlashcardAtIdx(i);
            String nextLine = (i + 1) + ": " + flashcard.getShortDescription() + LINE_SEPARATOR;
            feedback.append(nextLine);
        }
        return feedback.toString().trim();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ListCommand)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        ListCommand otherListCommand = (ListCommand) obj;
        return otherListCommand.getFlashcardList().equals(this.flashcardList);
    }
}
