package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.logic.Result;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

public class ProfileCommand extends Command {


    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public ProfileCommand(String command) {
        super(command);
    }

    @Override
    public Result execute(Profile profile, UI ui) {
        Result result = getResult(profile);
        return result;
    }

    @Override
    public Result getResult(Profile profile) {
        if (profile.isProfileExist()) {
            this.resultString = MessageBank.SHOW_PROFILE_MESSAGE + System.lineSeparator()
                    + String.format("Name:         %s", profile.getName()) + System.lineSeparator()
                    + String.format("Age:          %d years old", profile.getAge()) + System.lineSeparator()
                    + String.format("Gender:       %s", profile.getGender()) + System.lineSeparator()
                    + String.format("Height:       %.2f centimetres", profile.getHeight()) + System.lineSeparator()
                    + String.format("Weight        %.2f kilograms", profile.getWeight()) + System.lineSeparator()
                    + String.format("Weight Goal:  %.2f kilograms", profile.getWeightGoal());
        } else {
            this.resultString = MessageBank.INVALID_PROFILE_MESSAGE;
        }
        return new Result(this.resultString);
    }
}
