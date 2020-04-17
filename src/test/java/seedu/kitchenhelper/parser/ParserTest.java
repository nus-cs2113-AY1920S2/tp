package seedu.kitchenhelper.parser;

import org.junit.jupiter.api.Test;
import seedu.kitchenhelper.KitchenHelper;
import seedu.kitchenhelper.command.AddChoreCommand;
import seedu.kitchenhelper.command.AddIngredientCommand;
import seedu.kitchenhelper.command.DeleteRecipeCommand;
import seedu.kitchenhelper.command.DeleteChoreCommand;
import seedu.kitchenhelper.command.DeleteIngredientCommand;
import seedu.kitchenhelper.command.InvalidCommand;
import seedu.kitchenhelper.command.SearchChoreCommand;
import seedu.kitchenhelper.command.SearchRecipeCommand;
import seedu.kitchenhelper.command.SearchIngredientCommand;
import seedu.kitchenhelper.common.Messages;
import seedu.kitchenhelper.exception.KitchenHelperException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.kitchenhelper.parser.Parser.splitInputLine;

class ParserTest {
    
    @Test
    void parseUserCommand_testPass() throws KitchenHelperException {
        assertTrue(new Parser().parseUserCommand(
                "addingredient /n Beef /c Meat /q 30 /p 20.2 /e 20/02/2022") instanceof AddIngredientCommand);
        assertTrue(new Parser().parseUserCommand("searchingredient Beef") instanceof SearchIngredientCommand);
        assertTrue(new Parser().parseUserCommand("searchrecipe chicken") instanceof SearchRecipeCommand);
        assertTrue(new Parser().parseUserCommand("searchchore groceries") instanceof SearchChoreCommand);
    }
    
    @Test
    void parseUserCommand_testFail() throws KitchenHelperException {
        assertFalse(new Parser()
                .parseUserCommand("dada /n Beef /c Meat /q 30 /p 20.2 /e 20/02/2020") instanceof AddIngredientCommand);
    }
    
    @Test
    void prepareAddInventory_testPass() {
        String correctAttributes = "/n Beef /c Meat /q 30 /p 20.2 /e 20/02/2022";
        assertTrue(new Parser().prepareAddIngredient(correctAttributes) instanceof AddIngredientCommand);
        String output = String.format(Messages.MESSAGE_ADD_INGREDIENT_SUCCESS, "Beef", "Meat", 30, 20.2, "20/02/2022");
        assertEquals(output, showToConsole(new KitchenHelper()
                .executeCommand(new Parser().prepareAddIngredient(correctAttributes)).feedbackToUser));
    }
    
    @Test
    void prepareAddInventory_testFail() {
        String incorrectAttributes = "/ Beef /c Meat /q 30 /p 20.2 /e 20/02/2020";
        assertTrue(new Parser().prepareAddIngredient(incorrectAttributes) instanceof InvalidCommand);
        String output = "Invalid Command Format!\n"
                        + "Adds a ingredient to the ingredient list. /n <INGREDIENT> /c <CATEGORY> /q <QUANTITY> /p "
                        + "<PRICE> /e"
                        + " <EXPIRY>\n" + "Example: addingredient /n Beef /c Meat /q 1 /p 13.5 /e 13/02/2022";
        assertEquals(output, showToConsole(new KitchenHelper()
                .executeCommand(new Parser().prepareAddIngredient(incorrectAttributes)).feedbackToUser));
    }
    
    @Test
    void prepareAddChore_testPass() {
        String correctAttributesWithString = "buy groceries /by Tuesday 12pm";
        assertTrue(new Parser().prepareAddChore(correctAttributesWithString) instanceof AddChoreCommand);
        String correctAttributesWithDate = "buy groceries /by 14/04/2020 12:00";
        AddChoreCommand addChoreCommandDate
                = (AddChoreCommand) new Parser().prepareAddChore(correctAttributesWithDate);
        assertTrue(addChoreCommandDate.getDateStr().isEmpty());
        String overdueDate = "buy groceries /by 14/03/2020 12:00";
        assertTrue(new Parser().prepareAddChore(overdueDate) instanceof InvalidCommand);
    }
    
