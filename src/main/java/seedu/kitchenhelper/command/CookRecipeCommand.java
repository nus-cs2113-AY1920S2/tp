package seedu.kitchenhelper.command;

import seedu.kitchenhelper.exception.KitchenHelperException;
import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.object.Expenditure;
import seedu.kitchenhelper.object.Recipe;
import seedu.kitchenhelper.object.ingredient.Ingredient;
import seedu.kitchenhelper.storage.Storage;
import seedu.kitchenhelper.ui.Ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Cooks a recipe.
 */
public class CookRecipeCommand extends Command {
    public static final Logger kitchenLogs = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String COMMAND_WORD = "cookrecipe";
    public static final String COMMAND_DESC = "Cooks a recipe from the recipe list.";
    public static final String COMMAND_PARAMETER
            = "/n RECIPENAME /p NUMOFPAX";
    public static final String COMMAND_EXAMPLE
            = "Example: cookrecipe /n Chicken Salad /p 2";
    public static final String COMMAND_FORMAT =
            String.format("%s %s\n%s", COMMAND_DESC, COMMAND_PARAMETER, COMMAND_EXAMPLE);
    public static final String COMMAND_FAILURE_RECIPE_NOT_EXISTS = "The given recipe name does not exist!";
    public static String COMMAND_FAILURE_INSUFFICIENT_INGREDIENTS = "There are insufficient/"
                                                                            + "missing ingredients!";
    public static final String KITCHEN_HELPER_COOK = "Kitchen Helper is trying to cook!";
    public static final String COMMAND_SUCCESS = "%s was cooked with a pax of %d";
    public static final String MESSAGE_USAGE = String.format("%s: %s", COMMAND_WORD, COMMAND_DESC) + Ui.LS + String
            .format("Parameter: %s\n%s", COMMAND_PARAMETER, COMMAND_EXAMPLE);
    public final String logcookRecipe = "User attempted to cook with expired ingredient";
    public static ArrayList<String> expiredIngrNames = new ArrayList<>();
    public String recipeName;
    public int pax;

    /**
     * Cooks a recipe as specified by user.
     *
     * @param ingredientList    the list of ingredients available to use.
     * @param recipeList        the list of recipe that is known.
     * @return the message after a successful cook.
     * @throws KitchenHelperException if there is no recipe that is wanted by user/ insufficient ingredients.
     */
    public String cookRecipe(ArrayList<Ingredient> ingredientList, ArrayList<Recipe> recipeList) {
        expiredIngrNames.clear();
        // checks if the specified recipe given by user exists
        int indexOfRecipe = checkIfRecipeExist(recipeList);
        if (indexOfRecipe > recipeList.size()) {
            return COMMAND_FAILURE_RECIPE_NOT_EXISTS;
        }
        System.out.println(KITCHEN_HELPER_COOK);
        Recipe recipeToBeCooked = recipeList.get(indexOfRecipe - 1);
        Boolean sufficientIngr = checkForSufficientIngredient(ingredientList, recipeToBeCooked);
        Boolean suffButLessExpiredIngr = checkNotExpiredIngredientQty(ingredientList, recipeToBeCooked);
        if (sufficientIngr && suffButLessExpiredIngr) {
            deductIngredients(ingredientList, recipeToBeCooked);
            Storage.saveIngredientData(ingredientList);
            return String.format(COMMAND_SUCCESS, recipeName, pax);
        } else if ((sufficientIngr && !suffButLessExpiredIngr)
                || (!sufficientIngr && !suffButLessExpiredIngr && expiredIngrNames.size() != 0)) {
            String expiredList = craftExpiredList();
            if (expiredList.length() > 0) {
                expiredList = expiredList.substring(0, expiredList.length() - 2);
            }
            return COMMAND_FAILURE_INSUFFICIENT_INGREDIENTS
                    + "\nPlease check for these expired ingredients: " + expiredList.trim();
        } else {
            return COMMAND_FAILURE_INSUFFICIENT_INGREDIENTS;
        }
    }

    /**
     * Form the string of expired ingredients.
     *
     * @return string of expired ingredients.
     */
    public String craftExpiredList() {
        String expiredList = "";
        for (String item : expiredIngrNames) {
            expiredList = expiredList + item + ", ";
        }
        return expiredList;
    }

    /**
     * Check if the current ingredient is expired. If not, then deduct from the ingredient.
     *
     * @param ingredientToDeduct the ingredient where quantity is to be deducted from
     * @param totalCookedQty the remaining quantity of ingredients needed to cook
     * @return the remaining quantity of ingredients needed to cook
     */

    public int checkIfIngredientExpired(Ingredient ingredientToDeduct, int totalCookedQty,
                                        ArrayList<Ingredient> ingredientsList) {
        Date today = new Date();
        int quantity = ingredientToDeduct.getQuantity();
        try {
            Date expiredDate = new SimpleDateFormat("dd/MM/yyyy").parse(ingredientToDeduct.getExpiryDate());
            if (today.before(expiredDate)) {
                if (quantity <= totalCookedQty) {
                    totalCookedQty -= quantity;
                    Expenditure.getInstance().addAmountForCooking(ingredientToDeduct, quantity);
                    ingredientToDeduct.setQuantity(0);
                } else {
                    Expenditure.getInstance().addAmountForCooking(ingredientToDeduct, totalCookedQty);
                    ingredientToDeduct.setQuantity(quantity - totalCookedQty);
                    totalCookedQty = 0;
                }
            }
            if (ingredientToDeduct.getQuantity() == 0) {
                ingredientsList.remove(ingredientToDeduct);
            }
        } catch (ParseException e) {
            kitchenLogs.info(logcookRecipe);
        }
        return totalCookedQty;
    }

