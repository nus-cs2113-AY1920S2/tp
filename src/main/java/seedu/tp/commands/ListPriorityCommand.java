package seedu.tp.commands;

import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardList;

import java.util.List;
import java.util.Map;

/**
 * Command to list flashcards of a specified priority level.
 */
public class ListPriorityCommand extends Command {
    private FlashcardList flashcardList;
    private Flashcard.PriorityLevel pl;

    /**
     * Constructor for the ListPriorityCommand.
     *
     * @param flashcardList list containing all flashcards
     * @param pl            priority level of flashcards to list
     */
    public ListPriorityCommand(FlashcardList flashcardList, Flashcard.PriorityLevel pl) {
        assert flashcardList != null : "Invalid null FlashcardList!";

        this.flashcardList = flashcardList;
        this.pl = pl;
    }

    @Override
    public CommandFeedback execute() {
        LOGGER.info("Executing ListPriorityCommand...");
        List<Map.Entry<Integer, Flashcard>> priorityFlashcards = flashcardList.getFlashcardsOfPriority(pl);
        String feedback = getFeedback(priorityFlashcards);
        return new CommandFeedback(feedback);
    }

    private String getFeedback(List<Map.Entry<Integer, Flashcard>> flashcardListWithId) {
        if (flashcardListWithId.isEmpty()) {
            return "There are no flashcards with this priority level! "
                + "Use \"priority INDEX PRIORITY_LEVEL\" to assign priority to a flashcard.";
        }

        StringBuilder feedback = new StringBuilder("Here's the list of flashcards with priority " + pl + ":");
        feedback.append(System.lineSeparator());
        for (int i = 0; i < flashcardListWithId.size(); i++) {
            Map.Entry<Integer, Flashcard> flashcardEntry = flashcardListWithId.get(i);
            feedback.append((i + 1) + ": " + flashcardEntry.getValue().getName()
                + " | Reviewed: " + flashcardEntry.getValue().getReviewIcon()
                + " | " + flashcardEntry.getValue().getPriorityAsString()
                + " | ID: " + (flashcardEntry.getKey() + 1));
            feedback.append(System.lineSeparator());
        }
        return feedback.toString().trim();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ListPriorityCommand)) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        ListPriorityCommand otherListPriorityCommand = (ListPriorityCommand) obj;
        return this.flashcardList.equals(otherListPriorityCommand.flashcardList)
            && this.pl == otherListPriorityCommand.pl;
    }
}
