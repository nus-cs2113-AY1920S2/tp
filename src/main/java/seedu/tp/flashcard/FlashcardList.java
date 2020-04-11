package seedu.tp.flashcard;

import seedu.tp.commands.CommandFeedback;
import seedu.tp.commands.ReviewedCommand;
import seedu.tp.exceptions.DuplicateFlashcardNameException;
import seedu.tp.exceptions.InvalidFlashcardIndexException;
import seedu.tp.exceptions.InvalidInputFormatException;
import seedu.tp.storage.Savable;
import seedu.tp.ui.Ui;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.tp.utils.Constants.ERROR_CODE;
import static seedu.tp.utils.Constants.LOG_FOLDER;
import static seedu.tp.utils.Constants.MS_TO_S_CONVERSION_FACTOR;
import static seedu.tp.utils.Constants.NORMAL_CODE;
import static seedu.tp.utils.Constants.REGEX_MATCH_ALL_CHARACTER;

/**
 * List of flashcards.
 */
public class FlashcardList implements Savable {

    public static final String FLASHCARD_LIST_FOLDER = "flashcardlist";
    public static final String FLASHCARD_LIST_FILE_NAME = "flashcardlist";
    private static final String FILE_PATH = LOG_FOLDER + "flashcard_list.log";
    private static final Logger LOGGER = Logger.getLogger(FlashcardList.class.getName());

    private List<Flashcard> flashcards;
    private int totalReviewedNumber;

    /**
     * Constructor for FlashcardList.
     */
    public FlashcardList() {
        this.flashcards = new ArrayList<>();
        this.totalReviewedNumber = 0;
    }

    /**
     * Constructor for FlashcardList.
     *
     * @param flashcardList the list of flashcards to be added
     */
    public FlashcardList(List<Flashcard> flashcardList) {
        this();

        assert flashcardList != null : "Invalid null flashcard list!";

        this.flashcards.addAll(flashcardList);
    }

    /**
     * Copy constructor for FlashcardList.
     * Note that this is a shallow copy.
     *
     * @param flashcardList the flashcard list to be copied from
     */
    public FlashcardList(FlashcardList flashcardList) {
        this();

        assert flashcardList != null : "Invalid null FlashcardList!";

        for (int i = 0; i < flashcardList.getTotalFlashcardNum(); i++) {
            this.flashcards.add(flashcardList.getFlashcardAtIdx(i));
        }
    }

    /**
     * Set up the FlashcardList logger. Call once at the start of the program.
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
     * Adds a flashcard to the list.
     *
     * @param flashcard the task to be added to the list
     * @return the updated flashcardList with new flashcard just be added in
     */
    public FlashcardList addFlashcard(Flashcard flashcard) throws DuplicateFlashcardNameException {
        assert flashcard != null : "Invalid null flashcard!";

        if (flashcards.stream().anyMatch(o -> o.getName().equals(flashcard.getName()))) {
            throw new DuplicateFlashcardNameException();
        }
        flashcards.add(flashcard);
        LOGGER.info("Added flashcard " + flashcard.getName() + " to list.");
        return this;
    }

    /**
     * Deletes a flashcard from the list.
     *
     * @param index the index of the flashcard to be deleted
     */
    public void deleteFlashcard(int index) throws InvalidFlashcardIndexException {
        try {
            Flashcard flashcard = flashcards.remove(index);
            LOGGER.info("Deleted flashcard " + flashcard.getName() + " from list.");
        } catch (IndexOutOfBoundsException e) {
            LOGGER.warning("IndexOutOfBoundsException occurred when deleting flashcard at index " + index);
            LOGGER.warning("Throwing InvalidFlashcardIndexException...");
            throw new InvalidFlashcardIndexException();
        }
    }

    /**
     * Resets all the flashcards as unreviewed.
     *
     * @param ui the ui used to communicate with the user
     */
    public void resetAsUnreviewed(Ui ui) {
        assert flashcards != null : "Invalid flashcardList";
        assert ui != null : "Invalid ui";

        for (Flashcard flashcard : flashcards) {
            flashcard.setReviewStatus(false);
        }
        this.totalReviewedNumber = 0;
        ui.confirmResetCompletion();
    }

