package seedu.kitchenhelper.command;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ListIngredientCommandTest {

    @Test
    public void listCommandIngredient_validCategoryName_correctlyConstructed() throws Exception {
        ListIngredientCommand command = new ListIngredientCommand("all");
        assertEquals("all",command.getCategory());
        assertTrue(command.checkCategoryValid());
    }

    @Test
    public void listCommandIngredient_invalidCategoryName_correctlyConstructed() throws Exception {
        ListIngredientCommand command = new ListIngredientCommand("laptop");
        assertEquals("laptop",command.getCategory());
        assertFalse(command.checkCategoryValid());
    }

    @Test
    public void listCommandIngredient_invalidCategoryName2_correctlyConstructed() throws Exception {
        ListIngredientCommand command = new ListIngredientCommand("123");
        assertEquals("123",command.getCategory());
        assertFalse(command.checkCategoryValid());
    }




}
