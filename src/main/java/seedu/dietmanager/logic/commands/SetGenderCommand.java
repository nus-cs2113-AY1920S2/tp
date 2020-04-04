package seedu.dietmanager.logic.commands;

import seedu.dietmanager.model.Profile;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.logic.parser.Parser;
import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.ui.UI;

public class SetGenderCommand extends Command {

    private static final int ARGUMENTS_REQUIRED = 1;
    private String gender;
    private boolean noDescription;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public SetGenderCommand(String command, String description) throws InvalidFormatException {
        super(command);
        this.noDescription = false;

        try {
            String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);
            this.gender = descriptionArray[0];
        } catch (NullPointerException e) {
            this.noDescription = true;
        }
    }

    @Override
    public void execute(Profile profile, UI ui) {
        if (!this.noDescription) {
            profile.setGender(this.gender);
        }
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        if (!this.noDescription) {
            this.result = MessageBank.GENDER_CHANGE_MESSAGE + profile.getGender() + ".";
        } else {
            this.result = MessageBank.NO_DESCRIPTION_MESSAGE;
        }
    }
}
