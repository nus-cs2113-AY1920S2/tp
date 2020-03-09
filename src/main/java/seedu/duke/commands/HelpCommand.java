package seedu.duke.commands;

/**
 * Shows help instructions.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "HELP";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions."
            + System.lineSeparator();


    @Override
    public CommandResult execute() {
        return new CommandResult(HelpCommand.MESSAGE_USAGE);
    }
}