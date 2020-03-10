import java.util.ArrayList;

public class Dish {

    private ArrayList<String> ingredients;

    private String name;

    public Dish(String name, ArrayList<String> ingredients ) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

}
