package seedu.dietmanager.commands;

import seedu.dietmanager.Profile;
import seedu.dietmanager.exceptions.InvalidFormatException;
import seedu.dietmanager.parser.Parser;
import seedu.dietmanager.ui.MessageBank;
import seedu.dietmanager.ui.UI;

public class SetGenderCommand extends Command {

    private static final int ARGUMENTS_REQUIRED = 1;
    private String gender;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public SetGenderCommand(String command, String description) throws InvalidFormatException {
        super(command);
        String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);
        this.gender = descriptionArray[0];
    }

    @Override
    public void execute(Profile profile, UI ui) {
        profile.setGender(this.gender);
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        this.result = MessageBank.GENDER_CHANGE_MESSAGE + profile.getGender() + ".";
    }
}
