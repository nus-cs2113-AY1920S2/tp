package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.commons.exceptions.InvalidAgeException;
import seedu.dietmanager.logic.Result;
import seedu.dietmanager.logic.parser.AgeParser;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

public class SetAgeCommand extends Command {

    private int age;
    private boolean isValidCommand;
    private boolean isValidProfile;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public SetAgeCommand(String command, String description) {
        super(command);
        this.isValidCommand = true;
        try {
            this.age = AgeParser.parseAge(description);
        } catch (InvalidAgeException e) {
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
            profile.setAge(this.age);
        }
        Result result = getResult(profile);
        return result;
    }

    @Override
    public Result getResult(Profile profile) {
        if (!this.isValidProfile) {
            this.resultString = MessageBank.INVALID_PROFILE_MESSAGE;
        } else if (this.isValidCommand) {
            this.resultString = MessageBank.AGE_CHANGE_MESSAGE + profile.getAge() + ".";
        } else {
            this.resultString = MessageBank.INVALID_AGE_MESSAGE;
        }
        return new Result(this.resultString);
    }
}
