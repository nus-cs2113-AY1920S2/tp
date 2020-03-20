package seedu.duke.ui;

import java.util.Scanner;

public class Ui {

    private Scanner in = new Scanner(System.in);
    private static final String GREET_MESSAGE = "\n      HELLO! I'm SHOCO. Your digital shopping list!";
    private static final String SHOCO_LOGO =
              "\n                                         __"
            + "\n                                        /__\\"
            + "\n                                       /"
            + "\n        ______________________________/\n"
            + "       |     |     |     |    |      /\n"
            + "       |____ |____ |____ |____|_____/\n"
            + "       |     |     |     |    |    /\n"
            + "       |____ |____ |____ |____|___/\n"
            + "       |     |     |     |    |  /\n"
            + "       |____ |____ |____ |____|_/\n"
            + "       |____ |____ |____ |___ |/\n"
            + "                               \\"
            + "\n"
            + "                                \\"
            + "\n"
            + "                                /"
            + "\n"
            + "        _______________________/"
            + "\n          /\\              /\\"
            + "\n          \\/              \\/\n";

    /**
     * Prints the welcome message.
     */
    public void greet() {
        System.out.print(GREET_MESSAGE);
        System.out.println(SHOCO_LOGO);
    }

    /**
     * Prints the goodbye message.
     */
    public void bidFarewell() {
        System.out.println("BYE");
    }

    /**
     * Reads a non-empty input from the user.
     *
     * @return non-empty input
     */
    public String readCommand() {
        String input = "";
        while (input.isEmpty()) {
            input = in.nextLine();
            input = input.trim();
        }
        return input;
    }

    public void printline(String str) {
        System.out.println(str);
    }
}
