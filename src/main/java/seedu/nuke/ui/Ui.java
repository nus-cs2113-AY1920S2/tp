package seedu.nuke.ui;

import seedu.nuke.command.addCommand.AddModuleCommand;
import seedu.nuke.command.addCommand.AddTaskCommand;
import seedu.nuke.command.ChangeDirectoryCommand;
import seedu.nuke.command.listCommand.ListAllTasksDeadlineCommand;
import seedu.nuke.command.listCommand.ListModuleTasksDeadlineCommand;
import seedu.nuke.command.CommandResult;
import seedu.nuke.command.deletecommand.DeleteModuleCommand;
import seedu.nuke.command.deleteCommand.DeleteTaskCommand;
import seedu.nuke.command.editcommand.EditDeadlineCommand;
import seedu.nuke.command.ExitCommand;
import seedu.nuke.command.HelpCommand;
import seedu.nuke.command.listCommand.ListModuleCommand;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String LS = System.lineSeparator();
    public static final ArrayList<String> commands = new ArrayList<>();

    private final Scanner in;
    private final PrintStream out;


    /**
     * Constructs the <code>UI</code>.
     */
    public Ui() {
        in = new Scanner(System.in);
        out = new PrintStream(System.out);
        commands.add(AddModuleCommand.MESSAGE_USAGE);
        commands.add(AddTaskCommand.MESSAGE_USAGE);
        commands.add(ChangeDirectoryCommand.MESSAGE_USAGE);
        commands.add(ListAllTasksDeadlineCommand.MESSAGE_USAGE);
        commands.add(ListModuleTasksDeadlineCommand.MESSAGE_USAGE);
        commands.add(DeleteModuleCommand.MESSAGE_USAGE);
        commands.add(DeleteTaskCommand.MESSAGE_USAGE);
        commands.add(EditDeadlineCommand.MESSAGE_DONE);
        commands.add(ListModuleCommand.MESSAGE_USAGE);
        //commands.add(UndoCommand.MESSAGE_USAGE);
        commands.add(HelpCommand.MESSAGE_USAGE);
        commands.add(ExitCommand.MESSAGE_USAGE);
    }

    /**
     * Reads the user input from the command line.
     *
     * @return The input given by the user
     */
    public String getInput() {
        return in.nextLine().trim();
    }

    /**
     * Shows the result message after the command given by the user input is executed.
     * <br>
     * Also, shows the <b>Task List</b> or <i>Search List</i> if indicated in <code>result</code>.
     *
     * @param result The result after executing a command given by the user input
     */
    public void showResult(CommandResult result) {
        out.println(result.getFeedbackToUser().replace("\n", LS));
        if (result.isShowTasks()) {
            printShownList(result.getShownList());
        }
    }

    /**
     * Shows a message to the user.
     *
     * @param message Message to be shown
     */
    public void showSystemMessage(String message) {
        out.println(message.replace("\n", LS));
    }

    /**
     * print the Strings in an ArrayList to the user.
     *
     * @param shownList an ArrayList of Strings to be shown to the user
     */
    public void printShownList(ArrayList<String> shownList) {
        final String divider = String.format("%s%s%s", "+", "-".repeat(100), "+");
        System.out.println(divider);

        for (String str : shownList) {
            System.out.println(str);
        }

        System.out.println(divider + "\n");

    }

}
