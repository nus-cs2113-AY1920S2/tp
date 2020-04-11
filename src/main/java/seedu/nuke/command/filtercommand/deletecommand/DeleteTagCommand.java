package seedu.nuke.command.filtercommand.deletecommand;

import seedu.nuke.Executor;
import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.Directory;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.directory.TaskTag;

import java.util.ArrayList;

import static seedu.nuke.util.Message.MESSAGE_NO_TAGS_FOUND;
import static seedu.nuke.util.Message.messageConfirmDeleteTag;
import static seedu.nuke.util.Message.messagePromptDeleteTagIndices;

public class DeleteTagCommand extends DeleteCommand {
    public static final String COMMAND_WORD = "delg";
    public static final String FORMAT = COMMAND_WORD
            + " <tag info> -m <module code> -c <category name>"
            + " -t <task description>";
    public static final String MESSAGE_USAGE = COMMAND_WORD + System.lineSeparator() + "Delete tags of task"
            + System.lineSeparator() + FORMAT + System.lineSeparator();

    private String moduleCode;
    private String categoryName;
    private String taskDescription;
    private String tag;
    private boolean isExact;
    private boolean isAll;

    /**
     * Constructs the command to delete a file.
     *
     * @param moduleCode
     *  The module code of the module that has the category to delete the task
     * @param categoryName
     *  The name of the category to delete the task
     * @param taskDescription
     *  The description of the task
     * @param tag
     *  The tag of the task
     * @param isExact
     *  Checks if categories are to be filtered exactly
     * @param isAll
     *  Checks if filtering is to be done across all modules and categories
     */
    public DeleteTagCommand(String moduleCode, String categoryName, String taskDescription, String tag,
                             boolean isExact, boolean isAll) {
        this.moduleCode = moduleCode;
        this.categoryName = categoryName;
        this.taskDescription = taskDescription;
        this.tag = tag;
        this.isExact = isExact;
        this.isAll = isAll;
    }

    @Override
    public CommandResult execute() {
        ArrayList<TaskTag> filteredTagList =
                createFilteredTagList(moduleCode, categoryName, taskDescription, tag, isExact, isAll);
        sortTagList(filteredTagList);
        return executeInitialDelete(new ArrayList<>(filteredTagList));
    }

    @Override
    protected CommandResult executeInitialDelete(ArrayList<Directory> filteredTagList) {
        final int tagCount = filteredTagList.size();
        if (tagCount == 0) {
            return new CommandResult(MESSAGE_NO_TAGS_FOUND);
        } else if (tagCount == 1) {
            Executor.preparePromptConfirmation();
            Executor.setFilteredList(filteredTagList, DirectoryLevel.TAG);
            TaskTag toDelete = (TaskTag) filteredTagList.get(0);
            return new CommandResult(messageConfirmDeleteTag(toDelete));
        } else {
            Executor.preparePromptIndices();
            Executor.setFilteredList(filteredTagList, DirectoryLevel.TAG);
            return new CommandResult(messagePromptDeleteTagIndices(filteredTagList));
        }
    }
}
