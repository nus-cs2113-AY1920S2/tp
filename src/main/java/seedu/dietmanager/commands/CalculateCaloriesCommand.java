package seedu.dietmanager.commands;

import seedu.dietmanager.DailyFoodRecord;
import seedu.dietmanager.FoodNutritionInfo;
import seedu.dietmanager.Profile;
import seedu.dietmanager.Weekday;
import seedu.dietmanager.exceptions.InvalidFormatException;
import seedu.dietmanager.parser.Parser;
import seedu.dietmanager.ui.MessageBank;
import seedu.dietmanager.ui.UI;

public class CalculateCaloriesCommand extends Command {
    private static final int ARGUMENTS_REQUIRED = 1;
    private String begin;
    private String end;
    private boolean checkOneDay;
    private double sum;
    private FoodNutritionInfo foodNutritionInfo = new FoodNutritionInfo();

    /**
     * Constructs the Command object.
     * @param command the command prompt entered by the user.
     */
    public CalculateCaloriesCommand(String command, String description) throws InvalidFormatException {
        super(command);
        this.sum = 0.00;
        String[] descriptionArray = Parser.parseDescription(description, ARGUMENTS_REQUIRED);
        String[] timeDescription = descriptionArray[0].split("->");
        this.begin = timeDescription[0];
        switch (timeDescription.length) {
        case 1:
            checkOneDay = true;
            break;
        case 2:
            this.end = timeDescription[1];
            checkOneDay = false;
            break;
        default:
            checkOneDay = false;
            break;
        }
    }

    @Override
    public void execute(Profile profile, UI ui) {
        saveResult(profile);
    }

    @Override
    public void saveResult(Profile profile) {
        if (checkOneDay) {
            DailyFoodRecord record = profile.getRecordOfDay(this.begin);
            if (record.getDailyCalories().isPresent()) {
                assert record.getDailyCalories().isPresent();
                sum += record.getDailyCalories().get();
            }
        } else {
            Weekday firstDay;
            Weekday lastDay;
            DailyFoodRecord curRecord;

            firstDay = Weekday.valueOf(this.begin.toUpperCase());
            lastDay = Weekday.valueOf(this.end.toUpperCase());

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
        this.result = MessageBank.CALCULATE_CALORIES_MESSAGE + String.format("%.2f.",sum);
    }
}
