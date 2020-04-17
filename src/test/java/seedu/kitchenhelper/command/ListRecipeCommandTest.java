package seedu.kitchenhelper.command;

import org.junit.jupiter.api.Test;
import seedu.kitchenhelper.object.Recipe;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ListRecipeCommandTest {

    @Test
    public void listCommandRecipe_validItemNumber_correctlyConstructed() throws Exception {
        HashMap<String[], Integer> parsedIngr = new HashMap<>();
        String[] ingr = new String[2];
        ingr[0] = "milo";
        ingr[1] = "drink";
        parsedIngr.put(ingr, 10);
        String attributes = "recipe /n Chicken Salad /i Chicken Breast:2:meat, Lettuce:4:vegetable";
        ListRecipeCommand command = new ListRecipeCommand("1");
        ArrayList<Recipe> recipeList = new ArrayList<>();
        AddRecipeCommand newRecipe = new AddRecipeCommand();
        newRecipe.setAttributesOfCmd(attributes, parsedIngr);
        newRecipe.addRecipe(attributes, recipeList);

        // individual fields that compare privacy to simplify this
        assertEquals(1,command.getItemNumber());
        assertTrue(command.checkItemValid(1, recipeList));
    }

    @Test
    public void listCommandRecipe_invalidItemNumber_correctlyConstructed() throws Exception {
        HashMap<String[], Integer> parsedIngr = new HashMap<>();
        String[] ingr = new String[2];
        ingr[0] = "milo";
        ingr[1] = "drink";
        parsedIngr.put(ingr, 10);
        String attributes = "recipe /n Chicken Salad /i Chicken Breast:2:meat, Lettuce:4:vegetable";
        ListRecipeCommand command = new ListRecipeCommand("1");
        ArrayList<Recipe> recipeList = new ArrayList<>();
        AddRecipeCommand newRecipe = new AddRecipeCommand();
        newRecipe.setAttributesOfCmd(attributes, parsedIngr);
        newRecipe.addRecipe(attributes, recipeList);

        // individual fields that compare privacy to simplify this
        assertEquals(1,command.getItemNumber());
        assertFalse(command.checkItemValid(2, recipeList));
    }






}
