package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.logic.Result;
import seedu.dietmanager.logic.parser.DescriptionParser;
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
            String[] descriptionArray = DescriptionParser.parseDescription(description, ARGUMENTS_REQUIRED);
            this.index = Integer.parseInt(descriptionArray[0]) - 1;
        } catch (NullPointerException e) {
            this.noDescription = true;
        }
    }

    @Override
    public Result execute(Profile profile, UI ui) {
        if (!this.noDescription) {
            weightDeleted = profile.getWeightRecord().get(index);
            profile.getWeightRecord().remove(index);
        }
        Result result = getResult(profile);
        return result;
    }

    @Override
    public Result getResult(Profile profile) {
        if (!this.noDescription) {
            this.resultString = "Weight Record: " + weightDeleted + "kg " + MessageBank.WEIGHT_DELETED_MESSAGE;
        } else {
            this.resultString = MessageBank.NO_DESCRIPTION_MESSAGE;
        }
        return new Result(this.resultString);
    }

}
