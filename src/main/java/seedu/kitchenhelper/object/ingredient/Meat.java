package seedu.kitchenhelper.object.ingredient;

public class Meat extends Ingredient {
    
    public static final String INGREDIENT_WORD = "meat";
    
    public Meat(String ingredientName, String categoryName, int quantity, double price, String expiryDate) {
        super(ingredientName, categoryName, quantity, price, expiryDate);
    }
    
    @Override
    public String toFind() {
        return "[Meat] " + super.toFind();
    }
}
