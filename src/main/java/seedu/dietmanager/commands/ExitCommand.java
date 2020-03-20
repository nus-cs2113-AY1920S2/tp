package seedu.dietmanager.commands;

import seedu.dietmanager.Profile;
import seedu.dietmanager.ui.MessageBank;
import seedu.dietmanager.ui.UI;

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
    public void execute(Profile profile,UI ui) {
        ui.setExitStatus(true);
        this.saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        this.result = MessageBank.EXIT_MESSAGE;
    }
}
