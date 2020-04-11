package seedu.tp.commands;

import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardList;

import java.util.List;
import java.util.Map;

public class ListReviewedCommand extends Command {

    public static final String LINE_SEPARATOR = System.lineSeparator();
    private FlashcardList flashcardList;

    /**
     * Constructor for ListReviewedCommand.
     *
     * @param flashcardList the flashcard list for the command to execute on
     */
    public ListReviewedCommand(FlashcardList flashcardList) {
        assert flashcardList != null : "Invalid null FlashcardList!";

        this.flashcardList = flashcardList;
    }

    @Override
    public CommandFeedback execute() {
        LOGGER.info("Executing ListReviewedCommand...");
        List<Map.Entry<Integer, Flashcard>> reviewedFlashcards = flashcardList.getAllReviewedFlashcards();
        String feedback = getFeedback(reviewedFlashcards);
        LOGGER.info("ListReviewedCommand executed!");
        return new CommandFeedback(feedback);
    }

    private String getFeedback(List<Map.Entry<Integer, Flashcard>> flashcardListWithId) {
        if (flashcardListWithId.isEmpty()) {
            return "You have no reviewed flashcards! " + "Use \"reviewed [INDEX]\" to mark a flashcard as reviewed.";
        }

        StringBuilder feedback = new StringBuilder("Here's the list of reviewed flashcards:");
        feedback.append(LINE_SEPARATOR);
        for (int i = 0; i < flashcardListWithId.size(); i++) {
            Map.Entry<Integer, Flashcard> flashcardEntry = flashcardListWithId.get(i);
            feedback.append(i + 1).append(": ")
                .append(flashcardEntry.getValue().getName())
                .append(" | Reviewed: ").append(flashcardEntry.getValue().getReviewIcon())
                .append(" | ").append(flashcardEntry.getValue().getPriorityAsString())
                .append(" | ID: ").append(flashcardEntry.getKey() + 1);
            feedback.append(LINE_SEPARATOR);
        }
        return feedback.toString().trim();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ListReviewedCommand)) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        ListReviewedCommand otherListReviewedCommand = (ListReviewedCommand) obj;
        return this.flashcardList.equals(otherListReviewedCommand.flashcardList);
    }
}
