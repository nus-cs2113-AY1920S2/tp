package commands;

import menu.Menu;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddDishCommandTest {

    @Test
    public void parseIngredientsTestA() {
        Menu m = new Menu();
        ArrayList<String> l1 = new ArrayList<String>();
        l1.add("cheese");
        l1.add("bacon");
        String d1a = "n/bacon pizza; p/5.00; i/cheese, bacon;";
        assertEquals(l1, AddDishCommand.parseIngredients(d1a));
        String d1b = "p/10.00; i/cheese, bacon; n/bacon pizza;";
        assertEquals(l1, AddDishCommand.parseIngredients(d1b));
    }

    @Test
    public void parseIngredientsTestB() {
        Menu m = new Menu();
        ArrayList<String> l2 = new ArrayList<String>();
        l2.add("chicken");
        l2.add("rice");
        l2.add("chili");
        String d2 = "i/chicken, rice, chili; n/chicken biryani; p/12.00;";
        assertEquals(l2, AddDishCommand.parseIngredients(d2));
    }

    @Test
    public void parseNameTestA() {
        Menu m = new Menu();
        String d1 = "n/bacon pizza; p/3.00; i/cheese, bacon;";
        assertEquals("bacon pizza", AddDishCommand.parseName(d1));
    }

    @Test
    public void parseNameTestB() {
        Menu m = new Menu();
        String d2 = "p/6.00; i/chicken, rice, chili; n/chicken biryani;";
        assertEquals("chicken biryani", AddDishCommand.parseName(d2));
    }

    @Test
    public void parsePriceTestA() {
        Menu m = new Menu();
        String d1 = "n/bacon pizza; p/3.00; i/cheese, bacon;";
        assertEquals(3.00, AddDishCommand.parsePrice(d1));
    }

    @Test
    public void parsePriceTestB() {
        Menu m = new Menu();
        String d2 = "p/6.00; i/chicken, rice, chili; n/chicken biryani;";
        assertEquals(6.00, AddDishCommand.parsePrice(d2));
    }

    @Test
    public void addDishTestA() {
        Menu m = new Menu();
        String d1 = "n/bacon pizza; i/cheese, bacon; p/5.00;";
        AddDishCommand.addDish(d1);
        assertTrue(Menu.getDishMap().containsKey("bacon pizza"));
        String d2 = "i/chicken, rice, chili; n/chicken biryani; p/10.00;";
        AddDishCommand.addDish(d2);
        assertTrue(Menu.getDishMap().containsKey("chicken biryani"));
    }

    @Test
    public void addDishTestB() {
        Menu m = new Menu();
        String d2 = "i/chicken, rice, chili; n/chicken biryani; p/10.00;";
        AddDishCommand.addDish(d2);
        assertTrue(Menu.getDishMap().containsKey("chicken biryani"));
    }

}
