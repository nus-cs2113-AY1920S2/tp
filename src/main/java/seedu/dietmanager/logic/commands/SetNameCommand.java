package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.commons.exceptions.InvalidNameException;
import seedu.dietmanager.logic.Result;
import seedu.dietmanager.logic.parser.NameParser;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

public class SetNameCommand extends Command {

    private String name;
    private boolean isValidCommand;
    private boolean isValidProfile;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public SetNameCommand(String command, String description) {
        super(command);
        this.isValidCommand = true;
        try {
            this.name = NameParser.parseName(description);
        } catch (InvalidNameException e) {
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
            profile.setName(this.name);
        }
        Result result = getResult(profile);
        return result;
    }

    @Override
    public Result getResult(Profile profile) {
        if (!this.isValidProfile) {
            this.resultString = MessageBank.INVALID_PROFILE_MESSAGE;
        } else if (this.isValidCommand) {
            this.resultString = MessageBank.NAME_CHANGE_MESSAGE + profile.getName() + ".";
        } else {
            this.resultString = MessageBank.NO_DESCRIPTION_MESSAGE;
        }
        return new Result(this.resultString);
    }
}
