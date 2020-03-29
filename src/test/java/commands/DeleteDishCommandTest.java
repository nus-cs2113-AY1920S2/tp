package commands;

import menu.Menu;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteDishCommandTest {

    @Test
    public void deleteDishTestA() {
        Menu m = new Menu();
        String d1 = "n/bacon pizza; i/cheese, bacon; p/5.00;";
        AddDishCommand.addDish(d1);
        assertTrue(Menu.getDishMap().containsKey("bacon pizza"));
        String d2 = "i/chicken, rice, chili; n/chicken biryani; p/10.00;";
        AddDishCommand.addDish(d2);
        assertTrue(Menu.getDishMap().containsKey("chicken biryani"));
        DeleteDishCommand.deleteDish("bacon pizza");
        assertFalse(Menu.getDishMap().containsKey("bacon pizza"));
        assertTrue(Menu.getDishMap().containsKey("chicken biryani"));
    }

    @Test
    public void deleteDishTestB() {
        Menu m = new Menu();
        String d1 = "n/bacon pizza; i/cheese, bacon; p/5.00;";
        AddDishCommand.addDish(d1);
        assertTrue(Menu.getDishMap().containsKey("bacon pizza"));
        String d2 = "i/chicken, rice, chili; n/chicken biryani; p/10.00;";
        AddDishCommand.addDish(d2);
        DeleteDishCommand.deleteDish("bacon pizza");
        assertFalse(Menu.getDishMap().containsKey("bacon pizza"));
        DeleteDishCommand.deleteDish("chicken biryani");
        assertFalse(Menu.getDishMap().containsKey("chicken biryani"));
    }

}
