package seedu.tp.commands;

import seedu.tp.exceptions.HistoryFlashcardException;
import seedu.tp.exceptions.InvalidFlashcardIndexException;
import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.group.FlashcardGroup;
import seedu.tp.group.GroupList;
import seedu.tp.ui.Ui;

import static seedu.tp.utils.Constants.GROUP_ID_FIELD;
import static seedu.tp.utils.Constants.INDEX_FIELD;

public class AddFlashcardToGroupCommand extends ModifyingCommand {
    private Ui ui;
    private GroupList groupList;
    private FlashcardList flashcardList;

    /**
     * Constructs an addFlashcardToGroupCommand.
     *
     * @param ui            the ui need to pass to the execution method
     * @param groupList     the groupList where the flashcard wants to be added in
     * @param flashcardList the flashcardList where the flashcard is in
     */
    public AddFlashcardToGroupCommand(Ui ui, GroupList groupList, FlashcardList flashcardList) {
        assert flashcardList != null : "Invalid null FlashcardList!";
        assert groupList != null : "Invalid null GroupList!";

        this.ui = ui;
        this.groupList = groupList;
        this.flashcardList = flashcardList;
    }

    @Override
    public CommandFeedback execute() throws HistoryFlashcardException {
        try {
            int flashcardIndex = Integer.parseInt(ui.promptUserForRequiredField(INDEX_FIELD)) - 1;
            String groupID = ui.promptUserForRequiredField(GROUP_ID_FIELD);

            FlashcardGroup group = groupList.getGroupByID(groupID);
            Flashcard flashcard = flashcardList.getFlashcardAtIdx(flashcardIndex);

            LOGGER.info("Adding a flashcard to an existing group...");
            flashcard.attach(group);
            group.addFlashcardToTheGroup(flashcard);
            LOGGER.info("Added the flashcard to the group");
            final CommandFeedback saveFeedback = save(group);
            StringBuilder feedback = new StringBuilder("You've successfully added the flashcard below:");
            feedback.append(System.lineSeparator());
            feedback.append(flashcard.toString() + System.lineSeparator());
            feedback.append("To the group:" + System.lineSeparator());
            feedback.append(group.toString());
            if (!saveFeedback.isEmpty()) {
                feedback.append(saveFeedback);
            }
            return new CommandFeedback(feedback.toString());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidFlashcardIndexException();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AddFlashcardToGroupCommand)) {
            return false;
        }

        AddFlashcardToGroupCommand otherAddFlashcardToGroupCommand = (AddFlashcardToGroupCommand) obj;
        return this.getFlashcardList().equals(otherAddFlashcardToGroupCommand.getFlashcardList())
            && this.getGroupList().equals(otherAddFlashcardToGroupCommand.getGroupList());
    }

    public GroupList getGroupList() {
        return groupList;
    }

    public FlashcardList getFlashcardList() {
        return flashcardList;
    }
}