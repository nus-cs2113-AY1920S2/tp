package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.logic.Result;
import seedu.dietmanager.model.Profile;
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
    public Result execute(Profile profile, UI ui) {
        ui.setExitStatus(true);
        Result result = getResult(profile);
        return result;
    }

    @Override
    public Result getResult(Profile profile) {
        this.resultString = MessageBank.EXIT_COMMAND_MESSAGE;
        return new Result(this.resultString);
    }
}
