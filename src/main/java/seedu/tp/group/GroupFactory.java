package seedu.tp.group;

import seedu.tp.exceptions.InvalidFlashcardIndexException;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.ui.Ui;

/**
 * Group factory class to create flashcard group given string.
 */
public class GroupFactory {
    private Ui ui;
    private FlashcardList flashcardList;

    /**
     * Constructor for the GroupFactory.
     *
     * @param ui            instance for user interaction
     * @param flashcardList list containing all flashcards
     */
    public GroupFactory(Ui ui, FlashcardList flashcardList) {
        assert ui != null : "Invalid null Ui!";
        assert flashcardList != null : "Invalid null FlashcardList!";

        this.ui = ui;
        this.flashcardList = flashcardList;
    }

    /**
     * Forms a new group and adds it to groupList.
     *
     * @return the FlashcardGroup just created
     * @throws InvalidFlashcardIndexException if the flashcards used to create the group have invalid index
     */
    public FlashcardGroup form() throws InvalidFlashcardIndexException {
        FlashcardGroup group = FlashcardGroup.createGroup(ui, flashcardList);
        ui.confirmFlashcardGroupCreation(group);
        return group;
    }
}
