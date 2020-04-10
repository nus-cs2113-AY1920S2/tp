package seedu.nuke.command.filtercommand.listcommand;

import seedu.nuke.command.CommandResult;
import seedu.nuke.command.filtercommand.FilterCommand;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.directory.TaskTag;

import java.util.ArrayList;

import static seedu.nuke.util.Message.MESSAGE_NO_TAGS_TO_SHOW;
import static seedu.nuke.util.Message.MESSAGE_SHOW_LIST;

public class ListTagCommand extends FilterCommand {
    public static final String COMMAND_WORD = "lsg";
    public static final String FORMAT = "lsg [ <tag keyword> -m <module keyword> "
            + "-c <category keyword> -t <task keyword> -e -a ]";
    public static final String MESSAGE_USAGE = String.format(
            "%s - Filter and show the tags of task(s)\n"
                    + "Note: -e to filter for exact keywords; -a to filter across ALL your files\n"
                    + "Format: %s\n"
                    + "Example: lsg urgent -m cs2113t -t tut -a\n",
            COMMAND_WORD, FORMAT);

    private String moduleKeyword;
    private String categoryKeyword;
    private String taskKeyword;
    private String tagKeyword;
    private boolean isExact;
    private boolean isAll;

    /**
     * Constructs the command to list tags.
     * @param moduleKeyword the keyword to filter modules
     * @param categoryKeyword the keyword to filter categories
     * @param taskKeyword the keyword to filter tasks
     * @param tagKeyword the keyword to filter tags
     * @param isExact checks if tags are to be filtered exactly
     * @param isAll checks whether to show <b>all</b> tags across modules and categories
     */
    public ListTagCommand(String moduleKeyword, String categoryKeyword, String taskKeyword, String tagKeyword,
                          boolean isExact, boolean isAll) {
        this.moduleKeyword = moduleKeyword;
        this.categoryKeyword = categoryKeyword;
        this.taskKeyword = taskKeyword;
        this.tagKeyword = tagKeyword;
        this.isExact = isExact;
        this.isAll = isAll;
    }

    @Override
    public CommandResult execute() {
        ArrayList<TaskTag> filteredTagList =
                createFilteredTagList(moduleKeyword, categoryKeyword, taskKeyword, tagKeyword, isExact, isAll);
        if (filteredTagList.isEmpty()) {
            return new CommandResult(MESSAGE_NO_TAGS_TO_SHOW);
        }
        sortTagList(filteredTagList);
        return new CommandResult(MESSAGE_SHOW_LIST, DirectoryLevel.TAG, new ArrayList<>(filteredTagList));
    }
}
