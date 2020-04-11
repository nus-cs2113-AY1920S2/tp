package seedu.techtoday.ui;

import seedu.techtoday.common.Messages;
import seedu.techtoday.objects.Article;
import java.util.ArrayList;
import java.util.Scanner;

//@@author melylopez99
/**
 * Class for User Interface objects and interacting with user commands.
 */
public class Ui {
    /**
     * Returns the trimmed command of the user input.
     * @return String representation of user input trimmed.
     */
    public static String getCommand() {
        Scanner userInput = new Scanner(System.in);
        String userResponse = userInput.nextLine();
        System.out.println(Messages.SPLIT_LINE);
        System.out.println("\n");
        return userResponse.trim();
    }


}
