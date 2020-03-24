package seedu.nuke.command.promptcommand;

import seedu.nuke.Executor;
import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.Directory;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.directory.DirectoryTraverser;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;
import seedu.nuke.exception.IncorrectDirectoryLevelException;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static seedu.nuke.util.Message.MESSAGE_DELETE_ABORTED;
import static seedu.nuke.util.Message.MESSAGE_DELETE_CATEGORY_SUCCESS;
import static seedu.nuke.util.Message.MESSAGE_DELETE_MODULE_SUCCESS;
import static seedu.nuke.util.Message.MESSAGE_DELETE_TASK_SUCCESS;
import static seedu.nuke.util.Message.MESSAGE_ILLEGAL_DELETE;
import static seedu.nuke.util.Message.MESSAGE_PROMPT_FORMAT;

public class DeleteConfirmationPrompt extends Command {
    private ConfirmationStatus confirmationStatus;
    private DirectoryLevel directoryLevel;

    /**
     * Constructs the command to process the delete confirmation prompt.
     *
     * @param confirmationStatus
     *  The confirmation status of the deletion
     */
    public DeleteConfirmationPrompt(ConfirmationStatus confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
        this.directoryLevel = Executor.getDirectoryLevel();
    }

    /**
     * Deletes a single module.
     *
     * @param toDelete
     *  The module to be deleted
     */
    private void deleteSingleModule(Module toDelete) {
        ModuleManager.delete(toDelete);
    }

    /**
     * Deletes a single category.
     *
     * @param toDelete
     *  The category to be deleted
     */
    private void deleteSingleCategory(Category toDelete) {
        Module parentModule = toDelete.getParent();
        parentModule.getCategories().delete(toDelete);
    }

    /**
     * Deletes a single task.
     *
     * @param toDelete
     *  The task to be deleted
     */
    private void deleteSingleTask(Task toDelete) {
        Category parentCategory = toDelete.getParent();
        parentCategory.getTasks().delete(toDelete);
    }

    /**
     * Deletes multiple modules.
     *
     * @param modules
     *  The filtered list of modules
     * @param toDeleteIndices
     *  The list of indices of the modules to be deleted
     */
    private void deleteMultipleModules(ArrayList<Module> modules, ArrayList<Integer> toDeleteIndices) {
        for (int index : toDeleteIndices) {
            Module toDelete = modules.get(index);
            deleteSingleModule(toDelete);
        }
    }

    /**
     * Deletes multiple categories.
     *
     * @param categories
     *  The filtered list of categories
     * @param toDeleteIndices
     *  The list of indices of the categories to be deleted
     */
    private void deleteMultipleCategories(ArrayList<Category> categories, ArrayList<Integer> toDeleteIndices) {
        for (int index : toDeleteIndices) {
            Category toDelete = categories.get(index);
            deleteSingleCategory(toDelete);
        }
    }

    /**
     * Deletes multiple tasks.
     *
     * @param tasks
     *  The filtered list of tasks
     * @param toDeleteIndices
     *  The list of indices of the tasks to be deleted
     */
    private void deleteMultipleTasks(ArrayList<Task> tasks, ArrayList<Integer> toDeleteIndices) {
        for (int index : toDeleteIndices) {
            Task toDelete = tasks.get(index);
            deleteSingleTask(toDelete);
        }
    }

    /**
     * Executes single deletion for the various levels of the Directory.
     *
     * @param toDelete
     *  The Directory to be deleted
     * @return
     *  The Command result of the execution
     */
    private CommandResult executeSingleDelete(Directory toDelete) {
        switch (directoryLevel) {

        case MODULE: {
            deleteSingleModule(((Module) toDelete));
            return new CommandResult(MESSAGE_DELETE_MODULE_SUCCESS);
        }

        case CATEGORY: {
            deleteSingleCategory(((Category) toDelete));
            return new CommandResult(MESSAGE_DELETE_CATEGORY_SUCCESS);
        }

        case TASK: {
            deleteSingleTask(((Task) toDelete));
            return new CommandResult(MESSAGE_DELETE_TASK_SUCCESS);
        }

        default:
            return new CommandResult("Error in single deletion.");
        }
    }

