package seedu.nuke.util;

import seedu.nuke.data.module.Module;
import seedu.nuke.format.ListCreator;

import java.util.ArrayList;

public class Message {
    public static final String MESSAGE_LOGO = "      ___          ___          ___          ___     \n" +
            "     /__/\\        /__/\\        /__/|        /  /\\    \n" +
            "     \\  \\:\\       \\  \\:\\      |  |:|       /  /:/_   \n" +
            "      \\  \\:\\       \\  \\:\\     |  |:|      /  /:/ /\\  \n" +
            "  _____\\__\\:\\  ___  \\  \\:\\  __|  |:|     /  /:/ /:/_ \n" +
            " /__/::::::::\\/__/\\  \\__\\:\\/__/\\_|:|____/__/:/ /:/ /\\\n" +
            " \\  \\:\\~~\\~~\\/\\  \\:\\ /  /:/\\  \\:\\/:::::/\\  \\:\\/:/ /:/\n" +
            "  \\  \\:\\  ~~~  \\  \\:\\  /:/  \\  \\::/~~~~  \\  \\::/ /:/ \n" +
            "   \\  \\:\\       \\  \\:\\/:/    \\  \\:\\       \\  \\:\\/:/  \n" +
            "    \\  \\:\\       \\  \\::/      \\  \\:\\       \\  \\::/   \n" +
            "     \\__\\/        \\__\\/        \\__\\/        \\__\\/    \n" +
            "\n";

    public static final String MESSAGE_WELCOME_1 = "Welcome to NUKE";
    public static final String MESSAGE_WELCOME_2 = "What can I do for you?";
    public static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_NO_TASK_IN_LIST = "There is no task in the list!";
    public static final String MESSAGE_TASK_LIST_SUCCESSFUL = "There are (is) %d task(s) in the list!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!\n";
    public static final String MESSAGE_INVALID_PARAMETERS = "Invalid parameters found!\n";


    public static String MESSAGE_ADD_MODULE_SUCCESS(String moduleCode, String title) {
        return String.format("SUCCESS!! Module %s %s has been added.\n", moduleCode, title);
    }

    public static final String MESSAGE_DELETE_MODULE_SUCCESS = "SUCCESS!! Module(s) have been deleted.\n";

    public static final String MESSAGE_DELETE_MODULE_ABORTED = "Module deletion is aborted.\n";

    public static final String MESSAGE_NO_MODULES_FOUND = "Sorry. No modules found.\n";

    public static final String MESSAGE_SHOW_LIST = "Here are what you are looking for..\n";
    public static final String MESSAGE_TASK_ADDED = "Task added!";
    public static final String MESSAGE_TASK_REMOVED = "Task removed!";
    public static final String MESSAGE_MODULE_CHANGE_SUCCESSFUL = "Module change succeeded";

    public static String MESSAGE_ADD_CATEGORY_SUCCESS(String categoryName) {
        return String.format("SUCCESS!! Category %s is created.\n", categoryName);
    }

    public static String MESSAGE_DELETE_CATEGORY_SUCCESS(String categoryName) {
        return String.format("SUCCESS!! Category %s has been deleted.\n", categoryName);
    }

    public static String MESSAGE_ADD_TASK_SUCCESS(String taskDescription) {
        return String.format("SUCCESS!! Task %s is created.\n", taskDescription);
    }

    public static String MESSAGE_DELETE_TASK_SUCCESS(String taskDescription) {
        return String.format("SUCCESS!! Task %s has been deleted.\n", taskDescription);
    }



    /* PROMPT MESSAGES */
    public static String MESSAGE_CONFIRM_DELETE_MODULE(Module toDelete) {
        return String.format("Confirm delete %s %s?\n", toDelete.getModuleCode() , toDelete.getTitle());
    }

    public static String MESSAGE_CONFIRM_DELETE_MODULE(ArrayList<Module> filteredModules, ArrayList<Integer> toDeleteIndices) {
        StringBuilder promptMessage = new StringBuilder();
        promptMessage.append("Confirm delete these modules?\n");
        for (int index : toDeleteIndices) {
            String toDeleteModuleCode = filteredModules.get(index).getModuleCode();
            String toDeleteTitle = filteredModules.get(index).getTitle();
            promptMessage.append(String.format("%s %s\n", toDeleteModuleCode, toDeleteTitle));
        }
        return promptMessage.toString();
    }

    public static final String MESSAGE_PROMPT_FORMAT = "Enter 'yes' to confirm or 'no' to abort.";

    public static String MESSAGE_PROMPT_DELETE_MODULE_INDICES(ArrayList<Module> filteredModules) {
        return "Multiple matching modules found.\n" +
                ListCreator.createModuleListTable(filteredModules) +
                "\nEnter the list number(s) to delete modules.\n";
    }

    public static final String MESSAGE_INVALID_DELETE_INDICES = "Deletion aborted due to invalid index provided.\n";

}
