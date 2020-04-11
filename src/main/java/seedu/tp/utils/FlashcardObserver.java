package seedu.tp.utils;

import seedu.tp.flashcard.Flashcard;

/**
 * Observer interface so containers can be notified when a flashcard is deleted.
 */
public interface FlashcardObserver {
    /**
     * Call this when a flashcard is deleted.
     *
     * @param flashcard the flashcard that was deleted.
     */
    void delete(Flashcard flashcard);
}
