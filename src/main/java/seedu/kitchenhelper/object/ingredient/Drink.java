package seedu.kitchenhelper.object.ingredient;

public class Drink extends Ingredient {
    
    public static final String INGREDIENT_WORD = "drink";
    
    public Drink(String ingredientName, String categoryName, int quantity, double price, String expiryDate) {
        super(ingredientName, categoryName, quantity, price, expiryDate);
    }
    
    @Override
    public String toFind() {
        return "[Drink] " + super.toFind();
    }
}
