package seedu.dietmanager.logic.commands;

import seedu.dietmanager.model.Profile;
import seedu.dietmanager.commons.core.MessageBank;
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
    public void execute(Profile profile, UI ui) {
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        if (profile.isProfileExist()) {
            this.result = MessageBank.WELCOME_MESSAGE + System.lineSeparator()
                    + String.format("Age:          %d years old", profile.getAge()) + System.lineSeparator()
                    + String.format("Gender:       %s", profile.getGender()) + System.lineSeparator()
                    + String.format("Height:       %.2f centimetres", profile.getHeight()) + System.lineSeparator()
                    + String.format("Weight        %.2f kilograms", profile.getWeight()) + System.lineSeparator()
                    + String.format("Weight Goal:  %.2f kilograms", profile.getWeightGoal());
        } else {
            this.result = MessageBank.PROFILE_NOT_FOUND_MESSAGE;
        }
    }
}
