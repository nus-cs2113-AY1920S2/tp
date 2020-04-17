package seedu.kitchenhelper.object.ingredient;

public class Vegetable extends Ingredient {
    
    public static final String INGREDIENT_WORD = "vegetable";
    
    public Vegetable(String ingredientName, String categoryName, int quantity, double price, String expiryDate) {
        super(ingredientName, categoryName, quantity, price, expiryDate);
    }
    
    @Override
    public String toFind() {
        return "[Vegetable] " + super.toFind();
    }
}
