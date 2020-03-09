package seedu.duke.data;

public class Item {

    private double price;
    private String description;
    private boolean isBought;
    public static final String BOUGHT = "B";
    public static final String NOT_BOUGHT = "0";

    /**
     * Constructs an Item object with the provided description and price.
     *
     * @param description Name of the object.
     * @param price Price of the object
     */
    public Item(String description,double price) {
        this.price = price;
        this.description = description;
        this.isBought = false;
    }

    /**
     * Constructs an Item object with only description provided and no price indicated.
     *
     * @param description Name of the object.
     */
    public Item(String description) {
        this.price = 0.0;
        this.description = description;
        this.isBought = false;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

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
        return "[" + getStatusIcon() + "] " + description + " $" + price;
    }


}
