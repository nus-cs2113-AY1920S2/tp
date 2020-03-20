package seedu.dietmanager.commands;

import seedu.dietmanager.Profile;
import seedu.dietmanager.exceptions.InvalidFormatException;
import seedu.dietmanager.parser.Parser;
import seedu.dietmanager.ui.MessageBank;
import seedu.dietmanager.ui.UI;

public class SetWeightGoalCommand extends Command {

    private static final int ARGUMENTS_REQUIRED = 1;
    private double weightGoal;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public SetWeightGoalCommand(String command, String description)
            throws InvalidFormatException, NumberFormatException {
        super(command);
        String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);
        this.weightGoal = Double.parseDouble(descriptionArray[0]);
    }

    @Override
    public void execute(Profile profile, UI ui) {
        profile.setWeightGoal(this.weightGoal);
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        this.result = MessageBank.WEIGHT_GOAL_CHANGE_MESSAGE + String.format("%.2f.",profile.getWeightGoal());
    }
}
