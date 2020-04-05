package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.exceptions.InvalidNameException;
import seedu.dietmanager.logic.parser.NameParser;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.ui.UI;

import javax.naming.Name;

public class SetNameCommand extends Command {

    private String name;
    private boolean isValidCommand;

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
    public void execute(Profile profile, UI ui) {
        if (this.isValidCommand) {
            profile.setName(this.name);
        }
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        if (this.isValidCommand) {
            this.result = MessageBank.NAME_CHANGE_MESSAGE + profile.getName() + ".";
        } else {
            this.result = MessageBank.NO_DESCRIPTION_MESSAGE;
        }
    }
}
