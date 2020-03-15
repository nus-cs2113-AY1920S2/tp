package seedu.nuke.command;

/**
 * module level command inherits Command class.
 */
public abstract class ModuleCommand extends Command {

    public abstract CommandResult execute();
}
