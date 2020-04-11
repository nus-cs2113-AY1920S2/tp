package seedu.tp.commands;

import seedu.tp.exceptions.UnrecognizedFlashcardGroupException;
import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.group.GroupList;

import static seedu.tp.utils.Constants.BULLET_POINT;

/**
 * Command to list all flashcards in a specified group.
 */
public class ListFlashcardsInGroupCommand extends Command {

    private GroupList groupList;
    private String groupIdentifier;

    /**
     * Constructor for ListGroupCommand.
     *
     * @param groupList       list of all existing groups
     * @param groupIdentifier name or index of the group to list all flashcards for
     */
    public ListFlashcardsInGroupCommand(GroupList groupList, String groupIdentifier) {
        assert groupList != null : "Invalid null GroupList!";
        assert !groupIdentifier.isEmpty() : "Invalid empty groupIdentifier!";

        this.groupList = groupList;
        this.groupIdentifier = groupIdentifier;
    }

    @Override
    public CommandFeedback execute() {
        LOGGER.info("Executing ListFlashcardsInGroupCommand...");
        try {
            FlashcardList flashcardsInGroup = groupList.getFlashcardsInGroup(groupIdentifier);
            String feedback = getFeedback(flashcardsInGroup, groupIdentifier);
            return new CommandFeedback(feedback);
        } catch (UnrecognizedFlashcardGroupException e) {
            LOGGER.warning("UnrecognizedFlashcardGroupException occurred when executing "
                + "ListFlashcardsInGroupCommand.");
            String errorFeedback = "Please enter a valid flashcard group name or index."
                + " Use \"show-groups\" to view all groups.";
            return new CommandFeedback(errorFeedback);
        }
    }

    private String getFeedback(FlashcardList flashcardList, String groupIdentifier) {
        if (flashcardList.isEmpty()) {
            return "There are no flashcards in the group!";
        }

        StringBuilder feedback = new StringBuilder(groupIdentifier + " contains the following flashcards:");
        feedback.append(System.lineSeparator());
        for (Flashcard flashcard : flashcardList.getFlashcards()) {
            feedback.append(BULLET_POINT + flashcard.getShortDescription());
            feedback.append(System.lineSeparator());
        }
        return feedback.toString().trim();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ListFlashcardsInGroupCommand)) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        ListFlashcardsInGroupCommand otherListFlashcardsInGroupCommand = (ListFlashcardsInGroupCommand) obj;
        return this.groupList.equals(otherListFlashcardsInGroupCommand.groupList)
            && this.groupIdentifier.equals(otherListFlashcardsInGroupCommand.groupIdentifier);
    }
}
