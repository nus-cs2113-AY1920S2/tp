package seedu.tp.group;

import seedu.tp.exceptions.DuplicateFlashcardException;
import seedu.tp.exceptions.DuplicateFlashcardNameException;
import seedu.tp.exceptions.InvalidFlashcardIndexException;
import seedu.tp.flashcard.Flashcard;
import seedu.tp.flashcard.FlashcardList;
import seedu.tp.storage.Savable;
import seedu.tp.ui.Ui;
import seedu.tp.utils.FlashcardObserver;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.tp.utils.Constants.DESCRIPTION_FIELD;
import static seedu.tp.utils.Constants.INDEXES_FIELD;
import static seedu.tp.utils.Constants.LOG_FOLDER;
import static seedu.tp.utils.Constants.NAME_FIELD;

/**
 * A group of flashcards which have some of the same characteristics.
 */
public class FlashcardGroup implements Savable, FlashcardObserver {
    public static final String GROUPS_FOLDER = "groups";
    protected static final Logger LOGGER = Logger.getLogger(FlashcardGroup.class.getName());
    private static final String FILE_PATH = LOG_FOLDER + "flashcard_group.log";
    private String name;
    private String description;
    private FlashcardList groupCards = new FlashcardList();

    /**
     * Constructs a <code>FlashcardGroup</code> using some existing cards from the users original list.
     *
     * @param name         the name of the group.
     * @param description  brief description of the group.
     * @param originalList the original list of flashcards.
     * @param indexes      the indexes of the flashcards which are going to be added to this group.
     */
    public FlashcardGroup(String name, String description, FlashcardList originalList, int[] indexes) {
        assert originalList != null : "Invalid null FlashcardList!";
        assert !name.isEmpty() : "Invalid empty name!";
        assert !description.isEmpty() : "Invalid empty description!";

        this.name = name;
        this.description = description;
        for (int i : indexes) {
            try {
                Flashcard flashcard = originalList.getFlashcardAtIdx(i);
                groupCards.addFlashcard(flashcard);
            } catch (DuplicateFlashcardNameException e) {
                // Exception ignored because there shouldn't be any flashcard with duplicate names in the original list
            }
        }
        LOGGER.info("Constructed new Flashcard Group: " + this);
    }

    /**
     * Set up the Flashcard Group logger. Call once at the start of the program.
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
     * Creates an <code>flashcardGroup</code>  by prompting the user to enter info.
     *
     * @param ui            used to prompt the user
     * @param flashcardList the flashcardList which used to create the new group
     * @return the new flashcardGroup
     * @throws InvalidFlashcardIndexException if the indexes given by the users are not integers or out of bounds
     */
    public static FlashcardGroup createGroup(Ui ui, FlashcardList flashcardList) throws InvalidFlashcardIndexException {
        try {
            String name = ui.promptUserForRequiredField(NAME_FIELD);
            String description = ui.promptUserForRequiredField(DESCRIPTION_FIELD);
            String[] indexesGiven = ui.promptUserForRequiredField(INDEXES_FIELD).split(" ");

            assert indexesGiven != null : "Invalid null indexes";

            int[] indexes = new int[indexesGiven.length];
            for (int i = 0; i < indexes.length; i++) {
                indexes[i] = Integer.parseInt(indexesGiven[i]) - 1;
            }
            return new FlashcardGroup(name, description, flashcardList, indexes);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidFlashcardIndexException();
        }
    }

    /**
     * Adds a flashcard to an existing group.
     *
     * @param flashcard the flashcard to be added
     * @throws DuplicateFlashcardException if the flashcard is already in the group
     */
    public void addFlashcardToTheGroup(Flashcard flashcard) throws DuplicateFlashcardException,
        DuplicateFlashcardNameException {
        if (groupCards.contains(flashcard)) {
            throw new DuplicateFlashcardException();
        }
        groupCards.addFlashcard(flashcard);
        LOGGER.info("Added " + flashcard.getName() + " to " + name);
    }

    /**
     * Gets the string of a group including some basic information.
     *
     * @return String induction of the group.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Group name: ").append(name).append(System.lineSeparator());
        stringBuilder.append("Group description: ").append(description).append(System.lineSeparator());
        stringBuilder.append("There are ").append(groupCards.getTotalFlashcardNum())
            .append(" flashcards in this group.");
        return stringBuilder.toString();
    }

    public String getName() {
        return name;
    }

    /**
     * Get the file name of the flashcard group.
     *
     * @return the file name of the flashcard group.
     */
    public String getFileName() {
        return GROUPS_FOLDER + "/" + name;
    }

    public String getDescription() {
        return description;
    }

    public FlashcardList getGroupCards() {
        return groupCards;
    }

    public void delete(Flashcard flashcard) {
        groupCards.getFlashcards().remove(flashcard);

    }

    /**
     * Check if the current instance is equal to the object passed in.
     *
     * @param obj The object to be compared against the current instance
     * @return whether or not the two objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FlashcardGroup)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        FlashcardGroup otherFlashcardGroup = (FlashcardGroup) obj;
        return name.equals(otherFlashcardGroup.getName()) && description.equals(otherFlashcardGroup.getDescription())
            && groupCards.equals(otherFlashcardGroup.getGroupCards());
    }
}
