package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.commons.core.Weekday;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.logic.parser.CommandParser;
import seedu.dietmanager.model.DailyFoodRecord;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

public class CheckRequiredCaloriesCommand extends Command {
    private static final int ARGUMENTS_REQUIRED = 2;
    private String date;
    private String activityLevel;
    private double caloriesRequired;

    private boolean isInvalidDate = false;
    private boolean isInValidCommand = false;
    private boolean noDescription = false;
    private boolean noProfileFound = false;

    /**
     * Constructs the Command object.
     *
     * @param command     The command prompt entered by the user.
     * @param description The description of the command.
     * @throws InvalidFormatException If user input incorrect format for the command.
     */
    public CheckRequiredCaloriesCommand(String command, String description) throws InvalidFormatException {
        super(command);

        try {
            String[] descriptionArray = CommandParser.parseDescription(description, ARGUMENTS_REQUIRED);
            this.date = descriptionArray[0].trim().toUpperCase();
            this.activityLevel = descriptionArray[1];
            Weekday.valueOf(this.date);
        } catch (NullPointerException e) {
            noDescription = true;
        } catch (IllegalArgumentException e) {
            isInvalidDate = true;
        }
    }

    public CheckRequiredCaloriesCommand(String command) {
        super(command);
    }

    @Override
    public void execute(Profile profile, UI ui) {
        if (!profile.isProfileExist()) {
            noProfileFound = true;
            saveResult(profile);
            return;
        } else if (noDescription | isInvalidDate) {
            saveResult(profile);
            return;
        }

        getRecommendedCaloriesIntake(profile, activityLevel);

        saveResult(profile);
    }

    /**
     * Returns how much calories the profile need to consume in a day.
     *
     * @param profile       The user profile.
     * @param activityLevel the activity level of the user.
     * @return calories required to consume in a day based on user's personal info.
     */
    public double getRecommendedCaloriesIntake(Profile profile, String activityLevel) {
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
            isInValidCommand = true;
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
            isInValidCommand = false;
        }
        return caloriesRequired;
    }

    @Override
    public void saveResult(Profile profile) {
        if (noDescription) {
            this.result = MessageBank.NO_DESCRIPTION_MESSAGE;
        } else if (noProfileFound) {
            this.result = MessageBank.PROFILE_NOT_FOUND_MESSAGE;
        } else if (isInvalidDate) {
            this.result = MessageBank.INVALID_DATE_MESSAGE;
        } else if (!isInValidCommand) {
            DailyFoodRecord record = profile.getRecordOfDay(date);
            this.result = String.format("Calories Intake and Requirement for %s:", date) + System.lineSeparator()
                    + record.showDailyCalories()
                    + String.format("Calories requirement for %s activity level: ", activityLevel)
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
