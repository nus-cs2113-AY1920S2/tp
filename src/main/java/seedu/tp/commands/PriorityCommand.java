package seedu.tp.commands;

import seedu.tp.exceptions.InvalidFlashcardIndexException;
import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardList;

/**
 * Command to configure priority level of a flashcard.
 */
public class PriorityCommand extends ModifyingCommand {

    private FlashcardList flashcardList;
    private int index;
    private Flashcard.PriorityLevel priorityLevel;

    /**
     * Constructor for the PriorityCommand.
     *
     * @param flashcardList list containing all flashcards
     * @param index         index of the flashcard to show
     * @param priorityLevel priority level to set the flashcard to
     */
    public PriorityCommand(FlashcardList flashcardList, int index, Flashcard.PriorityLevel priorityLevel) {
        assert flashcardList != null : "Invalid null FlashcardList!";

        this.flashcardList = flashcardList;
        this.index = index;
        this.priorityLevel = priorityLevel;
    }

    /**
     * Gets index in the priority command.
     *
     * @return the index
     */
    public int getIndex() {
        return index;
    }


    /**
     * Gets the priority level in the priority command.
     *
     * @return the priority level
     */
    public Flashcard.PriorityLevel getPriorityLevel() {
        return priorityLevel;
    }

    @Override
    public CommandFeedback execute() throws InvalidFlashcardIndexException {
        try {
            LOGGER.info("Setting the priority for the flashcard " + index + "...");
            Flashcard flashcard = flashcardList.getFlashcardAtIdx(index);
            flashcard.setPriorityLevel(priorityLevel);
            LOGGER.info("Set the priority for the flashcard " + index);
            CommandFeedback saveFeedback = save(flashcard);
            String feedback = "Priority has been updated:" + System.lineSeparator()
                + flashcard.getName() + " | New priority: " + flashcard.getPriorityAsString();
            if (!saveFeedback.isEmpty()) {
                feedback += saveFeedback;
            }
            return new CommandFeedback(feedback);
        } catch (IndexOutOfBoundsException e) {
            LOGGER.warning("IndexOutOfBoundsException occurred when executing the priority command.");
            throw new InvalidFlashcardIndexException();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PriorityCommand)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        PriorityCommand otherPriorityCommand = (PriorityCommand) obj;
        return otherPriorityCommand.getIndex() == this.index
            && otherPriorityCommand.getPriorityLevel().equals(this.priorityLevel);
    }
}
