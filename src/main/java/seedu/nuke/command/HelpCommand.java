package seedu.nuke.command;

import seedu.nuke.command.addcommand.AddCategoryCommand;
import seedu.nuke.command.addcommand.AddFileCommand;
import seedu.nuke.command.addcommand.AddModuleCommand;
import seedu.nuke.command.addcommand.AddTagCommand;
import seedu.nuke.command.addcommand.AddTaskCommand;
import seedu.nuke.command.editcommand.EditCategoryCommand;
import seedu.nuke.command.editcommand.EditFileCommand;
import seedu.nuke.command.editcommand.EditModuleCommand;
import seedu.nuke.command.editcommand.EditTaskCommand;
import seedu.nuke.command.editcommand.MarkAsDoneCommand;
import seedu.nuke.command.filtercommand.deletecommand.DeleteCategoryCommand;
import seedu.nuke.command.filtercommand.deletecommand.DeleteFileCommand;
import seedu.nuke.command.filtercommand.deletecommand.DeleteModuleCommand;
import seedu.nuke.command.filtercommand.deletecommand.DeleteTaskCommand;
import seedu.nuke.command.filtercommand.listcommand.ListCategoryCommand;
import seedu.nuke.command.filtercommand.listcommand.ListFileCommand;
import seedu.nuke.command.filtercommand.listcommand.ListModuleCommand;
import seedu.nuke.command.filtercommand.listcommand.ListTaskCommand;
import seedu.nuke.command.filtercommand.listcommand.ListTaskSortedCommand;
import seedu.nuke.command.misc.ChangeDirectoryCommand;
import seedu.nuke.command.misc.InfoCommand;
import seedu.nuke.command.misc.OpenFileCommand;
import seedu.nuke.command.misc.RedoCommand;
import seedu.nuke.command.misc.UndoCommand;

import java.util.ArrayList;
import java.util.Arrays;

import static seedu.nuke.util.Message.MESSAGE_HELP;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String FORMAT = COMMAND_WORD;
    public static final String MESSAGE_USAGE = String.format(
            "%s - Show a help guide\n"
            + "Format: %s\n",
            COMMAND_WORD, FORMAT);
    private static final ArrayList<String> commands = new ArrayList<>(Arrays.asList(
            AddModuleCommand.MESSAGE_USAGE,
            AddCategoryCommand.MESSAGE_USAGE,
            AddTaskCommand.MESSAGE_USAGE,
            AddFileCommand.MESSAGE_USAGE,
            AddTagCommand.MESSAGE_USAGE,


            DeleteModuleCommand.MESSAGE_USAGE,
            DeleteCategoryCommand.MESSAGE_USAGE,
            DeleteTaskCommand.MESSAGE_USAGE,
            DeleteFileCommand.MESSAGE_USAGE,

            ListModuleCommand.MESSAGE_USAGE,
            ListCategoryCommand.MESSAGE_USAGE,
            ListTaskCommand.MESSAGE_USAGE,
            ListFileCommand.MESSAGE_USAGE,
            // ListModuleTask.MESSAGE_USAGE,
            ListTaskSortedCommand.MESSAGE_USAGE,

            EditModuleCommand.MESSAGE_USAGE,
            EditCategoryCommand.MESSAGE_USAGE,
            EditTaskCommand.MESSAGE_USAGE,
            EditFileCommand.MESSAGE_USAGE,
            MarkAsDoneCommand.MESSAGE_USAGE,

            ChangeDirectoryCommand.MESSAGE_USAGE,
            OpenFileCommand.MESSAGE_USAGE,
            InfoCommand.MESSAGE_USAGE,
            UndoCommand.MESSAGE_USAGE,
            RedoCommand.MESSAGE_USAGE,
            HelpCommand.MESSAGE_USAGE,
            ExitCommand.MESSAGE_USAGE
    ));


    public HelpCommand() {
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_HELP, commands);
    }
}
