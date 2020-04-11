package seedu.tp.commands;

import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardList;

import java.util.List;
import java.util.Map;

public class FindCommand extends Command {

    private static final String LINE_SEPARATOR = System.lineSeparator();
    private FlashcardList flashcardList;
    private String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param flashcardList the flashcard list to be used by the command
     * @param keyword       the specified keyword
     */
    public FindCommand(FlashcardList flashcardList, String keyword) {
        assert flashcardList != null : "Invalid null FlashcardList!";
        assert keyword != null : "Invalid null keyword!";

        this.flashcardList = flashcardList;
        this.keyword = keyword;
    }

    @Override
    public CommandFeedback execute() {
        LOGGER.info("Executing FindCommand...");
        List<Map.Entry<Integer, Flashcard>> flashcardsWithKeyword = flashcardList.getAllFlashcardsWithKeyword(keyword);
        String feedback = getFeedback(flashcardsWithKeyword);
        LOGGER.info("FindCommand executed!");
        return new CommandFeedback(feedback);
    }

    private String getFeedback(List<Map.Entry<Integer, Flashcard>> flashcardListWithId) {
        if (flashcardListWithId.isEmpty()) {
            return "You have no flashcard matching your query!";
        }

        StringBuilder feedback = new StringBuilder("Here's the list of flashcards you are looking for:");
        feedback.append(LINE_SEPARATOR);
        for (int index = 0; index < flashcardListWithId.size(); index++) {
            Map.Entry<Integer, Flashcard> flashcardEntry = flashcardListWithId.get(index);
            feedback.append(index + 1).append(": ")
                .append(flashcardEntry.getValue().getShortDescription()).append(" | ID: ")
                .append(flashcardEntry.getKey() + 1)
                .append(LINE_SEPARATOR);
        }
        return feedback.toString().trim();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FindCommand)) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        FindCommand otherFindCommand = (FindCommand) obj;
        return this.flashcardList.equals(otherFindCommand.flashcardList)
            & this.keyword.equals(otherFindCommand.keyword);
    }
}
