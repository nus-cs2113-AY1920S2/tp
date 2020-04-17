package seedu.kitchenhelper.command;

import org.junit.jupiter.api.Test;
import seedu.kitchenhelper.exception.KitchenHelperException;
import seedu.kitchenhelper.object.Chore;
import seedu.kitchenhelper.object.Recipe;
import seedu.kitchenhelper.object.ingredient.Ingredient;
import seedu.kitchenhelper.parser.Parser;
import seedu.kitchenhelper.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchChoreCommandTest {
    
    @Test
    void execute_notEmpty() throws KitchenHelperException, IOException {
        ArrayList<Ingredient> ingredientArrayList = new ArrayList<>();
        ArrayList<Recipe> recipeArrayList = new ArrayList<>();
        ArrayList<Chore> choreArrayList = new ArrayList<>();
        choreArrayList.add(new Chore("buy groceries", "Tuesday 12pm"));
        choreArrayList.add(new Chore("buy groceries", "Wednesday 12pm"));
        String expectedOutput = "Here are your matching chores in your list"
                                + Ui.LS
                                + "1.[x] buy groceries (by: Tuesday 12pm) [Location: Index 1]"
                                + Ui.LS
                                + "2.[x] buy groceries (by: Wednesday 12pm) [Location: Index 2]";
        String output = new Parser().parseUserCommand("searchchore groceries")
                .execute(ingredientArrayList, recipeArrayList, choreArrayList).feedbackToUser;
        assertEquals(expectedOutput, output);
    }
    
    @Test
    void execute_empty() throws KitchenHelperException, IOException {
        ArrayList<Ingredient> ingredientArrayList = new ArrayList<>();
        ArrayList<Recipe> recipeArrayList = new ArrayList<>();
        ArrayList<Chore> choreArrayList = new ArrayList<>();
        String expectedOutput = "There are no matching chores in your list.";
        String output = new Parser().parseUserCommand("searchchore groceries")
                .execute(ingredientArrayList, recipeArrayList, choreArrayList).feedbackToUser;
        assertEquals(expectedOutput, output);
    }
    
    @Test
    void execute_notFound() throws KitchenHelperException, IOException {
        ArrayList<Ingredient> ingredientArrayList = new ArrayList<>();
        ArrayList<Recipe> recipeArrayList = new ArrayList<>();
        ArrayList<Chore> choreArrayList = new ArrayList<>();
        choreArrayList.add(new Chore("buy groceries", "Tuesday 12pm"));
        choreArrayList.add(new Chore("buy groceries", "Wednesday 12pm"));
        String expectedOutput = "There are no matching chores in your list.";
        String output = new Parser().parseUserCommand("searchchore fruits")
                .execute(ingredientArrayList, recipeArrayList, choreArrayList).feedbackToUser;
        assertEquals(expectedOutput, output);
    }
}
