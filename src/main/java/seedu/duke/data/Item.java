package seedu.duke.data;

public class Item {

    private double price;
    private String description;
    private int quantity;
    private boolean isBought;
    private static final String BOUGHT = "B";
    private static final String NOT_BOUGHT = "X";

    //@@author trishaangelica
    /**
     * Constructs an Item object with the provided description and price.
     *
     * @param description Name of the object.
     * @param price Price of the object
     * @param quantity Quantity of the object
     */
    public Item(String description,double price,int quantity) {
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.isBought = false;
    }
    //@@author

    /**
     * Constructs an Item object with the provided description and price.
     *
     * @param description Name of the object.
     * @param price Price of the object
     */
    public Item(String description,double price) {
        this.price = price;
        this.description = description;
        this.quantity = 1;
        this.isBought = false;
    }
    //@@author

    //@@author JLoh579
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    //@@author

    //@@author kokjoon97

    /**
     * Returns the price of the <code>Item</code>.
     *
     * @return Price of the <code>Item</code>.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the <code>Item</code> to the value provided as argument.
     *
     * @param price The new price of the <code>Item</code>.
     */
    public void setPrice(double price) {
        this.price = price;
    }
    //@@author

    //@@author jiajuinphoon
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //@@author

    //@@author Shannonwje
    /**
     * Returns the status of the item to tell if it is bought or not bought yet.
     *
     * @return Status of the item.
     */
    public String getStatusIcon() {
        if (isBought) {
            return BOUGHT;
        } else {
            return NOT_BOUGHT;
        }
    }

    public void markAsBought() {
        isBought = true;
    }

    public void unmarkAsBought() {
        isBought = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description + String.format(" $%.2f", price) + " qty: " + quantity;
    }
    //@@author
}
