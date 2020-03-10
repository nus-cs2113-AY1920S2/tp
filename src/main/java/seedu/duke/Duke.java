package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.commands.ExitCommand;
import seedu.duke.data.Budget;
import seedu.duke.data.ShoppingList;
import seedu.duke.parser.Parser;
import java.util.Scanner;

public class Duke {

    /**
      * Main entry-point for the java.duke.Duke application.
      *
     */
    public static Budget myBudget = new Budget();
    private static ShoppingList items = new ShoppingList();


    public static void main(String[] args) {
        new Duke().run();
    }

    /** Runs the program until termination.  */
    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Sets up the required objects, loads up the data from the storage file, and prints the welcome message.
     *
     *
     */
    private void start() {
        System.out.println("HELLO! I'm SHOCO. Your digital shopping list!\n");
    }

    /** Prints the Goodbye message and exits. */
    private void exit() {
        System.out.println("BYE");
        System.exit(0);
    }

    /** Reads the user command and executes it, until the user issues the exit command.  */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = readCommand();
            System.out.println(userCommandText);
            command = new Parser().parseCommand(userCommandText);
            CommandResult result = executeCommand(command);
            System.out.println(result.feedbackToUser);
        } while (!ExitCommand.isExit(command));
    }

    /**Read the input when user type the command.
     *
     * @return input
     */
    public String readCommand() {
        Scanner in = new Scanner(System.in);
        String input = "";
        while (input == "") {
            input = in.nextLine();
            input = input.trim();
        }
        return input;
    }

    /**
     * Executes the command and returns the result.
     *
     * @param command user command
     * @return result of the command
     */
    private CommandResult executeCommand(Command command) {
        try {
            command.setData(items,myBudget);
            CommandResult result = command.execute();
            command.setData(items,myBudget);
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
