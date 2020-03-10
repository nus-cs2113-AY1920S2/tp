import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Menu {

    private static HashMap<String, Dish> dishMap = new HashMap<String, Dish>();

    public static void addDish (String input) {
        ArrayList<String> ingredients = parseIngredients(input);
        String name = parseName(input);
        Dish d = new Dish(name, ingredients);
        dishMap.put(name, d);
    }

    public static void deleteDish (String name) {
        dishMap.remove(name);
    }

    public static ArrayList<String> parseIngredients(String input) {
        ArrayList<String> ingredients = new ArrayList<String>();
        input = input.substring(input.indexOf("i/") + 2);
        input = input.substring(0, input.indexOf(";"));
        String[] splitString = input.split(",");
        for (String str: splitString) {
            ingredients.add(str.trim());
        }
        return ingredients;
    }

    public static String parseName(String input) {
        input = input.substring(input.indexOf("n/") + 2);
        input = input.substring(0, input.indexOf(";"));
        return input;
    }

    public static void printDishes() {
        for (String name: dishMap.keySet()) {
            String iList = "";
            for (String str: dishMap.get(name).getIngredients()) {
                iList += str + ",";
            }
            iList = iList.substring(0, iList.length()-1);
            System.out.println("Name: " + name + "; Ingredients: " + iList);
        }
    }

    public static HashMap<String, Dish> getDishMap() {
        return dishMap;
    }

}