    /**
     * Randomize the flashcard list to help user for reviewing.
     *
     * @return the random flashcard list
     */
    public FlashcardList reviewRandomFlashcards(Ui ui) throws InvalidFlashcardIndexException,
        InvalidInputFormatException {
        assert flashcards != null : "Invalid null flashcard!";

        FlashcardList randomFlashcards = new FlashcardList(flashcards);
        Collections.shuffle(randomFlashcards.getFlashcards(),
            new Random(System.currentTimeMillis() / MS_TO_S_CONVERSION_FACTOR));
        LOGGER.info("The flashcards have been randomized.");

        int reviewedNumber = 0;
        for (Flashcard flashcard : randomFlashcards.getFlashcards()) {
            System.out.println(flashcard);
            if (flashcard.isReviewed()) {
                System.out.println("You have already reviewed this flashcard.");
                System.out.println("");
            } else {
                reviewedNumber = handleResponse(ui, flashcard, reviewedNumber);
            }
        }
        int totalUnreviewedNumber = flashcards.size() - totalReviewedNumber;
        ui.confirmRandomFlashcardsReviewCompletion(reviewedNumber, totalUnreviewedNumber);
        return randomFlashcards;
    }

    private int handleResponse(Ui ui, Flashcard flashcard, int reviewedNumber)
        throws InvalidFlashcardIndexException, InvalidInputFormatException {
        int statusCode = NORMAL_CODE;
        do {
            try {
                String response = ui.promptUserResponseForReviewing(flashcard).toLowerCase();
                switch (response) {
                case "y":
                case "yes":
                    ReviewedCommand reviewedCommand = new ReviewedCommand(this,
                        flashcards.indexOf(flashcard));
                    CommandFeedback reviewedCommandFeedback = reviewedCommand.execute();
                    ui.showCommandFeedback(reviewedCommandFeedback);
                    reviewedNumber++;
                    break;
                case "n":
                case "no":
                    break;
                default:
                    throw new InvalidInputFormatException();
                }
                return reviewedNumber;
            } catch (InvalidInputFormatException e) {
                System.out.println("Please enter either yes or no.");
                statusCode = ERROR_CODE;
            }
        } while (statusCode == ERROR_CODE);
        return ERROR_CODE;
    }

    /**
     * Return whether or not this FlashcardList contains specified flashcard.
     *
     * @param flashcard the flashcard to check
     * @return whether or not this FlashcardList contains specified flashcard
     */
    public boolean contains(Flashcard flashcard) {
        assert flashcard != null : "Invalid null flashcard!";
        return flashcards.contains(flashcard);
    }

    /**
     * Gets the flashcard at a specific index.
     *
     * @param idx the index.
     * @return the flashcard at the specified index
     */
    public Flashcard getFlashcardAtIdx(int idx) throws IndexOutOfBoundsException {
        return flashcards.get(idx);
    }

    /**
     * Checks if the flashcard list is empty.
     *
     * @return boolean value indicating whether or not the flashcard list is empty
     */
    public boolean isEmpty() {
        return flashcards.isEmpty();
    }

    /**
     * Gets the total number of flashcards in the list.
     *
     * @return total number of flashcards in the list
     */
    public int getTotalFlashcardNum() {
        return flashcards.size();
    }

    /**
     * Gets the number of reviewed flashcards.
     *
     * @return the total number of reviewed flashcards at this moment
     */
    public int getTotalReviewedNumber() {
        return totalReviewedNumber;
    }

    /**
     * Updates the number of reviewed flashcards.
     *
     * @param totalReviewedNumber the updated number of reviewed flashcards
     */
    public void setTotalReviewedNumber(int totalReviewedNumber) {
        this.totalReviewedNumber = totalReviewedNumber;
    }

    /**
     * Gets the list of flashcards.
     *
     * @return the list of flashcards
     */
    public List<Flashcard> getFlashcards() {
        return flashcards;
    }

