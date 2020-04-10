package seedu.dietmanager.logic.commands.foodrecord;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.logic.Result;
import seedu.dietmanager.logic.commands.Command;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

public class ClearFoodRecordCommand extends Command {
    private static final int ARGUMENTS_REQUIRED = 0;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */
    public ClearFoodRecordCommand(String command) {
        super(command);
    }

    @Override
    public Result execute(Profile profile, UI ui) {
        profile.clearAllFoodRecords();
        Result result = getResult(profile);
        return result;
    }

    @Override
    public Result getResult(Profile profile) {
        this.resultString = MessageBank.RECORDS_CLEARED_MESSAGE;
        return new Result(this.resultString);
    }
}
