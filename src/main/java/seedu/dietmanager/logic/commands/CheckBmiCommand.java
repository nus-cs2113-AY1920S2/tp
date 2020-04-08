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

public class CheckBmiCommand extends Command {

    private static final int ARGUMENTS_REQUIRED = 1;
    private String profileName;
    private List<Double> weightRecord = new ArrayList<>();
    private double initialWeight;
    private double currentWeight;
    private double weightDifference;
    private double weightToGoal;
    private double weightGoal;
    private boolean noDescription;

    /**
     * Constructs the Command object.
     *
     * @param command the command prompt entered by the user.
     */

    public CheckBmiCommand(String command, String description)
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

        Result result = getResult(profile);
        return result;
    }

    @Override
    public Result getResult(Profile profile) {
        if (this.noDescription) {
            this.resultString = MessageBank.NO_DESCRIPTION_MESSAGE;
            return new Result(this.resultString);
        }
        this.resultString = MessageBank.BMI_TABLE_MESSAGE + MessageBank.BMI_TABLE_LEGEND + MessageBank.BMI_TABLE;
        return new Result(this.resultString);
    }

}
