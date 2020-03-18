package seedu.nuke.command.addCommand;

import seedu.nuke.command.Command;
import seedu.nuke.command.CommandResult;
import seedu.nuke.directory.Directory;
import seedu.nuke.directory.Task;

import java.util.regex.Pattern;

import static seedu.nuke.util.Message.MESSAGE_TAG_ADDED;

public class AddTagCommand extends Command {

    public static final String COMMAND_WORD = "add-tag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " task description";
    public static final Pattern[] REGEX_FORMATS = {
            Pattern.compile("([a][d][d])"),
            Pattern.compile("(\\s)(\\-)"),
            Pattern.compile("([t][a][g])")
    };
    private final String info;

    public AddTagCommand(String info) {
        this.info = info;
    }

    @Override
    public CommandResult execute() {
        Task task = (Task) Directory.getCurrentLevel();
        task.setTag(info);
        return new CommandResult(MESSAGE_TAG_ADDED);
    }
}
