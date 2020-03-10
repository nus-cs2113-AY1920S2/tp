import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class MenuTest {

    @Test
    public void parseIngredientsTest() {
        String d1a = "n/bacon pizza; i/cheese, bacon;";
        ArrayList<String> l1 = new ArrayList<String>();
        l1.add("cheese");
        l1.add("bacon");
        assertEquals(l1, Menu.parseIngredients(d1a));
        String d1b = "i/cheese, bacon; n/bacon pizza;";
        assertEquals(l1, Menu.parseIngredients(d1b));
        String d2 = "i/chicken, rice, chili; n/chicken biryani;";
        ArrayList<String> l2 = new ArrayList<String>();
        l2.add("chicken");
        l2.add("rice");
        l2.add("chili");
        assertEquals(l2, Menu.parseIngredients(d2));
    }

    @Test
    public void parseNameTest() {
        String d1 = "n/bacon pizza; i/cheese, bacon;";
        assertEquals("bacon pizza", Menu.parseName(d1));
        String d2 = "i/chicken, rice, chili; n/chicken biryani;";
        assertEquals("chicken biryani", Menu.parseName(d2));
    }

    @Test
    public void addDishTest() {
        String d1 = "n/bacon pizza; i/cheese, bacon;";
        Menu.addDish(d1);
        assertTrue(Menu.getDishMap().containsKey("bacon pizza"));
        String d2 = "i/chicken, rice, chili; n/chicken biryani;";
        Menu.addDish(d2);
        assertTrue(Menu.getDishMap().containsKey("chicken biryani"));
    }

    @Test
    public void deleteDishTest() {
        String d1 = "n/bacon pizza; i/cheese, bacon;";
        Menu.addDish(d1);
        assertTrue(Menu.getDishMap().containsKey("bacon pizza"));
        String d2 = "i/chicken, rice, chili; n/chicken biryani;";
        Menu.addDish(d2);
        assertTrue(Menu.getDishMap().containsKey("chicken biryani"));
        Menu.deleteDish("bacon pizza");
        assertFalse(Menu.getDishMap().containsKey("bacon pizza"));
        Menu.deleteDish("chicken biryani");
        assertFalse(Menu.getDishMap().containsKey("chicken biryani"));
    }

}
