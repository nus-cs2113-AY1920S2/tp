package seedu.duke.data;

public class Item {
    private double price;
    private String description;

    public Item(String description,double price) {
        this.price = price;
        this.description = description;
    }

    public Item(String description) {
        this.price = 0.0;
        this.description = description;
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

    @Override
    public String toString() {
        return description + " $" + price;
    }


}
