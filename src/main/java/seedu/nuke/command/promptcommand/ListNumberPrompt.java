package seedu.nuke.command.promptcommand;

import seedu.nuke.Executor;
import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.Directory;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;
import seedu.nuke.directory.TaskFile;
import seedu.nuke.directory.TaskTag;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static seedu.nuke.util.ExceptionMessage.MESSAGE_LIST_NUMBER_NOT_FOUND;
import static seedu.nuke.util.Message.messageConfirmDeleteCategory;
import static seedu.nuke.util.Message.messageConfirmDeleteFile;
import static seedu.nuke.util.Message.messageConfirmDeleteModule;
import static seedu.nuke.util.Message.messageConfirmDeleteTag;
import static seedu.nuke.util.Message.messageConfirmDeleteTask;

public class ListNumberPrompt extends Command {
    public static final Pattern INDICES_FORMAT = Pattern.compile("(?<indices>(?:\\s*\\d+)+\\s*)");

    private ArrayList<Integer> indices;

    /**
     * Constructs the command to process the indices received as input from the user.
     *
     * @param indices
     *  The indices received as input from the user
     */
    public ListNumberPrompt(ArrayList<Integer> indices) {
        this.indices = indices;
    }

    /**
     * Executes the prompt to confirm the deletion of directories.
     *
     * @param filteredList
     *  The filtered L
     * @return
     *  The Command result of the execution
     */
    private CommandResult executePromptConfirmation(ArrayList<Directory> filteredList, DirectoryLevel directoryLevel) {

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

        case FILE: {
            // Cast to Array List of tasks
            ArrayList<TaskFile> fileteredFiles = filteredList.stream()
                    .map(TaskFile.class::cast)
                    .collect(Collectors.toCollection(ArrayList::new));
            return new CommandResult(messageConfirmDeleteFile(fileteredFiles, indices));
        }

        case TAG: {
            ArrayList<TaskTag> filteredTags =  filteredList.stream()
                    .map(TaskTag.class::cast)
                    .collect(Collectors.toCollection(ArrayList::new));
            return new CommandResult(messageConfirmDeleteTag(filteredTags, indices));
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
        ArrayList<Directory> filteredList = Executor.getFilteredList();
        DirectoryLevel directoryLevel = Executor.getDirectoryLevel();
        Executor.preparePromptConfirmation();
        Executor.setIndices(indices);

        try {
            return executePromptConfirmation(filteredList, directoryLevel);
        } catch (IndexOutOfBoundsException e) {
            Executor.terminatePrompt();
            return new CommandResult(MESSAGE_LIST_NUMBER_NOT_FOUND);
        }
    }
}
