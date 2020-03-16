package seedu.duke.commands;

import seedu.duke.Profile;
import seedu.duke.exceptions.InvalidFormatException;
import seedu.duke.parser.Parser;
import seedu.duke.ui.UI;

public class ExitCommand extends Command {

    private static final int ARGUMENTS_REQUIRED = 1;
    private double weightGoal;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public ExitCommand(String command) {
        super(command);
    }

    @Override
    public void execute(Profile profile, UI ui) {
        System.out.println("Thank you and see you again soon!");
        ui.setExitStatus(true);
    }
}
