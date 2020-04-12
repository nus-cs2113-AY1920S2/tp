package seedu.ui;

import seedu.command.CommandResult;
import java.util.Scanner;
import static seedu.common.Constants.TAB;

public class Ui {

    /** A platform independent line separator. */
    private static final String NEW_LINE = System.lineSeparator();
    public static final String DIVIDER = "__________________________________________________________________________";
    private static final String PROMPT = "What is your command?" + NEW_LINE;
    private  static final  String LOGO =
                TAB + " ______     ______     ______   ______\n"
                + TAB + "/\\  ___\\   /\\  __ \\   /\\  ___\\ /\\  ___\\\n"
                + TAB + "\\ \\ \\____  \\ \\  __ \\  \\ \\  __\\ \\ \\___  \\\n"
                + TAB + " \\ \\_____\\  \\ \\_\\ \\_\\  \\ \\_\\    \\/\\_____\\\n"
                + TAB + "  \\/_____/   \\/_/\\/_/   \\/_/     \\/_____/\n";
    private static final String MESSAGE_INTRO = NEW_LINE + TAB + "Welcome to CAFS! Type \"help\" at any time if "
            + "you are lost";

    private Scanner input = new Scanner(System.in);



    /**
     * Method used to print all messages back to user.
     * Meant to improve performance by reducing number of system calls used.
     *
     * @param messages  Variable length argument to display to users
     */
    public void showUserMessage(String... messages) {

        System.out.println();

        for (String message : messages) {
            System.out.print(message);
        }

        System.out.println(NEW_LINE + DIVIDER + NEW_LINE);
    }

    public void showWelcome() {
        showUserMessage(LOGO + MESSAGE_INTRO);
    }

    /**
     * Prompts user for input.
     *
     * @return String containing user command for parsing
     */
    public String readCommand() {
        System.out.print(PROMPT);
        String userCommand = input.nextLine();
        assert userCommand.length() > 0 : "Command input should contain at least one word";
        return userCommand.trim();
    }

    /**
     * Displays all category.
     *
     * @param categories array of categories.
     */
    public void showAllCategory(String[] categories) {
        System.out.println(NEW_LINE + TAB + "Current categories: ");
        for (String category : categories) {
            System.out.println(TAB + TAB + " >>>" + category);
        }
    }

    public void showResultToUser(CommandResult result) {
        showUserMessage(result.getFeedback());
    }

    /** Displays the error message to user.
     *
     * @param error the error message.
     */
    public void showError(String error) {
        showUserMessage(error);
    }
}
