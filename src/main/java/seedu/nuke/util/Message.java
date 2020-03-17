package seedu.nuke.util;

public class Message {
    public static final String MESSAGE_DUPLICATE_MODULE_ADD = "ALERT! The module already existed\n";
    public static final String MESSAGE_MODULE_NOT_FOUND = "Alert! Module is not found\n";
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
    public static final String MESSAGE_SHOW_MODULES = "Here are ALL your modules.\n";
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

    public static String messageDeleteModuleSuccess(String moduleCode, String title) {
        return String.format("SUCCESS!! Module %s %s has been deleted.\n", moduleCode, title);
    }

    public static final String MESSAGE_UNDO_SUCCESSFUL = "Successfully undo!\n";

}
