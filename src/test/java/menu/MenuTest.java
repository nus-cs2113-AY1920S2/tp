package menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertEquals(l1, m.parseIngredients(d1a));
        String d1b = "i/cheese, bacon; n/bacon pizza;";
        assertEquals(l1, m.parseIngredients(d1b));
        ArrayList<String> l2 = new ArrayList<String>();
        l2.add("chicken");
        l2.add("rice");
        l2.add("chili");
        String d2 = "i/chicken, rice, chili; n/chicken biryani;";
        assertEquals(l2, m.parseIngredients(d2));
    }

    @Test
    public void parseNameTest() {
        Menu m = new Menu();
        String d1 = "n/bacon pizza; i/cheese, bacon;";
        assertEquals("bacon pizza", m.parseName(d1));
        String d2 = "i/chicken, rice, chili; n/chicken biryani;";
        assertEquals("chicken biryani", m.parseName(d2));
    }

    @Test
    public void addDishTest() {
        Menu m = new Menu();
        String d1 = "n/bacon pizza; i/cheese, bacon;";
        m.addDish(d1);
        assertTrue(m.getDishMap().containsKey("bacon pizza"));
        String d2 = "i/chicken, rice, chili; n/chicken biryani;";
        m.addDish(d2);
        assertTrue(m.getDishMap().containsKey("chicken biryani"));
    }

    @Test
    public void deleteDishTest() {
        Menu m = new Menu();
        String d1 = "n/bacon pizza; i/cheese, bacon;";
        m.addDish(d1);
        assertTrue(m.getDishMap().containsKey("bacon pizza"));
        String d2 = "i/chicken, rice, chili; n/chicken biryani;";
        m.addDish(d2);
        assertTrue(m.getDishMap().containsKey("chicken biryani"));
        m.deleteDish("bacon pizza");
        assertFalse(m.getDishMap().containsKey("bacon pizza"));
        m.deleteDish("chicken biryani");
        assertFalse(m.getDishMap().containsKey("chicken biryani"));
        m.printDishes();
    }

}
