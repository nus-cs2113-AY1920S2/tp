package seedu.nuke.command.filtercommand.deletecommand;

import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.Directory;

import java.util.ArrayList;

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
        return null; //to be implemented in v3.0
    }

    @Override
    protected CommandResult executeInitialDelete(ArrayList<Directory> filteredList) {
        return null; //to be implemented in v3.0
    }
}