    /**
     * Gets all reviewed flashcards with IDs.
     *
     * @return the list of reviewed flashcards with IDs
     */
    public List<Map.Entry<Integer, Flashcard>> getAllReviewedFlashcards() {
        LOGGER.info("Getting all reviewed flashcards...");
        List<Map.Entry<Integer, Flashcard>> reviewedFlashcards = new ArrayList<>();
        for (int i = 0; i < flashcards.size(); i++) {
            Flashcard flashcard = flashcards.get(i);
            if (flashcard.isReviewed()) {
                reviewedFlashcards.add(new AbstractMap.SimpleEntry<>(i, flashcard));
            }
        }
        LOGGER.info("Got all reviewed flashcards!");
        return reviewedFlashcards;
    }

    /**
     * Gets all flashcards with a specified priority level.
     *
     * @param pl desired priority level of flashcards
     * @return the list of flashcards with the specified priority level
     */
    public List<Map.Entry<Integer, Flashcard>> getFlashcardsOfPriority(Flashcard.PriorityLevel pl) {
        LOGGER.info("Getting all flashcards of specified priority...");
        List<Map.Entry<Integer, Flashcard>> priorityFlashcards = new ArrayList<>();
        for (int i = 0; i < flashcards.size(); i++) {
            Flashcard flashcard = flashcards.get(i);
            if (flashcard.getPriorityLevel() == pl) {
                priorityFlashcards.add(new AbstractMap.SimpleEntry<>(i, flashcard));
            }
        }
        LOGGER.info("Got all flashcards with specified priority!");
        return priorityFlashcards;
    }

    /**
     * Gets all flashcards which contain a certain keyword with IDs.
     *
     * @param keyword the specified keyword
     * @return the list of flashcards containing the specified keyword with IDs
     */
    public List<Map.Entry<Integer, Flashcard>> getAllFlashcardsWithKeyword(String keyword) {
        LOGGER.info("Getting all flashcards with keyword " + keyword + "...");
        List<Map.Entry<Integer, Flashcard>> flashcardsWithKeyword = new ArrayList<>();
        for (int i = 0; i < flashcards.size(); i++) {
            Flashcard flashcard = flashcards.get(i);
            if (flashcard.getName().matches(REGEX_MATCH_ALL_CHARACTER + keyword + REGEX_MATCH_ALL_CHARACTER)) {
                flashcardsWithKeyword.add(new AbstractMap.SimpleEntry<>(i, flashcard));
            }
        }
        LOGGER.info("Got all flashcards with keyword " + keyword + "!");
        return flashcardsWithKeyword;
    }

    /**
     * Check if the current instance is equal to the object passed in.
     *
     * @param obj The object to be compared against the current instance
     * @return whether or not the two objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FlashcardList)) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        FlashcardList otherFlashcards = (FlashcardList) obj;
        if (this.getTotalFlashcardNum() != otherFlashcards.getTotalFlashcardNum()) {
            return false;
        }

        List<Flashcard> flashcardList = new ArrayList<Flashcard>(this.getFlashcards());
        Collections.sort(flashcardList);
        List<Flashcard> otherFlashcardList = new ArrayList<Flashcard>(otherFlashcards.getFlashcards());
        Collections.sort(otherFlashcardList);
        for (int idx = 0; idx < this.getTotalFlashcardNum(); idx++) {
            if (!flashcardList.get(idx).equals(otherFlashcardList.get(idx))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get the file name of the study plan lit.
     *
     * @return the file name of the study plan list.
     */
    @Override
    public String getFileName() {
        return FLASHCARD_LIST_FOLDER + "/" + FLASHCARD_LIST_FILE_NAME;
    }

    /**
     * Gets all flashcard names.
     *
     * @return the list of flashcard names
     */
    public List<String> getAllFlashcardNames() {
        List<String> flashcardNames = new ArrayList<>();
        for (Flashcard flashcard : flashcards) {
            flashcardNames.add(flashcard.getName());
        }
        return flashcardNames;
    }
}
