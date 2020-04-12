package seedu.command;

import static seedu.common.Constants.TAB;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String HELP_LEGEND = TAB + "Legend:\n" + TAB + TAB + TAB + "[Y]: Task is completed\n"
            + TAB + TAB + TAB +  "[N]: Task is not completed\n\n";

    private String feedback = "";

    public HelpCommand() {
        // default constructor
    }

    public HelpCommand(String feedback) {
        this.feedback += TAB + feedback;
    }


    @Override
    public CommandResult execute() {


        feedback += HELP_LEGEND;

        feedback += TAB + "Command List:\n\n";

        feedback += TAB + TAB + AddCommand.COMMAND_INFO + System.lineSeparator();
        feedback += TAB + TAB + "Usage: " + AddCommand.COMMAND_USAGE + System.lineSeparator();
        feedback += System.lineSeparator();

        feedback += TAB + TAB + EditCommand.COMMAND_INFO + System.lineSeparator();
        feedback += TAB + TAB + "Usage: " + EditCommand.COMMAND_USAGE + System.lineSeparator();
        feedback += System.lineSeparator();

        feedback += TAB + TAB + ListCommand.COMMAND_INFO + System.lineSeparator();
        feedback += TAB + TAB + "Usage: " + System.lineSeparator() + TAB + TAB + TAB
            + ListCommand.COMMAND_USAGE + System.lineSeparator();
        feedback += System.lineSeparator();

        feedback += TAB + TAB + DeleteCommand.COMMAND_INFO + System.lineSeparator();
        feedback += TAB + TAB + "Usage: "  + System.lineSeparator() + TAB + TAB + TAB
                + DeleteCommand.COMMAND_USAGE + System.lineSeparator();
        feedback += System.lineSeparator();

        feedback += TAB + TAB + DoneCommand.COMMAND_INFO + System.lineSeparator();
        feedback += TAB + TAB + "Usage: " + DoneCommand.COMMAND_USAGE + System.lineSeparator();
        feedback += System.lineSeparator();

        feedback += TAB + TAB + FindCommand.COMMAND_INFO + System.lineSeparator();
        feedback += TAB + TAB + "Usage: " + FindCommand.COMMAND_USAGE + System.lineSeparator();
        feedback += System.lineSeparator();

        feedback += TAB + TAB + CalendarCommand.COMMAND_INFO + System.lineSeparator();
        feedback += TAB + TAB + "Usage: " + CalendarCommand.COMMAND_USAGE + System.lineSeparator();
        feedback += System.lineSeparator();

        feedback += TAB + TAB + ClearCommand.COMMAND_INFO + System.lineSeparator();
        feedback += TAB + TAB + "Usage: " + ClearCommand.COMMAND_USAGE + System.lineSeparator();
        feedback += System.lineSeparator();

        feedback += TAB + TAB + ExitCommand.COMMAND_INFO + System.lineSeparator();
        feedback += TAB + TAB + "Usage: " + ExitCommand.COMMAND_USAGE + System.lineSeparator();
        feedback += System.lineSeparator();

        return new CommandResult(feedback);
    }
}
