package seedu.nuke.command;

import seedu.nuke.directory.DirectoryLevel;

import static seedu.nuke.ui.Ui.commands;
import static seedu.nuke.util.Message.MESSAGE_HELP;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD;

    public HelpCommand() {
    }

    @Override
    public CommandResult execute() {


        return new CommandResult(MESSAGE_HELP + commands);
    }
}
