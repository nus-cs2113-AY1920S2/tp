package seedu.nuke.util;

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
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    public static String MESSAGE_ADD_MODULE_SUCCESS(String moduleCode, String title) {
        return String.format("SUCCESS!! Module %s %s has been added.\n", moduleCode, title);
    }

    public static String MESSAGE_DELETE_MODULE_SUCCESS(String moduleCode, String title) {
        return String.format("SUCCESS!! Module %s %s has been deleted.\n", moduleCode, title);
    }

    public static final String MESSAGE_SHOW_MODULES = "Here are ALL your modules.\n";
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

}
