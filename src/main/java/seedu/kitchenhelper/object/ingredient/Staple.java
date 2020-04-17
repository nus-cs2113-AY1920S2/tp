package seedu.kitchenhelper.object.ingredient;

public class Staple extends Ingredient {
    
    public static final String INGREDIENT_WORD = "staple";
    
    public Staple(String ingredientName, String categoryName, int quantity, double price, String expiryDate) {
        super(ingredientName, categoryName, quantity, price, expiryDate);
    }
    
    @Override
    public String toFind() {
        return "[Staple] " + super.toFind();
    }
}
