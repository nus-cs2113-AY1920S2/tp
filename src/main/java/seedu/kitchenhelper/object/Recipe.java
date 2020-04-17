package seedu.kitchenhelper.object;

import seedu.kitchenhelper.exception.KitchenHelperException;
import seedu.kitchenhelper.object.ingredient.Dairy;
import seedu.kitchenhelper.object.ingredient.Drink;
import seedu.kitchenhelper.object.ingredient.Fruit;
import seedu.kitchenhelper.object.ingredient.Ingredient;
import seedu.kitchenhelper.object.ingredient.Meat;
import seedu.kitchenhelper.object.ingredient.Miscellaneous;
import seedu.kitchenhelper.object.ingredient.Staple;
import seedu.kitchenhelper.object.ingredient.Vegetable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Map;
import java.util.Comparator;
import java.util.logging.Logger;

/**
 * A Recipe represents a collection of ingredients of different types.
 */
public class Recipe {

    public static final Logger kitchenLogs = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public final String warningAddRecipe =
            "An unidentifiable ingredient has been added to ingredient of Miscellaneous category";
    ArrayList<Ingredient> recipeItems = new ArrayList<>();
    public String recipeName;
    public Integer recipeIngrQty;

    /**
     * Prepares the addition of ingredients into recipe.
     *
     * @param ingrAndQty the hashmap of ingredients.
     */
    public void addIngredientsToRecipe(HashMap<String[], Integer> ingrAndQty) {
        assert ingrAndQty.size() > 0;
        for (Map.Entry<String[], Integer> entry : ingrAndQty.entrySet()) {
            String ingrName = (entry.getKey())[0];
            String ingrCategory = (entry.getKey())[1];
            Integer ingrQuantity = entry.getValue();
            assert ingrName.length() > 0;
            assert ingrCategory.length() > 0;
            assert ingrQuantity >= 0;
            Ingredient newIngredient = createIngr(ingrName, ingrCategory, ingrQuantity);
            recipeItems.add(newIngredient);
            assert recipeItems.size() > 0;
        }
        recipeIngrQty = recipeItems.size();
        sortRecipeItem();
    }

    /**
     * Sorts recipeItems arraylist by Category name, then, Ingredient name.
     *
     */
    public void sortRecipeItem() {
        Collections.sort(this.recipeItems, Comparator.comparing(Ingredient::getCategoryName)
                .thenComparing(Ingredient::getIngredientName));
    }
    /**
     * Prepares the addition of ingredients into recipe.
     *
     * @param ingrName the name of the ingredient.
     * @param ingrCategory the category of the ingredient.
     * @param ingrQuantity the quantity of the ingredient.
     * @return Ingredient specific to its category.
     */

    public Ingredient createIngr(String ingrName, String ingrCategory, Integer ingrQuantity) {
        switch (ingrCategory.toLowerCase()) {
        case "dairy":
            return new Dairy(ingrName, ingrCategory, ingrQuantity, 0, null);
        case "drink":
            return new Drink(ingrName, ingrCategory, ingrQuantity, 0, null);
        case "fruit":
            return new Fruit(ingrName, ingrCategory, ingrQuantity, 0, null);
        case "meat":
            return new Meat(ingrName, ingrCategory, ingrQuantity, 0, null);
        case "miscellaneous":
            return new Miscellaneous(ingrName, ingrCategory, ingrQuantity, 0, null);
        case "staple":
            return new Staple(ingrName, ingrCategory, ingrQuantity, 0, null);
        case "vegetable":
            return new Vegetable(ingrName, ingrCategory, ingrQuantity, 0, null);
        default:
            kitchenLogs.warning(warningAddRecipe);
            return new Miscellaneous(ingrName, "Miscellaneous", ingrQuantity, 0, null);
        }
    }

    /**
     * Loading of ingredients into a recipe.
     *
     * @param ingredients the list of ingredients.
     */
    public void addIngredientsToRecipeFromArrayList(ArrayList<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            this.recipeItems.add(ingredient);
        }
    }

    /**
     * For ease of saving into storage.
     *
     * @param attributes the string of ingredients and recipe.
     */
    public void setRecipeNameForStorage(String attributes) {
        recipeName = attributes;
    }

    /**
     * Sets the recipe name.
     *
     * @param attributes the list of ingredients and recipe name.
     */
    public void setRecipeName(String attributes) throws KitchenHelperException {
        try {
            String recipeNameAndIngr = attributes.substring(attributes.indexOf("/n") + 3, attributes.indexOf("/i") - 1);
            String trimmedRecipeName = recipeNameAndIngr.trim();
            recipeName = trimmedRecipeName;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new KitchenHelperException("Invalid command");
        }
    }

    /**
     * Retrieve the name of a recipe.
     *
     * @return the recipe name.
     */
    public String getRecipeName() {
        return recipeName;
    }

    /**
     * Get the list of ingredients in a recipe.
     *
     * @return the arraylist of ingredients.
     */
    public ArrayList<Ingredient> getRecipeItem() {
        return this.recipeItems;
    }

    /**
     * To compare two Recipe objects based on their attributes.
     * @return boolean return false if any of the attributes are not equal to each other.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Recipe) {
            Recipe i = (Recipe) o;
            return this.recipeName.equals(i.recipeName)
                    && this.recipeIngrQty == recipeIngrQty
                    && this.recipeItems.equals(i.recipeItems);
        } else {
            return false;
        }
    }

    /**
     * To format all variables of add ingredient as a string.
     * @return String consisting of ingredient name, category, quantity, price and expiry.
     */
    @Override
    public String toString() {
        return "/n " + getRecipeName() + " " + getRecipeItem() + " " + recipeItems.size();
    }
}


