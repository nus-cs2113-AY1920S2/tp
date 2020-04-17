package seedu.kitchenhelper.command;

import org.junit.jupiter.api.Test;
import seedu.kitchenhelper.exception.KitchenHelperException;
import seedu.kitchenhelper.object.Recipe;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteRecipeCommandTest {
    @Test
    public void deleteRecipeByName() throws KitchenHelperException {
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

        //successful deletion
        DeleteRecipeCommand deleteCmd = new DeleteRecipeCommand("Chicken");
        deleteCmd.deleteRecipeByName(recipeList);
        assertEquals(1, recipeList.size());

        //unsuccessful deletion
        DeleteRecipeCommand deleteCmd2 = new DeleteRecipeCommand("Chicken Salad");
        deleteCmd2.deleteRecipeByName(recipeList);
        assertEquals(0, recipeList.size());
    }

    @Test
    public void deleteRecipeByIndex() throws KitchenHelperException {
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

        //unsuccessful deletion
        DeleteRecipeCommand deleteCmd = new DeleteRecipeCommand(3);
        deleteCmd.deleteRecipeByIndex(recipeList);
        assertEquals(1, recipeList.size());

        //successful deletion
        DeleteRecipeCommand deleteCmd2 = new DeleteRecipeCommand(0);
        deleteCmd2.deleteRecipeByIndex(recipeList);
        assertEquals(0, recipeList.size());
    }

    @Test
    public void getRecipeIndex() throws KitchenHelperException {
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

        int index = 0;

        //Case 1: No such recipe
        DeleteRecipeCommand deleteCmd = new DeleteRecipeCommand("Chicken");
        index = deleteCmd.getRecipeIndex(deleteCmd.getObjectVariables().trim(), recipeList);
        assertEquals(index, -1);

        //Case 2: Recipe is found and an index is returned
        DeleteRecipeCommand deleteCmd2 = new DeleteRecipeCommand("Chicken Salad");
        index = deleteCmd2.getRecipeIndex(deleteCmd.getObjectVariables().trim(), recipeList);
        assertEquals(index, 0);

    }
}