    /**
     * Executes multiple deletion for the various levels of the Directory.
     *
     * @param filteredList
     *  The list of Directories to be deleted
     * @return
     *  The Command result of the execution
     */
    private CommandResult executeMultipleDelete(ArrayList<Directory> filteredList) {
        ArrayList<Integer> toDeleteIndices = Executor.getIndices();
        switch (directoryLevel) {

        case MODULE: {
            // Cast to Array List of modules
            ArrayList<Module> filteredModules = filteredList.stream()
                    .map(Module.class::cast)
                    .collect(Collectors.toCollection(ArrayList::new));
            deleteMultipleModules(filteredModules, toDeleteIndices);
            return new CommandResult(MESSAGE_DELETE_MODULE_SUCCESS);
        }

        case CATEGORY: {
            // Cast to Array List of categories
            ArrayList<Category> filteredCategories = filteredList.stream()
                    .map(Category.class::cast)
                    .collect(Collectors.toCollection(ArrayList::new));
            deleteMultipleCategories(filteredCategories, toDeleteIndices);
            return new CommandResult(MESSAGE_DELETE_CATEGORY_SUCCESS);
        }

        case TASK: {
            // Cast to Array List of tasks
            ArrayList<Task> filteredTasks = filteredList.stream()
                    .map(Task.class::cast)
                    .collect(Collectors.toCollection(ArrayList::new));
            deleteMultipleTasks(filteredTasks, toDeleteIndices);
            return new CommandResult(MESSAGE_DELETE_TASK_SUCCESS);
        }

        default:
            return new CommandResult("Error in multiple deletion.");
        }
    }

    /**
     * Checks if any of the directories to be deleted is the current directory or its parent.
     *
     * @param filteredList
     *  The filtered list of directories to be deleted
     * @return
     *  <code>TRUE</code> if the current directory or its parent is being deleted, or <code>FALSE</code> otherwise
     * @throws IncorrectDirectoryLevelException
     *  If there is illegal access to a directory
     */
    private boolean isCurrentDirectoryInList(ArrayList<Directory> filteredList)
            throws IncorrectDirectoryLevelException {
        // Current directory is of a higher level than the directories to be deleted
        if (DirectoryTraverser.getCurrentDirectoryLevel().ordinal() > directoryLevel.ordinal()) {
            return false;
        }
        // Checks if current directory or parent directory is to be deleted
        switch (directoryLevel) {

        case MODULE:
            String baseModuleCode = DirectoryTraverser.getBaseModule().getModuleCode();
            for (Directory module : filteredList) {
                if (((Module) module).isSameModule(baseModuleCode)) {
                    return true;
                }
            }
            return false;

        case CATEGORY:
            String baseCategoryName = DirectoryTraverser.getBaseCategory().getCategoryName();
            for (Directory category : filteredList) {
                if (((Category) category).isSameCategory(baseCategoryName)) {
                    return true;
                }
            }
            return false;

        case TASK:
            String baseTaskDescription = DirectoryTraverser.getBaseTask().getDescription();
            for (Directory task : filteredList) {
                if (((Task) task).isSameTask(baseTaskDescription)) {
                    return true;
                }
            }
            return false;

        default:
            throw new IncorrectDirectoryLevelException();
        }
    }

    /**
     * Executes the final phase of the <b>Delete Command</b> to delete Directories from their lists.
     *
     * @return
     *  The Command result of the execution
     */
    @Override
    public CommandResult execute() {
        switch (confirmationStatus) {
        case CONFIRM:
            Executor.terminatePrompt();
            ArrayList<Directory> filteredList = Executor.getFilteredList();
            try {
                if (isCurrentDirectoryInList(filteredList)) {
                    return new CommandResult(MESSAGE_ILLEGAL_DELETE);
                }
            } catch (IncorrectDirectoryLevelException e) {
                return new CommandResult("Error in deletion.");
            }
            if (filteredList.size() == 1) {
                return executeSingleDelete(filteredList.get(0));
            } else {
                return executeMultipleDelete(filteredList);
            }
        case ABORT:
            Executor.terminatePrompt();
            return new CommandResult(MESSAGE_DELETE_ABORTED);
        default:
            return new CommandResult(MESSAGE_PROMPT_FORMAT);
        }
    }
}
