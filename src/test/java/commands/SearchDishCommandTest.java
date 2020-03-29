package commands;

import dish.Dish;
import menu.Menu;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchDishCommandTest {

    public void start() {
        Menu m = new Menu();
        String d1 = "n/bacon pizza; i/cheese, bacon; p/5.00;";
        AddDishCommand.addDish(d1);
        String d2 = "i/chicken, rice, chili; n/chicken biryani; p/10.00;";
        AddDishCommand.addDish(d2);
        String d3 = "i/chicken, pasta, sauce; n/pasta with chicken; p/7.00;";
        AddDishCommand.addDish(d3);
        String d4 = "i/flatbread, bacon, cheese; p/3.00; n/bacon flatbread;";
        AddDishCommand.addDish(d4);
    }

    @Test
    public void searchDishTestA() {
        start();
        HashMap<String, Dish> matchingA = SearchDishCommand.searchDish("chicken");
        assertTrue(matchingA.containsKey("chicken biryani"));
        assertTrue(matchingA.containsKey("pasta with chicken"));
        assertFalse(matchingA.containsKey("bacon flatbread"));
        assertFalse(matchingA.containsKey("bacon pizza"));
    }

    @Test
    public void searchDishTestB() {
        start();
        HashMap<String, Dish> matchingA = SearchDishCommand.searchDish("bacon");
        assertTrue(matchingA.containsKey("bacon flatbread"));
        assertTrue(matchingA.containsKey("bacon pizza"));
        assertFalse(matchingA.containsKey("chicken biryani"));
        assertFalse(matchingA.containsKey("pasta with chicken"));
        HashMap<String, Dish> matchingB = SearchDishCommand.searchDish("bac");
        assertTrue(matchingB.containsKey("bacon flatbread"));
        assertTrue(matchingB.containsKey("bacon pizza"));
        assertFalse(matchingB.containsKey("chicken biryani"));
        assertFalse(matchingB.containsKey("pasta with chicken"));
    }

    @Test
    public void searchDishTestC() {
        start();
        HashMap<String, Dish> matchingA = SearchDishCommand.searchDish("cheese");
        assertFalse(matchingA.containsKey("bacon flatbread"));
        assertFalse(matchingA.containsKey("bacon pizza"));
        assertFalse(matchingA.containsKey("chicken biryani"));
        assertFalse(matchingA.containsKey("pasta with chicken"));
    }

    @Test
    public void searchDishTestD() {
        start();
        HashMap<String, Dish> matchingA = SearchDishCommand.searchDish("pizza");
        assertFalse(matchingA.containsKey("bacon flatbread"));
        assertTrue(matchingA.containsKey("bacon pizza"));
        assertFalse(matchingA.containsKey("chicken biryani"));
        assertFalse(matchingA.containsKey("pasta with chicken"));
    }
}
