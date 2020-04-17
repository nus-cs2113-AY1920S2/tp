package seedu.kitchenhelper.command;

import org.junit.jupiter.api.Test;
import seedu.kitchenhelper.object.ingredient.Ingredient;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DeleteIngredientCommandTest {
    @Test
    public void deleteIngredientByIndex() {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        new AddIngredientCommand("Beef", "Meat", 30, 20.2, "20/12/2020")
                                .addToCategory("Meat", ingredientList);
        new AddIngredientCommand("kailan", "Vegetable", 30, 30.45, "18/12/2020")
                                .addToCategory("Vegetable", ingredientList);

        int index = 1; //Beef
        DeleteIngredientCommand deleteIngredientQuantity = new DeleteIngredientCommand(index - 1, 2);
        deleteIngredientQuantity.deleteIngredientByIndex(ingredientList);
        assertEquals(ingredientList.get(index - 1).getQuantity(), 28);

        DeleteIngredientCommand deleteIngredientQuantityExceed = new DeleteIngredientCommand(index, 30);
        deleteIngredientQuantityExceed.deleteIngredientByIndex(ingredientList);
        assertEquals(ingredientList.get(index - 1).getQuantity(), 28);

        index = 2; //kailan

        DeleteIngredientCommand deleteKnownIngredient = new DeleteIngredientCommand(index - 1, null);
        deleteKnownIngredient.deleteIngredientByIndex(ingredientList);
        assertEquals(ingredientList.size(), 1);

        DeleteIngredientCommand deleteUnknownIngredient = new DeleteIngredientCommand(3, null);
        deleteUnknownIngredient.deleteIngredientByIndex(ingredientList);
        assertEquals(ingredientList.size(), 1);
    }

    @Test
    public void deleteIngredient() {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        new AddIngredientCommand("Beef", "Meat", 30, 20.2, "20/12/2020")
                .addToCategory("Meat", ingredientList);
        new AddIngredientCommand("kailan", "Vegetable", 30, 30.45, "18/12/2020")
                .addToCategory("Vegetable", ingredientList);
        DeleteIngredientCommand deleteIngredientQuantity = new DeleteIngredientCommand(1, 30);
        deleteIngredientQuantity.deleteIngredientByIndex(ingredientList);
        assertEquals(ingredientList.size(), 1);

        //quantity == 0 case and quantity is not null
        int index = 1; //Beef
        DeleteIngredientCommand deleteIngredient = new DeleteIngredientCommand(index - 1, 2);
        Ingredient ingredientToDelete = ingredientList.get(index - 1);
        deleteIngredient.deleteIngredient(ingredientToDelete, ingredientList);
        assertEquals(ingredientList.size(), 0);

        new AddIngredientCommand("kailan", "Vegetable", 30, 30.45, "18/12/2020")
                .addToCategory("Vegetable", ingredientList);
        //else case
        index = 1; //kailan
        DeleteIngredientCommand deleteIngredientQuantityNull = new DeleteIngredientCommand(index - 1, null);
        ingredientToDelete =  ingredientList.get(index - 1);
        deleteIngredientQuantityNull.deleteIngredient(ingredientToDelete, ingredientList);
        assertNotEquals(ingredientList.size(), 1); //supposed to be zero


    }

    @Test
    public void deleteQuantity() {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        new AddIngredientCommand("Beef", "Meat", 30, 20.2, "20/12/2020")
                .addToCategory("Meat", ingredientList);
        new AddIngredientCommand("kailan", "Vegetable", 30, 30.45, "18/12/2020")
                .addToCategory("Vegetable", ingredientList);

        //test whether quantity > 0
        System.out.println(ingredientList.size());
        int index = 1;
        DeleteIngredientCommand deleteIngredientByQuantity = new DeleteIngredientCommand(index - 1, 29);
        Ingredient ingredientToDeleteQuantity1 = ingredientList.get(index - 1);
        deleteIngredientByQuantity.deleteQuantity(ingredientToDeleteQuantity1);
        assertEquals(ingredientToDeleteQuantity1.getQuantity(), 1);
        //else case
        index = 2;
        DeleteIngredientCommand deleteIngredientByQuantity2 = new DeleteIngredientCommand(index - 1, -1);
        Ingredient ingredientToDeleteQuantity2 = ingredientList.get(index - 1);
        deleteIngredientByQuantity2.deleteQuantity(ingredientToDeleteQuantity2);
        assertEquals(ingredientToDeleteQuantity2.getQuantity(), 30);
    }

    @Test
    public void updateNewQuantity() {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        new AddIngredientCommand("Beef", "Meat", 30, 20.2, "20/12/2020")
                .addToCategory("Meat", ingredientList);
        new AddIngredientCommand("kailan", "Vegetable", 30, 30.45, "18/12/2020")
                .addToCategory("Vegetable", ingredientList);

        int index = 1; //beef
        Ingredient ingredientToUpdate;
        DeleteIngredientCommand updateOutOfRangeQuantity = new DeleteIngredientCommand(index - 1, 40);
        ingredientToUpdate = ingredientList.get(index - 1);
        updateOutOfRangeQuantity.updateNewQuantity(-10, ingredientToUpdate);
        assertEquals(ingredientToUpdate.getQuantity(), 30);

        index = 2; //kailan
        DeleteIngredientCommand updateRangeQuantity = new DeleteIngredientCommand(index - 1, 2);
        ingredientToUpdate = ingredientList.get(index - 1);
        updateRangeQuantity.updateNewQuantity(28, ingredientToUpdate);
        assertEquals(ingredientToUpdate.getQuantity(), 28);
    }
}