    @Test
    void prepareAddChore_testFail() {
        String incorrectAttributes = "buy groceries";
        String output = "Invalid Command Format!\nAdds a chore to the chore list. "
                + "TASK /by DEADLINE OR TASK /by <dd/MM/yyyy HH:mm>\n"
                        + "Example: addchore buy groceries /by Tuesday 12pm "
                + "OR addchore buy groceries /by 14/04/2020 12:00";
        assertEquals(output, showToConsole(
                new KitchenHelper().executeCommand(new Parser().prepareAddChore(incorrectAttributes)).feedbackToUser));
    }
    
    @Test
    void prepareDeleteIngredient_testPass() throws KitchenHelperException {
        String correctAttributes = "/i 1";
        //checking the instance of the DeleteIngredientCommand
        assertTrue(new Parser().prepareDeleteIngredient(correctAttributes) instanceof DeleteIngredientCommand);
    }
    
    @Test
    void prepareDeleteIngredient_testFail() throws KitchenHelperException {
        String incorrectAttributes = "/i 1";
        //checking the instance of the DeleteIngredientCommand
        assertTrue(new Parser().prepareDeleteIngredient(incorrectAttributes) instanceof DeleteIngredientCommand);
        String output = String.format(DeleteIngredientCommand.COMMAND_FAILURE);
        assertEquals(output, showToConsole(new KitchenHelper()
                .executeCommand(new Parser().prepareDeleteIngredient(incorrectAttributes)).feedbackToUser));
    }

    @Test
    void prepareDeleteRecipeIndex_testPass() throws KitchenHelperException {
        String correctAttributes = "/i 1";
        //checking the instance of the DeleteRecipeCommand
        assertTrue(new Parser().prepareDeleteRecipe(correctAttributes) instanceof DeleteRecipeCommand);
    }

    @Test
    void prepareDeleteRecipeIndex_testFail() throws KitchenHelperException {
        String incorrectAttributes = "/i -1";
        //checking the instance of the DeleteRecipeCommand
        assertTrue(new Parser().prepareDeleteRecipe(incorrectAttributes) instanceof DeleteRecipeCommand);
        String output = String.format(DeleteRecipeCommand.COMMAND_FAILURE);
        assertEquals(output, showToConsole(new KitchenHelper()
                .executeCommand(new Parser().prepareDeleteRecipe(incorrectAttributes)).feedbackToUser));
    }

    @Test
    void prepareDeleteRecipeName_testPass() throws KitchenHelperException {
        String correctAttributes = "/n Chicken Salad";
        //checking the instance of the DeleteRecipeCommand
        assertTrue(new Parser().prepareDeleteRecipe(correctAttributes) instanceof DeleteRecipeCommand);
    }

    @Test
    void prepareDeleteRecipeName_testFail() throws KitchenHelperException {
        String incorrectAttributes = "/n Chicken";
        //checking the instance of the DeleteRecipeCommand
        assertTrue(new Parser().prepareDeleteRecipe(incorrectAttributes) instanceof DeleteRecipeCommand);
        String output = String.format(DeleteRecipeCommand.COMMAND_FAILURE);
        assertEquals(output, showToConsole(new KitchenHelper()
                .executeCommand(new Parser().prepareDeleteRecipe(incorrectAttributes)).feedbackToUser));
    }
    
    @Test
    void prepareDeleteChore_testPass() {
        String correctAttributes = "1";
        assertTrue(new Parser().prepareDeleteChore(correctAttributes) instanceof DeleteChoreCommand);
    }
    
    @Test
    void prepareDeleteChore_testFail() {
        String incorrectAttributes = "one";
        String output = "Invalid Command Format!\n" + "Deletes a chore from the chore list. <index>\n"
                        + "Example: deletechore 1";
        assertEquals(output, showToConsole(new KitchenHelper()
                .executeCommand(new Parser().prepareDeleteChore(incorrectAttributes)).feedbackToUser));
    }
    
    @Test
    void splitInputLine_testPass() {
        String userInput = "addingredient /n Beef /c Meat /q 30 /p 20.2 /e 20/02/2020";
        assertEquals("addingredient", splitInputLine(userInput, " ")[0]);
        assertEquals("/n Beef /c Meat /q 30 /p 20.2 /e 20/02/2020", splitInputLine(userInput, " ")[1]);
    }
    
