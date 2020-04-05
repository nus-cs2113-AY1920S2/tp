package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.logic.parser.Parser;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

public class SetWeightGoalCommand extends Command {

    private static final int ARGUMENTS_REQUIRED = 1;
    private double weightGoal;
    private boolean noDescription;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public SetWeightGoalCommand(String command, String description)
            throws InvalidFormatException, NumberFormatException {
        super(command);
        this.noDescription = false;

        try {
            String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);
            this.weightGoal = Double.parseDouble(descriptionArray[0]);
        } catch (NullPointerException e) {
            this.noDescription = true;
        }
    }

    @Override
    public void execute(Profile profile, UI ui) {
        if (!this.noDescription) {
            profile.setWeightGoal(this.weightGoal);
        }
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        if (!this.noDescription) {
            this.result = MessageBank.WEIGHT_GOAL_CHANGE_MESSAGE + String.format("%.2f.", profile.getWeightGoal());
        } else {
            this.result = MessageBank.NO_DESCRIPTION_MESSAGE;
        }
    }
}
