package seedu.dietmanager.commands;

import seedu.dietmanager.Profile;
import seedu.dietmanager.exceptions.InvalidFormatException;
import seedu.dietmanager.parser.Parser;
import seedu.dietmanager.ui.MessageBank;
import seedu.dietmanager.ui.UI;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class CheckWeightProgressCommand extends Command {

    private static final int ARGUMENTS_REQUIRED = 1;
    private String profileName;
    private ArrayList<Double> weightRecord = new ArrayList<>();
    private ArrayList<String> weightRecordDays = new ArrayList<>();
    private double initialWeight;
    private double currentWeight;
    private double weightDifference;


    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public CheckWeightProgressCommand(String command, String description)
            throws InvalidFormatException, NumberFormatException {
        super(command);
        String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);
        this.profileName = descriptionArray[0];
    }

    @Override
    public void execute(Profile profile, UI ui) {
        weightRecord = profile.getWeightProgress();
        weightRecordDays = profile.getWeightProgressDays();
        ui.showCommandMessage(MessageBank.CHECK_WEIGHT_RECORD_MESSAGE); //Wil change this part later on
        for (int i = 0; i < weightRecord.size(); i++) {
            ui.showCommandMessage(i + 1 + ". " + weightRecord.get(i) + "kg " + weightRecordDays.get(i));
        }

        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        initialWeight = weightRecord.get(0);
        currentWeight = weightRecord.get(weightRecord.size() - 1);
        weightDifference = initialWeight - currentWeight;
        if (weightDifference > 0) {
            this.result = String.format(MessageBank.WEIGHT_LOSS_MESSAGE, weightDifference);
        } else if (weightDifference == 0) {
            this.result = MessageBank.WEIGHT_NO_CHANGE_MESSAGE;
        } else {
            this.result = String.format(MessageBank.WEIGHT_GAIN_MESSAGE, abs(weightDifference));
        }
    }

}
