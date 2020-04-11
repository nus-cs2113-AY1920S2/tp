package seedu.tp.commands;

import seedu.tp.exceptions.DeletionFailedException;
import seedu.tp.exceptions.HistoryFlashcardException;
import seedu.tp.exceptions.UnrecognizedFlashcardGroupException;
import seedu.tp.group.FlashcardGroup;
import seedu.tp.group.GroupList;
import seedu.tp.storage.Savable;

public class DeleteGroupCommand extends ModifyingCommand {
    private GroupList groupList;
    private String groupIdentifier;

    /**
     * Constructor of DeleteGroupCommand.
     *
     * @param groupList       groupList where the DeleteGroupCommand works on
     * @param groupIdentifier the index or name of the group to be deleted
     */
    public DeleteGroupCommand(GroupList groupList, String groupIdentifier) {
        assert groupList != null : "Invalid group list";

        this.groupList = groupList;
        this.groupIdentifier = groupIdentifier;
    }

    private CommandFeedback delete(Savable savable) {
        try {
            storage.delete(savable);
            return new CommandFeedback();
        } catch (DeletionFailedException e) {
            LOGGER.warning("Delete to disk failed for " + savable.getFileName());
            return new CommandFeedback("Deletion could not be saved to disk. Sorry");
        }
    }

    @Override
    public CommandFeedback execute() throws HistoryFlashcardException {
        try {
            LOGGER.info("Deleting a group from group list ... ");
            FlashcardGroup group = groupList.deleteFlashcardGroup(groupIdentifier);
            LOGGER.info("Deleted a group form group list.");
            CommandFeedback deleteFeedback = delete(group);
            String feedback = "The following flashcard group has been deleted:" + System.lineSeparator()
                + group;
            if (!deleteFeedback.isEmpty()) {
                feedback += deleteFeedback;
            }
            return new CommandFeedback(feedback);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            LOGGER.warning("Throwing UnrecognizedFlashcardGroupException ... ");
            throw new UnrecognizedFlashcardGroupException("Invalid group");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DeleteGroupCommand)) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        DeleteGroupCommand otherDeleteGroupCommand = (DeleteGroupCommand) obj;
        return this.groupList.equals(otherDeleteGroupCommand.groupList)
            && this.groupIdentifier.equals(otherDeleteGroupCommand.groupIdentifier);
    }
}
