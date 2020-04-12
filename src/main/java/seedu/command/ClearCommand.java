package seedu.command;

public class ClearCommand extends Command {


    public static final String COMMAND_WORD = "clc";
    public static final String COMMAND_INFO = COMMAND_WORD + ": clears the terminal output";
    public static final String COMMAND_USAGE = COMMAND_WORD;

    /** Lines to print to clear out the console. */
    private static final int LINES_TO_PRINT = 100;

    @Override
    public CommandResult execute() {

        String feedback = "";

        for (int i = 0; i < LINES_TO_PRINT; i++) {
            feedback += System.lineSeparator();
        }

        return new CommandResult(feedback);
    }

}
