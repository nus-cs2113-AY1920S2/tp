package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.commons.exceptions.InvalidHeightException;
import seedu.dietmanager.logic.parser.HeightParser;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

public class SetHeightCommand extends Command {

    private double height;
    private boolean isValidCommand;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public SetHeightCommand(String command, String description) {
        super(command);
        this.isValidCommand = true;

        try {
            this.height = HeightParser.parseHeight(description);
        } catch (InvalidHeightException e) {
            this.isValidCommand = false;
        }
    }

    @Override
    public void execute(Profile profile, UI ui) {
        if (this.isValidCommand) {
            profile.setHeight(this.height);
        }
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        if (this.isValidCommand) {
            this.result = MessageBank.HEIGHT_CHANGE_MESSAGE + String.format("%.2f.", profile.getHeight());
        } else {
            this.result = MessageBank.NO_DESCRIPTION_MESSAGE;
        }
    }
}
