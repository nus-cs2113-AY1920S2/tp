package seedu.dietmanager;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DailyFoodRecord {
    private String date;
    private ArrayList<Food> breakfast;
    private ArrayList<Food> lunch;
    private ArrayList<Food> dinner;

    /**
     * Constructs the Daily Food Record.
     */

    public DailyFoodRecord(String date) {
        setDate(date);
        breakfast = new ArrayList<Food>();
        lunch = new ArrayList<Food>();
        dinner = new ArrayList<Food>();
    }

    /**
     * Sets the date of the record.
     */

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Records the meals consumed into their respective categories.
     */

    public void recordMeals(String mealType, ArrayList<Food> foodList) {
        switch (mealType) {
        case "morning":
            breakfast.addAll(foodList);
            break;
        case "afternoon":
            lunch.addAll(foodList);
            break;
        case "night":
            dinner.addAll(foodList);
            break;
        default:
            break;
        }
    }

    /**
     * Displays the Daily Food Record.
     */

    public void showDailyRecord() {
        this.showBreakfast();
        this.showLunch();
        this.showDinner();
    }

    /**
     * Displays the Daily Breakfast Record.
     */

    public String showBreakfast() {
        String message = "";
        for (Food food : breakfast) {
            message = message + food.getFoodName() + ", ";
        }
        if (message.equals("")) {
            message = "Oops, you have no record for this meal";
            return message;
        }
        return message.substring(0, message.length() - 2);
    }

    /**
     * Displays the Daily Lunch Record.
     */

    public String showLunch() {
        String message = "";
        for (Food food : lunch) {
            message = message + food.getFoodName() + ", ";
        }
        if (message.equals("")) {
            message = "Oops, you have no record for this meal";
            return message;
        }
        return message.substring(0, message.length() - 2);
    }

    /**
     * Displays the Daily Dinner Record.
     */

    public String showDinner() {
        String message = "";
        for (Food food : dinner) {
            message = message + food.getFoodName() + ", ";
        }
        if (message.equals("")) {
            message = "Oops, you have no record for this meal";
            return message;
        }
        return message.substring(0, message.length() - 2);
    }

    public boolean isDate(String date) {
        return this.date.equals(date);
    }
}
