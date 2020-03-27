package command;

import seedu.atas.TaskList;
import seedu.atas.Ui;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_USAGE = "Help Format: help";
    private static int counter;

    /**
     * Create HelpCommand object and initialize counter to 1.
     */
    public HelpCommand() {
        counter = 1;
    }

    private String convertIndexToString() {
        String indexToString = String.format("%3d. ", counter);
        counter += 1;
        return indexToString;
    }

    private String getAllCommandUsage() {
        return "Following is the list of commands available:" + System.lineSeparator()
                + convertIndexToString() + COMMAND_USAGE + System.lineSeparator()
                + convertIndexToString() + AssignmentCommand.COMMAND_USAGE + System.lineSeparator()
                + convertIndexToString() + EventCommand.COMMAND_USAGE + System.lineSeparator()
                + convertIndexToString() + ListCommand.COMMAND_USAGE + System.lineSeparator()
                + convertIndexToString() + DoneCommand.COMMAND_USAGE + System.lineSeparator()
                + convertIndexToString() + EditCommand.COMMAND_USAGE + System.lineSeparator()
                + convertIndexToString() + DeleteCommand.COMMAND_USAGE + System.lineSeparator()
                + convertIndexToString() + ClearCommand.COMMAND_USAGE + System.lineSeparator()
                + convertIndexToString() + RepeatCommand.COMMAND_USAGE + System.lineSeparator()
                + convertIndexToString() + SearchCommand.COMMAND_USAGE + System.lineSeparator()
                + convertIndexToString() + SearchdCommand.COMMAND_USAGE + System.lineSeparator()
                + convertIndexToString() + CalendarCommand.COMMAND_USAGE + System.lineSeparator()
                + convertIndexToString() + ExitCommand.COMMAND_USAGE;
    }

    /**
     * Prints to user the help message.
     * @param taskList TaskList object that handles adding Task
     * @param ui       Ui object that interacts with user
     * @return CommandResult object with acknowledgment message
     */
    @Override
    public CommandResult execute(TaskList taskList, Ui ui) {
        return new CommandResult(getAllCommandUsage());
    }
}
