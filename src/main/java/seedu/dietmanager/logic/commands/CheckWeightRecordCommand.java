package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.logic.Result;
import seedu.dietmanager.logic.parser.CommandParser;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class CheckWeightRecordCommand extends Command {

    private static final int ARGUMENTS_REQUIRED = 1;
    private String profileName;
    private List<Double> weightRecord = new ArrayList<>();
    private double initialWeight;
    private double currentWeight;
    private double weightDifference;
    private boolean noDescription;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public CheckWeightRecordCommand(String command, String description)
            throws InvalidFormatException, NumberFormatException {
        super(command);
        this.noDescription = false;

        try {
            String[] descriptionArray = CommandParser.parseDescription(description, ARGUMENTS_REQUIRED);
            this.profileName = descriptionArray[0];
        } catch (NullPointerException e) {
            this.noDescription = true;
        }
    }

    @Override
    public Result execute(Profile profile, UI ui) {
        if (!this.noDescription) {
            weightRecord = profile.getWeightRecord();
            ui.showCommandMessage(MessageBank.CHECK_WEIGHT_RECORD_MESSAGE); //Wil change this part later on
            for (int i = 0; i < weightRecord.size(); i++) {
                ui.showCommandMessage(i + 1 + ". " + weightRecord.get(i) + "kg ");
            }
        }

        Result result = getResult(profile);
        return result;
    }

    @Override
    public Result getResult(Profile profile) {
        if (this.noDescription) {
            this.resultString = MessageBank.NO_DESCRIPTION_MESSAGE;
            return new Result(this.resultString);
        }

        initialWeight = weightRecord.get(0);
        currentWeight = weightRecord.get(weightRecord.size() - 1);
        weightDifference = initialWeight - currentWeight;
        if (weightDifference > 0) {
            this.resultString = String.format(MessageBank.WEIGHT_LOSS_MESSAGE, weightDifference);
        } else if (weightDifference == 0) {
            this.resultString = MessageBank.WEIGHT_NO_CHANGE_MESSAGE;
        } else {
            this.resultString = String.format(MessageBank.WEIGHT_GAIN_MESSAGE, abs(weightDifference));
        }
        return new Result(this.resultString);
    }

}
