package seedu.happypills.ui;

public class TextUi {
    public static final String DIVIDER = "    ===================================================";

    /**
     * Generates and prints the welcome message upon the start of the application.
     */
    public void printWelcomeMessage() {
        String logo = "\n"
                + " __   __  _______  _______  _______  __   __  _______  ___   ___      ___      _______ \n"
                + "|  | |  ||   _   ||       ||       ||  | |  ||       ||   | |   |    |   |    |       |\n"
                + "|  |_|  ||  |_|  ||    _  ||    _  ||  |_|  ||    _  ||   | |   |    |   |    |  _____|\n"
                + "|       ||       ||   |_| ||   |_| ||       ||   |_| ||   | |   |    |   |    | |_____ \n"
                + "|       ||       ||    ___||    ___||_     _||    ___||   | |   |___ |   |___ |_____  |\n"
                + "|   _   ||   _   ||   |    |   |      |   |  |   |    |   | |       ||       | _____| |\n"
                + "|__| |__||__| |__||___|    |___|      |___|  |___|    |___| |_______||_______||_______|\n";

        System.out.println("Hello from" + logo);
    }
}
