package dish;

import java.util.ArrayList;

public class Dish {

    /**
     * Arraylist of all ingredients for dish.
     */
    private ArrayList<String> ingredients;

    /**
     * Name of dish.
     */
    private String name;

    /**
     * Price of dish
     */
    private double price;

    /**
     * dish.Dish constructor.
     * @param name name of dish
     * @param ingredients arraylist of ingredients in dish
     */
    public Dish(String name, ArrayList<String> ingredients, double price) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
    }

    /**
     * Returns ingredients in dish.
     * @return arraylist of ingredients in dish
     */
    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    /**
     * Return price of dish
     * @return price of dish
     */
    public double getPrice() {
        return price;
    }

}
