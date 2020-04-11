package seedu.tp.group;

import seedu.tp.exceptions.UnrecognizedFlashcardGroupException;
import seedu.tp.flashcard.FlashcardList;

import java.util.ArrayList;
import java.util.List;

/**
 * Lists of flashcard groups.
 */
public class GroupList {
    private List<FlashcardGroup> groups;

    /**
     * Constructs a list of groups.
     */
    public GroupList() {
        this.groups = new ArrayList<FlashcardGroup>();
    }

    /**
     * Constructs a list of groups.
     *
     * @param flashcardGroups the list of flashcardGroups to be added
     */
    public GroupList(List<FlashcardGroup> flashcardGroups) {
        this();

        assert flashcardGroups != null : "Invalid flashcardGroups!";
        this.groups.addAll(flashcardGroups);
    }

    /**
     * Adds a new group to the group list.
     *
     * @param group the new group to be added to the list
     */
    public void addFlashcardGroup(FlashcardGroup group) {
        groups.add(group);
    }

    /**
     * Deletes a specific group from group list.
     *
     * @param groupIdentifier either the name or the index of the flashcard group
     * @return the group deleted
     */
    public FlashcardGroup deleteFlashcardGroup(String groupIdentifier) {
        for (FlashcardGroup g : groups) {
            if (g.getName().equals(groupIdentifier)) {
                groups.remove(g);
                return g;
            }
        }

        int groupIndex = Integer.parseInt(groupIdentifier) - 1;
        return groups.remove(groupIndex);
    }

    /**
     * Get a flashcard group by name.
     *
     * @param groupID the index or name of the flashcard group
     * @return the flashcard group
     * @throws UnrecognizedFlashcardGroupException if such a group does not exist
     */
    public FlashcardGroup getGroupByID(String groupID) throws UnrecognizedFlashcardGroupException {
        for (FlashcardGroup g : groups) {
            if (g.getName().equals(groupID)) {
                return g;
            }
        }

        try {
            int groupIndex = Integer.parseInt(groupID) - 1;
            return groups.get(groupIndex);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new UnrecognizedFlashcardGroupException("There is no such group.");
        }
    }

    public List<FlashcardGroup> getGroups() {
        return groups;
    }

    /**
     * Returns a FlashcardList of flashcards belonging to the group specified by groupName.
     *
     * @param groupIdentifier either the name or the index of the flashcard group
     * @return FlashcardList of flashcards belonging to the group
     */
    public FlashcardList getFlashcardsInGroup(String groupIdentifier) throws UnrecognizedFlashcardGroupException {
        FlashcardList flashcardsInGroup;
        for (FlashcardGroup g : groups) {
            if (g.getName().equals(groupIdentifier)) {
                flashcardsInGroup = g.getGroupCards();
                return flashcardsInGroup;
            }
        }

        try {
            int groupIndex = Integer.parseInt(groupIdentifier) - 1;
            FlashcardGroup g = groups.get(groupIndex);
            return g.getGroupCards();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new UnrecognizedFlashcardGroupException("Invalid group!");
        }
    }

    /**
     * Gets the flashcard group at a specific index.
     *
     * @param index the index.
     * @return the flashcard group at the specified index
     */
    public FlashcardGroup getGroupAtIdx(int index) {
        return groups.get(index);
    }

    /**
     * Gets the total number of groups in the list.
     *
     * @return total number of groups in the list
     */
    public int getTotalGroupNum() {
        return groups.size();
    }

    /**
     * Check if the current instance is equal to the object passed in.
     *
     * @param obj The object to be compared against the current instance
     * @return whether or not the two objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        GroupList otherGroupList = (GroupList) obj;
        if (this.getTotalGroupNum() != otherGroupList.getTotalGroupNum()) {
            return false;
        }

        for (int idx = 0; idx < otherGroupList.getTotalGroupNum(); idx++) {
            if (!this.getGroupAtIdx(idx).equals(otherGroupList.getGroupAtIdx(idx))) {
                return false;
            }
        }
        return true;
    }
}
