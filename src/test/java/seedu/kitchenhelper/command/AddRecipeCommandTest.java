package seedu.kitchenhelper.command;

import org.junit.jupiter.api.Test;
import seedu.kitchenhelper.exception.KitchenHelperException;
import seedu.kitchenhelper.object.Recipe;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AddRecipeCommandTest {

    @Test
    public void addRecipe_testPass() throws KitchenHelperException {
        HashMap<String[], Integer> parsedIngr = new HashMap<>();
        String[] ingr = new String[2];
        ingr[0] = "milo";
        ingr[1] = "drink";
        parsedIngr.put(ingr, 10);
        String attributes = "recipe /n Chicken Salad /i Chicken Breast:2:meat, Lettuce:4:vegetable";
        ArrayList<Recipe> recipeList = new ArrayList<>();
        AddRecipeCommand newRecipe = new AddRecipeCommand();
        newRecipe.setAttributesOfCmd(attributes, parsedIngr);
        newRecipe.addRecipe(attributes, recipeList);
        assertEquals(1, recipeList.size());
    }

    @Test
    public void addRecipe_testFail() throws KitchenHelperException {
        HashMap<String[], Integer> parsedIngr = new HashMap<>();
        String[] ingr = new String[2];
        ingr[0] = "milo";
        ingr[1] = " ";
        parsedIngr.put(ingr, 10);
        String attributes = "recipe /n Chicken Salad /i Chicken Breast:2:meat, Lettuce:4:vegetable";
        ArrayList<Recipe> recipeList = new ArrayList<>();
        AddRecipeCommand newRecipe = new AddRecipeCommand();
        newRecipe.setAttributesOfCmd(attributes, parsedIngr);
        newRecipe.addRecipe(attributes, recipeList);
        assertNotEquals("drink", recipeList.get(0).getRecipeItem().getClass());
    }

    @Test
    public void checkIfRecipeExist_testPass() throws KitchenHelperException {
        HashMap<String[], Integer> parsedIngr = new HashMap<>();
        String[] ingr = new String[2];
        ingr[0] = "milo";
        ingr[1] = "drink";
        parsedIngr.put(ingr, 10);
        String attributes = "recipe /n Chicken Salad /i Chicken Breast:2:meat, Lettuce:4:vegetable";
        ArrayList<Recipe> recipeList = new ArrayList<>();
        AddRecipeCommand newRecipe = new AddRecipeCommand();
        newRecipe.setAttributesOfCmd(attributes, parsedIngr);
        newRecipe.addRecipe(attributes, recipeList);
        assertEquals(Boolean.TRUE, newRecipe.checkIfRecipeExist("Chicken Salad", recipeList));
    }
}
