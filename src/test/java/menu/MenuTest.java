package menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import commands.AddDishCommand;
import commands.DeleteDishCommand;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import menu.Menu;

class MenuTest {

    @Test
    public void parseIngredientsTest() {
        Menu m = new Menu();
        ArrayList<String> l1 = new ArrayList<String>();
        l1.add("cheese");
        l1.add("bacon");
        String d1a = "n/bacon pizza; i/cheese, bacon;";
        assertEquals(l1, AddDishCommand.parseIngredients(d1a));
        String d1b = "i/cheese, bacon; n/bacon pizza;";
        assertEquals(l1, AddDishCommand.parseIngredients(d1b));
        ArrayList<String> l2 = new ArrayList<String>();
        l2.add("chicken");
        l2.add("rice");
        l2.add("chili");
        String d2 = "i/chicken, rice, chili; n/chicken biryani;";
        assertEquals(l2, AddDishCommand.parseIngredients(d2));
    }

    @Test
    public void parseNameTest() {
        Menu m = new Menu();
        String d1 = "n/bacon pizza; i/cheese, bacon;";
        assertEquals("bacon pizza", AddDishCommand.parseName(d1));
        String d2 = "i/chicken, rice, chili; n/chicken biryani;";
        assertEquals("chicken biryani", AddDishCommand.parseName(d2));
    }

    @Test
    public void addDishTest() {
        Menu m = new Menu();
        String d1 = "n/bacon pizza; i/cheese, bacon; p/5.00;";
        AddDishCommand.addDish(d1);
        assertTrue(Menu.getDishMap().containsKey("bacon pizza"));
        String d2 = "i/chicken, rice, chili; n/chicken biryani; p/10.00;";
        AddDishCommand.addDish(d2);
        assertTrue(Menu.getDishMap().containsKey("chicken biryani"));
    }

    @Test
    public void deleteDishTest() {
        Menu m = new Menu();
        String d1 = "n/bacon pizza; i/cheese, bacon; p/5.00;";
        AddDishCommand.addDish(d1);
        assertTrue(Menu.getDishMap().containsKey("bacon pizza"));
        String d2 = "i/chicken, rice, chili; n/chicken biryani; p/10.00;";
        AddDishCommand.addDish(d2);
        assertTrue(Menu.getDishMap().containsKey("chicken biryani"));
        DeleteDishCommand.deleteDish("bacon pizza");
        assertFalse(Menu.getDishMap().containsKey("bacon pizza"));
        DeleteDishCommand.deleteDish("chicken biryani");
        assertFalse(Menu.getDishMap().containsKey("chicken biryani"));
    }

}
