package seedu.kitchenhelper.command;

import seedu.kitchenhelper.common.Messages;
import seedu.kitchenhelper.storage.Storage;
import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.object.Recipe;
import seedu.kitchenhelper.object.ingredient.Ingredient;
import seedu.kitchenhelper.object.ingredient.Dairy;
import seedu.kitchenhelper.object.ingredient.Drink;
import seedu.kitchenhelper.object.ingredient.Fruit;
import seedu.kitchenhelper.object.ingredient.Meat;
import seedu.kitchenhelper.object.ingredient.Miscellaneous;
import seedu.kitchenhelper.object.ingredient.Staple;
import seedu.kitchenhelper.object.ingredient.Vegetable;
import seedu.kitchenhelper.ui.Ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Logger;

import static seedu.kitchenhelper.common.Messages.NEGATIVE_ONE;

/**
 * Adds the ingredient to the inventory list.
 */
public class AddIngredientCommand extends Command {
    
    // Regex for checking the format of add ingredient
    public static final String REGEX_FORMAT =
            "/n( )+[a-zA-Z]+( [a-zA-Z]+)*( )+/c( )+[a-zA-Z]+( )+/q( )+[0-9]+( )+/p( )+\\d+(\\.\\d{1,2})?( )"
            + "+/e( )+\\d{2}/\\d{2}/\\d{4}( )*";
    public static final Logger kitchenLogs = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static final String COMMAND_WORD = "addingredient";
    public static final String COMMAND_DESC = "Adds a ingredient to the ingredient list.";
    public static final String COMMAND_PARAMETER = "/n <INGREDIENT> /c <CATEGORY> /q <QUANTITY> /p <PRICE> /e <EXPIRY>";
    public static final String COMMAND_EXAMPLE = "Example: addingredient /n Beef /c Meat /q 1 /p 13.5 /e 13/02/2022";
    public static final String COMMAND_FORMAT =
            String.format("%s %s\n%s", COMMAND_DESC, COMMAND_PARAMETER, COMMAND_EXAMPLE);
    public static final String MESSAGE_USAGE = String.format("%s: %s", COMMAND_WORD, COMMAND_DESC) + Ui.LS + String
            .format("Parameter: %s\n%s", COMMAND_PARAMETER, COMMAND_EXAMPLE);
    
    private String ingredientName;
    private String categoryName;
    private int quantity;
    private double price;
    private String expiry;
    
    /**
     * Constructor for Add Inventory Command.
     *
     * @param ingredientName name of the ingredient.
     * @param categoryName   category of the ingredient.
     * @param quantity       number of serving of ingredient.
     * @param price          cost of the ingredient.
     * @param expiry         ingredient expiry date.
     */
    public AddIngredientCommand(String ingredientName, String categoryName, int quantity, double price, String expiry) {
        this.ingredientName = ingredientName;
        this.categoryName = categoryName;
        this.quantity = quantity;
        this.price = price;
        this.expiry = expiry;
    }
    
    /**
     * Adds the ingredient to the ArrayList according to the category type.
     *
     * @param category       category of the ingredient.
     * @param ingredientList the ArrayList to store ingredients.
     */
    public void addToCategory(String category, ArrayList<Ingredient> ingredientList) {
        switch (category.toLowerCase()) {
        case Meat.INGREDIENT_WORD:
            ingredientList.add(new Meat(ingredientName, categoryName, quantity, price, expiry));
            break;
        case Vegetable.INGREDIENT_WORD:
            ingredientList.add(new Vegetable(ingredientName, categoryName, quantity, price, expiry));
            break;
        case Drink.INGREDIENT_WORD:
            ingredientList.add(new Drink(ingredientName, categoryName, quantity, price, expiry));
            break;
        case Fruit.INGREDIENT_WORD:
            ingredientList.add(new Fruit(ingredientName, categoryName, quantity, price, expiry));
            break;
        case Staple.INGREDIENT_WORD:
            ingredientList.add(new Staple(ingredientName, categoryName, quantity, price, expiry));
            break;
        case Dairy.INGREDIENT_WORD:
            ingredientList.add(new Dairy(ingredientName, categoryName, quantity, price, expiry));
            break;
        case Miscellaneous.INGREDIENT_WORD:
            // Fallthrough
        default:
            this.categoryName = Miscellaneous.INGREDIENT_WORD;
            ingredientList.add(new Miscellaneous(ingredientName, categoryName, quantity, price, expiry));
            break;
        }
    }
    
    /**
     * Sorts ingredientList arraylist by Category name, then, Ingredient name, then, Expiry Date.
     */
    public void sortIngredientList(ArrayList<Ingredient> ingredientList) {
        Collections.sort(ingredientList,
                Comparator.comparing(Ingredient::getIngredientName).thenComparing(Ingredient::getExpiryDate));
    }
    
    /**
     * Check if the newly added ingredient has the same name (case ignored), same price and expiry date.
     *
     * @param i              ingredient object.
     * @param ingredientName ingredient name.
     * @param price          ingredient price.
     * @param expiryDate     ingredient expiry date.
     * @return true if has same name, price and expiry date, else return false.
     */
    public boolean hasSameNameAndPriceAndExpiry(Object i, String ingredientName, double price, String expiryDate) {
        boolean sameName = ((Ingredient) i).getIngredientName().equalsIgnoreCase(ingredientName);
        boolean samePrice = ((Ingredient) i).getPrice() == price;
        boolean sameExpiry = ((Ingredient) i).getExpiryDate().equals(expiryDate);
        return sameName && samePrice && sameExpiry;
    }
    
    /**
     * Get the index from the ingredientList if has matching name, price and expiry date.
     *
     * @param ingredientList list of ingredients.
     * @return the index if found, else return NEGATIVE_ONE.
     */
    public int mergeTogetherIndex(ArrayList<Ingredient> ingredientList) {
        int mergeIndex = 0;
        for (Ingredient i : ingredientList) {
            if (hasSameNameAndPriceAndExpiry(i, ingredientName, price, expiry)) {
                i.setQuantity(i.getQuantity() + quantity);
                return mergeIndex;
            }
            mergeIndex++;
        }
        return NEGATIVE_ONE;
    }
    
    /**
     * {@inheritDoc}
     *
     * @param ingredientList list of ingredients.
     * @param recipeList     list of recipes.
     * @param choreList      list of chores.
     * @return the success message of adding inventory.
     */
    @Override
    public CommandResult execute(ArrayList<Ingredient> ingredientList, ArrayList<Recipe> recipeList,
                                 ArrayList<Chore> choreList) {
        int index = mergeTogetherIndex(ingredientList);
        
        if (index != NEGATIVE_ONE) {
            Ingredient i = ingredientList.get(index);
            int currentQuantity = i.getQuantity();
            sortIngredientList(ingredientList);
            Storage.saveIngredientData(ingredientList);
            return new CommandResult(
                    String.format(Messages.MESSAGE_COMBINE_INGREDIENT, i.getIngredientName(), currentQuantity,
                            currentQuantity - quantity));
        } else {
            addToCategory(categoryName, ingredientList);
            sortIngredientList(ingredientList);
            Storage.saveIngredientData(ingredientList);
            kitchenLogs.info(Messages.MESSAGE_ADD_INGREDIENT_LOG);
            return new CommandResult(
                    String.format(Messages.MESSAGE_ADD_INGREDIENT_SUCCESS, ingredientName, categoryName, quantity,
                            price, expiry));
        }
    }
}
