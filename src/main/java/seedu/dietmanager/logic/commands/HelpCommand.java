package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.logic.Result;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

public class HelpCommand extends Command {
    private boolean isValidCommand;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public HelpCommand(String command) {
        super(command);
    }

    @Override
    public Result execute(Profile profile, UI ui) {
        Result result = getResult(profile);
        return result;
    }

    @Override
    public Result getResult(Profile profile) {
        this.resultString = MessageBank.FUNCTION_LIST;
        return new Result(this.resultString);
    }
}
