package seedu.kitchenhelper.object.ingredient;

public class Fruit extends Ingredient {
    
    public static final String INGREDIENT_WORD = "fruit";
    
    public Fruit(String ingredientName, String categoryName, int quantity, double price, String expiryDate) {
        super(ingredientName, categoryName, quantity, price, expiryDate);
    }
    
    @Override
    public String toFind() {
        return "[Fruit] " + super.toFind();
    }
}
