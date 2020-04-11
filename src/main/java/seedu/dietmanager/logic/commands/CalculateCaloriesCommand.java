package seedu.dietmanager.logic.commands;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.commons.core.Weekday;
import seedu.dietmanager.commons.exceptions.InvalidFormatException;
import seedu.dietmanager.logic.Result;
import seedu.dietmanager.logic.parser.DescriptionParser;
import seedu.dietmanager.model.DailyFoodRecord;
import seedu.dietmanager.model.Profile;
import seedu.dietmanager.ui.UI;

public class CalculateCaloriesCommand extends Command {

    private static final int ARGUMENTS_REQUIRED = 1;

    /**
     * Beginning day of the queried time period.
     */
    private String begin;

    /**
     * Last day of the queried time period.
     */
    private String end;

    /**
     * Represents whether the command is querying a day or a time period.
     */
    private boolean isOneDay;

    /**
     * Total calories intake.
     */
    private double sum;

    private boolean noDescription;

    private boolean isInvalidDate;

    private boolean isCircle = false;

    /**
     * Constructs the Command object.
     *
     * @param command     the command prompt entered by the user.
     * @param description the command description entered by the user.
     */

    public CalculateCaloriesCommand(String command, String description) throws InvalidFormatException {
        super(command);
        this.noDescription = false;
        this.isInvalidDate = false;

        this.sum = 0.00;

        try {
            String[] descriptionArray = DescriptionParser.parseDescription(description, ARGUMENTS_REQUIRED);
            String[] timeDescription = descriptionArray[0].split("->");

            this.begin = timeDescription[0].trim().toUpperCase();
            Weekday.valueOf(this.begin);

            switch (timeDescription.length) {
            case 1:
                isOneDay = true;
                break;
            case 2:
                this.end = timeDescription[1].trim().toUpperCase();
                Weekday.valueOf(this.end);
                isOneDay = false;
                break;
            default:
                isOneDay = false;
                break;
            }
        } catch (NullPointerException e) {
            this.noDescription = true;
        } catch (IllegalArgumentException e) {
            this.isInvalidDate = true;
        }
    }

    /**
     * Calculates the calories intake during given time period.
     *
     * @param profile the object containing user profile information.
     * @param ui      the object containing user interface functions.
     * @return
     */

    @Override
    public Result execute(Profile profile, UI ui) {
        if (this.noDescription | this.isInvalidDate) {
            Result result = getResult(profile);
            return result;
        } else if (this.isOneDay) {
            DailyFoodRecord record = profile.getRecordOfDay(this.begin);

            if (record.getDailyCalories().isPresent()) {
                assert record.getDailyCalories().isPresent();
                sum += record.getDailyCalories().get();
            }
        } else {
            Weekday firstDay;
            Weekday lastDay;
            DailyFoodRecord curRecord;

            firstDay = Weekday.valueOf(this.begin);
            lastDay = Weekday.valueOf(this.end);

            if (lastDay.getIndex() < firstDay.getIndex()) {
                lastDay = Weekday.valueOf("SUNDAY");
                isCircle = true;
            }

            for (Weekday day : Weekday.values()) {
                int curIndex = day.getIndex();
                if (curIndex >= firstDay.getIndex() && curIndex <= lastDay.getIndex()) {
                    curRecord = profile.getRecordOfDay(day.getName());

                    if (curRecord.getDailyCalories().isPresent()) {
                        assert curRecord.getDailyCalories().isPresent();
                        sum += curRecord.getDailyCalories().get();
                    }
                } else if (curIndex > lastDay.getIndex()) {
                    break;
                }
            }
        }
        Result result = getResult(profile);
        return result;
    }

    /**
     * Saves the execution result to the command.
     *
     * @param profile the profile that the command is dealing with.
     * @return
     */

    @Override
    public Result getResult(Profile profile) {
        if (this.noDescription) {
            this.resultString = MessageBank.NO_DESCRIPTION_MESSAGE;
        } else if (this.isInvalidDate) {
            this.resultString = MessageBank.INVALID_DATE_MESSAGE;
        } else {
            if (isCircle) {
                this.resultString = String.format("%s is ahead of %s, so only calories from %s to SUNDAY is calculated",
                        this.end, this.begin, this.begin) + System.lineSeparator()
                        + MessageBank.CALCULATE_CALORIES_MESSAGE + String.format("%.2f.", sum);
            } else {
                this.resultString = MessageBank.CALCULATE_CALORIES_MESSAGE + String.format("%.2f.", sum);
            }
        }
        return new Result(this.resultString);
    }
}