    @Test
    void splitInputLine_testFail() {
        String userInput = "addingredient /n Beef /c Meat /q 30 /p 20.2 /e 20/02/2020";
        assertNotEquals("addingredient", splitInputLine(userInput, "")[0]);
        assertNotEquals("/n Beef /c Meat /q 30 /p 20.2 /e 20/02/2020", splitInputLine(userInput, "/p")[1]);
    }
    
    @Test
    void isValidUserInputFormat_testPass() {
        String regex = "/n( )+[a-zA-Z]+( [a-zA-Z]+)*( )+/c( )+[a-zA-Z]+( )+/q( )+[0-9]+( )+/p( )+\\d+(\\.\\d{1,2})?( )"
                       + "+/e( )+\\d{2}/\\d{2}/\\d{4}( )*";
        assertTrue(new Parser().isValidUserInputFormat("/n Beef /c Meat /q 30 /p 20.2 /e 20/02/2020", regex));
        assertTrue(new Parser().isValidUserInputFormat("/n Beef cubes /c Meat /q 30 /p 20.2 /e 20/02/2020", regex));
        assertTrue(new Parser().isValidUserInputFormat("/n Kailan /c Vegetable /q 30 /p 20 /e 20/02/2020", regex));
        assertTrue(new Parser().isValidUserInputFormat("/n miLK /c dairy /q 30 /p 20.2 /e 20/02/2020", regex));
        assertTrue(new Parser().isValidUserInputFormat("/n APPLE /c fRuit /q 20 /p 20.22 /e 20/02/2020", regex));
        assertTrue(new Parser().isValidUserInputFormat("/n Rice /c Staple /q 30 /p 222.11 /e 20/02/2020", regex));
        assertTrue(new Parser()
                .isValidUserInputFormat("/n apple cider alcohol drink /c Drink /q 30 /p 50.1 /e 20/02/2020", regex));
        assertTrue(new Parser().isValidUserInputFormat("/n spoon /c miscellaneous /q 30 /p 20.2 /e 20/02/2020", regex));
        assertTrue(new Parser().isValidUserInputFormat("/n Beef cubes /c Meat /q 30 /p 20.2 /e 20/02/2020", regex));
        // Extra space between ingredient'category and identifier /q
        assertTrue(new Parser().isValidUserInputFormat("/n APPLE /c fRuit  /q 20 /p 20.22 /e 20/02/2020", regex));
    }
    
    @Test
    void isValidUserInputFormat_testFail() {
        String regex = "/n( )+[a-zA-Z]+( [a-zA-Z]+)*( )+/c( )+[a-zA-Z]+( )+/q( )+[0-9]+( )+/p( )+\\d+(\\.\\d{1,2})?( )"
                       + "+/e( )+\\d{2}/\\d{2}/\\d{4}( )*";
        // Invalid price notation
        assertFalse(new Parser().isValidUserInputFormat("/n Beef /c Meat /q 30 /p 20.212 /e 20/02/2020", regex));
        // Invalid Date format
        assertFalse(new Parser().isValidUserInputFormat("/n Beef cubes /c Meat /q 30 /p 20.2 /e 12-13/2020", regex));
        // Missing identifier /n
        assertFalse(new Parser().isValidUserInputFormat("Kailan /c Vegetable /q 30 /p 20 /e 20/02/2020", regex));
        // Missing space between ingredient name and identifier /c
        assertFalse(new Parser().isValidUserInputFormat("/n miLK/c dairy /q 30 /p 20.2 /e 20/02/2020", regex));
        // Invalid Date format, consist of 3 digit in day in a month
        assertFalse(new Parser().isValidUserInputFormat("/n Rice /c Staple /q 30 /p 222.11 /e 201/02/2020", regex));
        // Missing parameter
        assertFalse(new Parser().isValidUserInputFormat("/n Beef cubes /c Meat /q 30 /p /e 20/02/2020", regex));
    }
    
    String showToConsole(String... message) {
        StringBuilder output = new StringBuilder();
        for (String m : message) {
            output.append(m);
        }
        return output.toString();
    }
    
}
