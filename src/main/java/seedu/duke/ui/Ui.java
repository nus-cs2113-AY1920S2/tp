package seedu.duke.ui;

import seedu.duke.data.Person;

public class Ui {

    private static final String LOGO =
            "  __  __           _       _        __  __\n"
            + " |  \\/  |         | |     | |      |  \\/  |\n"
            + " | \\  / | ___   __| |_   _| | ___  | \\  / | __ _ _ __   __ _  __ _  ___ _ __\n"
            + " | |\\/| |/ _ \\ / _` | | | | |/ _ \\ | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '__|\n"
            + " | |  | | (_) | (_| | |_| | |  __/ | |  | | (_| | | | | (_| | (_| |  __/ |\n"
            + " |_|  |_|\\___/ \\__,_|\\__,_|_|\\___| |_|  |_|\\__,_|_| |_|\\__,_|\\__, |\\___|_|\n"
            + "                                                              __/ |\n"
            + "                                                             |___/\n";

    private static final String START_MESSAGE = "Hello from\n" + LOGO;

    private static final String FAREWELL_MESSAGE = "bye!";

    private static final String ADDED_TO_SEM_MESSAGE = "Okay! I have added this module to your semester's module list:";

    private static final String ADDED_TO_DATA_MESSAGE = "Okay! "
            + "I have added this module to the available modules to choose from:";

    private static final String VIEWED_MODULE_PLAN_MESSAGE = "Okay! Here is your module plan:";

    private static final String VIEWED_AVAILABLE_MODULE_MESSAGE = "Okay! Here are your available modules in database:";

    private static final String VIEWED_DONE_MODULE_MESSAGE = "Okay! Here are your completed modules:";

    private static final String DONE_MESSAGE = "Okay, I've marked the module as done!";

    private static final String FIND_MESSAGE = "Okay, this is the list of related modules:";

    private static final String DELETE_FROM_SEM_MESSAGE
            = "Okay, this module has been deleted from the corresponding semester";
    private static final String DELETE_FROM_AVAILABLE_MESSAGE
            = "Okay, this module has been deleted from the list of available modules";

    private static final String DELETE_FROM_AVAILABLE_FOLLOW_UP_MESSAGE
            = "Since the following module was found in your module plan, it will be deleted from your module plan too!";

    private static final String LINE_SEPARATOR = System.lineSeparator();

    private static final String BOX_EDGE = "--------------------------------------------------------------------------"
            + "--------------------------------------------------";

    private static final String BOX_HEADER = "| S/N |    ID    | Name                                                 "
            + "         | Module Credit | Prerequisites           |";

    public static final String BOX_MARGIN = "|-----|----------|------------------------------------------------------"
            + "---------|---------------|-------------------------|";

    private static final String HELP = "Here are the commands that I can do for you:\n"
            + "1. \"help\" to look at all the commands that I can do\n"
            + "2. \"view\" to see all modules you have in your module list\n"
            + "3. \"add id/[module code] s/[semester] mc/[credit] " +
            "| add n/[module name] s/[semester] mc/[credit] " +
            "| add id/[module code] n/[module name] s/[semester] mc/[credit]\"" +
            " to add a module to your personal module manager\n"
            + "4. \"add id/[module code] n/[name of module] mc/[module credit] pre/[pre requisites]\"" +
            " to add a module to the list of available modules\n"
            + "5. \"done n/[module name] g/[grade] | done id/[module code] g/[grade]\" "
            + "to mark it as done\n"
            + "6. \"view /mp\" to view your module plan\n"
            + "7. \"view /dm\" to view all finished modules\n"
            + "8. \"view /cc\" to view the number of modular credits u have completed";

    private static final String VIEWED_CREDITS = "You have completed this amount of credits:";

    private static final String SHOW_CAP = "Your current CAP is:";

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
        showToUser(VIEWED_AVAILABLE_MODULE_MESSAGE, BOX_EDGE, BOX_HEADER, viewList, BOX_EDGE, LINE_SEPARATOR);
    }

    public static void showViewDoneMessage(String viewList) {
        showToUser(VIEWED_DONE_MODULE_MESSAGE, viewList, LINE_SEPARATOR);
    }

    public static void showDoneMessage() {
        showToUser(DONE_MESSAGE, LINE_SEPARATOR);
    }

    public static void showFindMessage(String viewList) {
        showToUser(FIND_MESSAGE, viewList,  LINE_SEPARATOR);
    }

    public static void showDeleteFromSemMessage(String output) {
        showToUser(DELETE_FROM_SEM_MESSAGE, output, LINE_SEPARATOR);
    }

    public static void showDeleteFromAvailableMessage(String output) {
        showToUser(DELETE_FROM_AVAILABLE_MESSAGE, output, LINE_SEPARATOR);
    }

    public static void showDeleteFromAvailableFollowUpMessage(String module) {
        showToUser(DELETE_FROM_AVAILABLE_FOLLOW_UP_MESSAGE, module, LINE_SEPARATOR);
    }

    public static void showError(String errorMessage) {
        showToUser(errorMessage, LINE_SEPARATOR);
    }

    public static void showHelpMessage() {
        showToUser(HELP);
    }

    public static void showCompletedCredits() {
        showToUser(VIEWED_CREDITS, Integer.toString(Person.getTotalModuleCreditCompleted()), LINE_SEPARATOR);
    }

    public static void showCap(String cap) {
        showToUser(SHOW_CAP, cap, LINE_SEPARATOR);
    }
}
