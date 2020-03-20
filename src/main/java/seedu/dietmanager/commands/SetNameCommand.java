package seedu.dietmanager.commands;

import seedu.dietmanager.Profile;
import seedu.dietmanager.exceptions.InvalidFormatException;
import seedu.dietmanager.parser.Parser;
import seedu.dietmanager.ui.MessageBank;
import seedu.dietmanager.ui.UI;

public class SetNameCommand extends Command {

    private static final int ARGUMENTS_REQUIRED = 1;
    private String name;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public SetNameCommand(String command, String description) throws InvalidFormatException {
        super(command);
        String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);
        this.name = descriptionArray[0];
    }

    @Override
    public void execute(Profile profile, UI ui) {
        profile.setName(this.name);
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        this.result = MessageBank.NAME_CHANGE_MESSAGE + profile.getName() + ".";
    }
}
