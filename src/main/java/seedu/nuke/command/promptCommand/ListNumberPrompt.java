package seedu.nuke.command.promptcommand;

import seedu.nuke.Executor;
import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.Directory;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static seedu.nuke.util.ExceptionMessage.MESSAGE_LIST_NUMBER_NOT_FOUND;
import static seedu.nuke.util.Message.messageConfirmDeleteCategory;
import static seedu.nuke.util.Message.messageConfirmDeleteModule;
import static seedu.nuke.util.Message.messageConfirmDeleteTask;

public class ListNumberPrompt extends Command {
    public static final Pattern INDICES_FORMAT = Pattern.compile("(?<indices>(?:\\s*\\d+)+\\s*)");

    private ArrayList<Directory> filteredList;
    private ArrayList<Integer> indices;
    private DirectoryLevel directoryLevel;

    /**
     * Constructs the command to process the indices received as input from the user.
     *
     * @param indices
     *  The indices received as input from the user
     */
    public ListNumberPrompt(ArrayList<Integer> indices) {
        this.filteredList = Executor.getFilteredList();
        this.indices = indices;
        this.directoryLevel = Executor.getDirectoryLevel();
    }

    /**
     * Executes the prompt to confirm the deletion of directories.
     *
     * @return
     *  The Command result of the execution
     */
    private CommandResult executePromptConfirmation() {
        switch (directoryLevel) {

        case MODULE: {
            // Cast to Array List of modules
            ArrayList<Module> filteredModules = filteredList.stream()
                    .map(Module.class::cast)
                    .collect(Collectors.toCollection(ArrayList::new));
            return new CommandResult(messageConfirmDeleteModule(filteredModules, indices));
        }

        case CATEGORY: {
            // Cast to Array List of categories
            ArrayList<Category> filteredCategories = filteredList.stream()
                    .map(Category.class::cast)
                    .collect(Collectors.toCollection(ArrayList::new));
            return new CommandResult(messageConfirmDeleteCategory(filteredCategories, indices));
        }

        case TASK: {
            // Cast to Array List of tasks
            ArrayList<Task> filteredTasks = filteredList.stream()
                    .map(Task.class::cast)
                    .collect(Collectors.toCollection(ArrayList::new));
            return new CommandResult(messageConfirmDeleteTask(filteredTasks, indices));
        }

        default:
            return new CommandResult("Error in displaying list.");
        }
    }

    /**
     * Executes the intermediate step in the deletion of Directories.
     *
     * @return
     *  The Command result of the execution
     */
    @Override
    public CommandResult execute() {
        Executor.preparePromptConfirmation();
        Executor.setIndices(indices);

        try {
            return executePromptConfirmation();
        } catch (IndexOutOfBoundsException e) {
            Executor.terminatePrompt();
            return new CommandResult(MESSAGE_LIST_NUMBER_NOT_FOUND);
        }
    }
}
