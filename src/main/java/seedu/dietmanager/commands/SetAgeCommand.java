package seedu.dietmanager.commands;

import seedu.dietmanager.Profile;
import seedu.dietmanager.exceptions.InvalidFormatException;
import seedu.dietmanager.parser.Parser;
import seedu.dietmanager.ui.MessageBank;
import seedu.dietmanager.ui.UI;

public class SetAgeCommand extends Command {

    private static final int ARGUMENTS_REQUIRED = 1;
    private int age;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public SetAgeCommand(String command, String description) throws InvalidFormatException, NumberFormatException {
        super(command);
        String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);
        this.age = Integer.parseInt(descriptionArray[0]);
    }

    @Override
    public void execute(Profile profile, UI ui) {
        profile.setAge(this.age);
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        this.result = MessageBank.AGE_CHANGE_MESSAGE + profile.getAge() + ".";
    }
}
