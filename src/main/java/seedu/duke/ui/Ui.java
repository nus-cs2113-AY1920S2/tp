package seedu.duke.ui;

public class Ui {

    private static final String LOGO =
              " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String START_MESSAGE = "Hello from\n" + LOGO + "What can I do for you?";

    private static final String FAREWELL_MESSAGE = "bye!";

    private static final String ADDED_MESSAGE = "Okay! I have added this module to your module list:";

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

    public static void showAddedMessage(String module) {
        showToUser(ADDED_MESSAGE, module,LINE_SEPARATOR);
    }
}
