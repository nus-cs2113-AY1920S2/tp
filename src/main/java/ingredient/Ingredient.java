package ingredient;

import java.util.Optional;

/** 
 * The ingredient class that is used by the Stock and the
 * dish.Dish class.
 *
 */
public class Ingredient {
    
    private final String name;
    private final Optional<Integer> quantity;
    private final Optional<Double> price;
    
    /** A convenience constructor to be used for the Command classes. */
    public Ingredient(String name, Optional<Integer> quantity, Optional<Double> price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    
    /** Returns true if the current ingredient has a specified quantity. */
    public boolean isQuantitySpecified() {
        return this.quantity.isPresent();
    }
    
    public double getIngredientPrice() {
        return this.price.orElseThrow();
    }
    
    public int getIngredientQuantity() {
        return this.quantity.orElseThrow();
    }
    
    public String getIngredientName() {
        return this.name;
    }

    /** Returns true if both ingredients have the same name. */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } 
        if (obj instanceof Ingredient) {
            Ingredient task = (Ingredient) obj;
            return task.name == (this.name);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
