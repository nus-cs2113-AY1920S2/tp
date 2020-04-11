package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.commons.core.Weekday;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.logic.Result;
import seedu.dietmanager.logic.parser.DescriptionParser;
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
            String[] descriptionArray = DescriptionParser.parseDescription(description, ARGUMENTS_REQUIRED);
            this.date = descriptionArray[0].trim().toUpperCase();
            this.activityLevel = descriptionArray[1].toLowerCase();
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
    public Result execute(Profile profile, UI ui) {
        testAssertions(isInValidCommand);
        if (!profile.isProfileExist()) {
            noProfileFound = true;
            getResult(profile);
            Result result = getResult(profile);
            return result;
        } else if (noDescription | isInvalidDate) {
            getResult(profile);
            Result result = getResult(profile);
            return result;
        }

        getRecommendedCaloriesIntake(profile, activityLevel);

        Result result = getResult(profile);
        return result;
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
        case "male":
            basalMetabolicRate = 10 * profile.getWeight() + 6.25 * profile.getHeight() - 5 * profile.getAge() + 5;
            break;
        case "female":
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
            isInValidCommand = true;
        }
        return caloriesRequired;
    }

    @Override
    public Result getResult(Profile profile) {
        if (noDescription) {
            this.resultString = MessageBank.NO_DESCRIPTION_MESSAGE;
        } else if (noProfileFound) {
            this.resultString = MessageBank.INVALID_PROFILE_MESSAGE;
        } else if (isInvalidDate) {
            this.resultString = MessageBank.INVALID_DATE_MESSAGE;
        } else if (!isInValidCommand) {
            DailyFoodRecord record = profile.getRecordOfDay(date);
            this.resultString = String.format("Calories Intake and Requirement for %s:", date) + System.lineSeparator()
                    + record.showDailyCalories()
                    + String.format("Calories requirement for %s activity level: ", activityLevel)
                    + String.format("%.2f", caloriesRequired) + "cal." + System.lineSeparator();
            if (record.getDailyCalories().isPresent()) {
                double caloriesIntake = record.getDailyCalories().get();
                if (profile.getWeight() < profile.getWeightGoal() && caloriesIntake >= caloriesRequired) {
                    this.resultString = this.resultString + MessageBank.SUFFICIENT_CALORIES_MESSAGE;
                }
                if (profile.getWeight() < profile.getWeightGoal() && caloriesIntake < caloriesRequired) {
                    this.resultString = this.resultString + MessageBank.INSUFFICIENT_CALORIES_MESSAGE;
                }
                if (profile.getWeight() >= profile.getWeightGoal() && caloriesIntake > caloriesRequired) {
                    this.resultString = this.resultString + MessageBank.EXCESS_CALORIES_MESSAGE;
                }
                if (profile.getWeight() >= profile.getWeightGoal() && caloriesIntake <= caloriesRequired) {
                    this.resultString = this.resultString + MessageBank.SUFFICIENT_CALORIES_MESSAGE;
                }
            }
        } else {
            this.resultString = MessageBank.INVALID_CALORIES_REQUIREMENT_ERROR;
        }

        return new Result(this.resultString);
    }

    public static void testAssertions(boolean isInValidCommand) {
        assert (!isInValidCommand);
    }
}
