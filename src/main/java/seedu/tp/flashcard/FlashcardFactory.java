package seedu.tp.flashcard;

import seedu.tp.exceptions.UnrecognizedFlashcardTypeException;
import seedu.tp.ui.Ui;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.tp.utils.Constants.LOG_FOLDER;

/**
 * Flashcard factory class to create flashcards given string.
 */
public class FlashcardFactory {
    private Ui ui;
    private static Logger LOGGER = Logger.getLogger(FlashcardFactory.class.getName());
    private static String FILE_PATH = LOG_FOLDER + "flashcard_factory.log";
    
    public FlashcardFactory(Ui ui) {
        this.ui = ui;
    }

    /**
     * Set up the FlashcardFactory logger. Call once at the start of the program.
     *
     * @throws IOException when logger set up failed
     */
    public static void setupLogger() throws IOException {
        LOGGER.setLevel(Level.ALL);
        LOGGER.setUseParentHandlers(false);
        FileHandler fileHandler = new FileHandler(FILE_PATH, true);
        fileHandler.setFormatter(new SimpleFormatter());
        LOGGER.addHandler(fileHandler);
    }
    
    /**
     * Create a <code>Flashcard</code> given a string.
     *
     * @param flashcardType string representing type of flashcard to create
     * @return constructed <code>Flashcard</code>
     * @throws UnrecognizedFlashcardTypeException if the string is not a valid flashcard type
     */
    public Flashcard create(String flashcardType) throws UnrecognizedFlashcardTypeException {
        switch (flashcardType.toLowerCase()) {
        case "event":
            EventFlashcard eventFlashcard = EventFlashcard.createEventFlashcard(ui);
            ui.confirmFlashcardCreation(eventFlashcard);
            LOGGER.info("Created Event Flashcard");
            return eventFlashcard;
        case "person":
            PersonFlashcard personFlashcard = PersonFlashcard.createPersonFlashcard(ui);
            ui.confirmFlashcardCreation(personFlashcard);
            LOGGER.info("Created Person Flashcard");
            return personFlashcard;
        case "other":
            OtherFlashcard otherFlashcard = OtherFlashcard.createOtherFlashcard(ui);
            ui.confirmFlashcardCreation(otherFlashcard);
            LOGGER.info("Created Other Flashcard");
            return otherFlashcard;
        default:
            LOGGER.info("Received unknown flashcard type: " + flashcardType);
            throw new UnrecognizedFlashcardTypeException("Flashcard types: event, person, other");
        }
    }
}
