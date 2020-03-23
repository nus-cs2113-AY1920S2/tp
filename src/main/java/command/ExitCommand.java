package command;

import common.Messages;
import seedu.atas.TaskList;
import seedu.atas.Ui;

public class ExitCommand extends Command {
    public static final String EXIT_COMMAND_WORD = "exit";
    public static final String COMMAND_USAGE = "Exit ATAS: exit";

    private static boolean isExit = false;

    /**
     * Executes the Exit command.
     * @param taskList TaskList object that handles adding Task
     * @param ui       Ui object that interacts with user
     * @return CommandResult object with acknowledgment message
     */
    @Override
    public CommandResult execute(TaskList taskList, Ui ui) {
        isExit = true;
        return new CommandResult(Messages.EXIT_MESSAGE);
    }

    /**
     * Checks whether the program should terminate.
     * @return true if the program should exit, false otherwise
     */
    public static Boolean isExit() {
        return isExit;
    }
}
