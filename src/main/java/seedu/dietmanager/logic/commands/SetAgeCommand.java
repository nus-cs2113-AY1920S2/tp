package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.commons.exceptions.InvalidAgeException;
import seedu.dietmanager.logic.parser.AgeParser;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

public class SetAgeCommand extends Command {

    private int age;
    private boolean isValidCommand;

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
    public void execute(Profile profile, UI ui) {
        if (this.isValidCommand) {
            profile.setAge(this.age);
        }
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        if (this.isValidCommand) {
            this.result = MessageBank.AGE_CHANGE_MESSAGE + profile.getAge() + ".";
        } else {
            this.result = MessageBank.NO_DESCRIPTION_MESSAGE;
        }
    }
}
