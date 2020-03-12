package seedu.duke.commands;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "BYE";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program."
            + System.lineSeparator() + "|| Parameters: BYE"
            + System.lineSeparator() + "|| Example: BYE" + System.lineSeparator();
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = System.lineSeparator() + "Exiting SHOCO as requested ...";

    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT);
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }
}
