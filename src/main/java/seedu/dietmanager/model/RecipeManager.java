package seedu.dietmanager.model;

import seedu.dietmanager.commons.core.FoodNutritionInfo;
import seedu.dietmanager.commons.core.Weekday;

import java.util.ArrayList;

public class RecipeManager {
    private ArrayList<DailyFoodRecord> recipe;
    private static RecipeManager theOnlyOne = null;
    private static final int MAX_FOOD_TYPES = 4;

    private RecipeManager() {
        recipe = new ArrayList<>();
        String curDay;
        for (Weekday weekday : Weekday.values()) {
            curDay = weekday.getName();
            assert recipe != null;
            recipe.add(new DailyFoodRecord(curDay));
        }
    }

    /**
     * Returns the only one instance, if it doesn't exist, create one first.
     *
     * @return the only instance of RecipeManager
     */

    public static RecipeManager getInstance() {
        if (theOnlyOne == null) {
            theOnlyOne = new RecipeManager();
        }
        return theOnlyOne;
    }

    private void setRecipe(String date, String mealType, ArrayList<Food> foodList) {
        Weekday weekday = Weekday.valueOf(date.toUpperCase());
        int index = weekday.getIndex();
        recipe.get(index).recordMeals(mealType,foodList);
    }

    private void setRecipe(int index, String mealType, ArrayList<Food> foodList) {
        recipe.get(index).clearRecords(mealType);
        recipe.get(index).recordMeals(mealType,foodList);
    }

    /**
     * Returns the recipe of a week.
     * Each entry of the recipe contains three sections--morning/afternoon/night.
     *
     * @return the recipe of a week.
     */

    public String getRecipe() {
        String recipeTable = String.format("%1$-10s"," ") + "morning";
        recipeTable = String.format("%1$-70s",recipeTable) + "afternoon";
        recipeTable = String.format("%1$-130s",recipeTable) + "night\n";

        for (DailyFoodRecord record : recipe) {
            recipeTable = recipeTable + record.getRecipeEntry() + System.lineSeparator();
        }
        return recipeTable;
    }

    /**
     * Creates a recipe for the week based on user's needs.
     *
     * @param cap calories cap for each meal
     * @param num maximum food types the user want to have in a meal.
     * @return true if maximum food types is less than 5, else false.
     */

    public boolean customRecipe(double cap, int num) {
        FoodNutritionInfo foodInfo = FoodNutritionInfo.getInstance();
        int maxNum = foodInfo.getListSize();
        boolean overflow = false;

        if (num > MAX_FOOD_TYPES) {
            num = MAX_FOOD_TYPES;
            overflow = true;
        }

        int success = 0;
        while (success < 21) {
            double sum = 0;
            boolean[] checked = new boolean[maxNum];
            ArrayList<Food> foodList = new ArrayList<>();
            int checkedNum = 0;

            for (int i = 0; i < num && checkedNum < maxNum; i++) {
                while (checkedNum < maxNum) {
                    int rand = (int) (Math.random() * maxNum);
                    if (!checked[rand]) {
                        checked[rand] = true;
                        checkedNum += 1;

                        if (sum + foodInfo.getFoodCalories(rand) <= cap) {
                            sum += foodInfo.getFoodCalories(rand);
                            foodList.add(foodInfo.findFood(rand));
                            break;
                        }
                    }
                }
            }
            if (sum <= cap) {
                success += 1;
                String mealType = null;
                switch (success % 3) {
                case 1:
                    mealType = "morning";
                    break;
                case 2:
                    mealType = "afternoon";
                    break;
                case 0:
                    mealType = "night";
                    break;
                default:
                    break;

                }
                setRecipe((int) ((success - 1) / 3), mealType, foodList);
            }
        }
        return overflow;
    }
}
