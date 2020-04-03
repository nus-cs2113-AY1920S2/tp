package seedu.techtoday.common;

import org.jetbrains.annotations.NotNull;

public class Messages {
    public static String SPLIT_LINE_HELP = "**_____________________________________________"
            + "________________________________**";
    public static String SPLIT_LINE = "__________________________________________"
            + "________________________________________________";


    /** Let the statement be printed in center. **/
    public static void printInCenter(@NotNull String str) {
        int left = (83 - str.length()) / 2;
        int right = 83 - left - str.length();
        String repeatedChar = " ";
        String buff = "_"
                + repeatedChar.repeat(Math.max(0, left))
                + str
                + repeatedChar.repeat(Math.max(0, right - 1))
                + "_";
        System.out.println(buff);
    }

    /** Shows message to user. **/
    public static void greet() {
        printInCenter(SPLIT_LINE_HELP);
        printInCenter("");
        printInCenter("Hello! Here's TechToday.");
        printInCenter("Let me show you some technology news to refresh your mind!");
        printInCenter(SPLIT_LINE_HELP);
        printCommand();
        printInCenter(SPLIT_LINE_HELP);
        System.out.println("     What can I do for you?");
        System.out.println();
    }

    /** Prints the list of possible commands the user can type. **/
    public static void printCommand() {
        printInCenter("Your queries can be of the following forms:");
        printInCenter("1. help");
        printInCenter("2. view [article / job]");
        printInCenter("3. save [article / job] INDEX_NUMBER");
        printInCenter("4. create [article / job / note]");
        printInCenter("5. list [article / job / note]");
        printInCenter("6. delete [article / job / note] INDEX_NUMBER");
        printInCenter("7. addinfo [article / job / note] INDEX_NUMBER EXTRACT");
        printInCenter("8. exit");
        printInCenter("");
    }

    /** Prints a straight line. */
    public static void printStraightLine() {
        System.out.println(SPLIT_LINE + "\n");
        System.out.println("\n");
    }

    /**
     * Prints Exception Message when addInfo Command is incorrect.
     *
     * @param s - Message to be printed in the center.
     */
    public static void printAddInfoException(String s) {
        System.out.println("addinfo should be of the following form: \n");
        Messages.printInCenter(s);
        Messages.printStraightLine();
    }

}
