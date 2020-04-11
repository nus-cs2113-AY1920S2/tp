package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.logic.Result;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;

public class CheckBmiCommand extends Command {

    private static final int ARGUMENTS_REQUIRED = 1;
    private String profileName;
    private List<Double> weightRecord = new ArrayList<>();
    private double bmi;
    private double currentWeight;
    private double currentHeight;
    private boolean noDescription;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public CheckBmiCommand(String command) throws NumberFormatException {
        super(command);
    }

    @Override
    public Result execute(Profile profile, UI ui) {
        this.currentHeight = profile.getHeight();
        this.currentWeight = profile.getWeightRecord().get(profile.getWeightRecord().size() - 1);
        bmi = currentWeight / pow((currentHeight / 100), 2);
        ui.showMessage(String.format(MessageBank.USER_BMI_MESSAGE, bmi));
        Result result = getResult(profile);
        return result;
    }

    @Override
    public Result getResult(Profile profile) {
        this.resultString = MessageBank.BMI_TABLE_MESSAGE + MessageBank.BMI_TABLE_LEGEND + MessageBank.BMI_TABLE;
        return new Result(this.resultString);
    }

}
