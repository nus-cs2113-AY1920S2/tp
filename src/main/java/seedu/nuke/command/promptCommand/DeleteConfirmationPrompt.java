package seedu.nuke.command.promptCommand;

import seedu.nuke.Executor;
import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.DirectoryLevel;
import seedu.nuke.data.ModuleManager;
import seedu.nuke.directory.Category;
import seedu.nuke.directory.Directory;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static seedu.nuke.util.Message.*;

public class DeleteConfirmationPrompt extends Command {
    private ConfirmationStatus confirmationStatus;
    private DirectoryLevel directoryLevel;

    public DeleteConfirmationPrompt(ConfirmationStatus confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
        this.directoryLevel = Executor.getDirectoryLevel();
    }

    public void deleteSingleModule(Module toDelete) {
        ModuleManager.delete(toDelete);
    }

    public void deleteSingleCategory(Category toDelete) {
        Module parentModule = (Module) toDelete.getParent();
        parentModule.getCategories().delete(toDelete);
    }

    public void deleteSingleTask(Task toDelete) {
        Category parentCategory = (Category) toDelete.getParent();
        parentCategory.getTasks().delete(toDelete);
    }

    public void deleteMultipleModules(ArrayList<Module> modules, ArrayList<Integer> toDeleteIndices) {
        for (int index : toDeleteIndices) {
            Module toDelete = modules.get(index);
            deleteSingleModule(toDelete);
        }
    }

    public void deleteMultipleCategories(ArrayList<Category> categories, ArrayList<Integer> toDeleteIndices) {
        for (int index : toDeleteIndices) {
            Category toDelete = categories.get(index);
            deleteSingleCategory(toDelete);
        }
    }

    public void deleteMultipleTasks(ArrayList<Task> tasks, ArrayList<Integer> toDeleteIndices) {
        for (int index : toDeleteIndices) {
            Task toDelete = tasks.get(index);
            deleteSingleTask(toDelete);
        }
    }

    private CommandResult executeSingleDelete(ArrayList<Directory> filteredList) {
        switch (directoryLevel) {
            case MODULE: {
                Module toDelete = (Module) filteredList.get(0);
                deleteSingleModule(toDelete);
                return new CommandResult(MESSAGE_DELETE_MODULE_SUCCESS);
            }
            case CATEGORY: {
                Category toDelete = (Category) filteredList.get(0);
                deleteSingleCategory(toDelete);
                return new CommandResult(MESSAGE_DELETE_CATEGORY_SUCCESS);
            }
            case TASK: {
                Task toDelete = (Task) filteredList.get(0);
                deleteSingleTask(toDelete);
                return new CommandResult(MESSAGE_DELETE_TASK_SUCCESS);
            }
            default:
                return new CommandResult("Error in single deletion.");
        }
    }

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

    @Override
    public CommandResult execute() {
        switch (confirmationStatus) {
            case CONFIRM:
                Executor.terminatePrompt();
                ArrayList<Directory> filteredList = Executor.getFilteredList();
                if (filteredList.size() == 1) {
                    return executeSingleDelete(filteredList);
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
