package seedu.kitchenhelper.object.ingredient;

public class Dairy extends Ingredient {
    
    public static final String INGREDIENT_WORD = "dairy";
    
    public Dairy(String ingredientName, String categoryName, int quantity, double price, String expiryDate) {
        super(ingredientName, categoryName, quantity, price, expiryDate);
    }
    
    @Override
    public String toFind() {
        return "[Dairy] " + super.toFind();
    }
}
