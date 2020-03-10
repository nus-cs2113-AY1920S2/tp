package seedu.nuke.command;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD;

    public HelpCommand() {
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(
                AddModuleCommand.MESSAGE_USAGE
                        + "\n" + DeleteModuleCommand.MESSAGE_USAGE
                        + "\n" + ListModuleCommand.MESSAGE_USAGE
                        + "\n" + CheckAllTasksDeadlineCommand.MESSAGE_USAGE
                        + "\n" + HelpCommand.MESSAGE_USAGE
                        + "\n" + ExitCommand.MESSAGE_USAGE
        );
    }
}
