package seedu.nuke.util;

public class Message {

    public static String MESSAGE_ADD_MODULE_SUCCESS(String moduleCode, String title) {
        return String.format("SUCCESS!! Module %s %s has been added.", moduleCode, title);
    }

    public static String MESSAGE_DELETE_MODULE_SUCCESS(String moduleCode, String title) {
        return String.format("SUCCESS!! Module %s %s has been deleted.", moduleCode, title);
    }

    public static final String MESSAGE_SHOW_TASKS = "Here are ALL your tasks.";

    public static final String MESSAGE_EXIT = "Goodbye\nNuke program has terminated.";

}
