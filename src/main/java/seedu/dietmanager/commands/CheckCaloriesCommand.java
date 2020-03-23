package seedu.dietmanager.commands;

import seedu.dietmanager.DailyFoodRecord;
import seedu.dietmanager.Profile;
import seedu.dietmanager.exceptions.InvalidFormatException;
import seedu.dietmanager.parser.Parser;
import seedu.dietmanager.ui.MessageBank;
import seedu.dietmanager.ui.UI;

public class CheckCaloriesCommand extends Command {
    private static final int ARGUMENTS_REQUIRED = 2;
    private String date;
    private String activityLevel;
    private double caloriesRequired;
    boolean isValidCommand = true;

    /**
     * Constructs the Command object.
     * @param command The command prompt entered by the user.
     * @param description The description of the command.
     * @throws InvalidFormatException If user input incorrect format for the command.
     */
    public CheckCaloriesCommand(String command, String description) throws InvalidFormatException {
        super(command);
        String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);
        this.date = descriptionArray[0];
        this.activityLevel = descriptionArray[1];
    }

    @Override
    public void execute(Profile profile, UI ui) {
        double basalMetabolicRate;
        switch (profile.getGender()) {
        case "Male":
            basalMetabolicRate = 10 * profile.getWeight() + 6.25 * profile.getHeight() - 5 * profile.getAge() + 5;
            break;
        case "Female":
            basalMetabolicRate = 10 * profile.getWeight() + 6.25 * profile.getHeight() - 5 * profile.getAge() - 161;
            break;
        default:
            basalMetabolicRate = 0;
            isValidCommand = false;
        }
        switch (activityLevel) {
        case "low":
            this.caloriesRequired = basalMetabolicRate * 1.375;
            break;
        case "moderate":
            this.caloriesRequired = basalMetabolicRate * 1.55;
            break;
        case "high":
            this.caloriesRequired = basalMetabolicRate * 1.725;
            break;
        default:
            isValidCommand = false;
        }
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        DailyFoodRecord record = profile.getRecordOfDay(date);
        if (isValidCommand) {
            this.result = String.format("Calories Intake and Requirement for %s:",date) + System.lineSeparator()
                    + record.showDailyCalories()
                    + String.format("Calories requirement for %s activity level: ",activityLevel)
                    + String.format("%.2f", caloriesRequired) + "cal." + System.lineSeparator();
            if (record.getDailyCalories().isPresent()) {
                double caloriesIntake = record.getDailyCalories().get();
                if (profile.getWeight() < profile.getWeightGoal() && caloriesIntake >= caloriesRequired) {
                    this.result = this.result + MessageBank.SUFFICIENT_CALORIES_MESSAGE;
                }
                if (profile.getWeight() < profile.getWeightGoal() && caloriesIntake < caloriesRequired) {
                    this.result = this.result + MessageBank.INSUFFICIENT_CALORIES_MESSAGE;
                }
                if (profile.getWeight() >= profile.getWeightGoal() && caloriesIntake > caloriesRequired) {
                    this.result = this.result + MessageBank.EXCESS_CALORIES_MESSAGE;
                }
                if (profile.getWeight() >= profile.getWeightGoal() && caloriesIntake <= caloriesRequired) {
                    this.result = this.result + MessageBank.SUFFICIENT_CALORIES_MESSAGE;
                }
            }
        } else {
            this.result = MessageBank.INVALID_CALORIES_REQUIREMENT_ERROR;
        }
    }
}
