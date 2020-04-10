package seedu.dietmanager.model;

import seedu.dietmanager.commons.core.MessageBank;
import seedu.dietmanager.commons.core.Weekday;
import seedu.dietmanager.logic.commands.CheckRequiredCaloriesCommand;

import java.util.ArrayList;

public class RecipeManager {
    private ArrayList<DailyFoodRecord> recipe;
    private static RecipeManager theOnlyOne = null;
    private static final int MAX_FOOD_TYPES = 3;

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

    /**
     * Sets a meal into the recipe.
     *
     * @param date     the date of this meal
     * @param mealType the type of this meal
     * @param foodList foods recommended for this meal
     */

    public void setRecipe(String date, String mealType, ArrayList<Food> foodList) {
        Weekday weekday = Weekday.valueOf(date.toUpperCase());
        int index = weekday.getIndex() - 1;
        recipe.get(index).recordMeals(mealType, foodList);
    }

    public void setRecipe(int index, String mealType, ArrayList<Food> foodList) {
        recipe.get(index).clearRecords(mealType);
        recipe.get(index).recordMeals(mealType, foodList);
    }

    /**
     * Returns the recipe of a week.
     * Each entry of the recipe contains three sections--morning/afternoon/night.
     *
     * @return the recipe of a week.
     */

    public String getRecipe() {
        String recipeTable = this.getRecipeHead();

        for (DailyFoodRecord record : recipe) {
            recipeTable = recipeTable + record.getRecipeEntry() + System.lineSeparator();
        }
        return recipeTable;
    }

    /**
     * Returns the head of the recipe.
     *
     * @return the head of the recipe.
     */

    public String getRecipeHead() {
        String recipeHead = String.format("%1$-10s", " ") + "morning";
        recipeHead = String.format("%1$-80s", recipeHead) + "afternoon";
        recipeHead = String.format("%1$-150s", recipeHead) + "night\n";
        return recipeHead;
    }

    /**
     * Returns the body part of the recipe.
     *
     * @return the body of the recipe.
     */

    public String getRecipeBody() {
        String recipeBody = "";
        for (DailyFoodRecord record : recipe) {
            recipeBody = recipeBody + record.getRecipeEntry() + System.lineSeparator();
        }
        return recipeBody;
    }

    /**
     * Creates a recipe for the week based on user's needs.
     *
     * @param num maximum food types the user want to have in a meal.
     * @return true if maximum food types is less than 5, else false.
     */

    public boolean buildRecipe(Profile profile, int num, String activityLevel) {
        CheckRequiredCaloriesCommand command =
                new CheckRequiredCaloriesCommand(MessageBank.CHECK_REQUIRED_CAL_COMMAND_PROMPT);
        double cap = command.getRecommendedCaloriesIntake(profile, activityLevel) / 3;

        FoodNutritionRecord foodInfo = FoodNutritionRecord.getInstance();
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
