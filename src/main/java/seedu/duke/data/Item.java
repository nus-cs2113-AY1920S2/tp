package seedu.duke.data;

public class Item {

    private double price;
    private String description;
    private boolean isBought;
    public static final String BOUGHT = "B";
    public static final String NOT_BOUGHT = "0";

    public Item(String description,double price) {
        this.price = price;
        this.description = description;
        this.isBought = false;
    }

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

    public void unmarkAsBought() { isBought = false; }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description + " $" + price;
    }


}