    /**
     * Deducts ingredients from list of ingredients sorted on expiry.
     *
     * @param ingredientList    the list of ingredients available.
     * @param recipeToBeCooked  the recipe that the user want to cook.
     */
    public void deductIngredients(ArrayList<Ingredient> ingredientList, Recipe recipeToBeCooked) {
        for (Ingredient ingredient : recipeToBeCooked.getRecipeItem()) {
            String ingredientName = ingredient.getIngredientName();
            ArrayList<Ingredient> listOfSameName = getIngredientsWithSameName(ingredientList, ingredientName);
            int totalCookedQty = pax * ingredient.getQuantity();
            for (Ingredient ingredientToDeduct : listOfSameName) {
                if (totalCookedQty == 0) {
                    break;
                } else {
                    totalCookedQty = checkIfIngredientExpired(ingredientToDeduct, totalCookedQty, ingredientList);
                }
            }
        }
    }

    /**
     * Get a list of ingredients that has the same name as the specified ingredient.
     *
     * @param ingredientList    the list of ingredients available.
     * @param ingredientName  the ingredient to check for its occurrence in the ingredientlist
     * @return a list of ingredients with the same name as ingredientName
     */
    public ArrayList<Ingredient> getIngredientsWithSameName(ArrayList<Ingredient> ingredientList,
                                                            String ingredientName) {
        ArrayList<Ingredient> listOfSameName = new ArrayList<>();
        for (Ingredient ingredient : ingredientList) {
            if (ingredient.getIngredientName().equalsIgnoreCase(ingredientName)) {
                listOfSameName.add(ingredient);
            }
        }
        return listOfSameName;
    }

    /**
     * Checks if the recipe user wants exist.
     *
     * @param recipeList    the list of recipes.
     * @return the index of the recipe in the list or the size of list if not found.
     */
    public int checkIfRecipeExist(ArrayList<Recipe> recipeList) {
        int counter = 0;
        for (Recipe recipe : recipeList) {
            if (recipeName.equalsIgnoreCase(recipe.recipeName)) {
                break;
            }
            counter += 1;
        }
        counter += 1;
        return counter;
    }

    /**
     * Checks if there is sufficient ingredient for each ingredient needed in recipe.
     *
     * @param ingredientList    the list of ingredients available.
     * @param recipeToBeCooked  the recipe that the user want to cook.
     * @return true if there are sufficient ingredients, otherwise false.
     */
    public Boolean checkForSufficientIngredient(ArrayList<Ingredient> ingredientList, Recipe recipeToBeCooked) {
        for (Ingredient ingr : recipeToBeCooked.getRecipeItem()) {
            int totalCookedQty = pax * ingr.getQuantity();
            String ingrName = ingr.getIngredientName().toLowerCase();
            String ingrCategory = ingr.getCategoryName().toLowerCase();
            if (getTotalIngredientQty(ingrName, ingrCategory, ingredientList) < totalCookedQty) {
                return false;
            }
        }
        return true;
    }

    public Boolean checkNotExpiredIngredientQty(ArrayList<Ingredient> ingredientList, Recipe recipeToBeCooked) {
        Date today = new Date();
        for (Ingredient ingr : recipeToBeCooked.getRecipeItem()) {
            int totalCookedQty = pax * ingr.getQuantity();
            String ingrName = ingr.getIngredientName().toLowerCase();
            ArrayList<Ingredient> listOfSameName = getIngredientsWithSameName(ingredientList, ingrName);
            int goodIngrCount = 0;
            for (Ingredient ingrNameInStore : listOfSameName) {
                try {
                    Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(ingrNameInStore.getExpiryDate());
                    if (today.before(date1)) {
                        goodIngrCount += ingrNameInStore.getQuantity();
                    } else {
                        expiredIngrNames.add(ingrName);
                    }
                } catch (ParseException e) {
                    kitchenLogs.info(logcookRecipe);
                }
            }
            if (goodIngrCount < totalCookedQty) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retrieves the quantity of each ingredient needed in the recipe.
     *
     * @param ingrName          the name of the required ingredient.
     * @param ingredientList    the list of ingredients available.
     * @return the quantity of ingredients with the same name.
     */
    public int getTotalIngredientQty(String ingrName, String ingrCategory, ArrayList<Ingredient> ingredientList) {
        int availableIngrCount = 0;
        for (Ingredient ingr : ingredientList) {
            if (ingr.getIngredientName().equalsIgnoreCase(ingrName)
                    && ingr.getCategoryName().equalsIgnoreCase(ingrCategory)) {
                availableIngrCount += ingr.getQuantity();
            }
        }
        return availableIngrCount;
    }

    public void setExpiredIngrNames(ArrayList<String> expiredIngrNames) {
        this.expiredIngrNames = expiredIngrNames;
    }

    /**
     * Sets the Recipe to be cooked.
     *
     * @param name  the name of the recipe to be cooked.
     */
    public void setRecipeName(String name) {
        this.recipeName = name;
    }

    /**
     * Sets the number of pax for the recipe.
     *
     * @param numberofpax   the num of pax to cook.
     */
    public void setRecipePax(int numberofpax) {
        this.pax = numberofpax;
    }

    /**
     * {@inheritDoc}
     *
     * @param ingredientList list of ingredients.
     * @param recipeList     list of recipes.
     * @param choreList      list of chores.
     * @return the success message of adding recipe.
     */
    @Override
    public CommandResult execute(ArrayList<Ingredient> ingredientList, ArrayList<Recipe> recipeList,
                                 ArrayList<Chore> choreList) throws KitchenHelperException {
        String message = cookRecipe(ingredientList, recipeList);
        return new CommandResult(message);
    }

}
