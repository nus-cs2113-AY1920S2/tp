package seedu.nuke.util;

import seedu.nuke.directory.Category;
import seedu.nuke.directory.Directory;
import seedu.nuke.directory.Module;
import seedu.nuke.directory.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Message {
    public static final String MESSAGE_LOGO = "      ___          ___          ___          ___     \n"
            + "     /__/\\        /__/\\        /__/|        /  /\\    \n"
            + "     \\  \\:\\       \\  \\:\\      |  |:|       /  /:/_   \n"
            + "      \\  \\:\\       \\  \\:\\     |  |:|      /  /:/ /\\  \n"
            + "  _____\\__\\:\\  ___  \\  \\:\\  __|  |:|     /  /:/ /:/_ \n"
            + " /__/::::::::\\/__/\\  \\__\\:\\/__/\\_|:|____/__/:/ /:/ /\\\n"
            + " \\  \\:\\~~\\~~\\/\\  \\:\\ /  /:/\\  \\:\\/:::::/\\  \\:\\/:/ /:/\n"
            + "  \\  \\:\\  ~~~  \\  \\:\\  /:/  \\  \\::/~~~~  \\  \\::/ /:/ \n"
            + "   \\  \\:\\       \\  \\:\\/:/    \\  \\:\\       \\  \\:\\/:/  \n"
            + "    \\  \\:\\       \\  \\::/      \\  \\:\\       \\  \\::/   \n"
            + "     \\__\\/        \\__\\/        \\__\\/        \\__\\/    \n"
            + "\n";

    public static final String MESSAGE_WELCOME_1 = "Welcome to NUKE";
    public static final String MESSAGE_WELCOME_2 = "What can I do for you?";
    public static final String MESSAGE_NO_TASK_IN_LIST = "There is no task in the list!\n";
    public static final String MESSAGE_TASK_SUCCESSFULLY_LIST = "There are (is) %d task(s) in the list!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!\n";
    public static final String MESSAGE_INVALID_PARAMETERS = "Invalid parameters found!\n";
    public static final String MESSAGE_MISSING_PARAMETERS = "Alert! Some parameters are missing.\n";
    public static final String MESSAGE_EXIT = "Bye. Hope to see you again soon.";
    public static final String DIVIDER = "-".repeat(80);

    public static final String MESSAGE_SHOW_LIST = "Here are what you are looking for..\n";
    public static final String MESSAGE_PRIORITY_NOT_IN_RANGE =
            "Alert! Priority should be a number between 0 and 100 inclusive.\n";


    public static final String MESSAGE_NO_MODULES_FOUND = "Sorry. No modules found.\n";
    public static final String MESSAGE_NO_CATEGORIES_FOUND = "Sorry. No categories found.\n";
    public static final String MESSAGE_NO_TASKS_FOUND = "Sorry. No tasks found.\n";


    public static final String MESSAGE_TASK_ADDED = "Task added!\n";
    public static final String MESSAGE_TASK_REMOVED = "Task removed!\n";
    public static final String MESSAGE_MODULE_CHANGE_SUCCESSFUL = "Module change succeeded\n";
    public static final String MESSAGE_ROOT_CHANGE_SUCCESSFUL = "ROOT change succeeded\n";
    public static final String MESSAGE_GO_INTO_MODULE = "please go inside a Module!\n";
    public static final String MESSAGE_HELP = "Here are valid commands and corresponding format:\n";

    public static final String MESSAGE_TAG_ADDED = "Tag added!";


    public static String messageAddModuleSuccess(String moduleCode, String title) {
        return String.format("SUCCESS!! Module %s %s has been added.\n", moduleCode, title);
    }

    public static String messageAddCategorySuccess(String categoryName) {
        return String.format("SUCCESS!! Category %s is created.\n", categoryName);
    }

    public static String messageAddTaskSuccess(String taskDescription) {
        return String.format("SUCCESS!! Task %s is created.\n", taskDescription);
    }

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "SUCCESS!! Module(s) have been deleted.\n";

    public static final String MESSAGE_DELETE_CATEGORY_SUCCESS = "SUCCESS!! Category/Categories have been deleted.\n";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "SUCCESS!! Task(s) have been deleted.\n";

    public static final String MESSAGE_DELETE_ABORTED = "Deletion is aborted.\n";

    public static String messageConfirmDeleteModule(Module toDelete) {
        return String.format("Confirm delete %s %s?\n", toDelete.getModuleCode() , toDelete.getTitle());
    }

    public static String messageConfirmDeleteModule(ArrayList<Module> filteredModules, ArrayList<Integer> toDeleteIndices) {
        StringBuilder promptMessage = new StringBuilder();
        promptMessage.append("Confirm delete these modules?\n");
        for (int index : toDeleteIndices) {
            String toDeleteModuleCode = filteredModules.get(index).getModuleCode();
            String toDeleteTitle = filteredModules.get(index).getTitle();
            promptMessage.append(String.format("%s %s\n", toDeleteModuleCode, toDeleteTitle));
        }
        return promptMessage.toString();
    }

    public static String messagePromptDeleteModuleIndices(ArrayList<Directory> filteredModules) {
        ArrayList<Module> modules = filteredModules.stream()
                .map(Module.class::cast)
                .collect(Collectors.toCollection(ArrayList::new));
        return "Multiple matching modules found.\n" +
                ListCreator.createModuleListTable(modules) +
                "\nEnter the list number(s) of the modules to delete.\n";
    }

    public static String messageConfirmDeleteCategory(Category toDelete) {
        return String.format("Confirm delete %s?\n", toDelete.getCategoryName());
    }

    public static String messageConfirmDeleteCategory(ArrayList<Category> filteredCategory, ArrayList<Integer> toDeleteIndices) {
        StringBuilder promptMessage = new StringBuilder();
        promptMessage.append("Confirm delete these categories?\n");
        for (int index : toDeleteIndices) {
            String toDeleteCategoryName = filteredCategory.get(index).getCategoryName();
            promptMessage.append(String.format("%s\n", toDeleteCategoryName));
        }
        return promptMessage.toString();
    }

    public static String messagePromptDeleteCategoryIndices(ArrayList<Directory> filteredCategories) {
        ArrayList<Category> categories = filteredCategories.stream()
                .map(Category.class::cast)
                .collect(Collectors.toCollection(ArrayList::new));
        return "Multiple matching categories found.\n" +
                ListCreator.createCategoryListTable(categories) +
                "\nEnter the list number(s) of the categories to delete.\n";
    }

    public static String messageConfirmDeleteTask(Task toDelete) {
        return String.format("Confirm delete %s?\n", toDelete.getDescription());
    }

    public static String messageConfirmDeleteTask(ArrayList<Task> filteredTasks, ArrayList<Integer> toDeleteIndices) {
        StringBuilder promptMessage = new StringBuilder();
        promptMessage.append("Confirm delete these tasks?\n");
        for (int index : toDeleteIndices) {
            String toDeleteTaskDescription = filteredTasks.get(index).getDescription();
            promptMessage.append(String.format("%s\n", toDeleteTaskDescription));
        }
        return promptMessage.toString();
    }

    public static String messagePromptDeleteTaskIndices(ArrayList<Directory> filteredTasks) {
        ArrayList<Task> tasks = filteredTasks.stream()
                .map(Task.class::cast)
                .collect(Collectors.toCollection(ArrayList::new));
        return "Multiple matching tasks found.\n" +
                ListCreator.createTaskListTable(tasks) +
                "\nEnter the list number(s) of the tasks to delete.\n";
    }

    public static final String MESSAGE_PROMPT_FORMAT = "Enter 'yes' to confirm or 'no' to abort.";
    public static final String MESSAGE_INVALID_DELETE_INDICES = "Deletion aborted due to invalid index provided.\n";

    public static final String MESSAGE_UNDO_SUCCESSFUL = "Successfully undo!\n";



}
