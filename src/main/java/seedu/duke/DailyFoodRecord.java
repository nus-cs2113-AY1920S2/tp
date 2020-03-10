package seedu.duke;

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

    public DailyFoodRecord(String date){
        setDate(date);
        breakfast = new ArrayList<Food>();
        lunch = new ArrayList<Food>();
        dinner = new ArrayList<Food>();
    }

    public void setDate(String date) {
        LocalDate standardTime = null;
        boolean isStandardTime = false;
        try {
            standardTime = LocalDate.parse(date);
            isStandardTime = true;
        } catch (DateTimeParseException ignored) {
        } finally {
            if (isStandardTime) {
                this.date = standardTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH));
            } else {
                this.date = date;
            }
        }
    }


    public void recordMeals(String mealType, ArrayList<Food> foodList){
        switch (mealType){
        case "breakfast":
            breakfast.addAll(foodList);
            break;
        case "lunch":
            lunch.addAll(foodList);
            break;
        case "dinner":
            dinner.addAll(foodList);
            break;
        }
    }

    public void showDailyRecord(){
        this.showBreakfast();
        this.showLunch();
        this.showDinner();
    }

    public void showBreakfast(){
    }

    public void showLunch(){
    }

    public void showDinner(){
    }
}
