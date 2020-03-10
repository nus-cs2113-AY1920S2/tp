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
     * Dish constructor.
     * @param name name of dish
     * @param ingredients arraylist of ingredients in dish
     */
    public Dish(String name, ArrayList<String> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    /**
     * Returns ingredients in dish.
     * @return arraylist of ingredients in dish
     */
    public ArrayList<String> getIngredients() {
        return ingredients;
    }

}
