package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.commons.exceptions.InvalidWeightException;
import seedu.dietmanager.logic.Result;
import seedu.dietmanager.logic.parser.WeightParser;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

public class SetWeightCommand extends Command {

    private double weight;
    private boolean isValidCommand;
    private boolean isValidProfile;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public SetWeightCommand(String command, String description) {
        super(command);
        this.isValidCommand = true;
        try {
            this.weight = WeightParser.parseWeight(description);
        } catch (InvalidWeightException e) {
            this.isValidCommand = false;
        }
    }

    @Override
    public Result execute(Profile profile, UI ui) {
        this.isValidProfile = profile.isProfileExist();
        if (!this.isValidProfile) {
            this.isValidCommand = false;
        }
        if (this.isValidCommand) {
            profile.setWeight(weight);
        }
        Result result = getResult(profile);
        return result;
    }

    @Override
    public Result getResult(Profile profile) {
        if (!this.isValidProfile) {
            this.resultString = MessageBank.INVALID_PROFILE_MESSAGE;
        } else if (this.isValidCommand) {
            this.resultString = MessageBank.WEIGHT_CHANGE_MESSAGE + String.format("%.2f.", profile.getWeight());
        } else {
            this.resultString = MessageBank.NO_DESCRIPTION_MESSAGE;
        }
        return new Result(this.resultString);
    }
}
