package seedu.tp.commands;

import seedu.tp.group.FlashcardGroup;
import seedu.tp.group.GroupList;

import java.util.List;

/**
 * Command to list all existing groups.
 */
public class ShowGroupsCommand extends Command {

    private GroupList groupList;

    /**
     * Constructor for ShowGroupsCommand.
     *
     * @param groupList list of flashcard groups
     */
    public ShowGroupsCommand(GroupList groupList) {
        assert groupList != null : "Invalid null GroupList!";

        this.groupList = groupList;
    }

    @Override
    public CommandFeedback execute() {
        LOGGER.info("Executing ShowGroupsCommand...");
        LOGGER.info("ShowGroupsCommand executed!");
        String feedback = getFeedback(groupList);
        return new CommandFeedback(feedback);
    }

    private String getFeedback(GroupList groupList) {
        if (groupList.getTotalGroupNum() == 0) {
            return "There are no existing groups. Use \"group\" to create a new group.";
        }
        List<FlashcardGroup> groups = groupList.getGroups();
        StringBuilder feedback = new StringBuilder("Here are all existing groups:");
        feedback.append(System.lineSeparator());
        for (int i = 0; i < groups.size(); i++) {
            FlashcardGroup group = groups.get(i);
            String groupName = group.getName();
            feedback.append(i + 1 + ". " + groupName);
            feedback.append(" | Description: " + group.getDescription());
            feedback.append(System.lineSeparator());
        }
        return feedback.toString().trim();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ShowGroupsCommand)) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        ShowGroupsCommand otherShowGroupsCommand = (ShowGroupsCommand) obj;
        return this.groupList.equals(otherShowGroupsCommand.groupList);
    }
}
