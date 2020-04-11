package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.logic.Result;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class CheckWeightRecordCommand extends Command {

    private List<Double> weightRecord = new ArrayList<>();
    private double initialWeight;
    private double currentWeight;
    private double weightDifference;
    private double weightToGoal;
    private double weightGoal;
    private boolean isInValidCommand = false;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public CheckWeightRecordCommand(String command)
            throws NumberFormatException {
        super(command);
    }

    @Override
    public Result execute(Profile profile, UI ui) {
        testAssertions(isInValidCommand);
        this.weightGoal = profile.getWeightGoal();
        weightRecord = profile.getWeightRecord();
        ui.showMessage(MessageBank.CHECK_WEIGHT_RECORD_MESSAGE);
        for (int i = 0; i < weightRecord.size(); i++) {
            ui.showMessage(i + 1 + ". " + weightRecord.get(i) + "kg");
        }

        Result result = getResult(profile);
        return result;
    }

    @Override
    public Result getResult(Profile profile) {

        initialWeight = weightRecord.get(0);
        currentWeight = weightRecord.get(weightRecord.size() - 1);
        weightDifference = initialWeight - currentWeight;
        weightToGoal = weightGoal - currentWeight;
        checkWeightStatus();

        return new Result(this.resultString);
    }

    private void checkWeightStatus() {
        if (weightDifference > 0) {
            if (currentWeight == weightGoal) {
                this.resultString = String.format(MessageBank.WEIGHT_LOSS_MESSAGE + System.lineSeparator()
                        + MessageBank.WEIGHT_GOAL_ACHIEVED_MESSAGE, weightDifference, weightToGoal);
            } else {
                this.resultString = String.format(MessageBank.WEIGHT_LOSS_MESSAGE + System.lineSeparator()
                        + MessageBank.WEIGHT_GOAL_NOT_ACHIEVED_MESSAGE, weightDifference, weightToGoal);
            }
        } else if (weightDifference == 0) {
            if (currentWeight == weightGoal) {
                this.resultString = String.format(MessageBank.WEIGHT_NO_CHANGE_MESSAGE + System.lineSeparator()
                        + MessageBank.WEIGHT_GOAL_ACHIEVED_MESSAGE, weightToGoal);
            } else {
                this.resultString = String.format(MessageBank.WEIGHT_NO_CHANGE_MESSAGE + System.lineSeparator()
                        + MessageBank.WEIGHT_GOAL_NOT_ACHIEVED_MESSAGE, weightToGoal);
            }
        } else if (weightDifference < 0) {
            if (currentWeight == weightGoal) {
                this.resultString = String.format(MessageBank.WEIGHT_GAIN_MESSAGE + System.lineSeparator()
                        + MessageBank.WEIGHT_GOAL_ACHIEVED_MESSAGE, abs(weightDifference), weightToGoal);
            } else {
                this.resultString = String.format(MessageBank.WEIGHT_GAIN_MESSAGE + System.lineSeparator()
                        + MessageBank.WEIGHT_GOAL_NOT_ACHIEVED_MESSAGE, abs(weightDifference), weightToGoal);
            }
        }
    }

    public static void testAssertions(boolean isInValidCommand) {
        assert (!isInValidCommand);
    }
}
