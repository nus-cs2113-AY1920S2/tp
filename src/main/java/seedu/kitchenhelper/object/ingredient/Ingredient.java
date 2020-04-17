package seedu.kitchenhelper.object.ingredient;

/**
 * Represent an Ingredient object.
 */
public abstract class Ingredient {
    
    private String ingredientName;
    private String categoryName;
    private int quantity;
    private double price;
    private String expiryDate;
    private static final String FIND_PARAMETERS = "%s Qty:%d $%.2f Exp:%s";

    /**
     * Constructor for Ingredient.
     *
     * @param ingredientName name of the ingredient.
     * @param categoryName   category of the ingredient.
     * @param quantity       quantity of the ingredient.
     * @param price          price of the ingredient.
     * @param expiryDate     expiry date of the ingredient.
     */
    public Ingredient(String ingredientName, String categoryName, int quantity, double price, String expiryDate) {
        this.ingredientName = ingredientName;
        this.categoryName = categoryName;
        this.quantity = quantity;
        this.price = price;
        this.expiryDate = expiryDate;
    }
    
    /**
     * Getters for ingredient name.
     *
     * @return ingredient name.
     */
    public String getIngredientName() {
        return ingredientName;
    }
    
    /**
     * Getters for category name.
     *
     * @return category name.
     */
    public String getCategoryName() {
        return categoryName;
    }
    
    /**
     * Getters for quantity.
     *
     * @return quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setters for quantity.
     *
     * @param quantity quantity of ingredient
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Getters for price.
     *
     * @return price.
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Getters for expiry date.
     *
     * @return expiry date.
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * To format all variables of add ingredient as a string.
     * @return String consisting of ingredient name, category, quantity, price and expiry.
     */
    @Override
    public String toString() {
        return "/n " + getIngredientName() + " /c " + getCategoryName() + " /q " + getQuantity()
                + " /p " + getPrice() + " /e " + getExpiryDate();
    }
    
    public String toFind() {
        return String.format(FIND_PARAMETERS, getIngredientName(), getQuantity(), getPrice(), getExpiryDate());
    }

    /**
     * To compare two Ingredient objects based on their attributes.
     * @return boolean return false if any of the attributes are not equal to each other.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Ingredient) {
            Ingredient i = (Ingredient) o;
            return this.ingredientName.equals(i.ingredientName)
                && this.categoryName.equals(i.categoryName)
                && this.quantity == i.quantity
                && this.price == price
                && this.expiryDate.equals(i.expiryDate);
        } else {
            return false;
        }
    }
}
