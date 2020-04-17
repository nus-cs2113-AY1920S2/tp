package seedu.kitchenhelper.object.ingredient;

public class Miscellaneous extends Ingredient {
    
    public static final String INGREDIENT_WORD = "miscellaneous";
    
    public Miscellaneous(String ingredientName, String categoryName, int quantity, double price, String expiryDate) {
        super(ingredientName, categoryName, quantity, price, expiryDate);
    }
    
    @Override
    public String toFind() {
        return "[Miscellaneous] " + super.toFind();
    }
}
