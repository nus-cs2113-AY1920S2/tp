package seedu.nuke.util;

public class Message {
    public static final String MESSAGE_LOGO = "                     __ ____  ______  ____\n" +
            "                    / //_/ / / / __ \\/  _/\n" +
            "                   / ,< / / / / /_/ // /  \n" +
            "                  / /| / /_/ / _, _// /   \n" +
            "                 /_/ |_\\____/_/ |_/___/   \n" +
            "                         \n";
    public static final String MESSAGE_WELCOME_1 = "Welcome to NUKE";
    public static final String MESSAGE_WELCOME_2 = "What can I do for you?";
    public static final String MESSAGE_FAREWELL = "Bye. Hope to see you again soon!";

    public static String MESSAGE_ADD_MODULE_SUCCESS(String moduleCode, String title) {
        return String.format("SUCCESS!! Module %s %s has been added.\n", moduleCode, title);
    }

    public static String MESSAGE_DELETE_MODULE_SUCCESS(String moduleCode, String title) {
        return String.format("SUCCESS!! Module %s %s has been deleted.\n", moduleCode, title);
    }

    public static final String MESSAGE_SHOW_TASKS = "Here are ALL your tasks.\n";

}
