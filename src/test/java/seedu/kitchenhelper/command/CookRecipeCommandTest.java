package seedu.kitchenhelper.command;

import org.junit.jupiter.api.Test;
import seedu.kitchenhelper.exception.KitchenHelperException;
import seedu.kitchenhelper.object.Recipe;
import seedu.kitchenhelper.object.ingredient.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CookRecipeCommandTest {

    @Test
    public void cookRecipe_testPassCase1() throws KitchenHelperException {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        new AddIngredientCommand("Chicken Breast", "Meat", 30, 20.2,
                "20/12/2020").addToCategory("Meat", ingredientList);
        new AddIngredientCommand("Lettuce", "Vegetable", 15, 20.2,
                "15/12/2020").addToCategory("Vegetable", ingredientList);
        assertEquals(2, ingredientList.size());
        assertEquals(30, ingredientList.get(0).getQuantity());
        assertEquals(15, ingredientList.get(1).getQuantity());
        HashMap<String[], Integer> parsedIngr = new HashMap<>();
        String[] ingr = new String[2];
        ingr[0] = "Chicken Breast";
        ingr[1] = "meat";
        parsedIngr.put(ingr, 2);
        String[] ingr2 = new String[2];
        ingr2[0] = "Lettuce";
        ingr2[1] = "vegetable";
        parsedIngr.put(ingr2, 4);
        String attributes = "recipe /n Chicken Salad /i Chicken Breast:2:meat, Lettuce:4:vegetable";
        ArrayList<Recipe> recipeList = new ArrayList<>();
        AddRecipeCommand newRecipe = new AddRecipeCommand();
        newRecipe.setAttributesOfCmd(attributes, parsedIngr);
        newRecipe.addRecipe(attributes, recipeList);
        CookRecipeCommand cookRecipe = new CookRecipeCommand();
        cookRecipe.setRecipeName("Chicken Salad");
        cookRecipe.setRecipePax(2);
        assertEquals(Boolean.TRUE, cookRecipe.checkForSufficientIngredient(ingredientList, recipeList.get(0)));
        assertEquals("Chicken Salad was cooked with a pax of 2",
                cookRecipe.cookRecipe(ingredientList, recipeList));
    }

    @Test
    public void cookRecipe_testPassCase2() throws KitchenHelperException {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        new AddIngredientCommand("Chicken Breast", "Meat", 1, 20.2,
                "20/12/2020").addToCategory("Meat", ingredientList);
        new AddIngredientCommand("Lettuce", "Vegetable", 5, 20.2,
                "15/12/2020").addToCategory("Vegetable", ingredientList);
        new AddIngredientCommand("Lettuce", "Vegetable", 5, 20.2,
                "15/12/2020").addToCategory("Vegetable", ingredientList);
        HashMap<String[], Integer> parsedIngr = new HashMap<>();
        String[] ingr = new String[2];
        ingr[0] = "Chicken Breast";
        ingr[1] = "meat";
        parsedIngr.put(ingr, 2);
        String[] ingr2 = new String[2];
        ingr2[0] = "Lettuce";
        ingr2[1] = "vegetable";
        parsedIngr.put(ingr2, 4);
        String attributes = "recipe /n Chicken Salad /i Chicken Breast:2:meat, Lettuce:4:vegetable";
        ArrayList<Recipe> recipeList = new ArrayList<>();
        AddRecipeCommand newRecipe = new AddRecipeCommand();
        newRecipe.setAttributesOfCmd(attributes, parsedIngr);
        newRecipe.addRecipe(attributes, recipeList);
        CookRecipeCommand cookRecipe = new CookRecipeCommand();
        cookRecipe.setRecipeName("Chicken Salad");
        cookRecipe.setRecipePax(2);
        assertEquals("There are insufficient/missing ingredients!",
                cookRecipe.cookRecipe(ingredientList, recipeList));
    }

    @Test
    public void cookRecipe_testPassCase3() throws KitchenHelperException {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        new AddIngredientCommand("Chicken Breast", "Meat", 5, 20.2,
                "20/12/2020").addToCategory("Meat", ingredientList);
        new AddIngredientCommand("Lettuce", "Vegetable", 5, 20.2,
                "15/02/2020").addToCategory("Vegetable", ingredientList);
        new AddIngredientCommand("Lettuce", "Vegetable", 5, 20.2,
                "15/12/2020").addToCategory("Vegetable", ingredientList);
        HashMap<String[], Integer> parsedIngr = new HashMap<>();
        String[] ingr = new String[2];
        ingr[0] = "Chicken Breast";
        ingr[1] = "meat";
        parsedIngr.put(ingr, 2);
        String[] ingr2 = new String[2];
        ingr2[0] = "Lettuce";
        ingr2[1] = "vegetable";
        parsedIngr.put(ingr2, 4);
        String attributes = "recipe /n Chicken Salad /i Chicken Breast:2:meat, Lettuce:4:vegetable";
        ArrayList<Recipe> recipeList = new ArrayList<>();
        AddRecipeCommand newRecipe = new AddRecipeCommand();
        newRecipe.setAttributesOfCmd(attributes, parsedIngr);
        newRecipe.addRecipe(attributes, recipeList);
        CookRecipeCommand cookRecipe = new CookRecipeCommand();
        cookRecipe.setRecipeName("Chicken Salad");
        cookRecipe.setRecipePax(2);
        assertEquals("There are insufficient/missing ingredients!"
                + "\nPlease check for these expired ingredients: lettuce",
                cookRecipe.cookRecipe(ingredientList, recipeList));
    }

    @Test
    public void cookRecipe_testPassCase4() {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        new AddIngredientCommand("Chicken Breast", "Meat", 5, 20.2,
                "20/12/2020").addToCategory("Meat", ingredientList);
        new AddIngredientCommand("Lettuce", "Vegetable", 5, 20.2,
                "15/02/2020").addToCategory("Vegetable", ingredientList);
        CookRecipeCommand cookRecipe = new CookRecipeCommand();
        cookRecipe.setRecipeName("Chicken Salad");
        ArrayList<Recipe> recipeList = new ArrayList<>();
        assertEquals("The given recipe name does not exist!", cookRecipe.cookRecipe(ingredientList, recipeList));
    }

    @Test
    public void craftExpiredList_testPass() {
        ArrayList<String> expiredIngrNames = new ArrayList<>();
        expiredIngrNames.add("hl milk");
        CookRecipeCommand newrecipe = new CookRecipeCommand();
        newrecipe.setExpiredIngrNames(expiredIngrNames);

        assertEquals("hl milk, ", newrecipe.craftExpiredList());
    }

    @Test
    public void craftExpiredList_testFail() {
        ArrayList<String> expiredIngrNames = new ArrayList<>();
        expiredIngrNames.add("hl milk");
        CookRecipeCommand newrecipe = new CookRecipeCommand();
        newrecipe.setExpiredIngrNames(expiredIngrNames);
        assertNotEquals("hl milk", newrecipe.craftExpiredList());
    }

    @Test
    public void checkIfRecipeExist_testPass() throws KitchenHelperException {
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
        CookRecipeCommand cookRecipe = new CookRecipeCommand();
        cookRecipe.setRecipeName("Chicken Salad");
        assertEquals(1, cookRecipe.checkIfRecipeExist(recipeList));
    }

    @Test
    public void checkIfRecipeExist_testFail() throws KitchenHelperException {
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
        CookRecipeCommand cookRecipe = new CookRecipeCommand();
        cookRecipe.setRecipeName("Warm Milk");
        assertEquals(2, cookRecipe.checkIfRecipeExist(recipeList));
    }

    @Test
    public void getTotalIngredientQty_testPass() {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        new AddIngredientCommand("Chicken Breast", "Meat", 30, 20.2,
                "20/12/2020").addToCategory("Meat", ingredientList);
        new AddIngredientCommand("Chicken Breast", "Meat", 5, 20.2,
                "25/12/2020").addToCategory("Meat", ingredientList);
        assertEquals(2, ingredientList.size());
        CookRecipeCommand cookRecipe = new CookRecipeCommand();
        assertEquals(35, cookRecipe.getTotalIngredientQty("Chicken Breast",
                "Meat", ingredientList));
    }

    @Test
    public void getTotalIngredientQty_testFail() {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        new AddIngredientCommand("Chicken Breast", "Meat", 30, 20.2,
                "20/12/2020").addToCategory("Meat", ingredientList);
        new AddIngredientCommand("Chicken Breast", "Meat", 5, 20.2,
                "25/12/2020").addToCategory("Meat", ingredientList);
        assertEquals(2, ingredientList.size());
        CookRecipeCommand cookRecipe = new CookRecipeCommand();
        assertNotEquals(50, cookRecipe.getTotalIngredientQty("Chicken Breast",
                "Meat", ingredientList));
    }

    @Test
    public void getIngredientsWithSameName_testPass() {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        new AddIngredientCommand("Chicken Breast", "Meat", 30, 20.2,
                "20/12/2020").addToCategory("Meat", ingredientList);
        new AddIngredientCommand("Chicken Breast", "Meat", 5, 20.2,
                "25/12/2020").addToCategory("Meat", ingredientList);
        new AddIngredientCommand("Lettuce", "Vegetable", 3, 20.2,
                "15/12/2020").addToCategory("Vegetable", ingredientList);
        assertEquals(3, ingredientList.size());
        ArrayList<Ingredient> ingredientList2 = new ArrayList<>();
        new AddIngredientCommand("Chicken Breast", "Meat", 30, 20.2,
                "20/12/2020").addToCategory("Meat", ingredientList2);
        new AddIngredientCommand("Chicken Breast", "Meat", 5, 20.2,
                "25/12/2020").addToCategory("Meat", ingredientList2);
        assertEquals(2, ingredientList2.size());
        CookRecipeCommand cookRecipe = new CookRecipeCommand();
        assertEquals(ingredientList2, cookRecipe.getIngredientsWithSameName(ingredientList,
                "Chicken Breast"));
    }

    @Test
    public void getIngredientsWithSameName_testFail() {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        new AddIngredientCommand("Chicken Breast", "Meat", 30, 20.2,
                "20/12/2020").addToCategory("Meat", ingredientList);
        new AddIngredientCommand("Chicken Breast", "Meat", 5, 20.2,
                "25/12/2020").addToCategory("Meat", ingredientList);
        new AddIngredientCommand("Lettuce", "Vegetable", 3, 20.2,
                "15/12/2020").addToCategory("Meat", ingredientList);
        assertEquals(3, ingredientList.size());
        CookRecipeCommand cookRecipe = new CookRecipeCommand();
        assertNotEquals(ingredientList, cookRecipe.getIngredientsWithSameName(ingredientList,
                "Chicken Breast"));
    }

    @Test
    public void checkForSufficientIngredient_testPass() throws KitchenHelperException {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        new AddIngredientCommand("Chicken Breast", "Meat", 30, 20.2,
                "20/12/2020").addToCategory("Meat", ingredientList);
        new AddIngredientCommand("Chicken Breast", "Meat", 5, 20.2,
                "25/12/2020").addToCategory("Meat", ingredientList);
        new AddIngredientCommand("Lettuce", "Vegetable", 3, 20.2,
                "15/12/2020").addToCategory("Meat", ingredientList);
        assertEquals(3, ingredientList.size());
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
        CookRecipeCommand cookRecipe = new CookRecipeCommand();
        assertEquals(Boolean.TRUE, cookRecipe.checkForSufficientIngredient(ingredientList, recipeList.get(0)));
    }

    @Test
    public void checkForSufficientIngredient_testFail() throws KitchenHelperException {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        new AddIngredientCommand("Chicken Breast", "Meat", 1, 20.2, "20/12/2020").addToCategory("Meat", ingredientList);
        new AddIngredientCommand("Lettuce", "Vegetable", 3, 20.2, "15/12/2020").addToCategory("Meat", ingredientList);
        assertEquals(2, ingredientList.size());
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
        CookRecipeCommand cookRecipe = new CookRecipeCommand();
        cookRecipe.setRecipeName("Chicken Salad");
        cookRecipe.setRecipePax(2);
        assertNotEquals(Boolean.TRUE, cookRecipe.checkForSufficientIngredient(ingredientList, recipeList.get(0)));
    }
}
