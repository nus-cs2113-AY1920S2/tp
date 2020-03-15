package seedu.duke.commands;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "BYE";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program."
            + System.lineSeparator() + "|| Parameters: BYE"
            + System.lineSeparator() + "|| Example: BYE" + System.lineSeparator();
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = System.lineSeparator() + "Exiting SHOCO as requested ...";

    public void execute() {
        isExit = true;
        feedbackToUser = MESSAGE_EXIT_ACKNOWLEDGEMENT;
    }

}
