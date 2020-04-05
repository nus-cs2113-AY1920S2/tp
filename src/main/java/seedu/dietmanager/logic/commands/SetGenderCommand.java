package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.commons.exceptions.InvalidGenderException;
import seedu.dietmanager.logic.parser.GenderParser;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

public class SetGenderCommand extends Command {

    private String gender;
    private boolean isValidCommand;
    private boolean isValidProfile;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public SetGenderCommand(String command, String description) {
        super(command);
        this.isValidCommand = true;

        try {
            this.gender = GenderParser.parseGender(description);
        } catch (InvalidGenderException e) {
            this.isValidCommand = false;
        }
    }

    @Override
    public void execute(Profile profile, UI ui) {
        this.isValidProfile = profile.isProfileExist();
        if (!this.isValidProfile) {
            this.isValidCommand = false;
        }
        if (this.isValidCommand) {
            profile.setGender(this.gender);
        }
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        if (!this.isValidProfile) {
            this.result = MessageBank.INVALID_PROFILE_MESSAGE;
        } else if (this.isValidCommand) {
            this.result = MessageBank.GENDER_CHANGE_MESSAGE + profile.getGender() + ".";
        } else {
            this.result = MessageBank.NO_DESCRIPTION_MESSAGE;
        }
    }
}
