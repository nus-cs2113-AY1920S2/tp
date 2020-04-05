package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.logic.parser.CommandParser;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

public class DeleteWeightCommand extends Command {

    private static final int ARGUMENTS_REQUIRED = 1;
    private double weightDeleted;
    private int index;
    private boolean noDescription;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public DeleteWeightCommand(String command, String description)
            throws InvalidFormatException, NumberFormatException, IndexOutOfBoundsException {
        super(command);
        this.noDescription = false;

        try {
            String[] descriptionArray = CommandParser.parseDescription(description, ARGUMENTS_REQUIRED);
            this.index = Integer.parseInt(descriptionArray[0]) - 1;
        } catch (NullPointerException e) {
            this.noDescription = true;
        }
    }

    @Override
    public void execute(Profile profile, UI ui) {
        if (!this.noDescription) {
            weightDeleted = profile.getWeightRecord().get(index);
            profile.getWeightRecord().remove(index);
        }

        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        if (!this.noDescription) {
            this.result = "Weight Record: " + weightDeleted + "kg " + MessageBank.WEIGHT_DELETED_MESSAGE;
        } else {
            this.result = MessageBank.NO_DESCRIPTION_MESSAGE;
        }
    }

}
