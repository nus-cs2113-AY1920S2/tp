package seedu.duke.ui;

public class Ui {

    private static final String LOGO =
            " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String START_MESSAGE = "Hello from\n" + LOGO + "What can I do for you?";

    private static final String FAREWELL_MESSAGE = "bye!";

    private static final String ADDED_TO_SEM_MESSAGE = "Okay! I have added this module to your semester's module list:";

    private static final String ADDED_TO_DATA_MESSAGE = "Okay! "
            + "I have added this module to the available modules to choose from:";

    private static final String VIEWED_MODULE_PLAN_MESSAGE = "Okay! Here is your module plan:";

    private static final String VIEWED_AVAILABLE_MODULE_MESSAGE = "Okay! Here are your available modules in database:";

    private static final String VIEWED_DONE_MODULE_MESSAGE = "Okay! Here are your completed modules:";

    private static final String DONE_MESSAGE = "Okay, I've marked the module as done!";

    private static final String LINE_SEPARATOR = System.lineSeparator();

    private static void showToUser(String... messages) {
        for (String m: messages) {
            System.out.println(m);
        }
    }

    public static void greetUser() {
        showToUser(START_MESSAGE, LINE_SEPARATOR);
    }

    public static void greetFarewell() {
        showToUser(FAREWELL_MESSAGE);
    }

    public static void showAddedToSemMessage(String module) {
        showToUser(ADDED_TO_SEM_MESSAGE, module, LINE_SEPARATOR);
    }

    public static void showAddedToDataMessage(String module) {
        showToUser(ADDED_TO_DATA_MESSAGE, module, LINE_SEPARATOR);
    }

    public static void showViewMessage(String viewList) {
        showToUser(VIEWED_MODULE_PLAN_MESSAGE, viewList, LINE_SEPARATOR);
    }

    public static void showViewAvailableMessage(String viewList) {
        showToUser(VIEWED_AVAILABLE_MODULE_MESSAGE, viewList, LINE_SEPARATOR);
    }

    public static void showViewDoneMessage(String viewList) {
        showToUser(VIEWED_DONE_MODULE_MESSAGE, viewList, LINE_SEPARATOR);
    }

    public static void showDoneMessage() {
        showToUser(DONE_MESSAGE, LINE_SEPARATOR);
    }

    public static void showError(String errorMessage) {
        showToUser(errorMessage);
    }

}
